package com.endava.garagesaleapplication.service.impl;

import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Category;
import com.endava.garagesaleapplication.model.Issue;
import com.endava.garagesaleapplication.model.Order;
import com.endava.garagesaleapplication.repository.database.AssetRepository;
import com.endava.garagesaleapplication.repository.database.CategoryRepository;
import com.endava.garagesaleapplication.repository.database.IssueRepository;
import com.endava.garagesaleapplication.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("assetService")
public class DefaultAssetService implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Override
    public Asset save(Asset newAsset) {
        Category category = findCategoryInDb(newAsset.getCategory().getId());
        setFieldsToAsset(newAsset, category);
        setAssetToIssueAndSaveInDb(newAsset.getIssueList(), newAsset);

        return saveInDb(newAsset);
    }

    @Override
    public List<Asset> getAllAvailableAssets() {
        return this.assetRepository.findAvailableAssets();
    }

    @Override
    public Asset getAsset(Integer id) {
        Optional<Asset> optionalAsset = this.assetRepository.findById(id);
        if (optionalAsset.isPresent()) {
            return optionalAsset.get();
        }
        throw new IllegalArgumentException("Asset not available");
    }

    @Override
    public List<Asset> findOrderedAssetsInDb(Order order) {
        StringBuilder message = new StringBuilder();
        List<Asset> assetList = new ArrayList<>();
        order.getAssetList().forEach(
                (asset) -> {
                    Optional<Asset> assetToFind = this.assetRepository.findById(asset.getId());
                    if (!assetToFind.isPresent() || assetToFind.get().getQuantity() < 1) {
                        message.append("   " + asset.getId());
                    } else {
                        assetList.add(assetToFind.get());
                    }
                }
        );

        if (message.length() != 0) {
            throw new IllegalArgumentException("The following assets are not available [ID]: " + message);
        }

        return assetList;
    }

    @Override
    public void decrementAssets(List<Asset> assetList) {
        assetList.forEach(
                asset -> {
                    asset.setQuantity(asset.getQuantity() - 1);
                    this.assetRepository.save(asset);
                }
        );
    }

    @Override
    public void deleteAssetList(List<Asset> assetList) {
        assetList.forEach(
                asset -> this.assetRepository.delete(asset));
    }

    private Category findCategoryInDb(Integer categoryToFindId) {
        Optional<Category> categoryOptional = this.categoryRepository.findById(categoryToFindId);
        if (categoryOptional.isEmpty()) {
            throw new IllegalArgumentException("The selected category does not exist ");
        }

        return categoryOptional.get();
    }

    private void setFieldsToAsset(Asset newAsset, Category category) {
        newAsset.setCategory(category);
    }

    private Asset saveInDb(Asset newAsset) {
        return this.assetRepository.save(newAsset);
    }

    private void setAssetToIssueAndSaveInDb(List<Issue> issueList, Asset asset) {
        issueList.forEach(issue -> {
            issue.setAsset(asset);
            this.issueRepository.save(issue);
        });
    }
}