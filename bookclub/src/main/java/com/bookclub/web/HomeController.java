package com.bookclub.web;

import com.bookclub.model.Book;
import com.bookclub.service.impl.RestBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private RestBookDao bookDao;

    // ✅ HOME PAGE
    @GetMapping
    public String showHome(Model model) {
        List<Book> books = bookDao.list();
        model.addAttribute("books", books);
        return "index";
    }

    // ✅ ABOUT PAGE
    @GetMapping("/about")
    public String showAboutUs() {
        return "about";
    }

    // ✅ CONTACT PAGE
    @GetMapping("/contact")
    public String showContactUs() {
        return "contact";
    }

    // ✅ VIEW BOOK DETAILS (IMPORTANT FOR ASSIGNMENT)
    @GetMapping("/monthly-books/view")
    public String viewBook(@RequestParam String id, Model model) {
        Book book = bookDao.find(id);
        model.addAttribute("book", book);
        return "monthly-books/view";
    }
}