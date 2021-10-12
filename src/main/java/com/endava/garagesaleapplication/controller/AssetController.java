package com.endava.garagesaleapplication.controller;

import com.endava.garagesaleapplication.data.asset.AssetStockResponse;
import com.endava.garagesaleapplication.facade.asset.AssetFacade;
import com.endava.garagesaleapplication.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private AssetFacade assetFacade;

    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<AssetStockResponse>> getAll() {
        List<AssetStockResponse> assetStockResponse = this.assetFacade.getAssetStockResponse(this.assetService.getAllAvailableAssets());

        return ResponseEntity.ok(assetStockResponse);
    }
}