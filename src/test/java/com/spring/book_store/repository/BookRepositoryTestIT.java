package com.spring.book_store.repository;

import com.spring.book_store.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookRepositoryTestIT {

    @Autowired
    BookRepository bookRepository;


    @Test
    void testFindAllByTitle() {
       Page<Book> page =  bookRepository.findAllByTitleIsLikeIgnoreCase("The dark Highlander",null);
        assertThat(page).isNotEmpty();
    }

    @Test
    void testFindAllByPublisherLabel() {
        Page<Book> page = bookRepository.findAllByPublisherLabelIsLikeIgnoreCase("Clarkson N. potter",null);
        assertThat(page).isNotEmpty();
    }

    @Test
    void testFindAllByAuthorName() {
        Page<Book> page = bookRepository.findAllByAuthorNameIsLikeIgnoreCase("ali",PageRequest.of(0,10));
        assertThat(page).isNotEmpty();
    }

    @Test
    void testFindAllByAuthorLastName() {
        Page<Book> page = bookRepository.findAllByAuthorLastNameIsLikeIgnoreCase("rad",null);
        assertThat(page).isNotEmpty();
    }

    @Test
    void testFindAllByAuthorFullName() {
        Page<Book> page = bookRepository.findAllByAuthorNameIsLikeIgnoreCaseAndAuthorLastNameIsLikeIgnoreCase(
                "ali","rad",null);
        assertThat(page).isNotEmpty();
    }
}