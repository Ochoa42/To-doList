package com.example.tolist;

import Controllers.LoginController;
import DataBase.DataBaseTables;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {

  @Override

  public void start(Stage primaryStage) throws IOException {

      DataBaseTables Created = new DataBaseTables();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Login.fxml"));
      //URL fxmlUrl = getClass().getResource("/Views/Login.fxml");
      Parent root = loader.load();
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      LoginController controller = loader.getController();
      controller.setStage(primaryStage);
      primaryStage.show();

      primaryStage.setTitle("");
  }
    public static void main(String[] args){
        launch(args);
    }


}