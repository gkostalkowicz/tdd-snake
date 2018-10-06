package com.gk.snake.logic;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.rules.GameRule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class GameLogicProcessor {

    private final List<GameRule> gameRules;

    @Getter
    private GameState state;

    public void processNextFrame(KeyStroke keyStroke) {
        for (GameRule gameRule : gameRules) {
            state = gameRule.calculateNextState(state, keyStroke);
        }
    }
}
