package com.BikeStoreApi.BikeStoreApi.services;

import com.BikeStoreApi.BikeStoreApi.entities.Category;
import com.BikeStoreApi.BikeStoreApi.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Product saveProduct(Product product,MultipartFile file) throws IOException;

    public void deleteProduct(Product deleteProduct);
    public Product getProductById(int id);
    public List<Product> getAllProducts();


    public Product findById(int id);

    public boolean existById(int id);

    public List<Product> getProductSearch(String searchKey);

}
