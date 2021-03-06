package com.learn.movies.repository;

import com.learn.movies.entity.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserRepository extends Neo4jRepository<User, Long> {
    User findByUsername(String username);

}
