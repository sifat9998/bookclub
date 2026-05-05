package com.bookclub.web;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.util.List;

@Controller
@RequestMapping("/wishlist")


public class WishlistController {

    @Autowired
    private WishlistDao wishlistDao;

    // ✅ Show all wishlist items
//    @GetMapping
//    public String showWishlist(Model model) {
//        List<WishlistItem> items = wishlistDao.list();
//        model.addAttribute("wishlist", items);
//        return "wishlist/list";
//    }

    @GetMapping
    public String showWishlist(Model model,Authentication authentication) {

        System.out.println("Logged in user: " + authentication.getName());
        return "wishlist/list";   // ✅ MUST BE THIS
    }

    // ✅ Show form to add new item
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("wishlistItem", new WishlistItem()); // ✅ REQUIRED
        return "wishlist/new";
    }

    @GetMapping("/{id}")
    public String showWishlistItem(@PathVariable String id, Model model) {

        WishlistItem item = wishlistDao.find(id);
        model.addAttribute("wishlistItem", item);

        return "wishlist/view";
    }


    @GetMapping("/remove/{id}")
    public String removeWishlistItem(@PathVariable String id) {

        wishlistDao.remove(id);

        return "redirect:/wishlist";
    }


//    @RequestMapping(method = RequestMethod.GET)
//    public String showWishlist() {
//        return "wishlist/list";
//    }

    // ✅ Handle form submission
    @PostMapping
    public String addWishlistItem(@Valid WishlistItem wishlistItem,
                                  BindingResult bindingResult,
                                  Authentication authentication) {

        if (bindingResult.hasErrors()) {
            return "wishlist/new";
        }


        wishlistItem.setUsername(authentication.getName());

        wishlistDao.add(wishlistItem);

        return "redirect:/wishlist";
    }


    @PostMapping("/update")
    public String updateWishlistItem(
            @Valid WishlistItem wishlistItem,
            BindingResult bindingResult,
            Authentication authentication) {

        // 🔥 set username
        wishlistItem.setUsername(authentication.getName());

        if (bindingResult.hasErrors()) {
            return "wishlist/view";
        }

        wishlistDao.update(wishlistItem);

        return "redirect:/wishlist";
    }

}