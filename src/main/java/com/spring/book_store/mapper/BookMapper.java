package com.spring.book_store.mapper;

import com.spring.book_store.entity.Book;
import com.spring.book_store.model.BookDTO;
import com.spring.book_store.model.PublisherDTO;
import com.spring.book_store.repository.AuthorRepository;
import com.spring.book_store.service.AuthorService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
     Book bookDTOToBook(BookDTO bookDTO);
    default BookDTO bookToBookDTO(Book book){
        if (book == null) {
            return null;
        }

        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .version(book.getVersion())
                .publisherLabel(book.getPublisher().getLabel())
                .authorFullName(book.getAuthor().getName() + " " + book.getAuthor().getLastName())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .price(book.getPrice())
                .createdDate(book.getCreatedDate())
                .updateDate(book.getUpdateDate())
                .build();
    }
}
