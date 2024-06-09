package com.spring.book_store.mapper;

import com.spring.book_store.entity.Author;
import com.spring.book_store.model.AuthorDTO;
import org.mapstruct.Mapper;

/**
 * Author: ASOU SAFARI
 * Date:5/23/24
 * Time:11:08 PM
 */
@Mapper
public interface AuthoreMappper {
    Author authorDTOToAthor(AuthorDTO authorDTO);
    AuthorDTO authorToAuthorDTO(Author author);
}
