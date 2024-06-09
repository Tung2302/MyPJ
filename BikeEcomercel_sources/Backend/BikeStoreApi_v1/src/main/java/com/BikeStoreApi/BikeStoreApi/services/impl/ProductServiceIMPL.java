package com.BikeStoreApi.BikeStoreApi.services.impl;

import com.BikeStoreApi.BikeStoreApi.entities.Category;
import com.BikeStoreApi.BikeStoreApi.entities.Product;
import com.BikeStoreApi.BikeStoreApi.repositories.CategoryRepository;
import com.BikeStoreApi.BikeStoreApi.repositories.ProductRepository;
import com.BikeStoreApi.BikeStoreApi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceIMPL implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Product saveProduct(Product product, MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();


        //luu vao thu muc image
        String imagePath = "BikeStoreFontend/src/assets/images/product";
        Path directory = Paths.get(imagePath).toAbsolutePath().normalize();
        if (directory !=null) {
            Files.createDirectories(directory);
        }
        Path filePath = directory.resolve(filename);
        file.transferTo(filePath.toFile());
        product.setImage(filename);
        return productRepository.save(product);
    }



    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElseThrow();
    }

    @Override
    public boolean existById(int id) {
        return productRepository.existsById(id);
    }

    @Override
    public List<Product> getProductSearch(String searchKey) {
        return productRepository.searchProductsByNameCostSizeColor(searchKey);
    }




}
