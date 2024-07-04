package com.charles.bookstore.controllers;

import com.charles.bookstore.entity.Author;
import com.charles.bookstore.entity.Book;
import com.charles.bookstore.entity.Genre;
import com.charles.bookstore.repository.AuthorRepository;
import com.charles.bookstore.repository.BookRepository;
import com.charles.bookstore.repository.GenreRepository;
import com.charles.bookstore.request.BookRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/books")
    List<Book> index() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    Book show(@PathVariable Long id) {
        return bookRepository.findById(id).get();
    }

    @PostMapping("/books")
    Book store(@RequestBody @Valid BookRequest request) {
        Author author = authorRepository.findById(request.authorId).orElseThrow();
        Genre genre = genreRepository.findById(request.genreId).orElseThrow();
        Book book = new Book(request.title,author,genre,request.isbn,request.publicationYear);

        return bookRepository.save(book);

    }

    @PutMapping("/books/{id}")
    Book update(@RequestBody BookRequest newBook, @PathVariable Long id) {
        var book = bookRepository.findById(id).get();
        var genre = genreRepository.findById(newBook.genreId).get();
        var author = authorRepository.findById(newBook.authorId).get();

        if (!book.getTitle().equals(newBook.title)) {
            book.setTitle(newBook.title);
        }
        if (!book.getAuthor().equals(author)) {
            book.setAuthor(author);
        }

        if (!book.getGenre().equals(genre)) {
            book.setGenre(genre);
        }
        if (!book.getIsbn().equals(newBook.isbn)) {
            book.setIsbn(newBook.isbn);
        }
        if (!book.getPublicationYear().equals(newBook.publicationYear)) {
            book.setPublicationYear(newBook.publicationYear);
        }

        return bookRepository.save(book);
    }

    @DeleteMapping("/books/{id}")
    void delete(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}
