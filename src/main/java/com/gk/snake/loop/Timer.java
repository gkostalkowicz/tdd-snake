package com.gk.snake.loop;

public class Timer {

    public static final long FRAME_DURATION = 75L;

    public void waitOneFrame() {
        try {
            Thread.sleep(FRAME_DURATION);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
