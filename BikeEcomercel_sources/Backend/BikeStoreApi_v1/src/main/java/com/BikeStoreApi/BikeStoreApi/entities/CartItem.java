package com.BikeStoreApi.BikeStoreApi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Integer productId;
    private String image;
    private String name;
    private double price;
    private int quantity;
}
