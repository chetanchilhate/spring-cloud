package com.cj.universe.client.cache;

import com.cj.universe.client.dto.Galaxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Component("galaxyCache")
public class GalaxyCache implements CacheService<Galaxy, Integer>{

    private final ReactiveRedisTemplate<String, Galaxy> galaxyTemplate;

    @Autowired
    public GalaxyCache(@Qualifier("galaxyTemplate") ReactiveRedisTemplate<String, Galaxy> galaxyTemplate) {
        this.galaxyTemplate = galaxyTemplate;
    }

    @Override
    public Mono<Galaxy> get(Integer id) {
        return galaxyTemplate.opsForValue().get("galaxies::".concat(id.toString()));
    }

    @Override
    public void put(Galaxy galaxy) {
        Mono<Boolean> result = galaxyTemplate.opsForValue().set("galaxies::".concat(galaxy.getId().toString()), galaxy, Duration.ofMinutes(5l));
        result.subscribe();
    }

}
