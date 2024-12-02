package org.example;

public class Main {
    public static void main(String[] args) {

        new Thread(() -> DynamicTableExample.main(args)).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Calendar calendar = new Calendar();

        Integer totalRequests = 100;
        Smo smo = new Smo(1, 2, totalRequests, calendar);
        Sources sources = new Sources(2, smo.getRequestsDispatcher(), 3000, 1000, totalRequests, 3.45, 3.55, calendar);
//        Sources sources = new Sources(8, smo.getRequestsDispatcher(), 35, 10, totalRequests, 0.02, 0.05);
        calendar.set(sources, smo);


        smo.runDevices();
        sources.runSources();

        Statistics statistics = new Statistics(sources, smo);
        statistics.start();
    }
}
