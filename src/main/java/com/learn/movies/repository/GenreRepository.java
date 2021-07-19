package com.learn.movies.repository;

import com.learn.movies.entity.Genre;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface GenreRepository extends Neo4jRepository<Genre, Long> {
}
