package com.spring.book_store.model;

import com.spring.book_store.entity.Book;
import com.spring.book_store.entity.Publisher;
import lombok.Builder;
import lombok.Data;

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
public class AuthorDTO {

    private UUID id;
    private String name;
    private String lastName;
    private Integer version;
    private Set<Book> books;
    private Set<Publisher> publishers;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private String email;
}
