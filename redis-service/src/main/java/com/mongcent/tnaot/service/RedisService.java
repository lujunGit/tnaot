package com.mongcent.tnaot.service;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

public interface RedisService {

    boolean set(final String key, final String value);

    boolean set(final String key, final String value, final Long expire);

    String get(final String key);

    boolean expire(final String key, long expire);

    <T> boolean setList(String key, List<T> list);

    <T> List<T> getList(String key, Class<T> clz);

    long lpush(final String key, Object obj);

    long rpush(final String key, Object obj);

    String lpop(final String key);

    boolean existKey(final String key);

    Long getExpireTime(final String key);

    RedisTemplate getRedisTemplate();
}
