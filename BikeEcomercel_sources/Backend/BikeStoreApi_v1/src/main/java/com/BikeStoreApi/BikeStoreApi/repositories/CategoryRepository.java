package com.BikeStoreApi.BikeStoreApi.repositories;

import com.BikeStoreApi.BikeStoreApi.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@EnableJpaRepositories
public interface CategoryRepository extends JpaRepository<Category,Integer> {
//    boolean existsByCategoryName(String categoryName);


}
