package main.modelo.garaje;

import main.modelo.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Estadisticas {

    // Método para calcular el kilometraje medio
    public double calcularKilometrajeMedio() {
        double kilometrajeTotal = 0;
        int cantidadVehiculos = 0;

        try (Connection conn = Database.getConnection()) {
            String query = "SELECT kilometraje FROM vehiculo";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                kilometrajeTotal += rs.getDouble("kilometraje");
                cantidadVehiculos++;
            }

            if (cantidadVehiculos > 0) {
                return kilometrajeTotal / cantidadVehiculos; // Kilometraje medio
            }
        } catch (SQLException e) {
            System.err.println("Error al calcular el kilometraje medio: " + e.getMessage());
        }
        return 0;
    }

    // Método para calcular la suma total de los kilometrajes
    public double calcularSumaKilometrajes() {
        double kilometrajeTotal = 0;

        try (Connection conn = Database.getConnection()) {
            String query = "SELECT kilometraje FROM vehiculo";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                kilometrajeTotal += rs.getDouble("kilometraje");
            }
        } catch (SQLException e) {
            System.err.println("Error al calcular la suma de los kilometrajes: " + e.getMessage());
        }
        return kilometrajeTotal;
    }
}
