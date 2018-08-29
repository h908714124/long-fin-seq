package com.github.h90.longfinseq;

import java.util.Arrays;

class Seq {

    private char[] symbols;

    private Seq(char[] symbols) {
        this.symbols = symbols;
    }

    static Seq create(char[] symbols) {
        for (int i = 0; i < symbols.length; i++) {
            char symbol = symbols[i];
            if (symbol != '1' && symbol != '2' && symbol != '3') {
                throw new IllegalArgumentException("only 3 symbols allowed");
            }
        }
        return new Seq(symbols);
    }

    boolean isSubseq(Seq other) {
        return isSubseq(this.symbols, 0, symbols.length, other.symbols, 0, other.symbols.length);
    }

    boolean isStar() {
        return isStar(symbols);
    }

    private static boolean isStar(char[] a) {
        int h = a.length / 2;
        for (int j = 1; j < h; j++) {
            for (int i = 0; i < j; i++) {
                if (isSubseq(a, i, i + 2, a, j, j + 2)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isSubseq(char[] a, int a_start, int a_length, char[] b, int b_start, int b_length) {
        if (b_length < a_length) {
            return false;
        }
        int possibleMisses = b_length - a_length;
        int pos_b = b_start;
        int a_end = a_start + a_length;
        int b_end = b_start + b_length;
        for (int i = a_start; i < a_end; i++) {
            int sym = a[i];
            while (sym != b[pos_b++]) {
                if (pos_b == b_end) {
                    return false;
                }
                if (possibleMisses-- == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int symbol : symbols) {
            sb.append(Character.valueOf((char) symbol));
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Seq)) {
            return false;
        }
        return Arrays.equals(symbols, ((Seq) obj).symbols);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(symbols);
    }
}
