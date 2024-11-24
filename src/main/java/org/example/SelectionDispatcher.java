package org.example;

public class SelectionDispatcher {
    private final Buffer buffer;

    public SelectionDispatcher(Buffer buffer) {
        this.buffer = buffer;
    }

    public Request takeRequest() {
        return buffer.take();
    }
}
