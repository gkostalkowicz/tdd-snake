package com.gk.snake.loop;

import com.googlecode.lanterna.screen.Screen;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class GameOverBannerRenderer {

    // TODO development of this class wasn't driven by tests, add tests to existing code

    private static final List<String> GAME_OVER_MESSAGE = List.of(
            "        GAME OVER         ",
            "                          ",
            "Press any key to continue.",
            "Press Escape to quit.     ");

    private final MessageRenderer messageRenderer;
    private final Screen screen;

    public void renderGameOverBanner() throws IOException {
        messageRenderer.renderMessage(GAME_OVER_MESSAGE);
        screen.refresh();
    }
}
