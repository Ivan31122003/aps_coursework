package org.example.sources;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.example.queue.RequestsDispatcher;

public class Source implements Runnable {
    private static Integer idCounter = 1;

    private Sources sources;
    private IntegerProperty id = new SimpleIntegerProperty();
    private RequestsDispatcher requestsDispatcher;
    private Integer delay;
    private IntegerProperty requestsCount = new SimpleIntegerProperty();
    private IntegerProperty rejectedRequests = new SimpleIntegerProperty();
    private Double minRequestTime;
    private Double maxRequestTime;

    public Source(RequestsDispatcher requestsDispatcher, Integer delay, Sources sources, Double minRequestTime, Double maxRequestTime) {
        this.sources = sources;
        this.minRequestTime = minRequestTime;
        this.maxRequestTime = maxRequestTime;
        id.set(idCounter++);
        this.requestsDispatcher = requestsDispatcher;
        this.delay = delay;
    }

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while (sources.incrementCurrentRequestsNumber()) {
                Thread.sleep(delay);
                if(!requestsDispatcher.putRequest(new Request(id.get(), minRequestTime, maxRequestTime))) {
                    rejectedRequests.set(rejectedRequests.get() + 1);
                };
                requestsCount.set(requestsCount.get() + 1);
            }
        } catch (Throwable e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getRequestsCount() {
        return requestsCount.get();
    }

    public IntegerProperty requestsCountProperty() {
        return requestsCount;
    }

    public int getRejectedRequests() {
        return rejectedRequests.get();
    }

    public IntegerProperty rejectedRequestsProperty() {
        return rejectedRequests;
    }
}
