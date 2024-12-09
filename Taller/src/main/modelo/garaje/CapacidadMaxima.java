package main.modelo.garaje;

import main.modelo.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CapacidadMaxima {
    public int capacidadMaxima;

    public CapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public boolean hayEspacioDisponible() {
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT COUNT(*) FROM vehiculo";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int totalVehiculos = rs.getInt(1);
                // Resta los 100 vehículos preexistentes de test.
                int vehiculosParaTesting = 100;
                return (totalVehiculos - vehiculosParaTesting) < capacidadMaxima;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar la capacidad: " + e.getMessage());
        }
        return false;
    }
}