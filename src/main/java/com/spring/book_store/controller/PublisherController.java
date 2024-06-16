package com.spring.book_store.controller;

import com.spring.book_store.model.BookDTO;
import com.spring.book_store.model.PublisherDTO;
import com.spring.book_store.service.PublisherService;
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
 * Time:11:00 PM
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class PublisherController {
    private final PublisherService publisherService;
    public static final String PUBLISHER_PATH = "/api/v1/publishers";
    public static final String PUBLISHER_PATH_ID = PUBLISHER_PATH + "/{publisherId}";

    @GetMapping(PUBLISHER_PATH)
    public Page<PublisherDTO> listPublisher(@RequestParam String label,
                                            @RequestParam String bookTitle,
                                            @RequestParam String authorName,
                                            @RequestParam String authorLastName,
                                            @RequestParam Integer pageNumber,
                                            @RequestParam Integer pageSize){
      return publisherService.listOfPublisher(label,bookTitle,authorName,authorLastName,pageNumber,pageSize);
    }

    @GetMapping(PUBLISHER_PATH_ID)
    public PublisherDTO getPublisherById(@PathVariable("publisherId") UUID publisherId){
        return publisherService.getPublisherById(publisherId).orElseThrow(NotFoundException :: new);
    }

    @PostMapping(PUBLISHER_PATH)
    public ResponseEntity creatNewPublisher(@RequestBody PublisherDTO publisherDTO){
        PublisherDTO  savePublisher = publisherService.saveNewPublisher(publisherDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location",PUBLISHER_PATH + "/" + savePublisher.getId());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
}
