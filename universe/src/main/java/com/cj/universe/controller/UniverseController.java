package com.cj.universe.controller;

import com.cj.universe.model.Universe;
import com.cj.universe.service.UniverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("api/v1/universes")
public class UniverseController {

    @Autowired
    private final UniverseService universeService;

    @Autowired
    public UniverseController(UniverseService universeService) {
        this.universeService = universeService;
    }

    @GetMapping("")
    public Flux<Universe> getAllUniverse() {
        return universeService.getAllUniverse();
    }

    @GetMapping("/{id}")
    public Mono<Universe> getUniverseById(@PathVariable("id") Integer id) {
        return universeService.getUniverseById(id);
    }

}
