package com.spring.book_store.repository;

import com.spring.book_store.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book,UUID> {

    Page<Book> findAllByTitleIsLikeIgnoreCase(String title, Pageable pageable);

    @Query("SELECT b FROM Book b JOIN b.publisher p WHERE LOWER(p.label) LIKE LOWER(CONCAT('%', :label, '%'))")
    Page<Book> findAllByPublisherLabelIsLikeIgnoreCase(@Param("label") String publisherLabel, Pageable pageable);

    @Query("SELECT b FROM Book b JOIN b.author a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Book> findAllByAuthorNameIsLikeIgnoreCase(@Param("name") String authorName,Pageable pageable);

    @Query("SELECT b FROM Book b JOIN b.author a WHERE LOWER(a.lastName) LIKE LOWER(CONCAT('%', lastName, '%'))")
    Page<Book> findAllByAuthorLastNameIsLikeIgnoreCase(@Param("lastName") String authorLastName, Pageable pageable);

    @Query("SELECT b FROM Book b JOIN b.author a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND LOWER(a.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))")
    Page<Book> findAllByAuthorNameIsLikeIgnoreCaseAndAuthorLastNameIsLikeIgnoreCase(
            @Param("name") String name, @Param("lastName") String lastName, Pageable pageable);

}
