package com.learn.movies.controller;

import com.learn.movies.config.Context;
import com.learn.movies.entity.Movie;
import com.learn.movies.entity.User;
import com.learn.movies.objects.AddGenreRequest;
import com.learn.movies.objects.AddMovieRequest;
import com.learn.movies.service.MovieService;
import com.learn.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    MovieService movieService;

    @PostMapping(path = "/user/movie")
    public User addWatchedMovie(@RequestBody AddMovieRequest movieRequest){
        User user = Context.getUser();
        return userService.addWatchedMovies(user,movieRequest.getMovieId());
    }

    @PostMapping(path = "/user/genre")
    public User addLikedGenre(@RequestBody AddGenreRequest genreRequest){
        User user = Context.getUser();
        return userService.addLikedGenres(user, genreRequest.getGenreId());
    }

    @GetMapping(path = "/user/recommendation")
    public Set<Movie> getMovie(){
        User user = Context.getUser();
        return movieService.getRecommendations(user);
    }
}
