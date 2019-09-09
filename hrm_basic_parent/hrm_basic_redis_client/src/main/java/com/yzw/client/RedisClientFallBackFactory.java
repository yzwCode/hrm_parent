package com.yzw.client;

import feign.hystrix.FallbackFactory;

public class RedisClientFallBackFactory implements FallbackFactory<RedisClient> {

    @Override
    public RedisClient create(Throwable cause) {
        return new RedisClient() {
            @Override
            public void set(String key, String value) {

            }

            @Override
            public String get(String key) {
                return null;
            }
        };
    }
}
