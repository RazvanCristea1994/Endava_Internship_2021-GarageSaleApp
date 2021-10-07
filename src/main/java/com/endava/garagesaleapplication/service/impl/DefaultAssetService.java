package com.endava.garagesaleapplication.service.impl;

import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.repository.memory.DefaultAssetRepository;
import com.endava.garagesaleapplication.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("assetService")
public class DefaultAssetService implements AssetService {

    @Autowired
    private DefaultAssetRepository assetRepository;

    @Override
    public List<Asset> getAllAssets() {
        return this.assetRepository.getAll();
    }
}