package com.gk.snake;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class SnakeGameTest {

    private Screen screenMock;
    private Timer timerMock;
    private GameLogicProcessor gameLogicProcessor;
    private SnakeGame game;

    @Before
    public void setUp() {

        screenMock = mock(Screen.class);
        timerMock = mock(Timer.class);
        gameLogicProcessor = mock(GameLogicProcessor.class);
        game = new SnakeGame(screenMock, timerMock, gameLogicProcessor);
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
    public void testKeyStrokesAreSentToGameEngine() throws IOException {

        when(screenMock.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowLeft), new KeyStroke(KeyType.Escape));

        game.start();

        verify(gameLogicProcessor).processNextFrame(new KeyStroke(KeyType.ArrowLeft));
    }
}
