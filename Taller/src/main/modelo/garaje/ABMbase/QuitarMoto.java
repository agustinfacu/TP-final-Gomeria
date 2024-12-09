package main.modelo.garaje.ABMbase;

import main.modelo.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuitarMoto {
    public boolean ejecutar(int idVehiculo) {
        try (Connection conn = Database.getConnection()) {
            // Primero, eliminar de la tabla moto
            String motoQuery = "DELETE FROM moto WHERE id_vehiculo = ?";
            PreparedStatement motoStmt = conn.prepareStatement(motoQuery);
            motoStmt.setInt(1, idVehiculo);
            motoStmt.executeUpdate();

            // Luego, eliminar de la tabla vehiculo
            String vehiculoQuery = "DELETE FROM vehiculo WHERE id_vehiculo = ?";
            PreparedStatement vehiculoStmt = conn.prepareStatement(vehiculoQuery);
            vehiculoStmt.setInt(1, idVehiculo);
            int rowsAffected = vehiculoStmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al quitar moto: " + e.getMessage());
        }
        return false;
    }
}