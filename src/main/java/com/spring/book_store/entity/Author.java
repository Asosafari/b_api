package com.spring.book_store.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;
import org.springframework.expression.spel.ast.TypeCode;

import java.time.LocalDateTime;
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
@Builder
@Entity
public class Author {
    @Id
    @UuidGenerator
   // @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar",updatable = false,nullable = false)
    private UUID id;

    @NotBlank
    @NotNull
    @Size(max = 50)
    @Column(length = 50)
    private String name;

    @NotBlank
    @NotNull
    @Size(max = 50)
    @Column(length = 50)
    private String lastName;

    @Email()
    String email;


    @Version
    private Integer version;

    @Builder.Default
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "author_publisher"
            ,joinColumns = @JoinColumn(name = "author_id")
            ,inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private Set<Publisher> publishers = new HashSet<>();


    /*
    add and remove in memory
    public -> testable
   */

    public void addPublisher(Publisher publisher){
        this.publishers.add(publisher);
        publisher.getAuthors().add(this);
    }
    public void removePublisher(Publisher publisher){
        this.publishers.remove(publisher);
        publisher.getAuthors().remove(this);
    }

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

}
