package com.charles.bookstore.service;

import com.charles.bookstore.dto.BookDto;
import com.charles.bookstore.entity.Author;
import com.charles.bookstore.entity.Book;
import com.charles.bookstore.entity.Genre;
import com.charles.bookstore.exception.ModelNotFoundException;
import com.charles.bookstore.repository.AuthorRepository;
import com.charles.bookstore.repository.BookRepository;
import com.charles.bookstore.repository.GenreRepository;
import com.charles.bookstore.request.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private PagedResourcesAssembler<BookDto> pagedResourcesAssembler;

    public PagedModel<EntityModel<BookDto>> getAllBooks(Pageable paging) {
        Page<Book> result = bookRepository.findAll(paging);
        return pagedResourcesAssembler.toModel(result.map(BookDto::new));
    }

    public BookDto getBook(Long id) {
        return new BookDto(bookRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(Book.class, id)));
    }

    public BookDto addBook(BookRequest request) {
        Author author = authorRepository.findById(request.authorId).orElseThrow(() -> new ModelNotFoundException(Author.class, request.authorId));
        Genre genre = genreRepository.findById(request.genreId).orElseThrow(() -> new ModelNotFoundException(Genre.class, request.genreId));
        Book book = new Book(request.title, author, genre, request.isbn, request.publicationYear);

        return new BookDto(bookRepository.save(book));
    }

    public BookDto updateBook(BookRequest newBook, Long id) {
        var book = bookRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(Book.class, id));
        var genre = genreRepository.findById(newBook.genreId).orElseThrow(() -> new ModelNotFoundException(Genre.class, newBook.genreId));
        var author = authorRepository.findById(newBook.authorId).orElseThrow(() -> new ModelNotFoundException(Author.class, newBook.authorId));

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

        return new BookDto(bookRepository.save(book));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}