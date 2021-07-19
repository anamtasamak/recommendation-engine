package com.learn.movies.service;

import com.learn.movies.entity.Genre;
import com.learn.movies.repository.GenreRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{

    @Autowired
    GenreRepository genreRepository;

    @Override
    public Genre createGenre(String title){
        if(StringUtils.isBlank(title))
            throw new IllegalArgumentException("Genre name can not be null");

        Genre genre = new Genre(title);
        genreRepository.save(genre);
        return genre;
    }

    @Override
    public List<Genre> findAllById(List<Long> genreIdList){
        Iterable<Long> genreIds = genreIdList;
        return genreRepository.findAllById(genreIds);
    }
}
