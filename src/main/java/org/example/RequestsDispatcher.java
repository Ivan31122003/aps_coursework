package org.example;

public class RequestsDispatcher {
    private final Integer capacity;
    private Buffer buffer;
    private Pointers pointers;


    public RequestsDispatcher(Integer capacity, Pointers pointers, Buffer buffer) {
        this.capacity = --capacity;
        this.pointers = pointers;
        this.buffer = buffer;
    }

    public Boolean putRequest(Request request) {
        if (pointers.getHeadPointer() == null) {
            pointers.setState();
        }
        else {
            pointers.incrementHeadPointer();
            if (pointers.getHeadPointer().equals(pointers.getTailPointer())){
                pointers.decrementHeadPointer();
                return false;
            }
        }

        buffer.put(pointers.getHeadPointer(), request);
        return true;
    }
}
