package com.elmiraouy.jwtsecurity.neo4j.controllers;

import com.elmiraouy.jwtsecurity.neo4j.services.MetaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/meta")
public class MetaController {
    private final MetaService service;

    public MetaController(MetaService service) {
        this.service = service;
    }

    @GetMapping("/node-labels")
    public List<String> nodeLabels() {
        return service.getNodeLabels();
    }
    @GetMapping("/relationship-types")
    public List<String> relationshipTypes() {
        return service.getRelationshipTypes();
    }

    @GetMapping("/relationship-types/counts")
    public List<MetaService.RelCount> relationshipTypeCounts() {
        return service.getRelationshipTypeCounts();
    }

    @GetMapping("/relationship-schema")
    public List<MetaService.RelSchemaRow> relationshipSchema() {
        return service.getRelationshipSchema();
    }

    @GetMapping("/relationship-types/keys")
    public List<MetaService.RelKeys> relationshipKeys() {
        return service.getRelationshipKeys();
    }

}
