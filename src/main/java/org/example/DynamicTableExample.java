package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DynamicTableExample extends Application {

    private static DynamicTable<Device> dynamicTable;

    @Override
    public void start(Stage primaryStage) {
        dynamicTable = new DynamicTable<>();
        dynamicTable.generateColumns(Device.class);

        VBox vbox = new VBox(dynamicTable.getTableView());
        Scene scene = new Scene(vbox, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Devices");
        primaryStage.show();
    }

    public static void addDevice(Device device) {
        Platform.runLater(() -> dynamicTable.setData(Device.getDevices()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
