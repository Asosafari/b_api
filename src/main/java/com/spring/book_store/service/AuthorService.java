package com.spring.book_store.service;
import com.spring.book_store.model.AuthorDTO;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorService {
    Page<AuthorDTO> listOfAuthors(String name, String lastName, Integer pageNumber,Integer pageSize);
    Optional<AuthorDTO> getAuthorById(UUID id);
    AuthorDTO saveNewAuthor(AuthorDTO author);
    Optional<AuthorDTO> updateAuthorById(UUID id,AuthorDTO authorDTO);
    boolean deleteAuthoeById(UUID id);
    Optional<AuthorDTO> patchById(UUID id,AuthorDTO authorDTO);


}
