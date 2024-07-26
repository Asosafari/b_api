package com.spring.book_store.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.book_store.entity.Author;
import com.spring.book_store.entity.Publisher;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
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

    private UUID id;
    private String title;
    private Integer version;
    private String publisherLabel;
    private String authorFullName;
    @JsonIgnore
    private Author author;
    @JsonIgnore
    private Publisher publisher;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;


}
