package main.controlador;

import main.modelo.garaje.Caja;
import main.modelo.garaje.Estadisticas;
import main.vista.VistaGomeria;
import main.modelo.garaje.ABM.VehiculoManager;
import main.modelo.garaje.ABM.ModificarVehiculo;

import javax.swing.*;
import java.awt.*;

public class GarajeController {
    private String[] menuOptions;
    private boolean isTestMode;
    private VistaGomeria vistaGomeria;
    private VehiculoManager vehiculoManager;
    private Estadisticas estadisticas;
    private double[] precios; // Array para almacenar los precios de neumáticos

    public GarajeController(String[] menuOptions, boolean isTestMode) {
        this.menuOptions = menuOptions;
        this.isTestMode = isTestMode;
        this.vistaGomeria = new VistaGomeria(menuOptions);

        if (isTestMode) {
            // Valores predeterminados para modo de prueba
            this.precios = new double[]{3000, 1500};
        } else {
            // Solicitar precios al usuario en modo normal
            this.precios = solicitarPrecios();
        }

        int capacidadMaxima;
        if (isTestMode) {
            capacidadMaxima = 100; // Valor fijo para pruebas
        } else {
            capacidadMaxima = vistaGomeria.mostrarMenuInicial(); // Solicitar al usuario
        }

        this.vehiculoManager = new VehiculoManager(isTestMode, capacidadMaxima, precios);
        this.estadisticas = new Estadisticas();
    }

    // Método para solicitar los precios de cambio de neumáticos
    public double[] solicitarPrecios() {
        double[] precios = new double[2];

        // Precio para autos
        String inputAuto = JOptionPane.showInputDialog(null, "Ingrese el valor del cambio de neumáticos para autos:");
        try {
            precios[0] = Double.parseDouble(inputAuto); // Convertir la entrada a double
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
            return null; // Indica error si no es un número válido
        }

        // Precio para motos
        String inputMoto = JOptionPane.showInputDialog(null, "Ingrese el valor del cambio de neumáticos para motos:");
        try {
            precios[1] = Double.parseDouble(inputMoto); // Convertir la entrada a double
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
            return null; // Indica error si no es un número válido
        }

        return precios;
    }

    // Método para iniciar el controlador y mostrar el menú principal
    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            // Mostrar el menú principal
            int opcion = vistaGomeria.mostrarMenuPrincipal(
                    vehiculoManager.getPrecios().getPrecioAuto(),
                    vehiculoManager.getPrecios().getPrecioMoto(),
                    vehiculoManager.getCapacidadMaxima(),
                    vehiculoManager.getCantidadVehiculos());

            if (isTestMode) {
                // Menú para GarajeTest (modo prueba)
                switch (opcion) {
                    case 0:
                        // Importar vehículos de prueba
                        vehiculoManager.importarVehiculosDePrueba();
                        break;
                    case 1:
                        // Consultar vehículos
                        vehiculoManager.consultarVehiculos(vistaGomeria.getFrame());
                        break;
                    case 2:
                        // Mostrar caja
                        vehiculoManager.mostrarCaja();
                        break;
                    case 3:
                        // Mostrar estadísticas
                        mostrarEstadisticas();
                        break;
                    case 4:
                        continuar = false;
                        break;
                    default:
                        continuar = false;
                        break;
                }
            } else {
                // Menú para App (modo aplicación normal)
                switch (opcion) {
                    case 0:
                        // Ingresar vehículo
                        vehiculoManager.ingresarVehiculo();
                        break;
                    case 1:
                        // Retirar vehículo
                        vehiculoManager.retirarVehiculo();
                        break;
                    case 2:
                        // Consultar vehículos
                        vehiculoManager.consultarVehiculos(vistaGomeria.getFrame());
                        break;
                    case 3:
                        // Mostrar caja
                        vehiculoManager.mostrarCaja();
                        break;
                    case 4:
                        // Mostrar estadísticas
                        mostrarEstadisticas();
                        break;
                    case 5:
                        // Modificar vehículo
                        ModificarVehiculo.modificarVehiculo();
                        break;
                    case 6:
                        continuar = false;
                        break;
                    default:
                        continuar = false;
                        break;
                }
            }
        }

        // Salir de la aplicación
        System.exit(0);
    }

    // Mostrar las estadísticas del kilometraje medio en un JDialog
    public void mostrarEstadisticas() {
        double kilometrajeMedio = estadisticas.calcularKilometrajeMedio();
        double sumaKilometrajes = estadisticas.calcularSumaKilometrajes();
        int cantidadVehiculos = vehiculoManager.getCantidadVehiculos();
        
        vistaGomeria.mostrarEstadisticas(kilometrajeMedio, sumaKilometrajes, cantidadVehiculos);
    }

    // Método para formatear los valores a formato de moneda (en este caso, formato argentino) para el kilometraje medio
    private String formatCurrency(double amount) {
        java.text.NumberFormat currencyFormat = java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("es", "AR"));
        return currencyFormat.format(amount);
    }

    // Método para formatear los números sin símbolo de moneda para el kilometraje total
    private String formatNumber(double amount) {
        return String.format("%,.0f", amount); // Formato sin decimales y con separador de miles si es necesario
    }
}
