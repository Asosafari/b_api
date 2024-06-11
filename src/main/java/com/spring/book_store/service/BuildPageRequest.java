package com.spring.book_store.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * Author: ASOU SAFARI
 * Date:6/11/24
 * Time:3:30 PM
 */
public class BuildPageRequest {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 25;

    public static PageRequest build(Integer pageNumber, Integer pageSize, String sortProperty){
        int queryPageNumber;
        int queryPageSize;
        if (pageNumber != null && pageNumber > 0){
            queryPageNumber = pageNumber - 1 ;
        }else {
            queryPageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize == null){
            queryPageSize = DEFAULT_PAGE_SIZE;
        }else {
            if (pageSize > 1000){
                queryPageSize = 1000;
            }else {
                queryPageSize = pageSize;
            }
        }
        Sort sort = Sort.by(Sort.Order.asc(sortProperty));
        return PageRequest.of(queryPageNumber,queryPageSize,sort);
    }
}
