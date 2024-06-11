package com.spring.book_store.service;

import com.spring.book_store.model.BookDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface BookService {
    Page<Optional<BookDTO>> listOfBooks(String title, String authorName, String authorLastName,
                                        String publisherLabel, Integer pageNumber, Integer pageSize);
    Optional<BookDTO> getBookById(UUID id);
    BookDTO saveNewBook(BookDTO bookDTO);
    boolean deleteBookById(UUID id);
    Optional<BookDTO> updateBookById(UUID id, BookDTO bookDTO);
    Optional<BookDTO> patchBookById(UUID id, BookDTO bookDTO);
}
