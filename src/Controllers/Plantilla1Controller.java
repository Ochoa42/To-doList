package Controllers;

import java.io.IOException;

import ClassToList.TaskDescript;
import Manager.TaskTableView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Plantilla1Controller {

  @FXML
  private Button List;
  @FXML
  private Button LogOut;
  private Stage currentStage;

  private LoginController loginController;
  private Stage stage;

  private String userName;
  private String userPassword;
  private int ListTaksID;
  private int userID;

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public int getListTaksID() {
    return ListTaksID;
  }

  public void setListTaksID(int listTaksID) {
    ListTaksID = listTaksID;
  }

  public void OnActionTasksTrabajo(ActionEvent event) throws IOException {
    setListTaksID(1);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Plantilla2.fxml"));
    Parent root = loader.load();
    Plantilla2Controller controller = loader.getController();
    Scene scene = new Scene(root);
    Stage stage1 = new Stage();
    stage1.setScene(scene);
    controller.init(stage1,this);
    stage1.show();

    controller.initializeTableData(TaskTableView.loadTasksFromList2(userID, getListTaksID()));
    System.out.println("UserID: "+userID);
    System.out.println("UserName: "+userName);
    System.out.println("UserPassword: "+userPassword);
    System.out.println("ListID: "+getListTaksID());
    this.stage.close();

  }
  public void OnActionTasksEstudio(ActionEvent event) throws IOException {
    setListTaksID(2);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Plantilla2.fxml"));
    Parent root = loader.load();
    Plantilla2Controller controller = loader.getController();
    Scene scene = new Scene(root);
    Stage stage1 = new Stage();
    stage1.setScene(scene);
    controller.init(stage1,this);
    stage1.show();
    controller.initializeTableData(TaskTableView.loadTasksFromList2(userID, getListTaksID()));
    System.out.println("UserID: "+userID);
    System.out.println("UserName: "+userName);
    System.out.println("UserPassword: "+userPassword);
    System.out.println("ListID: "+getListTaksID());
    this.stage.close();

  }
  public void OnActionTasksCasa(ActionEvent event) throws IOException {
    setListTaksID(3);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Plantilla2.fxml"));
    Parent root = loader.load();
    Plantilla2Controller controller = loader.getController();
    Scene scene = new Scene(root);
    Stage stage1 = new Stage();
    stage1.setScene(scene);
    controller.init(stage1,this);
    stage1.show();
    controller.initializeTableData(TaskTableView.loadTasksFromList2(userID, getListTaksID()));
    System.out.println("UserID: "+userID);
    System.out.println("UserName: "+userName);
    System.out.println("UserPassword: "+userPassword);
    System.out.println("ListID: "+getListTaksID());
    this.stage.close();

  }
  public void ListOnActionBack(ActionEvent event) throws IOException {
    loginController.show();
    stage.close();
  }

  public void init(Stage stage1, LoginController loginController) {
    this.loginController = loginController;
    this.stage = stage1;
  }

  public void setUserData(String userName, String password,int userID) {
    this.userName = userName;
    this.userPassword = password;
    this.userID = userID;
  }

  public void show() {
    stage.show();
  }
}
