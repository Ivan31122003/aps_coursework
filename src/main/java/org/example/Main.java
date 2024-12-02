package org.example;

public class Main {
    public static void main(String[] args) {

        new Thread(() -> DynamicTableExample.main(args)).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Integer totalRequests = 13;
        Smo smo = new Smo(4, 1, totalRequests);
        Sources sources = new Sources(5, smo.getRequestsDispatcher(), 3500, 1000, totalRequests, 2.0, 5.0);

        smo.runDevices();
        sources.runSources();

        Statistics statistics = new Statistics(sources, smo);
        statistics.start();
    }
}
