package com.elmiraouy.neo4j.entities;


import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import java.util.*;

@Node("PLAYER")
public class Player {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    private String name;
    private Integer age;
    private Integer number;
    private Double height;
    private Double weight;

    // Relations


    @Relationship(type = "TEAMMATES", direction = Relationship.Direction.OUTGOING)
    private Set<Player> teammates = new HashSet<>();

    @Relationship(type = "PLAYS_FOR", direction = Relationship.Direction.OUTGOING)
    private Set<PlaysFor> playsFor = new HashSet<>();

    @Relationship(type = "PLAYED_AGAINST", direction = Relationship.Direction.OUTGOING)
    private Set<PlayedAgainst> playedAgainst = new HashSet<>();

    public Player() { }

    public Player(String name, Integer age, Integer number, Double height, Double weight) {
        this.name = name;
        this.age = age;
        this.number = number;
        this.height = height;
        this.weight = weight;
    }

    // Getters / Setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Integer getNumber() { return number; }
    public void setNumber(Integer number) { this.number = number; }

    public Double getHeight() { return height; }
    public void setHeight(Double height) { this.height = height; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public Set<Player> getTeammates() { return teammates; }
    public void setTeammates(Set<Player> teammates) { this.teammates = teammates; }

    public Set<PlaysFor> getPlaysFor() { return playsFor; }
    public void setPlaysFor(Set<PlaysFor> playsFor) { this.playsFor = playsFor; }

    public Set<PlayedAgainst> getPlayedAgainst() { return playedAgainst; }
    public void setPlayedAgainst(Set<PlayedAgainst> playedAgainst) { this.playedAgainst = playedAgainst; }
}

