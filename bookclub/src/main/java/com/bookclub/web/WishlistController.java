package com.bookclub.web;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistDao wishlistDao;

    // ✅ Show all wishlist items
    @GetMapping
    public String showWishlist(Model model) {
        List<WishlistItem> items = wishlistDao.list();
        model.addAttribute("wishlist", items);
        return "wishlist/list";
    }

    // ✅ Show form to add new item
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("wishlistItem", new WishlistItem());
        return "wishlist/new";
    }

    // ✅ Handle form submission
    @PostMapping
    public String addWishlistItem(@Valid WishlistItem wishlistItem,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "wishlist/new";
        }

        wishlistDao.add(wishlistItem);
        return "redirect:/wishlist";
    }
}