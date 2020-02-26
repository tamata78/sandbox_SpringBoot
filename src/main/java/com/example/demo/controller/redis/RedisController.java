package com.example.demo.controller.redis;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@RestController
@RequestMapping(value = "/SampleRedis")
public class RedisController {

	@Autowired
    private StringRedisTemplate redisTemplate;

	// build.gradleへ以下を追加
	// compile ('org.springframework.boot:spring-boot-starter-redis:1.4.7.RELEASE')

	// application.propertiesへ以下を追加
	//server.port=8088
	//spring.redis.database=0
	//spring.redis.host=localhost
	//spring.redis.port=6379
	//spring.redis.timeout=200

	@PostMapping
    public void post(@RequestBody RedisSampleData redisSampleData) {
		// 値設定
        redisTemplate.delete("redis-tutorial:string");
        redisTemplate.opsForValue()
                .set("redis-tutorial:string", redisSampleData.getString());

        // 配列設定
        redisTemplate.delete("redis-tutorial:list");
        redisTemplate.opsForList()
                .rightPushAll("redis-tutorial:list",
                        redisSampleData.getList().toArray(new String[0]));

        // マップ設定
        redisTemplate.delete("redis-tutorial:map");
        redisTemplate.opsForHash()
                .putAll("redis-tutorial:map", redisSampleData.getMap());
    }

    @GetMapping
    public RedisSampleData get() {
    	// 文字列設定
    	RedisSampleData redisSampleData = new RedisSampleData();
        redisSampleData.setString(
                redisTemplate.opsForValue()
                        .get("redis-tutorial:string")
        );

    	// リスト設定(値範囲)
        redisSampleData.setList(
                redisTemplate.opsForList()
                        .range("redis-tutorial:list", 0, -1)
        );

    	// マップ設定
        redisSampleData.setMap(
                redisTemplate.<String, String>opsForHash()
                        .entries("redis-tutorial:map")
        );
        return redisSampleData;
    }

    @Data
    @Getter
    @Setter
    class RedisSampleData {

        private String string;

        private List<String> list;

        private Map<String, String> map;

    }
}
