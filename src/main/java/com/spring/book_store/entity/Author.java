package com.spring.book_store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Author: ASOU SAFARI
 * Date:5/23/24
 * Time:9:09 PM
 */
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Author {
    @Id
    @UuidGenerator
    @Column(length = 36, columnDefinition = "varchar",updatable = false,nullable = false)
    private UUID id;

    private String name;
    private String lastName;
    @Version
    private Integer version;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "author_publisher"
            ,joinColumns = @JoinColumn(name = "author_id")
            ,inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private Set<Publisher> publishers = new HashSet<>();

}
