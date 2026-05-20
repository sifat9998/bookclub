package com.bookclub.web;

import com.bookclub.model.Book;
import com.bookclub.service.impl.RestBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bookclub.model.Book;
import com.bookclub.model.BookOfTheMonth;
import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;
import com.bookclub.service.impl.MongoBookOfTheMonthDao;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private RestBookDao bookDao;
    private BookOfTheMonthDao bookOfTheMonthDao = new MongoBookOfTheMonthDao();
    @Autowired
    public void setBookOfTheMonthDao(BookOfTheMonthDao bookOfTheMonthDao) {
        this.bookOfTheMonthDao = bookOfTheMonthDao;
    }

    // ✅ HOME PAGE
    @GetMapping
    public String showHome(Model model) {

        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int calMonth = cal.get(Calendar.MONTH) + 1;

        List<BookOfTheMonth> monthlyBooks =
                bookOfTheMonthDao.list(Integer.toString(calMonth));

        StringBuilder isbnBuilder = new StringBuilder();

        isbnBuilder.append("ISBN:");

        for (BookOfTheMonth monthlyBook : monthlyBooks) {
            isbnBuilder.append(monthlyBook.getIsbn()).append(",");
        }

        String isbnString =
                isbnBuilder.toString().substring(0,
                        isbnBuilder.toString().length() - 1);

        List<Book> books = bookDao.list(isbnString);

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

    // ✅ VIEW BOOK DETAILS
    @GetMapping("/monthly-books/view")
    public String viewBook(@RequestParam String id, Model model) {
        Book book = bookDao.find(id);
        model.addAttribute("book", book);
        return "monthly-books/view";
    }

}
