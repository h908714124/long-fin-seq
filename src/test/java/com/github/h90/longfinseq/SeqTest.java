package com.github.h90.longfinseq;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SeqTest {

    private String expand(String s) {
        StringBuilder result = new StringBuilder();
        char[] chars = s.toCharArray();
        int i = 0;
        boolean braceAllowed = false;
        while (i < chars.length) {
            char c = chars[i];
            if (c == '}') {
                throw new IllegalArgumentException("unmatched closing bracket");
            }
            if (c == '{') {
                int k = i;
                if (!braceAllowed) {
                    throw new IllegalArgumentException("floating bracket");
                }
                braceAllowed = false;
                i++;
                StringBuilder sb = new StringBuilder();
                while (chars[i] != '}') {
                    sb.append(chars[i]);
                    i++;
                }
                int n = Integer.parseInt(sb.toString());
                for (int j = 0; j < n; j++) {
                    result.append(chars[k - 1]);
                }
            } else {
                braceAllowed = true;
                if (i == chars.length - 1 || chars[i + 1] != '{') {
                    result.append(c);
                }
            }
            i++;
        }
        return result.toString();
    }

    private Seq seq(String s) {
        char[] result = new char[s.length()];
        char[] source = s.toCharArray();
        System.arraycopy(source, 0, result, 0, source.length);
        return Seq.create(result);
    }

    @Test
    void isSubseq() {
        assertTrue(seq("123").isSubseq(seq("112233")));
        assertFalse(seq("123").isSubseq(seq("332211")));
        assertTrue(seq("123").isSubseq(seq("11221133")));
        assertTrue(seq("1").isSubseq(seq("332211")));
        assertTrue(seq("").isSubseq(seq("332211")));
        assertTrue(seq("").isSubseq(seq("")));
        assertTrue(seq("11233").isSubseq(seq("332113221313")));
        assertFalse(seq("332211").isSubseq(seq("")));
        assertFalse(seq("332211").isSubseq(seq("1")));
    }

    @Test
    void star() {
        assertTrue(seq("12221111111").isStar());
        assertFalse(seq("122211111111").isStar());
        assertFalse(seq("122211111112").isStar());
    }

    @Test
    void expand() {
        assertEquals("1112", expand("1{3}2"));
        assertEquals("3333333333333333333311333333333333", expand("3{20}1{2}3{12}"));
    }

    @Test
    void special() {
        String specialSequence = expand("12{2}131{7}3{2}1313{8}13{5}13{20}1{2}3{53}13{108}");
        assertEquals(216, specialSequence.length());
        assertTrue(seq(specialSequence).isSpecial());
    }
}