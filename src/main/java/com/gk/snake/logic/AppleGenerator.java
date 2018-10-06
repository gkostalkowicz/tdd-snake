package com.gk.snake.logic;

public class AppleGenerator {

    private final PositionGenerator positionGenerator;

    public AppleGenerator(PositionGenerator positionGenerator) {
        this.positionGenerator = positionGenerator;
    }

    public GameState calculateNextState(GameState state) {
        if (state.getApplePosition() != null) {
            return state;
        } else {
            return new GameState(state.getSnake(), positionGenerator.generatePosition());
        }
    }
}
