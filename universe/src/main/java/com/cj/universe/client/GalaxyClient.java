package com.cj.universe.client;

import com.cj.universe.client.cache.CacheService;
import com.cj.universe.client.dto.Galaxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@Service
public class GalaxyClient {

    private final CacheService cacheService;

    @Autowired
    public GalaxyClient(@Qualifier("galaxyCache") CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public Flux<Galaxy> getAllGalaxies() {
        var url = UriComponentsBuilder.fromHttpUrl("http://localhost:9092/api/v1")
                .path("/galaxies")
                .toUriString();

        return WebClient.builder()
                .build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(Galaxy.class);
    }

    public Mono<Galaxy> getGalaxyById(@NotNull Integer id) {
        var url = UriComponentsBuilder.fromHttpUrl("http://localhost:9092/api/v1")
                .path("/galaxies/")
                .path(id.toString())
                .toUriString();

        Mono<Galaxy> galaxyService = WebClient.builder()
                .build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(Galaxy.class)
                .doOnSuccess(cacheService::put);

        return cacheService.get(id).switchIfEmpty(galaxyService);
    }

}
