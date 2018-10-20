package com.gk.snake.loop;

import com.gk.snake.input.InputReader;
import com.gk.snake.input.KeyStroke;
import com.gk.snake.loop.PlayingLoop.FinishCause;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class MenuLoop {

    private final PlayingLoop playingLoop;
    private final InputReader inputReader;
    private final GameOverBannerRenderer gameOverBannerRenderer;

    public void start() throws IOException {
        do {
            FinishCause finishCause = playingLoop.start();
            if (finishCause == FinishCause.PLAYER_DIED) {
                gameOverBannerRenderer.renderGameOverBanner();
            }
        } while (inputReader.readKey() != KeyStroke.ESCAPE);
    }
}
