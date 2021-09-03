package com.cj.universe.client.cache;

import reactor.core.publisher.Mono;

public interface CacheService <T, ID> {
    Mono<T> get(ID id);
    void put(T t);
}
