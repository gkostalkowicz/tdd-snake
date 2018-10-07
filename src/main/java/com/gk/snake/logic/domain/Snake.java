package com.gk.snake.logic.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Snake {

    private final List<XY> body;
    private final Direction direction;

    public Snake(List<XY> body, Direction direction) {
        this.body = Collections.unmodifiableList(new ArrayList<>(body));
        this.direction = direction;
    }
}
