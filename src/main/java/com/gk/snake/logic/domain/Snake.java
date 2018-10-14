package com.gk.snake.logic.domain;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.SnakeDirectionUpdater;
import com.gk.snake.logic.SnakePositionUpdater;
import com.gk.snake.logic.SnakePositionUpdater.SnakePositionUpdate;
import com.gk.snake.logic.rules.CrashedIntoItselfCheck;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@Getter
public class Snake {

    // TODO make immutable
    private List<XY> body;
    private Direction direction;
    private final SnakeDirectionUpdater snakeDirectionUpdater;
    private final SnakePositionUpdater snakePositionUpdater;
    private final CrashedIntoItselfCheck crashedIntoItselfCheck;

    public Snake(List<XY> body, Direction direction, SnakeDirectionUpdater snakeDirectionUpdater,
                 SnakePositionUpdater snakePositionUpdater, CrashedIntoItselfCheck crashedIntoItselfCheck) {
        this.body = List.copyOf(body);
        this.direction = direction;
        this.snakeDirectionUpdater = snakeDirectionUpdater;
        this.snakePositionUpdater = snakePositionUpdater;
        this.crashedIntoItselfCheck = crashedIntoItselfCheck;
    }

    public UpdateResult updateForNextFrame(KeyStroke keyStroke, XY applePosition) {
        this.direction = snakeDirectionUpdater.getNextDirection(direction, keyStroke);

        SnakePositionUpdate positionUpdate = snakePositionUpdater.getNextPosition(body, direction, applePosition);
        this.body = positionUpdate.getPosition();
        boolean appleEaten = positionUpdate.isAppleEaten();

        boolean crashedIntoItself = crashedIntoItselfCheck.hasCrashedIntoItself(body);

        return new UpdateResult(appleEaten, crashedIntoItself);
    }

    @Value
    public static class UpdateResult {
        private boolean appleEaten;
        private boolean crashedIntoItself;

        public UpdateResult(boolean appleEaten, boolean crashedIntoItself) {
            this.appleEaten = appleEaten;
            this.crashedIntoItself = crashedIntoItself;
        }

        public UpdateResult() {
            this(false, false);
        }
    }
}
