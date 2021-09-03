package com.cj.universe.client;

import com.cj.universe.client.cache.CacheService;
import com.cj.universe.client.dto.Constellation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@Service
public class ConstellationClient {

    private final CacheService cacheService;

    @Autowired
    public ConstellationClient(@Qualifier("constellationCache") CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public Flux<Constellation> getAllConstellations() {
        var url = UriComponentsBuilder.fromHttpUrl("http://localhost:9094/api/v1")
                .path("/constellations")
                .toUriString();

        return WebClient.builder()
                .build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(Constellation.class);
    }

    public Mono<Constellation> getConstellationById(@NotNull Integer id) {
        var url = UriComponentsBuilder.fromHttpUrl("http://localhost:9094/api/v1")
                .path("/constellations/")
                .path(id.toString())
                .toUriString();

        Mono<Constellation> constellationService = WebClient.builder()
                .build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(Constellation.class)
                .doOnSuccess(cacheService::put);

        return cacheService.get(id).switchIfEmpty(constellationService);
    }

}
