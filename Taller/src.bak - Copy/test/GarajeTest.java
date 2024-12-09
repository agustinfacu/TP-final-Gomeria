package test;

import main.modelo.Database;
import main.modelo.garaje.CapacidadMaxima;
import main.modelo.garaje.Estadisticas;
import main.modelo.garaje.Precios;
import main.modelo.vehiculos.Auto;
import main.modelo.vehiculos.Moto;
import main.modelo.vehiculos.Vehiculo;
import main.vista.VistaGomeria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GarajeTest {

    private List<Vehiculo> vehiculos;
    private CapacidadMaxima capacidadMaxima;
    private Precios precios;
    private Estadisticas estadisticas;
    private double totalCobrado;

    public GarajeTest() {
        this.vehiculos = new ArrayList<>();
        this.estadisticas = new Estadisticas();
        this.totalCobrado = 0;
    }

    public void iniciar() {
        configurarGaraje();
        boolean continuar = true;
        while (continuar) {
            int opcion = mostrarMenuPrincipal();

            switch (opcion) {
                case 0:
                    importarVehiculosDesdeBaseDeDatos();
                    break;
                case 1:
                    consultarVehiculos();
                    break;
                case 2:
                    mostrarCaja();
                    break;
                case 3:
                    mostrarEstadisticas();
                    break;
                default:
                    continuar = false;
                    break;
            }
        }
    }

    private int mostrarMenuPrincipal() {
        String[] options = {"Importar Vehículos", "Consultar Vehículos", "Caja", "Estadísticas", "Salir"};
        return JOptionPane.showOptionDialog(null,
                "Gomería Don Tejero\nFecha: " + java.time.LocalDate.now() +
                        "\nPrecio cambio neumáticos (Autos): " + precios.getPrecioAuto() +
                        "\nPrecio cambio neumáticos (Motos): " + precios.getPrecioMoto() +
                        "\nCapacidad máxima: " + capacidadMaxima.capacidadMaxima +
                        "\nCapacidad actual: " + vehiculos.size(),
                "Menú Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);
    }

    private void configurarGaraje() {
        VistaGomeria vistaGomeria = new VistaGomeria();
        int capacidadMaximaValor = vistaGomeria.mostrarMenuInicial();
        double[] preciosValores = vistaGomeria.solicitarPrecios();
        this.capacidadMaxima = new CapacidadMaxima(capacidadMaximaValor);
        this.precios = new Precios(preciosValores[0], preciosValores[1]);
    }

    private void importarVehiculosDesdeBaseDeDatos() {
        vehiculos.clear(); // Limpiar la lista antes de importar
        try (Connection conn = Database.getConnection()) {
            // Importar autos
            String autoQuery = "SELECT v.id_vehiculo, v.color, v.marca, v.kilometraje, v.numero_de_chasis, v.numero_de_motor, v.patente, a.cantidad_de_puerta, a.cantidad_de_rueda " +
                    "FROM vehiculo v JOIN auto_de_prueba a ON v.id_vehiculo = a.id_vehiculo";
            try (PreparedStatement stmt = conn.prepareStatement(autoQuery);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Auto auto = new Auto(
                            rs.getInt("id_vehiculo"),
                            rs.getString("color"),
                            rs.getString("marca"),
                            rs.getString("patente"),
                            rs.getInt("kilometraje"),
                            rs.getString("numero_de_chasis"),
                            rs.getString("numero_de_motor"),
                            rs.getInt("cantidad_de_puerta"),
                            rs.getInt("cantidad_de_rueda")
                    );
                    vehiculos.add(auto);
                }
            }

            // Importar motos
            String motoQuery = "SELECT v.id_vehiculo, v.color, v.marca, v.kilometraje, v.numero_de_chasis, v.numero_de_motor, v.patente, m.cilindrada, m.cantidad_de_rueda " +
                    "FROM vehiculo v JOIN moto_de_prueba m ON v.id_vehiculo = m.id_vehiculo";
            try (PreparedStatement stmt = conn.prepareStatement(motoQuery);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Moto moto = new Moto(
                            rs.getInt("id_vehiculo"),
                            rs.getString("color"),
                            rs.getString("marca"),
                            rs.getInt("kilometraje"),
                            rs.getString("patente"),
                            rs.getString("numero_de_chasis"),
                            rs.getString("numero_de_motor"),
                            rs.getString("cilindrada"),
                            rs.getInt("cantidad_de_rueda")
                    );
                    vehiculos.add(moto);
                }
            }

            JOptionPane.showMessageDialog(null, "Vehículos importados exitosamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al importar vehículos: " + e.getMessage());
        }
    }

    private void consultarVehiculos() {
        if (vehiculos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay vehículos para mostrar.");
            return;
        }

        String[] columnNames = {"ID", "Tipo", "Color", "Marca", "Patente", "Kilometraje", "Chasis", "Motor", "Cilindrada/Puertas", "Ruedas"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo instanceof Auto) {
                Auto auto = (Auto) vehiculo;
                model.addRow(new Object[]{
                        auto.getId(),
                        "Auto",
                        auto.getColor(),
                        auto.getMarca(),
                        auto.getPatente(),
                        auto.getKilometraje(),
                        auto.getNumeroChasis(),
                        auto.getNumeroMotor(),
                        auto.getCantidadPuertas(),
                        auto.getCantidadRuedas()
                });
            } else if (vehiculo instanceof Moto) {
                Moto moto = (Moto) vehiculo;
                model.addRow(new Object[]{
                        moto.getId(),
                        "Moto",
                        moto.getColor(),
                        moto.getMarca(),
                        moto.getPatente(),
                        moto.getKilometraje(),
                        moto.getNumeroChasis(),
                        moto.getNumeroMotor(),
                        moto.getCilindrada(),
                        moto.getCantidadRuedas()
                });
            }
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JOptionPane.showMessageDialog(null, scrollPane, "Vehículos en el Garaje", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarCaja() {
        if (vehiculos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay vehículos en el garaje.");
            return;
        }

        String[] columnNames = {"ID", "Patente", "Ruedas", "Costo"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        double totalCosto = 0;

        for (Vehiculo vehiculo : vehiculos) {
            int cantidadRuedas;
            double costo;
            if (vehiculo instanceof Auto) {
                Auto auto = (Auto) vehiculo;
                cantidadRuedas = auto.getCantidadRuedas();
                costo = cantidadRuedas * precios.getPrecioAuto();
            } else {
                Moto moto = (Moto) vehiculo;
                cantidadRuedas = moto.getCantidadRuedas();
                costo = cantidadRuedas * precios.getPrecioMoto();
            }
            totalCosto += costo;
            model.addRow(new Object[]{
                    vehiculo.getId(),
                    vehiculo.getPatente(),
                    cantidadRuedas,
                    costo
            });
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Crear un panel para mostrar el total
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel totalLabel = new JLabel("TOTAL: $" + totalCosto);
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(totalLabel);
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Caja", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarEstadisticas() {
        double kilometrajeMedio = estadisticas.calcularKilometrajeMedio();
        JOptionPane.showMessageDialog(null, "Kilometraje medio de todos los vehículos en el garaje: " + kilometrajeMedio);
    }

    public static void main(String[] args) {
        GarajeTest test = new GarajeTest();
        test.iniciar();
    }
}