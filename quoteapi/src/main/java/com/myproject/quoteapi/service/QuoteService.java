package com.myproject.quoteapi.service;

import java.util.List;

public interface QuoteService {
    public String getRandomQuote();
    void addQuote(String quote);
    boolean deleteQuote(String quote);
    List<String> getAllQuotes();
    List<String> getMultipleQuotes(int count);
}
