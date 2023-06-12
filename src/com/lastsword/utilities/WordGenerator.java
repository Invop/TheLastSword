package com.lastsword.utilities;

import java.util.Random;

/**
 * The type Word generator.
 */
public class WordGenerator {
    private static final Random random = new Random();
    private final String word;
    private final int[] letterValues;

    /**
     * Instantiates a new Word generator.
     *
     * @param lenght the lenght
     */
    public WordGenerator(int lenght) {
        word = generateWord(lenght);
        letterValues = convertWordToLetterValues(word);
    }

    private static String generateWord(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char randomLetter = getRandomLetter();
            sb.append(randomLetter);
        }
        return sb.toString();
    }

    private static int[] convertWordToLetterValues(String word) {
        int[] letterValues = new int[word.length()];
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            int letterValue = Letter.valueOf(String.valueOf(letter)).getNumericValue();
            letterValues[i] = letterValue;
        }
        return letterValues;
    }

    private static char getRandomLetter() {
        int randomValue = random.nextInt(Letter.values().length - 2);
        return Letter.values()[randomValue].getCharacter();
    }

    /**
     * Get letter values int [ ].
     *
     * @return the int [ ]
     */
    public int[] getLetterValues() {
        return letterValues;
    }

    /**
     * Gets word.
     *
     * @return the word
     */
    public String getWord() {
        return word;
    }
}
