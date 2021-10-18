package com.endava.garagesaleapplication.service.impl;

import com.endava.garagesaleapplication.model.Asset;
import com.endava.garagesaleapplication.model.Category;
import com.endava.garagesaleapplication.model.Order;
import com.endava.garagesaleapplication.repository.memory.InMemoryRepository;
import com.endava.garagesaleapplication.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("assetService")
public class DefaultAssetService implements AssetService {

    @Autowired
    private InMemoryRepository<Asset> assetRepository;

    @Autowired
    private InMemoryRepository<Category> categoryRepository;

    public static Integer id = 4;

    @PostConstruct
    private void init() {           //ToDo: delete this method after BD implementation

        Category laptop = Category.CategoryBuilder.aCategory()
                .withId(0)
                .withName("Laptop").build();
        Category pc = Category.CategoryBuilder.aCategory()
                .withId(1)
                .withName("PC").build();

        this.categoryRepository.save(laptop);
        this.categoryRepository.save(pc);

        this.assetRepository.save(Asset.AssetBuilder.anAsset()
                .withId(1)
                .withCategory(laptop)
                .withPrice(150)
                .withIssues(List.of("Missing enter button", "Minor scratches"))
                .withQuantity(10).build());

        this.assetRepository.save(Asset.AssetBuilder.anAsset()
                .withId(2)
                .withCategory(laptop)
                .withPrice(200)
                .withIssues(List.of("Minor scratches"))
                .withQuantity(15).build());

        this.assetRepository.save(Asset.AssetBuilder.anAsset()
                .withId(3)
                .withCategory(pc)
                .withPrice(99)
                .withIssues(List.of("Minor scratches"))
                .withQuantity(20).build());
    }

    @Override
    public Asset save(Asset newAsset) {
        validationExecutor(newAsset);
        Category category = findCategoryInDb(newAsset.getCategory().getId());
        setAssetFields(newAsset, category);
        saveRequestInDb(newAsset);

        return newAsset;
    }

    private void validationExecutor(Asset newAsset) {
        findCategoryInDb(newAsset.getCategory().getId());
    }

    private Category findCategoryInDb(Integer categoryToFindId) {
        Optional<Category> categoryOptional = this.categoryRepository.get(categoryToFindId);
        if (categoryOptional.isEmpty()) {
            throw new IllegalArgumentException("The selected category does not exist ");
        }

        return categoryOptional.get();
    }

    private void setAssetFields(Asset newAsset, Category category) {
        newAsset.setId(id++);
        newAsset.setCategory(category);
    }

    private void saveRequestInDb(Asset newAsset) {
        this.assetRepository.save(newAsset);
    }

    @Override
    public List<Asset> getAllAvailableAssets() {
        List<Asset> assetList = this.assetRepository.getAll();
        assetList = getOnlyAvailableAssets(assetList);

        return assetList;
    }

    @Override
    public Asset getAsset(Integer id) {
        Optional<Asset> optionalAsset = this.assetRepository.get(id);
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
                    Optional<Asset> assetToFind = this.assetRepository.get(asset.getId());
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
                    this.assetRepository.update(asset);
                }
        );
    }

    @Override
    public void deleteAssetList(List<Asset> assetList) {
        assetList.forEach(
                asset -> this.assetRepository.delete(asset.getId()));
    }

    private List<Asset> getOnlyAvailableAssets(List<Asset> assetList) {
        return assetList.stream()
                .filter(asset -> asset.getQuantity() > 0)
                .collect(Collectors.toList());
    }
}