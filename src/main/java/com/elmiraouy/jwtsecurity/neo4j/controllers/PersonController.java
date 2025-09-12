package com.elmiraouy.jwtsecurity.neo4j.controllers;

import com.elmiraouy.jwtsecurity.neo4j.services.PersonService;
import com.elmiraouy.jwtsecurity.neo4j.entities.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonService service;
    public PersonController(PersonService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Person> create(@RequestParam String name) {
        return
                new ResponseEntity<Person>(service.createActor(name),HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Person> search(@RequestParam String q) {
        return
                new ResponseEntity<>(service.search(q), HttpStatus.OK);
    }
}
