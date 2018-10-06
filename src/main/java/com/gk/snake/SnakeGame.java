package com.gk.snake;

import com.gk.snake.logic.*;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame {

    private final Screen screen;

    private final Timer timer;

    private final GameLogicProcessor gameLogicProcessor;

    public static void main(String[] args) throws IOException {
        Screen screen = new DefaultTerminalFactory().createScreen();
        TerminalSize terminalSize = screen.getTerminalSize();

        int boardHeight = terminalSize.getRows();
        int boardWidth = terminalSize.getColumns();

        List<GameRule> gameRules = new ArrayList<>();
        gameRules.add(new AppleGenerator(new PositionGenerator(boardWidth, boardHeight, new Random())));
        gameRules.add(new SnakeDirectionCalculator());
        gameRules.add(new SnakePositionCalculator());

        GameState initialState = new InitialStateCalculator().getInitialState(boardWidth, boardHeight);
        GameLogicProcessor gameLogicProcessor = new GameLogicProcessor(gameRules, initialState);

        SnakeGame snakeGame = new SnakeGame(screen, new Timer(), gameLogicProcessor);
        snakeGame.start();
    }

    public SnakeGame(Screen screen, Timer timer, GameLogicProcessor gameLogicProcessor) {
        this.screen = screen;
        this.timer = timer;
        this.gameLogicProcessor = gameLogicProcessor;
    }

    public void start() throws IOException {
        screen.startScreen();

        boolean finished = false;
        while (!finished) {
            KeyStroke keyStroke = screen.pollInput();
            if (keyStroke != null && keyStroke.getKeyType() == KeyType.Escape) {
                finished = true;
            } else {
                if (keyStroke != null) {
                    // TODO abstract key away from Lanterna
                    gameLogicProcessor.processNextFrame(keyStroke);
                }
                timer.waitOneFrame();
            }
        }

        screen.stopScreen();
    }
}
