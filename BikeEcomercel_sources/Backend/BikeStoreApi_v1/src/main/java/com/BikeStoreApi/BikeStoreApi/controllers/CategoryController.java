package com.BikeStoreApi.BikeStoreApi.controllers;


import com.BikeStoreApi.BikeStoreApi.dtos.CategoryDTO;
import com.BikeStoreApi.BikeStoreApi.entities.Category;
import com.BikeStoreApi.BikeStoreApi.entities.ResponseObject;
import com.BikeStoreApi.BikeStoreApi.services.CategoryService;
import com.BikeStoreApi.BikeStoreApi.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/Categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        ResponseObject responseObject = new ResponseObject("Successed", "Categories retrieved successfully", categories);
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getCategoryById(@PathVariable int id) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            ResponseObject responseObject = new ResponseObject("Successed", "Category retrieved successfully", category);
            return ResponseEntity.ok(responseObject);
        } else {
            ResponseObject responseObject = new ResponseObject("Failed", "Category not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObject);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        try {
            Category savedCategory = categoryService.saveCategory(category);
            ResponseObject response = new ResponseObject("Successed", "Category created", savedCategory);
            return ResponseEntity.ok(response);
        }catch (Exception ex){

            ResponseObject response = new ResponseObject("Failed", "Category created", null);
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);        }


    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateCategory( @RequestBody Category category,@PathVariable int id) {
        Optional<Category> existingCategory = categoryService.findById(id);
        if (existingCategory == null) {
            ResponseObject response = new ResponseObject("Failed", "Category not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        }
        else{
            categoryService.saveCategory(category);
            ResponseObject response = new ResponseObject("Successed", "Category updated", category);
            return ResponseEntity.ok(response);
        }

    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteUser(@PathVariable Integer id){
        boolean exists = categoryService.checkExistId(id);
        if(exists){
            categoryService.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Successed","Delete Category Successfully","")
            );
        }
        return  ResponseEntity.status((HttpStatus.NOT_FOUND)).body(
                new ResponseObject("Failed","Cant find Category to delete","")

        );
    };

}

