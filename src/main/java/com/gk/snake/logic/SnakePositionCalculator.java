package com.gk.snake.logic;

import java.util.List;

public class SnakePositionCalculator {

    public List<XY> getNewPosition(Direction direction, List<XY> body) {

        // remove old tail
        body.remove(body.size() - 1);

        // add new head
        body.add(0, new XY(body.get(0), direction.getXDelta(), direction.getYDelta()));

        return body;
    }
}
