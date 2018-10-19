package com.gk.snake;

import com.gk.snake.logic.Board;
import com.gk.snake.logic.domain.GameState;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class GameLoopTest {

    private Screen screenMock;
    private Timer timerMock;
    private Board boardMock;
    private Renderer rendererMock;
    private GameLoop loop;

    @Before
    public void setUp() {

        screenMock = mock(Screen.class);
        timerMock = mock(Timer.class);
        boardMock = mock(Board.class);
        rendererMock = mock(Renderer.class);
        loop = new GameLoop(screenMock, timerMock, boardMock, rendererMock);
    }

    // -------------------
    // starting screen

    @Test
    public void whenStart_thenScreenIsStarted() throws IOException {

        // given:
        when(screenMock.pollInput()).thenReturn(new KeyStroke(KeyType.Escape));

        // when:
        loop.start();

        // then:
        verify(screenMock).startScreen();
    }

    // -------------------
    // waiting and stopping screen

    @Test
    public void givenEscKeyPressInFirstFrame_whenStart_thenDoNotWaitAndStopScreen() throws IOException {

        // given:
        when(screenMock.pollInput()).thenReturn(new KeyStroke(KeyType.Escape));

        // when:
        loop.start();

        // then:
        verify(screenMock).pollInput();
        verify(timerMock, times(0)).waitOneFrame();
        verify(screenMock).stopScreen();
    }

    @Test
    public void givenEscKeyPressInSecondFrame_whenStart_thenWaitOneFrameAndStopScreen() throws IOException {

        // given:
        when(screenMock.pollInput()).thenReturn(null, new KeyStroke(KeyType.Escape));

        // when:
        loop.start();

        // then:
        verify(screenMock, times(2)).pollInput();
        verify(timerMock).waitOneFrame();
        verify(screenMock).stopScreen();
    }

    @Test
    public void givenThatExceptionIsThrownFromGameLogicProcessor_whenStart_thenScreenIsStopped() throws IOException {

        // given:
        when(screenMock.pollInput()).thenReturn(null, new KeyStroke(KeyType.Escape));
        doThrow(new RuntimeException()).when(timerMock).waitOneFrame();

        // when:
        try {
            loop.start();
        } catch (RuntimeException e) {
            // ignore
        }

        // then:
        verify(screenMock).stopScreen();
    }

    // -------------------
    // key strokes

    @Test
    public void givenKeyStroke_whenStart_thenProcessNextFrameIsCalledOnBoardWithPressedKeys() throws IOException {

        // given:
        when(screenMock.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowLeft), new KeyStroke(KeyType.Escape));

        // when:
        loop.start();

        // then:
        verify(boardMock).processNextFrame(com.gk.snake.KeyStroke.LEFT_ARROW);
    }

    @Test
    public void givenNoKeyStroke_whenStart_thenProcessNextFrameIsCalledOnBoardWithNullKeyStroke() throws IOException {

        // given:
        when(screenMock.pollInput()).thenReturn(null, new KeyStroke(KeyType.Escape));

        // when:
        loop.start();

        // then:
        verify(boardMock).processNextFrame(null);
    }

    // -------------------
    // rendering

    @Test
    public void givenSomeGameState_whenStart_thenGameStateIsRendered() throws IOException {

        // given:
        when(screenMock.pollInput()).thenReturn(new KeyStroke(KeyType.ArrowLeft), new KeyStroke(KeyType.Escape));
        GameState gameState = new GameState(null, null);
        when(boardMock.getState()).thenReturn(gameState);

        // when:
        loop.start();

        // then:
        verify(rendererMock).render(gameState);
    }
}
