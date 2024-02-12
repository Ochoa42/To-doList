package Controllers;

import ClassToList.Alerts;
import ClassToList.TaskDescript;
import Manager.NotificationPanelManager;
import Manager.TaskTableView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Plantilla2Controller implements Initializable {

    @FXML
    private Button Back;
    @FXML
    private Button add;
    @FXML
    private Button ButtonUpdate;
    @FXML
    private Stage currentStage;
    @FXML
    private ComboBox<String> myComboBox2;
    @FXML
    private TableView<TaskDescript> PTableTask;
    @FXML
    private TableColumn<TaskDescript, String> NombreTaskColumn;
    @FXML
    private TableColumn<TaskDescript, String> EstadoTaskColumn;
    @FXML
    private TableColumn<TaskDescript, String> PrioritiTaskColumn;
    @FXML
    private TableColumn<TaskDescript, String> ExpirationdateTaskColum;
    @FXML
    private TableColumn<TaskDescript, String> CreationdateTaskColum;
    private final String[] Status = {"PENDIENTE", "EN PROGRESO", "TERMINADO"};

    @FXML
    Label labelName;

    @FXML
    Button BottomRegister;
    private LoginController loginController;
    private Stage stage;

    private String userLogin;
    private String passwordLogin;
    private Plantilla1Controller plantilla1Controller;
    private int userID;
    private int ListID;
    private String taskName;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getListID() {
        return ListID;
    }

    public void setListID(int listID) {
        ListID = listID;
    }

    public void OnActionUpdate(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Delete.fxml"));
        Parent root = loader.load();
        DeleteController controller = loader.getController();

        TaskDescript selectTableStatus = PTableTask.getSelectionModel().getSelectedItem();
        if (selectTableStatus != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            String NombreTask = selectTableStatus.getCNombreTarea();
            setTaskName(NombreTask);
            String Status = selectTableStatus.getStado();
            String Priority = selectTableStatus.getPrioridad();
            String fechaCreation = selectTableStatus.getFechainicio();
            String fechaExpiration = selectTableStatus.getFechafinal();

            LocalDate fechaInicio = LocalDate.parse(fechaCreation, formatter);
            LocalDate fechaFin = LocalDate.parse(fechaExpiration,formatter);
            String notes = NotificationPanelManager.findTaskNotes(NombreTask, plantilla1Controller.getUserID(), plantilla1Controller.getListTaksID());

            controller.setDateDelete(NombreTask ,notes,fechaInicio,fechaFin);
            Scene scene = new Scene(root);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            controller.init(stage1,this);
            stage1.show();
            setListID(plantilla1Controller.getListTaksID());
            setUserID(plantilla1Controller.getUserID());
            this.stage.close();
        }
        else {
            Alerts.showErrorMessage("Por favor remarque un casillero");
        }
    }

    public void OnActionDelete(ActionEvent event) throws IOException{
        TaskDescript selectTableStatus = PTableTask.getSelectionModel().getSelectedItem();
        if (selectTableStatus != null){
            if (Alerts.showConfirmationAlert("Delete","Esta Seguro de Eliminar esta Tarea")){
                PTableTask.getItems().remove(selectTableStatus);
                NotificationPanelManager.deleteTaskTable(selectTableStatus.getCNombreTarea(), plantilla1Controller.getUserID(), plantilla1Controller.getListTaksID());
                refreshTableData();
            }
        }
    }
    public void TaskOnActionBack(ActionEvent event)throws IOException {
        plantilla1Controller.show();
        stage.close();
    }

    public void comboBox(ActionEvent actionEvent) {
        String selectedStatus = myComboBox2.getValue();
        TaskDescript selectTableStatus = PTableTask.getSelectionModel().getSelectedItem();
        if (selectTableStatus != null ){
            if(Alerts.showConfirmationAlert("Confirmation","Esta Seguro de Cambiar el Estado")){
                selectTableStatus.setStado(selectedStatus);
                NotificationPanelManager.updateOrderStatus(selectedStatus,selectTableStatus.getCNombreTarea(),
                        selectTableStatus.getStado(), plantilla1Controller.getUserID(), plantilla1Controller.getListTaksID());
                refreshTableData();
                //TaskTableView.loadTasksFromList2(plantilla1Controller.getUserID(), plantilla1Controller.getListTaksID());
            }
        }

    }

    public void init(Stage stage1, Plantilla1Controller plantilla1Controller) {
        this.plantilla1Controller = plantilla1Controller;
        this.stage = stage1;
    }

    public void OnActionAddTask(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Description.fxml"));
        Parent root = loader.load();
        DescriptionController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        controller.init(stage1,this);
        stage1.show();
        setListID(plantilla1Controller.getListTaksID());
        setUserID(plantilla1Controller.getUserID());
        this.stage.close();
    }

    public void show() {
        stage.show();
    }


    public void setUserData(int userID, int ListID) {
         this.userID = userID;
         this.ListID = ListID;
    }
    public void refreshTableData() {
        ObservableList<TaskDescript> refreshedData = TaskTableView.loadTasksFromList2(plantilla1Controller.getUserID(), plantilla1Controller.getListTaksID());
        PTableTask.setItems(refreshedData);
    }
    public void initializeTableData(ObservableList<TaskDescript> dataTask) {
        PTableTask.setItems(dataTask);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        myComboBox2.getItems().addAll(Status);
        NombreTaskColumn.setCellValueFactory(data -> data.getValue().getNameProperty());
        EstadoTaskColumn.setCellValueFactory(data -> data.getValue().getStadoProperty());
        PrioritiTaskColumn.setCellValueFactory(data -> data.getValue().getPriorityProperty());
        CreationdateTaskColum.setCellValueFactory(data -> data.getValue().getFechaInicioProperty());
        ExpirationdateTaskColum.setCellValueFactory(data -> data.getValue().getFechaFinalProperty());
        ObservableList<TaskDescript> dataTask = TaskTableView.loadTasksFromList2(userID, ListID);
        System.out.println(userID);
        System.out.println(ListID);
        PTableTask.setItems(dataTask);
    }

}

