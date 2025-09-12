package com.elmiraouy.jwtsecurity.neo4j.entities;


import lombok.Getter;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import java.util.*;

@Getter
@Node("COACH")
public class Coach {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    private String name;

    // (coach)-[:COACHES_FOR]->(team)
    @Relationship(type = "COACHES_FOR", direction = Relationship.Direction.OUTGOING)
    private Set<Team> coachesFor = new HashSet<>();

    public Coach() { }

    public Coach(String name) {
        this.name = name;
    }

    public void setId(String id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setCoachesFor(Set<Team> coachesFor) { this.coachesFor = coachesFor; }
}

