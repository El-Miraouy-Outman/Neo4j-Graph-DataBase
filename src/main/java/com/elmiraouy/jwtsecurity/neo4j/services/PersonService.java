package com.elmiraouy.jwtsecurity.neo4j.services;

import com.elmiraouy.jwtsecurity.neo4j.repositories.PersonRepository;
import com.elmiraouy.jwtsecurity.neo4j.entities.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {
    private final PersonRepository repo;

    public PersonService(PersonRepository repo) { this.repo = repo; }

    @Transactional
    public Person createActor(String name) {
        Person p = new Person(name,1L);
        return repo.save(p);
    }

    public Person search(String q) {
        return repo.findByName(q);
    }
}
