package com.spring.book_store.mapper;

import com.spring.book_store.entity.Book;
import com.spring.book_store.model.BookDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
    Book bookDTOToBook(BookDTO bookDTO);
    BookDTO bookToBookDTO(Book book);
}
