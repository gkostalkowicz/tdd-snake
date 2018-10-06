package com.gk.snake.logic.rules;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.domain.GameState;

public interface GameRule {
    GameState calculateNextState(GameState state, KeyStroke keyStroke);
}
