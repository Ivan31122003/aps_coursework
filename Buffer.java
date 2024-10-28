import java.util.ArrayList;
import java.util.List;

public class Buffer {
    private int headPointer;
    private int tailPointer;
    private int capacity;
    private List<Application> applicationsArray;

    public Buffer(List<Application> applicationsArray, int capacity) {
        this.applicationsArray = new ArrayList<>();
        this.applicationsArray = List.copyOf(applicationsArray);
        this.capacity = capacity;
        headPointer = 0;
        tailPointer = 0;
    }

    public Boolean push(Application application) {
        return true;
    }

    public Application take() {
        
    }
}