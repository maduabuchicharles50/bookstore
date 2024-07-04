package com.charles.bookstore.controllers;


import com.charles.bookstore.dto.GenreDto;
import com.charles.bookstore.entity.Genre;
import com.charles.bookstore.repository.GenreRepository;
import com.charles.bookstore.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private GenreService genreService;

    @GetMapping("/genres")
    ResponseEntity<PagedModel<EntityModel<GenreDto>>> index(
            @PageableDefault(page = 0, size = Integer.MAX_VALUE, sort = {"type"}) Pageable paging) {
        return new ResponseEntity<>(genreService.getAllGenres(paging), HttpStatus.OK);
    }

    @GetMapping("/genres/{id}")
    ResponseEntity<GenreDto> show(@PathVariable Long id) {
        return new ResponseEntity<>( genreService.getGenre(id), HttpStatus.OK);
    }
    @PostMapping("/genres")
    ResponseEntity<GenreDto> store(@RequestBody @Valid Genre genre) {
        return new ResponseEntity<>(genreService.createGenre(genre), HttpStatus.CREATED);
    }
    @PutMapping("/genres/{id}")
    ResponseEntity<GenreDto> update(@RequestBody Genre newGenre, @PathVariable Long id) {
      return new ResponseEntity<>(genreService.updateGenre(newGenre,id), HttpStatus.OK);
    }
    @DeleteMapping("/genres/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


