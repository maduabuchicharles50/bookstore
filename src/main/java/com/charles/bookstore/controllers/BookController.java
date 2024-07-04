package com.charles.bookstore.controllers;

import com.charles.bookstore.dto.BookDto;
import com.charles.bookstore.entity.Book;
import com.charles.bookstore.repository.BookRepository;
import com.charles.bookstore.request.BookRequest;
import com.charles.bookstore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    ResponseEntity<PagedModel<EntityModel<BookDto>>> index(
            @PageableDefault(page = 0, size = Integer.MAX_VALUE, sort = {"publicationYear"}) Pageable paging) {

        return new ResponseEntity<>( bookService.getAllBooks(paging), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    ResponseEntity<BookDto> show(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBook(id),HttpStatus.OK);
    }

    @PostMapping("/books")
    ResponseEntity<BookDto> store(@RequestBody @Valid BookRequest request) {
        return new ResponseEntity<>(bookService.addBook(request), HttpStatus.CREATED);
    }

    @PutMapping("/books/{id}")
    ResponseEntity<BookDto>update(@RequestBody BookRequest newBook, @PathVariable Long id) {
      return new ResponseEntity<>(bookService.updateBook(newBook,id), HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
