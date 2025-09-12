package com.elmiraouy.jwtsecurity.neo4j.repositories;

import com.elmiraouy.jwtsecurity.neo4j.entities.Player;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.*;

public interface PlayerRepository extends Neo4jRepository<Player, String> {
    Optional<Player> findByName(String name);

    List<Player> findByNameContainingIgnoreCase(String name);
    @Query("MATCH")
    List<Player> findPlayerByCoach(String nameCoach);
}
