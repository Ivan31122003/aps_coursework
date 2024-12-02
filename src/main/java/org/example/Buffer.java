package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class Buffer {
    private final BlockingQueue<Request> requests;

    public Buffer(Integer capacity) {
        this.requests = new ArrayBlockingQueue<>(capacity);
        DynamicTableExample.startBufferUpdater(this);
    }

    public boolean put(Request request) {
            if (!requests.offer(request)) {
                return false;
            }
            return true;
    }

    public Request take() {
        try {
            return requests.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    public BlockingQueue<Request> getRequests() {
        return requests;
    }
}
