package com.spring.book_store.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.book_store.entity.Book;
import lombok.Builder;
import lombok.Data;


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
    private UUID id;
    private String label;
    private String zipCode;
    private Integer version;
    @JsonBackReference
    private Set<Book> books;
    private String email;
}
