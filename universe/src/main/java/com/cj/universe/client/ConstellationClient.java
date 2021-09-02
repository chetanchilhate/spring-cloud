package com.cj.universe.client;

import com.cj.universe.client.cache.CacheService;
import com.cj.universe.client.dto.Constellation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ConstellationClient {

    private final CacheService cacheService;

    @Autowired
    public ConstellationClient(@Qualifier("constellationCache") CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public List<Constellation> getAllConstellations() {
        var url = UriComponentsBuilder.fromHttpUrl("http://localhost:9094/api/v1")
                .path("/constellations")
                .toUriString();

        return WebClient.builder()
                .build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(Constellation.class)
                .collectList()
                .blockOptional()
                .orElse(List.of());
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
                .doOnSuccess(val -> cacheService.put(id, val));

        return cacheService.get(id).switchIfEmpty(constellationService);
    }

}
