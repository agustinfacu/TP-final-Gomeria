package main.vista;

import javax.swing.*;
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
    public void mostrarEstadisticas(double kilometrajeMedio, double sumaKilometrajes, int cantidadVehiculos) {
        // Crear un panel para mostrar el contenido de las estadísticas
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Encabezado con la fórmula del cálculo
        JLabel formulaLabel = new JLabel("<html><b>Fórmula:</b> (Suma de kilometrajes) / (Cantidad de vehículos)</html>");
        formulaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        formulaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(formulaLabel);

        // Tabla con la cantidad total de vehículos y la suma de kilometrajes
        JPanel tablaPanel = new JPanel();
        tablaPanel.setLayout(new BoxLayout(tablaPanel, BoxLayout.Y_AXIS));
        tablaPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] columnNames = {"Descripción", "Cantidad"};
        Object[][] data = {
            {"Total de vehículos", cantidadVehiculos},
            {"Suma total de kilometrajes", formatNumber(sumaKilometrajes)}
        };

        JTable tabla = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setPreferredSize(new Dimension(300, 100));
        tablaPanel.add(scrollPane);
        panel.add(tablaPanel);

        // Pie de página con la media total de kilómetros
        JLabel mediaLabel = new JLabel("<html><b>Media total de kilómetros:</b> " + formatNumber(kilometrajeMedio) + "</html>");
        mediaLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mediaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(mediaLabel);

        // Crear un JDialog
        JDialog dialog = new JDialog(getFrame(), "Estadísticas de Kilometraje", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        dialog.add(panel, BorderLayout.CENTER);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(getFrame());

        // Crear un botón de "Volver"
        JButton volverButton = new JButton("Volver al Menú Principal");
        volverButton.addActionListener(e -> dialog.dispose());

        // Crear un panel para el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(volverButton);

        // Añadir el panel del botón al panel principal
        panel.add(buttonPanel);

        dialog.setVisible(true);
    }

    private String formatNumber(double amount) {
        return String.format("%,.0f", amount); // Formato sin decimales y con separador de miles
    }
}