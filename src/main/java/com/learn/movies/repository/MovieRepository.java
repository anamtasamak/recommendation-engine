package com.learn.movies.repository;

import com.learn.movies.entity.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface MovieRepository extends Neo4jRepository<Movie, Long> {

    @Query("match(user:User{name:$name})-[:LIKES_GENRE]->(genre:Genre)<-[:IN_GENRE]-(movie:Movie) where not exists((user)-[:WATCHED]->(movie)) return movie")
    Set<Movie> getUnwatchedMoviesInLikedGenre(@Param("name") String name);

    @Query("match(anamta:User{name:$name})-[:WATCHED]->(movie:Movie)<-[:WATCHED]-(user:User)  MATCH(m:Movie)<-[:WATCHED]-(user) where not exists((anamta)-[:WATCHED]->(m)) return m")
    Set<Movie> getMoviesWatchedByOtherUsers(@Param("name") String name);

}
