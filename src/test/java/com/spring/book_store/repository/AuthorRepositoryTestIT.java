package com.spring.book_store.repository;

import com.spring.book_store.entity.Author;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTestIT {

    @Autowired
    AuthorRepository authorRepository;


    @Test
    void testFindAllAuthorByBookTitle() {
        Page<Author> page = authorRepository.findAllByBookTitleIsLikeIgnoreCase("The dark Highlander",null);
        assertThat(page.getContent().size()).isEqualTo(1);
    }

    @Test
    void testFindAllAuthorByPublisherLabel() {
        Page<Author> page = authorRepository.findAllByPublisherLabelIsLikeIgnoreCase("Clarkson N. Potter",
                PageRequest.of(0,10));

        assertThat(page.getContent().size()).isEqualTo(1);

    }
}