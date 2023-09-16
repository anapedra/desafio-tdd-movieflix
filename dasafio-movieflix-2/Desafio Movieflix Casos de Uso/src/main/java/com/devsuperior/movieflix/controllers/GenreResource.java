package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.services.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/genres")
public class GenreResource {

    private final GenreService service;
    public GenreResource(GenreService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_VISITOR')")
    public ResponseEntity<List<GenreDTO>> findAll() {
        List<GenreDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

}
