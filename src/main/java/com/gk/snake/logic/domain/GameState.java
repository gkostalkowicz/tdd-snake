package com.gk.snake.logic.domain;

import com.gk.snake.logic.domain.snake.Snake;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GameState {

    private final Snake snake;
    private final XY applePosition;
    private final GameStatus gameStatus;

    public GameState(Snake snake, XY applePosition) {
        this(snake, applePosition, GameStatus.PLAYING);
    }

    public GameState withSnake(Snake snake) {
        return new GameState(snake, applePosition, gameStatus);
    }

    public GameState withApplePosition(XY applePosition) {
        return new GameState(snake, applePosition, gameStatus);
    }

    public GameState withGameStatus(GameStatus gameStatus) {
        return new GameState(snake, applePosition, gameStatus);
    }
}
