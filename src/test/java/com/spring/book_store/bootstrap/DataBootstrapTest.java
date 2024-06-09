package com.spring.book_store.bootstrap;

import com.spring.book_store.entity.Author;
import com.spring.book_store.entity.Book;
import com.spring.book_store.entity.Publisher;
import com.spring.book_store.repository.AuthorRepository;
import com.spring.book_store.repository.BookRepository;
import com.spring.book_store.repository.PublisherRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(DataBootstrap.class)
class DataBootstrapTest {
    
    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;


    DataBootstrap dataBootstrap;

    @BeforeEach
    void setUp() {
        dataBootstrap = new DataBootstrap(authorRepository,bookRepository,publisherRepository);
    }



    @Test
    void testRun()  throws  Exception {

        dataBootstrap.run((String) null);
        assertThat(publisherRepository.count()).isEqualTo(1);
        assertThat(authorRepository.count()).isEqualTo(1);
        assertThat(bookRepository.count()).isEqualTo(1);


    }
}