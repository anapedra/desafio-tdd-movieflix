package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository repository;
    private final GenreRepository genreRepository;
    public MovieService(MovieRepository repository, GenreRepository genreRepository) {
        this.repository = repository;
        this.genreRepository = genreRepository;
    }

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id) {
        Optional<Movie> obj = repository.findById(id);
        Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new MovieDetailsDTO(entity);
    }



    @Transactional(readOnly = true)
    public Page<MovieCardDTO> pageMovie(Long genreId,String title,Pageable pageable) {
        Genre genre=(genreId == 0) ? null : genreRepository.getReferenceById(genreId);
        Page<Movie> page =repository.pageMovie(genre,title,pageable);
        return page.map(MovieCardDTO::new);
    }

/*
    @Transactional(readOnly = true)
    public Page<MovieCardDTO> find(Long genreId, Pageable pageable){
        Genre genre = (genreId == 0) ? null : genreRepository.getReferenceById(genreId);
        Page<Movie> page = repository.findByGenre_Id(genre,pageable);
        repository.findMovie(page.stream().collect(Collectors.toList()));
        return page.map(MovieCardDTO::new);
    }

 */

}
