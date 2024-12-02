package org.example;

import javafx.beans.property.*;

public class Device implements Runnable {
    private final Integer id;
    private final IntegerProperty workTime = new SimpleIntegerProperty(0);
    private LongProperty idleTime = new SimpleLongProperty(0);
    private final IntegerProperty requestsCounter = new SimpleIntegerProperty(0);
    private final DoubleProperty averageTimeOfExecuting = new SimpleDoubleProperty(0.0);
    private final StringProperty status = new SimpleStringProperty("Простой");
    private final Devices devices;
    private Integer requestId;
    private SelectionDispatcher selectionDispatcher;
    private Calendar calendar;

    public Device(Integer id, SelectionDispatcher selectionDispatcher, Devices devices, Calendar calendar) {
        this.id = id;
        this.selectionDispatcher = selectionDispatcher;
        this.devices = devices;
        this.calendar = calendar;
    }

    @Override
    public void run() {
        long idleStartTime = 0;
        long idleEndTime = 0;
        try {
            while (devices.incrementCurrentRequestsNumber()) {
                idleStartTime = System.currentTimeMillis();
                Request request = selectionDispatcher.takeRequest();
                idleEndTime = System.currentTimeMillis();
                idleTime.set(idleTime.get() + idleEndTime - idleStartTime);

                status.set(String.format("Занят: Источник № %d; Заявка № %d", request.getSourceId(), request.getId()));
                Thread.sleep(request.getExecutingTime());
                request.setTimeInSystem();
                status.set("Простой");
                requestId = request.getId();
                requestsCounter.set(requestsCounter.get() + 1);
                workTime.set(workTime.get() + request.getExecutingTime());
                averageTimeOfExecuting.set((double) workTime.get() / requestsCounter.get());
                calendar.setEventDeviceRelease(this, System.currentTimeMillis());
            }
        } catch (Throwable e) {
            Thread.currentThread().interrupt();
        }
    }
    public Integer getRequestId() {
        return requestId;
    }

    public Integer getId() {
        return id;
    }

    public int getRequestsCounter() {
        return requestsCounter.get();
    }

    public IntegerProperty requestsCounterProperty() {
        return requestsCounter;
    }

    public int getWorkTime() {
        return workTime.get();
    }

    public IntegerProperty workTimeProperty() {
        return workTime;
    }

    public double getAverageTimeOfExecuting() {
        return averageTimeOfExecuting.get();
    }

    public DoubleProperty averageTimeOfExecutingProperty() {
        return averageTimeOfExecuting;
    }

    public long getIdleTime() {
        return idleTime.get();
    }

    public LongProperty idleTimeProperty() {
        return idleTime;
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public Boolean isIdle() {
        return status.get().equals("Простой");
    }
}
