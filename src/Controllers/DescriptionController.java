package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


import ClassToList.Alerts;
import DataBase.DataBaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class DescriptionController implements Initializable {

  @FXML
  private Button back;
  @FXML
  private TextField TaskName;
  @FXML
  private DatePicker TaskFechaInicio;
  @FXML
  private DatePicker TaskFechaFinal;
  @FXML
  private TextField TaskNotes;

  @FXML
  private ComboBox myComboBox;
  private final String[] Priority = {"MEDIA", "ALTA", "BAJA"}; //hicimos este pequeno cambio = ojito

  private int UserID;
  private int ListID;

  public int getUserID() {
    return UserID;
  }

  public void setUserID(int userID) {
    UserID = userID;
  }

  public int getListID() {
    return ListID;
  }

  public void setListID(int listID) {
    ListID = listID;
  }

  private Stage currentStage;

  private Plantilla2Controller plantilla2Controller;
  private LoginController loginController;
  private Stage stage;

  public void OnActionBack(ActionEvent event) throws IOException {
    plantilla2Controller.show();
    System.out.println(plantilla2Controller.getUserID());
    System.out.println(plantilla2Controller.getListID());
    stage.close();

  }

  public void init(Stage stage1, Plantilla2Controller plantilla2Controller) {
    this.plantilla2Controller = plantilla2Controller;
    this.stage = stage1;

  }

  public void OnActionTaskCreate (ActionEvent event) throws IOException{
    Connection connection = DataBaseConnection.getConnection();

    if (connection != null) {
      try {
        String sql = "INSERT INTO Tarea (Lista_ID, Usuario_ID, NombreTarea, FechaCreacion, FechaVencimiento, Estado, Prioridad, Notas) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        String nombreTarea = TaskName.getText();
        String fechaCreacion = TaskFechaInicio.getValue().toString();
        String fechaVencimiento = TaskFechaFinal.getValue().toString();
        String estado = null;
        String prioridad = (String) myComboBox.getSelectionModel().getSelectedItem();
        String notas = TaskNotes.getText();
        insertarTarea(preparedStatement, plantilla2Controller.getListID(), plantilla2Controller.getUserID(), nombreTarea, fechaCreacion, fechaVencimiento, estado, prioridad, notas);
        Alerts.showAlert(Alert.AlertType.INFORMATION,"Implementar Tarea","Se ha adicionado una nueva Tarea");
        plantilla2Controller.refreshTableData();
        preparedStatement.close();
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

  }

  private void insertarTarea(PreparedStatement preparedStatement, int listaID,int userID, String nombreTarea, String fechaCreacion, String fechaVencimiento, String estado, String prioridad, String notas) throws SQLException {
    preparedStatement.setInt(1, listaID);
    preparedStatement.setInt(2, userID);
    preparedStatement.setString(3, nombreTarea);
    preparedStatement.setString(4, fechaCreacion);
    preparedStatement.setString(5, fechaVencimiento);
    preparedStatement.setString(6, estado);
    preparedStatement.setString(7, prioridad);
    preparedStatement.setString(8, notas);
    preparedStatement.executeUpdate();
  }
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle){
    myComboBox.getItems().addAll(Priority);
  }

}

