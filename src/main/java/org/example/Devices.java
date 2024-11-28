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

    public Devices(Integer devicesNumber, SelectionDispatcher selectionDispatcher, Integer totalRequest) {
        this.devicesNumber = devicesNumber;
        this.totalRequest = totalRequest;
        devices = new ArrayList<>(devicesNumber);
        for(int i = 1; i <= devicesNumber; ++i) {
            devices.add(new Device(i, selectionDispatcher, this));
        }
    }

    public void runDevices() {
        for(int i = 0; i < devicesNumber; ++i) {
            new Thread(devices.get(i)).start();
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
}
