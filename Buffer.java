import java.util.ArrayList;
import java.util.List;

public class Buffer {
    private final Integer capacity;
    private List<Application> applicationsArray;

    public Buffer(List<Application> applicationsArray, int capacity) {
        this.applicationsArray = new ArrayList<>();
        this.applicationsArray = List.copyOf(applicationsArray);
        this.capacity = capacity;
    }

    public void put(Integer headPointer, Application application) {
        applicationsArray.set(headPointer, application);
    }

    
}