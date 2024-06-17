package com.spring.book_store.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.apache.commons.beanutils.converters.SqlTimeConverter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

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
@RequiredArgsConstructor
@Builder
@Entity
public class Book {

    public Book(UUID id, String title, Integer version, BigDecimal price, Publisher publisher
            , Set<Author> authors, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.id = id;
        this.title = title;
        this.version = version;
        this.price = price;
        this.setPublisher(publisher);
        this.authors = authors;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }

    @Id
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36,columnDefinition = "varchar(36)",updatable = false,nullable = false)
    private UUID id;

    @NotBlank
    @NotNull
    @Size(max = 50)
    @Column(length = 50)
    private String title;
    @Version
    private Integer version;


    @NotNull
    private BigDecimal price;

    @ManyToOne
    private Publisher publisher;

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
        publisher.getBooks().add(this);
    }

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "author_book"
            , joinColumns = @JoinColumn(name = "book_id")
            ,inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

    /*
    add and remove in memory
    public -> testable
     */
    public void addAuthor(Author author){
        this.authors.add(author);
        author.getBooks().add(this);
    }

    private void removeAuthor(Author author){
        this.authors.remove(author);
        author.getBooks().remove(this);
    }


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;
}
