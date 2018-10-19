package com.gk.snake;

import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.XY;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class Renderer {

    private final Screen screen;

    public void render(GameState gameState) throws IOException {
        screen.clear();

        XY applePosition = gameState.getApplePosition();
        if (applePosition != null) {
            screen.setCharacter(applePosition.getX(), applePosition.getY(), new TextCharacter('*'));
        }

        gameState.getSnake().getBody()
                .forEach(bodyPart -> screen.setCharacter(bodyPart.getX(), bodyPart.getY(), new TextCharacter('#')));

        screen.refresh();
    }
}
