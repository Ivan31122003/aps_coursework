public class BufferDispatcher {
    private Integer headPointer;
    private Integer tailPointer;
    private final Integer capacity;
    private Buffer buffer;
    
    public BufferDispatcher(Integer capacity, Buffer buffer) {
        this.capacity = capacity;
        this.buffer = buffer;
        headPointer = 1;
        tailPointer = 1;
    }

    public Boolean pushIntoBuffer(Application application) {
        headPointer = capacity.equals(headPointer + 1) ? 1 : headPointer + 1;
        if (headPointer == tailPointer) {
            --headPointer;
            return false;
        }
        buffer.put(headPointer, application);
        return true;
    }
}