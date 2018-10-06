package com.gk.snake.logic.rules;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.domain.GameState;

public class AppleGenerator implements GameRule {

    private final PositionGenerator positionGenerator;

    public AppleGenerator(PositionGenerator positionGenerator) {
        this.positionGenerator = positionGenerator;
    }

    @Override
    public GameState calculateNextState(GameState state, KeyStroke keyStroke) {
        if (state.getApplePosition() != null) {
            return state;
        } else {
            return new GameState(state.getSnake(), positionGenerator.generatePosition());
        }
    }
}
