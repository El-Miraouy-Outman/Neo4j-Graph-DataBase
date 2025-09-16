package com.elmiraouy.neo4j.entities;

import lombok.Getter;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import java.util.*;

@Getter
@Node("TEAM")
public class Team {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    private String name;

    // Inverses

    // (player)-[:PLAYS_FOR]->(this)
    @Relationship(type = "PLAYS_FOR", direction = Relationship.Direction.INCOMING)
    private Set<PlaysFor> players = new HashSet<>();

    // (coach)-[:COACHES_FOR]->(this)
    @Relationship(type = "COACHES_FOR", direction = Relationship.Direction.INCOMING)
    private Set<Coach> coaches = new HashSet<>();

    // (player)-[:PLAYED_AGAINST{...}]->(this)
    @Relationship(type = "PLAYED_AGAINST", direction = Relationship.Direction.INCOMING)
    private Set<PlayedAgainst> playedAgainstBy = new HashSet<>();

    public Team() { }

    public Team(String name) {
        this.name = name;
    }

    public void setId(String id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setPlayers(Set<PlaysFor> players) { this.players = players; }

    public void setCoaches(Set<Coach> coaches) { this.coaches = coaches; }

    public void setPlayedAgainstBy(Set<PlayedAgainst> playedAgainstBy) { this.playedAgainstBy = playedAgainstBy; }
}
