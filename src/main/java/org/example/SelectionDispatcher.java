package org.example;

public class SelectionDispatcher {
    private final Integer devicesNumber;
    private Pointers pointers;
    private Integer deviceHeadPointer = 0;
    private Integer deviceTailPointer = 0;

    public SelectionDispatcher(Integer devicesNumber, Pointers pointers) {
        this.devicesNumber = devicesNumber;
        this.pointers = pointers;
    }

    public Request takeRequest() {
        if (pointers.getTailPointer().equals(pointers.getHeadPointer())) {
            return false;
        }
        return ;
    }
}
