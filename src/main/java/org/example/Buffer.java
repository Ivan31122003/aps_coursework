package org.example;
import java.util.ArrayList;
import java.util.List;

public class Buffer {
    private List<Request> requests;

    public Buffer(Integer capacity) {
        requests = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            requests.add(null);
        }
    }

    public void put(Integer index, Request request) {
        requests.set(index, request);
    }

    public Request take(Integer index) {
        return requests.get(index);
    }
}
