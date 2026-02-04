package com.onekg.quotegenerator.util;

public class QuoteUtils {

    private static final String[][] motivationalWords = {
            {"Success", "Dream", "Goal", "Aspiration", "Hope", "Faith", "Desire", "Ambition", "Achievement", "Vision"},
            {"comes", "is achieved", "is born", "is created", "is formed", "emerges", "is built", "grows", "develops"},
            {"through hard work", "with persistence", "through belief in yourself", "by constant development", "through overcoming obstacles", "with dedication", "through determination", "by taking action"},
            {"and knows no bounds", "and inspires others", "and changes the world", "and opens new horizons", "and makes the impossible possible", "and transforms lives", "and creates opportunities"}
    };

    private static final String[][] wisdomWords = {
            {"True wisdom", "Real knowledge", "Deep understanding", "Life experience", "Inner strength", "Genuine insight"},
            {"lies in", "manifests through", "is expressed in", "consists of", "is achieved through", "comes from"},
            {"the ability to listen", "the capacity to accept", "the willingness to learn", "the desire to understand", "the pursuit of balance", "the courage to change"},
            {"and find harmony in simplicity", "and see beauty in the ordinary", "and cherish every moment", "and accept what cannot be changed", "and embrace the present"}
    };

    private static final String[][] loveWords = {
            {"True love", "Genuine affection", "Deep feeling", "Real connection", "Heartfelt warmth", "Sincere devotion"},
            {"requires no proof", "knows no boundaries", "seeks no gain", "fears no difficulty", "never fades with time", "asks for nothing in return"},
            {"it brings joy", "it creates happiness", "it inspires greatness", "it makes us stronger", "it fills life with meaning", "it heals the soul"},
            {"and creates miracles every day", "and turns ordinary into magical", "and connects hearts forever", "and lights the way forward"}
    };

    private static final String[][] lifeWords = {
            {"Life", "Every day", "Our journey", "Existence", "Being", "This moment"},
            {"is a priceless gift", "is an amazing adventure", "is a series of opportunities", "is a precious instant", "is a great journey", "is a beautiful mystery"},
            {"that teaches us", "that gives experience", "that opens perspectives", "that shapes character", "that reveals potential", "that shows true values"},
            {"and makes us who we are", "and fills every second with meaning", "and reveals what truly matters", "and brings endless possibilities"}
    };

    private static final String[][] successWords = {
            {"True success", "Real achievement", "Genuine victory", "Actual progress", "Meaningful result", "Lasting accomplishment"},
            {"is measured not by money", "is defined not by status", "is valued not by fame", "depends not on recognition", "is shown not through wealth"},
            {"but by obstacles overcome", "but by perseverance on the path", "but by staying true to principles", "but by positive impact on others", "but by lives touched"},
            {"and the legacy left behind", "and inspiration for future generations", "and creating a better world", "and making a difference"}
    };

    public static String[][] getWordsByCategory(String category) {
        return switch (category.toLowerCase()) {
            case "motivational" -> motivationalWords;
            case "wisdom" -> wisdomWords;
            case "love" -> loveWords;
            case "life" -> lifeWords;

            default -> successWords;
        };
    }
}
