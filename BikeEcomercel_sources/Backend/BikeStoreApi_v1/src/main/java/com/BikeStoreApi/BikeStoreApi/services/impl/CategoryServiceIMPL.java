package com.BikeStoreApi.BikeStoreApi.services.impl;

import com.BikeStoreApi.BikeStoreApi.entities.Category;
import com.BikeStoreApi.BikeStoreApi.repositories.CategoryRepository;
import com.BikeStoreApi.BikeStoreApi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceIMPL implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }


    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public boolean checkExistId(Integer id) {
        return categoryRepository.existsById(id);
    }

//    @Override
//    public boolean existByCategoryName(String name) {
//        return categoryRepository.existsByCategoryName(name);
//    }


}