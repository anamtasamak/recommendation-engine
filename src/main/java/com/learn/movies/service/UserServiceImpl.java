package com.learn.movies.service;

import com.learn.movies.entity.Genre;
import com.learn.movies.entity.Movie;
import com.learn.movies.entity.User;
//import com.learn.movies.objects.UserPrincipal;
import com.learn.movies.objects.UserPrincipal;
import com.learn.movies.repository.GenreRepository;
import com.learn.movies.repository.MovieRepository;
import com.learn.movies.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    GenreRepository genreRepository;

    @Override
    public User createUser(String name) {

        if(StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name can not be null");
        User user = new User(name,"abc", "abc" );
        userRepository.save(user);
        return user;
    }


    @Override
    public User addWatchedMovies(User user, Long movieId) {

        Optional<Movie> movieOp = movieRepository.findById(movieId);
        Movie movie = movieOp.orElse(null);
        if(movie == null)
            throw new IllegalArgumentException("Movie can not be null");

        user.addMovie(movie);
        userRepository.save(user);
        return user;
    }

    @Override
    public User addLikedGenres(User user, Long genreId) {

        Optional<Genre> genreOp = genreRepository.findById(genreId);
        Genre genre = genreOp.orElse(null);
        if(genre == null)
            throw new IllegalArgumentException("Genre can not be null");

        user.addGenre(genre);
        userRepository.save(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);
    }
}
