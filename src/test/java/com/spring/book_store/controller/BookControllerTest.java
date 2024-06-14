package com.spring.book_store.controller;

import com.spring.book_store.model.BookDTO;
import com.spring.book_store.service.AuthorService;
import com.spring.book_store.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;


    BookDTO bookDTO;

    @BeforeEach
    void setUp() {
        bookDTO = BookDTO.builder()
                .id(UUID.randomUUID())
                .title("The Dark Highlander")
                .build();
    }

    @Test
    void teatListBooks() throws Exception {
        Page<BookDTO> bookDTOPage = new PageImpl<>(Collections.singletonList(bookDTO));
        given(bookService.listOfBooks(any(),any(),any(),any(),any(),any()))
                .willReturn(bookDTOPage);

        mockMvc.perform(get(BookController.BOOK_PATH)
                        .queryParam("title","The Dark Highlander")
                        .queryParam("authorName","")
                        .queryParam("authorLastName","")
                        .queryParam("publisherLabel","")
                        .queryParam("pageNumber",String.valueOf(0))
                        .queryParam("pageSize",String.valueOf(10))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("The Dark Highlander"));
    }

    @Test
    void testGetBookById() throws Exception{
        given(bookService.getBookById(bookDTO.getId())).willReturn(Optional.of(bookDTO));
        mockMvc.perform(get(BookController.BOOK_PATH_ID,bookDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("The Dark Highlander"));

    }

    @Test
    void testBookNotFound() throws Exception {
        given(bookService.getBookById(any())).willReturn(Optional.empty());
        mockMvc.perform(get(BookController.BOOK_PATH_ID,UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}