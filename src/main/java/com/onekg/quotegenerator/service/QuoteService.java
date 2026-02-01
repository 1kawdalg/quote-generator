package com.onekg.quotegenerator.service;

import com.onekg.quotegenerator.dto.QuoteDto;
import com.onekg.quotegenerator.model.Quote;
import com.onekg.quotegenerator.model.User;
import com.onekg.quotegenerator.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final QuoteGeneratorService quoteGeneratorService;

    public QuoteDto generateAndSaveQuote(User user, String category) {
        if (!quoteGeneratorService.categoryExists(category)) {
            throw new IllegalArgumentException("Category doesn't exist: " + category);
        }

        String quoteText = quoteGeneratorService.generateQuote(category);

        Quote quote = new Quote();
        quote.setText(quoteText);
        quote.setCategory(category);
        quote.setUser(user);

        Quote savedQuote = quoteRepository.save(quote);

        return convertToDto(savedQuote);
    }

    @Transactional(readOnly = true)
    public List<QuoteDto> getUserQuotes(User user) {
        List<Quote> quotes = quoteRepository.findByUserOrderByCreatedAtDesc(user);

        return quotes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<QuoteDto> getUserQuotesByCategory(User user, String category) {
        List<Quote> quotes = quoteRepository.findByUserAndCategory(user, category);

        return quotes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public long getUserQuotesCount(User user) {
        return quoteRepository.countByUser(user);
    }

    @Transactional(readOnly = true)
    public long getUserQuotesCountByCategory(User user, String category) {
        return quoteRepository.countByUserAndCategory(user, category);
    }

    public void deleteQuote(Long quoteId, User user) {
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new IllegalArgumentException("Quote not found: id " + quoteId));

        if (!quote.getUser().getId().equals(user.getId()))
            throw new IllegalArgumentException(
                    "You don't have sufficient permissions to remove this quote");

        quoteRepository.delete(quote);
    }

    public String[] getAvailableCategories() {
        return quoteGeneratorService.getAvailableCategories();
    }

    private QuoteDto convertToDto(Quote quote) {
        QuoteDto dto = new QuoteDto();

        dto.setId(quote.getId());
        dto.setText(quote.getText());
        dto.setCategory(quote.getCategory());

        dto.setCreatedAt(quote.getCreatedAt());
        dto.setUsername(quote.getUser().getUsername());

        return dto;
    }

}
