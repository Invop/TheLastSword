package com.lastsword.utilities;

public enum Letter {
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