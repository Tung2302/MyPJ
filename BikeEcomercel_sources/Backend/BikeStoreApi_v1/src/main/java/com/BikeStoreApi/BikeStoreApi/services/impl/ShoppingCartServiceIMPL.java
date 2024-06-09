package com.BikeStoreApi.BikeStoreApi.services.impl;

import com.BikeStoreApi.BikeStoreApi.entities.CartItem;
import com.BikeStoreApi.BikeStoreApi.services.ShoppingCartService;
import org.springframework.stereotype.Service;



@Service
public class ShoppingCartServiceIMPL implements ShoppingCartService {
    Map<Integer,CartItem> maps = new HashMap<>();
    @Override
    public void add(CartItem item) {
        CartItem cartItem = maps.get(item.getProductId());
        if (cartItem==null){
            maps.put(item.getProductId(),item);
        }else {
            cartItem.setQuantity((cartItem.getQuantity()+1));
        }
    }

    @Override
    public void remove(int id) {
        maps.remove(id);

    }

    @Override
    public CartItem update(int productId, int quantity) {
        CartItem cartItem = maps.get(productId);
        cartItem.setQuantity(quantity);
        return cartItem;
    }
    @Override
    public void clear() {
        maps.clear();
    }

    @Override
    public Collection<CartItem> getAllItems() {
        return maps.values();
    }

    @Override
    public int getCount() {
        return maps.values().size();
    }

    @Override
    public double getAmount() {
        return maps.values().stream()
                .mapToDouble(item->item.getQuantity()*item.getPrice()).sum();
    }
}
