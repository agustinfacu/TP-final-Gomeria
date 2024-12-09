package main.modelo.garaje.ABMbase;

import main.modelo.Database;
import main.modelo.vehiculos.Auto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgregarAuto {
    public boolean ejecutar(Auto auto) {
        try (Connection conn = Database.getConnection()) {
            // Primero, insertar en la tabla vehiculo
            String vehiculoQuery = "INSERT INTO vehiculo (color, marca, kilometraje, numero_de_chasis, numero_de_motor, patente) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement vehiculoStmt = conn.prepareStatement(vehiculoQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            vehiculoStmt.setString(1, auto.getColor());
            vehiculoStmt.setString(2, auto.getMarca());
            vehiculoStmt.setInt(3, auto.getKilometraje());
            vehiculoStmt.setString(4, auto.getNumeroChasis());
            vehiculoStmt.setString(5, auto.getNumeroMotor());
            vehiculoStmt.setString(6, auto.getPatente());
            vehiculoStmt.executeUpdate();

            // Obtener el ID generado para el vehiculo
            ResultSet generatedKeys = vehiculoStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idVehiculo = generatedKeys.getInt(1);

                // Luego, insertar en la tabla auto
                String autoQuery = "INSERT INTO auto (cantidad_de_puerta, cantidad_de_rueda, id_vehiculo) VALUES (?, ?, ?)";
                PreparedStatement autoStmt = conn.prepareStatement(autoQuery);
                autoStmt.setInt(1, auto.getCantidadPuertas());
                autoStmt.setInt(2, auto.getCantidadRuedas());
                autoStmt.setInt(3, idVehiculo);
                int rowsAffected = autoStmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al agregar auto: " + e.getMessage());
        }
        return false;
    }
}