package Controllers;

import ClassToList.Alerts;
import ClassToList.Users;
import DataBase.DataBaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {


    private static final String LOGIN_FXML_PATH = "login.fxml";

    @FXML
    private TextField LoginName;
    @FXML
    private PasswordField LoginPassword;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button register;
    private Stage stage;
    private int UserID;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    @FXML
    protected void OnActionLogin(ActionEvent event) throws IOException {
        int state;
        if (!LoginName.getText().isEmpty() && !LoginPassword.getText().isEmpty()) {
            Users users = new Users();
            state = users.ValidarUser(DataBaseConnection.getConnection(), LoginName.getText(), LoginPassword.getText());
            if (state != -1) {
                if (state == 1) {
                    getUserIDFromDatabase(LoginName.getText(),LoginPassword.getText());
                    //Alerts.showErrorMessage("Datos correctos puede ingresar al sistema");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Plantilla1.fxml"));
                    Parent root = loader.load();
                    Plantilla1Controller controller = loader.getController();
                    controller.setUserData(LoginName.getText(), LoginPassword.getText(),getUserID());
                    Scene scene = new Scene(root);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene);
                    controller.init(stage1,this);
                    stage1.show();
                    this.stage.close();
        } else {
          Alerts.showErrorMessage("Error al iniciar Sesion Datos incorrectos");
        }
      }
    } else {
      Alerts.showErrorMessage("Complete los Campos");
    }
  }
    @FXML
    protected void OnActionCreateRegister(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Register.fxml"));
        Parent root = loader.load();
        RegisterController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        controller.init(stage1,this);
        stage1.show();
        this.stage.close();
    }


    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void show() {
        stage.show();
    }

    public void getUserIDFromDatabase(String username, String password) {
        String query = "SELECT Usuario_ID FROM usuario WHERE NombreUsuario = ? AND Contrasena = ?";
        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userID = resultSet.getInt("Usuario_ID");
                System.out.println(userID);
                setUserID(userID);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}