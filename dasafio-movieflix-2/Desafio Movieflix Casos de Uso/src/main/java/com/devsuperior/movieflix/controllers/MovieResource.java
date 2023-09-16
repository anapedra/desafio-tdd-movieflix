package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {
    private final MovieService service;
    public MovieResource(MovieService service) {
        this.service = service;
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_VISITOR')")
    public ResponseEntity<Page<MovieCardDTO>> findAll(
            @RequestParam(value = "genreId",defaultValue = "0") Long genreId,
            @RequestParam(value = "title",defaultValue = "") String title,
            Pageable pageable) {
        Page<MovieCardDTO> page = service.pageMovie(genreId,title.trim(),pageable);
        return ResponseEntity.ok().body(page);
    }

/*
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_VISITOR')")
    public ResponseEntity<Page<MovieCardDTO>> findAll(
            @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
            Pageable pageable) {
        Page<MovieCardDTO> page = service.find(genreId ,pageable);
        return ResponseEntity.ok().body(page);
    }

 */



    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_VISITOR')")
    public ResponseEntity<MovieDetailsDTO> findById(@PathVariable Long id) {
        MovieDetailsDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
}
