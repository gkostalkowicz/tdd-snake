package com.gk.snake.logic.domain.snake;

import com.gk.snake.logic.domain.XY;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CrashedIntoItselfCheck {

    public boolean hasCrashedIntoItself(List<XY> body) {
        Set<XY> xySet = new HashSet<>();
        for (XY xy : body) {
            if (xySet.contains(xy)) {
                return true;
            } else {
                xySet.add(xy);
            }
        }
        return false;
    }
}
