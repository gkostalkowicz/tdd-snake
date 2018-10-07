package com.gk.snake.logic.rules;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.GameStatus;
import com.gk.snake.logic.domain.XY;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CrashedIntoWallRule implements GameRule {

    private final int boardWidth;
    private final int boardHeight;

    @Override
    public GameState calculateNextState(GameState state, KeyStroke keyStroke) {
        GameStatus gameStatus = snakeCrashedIntoWall(state.getSnake().getBody())
                ? GameStatus.GAME_OVER : GameStatus.PLAYING;
        return new GameState(state.getSnake(), state.getApplePosition(), gameStatus);
    }

    private boolean snakeCrashedIntoWall(List<XY> body) {
        return body.stream()
                .anyMatch(this::outOfBoard);
    }

    private boolean outOfBoard(XY xy) {
        return xy.getX() < 0 || xy.getX() >= boardWidth
                || xy.getY() < 0 || xy.getY() >= boardHeight;
    }
}
