package com.gk.snake.input;

import com.googlecode.lanterna.input.KeyType;

import java.util.HashMap;
import java.util.Map;

public enum KeyStroke {

    LEFT_ARROW, RIGHT_ARROW, UP_ARROW, DOWN_ARROW, UNKNOWN_KEY, ESCAPE;

    private static Map<KeyType, KeyStroke> KEY_TYPE_TO_KEY_STROKE = new HashMap<>();

    static {
        KEY_TYPE_TO_KEY_STROKE.put(KeyType.ArrowLeft, LEFT_ARROW);
        KEY_TYPE_TO_KEY_STROKE.put(KeyType.ArrowRight, RIGHT_ARROW);
        KEY_TYPE_TO_KEY_STROKE.put(KeyType.ArrowUp, UP_ARROW);
        KEY_TYPE_TO_KEY_STROKE.put(KeyType.ArrowDown, DOWN_ARROW);
        KEY_TYPE_TO_KEY_STROKE.put(KeyType.Escape, ESCAPE);
    }

    public static KeyStroke of(com.googlecode.lanterna.input.KeyStroke keyStroke) {
        if (keyStroke == null) {
            return null;
        } else {
            return KEY_TYPE_TO_KEY_STROKE.getOrDefault(keyStroke.getKeyType(), UNKNOWN_KEY);
        }
    }
}
