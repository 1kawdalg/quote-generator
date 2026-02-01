package com.onekg.quotegenerator.controller;

import com.onekg.quotegenerator.dto.QuoteDto;
import com.onekg.quotegenerator.model.User;
import com.onekg.quotegenerator.service.QuoteService;
import com.onekg.quotegenerator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;
    private final UserService userService;

    @GetMapping("/dashboard")
    public String showDashboard(Authentication authentication, Model model) {
        String username = authentication.getName();

        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<QuoteDto> quotes = quoteService.getUserQuotes(user);
        String[] categories = quoteService.getAvailableCategories();

        long quotesCount = quoteService.getUserQuotesCount(user);

        model.addAttribute("username", username);
        model.addAttribute("quotes", quotes);
        model.addAttribute("categories", categories);
        model.addAttribute("quotesCount", quotesCount);

        return "dashboard";
    }

    @PostMapping("/quote/generate")
    public String generateQuote(
            @RequestParam("category") String category,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {

        String username = authentication.getName();

        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        try {
            QuoteDto quote = quoteService.generateAndSaveQuote(user, category);

            redirectAttributes.addFlashAttribute("success",
                    "New quote generated successfully!");

            redirectAttributes.addFlashAttribute("newQuote", quote);

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/dashboard";
    }

    @GetMapping("/quote/category/{category}")
    public String showQuotesByCategory(
            @PathVariable("category") String category,
            Authentication authentication,
            Model model) {

        String username = authentication.getName();

        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<QuoteDto> quotes = quoteService.getUserQuotesByCategory(user, category);
        long count = quoteService.getUserQuotesCountByCategory(user, category);

        model.addAttribute("category", category);
        model.addAttribute("quotes", quotes);
        model.addAttribute("count", count);
        model.addAttribute("username", username);

        return "category-quotes";
    }

    @PostMapping("/quote/delete/{id}")
    public String deleteQuote(
            @PathVariable("id") Long quoteId,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {

        String username = authentication.getName();

        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        try {
            quoteService.deleteQuote(quoteId, user);

            redirectAttributes.addFlashAttribute("success",
                    "Quote deleted successfully!");

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/dashboard";
    }

    @GetMapping("/api/quotes")
    @ResponseBody
    public List<QuoteDto> getUserQuotesJson(Authentication authentication) {
        String username = authentication.getName();

        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return quoteService.getUserQuotes(user);
    }

}
