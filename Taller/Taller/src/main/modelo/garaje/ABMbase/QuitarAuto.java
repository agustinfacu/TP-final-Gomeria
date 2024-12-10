package main.modelo.garaje.ABMbase;

import main.modelo.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuitarAuto {
    public boolean ejecutar(int idVehiculo) {
        try (Connection conn = Database.getConnection()) {
            // Primero, eliminar de la tabla auto
            String autoQuery = "DELETE FROM auto WHERE id_vehiculo = ?";
            PreparedStatement autoStmt = conn.prepareStatement(autoQuery);
            autoStmt.setInt(1, idVehiculo);
            autoStmt.executeUpdate();

            // Luego, eliminar de la tabla vehiculo
            String vehiculoQuery = "DELETE FROM vehiculo WHERE id_vehiculo = ?";
            PreparedStatement vehiculoStmt = conn.prepareStatement(vehiculoQuery);
            vehiculoStmt.setInt(1, idVehiculo);
            int rowsAffected = vehiculoStmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al quitar auto: " + e.getMessage());
        }
        return false;
    }
}