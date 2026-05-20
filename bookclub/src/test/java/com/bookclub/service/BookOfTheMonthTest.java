package com.bookclub.service;

import com.bookclub.model.BookOfTheMonth;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookOfTheMonthTest {

    @Test
    void shouldCreateMonthlyBook() {

        BookOfTheMonth book =
                new BookOfTheMonth();

        book.setMonth(5);
        book.setIsbn("9780590353427");

        assertEquals(5, book.getMonth());
    }
}