package com.cj.universe.client;

import com.cj.universe.client.cache.CacheService;
import com.cj.universe.client.dto.Constellation;
import com.cj.universe.client.dto.Galaxy;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.cache.CacheMono;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GalaxyClient {

    private final CacheService cacheService;

    @Autowired
    public GalaxyClient(@Qualifier("galaxyCache") CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public List<Galaxy> getAllGalaxies() {
        var url = UriComponentsBuilder.fromHttpUrl("http://localhost:9092/api/v1")
                .path("/galaxies")
                .toUriString();


        return WebClient.builder()
                .build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(Galaxy.class)
                .collectList()
                .block();
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
                .doOnSuccess(val -> cacheService.put(id,val));

        return cacheService.get(id).switchIfEmpty(galaxyService);
    }

}
