package com.spring.book_store.repository;

import com.spring.book_store.entity.Publisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PublisherRepositoryTestIT {

    @Autowired
    PublisherRepository publisherRepository;

    @Test
    void testFindAllByLabel() {
        Page<Publisher> page = publisherRepository.findAllByLabelIsLikeIgnoreCase("Clarkson N. Potter",null);
        assertThat(page).isNotEmpty();
    }

    @Test
    void testFindAllByAuthorFullName() {
        Page<Publisher> page = publisherRepository.findAllByAuthorNameIsLikeIgnoreCaseAndAuthorLastNameIsLikeIgnoreCase("ali","rad",null);
        assertThat(page).isNotEmpty();
    }

    @Test
    void testFindAllByBookTitle() {
        Page<Publisher> page = publisherRepository.findAllByBookTitleIsLikeIgnoreCase("The Dark highlander",null);
        assertThat(page).isNotEmpty();
    }
}