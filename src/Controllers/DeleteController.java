package Controllers;

import ClassToList.Alerts;
import Manager.NotificationPanelManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class DeleteController implements Initializable{

    private Plantilla2Controller plantilla2Controller;
    private Stage stage;
    @FXML
    private TextField TaskNameUpd;
    @FXML
    private TextField TaskNotesUpd;
    @FXML
    private DatePicker TaskFechaInicioUpd;
    @FXML
    private DatePicker TaskFechaFinalUpd;
    @FXML
    private ComboBox myComboBoxPriority;
    @FXML
    private ComboBox myComboBoxStatus;

    private final String[] Status = {"PENDIENTE", "EN PROGRESO", "TERMINADO"};
    private final String[] Priority = {"MEDIA", "ALTA", "BAJA"};

    public void init(Stage stage1, Plantilla2Controller plantilla2Controller) {
        this.plantilla2Controller = plantilla2Controller;
        this.stage = stage1;

    }

    public void setDateDelete(String TaskName, String Notes, LocalDate fechaFin, LocalDate fechaInicio){
            TaskNameUpd.setText(TaskName);
            TaskNotesUpd.setText(Notes);
            TaskFechaInicioUpd.setValue(fechaInicio);
            TaskFechaFinalUpd.setValue(fechaFin);


    }
    public void OnActionBackUpdate(ActionEvent event) throws IOException {
        plantilla2Controller.show();
        stage.close();

    }

    public void OnActionTaskUpdate(ActionEvent event) throws IOException {

        String newPriority = (String) myComboBoxPriority.getSelectionModel().getSelectedItem();
        String newStatus = (String) myComboBoxStatus.getSelectionModel().getSelectedItem();
        if (newPriority != null && newStatus != null){
            int userID= plantilla2Controller.getUserID();
            int listID= plantilla2Controller.getListID();
            String task = plantilla2Controller.getTaskName();
            String newnotas = TaskNotesUpd.getText();
            String newName = TaskNameUpd.getText();
            String newDateInicio=TaskFechaInicioUpd.getValue().toString();
            String newDateFin = TaskFechaFinalUpd.getValue().toString();
            NotificationPanelManager.updateTask(userID,listID,task,newnotas,newDateInicio,newDateFin,newName,newPriority,newStatus);
            Alerts.showAlert(Alert.AlertType.INFORMATION,"Actualizar Tarea","Se ha actualizado la tarea Correctamente");
            plantilla2Controller.refreshTableData();
        }else {
            Alerts.showErrorMessage("Seleccione la Prioridad y el Estado");
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myComboBoxPriority.getItems().addAll(Priority);
        myComboBoxStatus.getItems().addAll(Status);
    }

}

