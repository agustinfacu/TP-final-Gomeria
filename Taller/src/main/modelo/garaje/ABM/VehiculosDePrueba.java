package main.modelo.garaje.ABM;

import main.modelo.Database;
import main.modelo.vehiculos.Auto;
import main.modelo.vehiculos.Moto;
import main.modelo.vehiculos.Vehiculo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculosDePrueba {
    private List<Moto> motos;


    public List<Vehiculo> importarVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        try (Connection conn = Database.getConnection()) {
            // Importar autos de prueba
            String queryAutos = "SELECT v.id_vehiculo, v.color, v.marca, v.kilometraje, v.numero_de_chasis, v.numero_de_motor, v.patente, a.cantidad_de_puerta, a.cantidad_de_rueda " +
                                "FROM vehiculo v JOIN auto_de_prueba a ON v.id_vehiculo = a.id_vehiculo";
            PreparedStatement stmtAutos = conn.prepareStatement(queryAutos);
            ResultSet rsAutos = stmtAutos.executeQuery();
            while (rsAutos.next()) {
                Auto auto = new Auto(
                        rsAutos.getInt("id_vehiculo"),
                        rsAutos.getString("color"),
                        rsAutos.getString("marca"),
                        rsAutos.getString("patente"),
                        rsAutos.getInt("kilometraje"),
                        rsAutos.getString("numero_de_chasis"),
                        rsAutos.getString("numero_de_motor"),
                        rsAutos.getInt("cantidad_de_puerta"),
                        rsAutos.getInt("cantidad_de_rueda")
                );
                vehiculos.add(auto);
            }

            // Importar motos de prueba
            String queryMotos = "SELECT v.id_vehiculo, v.color, v.marca, v.kilometraje, v.numero_de_chasis, v.numero_de_motor, v.patente, m.cilindrada, m.cantidad_de_rueda " +
                                "FROM vehiculo v JOIN moto_de_prueba m ON v.id_vehiculo = m.id_vehiculo";
            PreparedStatement stmtMotos = conn.prepareStatement(queryMotos);
            ResultSet rsMotos = stmtMotos.executeQuery();
            while (rsMotos.next()) {
                Moto moto = new Moto(
                        rsMotos.getInt("id_vehiculo"),
                        rsMotos.getString("color"),
                        rsMotos.getString("marca"),
                        rsMotos.getInt("kilometraje"),
                        rsMotos.getString("numero_de_chasis"),
                        rsMotos.getString("numero_de_motor"),
                        rsMotos.getString("patente"),
                        rsMotos.getString("cilindrada"),
                        rsMotos.getInt("cantidad_de_rueda")
                );
                vehiculos.add(moto);
            }

            JOptionPane.showMessageDialog(null, "Vehículos de prueba importados exitosamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al importar vehículos de prueba: " + e.getMessage());
        }
        return vehiculos;
    }

    public List<Moto> getMotos() {return motos; }
}