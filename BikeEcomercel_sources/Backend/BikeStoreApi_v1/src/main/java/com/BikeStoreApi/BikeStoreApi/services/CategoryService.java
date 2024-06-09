package com.BikeStoreApi.BikeStoreApi.services;

import com.BikeStoreApi.BikeStoreApi.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public List<Category> getAllCategories();
    public Category getCategoryById(int id);

    public Category saveCategory(Category category);

    public void deleteCategory(int id);

    public Optional<Category> findById(int id);

    public boolean checkExistId(Integer id);

//    boolean existByCategoryName(String name);
}
