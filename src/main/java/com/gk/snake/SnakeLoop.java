package com.gk.snake;

import com.gk.snake.logic.GameLogicProcessor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class SnakeLoop {

    private final Screen screen;

    private final Timer timer;

    private final GameLogicProcessor gameLogicProcessor;

    public void start() throws IOException {
        screen.startScreen();
        try {
            loop();
        } finally {
            screen.stopScreen();
        }
    }

    private void loop() throws IOException {
        boolean finished = false;
        while (!finished) {
            KeyStroke keyStroke = screen.pollInput();
            if (keyStroke != null && keyStroke.getKeyType() == KeyType.Escape) {
                finished = true;
            } else {
                if (keyStroke != null) {
                    gameLogicProcessor.processNextFrame(com.gk.snake.KeyStroke.of(keyStroke));
                    // TODO render returned state
                }
                timer.waitOneFrame();
            }
        }
    }
}
