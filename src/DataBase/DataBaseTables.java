package DataBase;

import Manager.NotificationPanelManager;
import Utils.PasswordMysql;
import Utils.UserMysql;
import java.sql.*;

public class DataBaseTables{
    public DataBaseTables(){
        createDataBase();
        createUsersTable();
        createListTable();
        createTaskTable();
        createHistoryTable();
        try {
            if (!NotificationPanelManager.usuarioRepetido("Aldo")){
                insertValueUser();
            }
            insertValuesInList();
        }catch (Exception e){
            System.out.println("Los Valores ya han sido insertados");
        }

    }

    private void executeQuery(String tableQuery) {
        try {
            PreparedStatement preparedStatement = DataBaseConnection.getConnection().prepareStatement(tableQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createDataBase(){
        String createDataBase = "Create database if not exists ToDoList;";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/",
                    UserMysql.userDataBase, PasswordMysql.passwordDataBase);
            PreparedStatement preparedStatement = connection.prepareStatement(createDataBase);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        executeQuery(createDataBase);
        String useDataBase = "use ToDoList;";
        executeQuery(useDataBase);
    }
    private void createUsersTable(){
        String UserTable = "CREATE TABLE IF NOT EXISTS Usuario ("
                + "Usuario_ID int NOT NULL AUTO_INCREMENT,"
                + "NombreUsuario VARCHAR(25) NOT NULL,"
                + "CorreoElectronico VARCHAR(50),"
                + "Contrasena VARCHAR(25),"
                + "PRIMARY KEY (Usuario_ID)"
                + ")";
        executeQuery(UserTable);
    }
    private void createListTable(){
        String ListTable = "CREATE TABLE IF NOT EXISTS Lista ("
                + "Lista_ID int NOT NULL,"
                + "Usuario_ID int NOT NULL,"
                + "NombreLista VARCHAR(25) NOT NULL,"
                + "PRIMARY KEY (Lista_ID),"
                + "FOREIGN KEY (Usuario_ID) REFERENCES Usuario(Usuario_ID)"
                + ")";
        executeQuery(ListTable);
    }
    private void createTaskTable(){
        String TaskTable = "CREATE TABLE IF NOT EXISTS Tarea ("
                + "Tarea_ID int NOT NULL AUTO_INCREMENT,"
                + "Usuario_ID int NOT NULL,"
                + "Lista_ID int NOT NULL,"
                + "NombreTarea VARCHAR(25) NOT NULL,"
                + "FechaCreacion VARCHAR(50),"
                + "FechaVencimiento VARCHAR(50),"
                + "Estado VARCHAR(25),"
                + "Prioridad VARCHAR(25),"
                + "Notas VARCHAR(1000),"
                + "PRIMARY KEY (Tarea_ID),"
                + "FOREIGN KEY (Lista_ID) REFERENCES Lista(Lista_ID),"
                + "FOREIGN KEY (Usuario_ID) REFERENCES Usuario(Usuario_ID)"
                + ")";
        executeQuery(TaskTable);
    }
    private void createHistoryTable(){
        String HistoryTable = "CREATE TABLE IF NOT EXISTS Historial ("
                + "Historial_ID int NOT NULL AUTO_INCREMENT,"
                + "Tarea_ID int NOT NULL,"
                + "Usuario_ID int NOT NULL,"
                + "PRIMARY KEY (Historial_ID),"
                + "FOREIGN KEY (Tarea_ID) REFERENCES Tarea(Tarea_ID),"
                + "FOREIGN KEY (Usuario_ID) REFERENCES Usuario(Usuario_ID)"
                + ")";
        executeQuery(HistoryTable);
    }
    private void insertValuesInList(){

        String InsertValues = "INSERT INTO Lista (Lista_ID, Usuario_ID, NombreLista) VALUES"

                + "(1,1, 'TareasTrabajo'),"

                + "(2,1, 'TareasEstudio'),"

                + "(3,1, 'TareasCasa')";

        executeQuery(InsertValues);

    }

    private void insertValueUser(){
        String InsertValueUser = "INSERT INTO usuario (NombreUsuario,CorreoElectronico,Contrasena) VALUES "
              + "('Aldo','aldo@gmail.com','12345')";

        executeQuery(InsertValueUser);
    }
}