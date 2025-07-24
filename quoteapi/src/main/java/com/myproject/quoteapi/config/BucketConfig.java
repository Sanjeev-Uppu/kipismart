package com.myproject.quoteapi.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BucketConfig {
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
    public Bucket resolveBucket(String ipAddress) {
        return cache.computeIfAbsent(ipAddress, this::newBucket);
    }
    private Bucket newBucket(String key) {
         Refill refill = Refill.greedy(5,Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(5,refill);
        return Bucket.builder().addLimit(limit).build();
    }
}
