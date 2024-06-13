package com.spring.book_store.service;

import com.spring.book_store.entity.Author;
import com.spring.book_store.mapper.AuthoreMappper;
import com.spring.book_store.model.AuthorDTO;
import com.spring.book_store.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Author: ASOU SAFARI
 * Date:5/24/24
 * Time:12:47 AM
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthorServiceImplJPA implements AuthorService {

    private final AuthoreMappper authoreMappper;
    private final AuthorRepository authorRepository;


    @Override
    public Page<AuthorDTO> listOfAuthors(String name, String lastName, Integer pageNumber, Integer pageSize, String bookTitle, String publisherLabel) {

        PageRequest pageRequest = BuildPageRequest.build(pageNumber,pageSize,"name");

        Page<Author> authorPage;
        if (StringUtils.hasText(name) && lastName == null && bookTitle == null && publisherLabel == null){
            authorPage = listAuthorByName(name,pageRequest);
        }else if (StringUtils.hasText(lastName) && name == null && bookTitle == null && publisherLabel == null){
            authorPage = listAuthorByLastName(lastName,pageRequest);
        }else if ((StringUtils.hasText(name)) && StringUtils.hasText(lastName) && bookTitle == null && publisherLabel == null){
            authorPage = listAuthorsByNameAndLastName(name,lastName,pageRequest);
        }else if (StringUtils.hasText(bookTitle) && name == null && lastName == null  && publisherLabel == null){
            authorPage = listAuthorByBookTitle(bookTitle,pageRequest);
        }else if (StringUtils.hasText(publisherLabel) && name == null && lastName == null  && bookTitle == null){
            authorPage = listAuthorByPublisherLabel(publisherLabel,pageRequest);
        }
        else {
            authorPage = authorRepository.findAll(pageRequest);
        }
        return authorPage.map(authoreMappper ::authorToAuthorDTO);
    }

    private Page<Author> listAuthorByPublisherLabel(String publisherLabel, PageRequest pageRequest) {
        return authorRepository.findAllByPublisherLabelIsLikeIgnoreCase(publisherLabel,pageRequest);
    }

    private Page<Author> listAuthorByBookTitle(String bookTitle, PageRequest pageRequest) {
        return authorRepository.findAllByBookTitleIsLikeIgnoreCase(bookTitle,pageRequest);
    }

    private Page<Author> listAuthorsByNameAndLastName(String name, String lastName, PageRequest pageRequest) {
        return authorRepository.findAllByNameIsLikeIgnoreCaseAndLastNameLikeIgnoreCase(name,lastName,pageRequest);
    }

    private Page<Author> listAuthorByLastName(String lastName, PageRequest pageRequest) {
        return authorRepository.findAllByLastNameLikeIgnoreCase(lastName,pageRequest);
    }

    private Page<Author> listAuthorByName(String name, PageRequest pageRequest) {
        return authorRepository.findAllByNameIsLikeIgnoreCase(name,pageRequest);
    }

    @Override
    public Optional<AuthorDTO> getAuthorById(UUID id) {
        return Optional.ofNullable(authoreMappper.authorToAuthorDTO(authorRepository.findById(id).orElse(null)));
    }

    @Override
    public AuthorDTO saveNewAuthor(AuthorDTO author) {
        return authoreMappper.authorToAuthorDTO(authorRepository.save(authoreMappper.authorDTOToAthor(author)));
    }

    @Override
    public Optional<AuthorDTO> updateAuthorById(UUID id, AuthorDTO authorDTO) {

        AtomicReference<Optional<AuthorDTO>> atomicReference = new AtomicReference<>();

        authorRepository.findById(id).ifPresentOrElse(foundAuthor ->{
            foundAuthor.setName(authorDTO.getName());
            foundAuthor.setLastName(authorDTO.getLastName());
            foundAuthor.setEmail(authorDTO.getEmail());
            foundAuthor.setBooks(authorDTO.getBooks());
            foundAuthor.setPublishers(authorDTO.getPublishers());
            foundAuthor.setVersion(authorDTO.getVersion());
            atomicReference.set(Optional.of(authoreMappper.authorToAuthorDTO(authorRepository.save(foundAuthor))));
        } , () -> atomicReference.set(Optional.empty())
        );
        return atomicReference.get();
    }

    @Override
    public boolean deleteAuthoeById(UUID id) {
        if (authorRepository.existsById(id)){
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<AuthorDTO> patchById(UUID id, AuthorDTO authorDTO) {
        AtomicReference<Optional<AuthorDTO>> atomicReference = new AtomicReference<>();
        authorRepository.findById(id).ifPresentOrElse(foundAuthor ->{
            if (StringUtils.hasText(authorDTO.getName())){
                foundAuthor.setName(authorDTO.getName());
            }
            if (StringUtils.hasText(authorDTO.getLastName())){
                foundAuthor.setLastName(authorDTO.getLastName());
            }
            if (StringUtils.hasText(authorDTO.getEmail())){
                foundAuthor.setEmail(authorDTO.getEmail());
            }
            if (authorDTO.getPublishers() != null){
                foundAuthor.setPublishers(authorDTO.getPublishers());
            }
            if (authorDTO.getBooks() != null){
                foundAuthor.setBooks(authorDTO.getBooks());
            }

            atomicReference.set(Optional.of(authoreMappper.authorToAuthorDTO(authorRepository.save(foundAuthor))));

        }, () ->atomicReference.set(Optional.empty())
                );
        return atomicReference.get();
    }
}
