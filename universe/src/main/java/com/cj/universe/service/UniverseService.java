package com.cj.universe.service;

import com.cj.universe.client.ConstellationClient;
import com.cj.universe.client.GalaxyClient;
import com.cj.universe.client.dto.Constellation;
import com.cj.universe.client.dto.Galaxy;
import com.cj.universe.model.Universe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UniverseService {

    private GalaxyClient galaxyClient;
    private ConstellationClient constellationClient;

    @Autowired
    public UniverseService(GalaxyClient galaxyClient, ConstellationClient constellationClient) {
        this.galaxyClient = galaxyClient;
        this.constellationClient = constellationClient;
    }

    public List<Universe> getAllUniverse() {
        var galaxies = galaxyClient.getAllGalaxies();
        var constellations = constellationClient.getAllConstellations();

        return IntStream
                .range(0, constellations.size())
                .mapToObj(i -> new Universe(galaxies.get(i), constellations.get(i)))
                .collect(Collectors.toList());
    }

    public Mono<Universe> getUniverseById(Integer id) {
        Mono<Galaxy> galaxy = galaxyClient.getGalaxyById(id);
        Mono<Constellation> constellation = constellationClient.getConstellationById(id);
        return Mono.zip(galaxy, constellation).map((tuple) -> new Universe(tuple.getT1(), tuple.getT2()));
    }

}
