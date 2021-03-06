package com.gk.snake.loop;

import com.gk.snake.input.InputReader;
import com.gk.snake.input.KeyStroke;
import com.gk.snake.logic.Board;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.GameStatus;
import com.gk.snake.loop.PlayingLoop.FinishCause;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PlayingLoopTest {

    private InputReader inputReaderMock;
    private Timer timerMock;
    private Board boardMock;
    private Renderer rendererMock;
    private PlayingLoop loop;

    @Before
    public void setUp() {

        inputReaderMock = mock(InputReader.class);
        timerMock = mock(Timer.class);
        boardMock = mock(Board.class);
        rendererMock = mock(Renderer.class);
        loop = new PlayingLoop(inputReaderMock, timerMock, () -> boardMock, rendererMock);
    }

    // -------------------
    // waiting

    @Test
    public void givenEscKeyPressInFirstFrame_whenStart_thenDoNotWait() throws IOException {

        // given:
        when(inputReaderMock.pollKey()).thenReturn(KeyStroke.ESCAPE);

        // when:
        loop.start();

        // then:
        verify(inputReaderMock).pollKey();
        verify(timerMock, times(0)).waitOneFrame();
    }

    @Test
    public void givenEscKeyPressInSecondFrame_whenStart_thenWaitOneFrame() throws IOException {

        // given:
        when(inputReaderMock.pollKey()).thenReturn(null, KeyStroke.ESCAPE);
        when(boardMock.getState()).thenReturn(new GameState(null, null));

        // when:
        loop.start();

        // then:
        verify(inputReaderMock, times(2)).pollKey();
        verify(timerMock).waitOneFrame();
    }

    // -------------------
    // key strokes

    @Test
    public void givenKeyStroke_whenStart_thenProcessNextFrameIsCalledOnBoardWithPressedKeys() throws IOException {

        // given:
        when(inputReaderMock.pollKey()).thenReturn(KeyStroke.LEFT_ARROW, KeyStroke.ESCAPE);
        when(boardMock.getState()).thenReturn(new GameState(null, null));

        // when:
        loop.start();

        // then:
        verify(boardMock).processNextFrame(KeyStroke.LEFT_ARROW);
    }

    @Test
    public void givenNoKeyStroke_whenStart_thenProcessNextFrameIsCalledOnBoardWithNullKeyStroke() throws IOException {

        // given:
        when(inputReaderMock.pollKey()).thenReturn(null, KeyStroke.ESCAPE);
        when(boardMock.getState()).thenReturn(new GameState(null, null));

        // when:
        loop.start();

        // then:
        verify(boardMock).processNextFrame(null);
    }

    // -------------------
    // finish cause

    @Test
    public void givenEscapeKeyPress_whenStart_thenUserQuitIsReturnedAsFinishCause() throws IOException {

        // given:
        when(inputReaderMock.pollKey()).thenReturn(KeyStroke.ESCAPE);

        // when:
        FinishCause finishCause = loop.start();

        // then:
        assertEquals(FinishCause.USER_QUIT, finishCause);
    }

    @Test
    public void givenGameOverGameStatus_whenStart_thenPlayerDiedIsReturnedAsFinishCause() throws IOException {

        // given:
        when(boardMock.getState()).thenReturn(new GameState(null, null, GameStatus.GAME_OVER));

        // when:
        FinishCause finishCause = loop.start();

        // then:
        assertEquals(FinishCause.PLAYER_DIED, finishCause);
    }

    // -------------------
    // rendering

    @Test
    public void givenSomeGameState_whenStart_thenGameStateIsRendered() throws IOException {

        // given:
        when(inputReaderMock.pollKey()).thenReturn(KeyStroke.LEFT_ARROW, KeyStroke.ESCAPE);
        GameState gameState = new GameState(null, null);
        when(boardMock.getState()).thenReturn(gameState);

        // when:
        loop.start();

        // then:
        verify(rendererMock).render(gameState);
    }
}
