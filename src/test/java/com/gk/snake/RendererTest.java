package com.gk.snake;

import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.XY;
import com.gk.snake.logic.domain.snake.Snake;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.googlecode.lanterna.TextColor.ANSI.*;
import static org.mockito.Mockito.*;

public class RendererTest {

    private static final TextCharacter SNAKE_CHARACTER = new TextCharacter('#', GREEN, DEFAULT);
    private static final TextCharacter APPLE_CHARACTER = new TextCharacter('@', RED, DEFAULT);

    private Screen screenMock = mock(Screen.class);
    private Renderer renderer = new Renderer(screenMock);

    @Test
    public void whenRender_thenScreenIsCleared() throws IOException {
        // when:
        renderer.render(createDefaultGameState());

        // then:
        verify(screenMock).clear();
    }

    @Test
    public void givenNoApple_whenRender_thenAppleIsNotRendered() throws IOException {
        // when:
        renderer.render(new GameState(createSnake(List.of()), null));

        // then:
        verify(screenMock, times(0)).setCharacter(anyInt(), anyInt(), any());
    }

    @Test
    public void givenAnApple_whenRender_thenAppleCharacterIsPrintedAtApplePosition() throws IOException {
        // when:
        renderer.render(new GameState(createSnake(List.of()), new XY(10, 15)));

        // then:
        verify(screenMock).setCharacter(10, 15, APPLE_CHARACTER);
    }

    @Test
    public void givenSnakeBody_whenRender_thenSnakeCharactersArePrintedAtSnakePosition() throws IOException {
        // when:
        renderer.render(new GameState(createSnake(List.of(new XY(3, 4), new XY(5, 6))), null));

        // then:
        verify(screenMock).setCharacter(3, 4, SNAKE_CHARACTER);
        verify(screenMock).setCharacter(5, 6, SNAKE_CHARACTER);
    }

    @Test
    public void whenRender_thenScreenIsRefreshed() throws IOException {
        // when:
        renderer.render(createDefaultGameState());

        // then:
        verify(screenMock).refresh();
    }

    private GameState createDefaultGameState() {
        return new GameState(createSnake(List.of()), null);
    }

    private Snake createSnake(List<XY> body) {
        return new Snake(body, null, null, null, null);
    }
}