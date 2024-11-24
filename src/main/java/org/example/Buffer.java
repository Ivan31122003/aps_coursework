package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class Buffer {
    private final BlockingQueue<Request> requests;

    public Buffer(Integer capacity) {
        this.requests = new ArrayBlockingQueue<>(capacity);
    }

    public boolean put(Request request) {
            if (!requests.offer(request)) {
                System.out.println("Отказ: очередь переполнена. Заявка с ID " + request.getId() + " отклонена");
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
}
