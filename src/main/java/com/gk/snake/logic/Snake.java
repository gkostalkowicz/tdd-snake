package com.gk.snake.logic;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private final ArrayList<XY> body;

    @Getter
    private final Direction direction;

    public Snake(List<XY> body, Direction direction) {
        this.body = new ArrayList<>(body);
        this.direction = direction;
    }

    public ArrayList<XY> getBody() {
        return new ArrayList<>(body);
    }
}
