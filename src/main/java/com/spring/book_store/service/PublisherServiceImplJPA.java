package com.spring.book_store.service;

import com.spring.book_store.model.PublishetDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Author: ASOU SAFARI
 * Date:6/11/24
 * Time:8:50 PM
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PublisherServiceImplJPA implements PublisherService {

    @Override
    public Page<PublishetDTO> listOfPublisher(String label, String bookTitle, String authorFullName) {
        return null;
    }

    @Override
    public Optional<PublishetDTO> getPublisherById(UUID id) {
        return Optional.empty();
    }

    @Override
    public PublishetDTO saveNewPublisher(PublishetDTO publishetDTO) {
        return null;
    }

    @Override
    public Optional<PublishetDTO> updatePublisherById(UUID id, PublishetDTO publishetDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<PublishetDTO> patchPublisherById(UUID id, PublishetDTO publishetDTO) {
        return Optional.empty();
    }

    @Override
    public boolean deletePublisherById(UUID id) {
        return false;
    }
}
