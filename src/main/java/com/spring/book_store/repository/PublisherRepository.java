package com.spring.book_store.repository;

import com.spring.book_store.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublisherRepository extends JpaRepository<Publisher,UUID> {
}
