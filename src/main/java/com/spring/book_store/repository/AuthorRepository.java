package com.spring.book_store.repository;

import com.spring.book_store.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Author: ASOU SAFARI
 * Date:5/23/24
 * Time:10:49 PM
 */
public interface AuthorRepository extends JpaRepository<Author,UUID> {
}
