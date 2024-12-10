package main.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import main.modelo.vehiculos.Vehiculo;
import java.awt.*;

public class VistaGomeria {
    
    public static final String[] COLORES = {
        "Rojo", "Azul", "Negro", "Blanco", "Gris", 
        "Verde", "Amarillo", "Naranja", "Violeta", "Marrón"
    };

    public static final String[] MARCAS_AUTOS = {
        "Alfa Romeo", "Audi", "BMW", "Chevrolet", "Citroën", "Fiat", "Ford", "Honda", 
        "Hyundai", "Jeep", "Kia", "Mazda", "Mercedes-Benz", "Nissan", "Peugeot", 
        "Renault", "Toyota", "Volkswagen", "Volvo"
    };

    public static final String[] MARCAS_MOTOS = {
        "Aprilia", "BMW", "Ducati", "Harley-Davidson", "Honda", "Kawasaki", "KTM", 
        "Suzuki", "Triumph", "Yamaha"
    };

    private String[] menuOptions;
    private JFrame frame;  // Añadir la referencia al JFrame

    public VistaGomeria(String[] menuOptions) {
        this.menuOptions = menuOptions;
        this.frame = new JFrame("Menú Principal");  // Inicializar el JFrame
    }
    public JFrame getFrame() {
        return frame;
    }

    public int mostrarMenuPrincipal(double precioAuto, double precioMoto, int capacidadMaxima, int capacidadActual) {
        return JOptionPane.showOptionDialog(frame,
                "Gomería Don Tejero\nFecha: " + java.time.LocalDate.now() +
                        "\nPrecio cambio neumáticos (Autos): $" + precioAuto +
                        "\nPrecio cambio neumáticos (Motos): $" + precioMoto +
                        "\nCapacidad máxima: " + capacidadMaxima +
                        "\nCapacidad actual: " + capacidadActual,
                "Menú Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                menuOptions,
                menuOptions[0]);
    }

    public int mostrarMenuInicial() {
        String input = JOptionPane.showInputDialog(null, "Bienvenidos a la Gomería Don Tejero\nIngrese la capacidad máxima de vehículos admitidos para este día:");
        return Integer.parseInt(input);
    }

    public static double[] solicitarPrecios() {
        double[] precios = new double[2];
    
        String inputAuto = JOptionPane.showInputDialog(null, "Ingrese el valor del cambio de neumáticos para autos:");
        try {
            precios[0] = Double.parseDouble(inputAuto); // Convertir la entrada a double
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
            return null; // Indica error si no es un número válido
        }
    
        String inputMoto = JOptionPane.showInputDialog(null, "Ingrese el valor del cambio de neumáticos para motos:");
        try {
            precios[1] = Double.parseDouble(inputMoto); // Convertir la entrada a double
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
            return null; // Indica error si no es un número válido
        }
    
        return precios;
    }
    

    public String seleccionarColor(String mensaje) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(COLORES);
        JComboBox<String> comboBox = new JComboBox<>(model);
        comboBox.setEditable(true);

        model.addElement("Agregar nuevo color...");

        while (true) {
            JOptionPane.showMessageDialog(null, comboBox, mensaje, JOptionPane.QUESTION_MESSAGE);
            String seleccion = (String) comboBox.getSelectedItem();

            if ("Agregar nuevo color...".equals(seleccion)) {
                String nuevoColor = JOptionPane.showInputDialog("Ingrese el nombre del nuevo color:");
                if (nuevoColor != null && !nuevoColor.trim().isEmpty()) {
                    model.insertElementAt(nuevoColor, model.getSize() - 1);
                    comboBox.setSelectedItem(nuevoColor);
                }
            } else {
                return seleccion;
            }
        }
    }

    public String seleccionarMarca(String mensaje, String[] marcasIniciales) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(marcasIniciales);
        JComboBox<String> comboBox = new JComboBox<>(model);
        comboBox.setEditable(true);

        model.addElement("Agregar nueva marca...");

        while (true) {
            JOptionPane.showMessageDialog(null, comboBox, mensaje, JOptionPane.QUESTION_MESSAGE);
            String seleccion = (String) comboBox.getSelectedItem();

            if ("Agregar nueva marca...".equals(seleccion)) {
                String nuevaMarca = JOptionPane.showInputDialog("Ingrese el nombre de la nueva marca:");
                if (nuevaMarca != null && !nuevaMarca.trim().isEmpty()) {
                    model.insertElementAt(nuevaMarca, model.getSize() - 1);
                    comboBox.setSelectedItem(nuevaMarca);
                }
            } else {
                return seleccion;
            }
        }
    }
    public void mostrarEstadisticas(double kilometrajeMedio, double sumaKilometrajes, int cantidadVehiculos, List<Vehiculo> vehiculos) {
        // Crear un panel para mostrar el contenido de las estadísticas
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        // Encabezado con el kilometraje medio
        JLabel kilometrajeMedioLabel = new JLabel("Kilometraje Medio: " + String.format("%.2f", kilometrajeMedio) + " km");
        kilometrajeMedioLabel.setFont(new Font("Arial", Font.BOLD, 24));
        kilometrajeMedioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(kilometrajeMedioLabel);
    
        // Crear la tabla con la cantidad total de vehículos y la suma de kilometrajes
        String[] columnNames = {"Descripción", "Valor"};
        Object[][] data = {
            {"Total de vehículos", cantidadVehiculos},
            {"Suma total de kilometrajes", String.format("%.0f", sumaKilometrajes)}
        };
    
        // Crear la tabla con los datos
        JTable tabla = new JTable(data, columnNames); // No necesitamos hacer un cast aquí
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setPreferredSize(new Dimension(300, 100));
        panel.add(scrollPane);
    
        // Mostrar la fórmula para el cálculo del kilometraje medio
        JLabel formulaLabel = new JLabel("<html><i>Fórmula: Kilometraje Medio = Total de Kilómetros / Número de Vehículos</i></html>");
        formulaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        formulaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(formulaLabel);
    
        // Botón "Volver" al menú principal
        JButton volverButton = new JButton("Volver al Menú Principal");
        volverButton.addActionListener(e -> {
            // Cerrar el JDialog
            SwingUtilities.getWindowAncestor(volverButton).dispose();
            // Aquí puedes agregar el código para regresar al menú principal
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(volverButton);
        panel.add(buttonPanel);
    
        // Crear el JDialog con el panel de estadísticas
        JDialog statsDialog = new JDialog((Frame) null, "Estadísticas de Kilometraje", true);
        statsDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        statsDialog.setLayout(new BorderLayout());
        statsDialog.add(panel, BorderLayout.CENTER);
        statsDialog.setSize(600, 400);
        statsDialog.setLocationRelativeTo(null); // Centrar la ventana
        statsDialog.setResizable(true); // Hacer la ventana redimensionable
        statsDialog.setVisible(true);
    }
    
}