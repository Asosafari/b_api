
package com.spring.book_store.service;

import com.spring.book_store.entity.Publisher;
import com.spring.book_store.mapper.PublisherMapper;
import com.spring.book_store.model.PublisherDTO;
import com.spring.book_store.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Author: ASOU SAFARI
 * Date:6/11/24
 * Time:8:50 PM
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PublisherServiceImplJPA implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    @Override
    public Page<PublisherDTO> listOfPublisher(String label, String bookTitle, String authorName,
                                              String authorLastName, Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = BuildPageRequest.build(pageNumber,pageSize,"label");
        Page<Publisher> publisherPage;
        if (StringUtils.hasText(label) && bookTitle == null && authorName == null && authorLastName == null){
            publisherPage = listPublisherByLabel(label,pageRequest);
        }else if (StringUtils.hasText(bookTitle) && label == null && authorName == null && authorLastName == null){
            publisherPage = listPublisherByBookTitle(bookTitle, pageRequest);
        }else if (StringUtils.hasText(authorName) && StringUtils.hasText(authorLastName) && label == null && bookTitle == null){
            publisherPage = listPublisherByAuthorFullName(authorName,authorLastName,pageRequest);
        }else {
            publisherPage = publisherRepository.findAll(pageRequest);
        }
        return publisherPage.map(publisherMapper :: publisherToPublisherDTO);
    }

    private Page<Publisher> listPublisherByAuthorFullName(String authorName, String authorLastName,
                                                          PageRequest pageRequest) {
        return publisherRepository.findAllByAuthorNameIsLikeIgnoreCaseAndAuthorLastNameIsLikeIgnoreCase(authorName,
                authorLastName, pageRequest);
    }

    private Page<Publisher> listPublisherByBookTitle(String bookTitle, PageRequest pageRequest) {
        return publisherRepository.findAllByBookTitleIsLikeIgnoreCase(bookTitle,pageRequest);
    }

    private Page<Publisher> listPublisherByLabel(String label, PageRequest pageRequest) {
        return publisherRepository.findAllByLabelIsLikeIgnoreCase(label,pageRequest);
    }

    @Override
    public Optional<PublisherDTO> getPublisherById(UUID id) {
        return Optional.ofNullable(publisherMapper.publisherToPublisherDTO(publisherRepository.findById(id).orElse(null)));
    }

    @Override
    public PublisherDTO saveNewPublisher(PublisherDTO publisherDTO) {
        return publisherMapper.publisherToPublisherDTO(publisherRepository.save(publisherMapper.publisherDTOToPublisher(publisherDTO)));
    }

    @Override
    public Optional<PublisherDTO> updatePublisherById(UUID id, PublisherDTO publisherDTO) {
        AtomicReference<Optional<PublisherDTO>> atomicReference = new AtomicReference<>();
        publisherRepository.findById(id).ifPresentOrElse(foundPublisher ->{
            foundPublisher.setLabel(publisherDTO.getLabel());
            foundPublisher.setVersion(publisherDTO.getVersion());
            foundPublisher.setBooks(publisherDTO.getBooks());
            foundPublisher.setEmail(publisherDTO.getEmail());
            foundPublisher.setZipCode(publisherDTO.getZipCode());
            publisherRepository.save(foundPublisher);
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public Optional<PublisherDTO> patchPublisherById(UUID id, PublisherDTO publisherDTO) {
        AtomicReference<Optional<PublisherDTO>> atomicReference = new AtomicReference<>();
        publisherRepository.findById(id).ifPresentOrElse(foundPublisher ->{
            if (StringUtils.hasText(publisherDTO.getLabel())){
                foundPublisher.setLabel(publisherDTO.getLabel());
            }
            if (StringUtils.hasText(publisherDTO.getEmail())){
                foundPublisher.setEmail(publisherDTO.getEmail());
            }
            if (StringUtils.hasText(publisherDTO.getZipCode())){
                foundPublisher.setZipCode(publisherDTO.getZipCode());
            }
            if (publisherDTO.getBooks() != null){
                foundPublisher.setBooks(publisherDTO.getBooks());
            }
            if (publisherDTO.getVersion() != null){
                foundPublisher.setVersion(publisherDTO.getVersion());
            }
            publisherRepository.save(foundPublisher);
        }, () -> atomicReference.set(Optional.empty()));
        return atomicReference.get();
    }

    @Override
    public boolean deletePublisherById(UUID id) {
        if (publisherRepository.existsById(id)){
            publisherRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
