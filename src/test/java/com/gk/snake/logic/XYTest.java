package com.gk.snake.logic;

import com.gk.snake.logic.XY;
import org.junit.Test;

import static org.junit.Assert.*;

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
}