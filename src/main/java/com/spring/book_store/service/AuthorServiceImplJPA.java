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

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 25;


    @Override
    public Page<AuthorDTO> listOfAuthors(String name, String lastName, Integer pageNumber, Integer pageSize) {

        PageRequest pageRequest = buildPageRequest(pageNumber,pageSize);

        Page<Author> authorPage;
        if (StringUtils.hasText(name) && lastName == null){
            authorPage = listAuthorByName(name,pageRequest);
        }else if (StringUtils.hasText(lastName) && name == null){
            authorPage = listAuthorByLastName(lastName,pageRequest);
        }else if ((StringUtils.hasText(name)) && StringUtils.hasText(lastName)){
            authorPage = listAuthorsByNameAndLastName(name,lastName,pageRequest);
        }else {
            authorPage = authorRepository.findAll(pageRequest);
        }

        return authorPage.map(authoreMappper ::authorToAuthorDTO);
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

    private PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber;
        int queryPageSize;
        if (pageNumber != null && pageNumber > 0){
            queryPageNumber = pageNumber - 1 ;
        }else {
            queryPageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize == null){
            queryPageSize = DEFAULT_PAGE_SIZE;
        }else {
            if (pageSize > 1000){
                queryPageSize = 1000;
            }else {
                queryPageSize = pageSize;
            }
        }
        Sort sort = Sort.by(Sort.Order.asc("name"));
        return PageRequest.of(queryPageNumber,queryPageSize,sort);
    }

    @Override
    public Optional<AuthorDTO> getAuthorById(UUID id) {
        return Optional.empty();
    }

    @Override
    public AuthorDTO saveNewAuthor(AuthorDTO author) {
        return null;
    }

    @Override
    public Optional<AuthorDTO> updateAuthorById(UUID id, AuthorDTO authorDTO) {
        return Optional.empty();
    }

    @Override
    public boolean deleteAuthoeById(UUID id) {
        return false;
    }

    @Override
    public Optional<AuthorDTO> patchById(UUID id) {
        return Optional.empty();
    }
}
