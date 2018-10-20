package com.gk.snake.loop;

import com.gk.snake.input.InputReader;
import com.gk.snake.input.KeyStroke;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class MenuLoopTest {

    @Test
    public void givenEscKeyPressAfterPlayingLoopFinished_whenStart_thenPlayingLoopIsStartedOnce() throws IOException {
        // given:
        InputReader inputReaderStub = createInputReaderReturningEscapeKeyStroke();
        PlayingLoop playingLoopMock = mock(PlayingLoop.class);
        MenuLoop menuLoop = new MenuLoop(playingLoopMock, inputReaderStub, mock(GameOverBannerRenderer.class));

        // when:
        menuLoop.start();

        // then:
        verify(playingLoopMock).start();
    }

    @Test
    public void givenNonEscAndEscKeyPressesAfterPlayingLoopsFinished_whenStart_thenPlayingLoopIsRunTwoTimes()
            throws IOException {
        // given:
        InputReader inputReaderStub = createInputReaderReturningKeyStrokes(KeyStroke.UNKNOWN_KEY, KeyStroke.ESCAPE);
        PlayingLoop playingLoopMock = mock(PlayingLoop.class);
        MenuLoop menuLoop = new MenuLoop(playingLoopMock, inputReaderStub, mock(GameOverBannerRenderer.class));

        // when:
        menuLoop.start();

        // then:
        verify(playingLoopMock, times(2)).start();
    }

    @Test
    public void givenThatPlayingLoopIsFinishedBecausePlayerDied_whenStart_thenGameOverBannerIsDisplayed()
            throws IOException {
        // given:
        GameOverBannerRenderer gameOverBannerRendererMock = mock(GameOverBannerRenderer.class);
        PlayingLoop playingLoopStub = mock(PlayingLoop.class);
        when(playingLoopStub.start())
                .thenReturn(PlayingLoop.FinishCause.PLAYER_DIED);
        MenuLoop menuLoop = new MenuLoop(playingLoopStub, createInputReaderReturningEscapeKeyStroke(),
                gameOverBannerRendererMock);

        // when:
        menuLoop.start();

        // then:
        verify(gameOverBannerRendererMock).renderGameOverBanner();
    }

    @Test
    public void givenThatPlayingLoopIsFinishedBecauseUserQuits_whenStart_thenGameOverBannerIsNotDisplayed()
            throws IOException {
        // given:
        GameOverBannerRenderer gameOverBannerRendererMock = mock(GameOverBannerRenderer.class);
        PlayingLoop playingLoopStub = mock(PlayingLoop.class);
        when(playingLoopStub.start())
                .thenReturn(PlayingLoop.FinishCause.USER_QUIT);
        MenuLoop menuLoop = new MenuLoop(playingLoopStub, createInputReaderReturningEscapeKeyStroke(),
                gameOverBannerRendererMock);

        // when:
        menuLoop.start();

        // then:
        verifyZeroInteractions(gameOverBannerRendererMock);
    }

    @Test
    public void givenThatPlayingLoopIsFinishedBecauseUserQuits_whenStart_thenLoopIsFinished()
            throws IOException {
        // given:
        GameOverBannerRenderer gameOverBannerRendererMock = mock(GameOverBannerRenderer.class);
        PlayingLoop playingLoopStub = mock(PlayingLoop.class);
        when(playingLoopStub.start())
                .thenReturn(PlayingLoop.FinishCause.USER_QUIT);
        MenuLoop menuLoop = new MenuLoop(playingLoopStub, mock(InputReader.class), gameOverBannerRendererMock);

        // when:
        menuLoop.start();

        // then loop is finished
    }

    private InputReader createInputReaderReturningEscapeKeyStroke() throws IOException {
        return createInputReaderReturningKeyStrokes(KeyStroke.ESCAPE);
    }

    private InputReader createInputReaderReturningKeyStrokes(KeyStroke... keyStrokes) throws IOException {
        InputReader inputReaderStub = mock(InputReader.class);
        when(inputReaderStub.readKey())
                .thenReturn(keyStrokes[0], Arrays.copyOfRange(keyStrokes, 1, keyStrokes.length));
        return inputReaderStub;
    }
}
