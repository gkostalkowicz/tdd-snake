package com.gk.snake.input;

import com.googlecode.lanterna.input.InputProvider;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class InputReader {

    private final InputProvider inputProvider;

    public KeyStroke pollKey() throws IOException {
        com.googlecode.lanterna.input.KeyStroke firstKeyStroke = inputProvider.pollInput();
        while (inputProvider.pollInput() != null) {
            // poll as long as there are keystrokes in the buffer to clear it for better game responsiveness
        }
        return KeyStroke.of(firstKeyStroke);
    }

    public KeyStroke readKey() throws IOException {
        return KeyStroke.of(inputProvider.readInput());
    }
}
