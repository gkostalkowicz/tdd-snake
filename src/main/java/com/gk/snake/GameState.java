package com.gk.snake;

public class GameState {

    private final Snake snake;

    public GameState(Snake snake) {
        this.snake = snake;
    }

    public Snake getSnake() {
        return snake;
    }
}
