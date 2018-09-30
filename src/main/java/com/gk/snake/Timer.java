package com.gk.snake;

public class Timer {

    public void waitOneFrame() {
        try {
            Thread.sleep(300L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
