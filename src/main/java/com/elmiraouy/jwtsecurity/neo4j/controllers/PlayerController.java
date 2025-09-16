package com.elmiraouy.jwtsecurity.neo4j.controllers;

import com.elmiraouy.jwtsecurity.neo4j.entities.Player;
import com.elmiraouy.jwtsecurity.neo4j.dtos.PlayerInput;
import com.elmiraouy.jwtsecurity.neo4j.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Player> create(@RequestBody PlayerInput in) {
        Player created = service.create(in);
        return new ResponseEntity<>(created,HttpStatus.CREATED);
    }

    @GetMapping
    public List<Player> list() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Player get(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Player>> search(@RequestParam String name) {
        return  new ResponseEntity<>(service.searchByName(name), HttpStatus.OK);
    }
    @GetMapping("/byCoach")
    public ResponseEntity<List<Player>> playersByCoach(@RequestParam String name) {
        return  new ResponseEntity<>(service.playersByCoach( name), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public Player update(@PathVariable String id, @RequestBody PlayerInput in) {
        return service.update(id, in);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }
}
