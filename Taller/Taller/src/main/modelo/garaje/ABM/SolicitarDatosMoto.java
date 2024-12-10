package main.modelo.garaje.ABM;

import java.util.List;

import javax.swing.JOptionPane;

import main.controlador.Validaciones;
import main.modelo.vehiculos.Moto;
import main.modelo.vehiculos.Vehiculo;
import main.vista.VistaGomeria;

public class SolicitarDatosMoto {

    private static VistaGomeria vistaGomeria;
    private static List<Vehiculo> vehiculos;
    private static List<Moto> motos;
    // Setter para inicializar vistaGomeria
    public static void setVistaGomeria(VistaGomeria vista) {vistaGomeria = vista;}
    // Setter para inicializar la lista de vehículos
    public static void setVehiculos(List<Vehiculo> vehiculosList) {vehiculos = vehiculosList;}

    public static Moto solicitarDatosMoto() {
        String color = vistaGomeria.seleccionarColor("Seleccione el color de la moto:");
        String marca = vistaGomeria.seleccionarMarca("Seleccione la marca de la moto:", VistaGomeria.MARCAS_MOTOS);

        boolean esNueva = JOptionPane.showConfirmDialog(null, "¿La patente es nueva?", "Tipo de Patente", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        String patente;
        do {
            String formato = esNueva ? "AA123BB (7 caracteres)" : "AAA123 (6 caracteres)";
            patente = JOptionPane.showInputDialog("Ingrese la patente de la moto (Formato: " + formato + "):");
            if (!Validaciones.validarPatente(patente, esNueva)) {
                String mensajeError = "Formato incorrecto. Debe ser " + formato + ".";
                JOptionPane.showMessageDialog(null, "Error: " + mensajeError);
            }
        } while (!Validaciones.validarPatente(patente, esNueva));

        int kilometraje = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el kilometraje de la moto:"));

        String numeroChasis;
        do {
            numeroChasis = JOptionPane.showInputDialog("Ingrese el número de chasis de la moto (17 caracteres):");
            if (!Validaciones.validarNumeroChasis(numeroChasis)) {
                int diferencia = 17 - numeroChasis.length();
                String mensajeError = diferencia > 0 ? "Faltan " + Math.abs(diferencia) + " caracteres." : "Sobran " + Math.abs(diferencia) + " caracteres.";
                JOptionPane.showMessageDialog(null, "Error: " + mensajeError);
            }
        } while (!Validaciones.validarNumeroChasis(numeroChasis));

        String numeroMotor;
        do {
            numeroMotor = JOptionPane.showInputDialog("Ingrese el número de motor de la moto (20 caracteres):");
            if (!Validaciones.validarNumeroMotor(numeroMotor)) {
                int diferencia = 20 - numeroMotor.length();
                String mensajeError = diferencia > 0 ? "Faltan " + Math.abs(diferencia) + " caracteres." : "Sobran " + Math.abs(diferencia) + " caracteres.";
                JOptionPane.showMessageDialog(null, "Error: " + mensajeError);
            }
        } while (!Validaciones.validarNumeroMotor(numeroMotor));

        String cilindrada = JOptionPane.showInputDialog("Ingrese la cilindrada de la moto:");
        int cantidadRuedas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cuántas ruedas se deben cambiar de la moto:"));

        return new Moto(vehiculos.size() + 1, color, marca, kilometraje, numeroChasis, numeroMotor, patente, cilindrada, cantidadRuedas);
    }
    
}
