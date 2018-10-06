package com.gk.snake;

import com.googlecode.lanterna.input.KeyType;

import java.util.HashMap;
import java.util.Map;

public enum KeyStroke {

    LEFT_ARROW, RIGHT_ARROW, UP_ARROW, DOWN_ARROW;

    private static Map<KeyType, KeyStroke> KEY_TYPE_TO_KEY_STROKE = new HashMap<>();
    static {
        KEY_TYPE_TO_KEY_STROKE.put(KeyType.ArrowLeft, LEFT_ARROW);
        KEY_TYPE_TO_KEY_STROKE.put(KeyType.ArrowRight, RIGHT_ARROW);
        KEY_TYPE_TO_KEY_STROKE.put(KeyType.ArrowUp, UP_ARROW);
        KEY_TYPE_TO_KEY_STROKE.put(KeyType.ArrowDown, DOWN_ARROW);
    }

    public static KeyStroke of(com.googlecode.lanterna.input.KeyStroke keyStroke) {
        if (keyStroke.getKeyType() == null) {
            return null;
        } else {
            return KEY_TYPE_TO_KEY_STROKE.get(keyStroke.getKeyType());
        }
    }
}
