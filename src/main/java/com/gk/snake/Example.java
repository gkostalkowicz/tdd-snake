package com.gk.snake;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class Example {

    // TODO remove

    public static void main(String[] args) throws IOException, InterruptedException {
        Screen screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        screen.refresh();

//        Thread.sleep(3000L);

        char c = 'a';
        while (true) {
            Thread.sleep(1000L);
            c++;
            screen.setCharacter(10, 5, new TextCharacter(c, TextColor.ANSI.RED, TextColor.ANSI.GREEN));
            screen.refresh();

            KeyStroke keyStroke = screen.pollInput();
            if (keyStroke != null) {
                System.out.println(keyStroke.getKeyType());
            }
        }

//        screen.stopScreen();
    }
}
