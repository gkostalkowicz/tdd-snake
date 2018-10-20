package com.gk.snake;

import com.googlecode.lanterna.input.InputProvider;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class InputReader {

    private final InputProvider inputProvider;

    public KeyStroke readKey() throws IOException {
        return KeyStroke.of(inputProvider.readInput());
    }
}
