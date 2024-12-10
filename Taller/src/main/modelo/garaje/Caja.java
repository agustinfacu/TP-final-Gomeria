package main.modelo.garaje;

import main.modelo.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Caja {
    private double precioAuto; // Precio para cambio de neumáticos de autos
    private double precioMoto; // Precio para cambio de neumáticos de motos

    public Caja(double precioAuto, double precioMoto) {
        this.precioAuto = precioAuto;
        this.precioMoto = precioMoto;
    }

    public double getPrecioAuto() {
        return precioAuto;
    }

    public double getPrecioMoto() {
        return precioMoto;
    }

    public double calcularTotal() {
        double total = 0;
        try (Connection conn = Database.getConnection()) {
            // Consulta para sumar las ruedas de autos
            String queryAuto = "SELECT SUM(cantidad_de_rueda) FROM auto";
            PreparedStatement stmtAuto = conn.prepareStatement(queryAuto);
            ResultSet rsAuto = stmtAuto.executeQuery();
            if (rsAuto.next()) {
                total += rsAuto.getInt(1) * precioAuto;
            }

            // Consulta para sumar las ruedas de motos
            String queryMoto = "SELECT SUM(cantidad_de_rueda) FROM moto";
            PreparedStatement stmtMoto = conn.prepareStatement(queryMoto);
            ResultSet rsMoto = stmtMoto.executeQuery();
            if (rsMoto.next()) {
                total += rsMoto.getInt(1) * precioMoto;
            }
        } catch (SQLException e) {
            System.err.println("Error al calcular el precio del cambio de ruedas: " + e.getMessage());
        }
        return total;
    }
}
