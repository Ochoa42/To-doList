package ClassToList;

import DataBase.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {
    private String UsersFullName;
    private String Email;
    private String Password;

     int state = -1;

    public Users(String usersName, String email, String password) {
        UsersFullName = usersName;
        Email = email;
        Password = password;
    }
    public Users(){}

    public String getUsersName() {
        return UsersFullName;
    }

    public void setUsersName(String usersName) {
        UsersFullName = usersName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void leerTodos(Connection conexion) {
        try {
            String query = "SELECT * FROM Usuario";
            PreparedStatement statement = conexion.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("Usuario_ID");
                String nombre = resultSet.getString("NombreUsuario");
                String Email = resultSet.getString("CorreoElectronico");
                String password = resultSet.getString("Contrasena");
                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Email: " + Email);
                System.out.println("Password: " + password);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer estudiantes");
            e.printStackTrace();
        }
    }

    public void crear(Connection conexion) {
        try {
            String query = "INSERT INTO Usuario (NombreUsuario, CorreoElectronico, Contrasena) VALUES (?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, this.UsersFullName);
            statement.setString(2, this.Email);
            statement.setString(3, this.Password);
            statement.executeUpdate();

            System.out.println("Estudiante creado con éxito");
        } catch (SQLException e) {
            System.err.println("Error al crear estudiante");
            e.printStackTrace();
        }
    }

    public int ValidarUser(Connection connection,String Users,String Password){
        try {
            if (connection!=null){
                String sql = "SELECT * FROM Usuario WHERE BINARY NombreUsuario=? AND Contrasena=?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1,Users);
                statement.setString(2,Password);

                ResultSet rs = statement.executeQuery();
                if (rs.next()){
                    state = 1;
                }else {
                    state= 0;
                }
            }else {
                System.out.println("Error Al Conectarse a la base de datos");
            }


        }catch (Exception e){
                System.out.println("Hubo error de ejecucion");
        }
        return state;
    }

    public static void main(String[] args) {

        Users estudentCrud = new Users("Juam","Juan@gmail.com","12345");
        estudentCrud.crear(DataBaseConnection.getConnection());
        estudentCrud.leerTodos(DataBaseConnection.getConnection());
    }



}
