package com.spring.book_store.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.book_store.entity.Author;
import com.spring.book_store.entity.Publisher;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
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
public class BookDTO {

    @JsonIgnore
    private UUID id;
    private String title;

    @JsonIgnore
    private Integer version;

    @JsonBackReference
    private Publisher publisher;

    @JsonBackReference
    private Author author;
    private BigDecimal price;

    @JsonIgnore
    private LocalDateTime createdDate;

    @JsonIgnore
    private LocalDateTime updateDate;


}
