package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {


     @Query("SELECT DISTINCT obj FROM Movie obj WHERE "
            + "(:genre IS NULL OR :genre = obj.genre) AND "
            + "(LOWER(obj.title) LIKE LOWER(CONCAT('%',:title,'%'))) order by obj.title")
    Page<Movie> pageMovie(Genre genre, String title, Pageable pageable);




/*
    @Query("SELECT obj FROM Movie obj INNER JOIN obj.genre gen  WHERE " +
            "(COALESCE(:genre) IS NULL OR gen IN :genre)  " )
    Page<Movie> find(Genre genre, Pageable pageable);
    @Query("SELECT obj FROM Movie obj JOIN FETCH obj.genre  WHERE obj IN : movies")
    List<Movie> findMovie(List<Movie> movies);

 */




  //  Page<Movie> findByGenre_Id(Genre genre, Pageable pageable);

}
