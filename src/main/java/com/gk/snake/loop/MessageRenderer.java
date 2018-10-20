package com.gk.snake.loop;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MessageRenderer {

    // TODO development of this class wasn't driven by tests, add tests to existing code

    private final Screen screen;

    private final int boardWidth;
    private final int boardHeight;

    public void renderMessage(List<String> lines) {
        TextGraphics textGraphics = screen.newTextGraphics();

        int messageWidth = lines.stream()
                .mapToInt(String::length)
                .max().getAsInt();
        int messageHeight = lines.size();

        int x = boardWidth / 2 - messageWidth / 2;
        int y = boardHeight / 2 - messageHeight / 2;

        for (int i = 0; i < lines.size(); i++) {
            textGraphics.putString(x, y + i, lines.get(i));
        }
    }
}
