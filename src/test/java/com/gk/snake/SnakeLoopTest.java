package com.gk.snake;

import com.gk.snake.logic.Board;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class SnakeLoopTest {

    private Screen screenMock;
    private Timer timerMock;
    private Board boardMock;
    private SnakeLoop game;

    @Before
    public void setUp() {

        screenMock = mock(Screen.class);
        timerMock = mock(Timer.class);
        boardMock = mock(Board.class);
        game = new SnakeLoop(screenMock, timerMock, boardMock);
    }

    @Test
    public void testScreenStarted() throws IOException {

        when(screenMock.pollInput()).thenReturn(new KeyStroke(KeyType.Escape));

        game.start();

        verify(screenMock).startScreen();
    }

    @Test
    public void testEscKeyPressed() throws IOException {

        when(screenMock.pollInput()).thenReturn(new KeyStroke(KeyType.Escape));

        game.start();

        verify(screenMock).pollInput();
        verify(timerMock, times(0)).waitOneFrame();
        verify(screenMock).stopScreen();
    }

    @Test
    public void testNoKeyPressedThenEscKeyPressed() throws IOException {

        when(screenMock.pollInput()).thenReturn(null, new KeyStroke(KeyType.Escape));

        game.start();

        verify(screenMock, times(2)).pollInput();
        verify(timerMock).waitOneFrame();
        verify(screenMock).stopScreen();
    }

    @Test
    public void testScreenIsStoppedWhenExceptionIsThrownFromGameLogicProcessor() throws IOException {

        when(screenMock.pollInput()).thenReturn(null, new KeyStroke(KeyType.Escape));
        doThrow(new RuntimeException()).when(timerMock).waitOneFrame();

        try {
            game.start();
        } catch (RuntimeException e) {
            // ignore
        }

        verify(screenMock).stopScreen();
    }

    @Test
    public void testKeyStrokesAreSentToGameEngine() throws IOException {

        when(screenMock.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowLeft), new KeyStroke(KeyType.Escape));

        game.start();

        verify(boardMock).processNextFrame(com.gk.snake.KeyStroke.LEFT_ARROW);
    }
}
