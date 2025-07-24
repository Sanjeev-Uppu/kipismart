package com.myproject.quoteapi.ratelimiter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.quoteapi.config.BucketConfig;

import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RateLimiterServiceClass {

    @Autowired
    private BucketConfig bucketConfig;

    public boolean isAllowed(HttpServletRequest request) {
    	
    	log.info("Received request : {}",request);
        String ipAddress=extractClientIP(request);
        log.info("Ip address is : {}",ipAddress);
        Bucket bucket = bucketConfig.resolveBucket(ipAddress);
        return bucket.tryConsume(1);
    }

    private String extractClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
