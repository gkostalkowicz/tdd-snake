package com.gk.snake;

import com.googlecode.lanterna.input.KeyStroke;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class GameLogicProcessor {

    // TODO clean up

    private final SnakeDirectionCalculator directionCalculator;
    private final SnakePositionCalculator positionCalculator;

    // TODO remove
    private final int boardWidth;
    private final int boardHeight;

    @Getter
    private GameState state;

    public GameLogicProcessor(SnakeDirectionCalculator directionCalculator, SnakePositionCalculator positionCalculator,
                              int boardWidth, int boardHeight) {
        this(directionCalculator, positionCalculator, boardWidth, boardHeight, getInitialState(boardWidth, boardHeight));
    }

    public void processNextFrame(KeyStroke keyStroke) {

        Direction newDirection = directionCalculator.getNewDirection(state, keyStroke);
        List<XY> newBody = positionCalculator.moveSnakeByOneStep(newDirection, state.getSnake().getBody());
        state = new GameState(new Snake(newBody, newDirection));
    }

    private static GameState getInitialState(int boardWidth, int boardHeight) {
        ArrayList<XY> snakeBody = new ArrayList<>();
        int headX = (boardWidth - 1) / 2;
        int headY = (boardHeight - 1) / 2;
        for (int i = 0; i < 5; i++) {
            snakeBody.add(new XY(headX + i, headY));
        }
        Snake snake = new Snake(snakeBody, Direction.LEFT);
        return new GameState(snake);
    }
}
