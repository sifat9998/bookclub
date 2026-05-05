package com.bookclub.web;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistRestController {

    @Autowired
    private WishlistDao wishlistDao;

    // ✅ GET ALL ITEMS (USER-SPECIFIC)
    @GetMapping
    public List<WishlistItem> list(Authentication authentication) {

        String username = authentication.getName();

        return wishlistDao.list(username);
    }

    // ✅ GET SINGLE ITEM
    @GetMapping("/{id}")
    public WishlistItem find(@PathVariable String id) {

        return wishlistDao.find(id);
    }

    // ✅ CREATE NEW ITEM
    @PostMapping
    public WishlistItem add(@RequestBody WishlistItem wishlistItem,
                            Authentication authentication) {

        wishlistItem.setUsername(authentication.getName());

        wishlistDao.add(wishlistItem);

        return wishlistItem;
    }

    // ✅ UPDATE ITEM
    @PutMapping("/{id}")
    public WishlistItem update(@PathVariable String id,
                               @RequestBody WishlistItem wishlistItem,
                               Authentication authentication) {

        wishlistItem.setId(id);
        wishlistItem.setUsername(authentication.getName());

        wishlistDao.update(wishlistItem);

        return wishlistItem;
    }

    // ✅ DELETE ITEM
    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable String id) {

        return wishlistDao.remove(id);
    }
}