package main.modelo.garaje;

import main.modelo.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Estadisticas {
    public double calcularKilometrajeMedio() {
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT AVG(kilometraje) FROM vehiculo";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al calcular el kilometraje medio: " + e.getMessage());
        }
        return 0;
    }
}
