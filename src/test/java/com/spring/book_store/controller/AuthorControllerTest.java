package com.spring.book_store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.book_store.entity.Book;
import com.spring.book_store.entity.Publisher;
import com.spring.book_store.model.AuthorDTO;
import com.spring.book_store.service.AuthorService;
import com.spring.book_store.service.AuthorServiceImplJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class AuthorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AuthorService authorService;

    private AuthorDTO authorDTO;

    @BeforeEach
    void setUp() {
       authorDTO = AuthorDTO.builder()
               .id(UUID.randomUUID())
               .name("Ali")
               .lastName("Rad")
               .build();
    }

    @Test
    void testListAuthors() throws Exception {
        Page<AuthorDTO> authorDTOPage = new PageImpl<>(Collections.singletonList(authorDTO));

        given(authorService.listOfAuthors(any(),any(),any(),any(),any(),any())).willReturn(authorDTOPage);
        mockMvc.perform(get(AuthorController.AUTHOR_PHATH)
                    .queryParam("name","ali")
                    .queryParam("lastName","rad")
                    .queryParam("pageNumber", String.valueOf(0))
                    .queryParam("pageSize",String.valueOf(10))
                    .queryParam("bookTitle","")
                    .queryParam("publisherLabel","")
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Ali"))
                .andExpect(jsonPath("$.content[0].lastName").value("Rad"));
    }

    @Test
    void testGetBeerById() throws Exception {
        given(authorService.getAuthorById(authorDTO.getId())).willReturn(Optional.of(authorDTO));
        mockMvc.perform(get(AuthorController.AUTHOR_PATH_ID,authorDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ali"));
    }

    @Test
    void testGetBeerByIdNotFound() throws Exception {
        given(authorService.getAuthorById(any(UUID.class))).willReturn(Optional.empty());
        mockMvc.perform(get(AuthorController.AUTHOR_PATH_ID,UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}