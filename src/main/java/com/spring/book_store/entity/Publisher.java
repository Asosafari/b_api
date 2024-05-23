package com.spring.book_store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Author: ASOU SAFARI
 * Date:5/23/24
 * Time:9:01 PM
 */

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Publisher {

    @Id
    @UuidGenerator
    @Column(length = 36, columnDefinition = "varchar",updatable = false,nullable = false)
    private UUID id;
    private String label;
    private String ZipCode;
    @Version
    private Integer version;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books = new HashSet<>();

    @ManyToMany(mappedBy = "authors")
    private Set<Author> authors;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;


}
