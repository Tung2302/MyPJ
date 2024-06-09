package com.BikeStoreApi.BikeStoreApi.dtos;

public class ProductDTO {
    private String name;
    private double cost;
    private String size;
    private int quantity;
    private String description;
    private String color;
    private String image;
    private int categoryId;

    public ProductDTO() {
    }

    public ProductDTO(String name, double cost, String size, int quantity, String description, String color, String image, int categoryId) {
        this.name = name;
        this.cost = cost;
        this.size = size;
        this.quantity = quantity;
        this.description = description;
        this.color = color;
        this.image = image;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
