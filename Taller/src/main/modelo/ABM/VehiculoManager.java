package main.modelo.ABM;

import main.controlador.Validaciones;
import main.modelo.garaje.*;
import main.modelo.vehiculos.*;
import main.vista.VistaGomeria;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;

public class VehiculoManager {
    private CapacidadMaxima capacidadMaxima;
    private Precios precios;
    private Estadisticas estadisticas;
    private List<Vehiculo> vehiculos;
    private VistaGomeria vistaGomeria;
    private VehiculosDePrueba vehiculosDePrueba;

    public VehiculoManager(VistaGomeria vistaGomeria, boolean isTestMode) {
        this.vistaGomeria = vistaGomeria;
        this.vehiculosDePrueba = new VehiculosDePrueba();
        if (isTestMode) {
            this.capacidadMaxima = new CapacidadMaxima(100);
            this.precios = new Precios(3000, 1500);
        } else {
            int capacidadMaximaValor = vistaGomeria.mostrarMenuInicial();
            double[] preciosValores = vistaGomeria.solicitarPrecios();
            this.capacidadMaxima = new CapacidadMaxima(capacidadMaximaValor);
            this.precios = new Precios(preciosValores[0], preciosValores[1]);
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
            Auto auto = solicitarDatosAuto();
            if (capacidadMaxima.hayEspacioDisponible()) {
                vehiculos.add(auto);
                JOptionPane.showMessageDialog(null, "Auto ingresado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No hay espacio disponible en el garaje.");
            }
        } else if (tipoVehiculo == 1) {
            Moto moto = solicitarDatosMoto();
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

    public void modificarVehiculo() {
        // Seleccionar el vehículo a modificar
        VehiculoSelector selector = new VehiculoSelector(vehiculos);
        int idVehiculo = selector.seleccionarVehiculo("Seleccione el vehículo a modificar");
    
        if (idVehiculo != -1) {
            Vehiculo vehiculoAModificar = vehiculos.stream()
                    .filter(v -> v.getId() == idVehiculo)
                    .findFirst().orElse(null);
    
            if (vehiculoAModificar != null) {
                // Mostrar un JComboBox con los datos disponibles para modificar
                String[] opciones = new String[]{
                    "Patente",
                    "Kilometraje",
                    "Número de Chasis",
                    "Número de Motor",
                    "Cantidad de Puertas",
                    "Cantidad de Ruedas"
                };
    
                // Crear un combo box para seleccionar qué atributo modificar
                String atributoSeleccionado = (String) JOptionPane.showInputDialog(
                        null,
                        "Seleccione el dato que desea modificar:",
                        "Modificar Vehículo",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );
    
                if (atributoSeleccionado != null) {
                    boolean modificacionExitosa = false;
    
                    // Dependiendo del atributo seleccionado, se solicita el nuevo valor
                    switch (atributoSeleccionado) {
                        case "Patente":
                            String nuevaPatente = JOptionPane.showInputDialog("Ingrese la nueva patente:");
                            if (nuevaPatente != null && !nuevaPatente.trim().isEmpty()) {
                                if (vehiculoAModificar instanceof Auto) {
                                    ((Auto) vehiculoAModificar).setPatente(nuevaPatente);
                                } else if (vehiculoAModificar instanceof Moto) {
                                    ((Moto) vehiculoAModificar).setPatente(nuevaPatente);
                                }
                                modificacionExitosa = true;
                            }
                            break;
    
                        case "Kilometraje":
                            try {
                                int nuevoKilometraje = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo kilometraje:"));
                                if (vehiculoAModificar instanceof Auto) {
                                    ((Auto) vehiculoAModificar).setKilometraje(nuevoKilometraje);
                                } else if (vehiculoAModificar instanceof Moto) {
                                    ((Moto) vehiculoAModificar).setKilometraje(nuevoKilometraje);
                                }
                                modificacionExitosa = true;
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Error: El kilometraje debe ser un número.");
                            }
                            break;
    
                        case "Número de Chasis":
                            String nuevoChasis = JOptionPane.showInputDialog("Ingrese el nuevo número de chasis:");
                            if (nuevoChasis != null && !nuevoChasis.trim().isEmpty()) {
                                if (vehiculoAModificar instanceof Auto) {
                                    ((Auto) vehiculoAModificar).setNumeroChasis(nuevoChasis);
                                } else if (vehiculoAModificar instanceof Moto) {
                                    ((Moto) vehiculoAModificar).setNumeroChasis(nuevoChasis);
                                }
                                modificacionExitosa = true;
                            }
                            break;
    
                        case "Número de Motor":
                            String nuevoMotor = JOptionPane.showInputDialog("Ingrese el nuevo número de motor:");
                            if (nuevoMotor != null && !nuevoMotor.trim().isEmpty()) {
                                if (vehiculoAModificar instanceof Auto) {
                                    ((Auto) vehiculoAModificar).setNumeroMotor(nuevoMotor);
                                } else if (vehiculoAModificar instanceof Moto) {
                                    ((Moto) vehiculoAModificar).setNumeroMotor(nuevoMotor);
                                }
                                modificacionExitosa = true;
                            }
                            break;
    
                        case "Cantidad de Puertas":
                            try {
                                int nuevasPuertas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad de puertas:"));
                                if (vehiculoAModificar instanceof Auto) {
                                    ((Auto) vehiculoAModificar).setCantidadPuertas(nuevasPuertas);
                                }
                                modificacionExitosa = true;
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Error: La cantidad de puertas debe ser un número.");
                            }
                            break;
    
                        case "Cantidad de Ruedas":
                            try {
                                int nuevasRuedas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad de ruedas:"));
                                if (vehiculoAModificar instanceof Auto) {
                                    ((Auto) vehiculoAModificar).setCantidadRuedas(nuevasRuedas);
                                } else if (vehiculoAModificar instanceof Moto) {
                                    ((Moto) vehiculoAModificar).setCantidadRuedas(nuevasRuedas);
                                }
                                modificacionExitosa = true;
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Error: La cantidad de ruedas debe ser un número.");
                            }
                            break;
                    }
    
                    // Mostrar mensaje según si la modificación fue exitosa o no
                    if (modificacionExitosa) {
                        JOptionPane.showMessageDialog(null, "Modificación realizada correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error con su modificación.");
                    }
                }
            }
        }
    }
    

    public Precios getPrecios() {
        return precios;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima.capacidadMaxima;
    }

    public int getCantidadVehiculos() {
        return vehiculos.size();
    }
    
    private Auto solicitarDatosAuto() {
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

    private Moto solicitarDatosMoto() {
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