package com.spring.book_store.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Author: ASOU SAFARI
 * Date:5/23/24
 * Time:8:28 PM
 */
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
public class Book {

    @Id
    @UuidGenerator
    @Column(length = 36,columnDefinition = "varchar",updatable = false,nullable = false)
    private UUID uuid;

    private String title;
    @Version
    private Integer version;

    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    @ManyToOne
    private Publisher publisher;

    @ManyToMany
    @JoinTable(name = "author_book"
            , joinColumns = @JoinColumn(name = "book_id")
            ,inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;


}
