package com.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.domain.es.Book;

/**
* @ClassName: BookRepository
* @Description: 
* @date 2018年11月14日
*
*/

public interface BookRepository extends ElasticsearchRepository<Book,String>{

}
