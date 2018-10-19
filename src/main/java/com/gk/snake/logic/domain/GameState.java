package com.gk.snake.logic.domain;

import com.gk.snake.logic.domain.snake.Snake;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GameState {

    // TODO make GameState mutable

    private final Snake snake; // caution - this class is mutable!
    private final XY applePosition;
    private final GameStatus gameStatus;

    public GameState(Snake snake, XY applePosition) {
        this(snake, applePosition, GameStatus.PLAYING);
    }

    public GameState withApplePosition(XY applePosition) {
        return new GameState(snake, applePosition, gameStatus);
    }

    public GameState withGameStatus(GameStatus gameStatus) {
        return new GameState(snake, applePosition, gameStatus);
    }
}
