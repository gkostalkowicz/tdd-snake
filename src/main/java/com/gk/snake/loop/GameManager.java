package com.gk.snake.loop;

import com.googlecode.lanterna.screen.Screen;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class GameManager {

    private final MenuLoop menuLoop;
    private final Screen screen;

    public void play() throws IOException {
        screen.startScreen();
        try {
            menuLoop.start();
        } finally {
            screen.stopScreen();
        }
    }
}
