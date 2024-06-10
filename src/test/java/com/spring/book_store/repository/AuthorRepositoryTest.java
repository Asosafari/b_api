package com.spring.book_store.repository;

import com.spring.book_store.entity.Author;
import com.spring.book_store.entity.Book;
import com.spring.book_store.entity.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;
    Author saveAuthor;

    @BeforeEach
    void setUp() {
        saveAuthor =  authorRepository.save(Author.builder()
                .id(UUID.randomUUID())
                .version(1)
                .name("Ali")
                .lastName("Rad")
                .email("ali.rad@example.com")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build());

    }

    @Test
    void tesGetAuthorListByName() {
        Page<Author> page = authorRepository.findAllByNameIsLikeIgnoreCase("%ali%", null);
        assertThat(page.getContent().size()).isEqualTo(1);
        assertThat(page.getContent().get(0)).isEqualTo(saveAuthor);
    }

    @Test
    void tesGetAuthorListByLastName() {
        Page<Author> page = authorRepository
                .findAllByLastNameLikeIgnoreCase("rad", PageRequest.of(0, 10));
        assertThat(page.getContent().size()).isEqualTo(1);
        assertThat(page.getContent().get(0)).isEqualTo(saveAuthor);
    }

    @Test
    void tesGetAuthorListByFullName() {
        Page<Author> page = authorRepository
                .findAllByNameIsLikeIgnoreCaseAndLastNameLikeIgnoreCase("ali" ,"rad",
                        PageRequest.of(0, 10));
        assertThat(page.getContent().size()).isEqualTo(1);
        assertThat(page.getContent().get(0)).isEqualTo(saveAuthor);
    }

    @Test
    void testSaveAuthor() {
        authorRepository.save(saveAuthor);
        authorRepository.flush();
        assertThat(saveAuthor).isNotNull();
        assertThat(saveAuthor.getId()).isNotNull();

    }
}