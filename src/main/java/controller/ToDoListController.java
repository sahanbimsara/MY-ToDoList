package controller;

import DBConnection.DBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.ref.PhantomReference;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ToDoListController implements Initializable {

    @FXML
    private TableView <String> tblCompletedTasks;

    @FXML
    private TextField txtField;

    @FXML
    private ListView <String> txtList;

    @FXML
    private TableColumn <String,String> tblCol;

    private ObservableList<String> tasks = FXCollections.observableArrayList();
    private ObservableList<String> sendDataToTable = FXCollections.observableArrayList();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String newTask = txtField.getText();
        if (!newTask.isEmpty()) {
            tasks.add(newTask);

        }
    }
    @FXML
    public void btnDoneOnAction(ActionEvent actionEvent) {
        String selectedTask = txtList.getSelectionModel().getSelectedItem().toString();
        if (selectedTask != null) {
            tasks.remove(selectedTask);
            sendDataToTable.add(selectedTask);
            insertTaskToDB(selectedTask);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtList.setItems(tasks);
        //Chat Gpt Genat=reted
         //

        tblCompletedTasks.setItems(sendDataToTable);
    }

    private void insertTaskToDB(String task) {
        String sql = "INSERT INTO tasks (description) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, task);
            pstm.executeUpdate();

        } catch (SQLException e) {
        }
    }

}