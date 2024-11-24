package org.example;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;


public class Device implements Runnable {
    private static List<Device> devices = new ArrayList<>();
    private static Integer idCounter = 1;
    private static SelectionDispatcher selectionDispatcher;

    private final Integer id;
    private final IntegerProperty requestsCounter = new SimpleIntegerProperty(0);
    private final IntegerProperty workTime = new SimpleIntegerProperty(0);
    private final DoubleProperty averageTimeOfExecuting = new SimpleDoubleProperty(0.0);
    private final IntegerProperty status = new SimpleIntegerProperty(0);

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
        try {
            while (true) {
                Request request = selectionDispatcher.takeRequest();
                status.set(1);
                System.out.println("Device " + id + " execute:\n" + "Request Id: " + request.getId() + "\nExecuting time: " + request.getExecutingTime() + "\n");
                Thread.sleep(request.getExecutingTime());
                processRequest(request);

                requestsCounter.set(requestsCounter.get() + 1);
                workTime.set(workTime.get() + request.getExecutingTime());
                averageTimeOfExecuting.set((double) workTime.get() / requestsCounter.get());
                status.set(0);
            }
        } catch (Throwable e) {
            Thread.currentThread().interrupt();
        }
    }

    private void processRequest(Request request) {
        System.out.println("Device " + id + " processing request: " + request.getId());
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

    public int getStatus() {
        return status.get();
    }

    public IntegerProperty statusProperty() {
        return status;
    }
}
