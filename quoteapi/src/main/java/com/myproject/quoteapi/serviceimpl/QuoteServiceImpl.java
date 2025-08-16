package com.myproject.quoteapi.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.myproject.quoteapi.service.QuoteService;

@Service
public class QuoteServiceImpl implements QuoteService {

    private final List<String> quotes = new CopyOnWriteArrayList<>(List.of(
        "Believe in yourself!",
        "Never give up.",
        "Stay focused and keep going.",
        "Never stop learning.",
        "Success is a journey, not a destination."
    ));

    @Override
    public String getRandomQuote() {
        int randomIndex = ThreadLocalRandom.current().nextInt(quotes.size());
        return quotes.get(randomIndex);
    }

    @Override
    public void addQuote(String quote) {
        String cleaned = quote.replaceAll("[\\r\\n]+", " ").trim();
        quotes.add(cleaned);
    }


    @Override
    public boolean deleteQuote(String quote) {
        String normalizedInput = quote.trim().toLowerCase();

        Optional<String> match = quotes.stream()
            .filter(q -> q.trim().toLowerCase().equals(normalizedInput))
            .findFirst();

        match.ifPresent(quotes::remove);
        return match.isPresent();
    }
    @Override
    public List<String> getAllQuotes() {
        return new ArrayList<>(quotes);
    }
    @Override
    public List<String> getMultipleQuotes(int count) {
        return ThreadLocalRandom.current()
            .ints(0, quotes.size())
            .distinct()
            .limit(count)
            .mapToObj(quotes::get)
            .toList();
    }
}
