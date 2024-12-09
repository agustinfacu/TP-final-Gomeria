package main.controlador;

import main.modelo.garaje.Estadisticas;
import main.vista.VistaGomeria;
import main.modelo.ABM.VehiculoManager;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class GarajeController {
    private VehiculoManager vehiculoManager;
    private VistaGomeria vistaGomeria;
    private String[] menuOptions;
    private boolean isTestMode;
    private Estadisticas estadisticas;  // Instanciamos la clase Estadisticas

    public GarajeController(String[] menuOptions, boolean isTestMode) {
        this.menuOptions = menuOptions;
        this.isTestMode = isTestMode;
        this.vistaGomeria = new VistaGomeria(menuOptions);
        this.vehiculoManager = new VehiculoManager(vistaGomeria, isTestMode);
        this.estadisticas = new Estadisticas();  // Instanciamos la clase Estadisticas
    }

    // Mostrar las estadísticas del kilometraje medio en un JDialog
    public void mostrarEstadisticas() {
        double kilometrajeMedio = estadisticas.calcularKilometrajeMedio();
        double sumaKilometrajes = estadisticas.calcularSumaKilometrajes();

        // Crear un panel para mostrar el contenido de las estadísticas
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Fórmula del cálculo (se muestra primero)
        JLabel formulaLabel = new JLabel("<html><b>Fórmula:</b> (Suma de kilometrajes) / (Cantidad de vehículos)</html>");
        formulaLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Font correcta
        formulaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Centrado
        panel.add(formulaLabel);

        // Suma total de kilometrajes (se muestra después de la fórmula)
        JLabel sumaKilometrajesLabel = new JLabel("<html><b>Suma Total de Kilometrajes:</b> " + formatNumber(sumaKilometrajes) + "</html>");
        sumaKilometrajesLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Font correcta
        sumaKilometrajesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Centrado
        panel.add(sumaKilometrajesLabel);

        // Kilometraje medio (se muestra último y con fuente más grande)
        JLabel kilometrajeMedioLabel = new JLabel("<html><b>Kilometraje Medio:</b> " + (kilometrajeMedio) + "</html>");
        kilometrajeMedioLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Fuente más grande y negrita
        kilometrajeMedioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Centrado
        panel.add(kilometrajeMedioLabel);

        // Crear el JDialog
        JDialog dialog = new JDialog(vistaGomeria.getFrame(), "Estadísticas de Kilometraje", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        dialog.add(panel, BorderLayout.CENTER);

        // Tamaño y visibilidad
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(vistaGomeria.getFrame()); // Centrar respecto a la ventana principal
        dialog.setVisible(true);
    }

    // Método para formatear los valores a formato de moneda (en este caso, formato argentino) para el kilometraje medio
    private String formatCurrency(double amount) {
        java.text.NumberFormat currencyFormat = java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("es", "AR"));
        return currencyFormat.format(amount);
    }

    // Método para formatear los números sin símbolo de moneda para el kilometraje total
    private String formatNumber(double amount) {
        return String.format("%,.0f", amount);  // Formato sin decimales y con separador de miles si es necesario
    }

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
                        // Implementar lógica para "Importar Vehículos" si es necesario
                        // O si el importado se debe hacer al iniciar el sistema
                        vehiculoManager.importarVehiculosDePrueba();
                        break;
                    case 1:
                        vehiculoManager.consultarVehiculos(vistaGomeria.getFrame()); // Correcto: Opción 2 para consultar vehículos en GarajeTest
                        break;
                    case 2:
                        vehiculoManager.mostrarCaja();
                        break;
                    case 3:
                        mostrarEstadisticas(); // Llamada a la opción de estadísticas
                        break;
                    case 4:
                        continuar = false; // Salir
                        break;
                    default:
                        continuar = false; // Salir en caso de opción no válida
                        break;
                }
            } else {
                // Menú para App (modo aplicación normal)
                switch (opcion) {
                    case 0:
                        vehiculoManager.ingresarVehiculo();
                        break;
                    case 1:
                        vehiculoManager.retirarVehiculo();
                        break;
                    case 2:
                        vehiculoManager.consultarVehiculos(vistaGomeria.getFrame()); // Consultar vehículos en el modo normal (case 2)
                        break;
                    case 3:
                        vehiculoManager.mostrarCaja();
                        break;
                    case 4:
                        mostrarEstadisticas(); // Llamada a la opción de estadísticas
                        break;
                    case 5:
                        vehiculoManager.modificarVehiculo();
                        break;
                    case 6:
                        continuar = false; // Salir
                        break;
                    default:
                        continuar = false; // Salir en caso de opción no válida
                        break;
                }
            }
        }

        // Salir de la aplicación
        System.exit(0);
    }
}
