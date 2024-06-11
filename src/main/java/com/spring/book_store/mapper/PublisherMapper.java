package com.spring.book_store.mapper;

import com.spring.book_store.entity.Publisher;
import com.spring.book_store.model.PublisherDTO;
import org.mapstruct.Mapper;

/**
 * Author: ASOU SAFARI
 * Date:5/23/24
 * Time:11:08 PM
 */
@Mapper
public interface PublisherMapper {
    Publisher publisherDTOToPublishe(PublisherDTO publisherDTO);
    PublisherDTO publisherToPublisherDTO(Publisher publisher);
}
