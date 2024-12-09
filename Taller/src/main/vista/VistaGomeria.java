package main.vista;

import javax.swing.*;

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

    public double[] solicitarPrecios() {
        double[] precios = new double[2];
        precios[0] = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el valor del cambio de neumáticos para autos:"));
        precios[1] = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el valor del cambio de neumáticos para motos:"));
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
}