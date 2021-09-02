package com.cj.universe.client.cache;

import com.cj.universe.client.dto.Constellation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

@Component("constellationCache")
public class ConstellationCache implements CacheService<Constellation, Integer>{

    private final RedisCacheManager redisCacheManager;

    @Autowired
    public ConstellationCache(RedisCacheManager redisCacheManager) {
        this.redisCacheManager = redisCacheManager;
    }

    @Override
    public Mono<Constellation> get(Integer id) {
        Cache constellationCache = redisCacheManager.getCache("constellations");
        Optional<Constellation> constellation = Optional.empty();

        if(Objects.nonNull(constellationCache)) {
            constellation = Optional.ofNullable(constellationCache.get(id, Constellation.class));
        }

        if(constellation.isPresent()) {
            return Mono.just(constellation.get());
        }

        return Mono.empty();
    }

    @Override
    public void put(Integer id, Constellation constellation) {
        redisCacheManager.getCache("constellations").put(id, constellation);
    }

}
