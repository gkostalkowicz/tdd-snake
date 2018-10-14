package com.gk.snake.logic;

import com.gk.snake.logic.domain.XY;

import java.util.Random;

public class PositionGenerator {

    private final int boardWidth;
    private final int boardHeight;
    private final Random random;

    public PositionGenerator(int boardWidth, int boardHeight, Random random) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.random = random;
    }

    public XY generatePosition() {
        return new XY(random.nextInt(boardWidth), random.nextInt(boardHeight));
    }
}
