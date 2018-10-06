package com.gk.snake.logic;

import com.gk.snake.KeyStroke;

public interface GameRule {
    GameState calculateNextState(GameState state, KeyStroke keyStroke);
}
