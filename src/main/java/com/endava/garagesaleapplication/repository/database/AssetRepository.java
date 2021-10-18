package com.endava.garagesaleapplication.repository.database;

import com.endava.garagesaleapplication.model.Asset;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends CrudRepository<Asset, Integer> {

    @Query(value = "SELECT * FROM asset a WHERE a.quantity > 0",
            nativeQuery = true)
    List<Asset> findAvailableAssets();
}