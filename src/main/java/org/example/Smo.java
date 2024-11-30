package org.example;

import org.example.devices.Devices;
import org.example.queue.Queue;
import org.example.queue.RequestsDispatcher;
import org.example.table.DynamicTableExample;

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
}
