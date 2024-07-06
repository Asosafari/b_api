package com.spring.book_store.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.book_store.entity.Author;
import com.spring.book_store.entity.Book;
import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Author: ASOU SAFARI
 * Date:5/23/24
 * Time:9:32 PM
 */
@Data
@Builder
public class PublisherDTO {

    @JsonIgnore
    private UUID id;
    private String label;
    private String zipCode;

    @JsonIgnore
    private Integer version;

    @JsonBackReference
    private Set<Book> books;

    @JsonBackReference
    private Set<Author> authors;

    @JsonIgnore
    private LocalDateTime createdDate;

    @JsonIgnore
    private LocalDateTime updateDate;
    private String email;
}
