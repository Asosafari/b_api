package com.spring.book_store.model;

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

    private UUID id;
    private String title;
    private Integer version;
    private Publisher publisher;
    private Set<Author> authors;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;


}
