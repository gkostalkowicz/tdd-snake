package com.gk.snake.logic;

import com.googlecode.lanterna.input.KeyStroke;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class GameLogicProcessor {

    private final SnakeDirectionCalculator directionCalculator;
    private final SnakePositionCalculator positionCalculator;

    // TODO remove
    private final int boardWidth;
    private final int boardHeight;

    @Getter
    private GameState state;

    public void processNextFrame(KeyStroke keyStroke) {
        // TODO refactor
        // TODO generate apple
        Direction newDirection = directionCalculator.getNewDirection(state, keyStroke);
        List<XY> newBody = positionCalculator.getNewPosition(newDirection, state.getSnake().getBody());
        state = new GameState(new Snake(newBody, newDirection), state.getApplePosition());
    }
}
