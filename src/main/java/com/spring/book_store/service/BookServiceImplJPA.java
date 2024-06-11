package com.spring.book_store.service;

import com.spring.book_store.model.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

/**
 * Author: ASOU SAFARI
 * Date:6/11/24
 * Time:1:50 PM
 */
public class BookServiceImplJPA implements BookService {
    @Override
    public Page<Optional<BookDTO>> listOfBooks(String title, String authorName, String authorLastName,
                                               String publisherLabel, Integer pageNumber, Integer pageSize) {

        return null;
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
