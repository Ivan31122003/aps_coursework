package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

public class DynamicTable<T> {

    private final TableView<T> tableView;
    private final ObservableList<T> data;

    public DynamicTable() {
        this.tableView = new TableView<>();
        this.data = FXCollections.observableArrayList();
        this.tableView.setItems(data);
    }

    public void generateColumns(Class<T> clazz) {
        tableView.getColumns().clear();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            TableColumn<T, ?> column = new TableColumn<>(field.getName());
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            tableView.getColumns().add(column);
        }
    }

    public void setData(List<T> items) {
        data.clear();
        data.addAll(items);
    }

    public TableView<T> getTableView() {
        return tableView;
    }
}
