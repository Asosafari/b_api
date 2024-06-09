package com.spring.book_store.repository;

import com.spring.book_store.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Author: ASOU SAFARI
 * Date:5/23/24
 * Time:10:49 PM
 */
public interface AuthorRepository extends JpaRepository<Author,UUID> {
    Page<Author> findAllByNameIsLikeIgnoreCase(String name, Pageable pageable);
    Page<Author> findAllByLastNameLikeIgnoreCase(String lastName,Pageable pageable);
    Page<Author> findAllByNameIsLikeIgnoreCaseAndLastNameLikeIgnoreCase(String name, String lastName, Pageable pageable);
}
