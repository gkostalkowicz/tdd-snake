package com.gk.snake.logic.rules;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.domain.GameState;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GenerateAppleRule implements GameRule {

    private final PositionGenerator positionGenerator;

    @Override
    public GameState calculateNextState(GameState state, KeyStroke keyStroke) {
        if (state.getApplePosition() != null) {
            return state;
        } else {
            return new GameState(state.getSnake(), positionGenerator.generatePosition());
        }
    }
}
