package com.endava.garagesaleapplication.controller;

import com.endava.garagesaleapplication.data.asset.AssetResponse;
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

    @GetMapping(value = "/all")
    @ResponseBody
    public ResponseEntity<List<AssetResponse>> getAll() {
        List<AssetResponse> assetResponseList = this.assetFacade.getAssetResponse(this.assetService.getAllAssets());

        return ResponseEntity.ok(assetResponseList);
    }
}