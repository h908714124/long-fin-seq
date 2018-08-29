package com.github.h90.longfinseq;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SeqTest {

    private Seq seq(String s) {
        int[] result = new int[s.length()];
        byte[] bytes = s.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            byte c = bytes[i];
            result[i] = c;
        }
        return Seq.create(result);
    }

    @Test
    void isSubseq() {
        assertTrue(seq("123").isSubseq(seq("112233")));
        assertTrue(seq("1").isSubseq(seq("332211")));
        assertTrue(seq("").isSubseq(seq("332211")));
        assertTrue(seq("11233").isSubseq(seq("332113221313")));
        assertFalse(seq("332211").isSubseq(seq("")));
        assertFalse(seq("332211").isSubseq(seq("1")));
    }

    @Test
    void slice() {
        assertTrue(seq("123").isSubseq(seq("112233")));
        assertTrue(seq("1").isSubseq(seq("332211")));
        assertTrue(seq("").isSubseq(seq("332211")));
        assertTrue(seq("11233").isSubseq(seq("332113221313")));
        assertFalse(seq("332211").isSubseq(seq("")));
        assertFalse(seq("332211").isSubseq(seq("1")));
    }
}