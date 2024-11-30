package org.example.sources;

import org.example.table.DynamicTableExample;
import org.example.queue.RequestsDispatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Sources {
    private List<Source> sources = new ArrayList<>();
    private final Integer totalRequests;
    private final Integer startDelay;
    private AtomicInteger currentRequestsNumber = new AtomicInteger(0);

    public Sources(Integer sourcesNumber, RequestsDispatcher requestsDispatcher, Integer delay, Integer startDelay, Integer totalRequests, Double minRequestTime, Double maxRequestTime) {
        this.totalRequests = totalRequests;
        this.startDelay = startDelay;
        for(int i = 0; i < sourcesNumber; ++i) {
            sources.add(new Source(requestsDispatcher, delay, this, minRequestTime, maxRequestTime));
        }

        DynamicTableExample.addSources(this);
    }

    public void runSources() {
        for(int i = 0; i < sources.size(); ++i) {
            sources.get(i).start();
            try {
                Thread.sleep(startDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Source> getSources() {
        return sources;
    }

    public Boolean incrementCurrentRequestsNumber() {
        if (currentRequestsNumber.getAndIncrement() < totalRequests) {
            return true;
        }
        return false;
    }
}
