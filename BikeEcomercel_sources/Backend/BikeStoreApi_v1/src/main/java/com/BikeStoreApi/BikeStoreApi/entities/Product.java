package com.BikeStoreApi.BikeStoreApi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name", length = 100)
    private String name;

    @Column(name = "product_cost")
    private double cost;

    @Column(name = "product_size", length = 20)
    private String size;

    @Column(name = "product_quantity")
    private int quantity;

    @Column(name = "product_description", length = 200)
    private String description;

    @Column(name = "product_color", length = 20)
    private String color;

    @Column(name = "product_image", length = 1000)
    private String image;

    @Column(name = "product_disable",  columnDefinition = "boolean default false")
    private Boolean disable;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;


}
