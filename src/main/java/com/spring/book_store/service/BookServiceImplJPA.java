package com.spring.book_store.service;

import com.spring.book_store.entity.Book;
import com.spring.book_store.mapper.BookMapper;
import com.spring.book_store.model.BookDTO;
import com.spring.book_store.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;

/**
 * Author: ASOU SAFARI
 * Date:6/11/24
 * Time:1:50 PM
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImplJPA implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    @Override
    public Page<BookDTO> listOfBooks(String title, String authorName, String authorLastName,
                                               String publisherLabel, Integer pageNumber, Integer pageSize) {

        PageRequest pageRequest = BuildPageRequest.build(pageNumber,pageSize,"title");

        Page<Book> bookPage;

        if (StringUtils.hasText(title) && authorName == null && authorLastName == null && publisherLabel == null){
            bookPage = listBookByTitle(title,pageRequest);
        }else if (StringUtils.hasText(authorName)  && title == null && authorLastName == null && publisherLabel == null){
            bookPage = listBooksByAuthorName(authorName,pageRequest);
        }else if (StringUtils.hasText(authorLastName)  && title == null && authorName == null && publisherLabel == null){
            bookPage = listBookByAuthorLastName(authorLastName, pageRequest);
        }else if (StringUtils.hasText(publisherLabel) && authorName == null && authorLastName == null && title== null){
            bookPage = listBookByPublisherLabel(publisherLabel, pageRequest);
        }else if (StringUtils.hasText(authorName) && StringUtils.hasText(authorLastName)
                && title == null && publisherLabel == null){
            bookPage = listBookByAuthorFullName(authorName,authorLastName,pageRequest);
        }else {
            bookPage = bookRepository.findAll(pageRequest);
        }

        return bookPage.map(bookMapper :: bookToBookDTO);
    }

    private Page<Book> listBookByAuthorFullName(String authorName, String authorLastName, PageRequest pageRequest) {
        return bookRepository.findAllByAuthorNameIsLikeIgnoreCaseAndAuthorLastNameIsLikeIgnoreCase(authorName,
                authorLastName, pageRequest);
    }

    private Page<Book> listBookByPublisherLabel(String publisherLabel, PageRequest pageRequest) {
        return bookRepository.findAllByPublisherLabelIsLikeIgnoreCase(publisherLabel,pageRequest);
    }

    private Page<Book> listBookByAuthorLastName(String authorLastName, PageRequest pageRequest) {
        return bookRepository.findAllByAuthorLastNameIsLikeIgnoreCase(authorLastName, pageRequest);
    }

    private Page<Book> listBooksByAuthorName(String authorName, PageRequest pageRequest) {
        return bookRepository.findAllByAuthorNameIsLikeIgnoreCase(authorName, pageRequest);
    }

    private Page<Book> listBookByTitle(String title, PageRequest pageRequest) {
        return bookRepository.findAllByTitleIsLikeIgnoreCase(title, pageRequest);
    }

    @Override
    public Optional<BookDTO> getBookById(UUID id) {
        return Optional.empty();
    }

    @Override
    public BookDTO saveNewBook(BookDTO bookDTO) {
        return null;
    }

    @Override
    public boolean deleteBookById(UUID id) {
        return false;
    }

    @Override
    public Optional<BookDTO> updateBookById(UUID id, BookDTO bookDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<BookDTO> patchBookById(UUID id, BookDTO bookDTO) {
        return Optional.empty();
    }
}
