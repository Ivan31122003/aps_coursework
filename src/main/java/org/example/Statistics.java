package org.example;

import java.util.List;

public class Statistics {
    private final Sources sources;
    private final Smo smo;

    public Statistics(Sources sources, Smo smo) {
        this.sources = sources;
        this.smo = smo;
    }

    public void start() {
        while(!(sources.isAllRequestGenerated() && smo.isDevicesIdle())){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        List<Long> timeOfRequestsInSystem = sources.getAverageTimeOfRequestsInSystem();
        Double averageTime = 0d;
        for (Long time : timeOfRequestsInSystem) {
            averageTime += time;
        }
        averageTime /= timeOfRequestsInSystem.size();
        Double percent = (double) timeOfRequestsInSystem.size() / (double) sources.getTotalRequests() * 100;
        percent = ((double) Math.round(percent * 100)) / 100;
//        System.out.println("Процент обработанных заявок: " + percent + "%");
//        System.out.println("Среднее время нахождения заявки в системе: " + ((double) Math.round(averageTime)) / 1000);
//        System.out.println("Среднее время обработки заявки: " + smo.getAverageTimeOfExecutingRequest());
//        System.out.println("Загруженность приборов: " + smo.getDevicesWorkload() + "%");
    }
}
