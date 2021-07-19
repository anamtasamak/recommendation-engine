package com.learn.movies.service;

import com.learn.movies.entity.User;

public interface UserService {

    User createUser(String name);

    User addWatchedMovies(User user, Long movieId);

    User addLikedGenres(User user, Long genreId);
}
