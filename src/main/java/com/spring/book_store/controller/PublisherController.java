package com.spring.book_store.controller;

import com.spring.book_store.model.BookDTO;
import com.spring.book_store.model.PublisherDTO;
import com.spring.book_store.service.PublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
