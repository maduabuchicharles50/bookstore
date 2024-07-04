package com.charles.bookstore.service;

import com.charles.bookstore.dto.AuthorDto;
import com.charles.bookstore.dto.GenreDto;
import com.charles.bookstore.entity.Author;
import com.charles.bookstore.entity.Genre;
import com.charles.bookstore.exception.ModelNotFoundException;
import com.charles.bookstore.repository.AuthorRepository;
import com.charles.bookstore.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private PagedResourcesAssembler<GenreDto> pagedResourcesAssembler;

    public PagedModel<EntityModel<GenreDto>> getAllGenres(Pageable paging) {
        Page<Genre> result = genreRepository.findAll(paging);

        return  pagedResourcesAssembler.toModel(result.map(GenreDto::new));
    }

    public GenreDto getGenre(Long id) {
        return new GenreDto(genreRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(Genre.class, id)));
    }

    public GenreDto createGenre(Genre genre) {
        return new GenreDto(genreRepository.save(genre));
    }

    public GenreDto updateGenre(Genre newGenre, Long id) {
        var genre = genreRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(Genre.class, id));

        if (!genre.getType().equals(newGenre.getType())) {
            genre.setType(newGenre.getType());
        }
        return new GenreDto(genreRepository.save(genre));
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}
