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

    /**
     * This method structures saving a new asset in the database
     *
     * @param newAsset the product sent by the client
     * @return the saved product
     */
    @Override
    public Asset save(Asset newAsset) {
        Category category = findCategoryInDb(newAsset.getCategory().getId());
        setFieldsToAsset(newAsset, category);

        Asset savedAsset = saveInDb(newAsset);
        setAssetToIssueAndSaveInDb(newAsset.getIssueList(), newAsset);

        return savedAsset;
    }

    /**
     * This method shows all the products available
     *
     * @return a list of products
     */
    @Override
    public List<Asset> getAllAvailableAssets() {
        return this.assetRepository.findAvailableAssets();
    }

    /**
     * This method search for a product in the database
     *
     * @param id the id of the product
     * @return the product if it exists, otherwise throws IllegalArgumentException
     */
    @Override
    public Asset findById(Integer id) {
        Optional<Asset> optionalAsset = this.assetRepository.findById(id);
        if (optionalAsset.isPresent()) {
            return optionalAsset.get();
        }
        throw new IllegalArgumentException("Asset not available");
    }

    /**
     * This method search each product of an order in database
     * If even one product is not found, a message is build and IllegalArgumentException is thrown
     *
     * @param order the order sent by the client
     * @return the list of products found în database, or IllegalArgumentException if at least one does not exist
     */
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

    /**
     * This method decrements by 1 the quantity of each product in a list
     *
     * @param assetList list containing products
     */
    @Override
    public void decrementAssetsByOne(List<Asset> assetList) {
        assetList.forEach(
                asset -> {
                    asset.setQuantity(asset.getQuantity() - 1);
                    this.assetRepository.save(asset);
                }
        );
    }

    /**
     * This method deletes from the database each product in the provided list
     *
     * @param assetList the list of products that should be deleted
     */
    @Override
    public void deleteAssetList(List<Asset> assetList) {
        assetList.forEach(
                asset -> this.assetRepository.delete(asset));
    }

    /**
     * This method searches for a category in database
     *
     * @param categoryToFindId the id of the searched category
     * @return the category found in database, otherwise IllegalArgumentException is thrown
     */
    private Category findCategoryInDb(Integer categoryToFindId) {
        Optional<Category> categoryOptional = this.categoryRepository.findById(categoryToFindId);
        if (categoryOptional.isEmpty()) {
            throw new IllegalArgumentException("The selected category does not exist ");
        }

        return categoryOptional.get();
    }

    /**
     * This method sets to product the field that is indicated by the client and previously taken from the database
     *
     * @param newAsset the asset whose attribute should be set
     * @param category the category that should be set
     */
    private void setFieldsToAsset(Asset newAsset, Category category) {
        newAsset.setCategory(category);
    }

    /**
     * This method saves the new asset in the database
     *
     * @param newAsset the asset sent by the client with attributes previously set
     * @return the asset saved in the database
     */
    private Asset saveInDb(Asset newAsset) {
        return this.assetRepository.save(newAsset);
    }

    /**
     * This method sets the product to each of its issues (this provides the foreign key to link them in the database)
     * Then each issue is saved in the database
     *
     * @param issueList the list of issues corresponding to the product
     * @param asset     the asset to set
     */
    private void setAssetToIssueAndSaveInDb(List<Issue> issueList, Asset asset) {
        issueList.forEach(issue -> {
            issue.setAsset(asset);
            this.issueRepository.save(issue);
        });
    }
}