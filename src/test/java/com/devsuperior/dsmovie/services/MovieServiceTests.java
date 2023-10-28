package com.devsuperior.dsmovie.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.entities.MovieEntity;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dsmovie.tests.MovieFactory;

@ExtendWith(SpringExtension.class)
public class MovieServiceTests {

	@InjectMocks
	private MovieService service;

	@Mock
	private MovieRepository repository;

	private Long existingId, nonExistingId;
	private String movieTitle;
	private MovieEntity movie;
	@SuppressWarnings("unused")
	private MovieDTO movieDTO;
	private PageImpl<MovieEntity> page;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 2L;

		movieTitle = "Test Movie";

		movie = MovieFactory.createMovieEntity();
		page = new PageImpl<>(List.of(movie));
		movieDTO = MovieFactory.createMovieDTO();
		MovieEntity existingEntity = new MovieEntity();

		Mockito.when(repository.searchByTitle(Mockito.anyString(), ArgumentMatchers.any(Pageable.class)))
				.thenReturn(page);

		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(movie));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

		Mockito.when(repository.save(any())).thenReturn(movie);
		
		Mockito.when(repository.getReferenceById(existingId)).thenReturn(movie);

	}

	@Test
	public void findAllShouldReturnPagedMovieDTO() {
		Pageable pageable = PageRequest.of(0, 12);

		Page<MovieDTO> result = service.findAll(movieTitle, pageable);
		Assertions.assertEquals(1, result.getSize());
		for (MovieDTO movieDTO : result.getContent()) {
			Assertions.assertEquals(movieTitle, movieDTO.getTitle());
		}

	}

	@Test
	public void findByIdShouldReturnMovieDTOWhenIdExists() {

		MovieDTO result = service.findById(existingId);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), existingId);
		Assertions.assertEquals(result.getTitle(), movie.getTitle());
	}

	@Test
	public void findByIdThrowsResourceNotFoundExceptionWhenIdDoesNotExist() {

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
		assertFalse(exception.getMessage().contains("Recurso não encontrado " + nonExistingId));
	}
	
	@Test
	  public void insertShouldReturnMovieDTO() {
		  
		  MovieDTO result = service.insert(movieDTO);
		  
		  Assertions.assertNotNull(result);
		  Assertions.assertEquals(movieDTO.getId(), result.getId());
		  
	  }
	@Test
	   public void updateShouldReturnMovieDTOWhenIdExists() {
		
		MovieDTO result = service.update(existingId, movieDTO);
		
		 Assertions.assertNotNull(result);
		 Assertions.assertEquals(existingId, result.getId());
		   
	   }
	}


