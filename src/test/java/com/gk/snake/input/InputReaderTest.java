package com.gk.snake.input;

import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.KeyType;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class InputReaderTest {

    @Test
    public void whenReadKey_thenReadInputIsCalledOnInputProvider() throws IOException {
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
        when(inputProviderStub.readInput()).thenReturn(libraryKeyStroke(KeyType.ArrowLeft));
        InputReader inputReader = new InputReader(inputProviderStub);

        // when:
        KeyStroke keyStroke = inputReader.readKey();

        // then:
        assertEquals(KeyStroke.LEFT_ARROW, keyStroke);
    }

    @Test
    public void givenLeftArrowKeyPress_whenPollKey_thenLeftArrowKeyStrokeIsReturned() throws IOException {
        // given:
        InputProvider inputProviderStub = mock(InputProvider.class);
        when(inputProviderStub.pollInput()).thenReturn(libraryKeyStroke(KeyType.ArrowLeft), null);
        InputReader inputReader = new InputReader(inputProviderStub);

        // when:
        KeyStroke keyStroke = inputReader.pollKey();

        // then:
        assertEquals(KeyStroke.LEFT_ARROW, keyStroke);
    }

    @Test
    public void givenManyKeyPressesInInputBuffer_whenPollKey_thenFirstKeyStrokeIsReturned() throws IOException {
        // given:
        InputProvider inputProviderStub = mock(InputProvider.class);
        when(inputProviderStub.pollInput()).thenReturn(libraryKeyStroke(KeyType.ArrowLeft),
                libraryKeyStroke(KeyType.ArrowRight), libraryKeyStroke(KeyType.ArrowDown), null);
        InputReader inputReader = new InputReader(inputProviderStub);

        // when:
        KeyStroke keyStroke = inputReader.pollKey();

        // then:
        assertEquals(KeyStroke.LEFT_ARROW, keyStroke);
    }

    @Test
    public void givenManyKeyPressesInInputBuffer_whenPollKey_thenPollKeyIsCalledAsLongAsThereAreKeyStrokesInTheBuffer()
            throws IOException {
        // given:
        InputProvider inputProviderMock = mock(InputProvider.class);
        when(inputProviderMock.pollInput()).thenReturn(libraryKeyStroke(KeyType.ArrowLeft),
                libraryKeyStroke(KeyType.ArrowRight), libraryKeyStroke(KeyType.ArrowDown), null);
        InputReader inputReader = new InputReader(inputProviderMock);

        // when:
        KeyStroke keyStroke = inputReader.pollKey();

        // then:
        verify(inputProviderMock, times(4)).pollInput();
    }

    private com.googlecode.lanterna.input.KeyStroke libraryKeyStroke(KeyType keyType) {
        return new com.googlecode.lanterna.input.KeyStroke(keyType);
    }
}