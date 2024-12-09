package main.modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//conexion con la base de datos
public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/Gomeria?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection = null;

    private Database() {
        // Constructor privado para evitar instanciación
    }
    //iniciar conexion
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
    //terminar la conexion
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("¡Conexión exitosa a MySQL!");
        } catch (SQLException e) {
            System.err.println("Error al conectar: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }
}