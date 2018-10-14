package com.gk.snake.logic.domain.snake;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.domain.Direction;
import com.gk.snake.logic.domain.XY;
import com.gk.snake.logic.domain.snake.SnakePositionUpdater.SnakePositionUpdate;
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
    private final SnakeCrashIntoItselfCheck snakeCrashIntoItselfCheck;

    public Snake(List<XY> body, Direction direction, SnakeDirectionUpdater snakeDirectionUpdater,
                 SnakePositionUpdater snakePositionUpdater, SnakeCrashIntoItselfCheck snakeCrashIntoItselfCheck) {
        this.body = List.copyOf(body);
        this.direction = direction;
        this.snakeDirectionUpdater = snakeDirectionUpdater;
        this.snakePositionUpdater = snakePositionUpdater;
        this.snakeCrashIntoItselfCheck = snakeCrashIntoItselfCheck;
    }

    public UpdateResult updateForNextFrame(KeyStroke keyStroke, XY applePosition) {
        this.direction = snakeDirectionUpdater.getNextDirection(direction, keyStroke);

        SnakePositionUpdate positionUpdate = snakePositionUpdater.getNextPosition(body, direction, applePosition);
        this.body = positionUpdate.getPosition();
        boolean appleEaten = positionUpdate.isAppleEaten();

        boolean crashedIntoItself = snakeCrashIntoItselfCheck.hasCrashedIntoItself(body);

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
