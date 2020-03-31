package com.example.demo.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PropertyBean {
	@Value("${spring.redis.retry.limit.count}")
	private int redisRetryLimitCount;

}