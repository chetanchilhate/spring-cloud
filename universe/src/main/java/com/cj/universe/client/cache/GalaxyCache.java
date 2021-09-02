package com.cj.universe.client.cache;

import com.cj.universe.client.dto.Galaxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

@Component("galaxyCache")
public class GalaxyCache implements CacheService<Galaxy, Integer>{

    private final RedisCacheManager redisCacheManager;

    @Autowired
    public GalaxyCache(RedisCacheManager redisCacheManager) {
        this.redisCacheManager = redisCacheManager;
    }

    @Override
    public Mono<Galaxy> get(Integer id) {
        Cache galaxyCahce = redisCacheManager.getCache("galaxies");
        Optional<Galaxy> galaxy = Optional.empty();

        if(Objects.nonNull(galaxyCahce)) {
            galaxy = Optional.ofNullable(galaxyCahce.get(id, Galaxy.class));
        }

        if(galaxy.isPresent()) {
            return Mono.just(galaxy.get());
        }

        return Mono.empty();
    }

    @Override
    public void put(Integer id, Galaxy galaxy) {
        redisCacheManager.getCache("galaxies").put(id, galaxy);
    }

}
