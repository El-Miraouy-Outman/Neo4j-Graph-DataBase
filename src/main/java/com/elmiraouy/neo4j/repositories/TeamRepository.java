package com.elmiraouy.neo4j.repositories;

import com.elmiraouy.neo4j.entities.Team;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TeamRepository extends Neo4jRepository<Team,String> {
}
