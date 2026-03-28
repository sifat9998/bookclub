package com.bookclub.web;

import com.bookclub.model.Book;
import com.bookclub.service.impl.MemBookDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")

public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model) {

        MemBookDao bookDao = new MemBookDao();
        List<Book> books = bookDao.list();

        model.addAttribute("books", books);

        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/about")
    public String showAboutUs(Model model) {
        return "about";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/contact")
    public String showContactUs(Model model) {
        return "contact";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getMonthlyBook(@PathVariable String id, Model model) {

        MemBookDao bookDao = new MemBookDao();
        Book book = bookDao.find(id);

        model.addAttribute("book", book);

        return "monthly-books/view";
    }

}

