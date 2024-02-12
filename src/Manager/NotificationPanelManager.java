package Manager;

import DataBase.DataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationPanelManager {

    public static void updateOrderStatus(String newStatus, String nombreTarea, String StadoActual, int UserID, int ListID) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            String sql = "UPDATE tarea SET Estado = ? WHERE NombreTarea = ? AND Estado = ? AND Usuario_ID = ? AND Lista_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newStatus);
            statement.setString(2, nombreTarea);
            statement.setString(3, StadoActual);
            statement.setInt(4, UserID);
            statement.setInt(5, ListID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void moveOrderToDelivered(String orderId) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            String selectedStatus = getOrderStatus(orderId);
            if (selectedStatus.equals("delivered")) {
                String insertSql = "INSERT INTO delivered_orders (ID_customer, ID_cake, size, status, total, order_date) " +
                        "SELECT ID_customer, ID_cake, size, status, total, order_date FROM orders WHERE order_id = ?";
                PreparedStatement insertStatement = connection.prepareStatement(insertSql);
                insertStatement.setString(1, orderId);
                int rowsInserted = insertStatement.executeUpdate();

                if (rowsInserted > 0) {
                    String deleteSql = "DELETE FROM orders WHERE order_id = ? AND status = 'delivered'";
                    PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
                    deleteStatement.setString(1, orderId);
                    deleteStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String getOrderStatus(String orderId) {
        String status = "";

        try (Connection connection = DataBaseConnection.getConnection()) {
            String sql = "SELECT status FROM orders WHERE order_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                status = resultSet.getString("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }
    public static void deleteTaskTable(String nombreTarea, int usuarioId, int listaId) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            String sql = "DELETE FROM tarea WHERE NombreTarea = ? AND Usuario_ID = ? AND Lista_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nombreTarea);
            statement.setInt(2, usuarioId);
            statement.setInt(3, listaId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String findTaskNotes(String taskName, int userID, int listID) {
        String taskNotes = null;

        Connection connection = DataBaseConnection.getConnection();
        if (connection != null) {
            try {
                String sql = "SELECT Notas FROM Tarea WHERE NombreTarea = ? AND Usuario_ID = ? AND Lista_ID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, taskName);
                preparedStatement.setInt(2, userID);
                preparedStatement.setInt(3, listID);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    taskNotes = resultSet.getString("Notas");
                }

                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return taskNotes;
    }

    public static void updateTask(int userID, int listID, String taskName, String newNotes, String newStartDate,
                           String newEndDate, String newName, String newPriority, String newState) {
        Connection connection = DataBaseConnection.getConnection();
        if (connection != null) {
            try {
                String sql = "UPDATE Tarea " +
                        "SET Notas = ?, " +
                        "FechaCreacion = ?, " +
                        "FechaVencimiento = ?, " +
                        "NombreTarea = ?, " +
                        "Prioridad = ?, " +
                        "Estado = ? " +
                        "WHERE Usuario_ID = ? AND Lista_ID = ? AND NombreTarea = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, newNotes);
                preparedStatement.setString(2, newStartDate);
                preparedStatement.setString(3, newEndDate);
                preparedStatement.setString(4, newName);
                preparedStatement.setString(5, newPriority);
                preparedStatement.setString(6, newState);
                preparedStatement.setInt(7, userID);
                preparedStatement.setInt(8, listID);
                preparedStatement.setString(9, taskName);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Tarea actualizada exitosamente.");
                } else {
                    System.out.println("No se encontró la tarea para actualizar.");
                }

                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean usuarioRepetido(String nombreUsuario) {
            try {
                Connection connection = DataBaseConnection.getConnection();
                String consulta = "SELECT COUNT(*) FROM usuario WHERE NombreUsuario = ?";
                PreparedStatement statement = connection .prepareStatement(consulta);
                statement.setString(1, nombreUsuario);
                ResultSet resultado = statement.executeQuery();
                if (resultado.next()) {
                    int count = resultado.getInt(1);
                    return count > 0;
                }
                resultado.close();
                statement.close();
                connection .close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
    }
}
