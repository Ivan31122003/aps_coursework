package org.example;

public class Item {
    private static Integer idCounter = 1;
    private final Integer bufferSize;
    private final String title;
    private final Integer id;
    private final Double time;
    private final Integer sourceId;
    private final Integer deviceId;
    private final Integer requestId;
    private final Boolean isRejected;

    public Item(Integer bufferSize, String title, Double time, Integer sourceId, Integer deviceId, Integer requestId, Boolean isRejected) {
        this.bufferSize = bufferSize;
        this.title = title;
        id = idCounter++;
        this.time = time;
        this.sourceId = sourceId;
        this.deviceId = deviceId;
        this.requestId = requestId;
        this.isRejected = isRejected;
    }

    public String getTitle() {
        return title;
    }

    public Integer getId() {
        return id;
    }

    public Double getTime() {
        return time;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public Integer getBufferSize() {
        return bufferSize;
    }

    public Boolean getIsRejected() {
        return isRejected;
    }
}
