package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Devices {
    private List<Device> devices;
    private SelectionDispatcher selectionDispatcher;
    private Integer devicesNumber;
    private final Integer totalRequest;
    private AtomicInteger currentRequestsNumber = new AtomicInteger(0);

    public Devices(Integer devicesNumber, SelectionDispatcher selectionDispatcher, Integer totalRequest, Calendar calendar) {
        this.devicesNumber = devicesNumber;
        this.totalRequest = totalRequest;
        devices = new ArrayList<>(devicesNumber);
        for(int i = 1; i <= devicesNumber; ++i) {
            devices.add(new Device(i, selectionDispatcher, this, calendar));
        }
    }

    public void runDevices() {
        for(int i = 0; i < devicesNumber; ++i) {
            new Thread(devices.get(i)).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Device> getDevices() {
        return devices;
    }

    public SelectionDispatcher getSelectionDispatcher() {
        return selectionDispatcher;
    }

    public Boolean incrementCurrentRequestsNumber() {
        if (currentRequestsNumber.getAndIncrement() < totalRequest) {
            return true;
        }
        return false;
    }

    public Boolean isDevicesIdle() {
        for (Device device : devices) {
            if (!device.isIdle()) {
                return false;
            }
        }
        return true;
    }

    public Double getWorkload() {
        Integer workTime = 0;
        Integer idleTime = 0;
        for (Device device : devices) {
            workTime += device.getWorkTime();
            idleTime += (int) device.getIdleTime();
        }
        Double workload = ((double) Math.round(((double) workTime / (workTime + idleTime)) * 1000)) / 10;
        return workload;
    }

    public Double getAverageTimeOfExecutingRequest() {
        Double sumOfAverageTime = 0d;
        for (Device device : devices) {
            sumOfAverageTime += device.getAverageTimeOfExecuting();
        }
        return ((double) Math.round(sumOfAverageTime / devices.size())) / 1000;
    }
}
