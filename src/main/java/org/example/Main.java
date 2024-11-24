package org.example;

public class Main {
    public static void main(String[] args) {
        new Thread(() -> DynamicTableExample.main(args)).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Queue queue = new Queue(3);
        queue.formingRequests();
    }
}
