package com.spring.book_store.repository;

import com.spring.book_store.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book,UUID> {
}
