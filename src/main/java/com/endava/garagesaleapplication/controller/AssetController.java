package com.endava.garagesaleapplication.controller;

import com.endava.garagesaleapplication.data.asset.AssetResponse;
import com.endava.garagesaleapplication.data.asset.AssetStockResponse;
import com.endava.garagesaleapplication.data.asset.NewAssetRequest;
import com.endava.garagesaleapplication.facade.asset.AssetFacade;
import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.service.AssetService;
import com.endava.garagesaleapplication.validator.BindingRequestValidation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private AssetFacade assetFacade;

    /**
     * API exposing add a new asset
     *
     * @param newAssetRequest - asset sent by the client
     * @param bindingResult   - result of validation
     * @return {@link AssetResponse} containing newly added asset
     */
    @PostMapping()
    @ResponseBody
    @Operation(summary = "Adds a new asset")
    public ResponseEntity<AssetResponse> addAsset(
            @Valid @RequestBody NewAssetRequest newAssetRequest, BindingResult bindingResult) {
        BindingRequestValidation.check(bindingResult);

        AssetResponse assetResponse = handleAddAssetFlow(newAssetRequest);
        return ResponseEntity.ok(assetResponse);
    }

    /**
     * API exposing all available assets
     *
     * @return {@link AssetStockResponse} a list containing all the available assets
     */
    @GetMapping()
    @ResponseBody
    @Operation(summary = "Shows all the available assets")
    public ResponseEntity<List<AssetStockResponse>> getAllAvailableAssets() {
        List<AssetStockResponse> assetStockResponse = this.assetFacade.getAssetStockResponse(this.assetService.getAllAvailableAssets());

        return ResponseEntity.ok(assetStockResponse);
    }

    private AssetResponse handleAddAssetFlow(NewAssetRequest newAssetRequest) {
        Asset asset = this.assetService.save(this.assetFacade.convertToAsset(newAssetRequest));
        return this.assetFacade.convertToAssetResponse(asset);
    }
}