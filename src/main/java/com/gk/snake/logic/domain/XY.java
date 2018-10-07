package com.gk.snake.logic.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class XY {

    private final int x;
    private final int y;

    public XY(XY xy, int xDelta, int yDelta) {
        this.x = xy.x + xDelta;
        this.y = xy.y + yDelta;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
