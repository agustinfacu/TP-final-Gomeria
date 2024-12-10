package main.modelo.garaje.ABM;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import main.modelo.vehiculos.Auto;
import main.modelo.vehiculos.Moto;
import main.modelo.vehiculos.Vehiculo;

import java.awt.*;
import java.util.List;

public class VehiculoViewer {
    private List<Vehiculo> vehiculos;

    public VehiculoViewer(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public JPanel crearPanelVehiculos(JDialog parentDialog) {
        DefaultTableModel autoModel = new DefaultTableModel(new String[]{
                "ID", "Color", "Marca", "Patente", "Kilometraje",
                "Número de Chasis", "Número de Motor", "Puertas", "Ruedas"
        }, 0);

        DefaultTableModel motoModel = new DefaultTableModel(new String[]{
                "ID", "Color", "Marca", "Patente", "Kilometraje",
                "Número de Chasis", "Número de Motor", "Cilindrada", "Ruedas"
        }, 0);

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo instanceof Auto) {
                Auto auto = (Auto) vehiculo;
                autoModel.addRow(new Object[]{
                        auto.getId(), auto.getColor(), auto.getMarca(), auto.getPatente(),
                        auto.getKilometraje(), auto.getNumeroChasis(), auto.getNumeroMotor(),
                        auto.getCantidadPuertas(), auto.getCantidadRuedas()
                });
            } else if (vehiculo instanceof Moto) {
                Moto moto = (Moto) vehiculo;
                motoModel.addRow(new Object[]{
                        moto.getId(), moto.getColor(), moto.getMarca(), moto.getPatente(),
                        moto.getKilometraje(), moto.getNumeroChasis(), moto.getNumeroMotor(),
                        moto.getCilindrada(), moto.getCantidadRuedas()
                });
            }
        }

        JTable autoTable = new JTable(autoModel);
        JTable motoTable = new JTable(motoModel);

        JPanel autoPanel = new JPanel(new BorderLayout());
        JLabel autoLabel = new JLabel("Autos", SwingConstants.CENTER);
        autoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        autoPanel.add(autoLabel, BorderLayout.NORTH);
        autoPanel.add(new JScrollPane(autoTable), BorderLayout.CENTER);

        JPanel motoPanel = new JPanel(new BorderLayout());
        JLabel motoLabel = new JLabel("Motos", SwingConstants.CENTER);
        motoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        motoPanel.add(motoLabel, BorderLayout.NORTH);
        motoPanel.add(new JScrollPane(motoTable), BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel tablesPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        tablesPanel.add(autoPanel);
        tablesPanel.add(motoPanel);

        JButton volverButton = new JButton("VOLVER");
        volverButton.addActionListener(e -> parentDialog.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(volverButton);

        mainPanel.add(tablesPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        return mainPanel;
    }
}
