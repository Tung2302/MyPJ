package com.BikeStoreApi.BikeStoreApi.repositories;

import com.BikeStoreApi.BikeStoreApi.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:searchKey%"
            + " OR p.size LIKE %:searchKey%"
            + " OR p.color LIKE %:searchKey%"
            + " OR p.category.categoryName LIKE %:searchKey%"
            +" OR CONCAT(p.cost, '') LIKE %:searchKey%")
    List<Product> searchProductsByNameCostSizeColor(@Param("searchKey") String searchKey);


}
