package com.gk.snake.input;

import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.KeyType;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class InputReaderTest {

    @Test
    public void whenReadKey_thenReadKeyIsCalledOnScreen() throws IOException {
        // given:
        InputProvider inputProviderMock = mock(InputProvider.class);
        InputReader inputReader = new InputReader(inputProviderMock);

        // when:
        inputReader.readKey();

        // then:
        verify(inputProviderMock).readInput();
    }

    @Test
    public void givenLeftArrowKeyPress_whenReadKey_thenLeftArrowKeyStrokeIsReturned() throws IOException {
        // given:
        InputProvider inputProviderStub = mock(InputProvider.class);
        when(inputProviderStub.readInput()).thenReturn(new com.googlecode.lanterna.input.KeyStroke(KeyType.ArrowLeft));
        InputReader inputReader = new InputReader(inputProviderStub);

        // when:
        KeyStroke keyStroke = inputReader.readKey();

        // then:
        assertEquals(KeyStroke.LEFT_ARROW, keyStroke);
    }
}