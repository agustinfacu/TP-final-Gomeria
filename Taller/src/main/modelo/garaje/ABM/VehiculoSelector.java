package main.modelo.garaje.ABM;

import main.modelo.vehiculos.Vehiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class VehiculoSelector {
    private static List<Vehiculo> vehiculos;
    
        public VehiculoSelector(List<Vehiculo> vehiculos) {
            VehiculoSelector.vehiculos = vehiculos;
    }

    public int seleccionarVehiculo(String titulo) {
        String[] columnNames = {"ID", "Patente", "Marca", "Color"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Vehiculo vehiculo : vehiculos) {
            model.addRow(new Object[]{vehiculo.getId(), vehiculo.getPatente(), vehiculo.getMarca(), vehiculo.getColor()});
        }

        JTable table = new JTable(model);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JTextField searchField = new JTextField();
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            private void filter() {
                String text = searchField.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1));
                }
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Buscar por patente:"), BorderLayout.NORTH);
        panel.add(searchField, BorderLayout.CENTER);
        panel.add(new JScrollPane(table), BorderLayout.SOUTH);

        int result = JOptionPane.showConfirmDialog(null, panel, titulo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int modelRow = table.convertRowIndexToModel(selectedRow);
                return (int) model.getValueAt(modelRow, 0);
            } else {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún vehículo.");
            }
        }
        return -1;
    }
    //settter
    public static void setVehiculos(List<Vehiculo> vehiculosList) {vehiculos = vehiculosList;}
}