package main.modelo.garaje.ABMbase;

import main.modelo.Database;
import main.modelo.vehiculos.Moto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AgregarMoto {
    public boolean ejecutar(Moto moto) {
        try (Connection conn = Database.getConnection()) {
            // Primero, insertar en la tabla vehiculo
            String vehiculoQuery = "INSERT INTO vehiculo (color, marca, kilometraje, numero_de_chasis, numero_de_motor, patente) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement vehiculoStmt = conn.prepareStatement(vehiculoQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            vehiculoStmt.setString(1, moto.getColor());
            vehiculoStmt.setString(2, moto.getMarca());
            vehiculoStmt.setInt(3, moto.getKilometraje());
            vehiculoStmt.setString(4, moto.getNumeroChasis());
            vehiculoStmt.setString(5, moto.getNumeroMotor());
            vehiculoStmt.setString(6, moto.getPatente());
            vehiculoStmt.executeUpdate();

            // Obtener el ID generado para el vehiculo
            ResultSet generatedKeys = vehiculoStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idVehiculo = generatedKeys.getInt(1);

                // Luego, insertar en la tabla moto
                String motoQuery = "INSERT INTO moto (cilindrada, cantidad_de_rueda, id_vehiculo) VALUES (?, ?, ?)";
                PreparedStatement motoStmt = conn.prepareStatement(motoQuery);
                motoStmt.setString(1, moto.getCilindrada());
                motoStmt.setInt(2, moto.getCantidadRuedas());
                motoStmt.setInt(3, idVehiculo);
                int rowsAffected = motoStmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al agregar moto: " + e.getMessage());
        }
        return false;
    }
}