package com.gk.snake;

import com.googlecode.lanterna.input.KeyType;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(DataProviderRunner.class)
public class KeyStrokeTest {

    @DataProvider
    public static Object[][] keyStrokeDataProvider() {
        return new Object[][] {
                {KeyType.ArrowLeft, KeyStroke.LEFT_ARROW},
                {KeyType.ArrowRight, KeyStroke.RIGHT_ARROW},
                {KeyType.ArrowUp, KeyStroke.UP_ARROW},
                {KeyType.ArrowDown, KeyStroke.DOWN_ARROW},
                {KeyType.Enter, KeyStroke.UNKNOWN_KEY}
        };
    }

    @Test
    @UseDataProvider("keyStrokeDataProvider")
    public void givenALibraryKeyStroke_whenFactoryMethodIsCalled_thenCorrespondingSnakeKeyStrokeIsReturned(
            KeyType libraryKeyType, KeyStroke expectedKeyStroke) {
        // given:
        com.googlecode.lanterna.input.KeyStroke libraryKeyStroke =
                new com.googlecode.lanterna.input.KeyStroke(libraryKeyType);

        // when:
        KeyStroke keyStroke = KeyStroke.of(libraryKeyStroke);

        // then:
        assertEquals(expectedKeyStroke, keyStroke);
    }

    @Test
    public void givenNoKeyStroke_whenFactoryMethodIsCalled_thenNullIsReturned() {
        // given:
        com.googlecode.lanterna.input.KeyStroke libraryKeyStroke = null;

        // when:
        KeyStroke keyStroke = KeyStroke.of(libraryKeyStroke);

        // then:
        assertNull(keyStroke);
    }
}