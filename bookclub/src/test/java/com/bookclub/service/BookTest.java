package com.bookclub.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    void shouldCreateBook() {

        Book book = new Book(
                "123456789",
                "Harry Potter",
                "Fantasy",
                "https://test.com",
                300
        );

        assertEquals("Harry Potter", book.getTitle());
    }

    @Test
    void shouldReturnCorrectIsbn() {

        Book book = new Book();

        book.setIsbn("999999");

        assertEquals("999999", book.getIsbn());
    }
}