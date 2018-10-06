package com.gk.snake.logic;

import com.googlecode.lanterna.input.KeyStroke;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class GameLogicProcessor {

    private final List<GameRule> gameRules;

    // TODO remove
    private final int boardWidth;
    private final int boardHeight;

    @Getter
    private GameState state;

    public void processNextFrame(KeyStroke keyStroke) {
        // TODO generate apple
        for (GameRule gameRule : gameRules) {
            state = gameRule.calculateNextState(state, keyStroke);
        }
    }
}
