package com.gk.snake.logic.rules;

import com.gk.snake.logic.domain.XY;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CrashedIntoWallCheck {

    private final int boardWidth;
    private final int boardHeight;

    public boolean hasSnakeCrashedIntoWall(List<XY> body) {
        return body.stream()
                .anyMatch(this::outOfBoard);
    }

    private boolean outOfBoard(XY xy) {
        return xy.getX() < 0 || xy.getX() >= boardWidth
                || xy.getY() < 0 || xy.getY() >= boardHeight;
    }
}
