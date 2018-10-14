package com.fatshaw.learning.refactoring.regex;

import static com.fatshaw.learning.refactoring.regex.Re.any;
import static com.fatshaw.learning.refactoring.regex.Re.either;
import static com.fatshaw.learning.refactoring.regex.Re.literal;
import static com.fatshaw.learning.refactoring.regex.Re.sequence;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class ReTest {

    @Test
    public void LitAshouldEqualA() {
        assertThat(literal("A").match("A"), is(Boolean.TRUE));
    }

    @Test
    public void LitBshouldNotEqualA() {
        assertThat(literal("B").match("A"), is(Boolean.FALSE));
    }

    @Test
    public void LitAAshouldEqualAA() {
        assertThat(literal("AA").match("AA"), is(Boolean.TRUE));
    }

    @Test
    public void LitBAshouldNotEqualAA() {
        assertThat(literal("BA").match("AA"), is(Boolean.FALSE));
    }

    @Test
    public void SequenceAABBshouldEqualAABB() {
        assertThat(sequence(literal("AA"), literal("BB")).match("AABB"), is(Boolean.TRUE));
    }


    @Test
    public void SequenceAABBCshouldNotEqualAABB() {
        assertThat(sequence(literal("AA"), literal("BBC")).match("AABB"), is(Boolean.FALSE));
    }

    @Test
    public void EitherAABBshouldEqualAA() {
        assertThat(either(literal("AA"), literal("BBC")).match("AA"), is(Boolean.TRUE));
    }

    @Test
    public void AnyshouldEqualAA() {
        assertThat(any().match("AA"), is(Boolean.TRUE));
    }

}