package org.example;

public class Smo {
    private Queue queue;
    private Devices devices;

    public Smo(Integer bufferCapacity, Integer devicesNumber, Integer totalRequests) {
        queue = new Queue(bufferCapacity);
        devices = new Devices(devicesNumber, queue.getSelectionDispatcher(), totalRequests);

        DynamicTableExample.addDevices(devices);
    }

    public RequestsDispatcher getRequestsDispatcher() {
        return queue.getRequestsDispatcher();
    }

    public void runDevices() {
        devices.runDevices();
    }

    public Boolean isDevicesIdle() {
        return devices.isDevicesIdle();
    }

    public Double getDevicesWorkload() {
        return devices.getWorkload();
    }

    public Double getAverageTimeOfExecutingRequest() {
        return devices.getAverageTimeOfExecutingRequest();
    }
}
