package org.example.queue;

public class Queue {
    private Buffer buffer;
    private RequestsDispatcher requestsDispatcher;
    private SelectionDispatcher selectionDispatcher;

    public Queue(Integer capacity) {
        buffer = new Buffer(capacity);
        requestsDispatcher = new RequestsDispatcher(buffer);
        selectionDispatcher = new SelectionDispatcher(buffer);
    }

    public SelectionDispatcher getSelectionDispatcher() {
        return selectionDispatcher;
    }

    public RequestsDispatcher getRequestsDispatcher(){
        return requestsDispatcher;
    }
}
