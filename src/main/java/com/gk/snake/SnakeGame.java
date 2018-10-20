package com.gk.snake;

import com.gk.snake.input.InputReader;
import com.gk.snake.logic.Board;
import com.gk.snake.logic.InitialStateCalculator;
import com.gk.snake.logic.PositionGenerator;
import com.gk.snake.logic.SnakeCrashIntoWallCheck;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.snake.SnakeCrashIntoItselfCheck;
import com.gk.snake.logic.domain.snake.SnakeDirectionUpdater;
import com.gk.snake.logic.domain.snake.SnakePositionUpdater;
import com.gk.snake.loop.*;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.util.Random;
import java.util.function.Supplier;

public class SnakeGame {

    public static void main(String[] args) throws IOException {
        Screen screen = new DefaultTerminalFactory().createScreen();
        TerminalSize terminalSize = screen.getTerminalSize();

        int boardWidth = terminalSize.getColumns();
        int boardHeight = terminalSize.getRows();

        MessageRenderer messageRenderer = new MessageRenderer(screen, boardWidth, boardHeight);
        GameOverBannerRenderer gameOverBannerRenderer = new GameOverBannerRenderer(messageRenderer, screen);

        InputReader inputReader = new InputReader(screen);
        Supplier<Board> boardSupplier = () -> SnakeGame.createBoard(boardWidth, boardHeight);
        PlayingLoop playingLoop = new PlayingLoop(inputReader, new Timer(), boardSupplier, new Renderer(screen));
        MenuLoop menuLoop = new MenuLoop(playingLoop, inputReader, gameOverBannerRenderer);
        GameManager gameManager = new GameManager(menuLoop, screen);

        gameManager.play();
    }

    private static Board createBoard(int boardWidth, int boardHeight) {
        PositionGenerator positionGenerator = new PositionGenerator(boardWidth, boardHeight, new Random());
        GameState initialState = new InitialStateCalculator(positionGenerator).getInitialState(boardWidth, boardHeight,
                new SnakeDirectionUpdater(), new SnakePositionUpdater(), new SnakeCrashIntoItselfCheck());
        return new Board(new SnakeCrashIntoWallCheck(boardWidth, boardHeight),
                positionGenerator, initialState);
    }
}
