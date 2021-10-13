package com.endava.garagesaleapplication.controller;

import com.endava.garagesaleapplication.data.asset.CustomerAssetRequest;
import com.endava.garagesaleapplication.data.asset.AssetResponse;
import com.endava.garagesaleapplication.data.asset.AssetStockResponse;
import com.endava.garagesaleapplication.data.asset.NewAssetRequest;
import com.endava.garagesaleapplication.data.order.OrderRequest;
import com.endava.garagesaleapplication.data.order.OrderResponse;
import com.endava.garagesaleapplication.facade.asset.AssetFacade;
import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Order;
import com.endava.garagesaleapplication.service.AssetService;
import com.endava.garagesaleapplication.validator.BindingRequestValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private AssetFacade assetFacade;

    @PostMapping()
    @ResponseBody
    public ResponseEntity<AssetResponse> addAsset(
            @Valid @RequestBody NewAssetRequest newAssetRequest, BindingResult bindingResult) {
        BindingRequestValidation.check(bindingResult);

        try {
            AssetResponse assetResponse = handleAddAssetFlow(newAssetRequest);
            return ResponseEntity.ok(assetResponse);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<AssetStockResponse>> getAll() {
        List<AssetStockResponse> assetStockResponse = this.assetFacade.getAssetStockResponse(this.assetService.getAllAvailableAssets());

        return ResponseEntity.ok(assetStockResponse);
    }

    private AssetResponse handleAddAssetFlow(NewAssetRequest newAssetRequest) {
        Asset asset = this.assetService.save(this.assetFacade.convertToAsset(newAssetRequest));
        AssetResponse assetResponse = this.assetFacade.convertToAssetResponse(asset);

        return assetResponse;
    }
}