package org.example;

public class RequestsDispatcher {
    private final Buffer buffer;

    public RequestsDispatcher(Buffer buffer) {
        this.buffer = buffer;
    }

    public boolean putRequest(Request request) {
        return buffer.put(request);
    }
}
