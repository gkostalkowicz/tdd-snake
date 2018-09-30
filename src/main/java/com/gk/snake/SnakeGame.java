package com.gk.snake;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class SnakeGame {

    private final Screen screen;

    private final Timer timer;

    private final GameLogicProcessor gameLogicProcessor;

    public static void main(String[] args) throws IOException {
        Screen screen = new DefaultTerminalFactory().createScreen();
        TerminalSize terminalSize = screen.getTerminalSize();
        new SnakeGame(screen, new Timer(), new GameLogicProcessor(new SnakeDirectionCalculator(),
                new SnakePositionCalculator(), terminalSize.getColumns(),
                terminalSize.getRows())).start();
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
                    gameLogicProcessor.processNextFrame(keyStroke);
                }
                timer.waitOneFrame();
            }
        }

        screen.stopScreen();
    }
}
