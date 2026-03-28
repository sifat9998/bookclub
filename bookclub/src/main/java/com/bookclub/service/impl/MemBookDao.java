package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemBookDao implements BookDao {

    private List<Book> books = new ArrayList<>();

    public MemBookDao() {
        books.add(new Book("1", "Java Basics", "Intro to Java", 300, Arrays.asList("John Doe")));
        books.add(new Book("2", "Spring Boot", "Spring Boot Guide", 400, Arrays.asList("Jane Smith")));
        books.add(new Book("3", "Thymeleaf", "Template Engine", 250, Arrays.asList("Mark Lee")));
        books.add(new Book("4", "Microservices", "Architecture", 500, Arrays.asList("Chris Evans")));
        books.add(new Book("5", "Docker", "Containerization", 350, Arrays.asList("Elon Musk")));
    }

    @Override
    public List<Book> list() {
        return books;
    }

    @Override
    public Book find(String key) {
        for (Book book : books) {
            if (book.getIsbn().equals(key)) {
                return book;
            }
        }
        return new Book();
    }
}