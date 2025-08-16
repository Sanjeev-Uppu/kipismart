package com.myproject.quoteapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.quoteapi.service.QuoteService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/admin")
@Slf4j
public class AdminController {

    @Autowired
    private QuoteService quoteService;

    @PostMapping("/add")
    public ResponseEntity<String> addQuote(@RequestBody String quote) {
        quoteService.addQuote(quote);
        return ResponseEntity.ok("Quote added successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteQuote(@RequestBody String quote) {
        boolean removed = quoteService.deleteQuote(quote);
        if (removed) {
            return ResponseEntity.ok("Quote deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Quote not found");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllQuotes() {
        return ResponseEntity.ok(quoteService.getAllQuotes());
    }
}
