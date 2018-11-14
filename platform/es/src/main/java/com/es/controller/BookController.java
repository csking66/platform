package com.es.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.es.Book;
import com.es.repository.BookRepository;

import io.swagger.annotations.Api;

/**
 * @ClassName: BookController
 * @Description:
 * @date 2018年11月14日
 *
 */
@Api("es")
@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookRepository bookDao;

	@GetMapping("/get/{id}")
	public Book getBookById(@PathVariable String id) {
		return bookDao.findOne(id);
	}

	@GetMapping("/select/{q}")
	public List<Book> testSearch(@PathVariable String q) {
		QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
		Iterable<Book> searchResult = bookDao.search(builder);
		Iterator<Book> iterator = searchResult.iterator();
		List<Book> list = new ArrayList<Book>();
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}

	@GetMapping("/{page}/{size}/{q}")
	public List<Book> searchCity(@PathVariable Integer page, @PathVariable Integer size, @PathVariable String q) {

		// 分页参数
		Pageable pageable = new PageRequest(page, size);

		// 分数，并自动按分排序
		FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
				.add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", q)),
						ScoreFunctionBuilders.weightFactorFunction(1000)) // 权重：name 1000分
				.add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("message", q)),
						ScoreFunctionBuilders.weightFactorFunction(100)); // 权重：message 100分

		// 分数、分页
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable)
				.withQuery(functionScoreQueryBuilder).build();

		Page<Book> searchPageResults = bookDao.search(searchQuery);
		return searchPageResults.getContent();

	}

	@PostMapping("/insert")
	public Book insertBook(Book book) {
		bookDao.save(book);
		return book;
	}

	@DeleteMapping("/delete/{id}")
	public Book insertBook(@PathVariable String id) {
		Book book = bookDao.findOne(id);
		bookDao.delete(id);
		return book;
	}

	@PutMapping("/update")
	public Book updateBook(Book book) {
		bookDao.save(book);
		return book;
	}

}
