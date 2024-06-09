package com.spring.book_store.bootstrap;

import com.spring.book_store.entity.Author;
import com.spring.book_store.entity.Book;
import com.spring.book_store.entity.Publisher;
import com.spring.book_store.repository.AuthorRepository;
import com.spring.book_store.repository.BookRepository;
import com.spring.book_store.repository.PublisherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.UUID;

/**
 * Author: ASOU SAFARI
 * Date:5/24/24
 * Time:12:09 PM
 */
@Component
@RequiredArgsConstructor
public class DataBootstrap implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
      load();
    }



    private void load() {
        if (authorRepository.count() == 0 && publisherRepository.count() == 0 && bookRepository.count()== 0){
            Publisher publisher = Publisher.builder()
                    .id(UUID.randomUUID())
                    .version(1)
                    .label("Clarkson N. Potter")
                    .zipCode("4466")
                    .email("info@clarksonpotter.com")
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();
            Author author = Author.builder()
                    .id(UUID.randomUUID())
                    .version(1)
                    .name("Ali")
                    .lastName("Rad")
                    .email("ali.rad@example.com")
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();
           Publisher savePublisher =  publisherRepository.save(publisher);

            author.addPublisher(savePublisher);

            Author saveAuthor = authorRepository.save(author);

            Book book = Book.builder()
                    .id(UUID.randomUUID())
                    .title("The Dark Highlander")
                    .price(new BigDecimal("12.99"))
                    .version(1)
                    .publisher(savePublisher)
                    .createdDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

            book.addAuthor(saveAuthor);
            bookRepository.save(book);


        }
    }

}