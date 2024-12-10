package main.modelo.garaje.ABM;

import main.modelo.garaje.Caja;
import main.modelo.vehiculos.Auto;
import main.modelo.vehiculos.Moto;
import main.modelo.vehiculos.Vehiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CajaViewer {
    private List<Vehiculo> vehiculos;
    private Caja precios;

    public CajaViewer(List<Vehiculo> vehiculos, Caja precios) {
        this.vehiculos = vehiculos;
        this.precios = precios;
    }

    public void mostrarCaja(JFrame parentFrame) {
        // Calcular el total a cobrar
        double total = 0;
        String[] columnNames = {"ID", "Patente", "Cantidad de Ruedas", "Costo"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Vehiculo vehiculo : vehiculos) {
            int cantidadRuedas = (vehiculo instanceof Auto) ? ((Auto) vehiculo).getCantidadRuedas() : ((Moto) vehiculo).getCantidadRuedas();
            double costo = (vehiculo instanceof Auto) ? precios.getPrecioAuto() * cantidadRuedas : precios.getPrecioMoto() * cantidadRuedas;
            total += costo;
            model.addRow(new Object[]{vehiculo.getId(), vehiculo.getPatente(), cantidadRuedas, formatCurrency(costo)});
        }

        // Crear la tabla
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Crear un panel para contener el total y la tabla
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear un panel para el total
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new BorderLayout());
        JLabel totalLabel = new JLabel("Total a Cobrar: " + formatCurrency(total), SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 20));  // Destacar el total
        totalPanel.add(totalLabel, BorderLayout.CENTER);

        // Añadir el totalPanel y la tabla al panel principal
        panel.add(totalPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Crear un JDialog modal para mostrar la ventana
        JDialog dialog = new JDialog(parentFrame, "Caja de Vehículos", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        dialog.add(panel, BorderLayout.CENTER);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null);  // Centrar la ventana en la pantalla

        // Crear un botón de "Volver"
        JButton volverButton = new JButton("Volver al Menú Principal");
        volverButton.addActionListener(e -> dialog.dispose());

        // Crear un panel para el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(volverButton);

        // Añadir el panel del botón al panel principal
        panel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private String formatCurrency(double amount) {
        // Formato de moneda en AR
        @SuppressWarnings("deprecation")
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
        return currencyFormat.format(amount);
    }
}