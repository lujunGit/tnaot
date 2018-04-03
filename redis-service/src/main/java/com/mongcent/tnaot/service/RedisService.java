package com.mongcent.tnaot.service;

import java.util.List;

public interface RedisService {

    boolean set(final String key, final String value);

    String get(final String key);

    boolean expire(final String key, long expire);

    <T> boolean setList(String key, List<T> list);

    <T> List<T> getList(String key, Class<T> clz);

    long lpush(final String key, Object obj);

    long rpush(final String key, Object obj);

    String lpop(final String key);
}
