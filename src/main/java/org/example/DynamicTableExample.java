package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

public class DynamicTableExample extends Application {

    private static DynamicTable<Device> dynamicTableDevices;
    private static DynamicTable<Source> dynamicTableSource;
    private static DynamicTable<Request> dynamicTableBuffer;

    @Override
    public void start(Stage primaryStage) {
        dynamicTableDevices = new DynamicTable<>();
        dynamicTableDevices.generateColumns(Device.class);

        dynamicTableSource = new DynamicTable<>();
        dynamicTableSource.generateColumns(Source.class);

        dynamicTableBuffer = new DynamicTable<>();
        dynamicTableBuffer.generateColumns(Request.class);

        VBox vbox = new VBox(10,
                dynamicTableSource.getTableView(),
                dynamicTableBuffer.getTableView(),
                dynamicTableDevices.getTableView()
                );

        Scene scene = new Scene(vbox, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sources, Buffer, Devices");
        primaryStage.show();
    }

    public static void addDevices(Devices devices) {
        Platform.runLater(() -> dynamicTableDevices.setData(devices.getDevices()));
    }

    public static void addSources(Sources sources) {
        Platform.runLater(() -> dynamicTableSource.setData(sources.getSources()));
    }

    public static void startBufferUpdater(Buffer buffer) {
        Thread updater = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                    updateBufferTable(buffer);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        updater.setDaemon(true);
        updater.start();
    }

    private static void updateBufferTable(Buffer buffer) {
        Platform.runLater(() -> {
            BlockingQueue<Request> requestQueue = buffer.getRequests();
            List<Request> requestList = requestQueue.stream().collect(Collectors.toList());
            dynamicTableBuffer.setData(requestList);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
