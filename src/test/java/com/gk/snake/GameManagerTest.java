package com.gk.snake;

import com.googlecode.lanterna.screen.Screen;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class GameManagerTest {

    @Test
    public void whenPlay_thenMenuLoopIsStarted() throws IOException {
        // given:
        MenuLoop menuLoop = mock(MenuLoop.class);
        GameManager gameManager = new GameManager(menuLoop, mock(Screen.class));

        // when:
        gameManager.play();

        // then:
        verify(menuLoop).start();
    }

    @Test
    public void whenPlay_thenScreenIsStarted() throws IOException {

        // given:
        Screen screenMock = mock(Screen.class);
        GameManager gameManager = new GameManager(mock(MenuLoop.class), screenMock);

        // when:
        gameManager.play();

        // then:
        verify(screenMock).startScreen();
    }

    @Test
    public void whenPlay_thenScreenIsStopped() throws IOException {

        // given:
        Screen screenMock = mock(Screen.class);
        GameManager gameManager = new GameManager(mock(MenuLoop.class), screenMock);

        // when:
        gameManager.play();

        // then:
        verify(screenMock).stopScreen();
    }

    @Test
    public void givenThatExceptionIsThrownFromGameLogicProcessor_whenStart_thenScreenIsStopped() throws IOException {

        // given:
        Screen screenMock = mock(Screen.class);
        MenuLoop menuLoopStub = mock(MenuLoop.class);
        doThrow(new RuntimeException()).when(menuLoopStub).start();
        GameManager gameManager = new GameManager(menuLoopStub, screenMock);

        // when:
        try {
            gameManager.play();
        } catch (RuntimeException e) {
            // ignore
        }

        // then:
        verify(screenMock).stopScreen();
    }
}