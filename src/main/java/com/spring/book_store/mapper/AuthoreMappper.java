package com.spring.book_store.mapper;

import com.spring.book_store.entity.Author;
import com.spring.book_store.model.AuthorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Author: ASOU SAFARI
 * Date:5/23/24
 * Time:11:08 PM
 */
@Mapper
public interface AuthoreMappper {
    AuthoreMappper INSTANCE = Mappers.getMapper(AuthoreMappper.class);
    Author authorDTOToAUthor(AuthorDTO authorDTO);
    AuthorDTO authorToAuthorDTO(Author author);
}
