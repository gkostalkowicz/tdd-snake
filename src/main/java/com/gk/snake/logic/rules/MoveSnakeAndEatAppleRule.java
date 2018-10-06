package com.gk.snake.logic.rules;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.domain.Direction;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.Snake;
import com.gk.snake.logic.domain.XY;

import java.util.List;

public class MoveSnakeAndEatAppleRule implements GameRule {

    @Override
    public GameState calculateNextState(GameState state, KeyStroke keyStroke) {

        Direction direction = state.getSnake().getDirection();
        List<XY> body = state.getSnake().getBody();

        // add new head
        body.add(0, new XY(body.get(0), direction.getXDelta(), direction.getYDelta()));

        boolean snakeBodyOverlapsWithApple = snakeBodyOverlapsWithApple(body, state.getApplePosition());

        // remove the apple if it has overlap with snake
        XY newApplePosition = snakeBodyOverlapsWithApple ? null : state.getApplePosition();

        // remove old tail
        if (!snakeBodyOverlapsWithApple) {
            body.remove(body.size() - 1);
        }

        return new GameState(new Snake(body, direction), newApplePosition);
    }

    private boolean snakeBodyOverlapsWithApple(List<XY> body, XY applePosition) {
        return body.stream()
                .anyMatch(bodyXy -> bodyXy.equals(applePosition));
    }
}
