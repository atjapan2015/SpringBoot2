package com.springboot2.business.sample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@RestController
public class RedisController {

    @RequestMapping("/redis")
    public String testRedis() {

        Jedis jedis = new Jedis("192.168.1.171", 6379);
        int i = 0;
        try {
            long start = System.currentTimeMillis();
            while (true) {
                long end = System.currentTimeMillis();
                if (end - start >= 1000) {
                    break;
                }
                i++;
                jedis.set("test" + i, i + "");

            }
        } finally {
            jedis.close();
        }
        return "Operate: " + i + " times every seconds";
    }

    @RequestMapping("/redispool")
    public String testRedisPool() {

        JedisPoolConfig poolCfg = new JedisPoolConfig();
        poolCfg.setMaxIdle(50);
        poolCfg.setMaxTotal(100);
        poolCfg.setMaxWaitMillis(20000);

        JedisPool pool = new JedisPool(poolCfg, "192.168.1.171");

        Jedis jedis = pool.getResource();
        int i = 0;
        try {
            long start = System.currentTimeMillis();
            while (true) {
                long end = System.currentTimeMillis();
                if (end - start >= 1000) {
                    break;
                }
                i++;
                jedis.set("test" + i, i + "");

            }
        } finally {
            jedis.close();
        }
        return "Operate: " + i + " times every seconds";
    }

}
