package com.gk.snake.logic;

import com.googlecode.lanterna.input.KeyStroke;

public interface GameRule {
    GameState calculateNextState(GameState state, KeyStroke keyStroke);
}
