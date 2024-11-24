package org.example;
import javafx.application.Platform;

public class Queue {
    private Buffer buffer;
    private RequestsDispatcher requestsDispatcher;
    private SelectionDispatcher selectionDispatcher;

    public Queue(Integer capacity) {
        buffer = new Buffer(capacity);
        requestsDispatcher = new RequestsDispatcher(buffer);
        selectionDispatcher = new SelectionDispatcher(buffer);
        Device.setSelectionDispatcher(selectionDispatcher);
    }

    public void formingRequests() {
        Device device1 = new Device();
        Device device2 = new Device();
        Device device3 = new Device();


        DynamicTableExample.addDevice(device1);
        DynamicTableExample.addDevice(device2);
        DynamicTableExample.addDevice(device3);

        new Thread(device1).start();
        new Thread(device2).start();
        new Thread(device3).start();

        for (int i = 0; i < 10; ++i) {
            Request request = new Request();
            requestsDispatcher.putRequest(request);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread was interrupted during sleep: " + e.getMessage());
            }
        }
    }
}
