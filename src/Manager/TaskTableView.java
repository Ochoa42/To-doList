package Manager;

import ClassToList.TaskDescript;
import DataBase.DataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskTableView {

    public static ObservableList<TaskDescript> loadTasksFromList2(int UsuarioID,int ListaID) {
        ObservableList<TaskDescript> dataList = FXCollections.observableArrayList();

        try (Connection connection = DataBaseConnection.getConnection()) {
            String sql = "SELECT t.NombreTarea AS Tarea, t.Prioridad, t.Estado, t.FechaCreacion AS FechaInicio, t.FechaVencimiento AS FechaFinal FROM tarea AS t " +
                    "INNER JOIN usuario AS u ON t.Usuario_ID = u.Usuario_ID " +
                    "INNER JOIN lista AS l ON t.Lista_ID = l.Lista_ID " +
                    "WHERE t.Usuario_ID = ? AND t.Lista_ID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,UsuarioID);
            preparedStatement.setInt(2, ListaID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nombreTarea = resultSet.getString("Tarea");
                String estado = resultSet.getString("Estado");
                String prioridad = resultSet.getString("Prioridad");
                String fechaInicio = resultSet.getString("FechaInicio");
                String fechaFinal = resultSet.getString("FechaFinal");
                System.out.println(fechaFinal);
                System.out.println(fechaInicio);
                TaskDescript task = new TaskDescript(nombreTarea, estado, prioridad,fechaInicio,fechaFinal);
                dataList.add(task);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dataList;
    }
}
