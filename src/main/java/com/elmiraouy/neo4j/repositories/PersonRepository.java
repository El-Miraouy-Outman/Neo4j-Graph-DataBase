package com.elmiraouy.neo4j.repositories;

import java.util.List;

import com.elmiraouy.neo4j.entities.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    Person findByName(String name);
    List<Person> findByTeammatesName(String name);
}
