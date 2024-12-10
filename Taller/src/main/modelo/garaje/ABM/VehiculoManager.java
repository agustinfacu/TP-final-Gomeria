package main.modelo.garaje.ABM;

import main.modelo.garaje.*;
import main.modelo.vehiculos.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;

public class VehiculoManager {
    private CapacidadMaxima capacidadMaxima;
    private Caja precios;
    private Estadisticas estadisticas;
    private List<Vehiculo> vehiculos;
    private VehiculosDePrueba vehiculosDePrueba;

    public VehiculoManager(boolean isTestMode, int capacidadMaxima, double[] preciosValores) {

        this.capacidadMaxima = new CapacidadMaxima(capacidadMaxima);
        this.precios = new Caja(preciosValores[0], preciosValores[1]);
        this.vehiculos = new ArrayList<>();
        this.estadisticas = new Estadisticas();
        this.vehiculosDePrueba = new VehiculosDePrueba();

        if (isTestMode) {
            this.capacidadMaxima = new CapacidadMaxima(100);
            this.precios = new Caja(3000, 1500);
        } else {
            
            this.capacidadMaxima = new CapacidadMaxima(capacidadMaxima);
            this.precios = new Caja(preciosValores[0], preciosValores[1]);
        }
        this.estadisticas = new Estadisticas();
        this.vehiculos = new ArrayList<>();
    }

    public void importarVehiculosDePrueba() {
        List<Vehiculo> vehiculosImportados = vehiculosDePrueba.importarVehiculos();
        vehiculos.addAll(vehiculosImportados);
        JOptionPane.showMessageDialog(null, "Se han importado " + vehiculosImportados.size() + " vehículos de prueba.");
    }

    public void ingresarVehiculo() {
        String[] options = {"Auto", "Moto"};
        int tipoVehiculo = JOptionPane.showOptionDialog(null,
                "Seleccione el tipo de vehículo a ingresar:",
                "Ingresar Vehículo",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (tipoVehiculo == 0) {
            Auto auto = SolicitarDatosAuto.solicitarDatosAuto();
            if (capacidadMaxima.hayEspacioDisponible()) {
                vehiculos.add(auto);
                JOptionPane.showMessageDialog(null, "Auto ingresado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No hay espacio disponible en el garaje.");
            }
        } else if (tipoVehiculo == 1) {
            Moto moto = SolicitarDatosMoto.solicitarDatosMoto();
            if (capacidadMaxima.hayEspacioDisponible()) {
                vehiculos.add(moto);
                JOptionPane.showMessageDialog(null, "Moto ingresada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No hay espacio disponible en el garaje.");
            }
        }
    }

    public void retirarVehiculo() {
        VehiculoSelector selector = new VehiculoSelector(vehiculos);
        int idVehiculo = selector.seleccionarVehiculo("Seleccione el vehículo a retirar");
        if (idVehiculo != -1) {
            vehiculos.removeIf(vehiculo -> vehiculo.getId() == idVehiculo);
            JOptionPane.showMessageDialog(null, "Vehículo retirado exitosamente.");
        }
    }

    public void consultarVehiculos(JFrame parentFrame) {
        if (vehiculos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay vehículos para mostrar.");
            return;
        }

        JDialog consultaDialog = new JDialog(parentFrame, "Consultar Vehículos", true);
        consultaDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        consultaDialog.setLayout(new BorderLayout());
        consultaDialog.setSize(800, 600);
        consultaDialog.setLocationRelativeTo(null);

        VehiculoViewer viewer = new VehiculoViewer(vehiculos);
        JPanel panelVehiculos = viewer.crearPanelVehiculos(consultaDialog);

        consultaDialog.add(panelVehiculos);
        consultaDialog.setVisible(true); // Pausar flujo hasta cerrar
    }

    public void mostrarCaja() {
        CajaViewer cajaViewer = new CajaViewer(vehiculos, precios);
        cajaViewer.mostrarCaja(null);
    }

    public void mostrarEstadisticas() {
        double kilometrajeMedio = estadisticas.calcularKilometrajeMedio();
        JOptionPane.showMessageDialog(null, "Kilometraje medio de todos los vehículos en el garaje: " + kilometrajeMedio);
    }

   
    public Caja getPrecios() {
        return precios;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima.capacidadMaxima;
    }

    public int getCantidadVehiculos() {
        return vehiculos.size();
    }
    
}