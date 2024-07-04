package com.charles.bookstore.controllers;

import com.charles.bookstore.dto.BookDto;
import com.charles.bookstore.entity.Author;
import com.charles.bookstore.entity.Book;
import com.charles.bookstore.entity.Genre;
import com.charles.bookstore.repository.AuthorRepository;
import com.charles.bookstore.repository.BookRepository;
import com.charles.bookstore.repository.GenreRepository;
import com.charles.bookstore.request.BookRequest;
import com.charles.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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
    PagedModel<EntityModel<BookDto>> index(
            @PageableDefault(page = 0, size = Integer.MAX_VALUE, sort = {"publicationYear"}) Pageable paging) {

        return bookService.getAllBooks(paging);
    }

    @GetMapping("/books/{id}")
    BookDto show(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @PostMapping("/books")
    BookDto store(@RequestBody BookRequest request) {
        return bookService.addBook(request);
    }

    @PutMapping("/books/{id}")
    BookDto update(@RequestBody BookRequest newBook, @PathVariable Long id) {
      return bookService.updateBook(newBook,id);
    }

    @DeleteMapping("/books/{id}")
    void delete(@PathVariable Long id) {
         bookService.deleteBook(id);
    }
}
