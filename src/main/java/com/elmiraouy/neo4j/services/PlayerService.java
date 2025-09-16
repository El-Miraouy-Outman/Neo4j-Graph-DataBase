package com.elmiraouy.neo4j.services;

import com.elmiraouy.neo4j.entities.Player;
import com.elmiraouy.neo4j.dtos.PlayerInput;
import com.elmiraouy.neo4j.repositories.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.*;

@Service
public class PlayerService {

    private final PlayerRepository repo;

    public PlayerService(PlayerRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public Player create(PlayerInput in) {
        if (in == null || in.name == null || in.name.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name is required");
        }
        Player p = new Player(in.name, in.age, in.number, in.height, in.weight);
        return repo.save(p);
    }

    public Player getById(String id) {
        return repo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found: " + id));
    }

    public List<Player> getAll() {
        return repo.findAll();
    }

    public List<Player> searchByName(String q) {
        if (q == null || q.isBlank())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found: " + q);
        return repo.findByNameContainingIgnoreCase(q);
    }
    public List<Player> playersByCoach(String q) {
        if (q == null || q.isBlank())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found: " + q);
        return repo.playersByCoach(q);
    }

    @Transactional
    public Player update(String id, PlayerInput in) {
        Player p = getById(id);
        if (in.name == null || in.name.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name is required");
        }
        p.setName(in.name);
        p.setAge(in.age);
        p.setNumber(in.number);
        p.setHeight(in.height);
        p.setWeight(in.weight);
        return repo.save(p);
    }

    @Transactional
    public Boolean delete(String id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found: " + id);
        }
        repo.deleteById(id);
        return true;
    }
}
