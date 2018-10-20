package com.gk.snake.loop;

import com.gk.snake.input.InputReader;
import com.gk.snake.input.KeyStroke;
import com.gk.snake.logic.Board;
import com.gk.snake.logic.domain.GameStatus;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class PlayingLoop {

    private final InputReader inputReader;
    private final Timer timer;
    private final Supplier<Board> boardSupplier;
    private final Renderer renderer;

    public enum FinishCause {
        PLAYER_DIED, USER_QUIT
    }

    public FinishCause start() throws IOException {
        Board board = boardSupplier.get();
        FinishCause finishCause = null;

        while (finishCause == null) {
            KeyStroke keyStroke = inputReader.pollKey();

            if (keyStroke == KeyStroke.ESCAPE) {
                finishCause = FinishCause.USER_QUIT;

            } else {
                board.processNextFrame(keyStroke);
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
