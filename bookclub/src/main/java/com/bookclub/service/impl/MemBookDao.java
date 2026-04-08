package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
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
    public void add(Book entity) {
        books.add(entity);
    }

    @Override
    public void update(Book entity) {
        Book existing = find(entity.getIsbn());
        if (existing != null) {
            existing.setTitle(entity.getTitle());
            existing.setDescription(entity.getDescription());
        }
    }

    @Override
    public boolean remove(Book entity) {
        return books.remove(entity);
    }

    @Override
    public List<Book> list() {
        return books;
    }

    @Override
    public Book find(String key) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(key))
                .findFirst()
                .orElse(null);
    }
}