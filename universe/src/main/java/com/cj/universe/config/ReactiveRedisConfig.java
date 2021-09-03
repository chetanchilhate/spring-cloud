package com.cj.universe.config;

import com.cj.universe.client.dto.Constellation;
import com.cj.universe.client.dto.Galaxy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class ReactiveRedisConfig {

    @Bean("reactiveRedisConnectionFactory")
    public ReactiveRedisConnectionFactory connectionFactory() {
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(2))
                .shutdownTimeout(Duration.ZERO)
                .build();

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379), clientConfig);
    }

    @Bean("galaxyTemplate")
    public ReactiveRedisTemplate<String, Galaxy> galaxyTemplate(
            @Qualifier("reactiveRedisConnectionFactory") ReactiveRedisConnectionFactory factory) {
        return createReactiveRedisTemplate(factory, new Jackson2JsonRedisSerializer<>(Galaxy.class));
    }

    @Bean("constellationTemplate")
    public ReactiveRedisTemplate<String, Constellation> constellationTemplate(
            @Qualifier("reactiveRedisConnectionFactory") ReactiveRedisConnectionFactory factory) {
        return createReactiveRedisTemplate(factory, new Jackson2JsonRedisSerializer<>(Constellation.class));
    }

    private <T> ReactiveRedisTemplate<String, T> createReactiveRedisTemplate(
            ReactiveRedisConnectionFactory factory, Jackson2JsonRedisSerializer<T> valueSerializer) {

        StringRedisSerializer keySerializer = new StringRedisSerializer();
        RedisSerializationContext.RedisSerializationContextBuilder<String, T> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);

        RedisSerializationContext<String, T> context = builder.value(valueSerializer).build();
        return new ReactiveRedisTemplate<>(factory, context);
    }
}
