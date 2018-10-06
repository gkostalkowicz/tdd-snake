package com.gk.snake.logic.rules;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.domain.Direction;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.Snake;
import com.gk.snake.logic.domain.XY;

import java.util.List;

public class SnakePositionCalculator implements GameRule {

    @Override
    public GameState calculateNextState(GameState state, KeyStroke keyStroke) {
        Snake snake = new Snake(getNewPosition(state.getSnake()), state.getSnake().getDirection());
        return new GameState(snake, state.getApplePosition());
    }

    private List<XY> getNewPosition(Snake snake) {

        Direction direction = snake.getDirection();
        List<XY> body = snake.getBody();

        // remove old tail
        body.remove(body.size() - 1);

        // add new head
        body.add(0, new XY(body.get(0), direction.getXDelta(), direction.getYDelta()));

        return body;
    }
}