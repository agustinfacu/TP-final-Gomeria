package main.vista;

import javax.swing.JOptionPane;

public class VistaGomeria {

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

    public int mostrarMenuPrincipal(double precioAuto, double precioMoto, int capacidadMaxima, int capacidadActual) {
        String[] options = {"Ingresar Vehículo", "Retirar Vehículo", "Consultar Vehículos", "Caja", "Estadísticas", "Salir"};
        return JOptionPane.showOptionDialog(null,
                "Gomería Don Tejero\nFecha: " + java.time.LocalDate.now() +
                        "\nPrecio cambio neumáticos (Autos): $" + precioAuto +
                        "\nPrecio cambio neumáticos (Motos): $" + precioMoto +
                        "\nCapacidad máxima: " + capacidadMaxima +
                        "\nCapacidad actual: " + capacidadActual,
                "Menú Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);
    }

}