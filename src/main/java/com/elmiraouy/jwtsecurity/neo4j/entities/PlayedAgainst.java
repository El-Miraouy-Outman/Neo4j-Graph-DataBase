package com.elmiraouy.jwtsecurity.neo4j.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

@Setter
@Getter
@RelationshipProperties
public class PlayedAgainst {

    @Id
    @GeneratedValue
    private Long id;

    private Integer minutes;
    private Integer points;
    private Integer assists;
    private Integer rebounds;
    private Integer turnovers;

    @TargetNode
    private Team opponent;

    public PlayedAgainst() { }

    public PlayedAgainst(Integer minutes, Integer points, Integer assists,
                         Integer rebounds, Integer turnovers, Team opponent) {
        this.minutes = minutes;
        this.points = points;
        this.assists = assists;
        this.rebounds = rebounds;
        this.turnovers = turnovers;
        this.opponent = opponent;
    }

}
