package com.learn.movies.service;

import com.learn.movies.entity.Genre;

import java.util.List;

public interface GenreService {
    Genre createGenre(String title);

    List<Genre> findAllById(List<Long> genreIdList);
}
