package com.github.h90.longfinseq;

import java.util.Arrays;

class Seq {

    private int start;

    private int length;

    private int[] symbols;

    private Seq(int start, int length, int[] symbols) {
        this.start = start;
        this.length = length;
        this.symbols = symbols;
    }

    static Seq create(int[] symbols) {
        return new Seq(0, symbols.length, symbols);
    }

    boolean isSubseq(Seq other) {
        int[] a = this.symbols;
        int[] b = other.symbols;
        return isSubseq(a, b);
    }

    boolean isStar() {
        return isStar(this.symbols);
    }

    static boolean isStar(int[] a) {
        int h = a.length / 2;
        for (int i = 0; i < h; i++) {
//            new Seq(Arrays.copyOfRange(a, i, 2 * i));
        }
        return false;
    }

    Seq slice(int start, int end) {
        return new Seq(start, end - start, symbols);
    }

    static boolean isSubseq(int[] a, int[] b) {
        int b_length = b.length;
        if (b_length < a.length) {
            return false;
        }
        int possibleMisses = b_length - a.length;
        int pos_b = 0;
        for (int i = 0; i < a.length; i++) {
            int sym = a[i];
            while (sym != b[pos_b++]) {
                if (pos_b == b_length) {
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
        for (int i = start; i < length; i++) {
            int symbol = symbols[i];
            sb.append(Character.valueOf((char) symbol));
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Seq)) {
            return false;
        }
        return ((Seq) obj).isSubseq(this) && isSubseq(((Seq) obj));
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(Arrays.copyOfRange(symbols, start, start + length));
    }
}
