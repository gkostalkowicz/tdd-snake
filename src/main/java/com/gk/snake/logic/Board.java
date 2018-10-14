package com.gk.snake.logic;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.GameStatus;
import com.gk.snake.logic.domain.Snake;
import com.gk.snake.logic.rules.CrashedIntoWallCheck;
import com.gk.snake.logic.rules.PositionGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Board {

    private final CrashedIntoWallCheck crashedIntoWallCheck;
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

        if (crashedIntoWallCheck.hasSnakeCrashedIntoWall(state.getSnake().getBody())) {
            state = state.withGameStatus(GameStatus.GAME_OVER);
        }
    }
}
