package com.spring.book_store.repository;

import com.spring.book_store.entity.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PublisherRepository extends JpaRepository<Publisher,UUID> {

    Page<Publisher> findAllByLabelIsLikeIgnoreCase(String label, Pageable pageable);

    @Query("SELECT p FROM Publisher p JOIN p.authors a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND LOWER(a.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))")
    Page<Publisher> findAllByAuthorNameIsLikeIgnoreCaseAndAuthorLastNameIsLikeIgnoreCase(
            @Param("name") String name, @Param("lastName") String lastName, Pageable pageable);
    @Query("SELECT p FROM Publisher p JOIN p.books b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Publisher> findAllByBookTitleIsLikeIgnoreCase(@Param("title") String bookTitle, Pageable pageable);
}
