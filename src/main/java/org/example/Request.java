package org.example;

public class Request {
    private final Integer id;
    private final Integer executingTime;
    private static Integer idCounter = 1;

    public Request() {
        this.id = generateId();
        this.executingTime = (int) ((Math.random() * 3 + 2) * 1000);
    }

    private static Integer generateId() {
        return idCounter++;
    }

    public Integer getId() {
        return id;
    }

    public Integer getExecutingTime() {
        return executingTime;
    }
}
