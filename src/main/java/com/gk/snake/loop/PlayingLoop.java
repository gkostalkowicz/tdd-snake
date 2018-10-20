package com.gk.snake.loop;

import com.gk.snake.logic.Board;
import com.gk.snake.logic.domain.GameStatus;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class PlayingLoop {

    private final Screen screen;
    private final Timer timer;
    private final Board board;
    private final Renderer renderer;

    public enum FinishCause {
        PLAYER_DIED, USER_QUIT
    }

    public FinishCause start() throws IOException {
        FinishCause finishCause = null;

        while (finishCause == null) {
            // TODO use InputReader
            KeyStroke keyStroke = screen.pollInput();

            if (keyStroke != null && keyStroke.getKeyType() == KeyType.Escape) {
                finishCause = FinishCause.USER_QUIT;

            } else {
                board.processNextFrame(com.gk.snake.input.KeyStroke.of(keyStroke));
                if (board.getState().getGameStatus() == GameStatus.GAME_OVER) {
                    finishCause = FinishCause.PLAYER_DIED;
                }
                renderer.render(board.getState());
                timer.waitOneFrame();
            }
        }
        return finishCause;
    }
}
