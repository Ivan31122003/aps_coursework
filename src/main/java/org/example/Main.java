package org.example;

public class Main {
    public static void main(String[] args) {

        new Thread(() -> DynamicTableExample.main(args)).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Integer totalRequests = 100;
        Smo smo = new Smo(7, 7, totalRequests);
        Sources sources = new Sources(8, smo.getRequestsDispatcher(), 3500, 1000, totalRequests, 2d, 5d);
//        Sources sources = new Sources(8, smo.getRequestsDispatcher(), 35, 10, totalRequests, 0.02, 0.05);


        smo.runDevices();
        sources.runSources();

        Statistics statistics = new Statistics(sources, smo);
        statistics.start();
    }
}
