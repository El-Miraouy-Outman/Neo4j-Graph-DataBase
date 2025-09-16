package com.elmiraouy.neo4j.entities;

import lombok.Getter;
import org.springframework.data.neo4j.core.schema.*;

@Getter
@RelationshipProperties
public class PlaysFor {

    @Id
    @GeneratedValue
    private Long id;           // id technique de la relation

    private Integer salary;

    @TargetNode
    private Team team;

    public PlaysFor() { }

    public PlaysFor(Integer salary, Team team) {
        this.salary = salary;
        this.team = team;
    }

    public void setId(Long id) { this.id = id; }

    public void setSalary(Integer salary) { this.salary = salary; }

    public void setTeam(Team team) { this.team = team; }
}
