package com.myproject.quoteapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.quoteapi.ratelimiter.RateLimiterServiceClass;
import com.myproject.quoteapi.service.QuoteService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/quotes")
@Slf4j
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private RateLimiterServiceClass rateLimiter;
    
    
   /* @GetMapping("/random")
    public ResponseEntity<String> getQuote(HttpServletRequest request) {
    	log.info("Received request is : {}",request);
    	
        if (!rateLimiter.isAllowed(request)) {
            return ResponseEntity.status(429).body("Too many requests, try after a minute");
        }

        String quote=quoteService.getRandomQuote();
        
       return ResponseEntity.ok(quote);
    }*/
    
    
    @GetMapping("/random")
    public ResponseEntity<Map<String, Object>> getQuotes(HttpServletRequest request) {
        if (!rateLimiter.isAllowed(request)) {
            return ResponseEntity.status(429).body(Map.of("error", "Too many requests, try after a minute"));
        }

        List<String> quotes = quoteService.getMultipleQuotes(5);  
        Map<String, Object> response = Map.of(
            "quotes", quotes
           // "count", quotes.size()
        );

        return ResponseEntity.ok(response);
    }

}
