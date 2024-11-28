package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DynamicTableExample extends Application {

    private static DynamicTable<Device> dynamicTableDevices;
    private static DynamicTable<Source> dynamicTableSource;

    @Override
    public void start(Stage primaryStage) {
        // Таблица устройств
        dynamicTableDevices = new DynamicTable<>();
        dynamicTableDevices.generateColumns(Device.class);

        // Таблица источников
        dynamicTableSource = new DynamicTable<>();
        dynamicTableSource.generateColumns(Source.class);

        // Контейнер для обеих таблиц
        VBox vbox = new VBox(10,
                dynamicTableDevices.getTableView(),
                dynamicTableSource.getTableView()
        );

        Scene scene = new Scene(vbox, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Devices and Sources");
        primaryStage.show();
    }

    public static void addDevices(Devices devices) {
        Platform.runLater(() -> dynamicTableDevices.setData(devices.getDevices()));
    }

    public static void addSources(Sources sources) {
        Platform.runLater(() -> dynamicTableSource.setData(sources.getSources()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
