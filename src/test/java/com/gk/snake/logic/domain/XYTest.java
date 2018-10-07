package com.gk.snake.logic.domain;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class XYTest {

    @Test
    public void testEquals() {
        assertTrue(new XY(10, 20).equals(new XY(10, 20)));
        assertFalse(new XY(10, 20).equals(new XY(11, 20)));
        assertFalse(new XY(10, 20).equals(new XY(10, 21)));
        assertFalse(new XY(10, 20).equals(new XY(11, 21)));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(new XY(10, 20).equals(null));
    }

    @Test
    public void testEqualsObject() {
        assertFalse(new XY(10, 20).equals(new Object()));
    }

    @Test
    public void hashCodeOfEqualXyInstancesIsTheSame() {
        assertTrue(new XY(10, 20).hashCode() == new XY(10, 20).hashCode());
    }
}