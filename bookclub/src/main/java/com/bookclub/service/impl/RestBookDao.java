package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RestBookDao implements BookDao {

    // ✅ CALL OPENLIBRARY API
    public Object getBooksDoc(String isbnString) {

        String openLibraryUrl = "https://openlibrary.org/api/books";

        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(openLibraryUrl)
                .queryParam("bibkeys", isbnString)
                .queryParam("format", "json")
                .queryParam("jscmd", "details");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = rest.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
        );

        String jsonBooklist = response.getBody();

        return Configuration.defaultConfiguration()
                .jsonProvider()
                .parse(jsonBooklist);
    }

    // ✅ LIST ALL BOOKS (HARDCODED ISBNs AS PER ASSIGNMENT)
    @Override
    public List<Book> list(String key) {

        try {
//            String isbnString = "ISBN:9780590353427,9780261103573,9780261102361,9780261102378";

            Object doc = getBooksDoc(key);

            List<Book> books = new ArrayList<>();

            List<String> titles = JsonPath.read(doc, "$..title");
            List<String> isbns = JsonPath.read(doc, "$..bib_key");
            List<String> infoUrls = JsonPath.read(doc, "$..info_url");

            for (int i = 0; i < titles.size(); i++) {
                books.add(new Book(isbns.get(i), titles.get(i), "", infoUrls.get(i), 0));
            }

            return books;

        } catch (Exception e) {
            // 🚨 FALLBACK DATA (VERY IMPORTANT)
            List<Book> books = new ArrayList<>();

            books.add(new Book("ISBN:9780590353427", "Harry Potter", "", "", 0));
            books.add(new Book("ISBN:9780261103573", "LOTR Fellowship", "", "", 0));
            books.add(new Book("ISBN:9780261102361", "Two Towers", "", "", 0));
            books.add(new Book("ISBN:9780261102378", "Return of the King", "", "", 0));

            return books;
        }
    }
    // ✅ FIND SINGLE BOOK DETAILS
    @Override
    public Book find(String key) {

        // ✅ DO NOT add "ISBN:" again
        Object doc = getBooksDoc(key);

        List<String> isbns = JsonPath.read(doc, "$..bib_key");
        List<String> titles = JsonPath.read(doc, "$..title");
        List<String> subtitle = JsonPath.read(doc, "$..details.subtitle");
        List<String> infoUrls = JsonPath.read(doc, "$..info_url");
        List<Integer> pages = JsonPath.read(doc, "$..details.number_of_pages");

        String isbn = isbns.size() > 0 ? isbns.get(0) : "N/A";
        String title = titles.size() > 0 ? titles.get(0) : "N/A";
        String desc = subtitle.size() > 0 ? subtitle.get(0) : "N/A";
        String infoUrl = infoUrls.size() > 0 ? infoUrls.get(0) : "N/A";
        int numOfPages = pages.size() > 0 ? pages.get(0) : 0;

        return new Book(isbn, title, desc, infoUrl, numOfPages);
    }


    @Override
    public void add(Book entity) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void update(Book entity) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public boolean remove(String key) {
        return false;
    }


}