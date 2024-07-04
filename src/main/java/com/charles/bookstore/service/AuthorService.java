package com.charles.bookstore.service;

import com.charles.bookstore.dto.AuthorDto;
import com.charles.bookstore.entity.Author;
import com.charles.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PagedResourcesAssembler<AuthorDto> pagedResourcesAssembler;


    public PagedModel<EntityModel<AuthorDto>> getAllAuthors(Pageable paging) {
        Page<Author> result = authorRepository.findAll(paging);

        return pagedResourcesAssembler.toModel(result.map(AuthorDto::new));
    }

    public AuthorDto getAuthor(Long id) {
        return new AuthorDto(authorRepository.findById(id).get());
    }

    public AuthorDto createAuthors(Author author) {
        return new AuthorDto(authorRepository.save(author));
    }

    public AuthorDto updateAuthor(Author newAuthor, Long id) {
        var author = authorRepository.findById(id).get();

        if (!author.getName().equals(newAuthor.getName())) {
            author.setName(newAuthor.getName());
        }
        if (!author.getContact().equals(newAuthor.getContact())) {
            author.setContact(newAuthor.getContact());
        }
        return new AuthorDto(authorRepository.save(author));
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
