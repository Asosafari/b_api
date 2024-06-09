package com.spring.book_store.model;

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
public class PublishetDTO {

    private UUID id;
    private String label;
    private String zipCode;
    private Integer version;
    private Set<Book> books;
    private Set<Author> authors;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
