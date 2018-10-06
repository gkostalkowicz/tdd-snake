package com.gk.snake;

import com.gk.snake.logic.GameLogicProcessor;
import com.gk.snake.logic.InitialStateCalculator;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.rules.*;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame {

    public static void main(String[] args) throws IOException {
        Screen screen = new DefaultTerminalFactory().createScreen();
        TerminalSize terminalSize = screen.getTerminalSize();

        int boardHeight = terminalSize.getRows();
        int boardWidth = terminalSize.getColumns();

        GameLogicProcessor gameLogicProcessor = createGameLogicProcessor(boardHeight, boardWidth);

        SnakeLoop snakeLoop = new SnakeLoop(screen, new Timer(), gameLogicProcessor);
        snakeLoop.start();
    }

    private static GameLogicProcessor createGameLogicProcessor(int boardHeight, int boardWidth) {
        List<GameRule> gameRules = new ArrayList<>();
        gameRules.add(new AppleGenerator(new PositionGenerator(boardWidth, boardHeight, new Random())));
        gameRules.add(new SnakeDirectionCalculator());
        gameRules.add(new SnakePositionCalculator());

        GameState initialState = new InitialStateCalculator().getInitialState(boardWidth, boardHeight);
        return new GameLogicProcessor(gameRules, initialState);
    }
}
