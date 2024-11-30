package org.example.sources;

public class Request {
    private static Integer idCounter = 1;

    private final Integer sourceId;
    private final Integer id;
    private final Integer executingTime;
    private final Long startTime;
    private Long timeInSystem;
    private Boolean isRejected = false;

    public Request(Integer sourceId, Double minTime, Double maxTime) {
        this.id = generateId();
        this.sourceId = sourceId;
        this.executingTime = (int) ((Math.random() * (maxTime - minTime) + minTime) * 1000);
        startTime = System.currentTimeMillis();
    }

    public void setTimeInSystem() {
        Long endTime = System.currentTimeMillis();
        timeInSystem = endTime - startTime;
    }

    public Long getTimeInSystem() {
        return timeInSystem;
    }

    private static Integer generateId() {
        return idCounter++;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getExecutingTime() {
        return executingTime;
    }

    public Boolean getIsRejected() {
        return isRejected;
    }

    public void setIsRejected() {
        isRejected = true;
    }
}
