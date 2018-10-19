package com.gk.snake;

import com.googlecode.lanterna.input.KeyType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class KeyStrokeTest {

    @Test
    public void leftArrow() {
        assertEquals(KeyStroke.LEFT_ARROW, keyStrokeOf(KeyType.ArrowLeft));
    }

    @Test
    public void rightArrow() {
        assertEquals(KeyStroke.RIGHT_ARROW, keyStrokeOf(KeyType.ArrowRight));
    }

    @Test
    public void upArrow() {
        assertEquals(KeyStroke.UP_ARROW, keyStrokeOf(KeyType.ArrowUp));
    }

    @Test
    public void downArrow() {
        assertEquals(KeyStroke.DOWN_ARROW, keyStrokeOf(KeyType.ArrowDown));
    }

    @Test
    public void unknownKey() {
        assertNull(keyStrokeOf(KeyType.Enter));
    }

    @Test
    public void noKeyPressed() {
        assertNull(KeyStroke.of(null));
    }

    private KeyStroke keyStrokeOf(KeyType keyType) {
        return KeyStroke.of(new com.googlecode.lanterna.input.KeyStroke(keyType));
    }
}