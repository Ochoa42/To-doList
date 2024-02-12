package Controllers;
import ClassToList.Alerts;
import ClassToList.Users;
import Controllers.LoginController;
import DataBase.DataBaseConnection;
import java.io.IOException;

import Manager.NotificationPanelManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;


public class RegisterController{

    @FXML
    private TextField TxtName;
    @FXML
    private TextField TxtEmail;
    @FXML
    private PasswordField TxtPassword;
    @FXML
    private PasswordField TxtConfirPassword;
    @FXML
    private Button Back;
    private Users users;
    private Stage stage;

    private LoginController loginController;

    @Override
    public String toString() {
        return users.getUsersName();

    }

    public void OnActionCreate(ActionEvent event)  {
        boolean seRepite;
        if (TxtName.getText().isEmpty() || TxtEmail.getText().isEmpty() || TxtPassword.getText().isEmpty()
        || TxtConfirPassword.getText().isEmpty()){
            Alerts.showErrorMessage("Por Favor Llene todo los Campos");
        } else if (TxtPassword.getText().equals(TxtConfirPassword.getText())) {
            seRepite = NotificationPanelManager.usuarioRepetido(TxtName.getText());
            if (seRepite){
                Alerts.showErrorMessage("El usuario ya Existe");
            }else {
                Users users1 = new Users(TxtName.getText(),TxtEmail.getText(),TxtPassword.getText());
                users1.crear(DataBaseConnection.getConnection());
                users1.leerTodos(DataBaseConnection.getConnection());
                Alerts.showAlert(Alert.AlertType.INFORMATION,"Usuario Creado Exitosamente","El usuario ha sido creado de forma exitosa.");
            }
        }else {
            Alerts.showErrorMessage("La Contrasena No Coincide");
        }
    }
    @FXML
    public void OnActionBack(ActionEvent event) throws IOException {
        loginController.show();
        stage.close();
    }


    public void init(Stage stage1, LoginController loginController) {
        this.loginController = loginController;
        this.stage = stage1;
    }



}
