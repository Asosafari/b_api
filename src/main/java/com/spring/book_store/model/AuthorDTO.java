package com.spring.book_store.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.book_store.entity.Book;
import com.spring.book_store.entity.Publisher;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Author: ASOU SAFARI
 * Date:5/23/24
 * Time:9:31 PM
 */
@Data
@Builder
public class AuthorDTO  {

    private UUID id;
    private String name;
    private String lastName;
    private Integer version;
    @JsonBackReference
    private Set<Book> books;
    private String email;
}
