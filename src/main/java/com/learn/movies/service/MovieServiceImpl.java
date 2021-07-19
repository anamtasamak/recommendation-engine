package com.learn.movies.service;

import com.learn.movies.entity.Genre;
import com.learn.movies.entity.Movie;
import com.learn.movies.entity.User;
import com.learn.movies.repository.GenreRepository;
import com.learn.movies.repository.MovieRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    GenreRepository genreRepository;

    @Override
    public Movie createMovie(String title, Double rating){

        if(StringUtils.isBlank(title))
            throw new IllegalArgumentException("Title can not be null");

        if(rating == null)
            throw new IllegalArgumentException("Rating can not be null");

        Movie movie = new Movie(title, rating);
        movieRepository.save(movie);
        return movie;
    }

    @Override
    public Movie addGenre(Long movieId, Long genreId){
        Optional<Movie> movieOp =  movieRepository.findById(movieId);
        Movie movie = movieOp.orElse(null);
        if(movie == null)
            throw new IllegalArgumentException("User can not be null");

        Optional<Genre> genreOp = genreRepository.findById(genreId);
        Genre genre = genreOp.orElse(null);
        if(genre == null)
            throw new IllegalArgumentException("Genre can not be null");

        movie.addGenre(genre);
        movieRepository.save(movie);
        return movie;

    }


    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Set<Movie> getUnwatchedMoviesInLikedGenre(User user){
        return movieRepository.getUnwatchedMoviesInLikedGenre(user.getName());
    }

    @Override
    public Set<Movie> getMoviesWatchedByOtherUsers(User user){
        return movieRepository.getMoviesWatchedByOtherUsers(user.getName());
    }

    @Override
    public Set<Movie> getRecommendations(User user){

        Set<Movie> contentBasedFiltering = getUnwatchedMoviesInLikedGenre(user);
        Set<Movie> collaborativeFiltering = getMoviesWatchedByOtherUsers(user);

        Set<Movie> recommendations = new HashSet<>();
        recommendations.addAll(contentBasedFiltering);
        recommendations.addAll(collaborativeFiltering);

        return recommendations;
    }
}
