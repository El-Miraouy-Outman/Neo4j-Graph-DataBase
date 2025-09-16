package com.elmiraouy.neo4j.services;

import com.elmiraouy.neo4j.entities.Coach;
import com.elmiraouy.neo4j.entities.Team;
import com.elmiraouy.neo4j.repositories.CoachRepository;
import com.elmiraouy.neo4j.repositories.TeamRepository;
import jakarta.transaction.Transactional;

public class CoachService {
    private final CoachRepository coachRepo;
    private final TeamRepository teamRepo;

    public CoachService(CoachRepository coachRepo, TeamRepository teamRepo) {
        this.coachRepo = coachRepo;
        this.teamRepo = teamRepo;
    }

    // Version entités (lit coach & team, ajoute au Set, puis save)
    @Transactional
    public void linkCoachToTeam(String coachId, String teamId) {
        Coach coach = coachRepo.findById(coachId)
                .orElseThrow(() -> new IllegalArgumentException("Coach introuvable: " + coachId));
        Team team = teamRepo.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team introuvable: " + teamId));

        if (coach.getCoachesFor() == null) {
            coach.setCoachesFor(new java.util.HashSet<>());
        }
        boolean alreadyLinked = coach.getCoachesFor().stream()
                .anyMatch(t -> teamId.equals(t.getId()));
        if (!alreadyLinked) {
            coach.getCoachesFor().add(team);
            coachRepo.save(coach); // crée (:COACH)-[:COACHES_FOR]->(:TEAM)
        }
    }

    @Transactional
    public void unlinkCoachFromTeam(String coachId, String teamId) {
        Coach coach = coachRepo.findById(coachId)
                .orElseThrow(() -> new IllegalArgumentException("Coach introuvable: " + coachId));
        if (coach.getCoachesFor() != null) {
            coach.getCoachesFor().removeIf(t -> teamId.equals(t.getId()));
            coachRepo.save(coach); // supprime la relation
        }
    }

    // 👉 Variante sans charger le graphe (utilise les @Query du repo)
    @Transactional
    public void linkCoachToTeamFast(String coachId, String teamId) {
        // Optionnel: vérifier l'existence avant (sinon MERGE échoue silencieusement si l'un manque)
        if (!coachRepo.existsById(coachId)) {
            throw new IllegalArgumentException("Coach introuvable: " + coachId);
        }
        if (!teamRepo.existsById(teamId)) {
            throw new IllegalArgumentException("Team introuvable: " + teamId);
        }
        coachRepo.linkCoachToTeam(coachId, teamId);
    }

    @Transactional
    public void unlinkCoachFromTeamFast(String coachId, String teamId) {
        coachRepo.unlinkCoachFromTeam(coachId, teamId);
    }
}
