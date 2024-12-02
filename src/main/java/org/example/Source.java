package org.example;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Source implements Runnable {
    private IntegerProperty id = new SimpleIntegerProperty();
    private IntegerProperty requestsCount = new SimpleIntegerProperty();
    private IntegerProperty rejectedRequests = new SimpleIntegerProperty();
    private List<Request> requests = new ArrayList<>();
    private Sources sources;
    private RequestsDispatcher requestsDispatcher;
    private Integer delay;
    private Double minRequestTime;
    private Double maxRequestTime;
    private Calendar calendar;

    private static Integer idCounter = 1;

    public Source(RequestsDispatcher requestsDispatcher, Integer delay, Sources sources, Double minRequestTime, Double maxRequestTime, Calendar calendar) {
        this.sources = sources;
        this.minRequestTime = minRequestTime;
        this.maxRequestTime = maxRequestTime;
        id.set(idCounter++);
        this.requestsDispatcher = requestsDispatcher;
        this.delay = delay;
        this.calendar = calendar;
    }

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while (sources.incrementCurrentRequestsNumber()) {
                Thread.sleep(delay);
                requests.add(new Request(id.get(), minRequestTime, maxRequestTime));
                if(!requestsDispatcher.putRequest(requests.getLast())) {
                    rejectedRequests.set(rejectedRequests.get() + 1);
                    requests.getLast().setIsRejected();
                };
                requestsCount.set(requestsCount.get() + 1);
                calendar.setEventRequest(requests.getLast());
            }
        } catch (Throwable e) {
            Thread.currentThread().interrupt();
        }
    }

    public List<Long> getAverageTimeOfRequestsInSystem() {
        List<Long> list = new ArrayList<>();
        for (Request request : requests) {
            if (!request.getIsRejected()) {
                list.add(request.getTimeInSystem());
            }
        }
        return list;
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
