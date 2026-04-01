package com.bookclub.dao.impl;

import com.bookclub.dao.WishlistDao;
import com.bookclub.model.WishlistItem;

import java.util.ArrayList;
import java.util.List;

public class MemWishlistDao implements WishlistDao {

    private List<WishlistItem> wishlist = new ArrayList<>();

    public MemWishlistDao() {
        wishlist.add(new WishlistItem("123", "Sample Book 1"));
        wishlist.add(new WishlistItem("456", "Sample Book 2"));
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