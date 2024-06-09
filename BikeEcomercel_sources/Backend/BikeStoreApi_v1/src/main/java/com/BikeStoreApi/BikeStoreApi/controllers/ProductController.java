package com.BikeStoreApi.BikeStoreApi.controllers;

import com.BikeStoreApi.BikeStoreApi.dtos.ProductDTO;
import com.BikeStoreApi.BikeStoreApi.entities.Category;
import com.BikeStoreApi.BikeStoreApi.entities.Product;
import com.BikeStoreApi.BikeStoreApi.entities.ResponseObject;
import com.BikeStoreApi.BikeStoreApi.repositories.CategoryRepository;
import com.BikeStoreApi.BikeStoreApi.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/Products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/auth")
    public ResponseEntity<ResponseObject> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        ResponseObject responseObject = new ResponseObject("Successed", "Products retrieved successfully", products);
        return ResponseEntity.ok(responseObject);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            ResponseObject responseObject = new ResponseObject("Successed", "Product retrieved successfully", product);
            return ResponseEntity.ok(responseObject);
        } else {
            ResponseObject responseObject = new ResponseObject("Failed", "Product not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObject);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createProduct(@ModelAttribute ProductDTO productDTO, @RequestPart("file") MultipartFile file) throws IOException {
        Product product = modelMapper.map(productDTO, Product.class);
        Optional<Category> categoryOptional = categoryRepository.findById(productDTO.getCategoryId());
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            product.setCategory(category);
            product.setId(null);
            Product savedProduct = productService.saveProduct(product, file);
            ResponseObject response = new ResponseObject("Successed", "Product created", savedProduct);
            return ResponseEntity.ok(response);
        } else {
            // Nếu không tìm thấy Category, xử lý lỗi hoặc trả về lỗi
            ResponseObject response = new ResponseObject("Error", "Category not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateProduct(@PathVariable Integer id, @ModelAttribute ProductDTO productDTO ,@RequestPart("file")MultipartFile file) throws Exception {
        Product product = modelMapper.map(productDTO, Product.class);
        Optional<Category> categoryOptional = categoryRepository.findById(productDTO.getCategoryId());
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            product.setCategory(category);
            product.setId(id);
            Product savedProduct = productService.saveProduct(product, file);
            ResponseObject response = new ResponseObject("Successed", "Product updated", savedProduct);
            return ResponseEntity.ok(response);
        } else {
            // Nếu không tìm thấy Category, xử lý lỗi hoặc trả về lỗi
            ResponseObject response = new ResponseObject("Error", "Category not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable("id") int id) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct == null) {
            ResponseObject response = new ResponseObject("Error", "Product not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        productService.deleteProduct(existingProduct);
        ResponseObject response = new ResponseObject("Successed", "Product deleted successfully", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/auth/search-product")
    public ResponseEntity<ResponseObject> searchProduct(@RequestParam("searchKey") String searchKey){
        List<Product> searchResults = productService.getProductSearch(searchKey);
        if (searchResults!=null){
            ResponseObject response = new ResponseObject("Successed", "Products retrieved successfully", searchResults);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else {
            ResponseObject response = new ResponseObject("Error", "Product not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }



}








