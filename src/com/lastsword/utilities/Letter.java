package com.lastsword.utilities;

/**
 * The enum Letter.
 */
public enum Letter {
    /**
     * A letter.
     */
    A(23),
    /**
     * S letter.
     */
    S(24),
    /**
     * D letter.
     */
    D(25),
    /**
     * F letter.
     */
    F(26),
    /**
     * G letter.
     */
    G(27),
    /**
     * H letter.
     */
    H(28),
    /**
     * J letter.
     */
    J(29),
    /**
     * K letter.
     */
    K(30),
    /**
     * L letter.
     */
    L(31),
    /**
     * R letter.
     */
    R(16),
    /**
     * T letter.
     */
    T(17);
    private final int numericValue;

    Letter(int numericValue) {
        this.numericValue = numericValue;
    }

    /**
     * Gets numeric value.
     *
     * @return the numeric value
     */
    public int getNumericValue() {
        return numericValue;
    }

    /**
     * Gets character.
     *
     * @return the character
     */
    public char getCharacter() {
        return this.name().charAt(0);
    }
}