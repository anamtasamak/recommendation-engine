package com.learn.movies.controller;

import com.learn.movies.config.Context;
import com.learn.movies.entity.Genre;
import com.learn.movies.entity.Movie;
import com.learn.movies.entity.User;
import com.learn.movies.objects.*;
import com.learn.movies.service.GenreService;
import com.learn.movies.service.MovieService;
import com.learn.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    MovieService movieService;
    @Autowired
    GenreService genreService;

    @PostMapping(path = "/user")
    public User createUser(@RequestBody CreateUserRequest createUserRequest){
        return userService.createUser(createUserRequest.getName());
    }

    @PostMapping(path = "/genre")
    public Genre createGenre(@RequestBody CreateGenreRequest createGenreRequest){
        return genreService.createGenre(createGenreRequest.getTitle());
    }

    @PostMapping(path = "/movie")
    public Movie createMovie(@RequestBody CreateMovieRequest createMovieRequest){
        return movieService.createMovie(createMovieRequest.getTitle(), createMovieRequest.getRating());
    }


    @PostMapping(path = "/movie/{movieId}/genre")
    public Movie addGenre(@PathVariable Long movieId,
                          @RequestBody AddGenreRequest genreRequest){
        return movieService.addGenre(movieId, genreRequest.getGenreId());
    }

    @GetMapping(path = "/movie")
    public List<Movie> getMovies(){
        return movieService.getMovies();
    }


}
