package com.BikeStoreApi.BikeStoreApi.controllers;

import com.BikeStoreApi.BikeStoreApi.entities.CartItem;
import com.BikeStoreApi.BikeStoreApi.entities.Product;
import com.BikeStoreApi.BikeStoreApi.entities.ResponseObject;
import com.BikeStoreApi.BikeStoreApi.services.ProductService;
import com.BikeStoreApi.BikeStoreApi.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/shopping-cart")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = {"Authorization", "Origin"})
public class ShoppingCartController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getCarts(){
        Collection<CartItem> cartItemCollection=shoppingCartService.getAllItems();
        ResponseObject response = new ResponseObject("Successed", "Get all CartItem", cartItemCollection);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/add/{id}")
    public ResponseEntity<ResponseObject> addCart(@PathVariable Integer id){
        Product product = productService.findById(id);
        if (product !=null){
            CartItem item = new CartItem();
            item.setProductId(product.getId());
            item.setName(product.getName());
            item.setPrice(product.getCost());
            item.setImage(product.getImage());
            item.setQuantity(1);
            shoppingCartService.add(item);
            ResponseObject response = new ResponseObject("Successed", "CartItem add successfully", null);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else {
            ResponseObject response = new ResponseObject("Failed", "Cant find this product", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/count")
    ResponseEntity<ResponseObject> countCart(){
        int countCart = shoppingCartService.getCount();
        ResponseObject response = new ResponseObject("Successed", "Cart item number get successfully", countCart);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/total")
    ResponseEntity<ResponseObject> getTotal(){
        double amount = shoppingCartService.getAmount();
        ResponseObject response = new ResponseObject("Successed", "Cart item number get successfully", amount);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/remove/{id}")
    ResponseEntity<ResponseObject> removeCart(@PathVariable Integer id){
        Product product = productService.findById(id);
        if (product !=null){
            shoppingCartService.remove(id);
            ResponseObject response = new ResponseObject("Successed", "Remove CartItem successfully", null);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else {
            ResponseObject response = new ResponseObject("Failed", "Cant find product to remove", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/clear")
    ResponseEntity<ResponseObject> clearCart(){
        this.shoppingCartService.clear();
        ResponseObject response = new ResponseObject("Successed", "Remove All CartItem successfully", null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("update/{id}")
    ResponseEntity<ResponseObject> updateCart(@PathVariable Integer id ,@RequestBody int quantity){
        Product product = productService.findById(id);
        if (product !=null){
            CartItem cartItem = this.shoppingCartService.update(id,quantity);
            ResponseObject response = new ResponseObject("Successed", "Remove All CartItem successfully", cartItem);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else {
            ResponseObject response = new ResponseObject("Failed", "Cant find product to update", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }



}
