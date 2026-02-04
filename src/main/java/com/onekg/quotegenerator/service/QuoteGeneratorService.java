package com.onekg.quotegenerator.service;

import com.onekg.quotegenerator.util.QuoteUtils;
import com.onekg.quotegenerator.util.StringListUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;

@Service
public class QuoteGeneratorService {

    private final Random random = new Random();

    public String generateQuote(String category) {
        String[][] wordArray = QuoteUtils.getWordsByCategory(category);
        StringBuilder quote = new StringBuilder();

        for (int i = 0; i < wordArray.length; i++) {
            int randomIndex = random.nextInt(wordArray[i].length);
            quote.append(wordArray[i][randomIndex]);

            if (i < wordArray.length - 1) {
                quote.append(" ");
            }
        }

        return quote.toString();
    }

    public String[] getAvailableCategories() {
        return new String[] {"Motivational", "Wisdom", "Love", "Life", "Success"};
    }

    public boolean categoryExists(String category) {
        String[] categories = StringListUtils.makeStringsLower(getAvailableCategories());

        return Arrays.asList(categories)
                .contains(category.toLowerCase());
    }

}
