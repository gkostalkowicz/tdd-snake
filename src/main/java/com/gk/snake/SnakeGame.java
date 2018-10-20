package com.gk.snake;

import com.gk.snake.logic.Board;
import com.gk.snake.logic.InitialStateCalculator;
import com.gk.snake.logic.PositionGenerator;
import com.gk.snake.logic.SnakeCrashIntoWallCheck;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.snake.SnakeCrashIntoItselfCheck;
import com.gk.snake.logic.domain.snake.SnakeDirectionUpdater;
import com.gk.snake.logic.domain.snake.SnakePositionUpdater;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.util.Random;

public class SnakeGame {

    public static void main(String[] args) throws IOException {
        Screen screen = new DefaultTerminalFactory().createScreen();
        TerminalSize terminalSize = screen.getTerminalSize();

        int boardHeight = terminalSize.getRows();
        int boardWidth = terminalSize.getColumns();

        Board board = createGameLogicProcessor(boardHeight, boardWidth);

        PlayingLoop playingLoop = new PlayingLoop(screen, new Timer(), board, new Renderer(screen));
        MenuLoop menuLoop = new MenuLoop(playingLoop, new InputReader(screen), new GameOverBannerRenderer());
        GameManager gameManager = new GameManager(menuLoop, screen);

        gameManager.play();
    }

    private static Board createGameLogicProcessor(int boardHeight, int boardWidth) {
        PositionGenerator positionGenerator = new PositionGenerator(boardWidth, boardHeight, new Random());
        GameState initialState = new InitialStateCalculator(positionGenerator).getInitialState(boardWidth, boardHeight,
                new SnakeDirectionUpdater(), new SnakePositionUpdater(), new SnakeCrashIntoItselfCheck());
        return new Board(new SnakeCrashIntoWallCheck(boardWidth, boardHeight),
                positionGenerator, initialState);
    }
}
