package com.gk.snake;

public class Timer {

    public static final long FRAME_DURATION = 100L;

    public void waitOneFrame() {
        try {
            Thread.sleep(FRAME_DURATION);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
