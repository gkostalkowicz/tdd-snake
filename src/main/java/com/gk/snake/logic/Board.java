package com.gk.snake.logic;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.GameStatus;
import com.gk.snake.logic.domain.snake.Snake;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Board {

    private final SnakeCrashIntoWallCheck snakeCrashIntoWallCheck;
    private final PositionGenerator positionGenerator;

    // TODO remove GameState
    @Getter
    private GameState state;

    public void processNextFrame(KeyStroke keyStroke) {
        // TODO return playing/game over flag

        if (state.getApplePosition() == null) {
            state = state.withApplePosition(positionGenerator.generatePosition());
        }

        Snake.UpdateResult updateResult = state.getSnake().updateForNextFrame(keyStroke, state.getApplePosition());
        if (updateResult.isAppleEaten()) {
            state = state.withApplePosition(null);
        }
        if (updateResult.isCrashedIntoItself()) {
            state = state.withGameStatus(GameStatus.GAME_OVER);
        }

        if (snakeCrashIntoWallCheck.hasSnakeCrashedIntoWall(state.getSnake().getBody())) {
            state = state.withGameStatus(GameStatus.GAME_OVER);
        }
    }
}
