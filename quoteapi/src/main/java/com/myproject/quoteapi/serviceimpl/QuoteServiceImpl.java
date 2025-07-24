package com.myproject.quoteapi.serviceimpl;

import com.myproject.quoteapi.service.QuoteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuoteServiceImpl implements QuoteService {

    private final List<String> quotes = List.of(
            "Believe in yourself!",
            "Never give up.",
            "Stay focused and keep going.",
            "Never stop learning.",
            "Success is a journey, not a destination.",
            "Push yourself, because no one else is going to do it for you.",
            "Success doesn’t come to you, you go to it.",
            "Small steps every day lead to big results.",
            "Don’t watch the clock; do what it does. Keep going.",
            "Great things never come from comfort zones.",
            "Work hard in silence, let your success be your noise.",
            "Dream it. Wish it. Do it."
    );

    @Override
    public String getRandomQuote() {
        int randomIndex = ThreadLocalRandom.current().nextInt(quotes.size());
        return quotes.get(randomIndex);
    }
}
