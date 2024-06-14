package com.spring.book_store.controller;

import com.spring.book_store.model.AuthorDTO;
import com.spring.book_store.service.AuthorService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * Author: ASOU SAFARI
 * Date:5/23/24
 * Time:11:00 PM
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthorController {
    private final AuthorService authorService;
    public static final String AUTHOR_PHATH = "/api/v1/author";
    public static final String AUTHOR_PATH_ID = AUTHOR_PHATH + "/{authorId}";

    @GetMapping(AUTHOR_PHATH)
    public Page<AuthorDTO> listAuthors(@RequestParam String name,
                                       @RequestParam String lastName,
                                       @RequestParam Integer pageNumber,
                                       @RequestParam Integer pageSize,
                                       @RequestParam String bookTitle,
                                       @RequestParam String publisherLabel) {

        return authorService.listOfAuthors(name, lastName, pageNumber, pageSize, bookTitle, publisherLabel);
    }

    @GetMapping(AUTHOR_PATH_ID)
    public AuthorDTO getAuhtorById(@PathVariable("authorId") UUID authorId) {
        return authorService.getAuthorById(authorId).orElseThrow(NotFoundException :: new);
    }

    @PostMapping(AUTHOR_PHATH)
    public ResponseEntity CreateNewAuthor(@Validated @RequestBody AuthorDTO authorDTO){
        AuthorDTO saveAuthorDTO = authorService.saveNewAuthor(authorDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location",AUTHOR_PHATH + "/" + saveAuthorDTO.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(AUTHOR_PATH_ID)
    public ResponseEntity updateAuthor(@PathVariable("authorId") UUID authorId, @RequestBody AuthorDTO authorDTO){
        if (authorService.updateAuthorById(authorId,authorDTO).isEmpty()){
            throw new NotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping(AUTHOR_PATH_ID)
    public ResponseEntity deleteAuthor(@PathVariable("authorId") UUID authorId){
        if (!authorService.deleteAuthoeById(authorId)){
            throw new NotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
