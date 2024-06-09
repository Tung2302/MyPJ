package com.BikeStoreApi.BikeStoreApi.services;

import com.BikeStoreApi.BikeStoreApi.entities.CartItem;

import java.util.Collection;

public interface ShoppingCartService {
    public  void add(CartItem item);
    public void remove(int id);
    public CartItem update(int productId,int quantity);
    public void clear();
    public Collection<CartItem> getAllItems();
    public int getCount();
    public double getAmount();
}
