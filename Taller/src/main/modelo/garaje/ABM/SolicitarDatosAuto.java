package main.modelo.garaje.ABM;

import java.util.List;

import javax.swing.JOptionPane;

import main.controlador.Validaciones;
import main.modelo.vehiculos.Auto;
import main.modelo.vehiculos.Vehiculo;
import main.vista.VistaGomeria;

public class SolicitarDatosAuto {

    public static VistaGomeria vistaGomeria;
    private static List<Vehiculo> vehiculos;
    //getter y setter
    public static void setVistaGomeria(VistaGomeria vista) {vistaGomeria = vista;}
    public static void setVehiculos(List<Vehiculo> vehiculosList) {vehiculos = vehiculosList;}
    
    public static Auto solicitarDatosAuto() {

        String color = vistaGomeria.seleccionarColor("Seleccione el color del auto:");
        String marca = vistaGomeria.seleccionarMarca("Seleccione la marca del auto:", VistaGomeria.MARCAS_AUTOS);
        
        boolean esNueva = JOptionPane.showConfirmDialog(null, "¿La patente es nueva?", "Tipo de Patente", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        
         String patente;
            do {
                    String formato = esNueva ? "AA123BB (7 caracteres)" : "AAA123 (6 caracteres)";
                    patente = JOptionPane.showInputDialog("Ingrese la patente del auto (Formato: " + formato + "):");
                    if (!Validaciones.validarPatente(patente, esNueva)) {
                        String mensajeError = "Formato incorrecto. Debe ser " + formato + ".";
                        JOptionPane.showMessageDialog(null, "Error: " + mensajeError);
                    }
                } while (!Validaciones.validarPatente(patente, esNueva));
        
            int kilometraje = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el kilometraje del auto:"));
        
            String numeroChasis;
                do {
                    numeroChasis = JOptionPane.showInputDialog("Ingrese el número de chasis del auto (17 caracteres):");
                    if (!Validaciones.validarNumeroChasis(numeroChasis)) {
                        int diferencia = 17 - numeroChasis.length();
                        String mensajeError = diferencia > 0 ? "Faltan " + Math.abs(diferencia) + " caracteres." : "Sobran " + Math.abs(diferencia) + " caracteres.";
                        JOptionPane.showMessageDialog(null, "Error: " + mensajeError);
                    }
                } while (!Validaciones.validarNumeroChasis(numeroChasis));
        
            String numeroMotor;
                do {
                    numeroMotor = JOptionPane.showInputDialog("Ingrese el número de motor del auto (20 caracteres):");
                    if (!Validaciones.validarNumeroMotor(numeroMotor)) {
                        int diferencia = 20 - numeroMotor.length();
                        String mensajeError = diferencia > 0 ? "Faltan " + Math.abs(diferencia) + " caracteres." : "Sobran " + Math.abs(diferencia) + " caracteres.";
                        JOptionPane.showMessageDialog(null, "Error: " + mensajeError);
                    }
                } while (!Validaciones.validarNumeroMotor(numeroMotor));
        
                int cantidadPuertas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de puertas del auto:"));
                int cantidadRuedas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cuántas ruedas se deben cambiar del auto:"));
        
                return new Auto(vehiculos.size() + 1, color, marca, patente, kilometraje, numeroChasis, numeroMotor, cantidadPuertas, cantidadRuedas);
        }

}
