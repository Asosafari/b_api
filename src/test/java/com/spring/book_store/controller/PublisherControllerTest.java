package com.spring.book_store.controller;

import com.spring.book_store.model.PublisherDTO;
import com.spring.book_store.service.PublisherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(PublisherController.class)
class PublisherControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PublisherService publisherService;

    PublisherDTO publisherDTO;

    @BeforeEach
    void setUp() {
        publisherDTO = PublisherDTO.builder()
                .label("Clarkson N. Potter")
                .zipCode("4466")
                .build();
    }

    @Test
    void listPublisher() throws Exception {
        Page<PublisherDTO> publisherDTOPage = new PageImpl<>(Collections.singletonList(publisherDTO));
        given(publisherService.listOfPublisher(any(),any(),any(),any(),any(),any())).willReturn(publisherDTOPage);

        mockMvc.perform(get(PublisherController.PUBLISHER_PATH)
                        .queryParam("label","Clarkson n. Potter")
                        .queryParam("bookTitle", "")
                        .queryParam("authorName","")
                        .queryParam("authorLastName", "")
                        .queryParam("pageNumber",String.valueOf(0))
                        .queryParam("pageSize",String.valueOf(10))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].label").value("Clarkson N. Potter"));
    }
}