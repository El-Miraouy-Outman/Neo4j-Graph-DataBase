package com.elmiraouy.neo4j.services;
import org.springframework.stereotype.Service;
import org.springframework.data.neo4j.core.Neo4jClient;

import java.util.List;
@Service
public class MetaService {
    private final Neo4jClient neo4j;

    public MetaService(Neo4jClient neo4j) {
        this.neo4j = neo4j;
    }

    // Option 1: procédure (toutes versions)
    public List<String> getNodeLabels() {
        String cypher = "CALL db.labels() YIELD label RETURN label ORDER BY label";
        return neo4j.query(cypher)
                .fetch().all().stream()
                .map(row -> (String) row.get("label"))
                .toList();
    }

    // Option 2: SHOW (Neo4j 5+)
    public List<String> getNodeLabelsShow() {
        String cypher = "SHOW LABELS YIELD name RETURN name ORDER BY name";
        return neo4j.query(cypher)
                .fetch().all().stream()
                .map(row -> (String) row.get("name"))
                .toList();
    }

    public List<String> getNodeLabelsDistinct() {
        String cypher = """
                  MATCH (n)
                  UNWIND labels(n) AS label
                  RETURN DISTINCT label
                  ORDER BY label
                """;
        return neo4j.query(cypher)
                .fetch().all().stream()
                .map(row -> (String) row.get("label"))
                .toList();
    }

    // info sur les  Relations
    // ✅ Option 1 — Procédure (toutes versions Neo4j)
    public List<String> getRelationshipTypes() {
        String cypher = "CALL db.relationshipTypes() YIELD relationshipType " +
                "RETURN relationshipType ORDER BY relationshipType";
        return neo4j.query(cypher).fetch().all().stream()
                .map(r -> (String) r.get("relationshipType"))
                .toList();
    }

    //  SHOW (Neo4j 5+)
    public List<String> getRelationshipTypesShow() {
        String cypher = "SHOW RELATIONSHIP TYPES YIELD name RETURN name ORDER BY name";
        return neo4j.query(cypher).fetch().all().stream()
                .map(r -> (String) r.get("name"))
                .toList();
    }

    // Fallback sans procédures (scan des relations)
    public List<String> getRelationshipTypesDistinct() {
        String cypher = """
                  MATCH ()-[r]-()
                  RETURN DISTINCT type(r) AS name
                  ORDER BY name
                """;
        return neo4j.query(cypher).fetch().all().stream()
                .map(r -> (String) r.get("name"))
                .toList();
    }

    // Compter par type
    public record RelCount(String type, long count) {
    }

    public List<RelCount> getRelationshipTypeCounts() {
        String cypher = """
                  MATCH ()-[r]-()
                  RETURN type(r) AS type, count(*) AS count
                  ORDER BY type
                """;
        return neo4j.query(cypher).fetch().all().stream()
                .map(r -> new RelCount((String) r.get("type"), ((Number) r.get("count")).longValue()))
                .toList();
    }

    //  “Schéma” simple: type + labels source/target + count
    public record RelSchemaRow(String type, List<String> fromLabels, List<String> toLabels, long count) {
    }

    public List<RelSchemaRow> getRelationshipSchema() {
        String cypher = """
                  MATCH (a)-[r]->(b)
                  RETURN type(r) AS type, labels(a) AS fromLabels, labels(b) AS toLabels, count(*) AS count
                  ORDER BY type, fromLabels, toLabels
                """;
        return neo4j.query(cypher).fetch().all().stream()
                .map(r -> new RelSchemaRow(
                        (String) r.get("type"),
                        (List<String>) r.get("fromLabels"),
                        (List<String>) r.get("toLabels"),
                        ((Number) r.get("count")).longValue()))
                .toList();
    }

    //  Clés de propriétés par type
    public record RelKeys(String type, List<String> keys) {
    }

    public List<RelKeys> getRelationshipKeys() {
        String cypher = """
                  MATCH ()-[r]->()
                  WITH type(r) AS t, collect(DISTINCT keys(r)) AS ksets
                  UNWIND ksets AS ks
                  UNWIND ks AS k
                  WITH t, collect(DISTINCT k) AS keys
                  RETURN t AS type, keys
                  ORDER BY type
                """;
        return neo4j.query(cypher).fetch().all().stream()
                .map(r -> new RelKeys((String) r.get("type"), (List<String>) r.get("keys")))
                .toList();
    }

}
