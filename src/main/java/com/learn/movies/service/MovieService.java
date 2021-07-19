package com.learn.movies.service;

import com.learn.movies.entity.Movie;
import com.learn.movies.entity.User;

import java.util.List;
import java.util.Set;

public interface MovieService {

    Movie createMovie(String title, Double rating);

    Movie addGenre(Long movieId, Long genreId);

    List<Movie> getMovies();

    Set<Movie> getUnwatchedMoviesInLikedGenre(User user);

    Set<Movie> getMoviesWatchedByOtherUsers(User user);

    Set<Movie> getRecommendations(User user);
}
