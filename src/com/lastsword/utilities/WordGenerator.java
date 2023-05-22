package com.lastsword.utilities;

import java.util.Random;

public class WordGenerator {
    private static final Random random = new Random();
    private String word;
    private int[] letterValues;

    public WordGenerator(int lenght){
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
        int randomValue = random.nextInt(Letter.values().length);
        return Letter.values()[randomValue].getCharacter();
    }

    public int[] getLetterValues() {
        return letterValues;
    }

    private enum Letter {
        A(23),
        S(24),
        D(25),
        F(26),
        G(27),
        H(28),
        J(29),
        K(30),
        L(31);

        private final int numericValue;

        Letter(int numericValue) {
            this.numericValue = numericValue;
        }

        public int getNumericValue() {
            return numericValue;
        }

        public char getCharacter() {
            return this.name().charAt(0);
        }
    }
}
