package com.gk.snake.logic.domain;

import lombok.Data;

@Data
public class GameState {

    private final Snake snake;

    private final XY applePosition;
}
