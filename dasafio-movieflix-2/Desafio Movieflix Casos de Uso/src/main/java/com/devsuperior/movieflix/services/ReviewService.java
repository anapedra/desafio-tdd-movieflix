package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

	private final AuthService authService;
    private final ReviewRepository repository;
    public ReviewService(AuthService authService, ReviewRepository repository) {
		this.authService = authService;
		this.repository = repository;
    }

	@Transactional(readOnly = true)
	public List<ReviewDTO> findReviewWithMovie(Long id) {
		List<Review> result = repository.findReviewWithMovie();
		return result.stream().map(ReviewDTO::new).collect(Collectors.toList());
	}

    @Transactional
	public ReviewDTO insert(ReviewDTO dto) {
        Review entity = new Review();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ReviewDTO(entity);
	}



	private void copyDtoToEntity(ReviewDTO dto, Review entity) {

		entity.setText(dto.getText());

		Movie movie=new Movie();
		movie.setId(dto.getMovieId());
		entity.setMovie(movie);

		User user=authService.authenticated();
		entity.setUser(user);




	}
}



