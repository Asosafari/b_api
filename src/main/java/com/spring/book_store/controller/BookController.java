package com.spring.book_store.controller;

import com.spring.book_store.model.BookDTO;
import com.spring.book_store.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Author: ASOU SAFARI
 * Date:5/23/24
 * Time:10:59 PM
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class BookController {
     private final BookService bookService;
     public static final String BOOK_PATH = "/api/vi/book";
     public static final String BOOK_PATH_ID = BOOK_PATH + "/{bookId}";

     @GetMapping(BOOK_PATH)
     public Page<BookDTO> listBooks(@RequestParam String title,
                                    @RequestParam String authorName,
                                    @RequestParam String authorLastName,
                                    @RequestParam String publisherLabel,
                                    @RequestParam Integer pageNumber,
                                    @RequestParam Integer pageSize){
        return bookService.listOfBooks(title,authorName,authorLastName,publisherLabel,pageNumber,pageSize);
     }

     @GetMapping(BOOK_PATH_ID)
     public BookDTO getBookBYId(@PathVariable("bookId") UUID bookId){
          return bookService.getBookById(bookId).orElseThrow(NotFoundException :: new);
     }

     @PostMapping(BOOK_PATH)
     public ResponseEntity CreateNewBook(@RequestBody BookDTO bookDTO){
          BookDTO saveBookDTO = bookService.saveNewBook(bookDTO);
          HttpHeaders headers = new HttpHeaders();
          headers.add("Location",BOOK_PATH + "/" + saveBookDTO.getId());
          return new ResponseEntity(headers, HttpStatus.CREATED);
     }

     @DeleteMapping(BOOK_PATH_ID)
     public ResponseEntity deleteBookById(@PathVariable("bookId") UUID bookId){
          if (!bookService.deleteBookById(bookId)){
               throw  new NotFoundException();
          }
          return new ResponseEntity(HttpStatus.NO_CONTENT);
     }

     @PutMapping(BOOK_PATH_ID)
     public ResponseEntity updateBookById(@PathVariable("bookId") UUID bookId, @RequestBody BookDTO bookDTO){
          if (bookService.updateBookById(bookId,bookDTO).isEmpty()){
               throw new NotFoundException();
          }
          return new ResponseEntity(HttpStatus.NO_CONTENT);
     }
}
