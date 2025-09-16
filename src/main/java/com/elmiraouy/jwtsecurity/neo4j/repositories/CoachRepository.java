package com.elmiraouy.jwtsecurity.neo4j.repositories;

import com.elmiraouy.jwtsecurity.neo4j.entities.Coach;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface  CoachRepository extends Neo4jRepository<Coach,String> {
    // 👉 Option performante sans charger le graphe (facultatif)
    @Query("""
    MATCH (c:COACH {id: $coachId}), (t:TEAM {id: $teamId})
    MERGE (c)-[:COACHES_FOR]->(t)
  """)
    void linkCoachToTeam(String coachId, String teamId);

    @Query("""
    MATCH (c:COACH {id: $coachId})-[r:COACHES_FOR]->(t:TEAM {id: $teamId})
    DELETE r
  """)
    void unlinkCoachFromTeam(String coachId, String teamId);
}
