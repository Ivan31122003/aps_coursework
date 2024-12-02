package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
        Long averageTime = 0L;
        for (Long time : timeOfRequestsInSystem) {
            averageTime += time;
        }
        averageTime /= timeOfRequestsInSystem.size();
        Double percent = (double) timeOfRequestsInSystem.size() / (double) sources.getTotalRequests() * 100;
        percent = (double) (Math.round(percent * 100) / 100);
        System.out.println("Процент обработанных заявок: " + percent + "%");
        System.out.println("Среденее время нахождения заявки в системе: " + averageTime);
    }
}
