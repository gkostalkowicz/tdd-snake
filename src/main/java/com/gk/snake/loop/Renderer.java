package com.gk.snake.loop;

import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.XY;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class Renderer {

    private static final TextCharacter APPLE_CHARACTER = new TextCharacter('@', TextColor.ANSI.RED,
            TextColor.ANSI.DEFAULT);
    private static final TextCharacter SNAKE_SEGMENT_CHARACTER = new TextCharacter('#', TextColor.ANSI.GREEN,
            TextColor.ANSI.DEFAULT);

    private final Screen screen;

    public void render(GameState gameState) throws IOException {
        screen.clear();

        XY applePosition = gameState.getApplePosition();
        if (applePosition != null) {
            screen.setCharacter(applePosition.getX(), applePosition.getY(), APPLE_CHARACTER);
        }

        gameState.getSnake().getBody()
                .forEach(bodyPart -> screen.setCharacter(bodyPart.getX(), bodyPart.getY(), SNAKE_SEGMENT_CHARACTER));

        screen.refresh();
    }
}
