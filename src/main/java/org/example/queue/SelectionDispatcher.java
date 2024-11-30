package org.example.queue;

import org.example.sources.Request;

public class SelectionDispatcher {
    private final Buffer buffer;

    public SelectionDispatcher(Buffer buffer) {
        this.buffer = buffer;
    }

    public Request takeRequest() {
        return buffer.take();
    }
}
