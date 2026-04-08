package com.bookclub.service.impl;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class MemWishlistDao implements WishlistDao {

    private List<WishlistItem> wishlist = new ArrayList<>();

    // Constructor with sample data
    public MemWishlistDao() {
        wishlist.add(new WishlistItem("123", "Sample Book 1"));
        wishlist.add(new WishlistItem("456", "Sample Book 2"));
    }

    @Override
    public void add(WishlistItem entity) {
        wishlist.add(entity);
    }

    @Override
    public void update(WishlistItem entity) {
        WishlistItem existing = find(entity.getIsbn());
        if (existing != null) {
            existing.setTitle(entity.getTitle());
        }
    }

    @Override
    public boolean remove(WishlistItem entity) {
        return wishlist.remove(entity);
    }

    @Override
    public List<WishlistItem> list() {
        return wishlist;
    }

    @Override
    public WishlistItem find(String key) {
        return wishlist.stream()
                .filter(item -> item.getIsbn().equals(key))
                .findFirst()
                .orElse(null);
    }
}