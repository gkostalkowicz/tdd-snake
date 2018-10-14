package com.gk.snake;

import com.gk.snake.logic.Board;
import com.gk.snake.logic.InitialStateCalculator;
import com.gk.snake.logic.SnakeDirectionUpdater;
import com.gk.snake.logic.SnakePositionUpdater;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.rules.CrashedIntoItselfCheck;
import com.gk.snake.logic.rules.CrashedIntoWallCheck;
import com.gk.snake.logic.rules.GameRule;
import com.gk.snake.logic.rules.PositionGenerator;
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

        Board board = createGameLogicProcessor(boardHeight, boardWidth);

        SnakeLoop snakeLoop = new SnakeLoop(screen, new Timer(), board);
        snakeLoop.start();
    }

    private static Board createGameLogicProcessor(int boardHeight, int boardWidth) {
        List<GameRule> gameRules = new ArrayList<>();

        GameState initialState = new InitialStateCalculator().getInitialState(boardWidth, boardHeight,
                new SnakeDirectionUpdater(), new SnakePositionUpdater(), new CrashedIntoItselfCheck());
        return new Board(gameRules, new CrashedIntoWallCheck(boardWidth, boardHeight),
                new PositionGenerator(boardWidth, boardHeight, new Random()), initialState);
    }
}
