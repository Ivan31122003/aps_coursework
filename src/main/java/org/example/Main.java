package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        new Thread(() -> DynamicTableExample.main(args)).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Calendar calendar = new Calendar();
        System.out.println("Нажмите Enter, чтобы вывести следующую информацию:");
        Thread inputThread = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.println("Нажмите Enter, чтобы вывести информацию:");
                while (true) {
                    String input = reader.readLine(); // Ждет ввода
                    if (input.isEmpty()) { // Если пользователь нажал только Enter
                        calendar.getStepInfo();
                    }
                }
            } catch (IOException e) {
                System.err.println("Ошибка чтения ввода: " + e.getMessage());
            }
        });
        inputThread.start();

        Integer totalRequests = 100;
        Smo smo = new Smo(2, 2, totalRequests, calendar);
        Sources sources = new Sources(2, smo.getRequestsDispatcher(), 2000, 1000, totalRequests, 3.45, 3.55, calendar);
//        Sources sources = new Sources(8, smo.getRequestsDispatcher(), 35, 10, totalRequests, 0.02, 0.05);
        calendar.set(sources, smo);


        smo.runDevices();
        sources.runSources();

        Statistics statistics = new Statistics(sources, smo);
        statistics.start();



    }
}
