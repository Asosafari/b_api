package com.spring.book_store.service;

import com.spring.book_store.model.PublisherDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface PublisherService {
    Page<PublisherDTO> listOfPublisher(String label, String bookTitle, String authorName, String authorLastName, Integer pageNumber, Integer pageSize);
    Optional<PublisherDTO> getPublisherById(UUID id);
    PublisherDTO saveNewPublisher (PublisherDTO publisherDTO);
    Optional<PublisherDTO> updatePublisherById(UUID id, PublisherDTO publisherDTO);
    Optional<PublisherDTO> patchPublisherById(UUID id, PublisherDTO publisherDTO);
    boolean deletePublisherById(UUID id);


}
