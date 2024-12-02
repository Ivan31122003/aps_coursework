package org.example;

import java.util.List;

public class Calendar {
    private static Integer idCounter = 1;
    private Long startTime;
    private Sources sources;
    private Smo smo;

    public Calendar() {
        startTime = System.currentTimeMillis();
    }

    public void set(Sources sources, Smo smo) {
        this.smo = smo;
        this.sources = sources;

    }

    public void setEventRequest(Request request) {
        Boolean status = smo.isDevicesIdle();
        System.out.println("Поступила заявка:");
        System.out.println("Событие №    Время     Источник №   Заявка №    Приборы");
        System.out.println("    " + idCounter++ + "        " + (((double) request.getStartTime() - startTime) / 1000) + "        " + request.getSourceId() + "           " + request.getId() + "          " + status);
        System.out.println("Заявок в буфере: " + smo.getNumberOfElementsInQueue());
        System.out.println("-------------------------------------------------");
    }

    public void setEventDeviceRelease(Device device,  Long time) {
        System.out.println("Прибор освободился:");
        System.out.println("Событие №    Время     Прибор №     Заявка №");
        System.out.println("    " + idCounter++ + "        " + (((double) time - startTime) / 1000) + "        " + device.getId() + "           " + device.getRequestId());
        System.out.println("Заявок в буфере: " + smo.getNumberOfElementsInQueue());
        System.out.println("-------------------------------------------------");
    }

    public void setEvent(Long time) {
        System.out.println("Особые события      Время      Признак      Заявки      Отказы");
        System.out.println("      И1           " + System.currentTimeMillis() + "    ");
    }
}
