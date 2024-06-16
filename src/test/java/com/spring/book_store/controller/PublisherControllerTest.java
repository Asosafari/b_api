package com.spring.book_store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.book_store.model.PublisherDTO;
import com.spring.book_store.service.PublisherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;


@WebMvcTest(PublisherController.class)
class PublisherControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PublisherService publisherService;

    @Autowired
    ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<PublisherDTO> publisherDTOArgumentCaptor;

    PublisherDTO publisherDTO;

    @BeforeEach
    void setUp() {
        publisherDTO = PublisherDTO.builder()
                .id(UUID.randomUUID())
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

    @Test
    void testGetPublisherById()  throws Exception {
        given(publisherService.getPublisherById(publisherDTO.getId())).willReturn(Optional.of(publisherDTO));

        mockMvc.perform(get(PublisherController.PUBLISHER_PATH_ID,publisherDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.label").value("Clarkson N. Potter"));
    }

    @Test
    void testNotFoundPublisher() throws Exception{
        given(publisherService.getPublisherById(any())).willReturn(Optional.empty());

        mockMvc.perform(get(PublisherController.PUBLISHER_PATH_ID, UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSaveNewPublisher() throws Exception {
        given(publisherService.saveNewPublisher(any())).willReturn(publisherDTO);

        mockMvc.perform(post(PublisherController.PUBLISHER_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(publisherDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void testDeletePublisher() throws Exception {
        given(publisherService.deletePublisherById(any())).willReturn(true);

        mockMvc.perform(delete(PublisherController.PUBLISHER_PATH_ID,publisherDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(publisherService).deletePublisherById(uuidArgumentCaptor.capture());
        assertThat(publisherDTO.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void testUpdatePublisher() throws Exception {
        given(publisherService.updatePublisherById(any(),any())).willReturn(Optional.of(publisherDTO));

        mockMvc.perform(put(PublisherController.PUBLISHER_PATH_ID,publisherDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(publisherDTO)))
                .andExpect(status().isNoContent());

        verify(publisherService).updatePublisherById(uuidArgumentCaptor.capture(),any(PublisherDTO.class));
        assertThat(publisherDTO.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void testPatchPublisher() throws Exception {
        given(publisherService.patchPublisherById(any(),any())).willReturn(Optional.of(publisherDTO));
        publisherDTO.setLabel("Kaj");

        mockMvc.perform(patch(PublisherController.PUBLISHER_PATH_ID,publisherDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(publisherDTO)))
                .andExpect(status().isNoContent());

        verify(publisherService).patchPublisherById(uuidArgumentCaptor.capture(),publisherDTOArgumentCaptor.capture());
        assertThat(publisherDTO.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(publisherDTO.getLabel()).isEqualTo(publisherDTOArgumentCaptor.getValue().getLabel());
    }
}