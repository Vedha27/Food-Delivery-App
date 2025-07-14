package com.daoimpl;

import java.util.HashMap;
import java.util.Map;

import com.model.CartItem;

public class Cart {

    private Map<Integer, CartItem> items = new HashMap<>();

    public void addItem(CartItem item)
    {
        int itemId = item.getItemId();
        if (items.containsKey(itemId)) 
        {
            CartItem existingItem = items.get(itemId);
            existingItem.setQuantity(existingItem.getQuantity() + 1);
        } else {
            items.put(itemId, item);
        }
    }

    public void increaseQuantity(int itemId)
    {
        if (items.containsKey(itemId))
        {
            CartItem item = items.get(itemId);
            item.setQuantity(item.getQuantity() + 1);
        }
    }

    public void decreaseQuantity(int itemId) 
    {
        if (items.containsKey(itemId))
        {
            CartItem item = items.get(itemId);
            int newQty = item.getQuantity() - 1;
            if (newQty <= 0)
            {
                items.remove(itemId);
            }
            else 
            {
                item.setQuantity(newQty);
            }
        }
    }

    public void removeItemById(int itemId) {
        items.remove(itemId);
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }
}
