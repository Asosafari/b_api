package com.spring.book_store.service;

import com.spring.book_store.model.PublishetDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface PublisherService {
    Page<PublishetDTO> listOfPublisher(String label, String bookTitle, String authorName,String authorLastName, Integer pageNumber, Integer pageSize);
    Optional<PublishetDTO> getPublisherById(UUID id);
    PublishetDTO saveNewPublisher (PublishetDTO publishetDTO);
    Optional<PublishetDTO> updatePublisherById(UUID id, PublishetDTO publishetDTO);
    Optional<PublishetDTO> patchPublisherById(UUID id, PublishetDTO publishetDTO);
    boolean deletePublisherById(UUID id);


}
