package com.elmiraouy.neo4j.services;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.data.neo4j.core.Neo4jClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Service
public class InitDbByQuery {

    private final Neo4jClient neo4jClient;
    ResourceLoader resourceLoader;
    public InitDbByQuery(Neo4jClient neo4jClient, ResourceLoader resourceLoader) {
        this.neo4jClient = neo4jClient;
        this.resourceLoader = resourceLoader;
    }

    public void InitDatabase() {

        try {
            Resource resource = resourceLoader.getResource("classpath:Queries-Neo4j/initDB.cypher");
            String cypherQuery;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                cypherQuery = reader.lines().collect(Collectors.joining("\n"));
            }

            neo4jClient.query(cypherQuery).run();
            System.out.println("Script Cypher exécuté avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution du script Cypher : " + e.getMessage());
        }

    }
}

