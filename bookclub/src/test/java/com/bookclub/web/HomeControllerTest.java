package com.bookclub.web;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import com.bookclub.service.dao.BookOfTheMonthDao;
import com.bookclub.service.impl.RestBookDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestBookDao bookDao;

    @MockBean
    private BookOfTheMonthDao bookOfTheMonthDao;

    @Test
    void shouldReturnHomePage() throws Exception {

        mockMvc.perform(get("/")
                        .with(user("user").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void shouldReturnAboutPage() throws Exception {

        mockMvc.perform(get("/about")
                        .with(user("user").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(view().name("about"));
    }
}