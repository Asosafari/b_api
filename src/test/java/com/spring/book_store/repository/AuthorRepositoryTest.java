package com.spring.book_store.repository;


import com.spring.book_store.entity.Author;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.time.LocalDateTime;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


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
    void testBadNameValue() {
        assertThrows(ConstraintViolationException.class, () ->{
            Author author = authorRepository.save(Author.builder()
                            .name("neda aaaaaaaaaaaaaaaaaaaaaaaaa" +
                                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                            .lastName("Pars")
                    .build());
            authorRepository.flush();
        });
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
        Author author = authorRepository.save(saveAuthor);
        authorRepository.flush();
        assertThat(author).isNotNull();
        assertThat(author.getId()).isNotNull();

    }
}