package org.example;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class Device implements Runnable {
    private static List<Device> devices = new ArrayList<>();
    private static Integer idCounter = 1;
    private static SelectionDispatcher selectionDispatcher;

    private final Integer id;
    private final IntegerProperty workTime = new SimpleIntegerProperty(0);
    private LongProperty idleTime = new SimpleLongProperty(0);
    private final IntegerProperty requestsCounter = new SimpleIntegerProperty(0);
    private final DoubleProperty averageTimeOfExecuting = new SimpleDoubleProperty(0.0);
    private final StringProperty status = new SimpleStringProperty("Простой");

    public Device() {
        this.id = generateId();
        devices.add(this);
    }

    public static void setSelectionDispatcher(SelectionDispatcher dispatcher) {
        selectionDispatcher = dispatcher;
    }

    private static Integer generateId() {
        return idCounter++;
    }

    public static List<Device> getDevices() {
        return devices;
    }

    @Override
    public void run() {
        long idleStartTime = 0;
        long idleEndTime = 0;
        try {
            while (true) {
                idleStartTime = System.currentTimeMillis();
                Request request = selectionDispatcher.takeRequest();
                idleEndTime = System.currentTimeMillis();
                idleTime.set(idleTime.get() + idleEndTime - idleStartTime);

                status.set("Занят");
                Thread.sleep(request.getExecutingTime());

                status.set("Простой");
                requestsCounter.set(requestsCounter.get() + 1);
                workTime.set(workTime.get() + request.getExecutingTime());
                averageTimeOfExecuting.set((double) workTime.get() / requestsCounter.get());
            }
        } catch (Throwable e) {
            Thread.currentThread().interrupt();
        }
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
}
