package com.cj.universe.client.cache;

import com.cj.universe.client.dto.Constellation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component("constellationCache")
public class ConstellationCache implements CacheService<Constellation, Integer> {

    private final ReactiveRedisTemplate<String, Constellation> constellationTemplate;

    @Autowired
    public ConstellationCache(@Qualifier("constellationTemplate") ReactiveRedisTemplate<String, Constellation> constellationTemplate) {
        this.constellationTemplate = constellationTemplate;
    }

    @Override
    public Mono<Constellation> get(Integer id) {
        return constellationTemplate.opsForValue().get("constellations::".concat(id.toString()));
    }

    @Override
    public void put(Constellation constellation) {
        Mono<Boolean> result = constellationTemplate.opsForValue().set("constellations::".concat(constellation.getId().toString()),
                constellation, Duration.ofMinutes(5l));
        result.subscribe();
    }

}
