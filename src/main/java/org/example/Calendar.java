package org.example;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private static Integer idCounter = 1;
    private final Long startTime;
    private Sources sources;
    private Smo smo;
    private List<Item> items;
    private Integer index = 0;

    public Calendar() {
        startTime = System.currentTimeMillis();
    }

    public void set(Sources sources, Smo smo) {
        this.smo = smo;
        this.sources = sources;
        this.items = new ArrayList<>();
    }

    public void setEventRequest(Request request) {
        items.add(new Item(smo.getNumberOfElementsInQueue(), "Поступила заявка", (((double) request.getStartTime() - startTime) / 1000), request.getSourceId(), -1, request.getId()));
//        Boolean status = smo.isDevicesIdle();
//        System.out.println("Поступила заявка:");
//        System.out.println("Событие №    Время     Источник №   Заявка №    Приборы");
//        System.out.println("    " + idCounter++ + "        " + (((double) request.getStartTime() - startTime) / 1000) + "        " + request.getSourceId() + "           " + request.getId() + "          " + status);
//        System.out.println("Заявок в буфере: " + smo.getNumberOfElementsInQueue());
//        System.out.println("-------------------------------------------------");
    }

    public void setEventDeviceRelease(Device device,  Long time) {
        Integer bufferSize = smo.getNumberOfElementsInQueue();
        items.add(new Item(bufferSize, "Освободился прибор", (((double) time - startTime) / 1000), -1, device.getId(), device.getRequestId()));
//        System.out.println("Прибор освободился:");
//        System.out.println("Событие №    Время     Прибор №     Заявка №");
//        System.out.println("    " + idCounter++ + "        " + (((double) time - startTime) / 1000) + "        " + device.getId() + "           " + device.getRequestId());
//        System.out.println("Заявок в буфере: " + smo.getNumberOfElementsInQueue());
//        System.out.println("-------------------------------------------------");
    }

    public void getStepInfo() {
        if (items.size() > index) {
            System.out.println(items.get(index).getTitle());
            System.out.println("Событие №    Время     Источник №   Прибор №   Заявка №");
            System.out.println("    " + items.get(index).getId() + "        " + items.get(index).getTime() + "         " + items.get(index).getSourceId() + "           " + items.get(index).getDeviceId() + "         " + items.get(index).getRequestId());
            System.out.println("Заявок в буфере: " + items.get(index).getBufferSize());
            System.out.println("-------------------------------------");
            ++index;
        } else {
         System.out.println("Подождите");
        }
    }
}
