package org.example;

public class Statistics {
    private Integer averageTimeOfRequestInSystem = 0;
    private Integer averageTimeOfDevicesIdle = 0;
    private Integer averageTimeOfDevicesWork = 0;
    private Integer rejectedRequests = 0;
    private Integer allRequests = 0;

    public Statistics() {
        System.out.println("Завершенные/отклоненные: " + (1 - rejectedRequests / allRequests));
        System.out.println("Среднее время нахождения заявки в системе: ");
        System.out.println("Среднее рабочее время приборов: ");
        System.out.println("Среднее время простоя приборов: ");
    }
}
