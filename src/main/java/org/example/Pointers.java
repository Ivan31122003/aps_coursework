package org.example;

public class Pointers {
    private final Integer capacity;
    private Integer headPointer = null;
    private Integer tailPointer = null;

    public Pointers(Integer capacity){
        this.capacity = capacity;
    };

    public void setState() {
        headPointer = 0;
        tailPointer = 0;
    }

    public void resetState() {
        headPointer = null;
        tailPointer = null;
    }

    public void incrementHeadPointer() {
        ++headPointer;
        if (headPointer.equals(capacity + 1)) {
            headPointer = 0;
        }
    }

    public void incrementTailPointer() {
        ++tailPointer;
        if (tailPointer.equals((capacity + 1))) {
            tailPointer = 0;
        }
    }

    public void decrementHeadPointer() {
        --headPointer;
        if (headPointer == -1) {
            headPointer = capacity;
        }
    }

    public void decrementTailPointer() {
        --tailPointer;
        if (tailPointer == -1) {
            tailPointer = capacity;
        }
    }

    public Integer getHeadPointer() {
        return headPointer;
    }

    public Integer getTailPointer() {
        return tailPointer;
    }
}
