package main.controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import main.modelo.garaje.CapacidadMaxima;
import main.modelo.garaje.Estadisticas;
import main.modelo.garaje.Precios;
import main.modelo.garaje.ABMbase.AgregarAuto;
import main.modelo.garaje.ABMbase.AgregarMoto;
import main.modelo.garaje.ABMbase.QuitarAuto;
import main.modelo.garaje.ABMbase.QuitarMoto;
import main.modelo.vehiculos.Auto;
import main.modelo.vehiculos.Moto;
import main.modelo.vehiculos.Vehiculo;
import main.vista.VistaGomeria;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.awt.*;

public class GarajeController {
    private AgregarAuto agregarAuto;
    private AgregarMoto agregarMoto;
    private QuitarAuto quitarAuto;
    private QuitarMoto quitarMoto;
    private CapacidadMaxima capacidadMaxima;
    private Precios precios;
    private Estadisticas estadisticas;
    private VistaGomeria vistaGomeria;
    private List<Vehiculo> vehiculos;
    private double totalCobrado;
    private int nextId = 1; // Contador para asignar IDs únicos
    private static final String[] COLORES = {
        "Rojo", "Azul", "Negro", "Blanco", "Gris", 
        "Verde", "Amarillo", "Naranja", "Violeta", "Marrón"
    };
        private static final String[] MARCAS_AUTOS = {
            "Alfa Romeo", "Audi", "BMW", "Chevrolet", "Citroën", "Fiat", "Ford", "Honda", 
            "Hyundai", "Jeep", "Kia", "Mazda", "Mercedes-Benz", "Nissan", "Peugeot", 
            "Renault", "Toyota", "Volkswagen", "Volvo"
        };
        
        private static final String[] MARCAS_MOTOS = {
            "Aprilia", "BMW", "Ducati", "Harley-Davidson", "Honda", "Kawasaki", "KTM", 
            "Suzuki", "Triumph", "Yamaha"
        };


    public GarajeController() {
        this.vistaGomeria = new VistaGomeria();
        int capacidadMaximaValor = vistaGomeria.mostrarMenuInicial();
        double[] preciosValores = vistaGomeria.solicitarPrecios();
        this.capacidadMaxima = new CapacidadMaxima(capacidadMaximaValor);
        this.precios = new Precios(preciosValores[0], preciosValores[1]); // Precios de autos y motos
        this.estadisticas = new Estadisticas();
        this.agregarAuto = new AgregarAuto();
        this.agregarMoto = new AgregarMoto();
        this.quitarAuto = new QuitarAuto();
        this.quitarMoto = new QuitarMoto();
        this.vehiculos = new ArrayList<>();
        this.totalCobrado = 0;
    }

    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            int opcion = vistaGomeria.mostrarMenuPrincipal(
                    precios.getPrecioAuto(),
                    precios.getPrecioMoto(),
                    capacidadMaxima.capacidadMaxima,
                    vehiculos.size());
    
            switch (opcion) {
                case 0:
                    ingresarVehiculo();
                    break;
                case 1:
                    retirarVehiculo();
                    break;
                case 2:
                    consultarVehiculos();
                    break;
                case 3:
                    mostrarCaja();
                    break;
                case 4:
                    mostrarEstadisticas();
                    break;
                case 5:
                    continuar = false; // Opción para salir
                    break;
                default:
                    continuar = false;
                    break;
            }
        }
        System.exit(0); // Cerrar el programa
    }

    private void ingresarVehiculo() {
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
                boolean exito = agregarAuto.ejecutar(auto);
                if (exito) {
                    vehiculos.add(auto);
                    JOptionPane.showMessageDialog(null, "Auto ingresado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al ingresar el auto.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay espacio disponible en el garaje.");
            }
        } else if (tipoVehiculo == 1) {
            Moto moto = solicitarDatosMoto();
            if (capacidadMaxima.hayEspacioDisponible()) {
                boolean exito = agregarMoto.ejecutar(moto);
                if (exito) {
                    vehiculos.add(moto);
                    JOptionPane.showMessageDialog(null, "Moto ingresada exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al ingresar la moto.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay espacio disponible en el garaje.");
            }
        }
    }
    private String seleccionarColor(String mensaje) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(COLORES);
        JComboBox<String> comboBox = new JComboBox<>(model);
        comboBox.setEditable(true);
    
        // Agregar opción para nuevo color
        model.addElement("Agregar nuevo color...");
    
        while (true) {
            JOptionPane.showMessageDialog(null, comboBox, mensaje, JOptionPane.QUESTION_MESSAGE);
            String seleccion = (String) comboBox.getSelectedItem();
    
            if ("Agregar nuevo color...".equals(seleccion)) {
                String nuevoColor = JOptionPane.showInputDialog("Ingrese el nombre del nuevo color:");
                if (nuevoColor != null && !nuevoColor.trim().isEmpty()) {
                    model.insertElementAt(nuevoColor, model.getSize() - 1); // Insertar antes de la opción "Agregar nuevo color..."
                    comboBox.setSelectedItem(nuevoColor); // Seleccionar el nuevo color
                }
            } else {
                return seleccion;
            }
        }
    }

    private String seleccionarMarca(String mensaje, String[] marcasIniciales) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(marcasIniciales);
        JComboBox<String> comboBox = new JComboBox<>(model);
        comboBox.setEditable(true);

        // Agregar opción para nueva marca
        model.addElement("Agregar nueva marca...");

        while (true) {
            JOptionPane.showMessageDialog(null, comboBox, mensaje, JOptionPane.QUESTION_MESSAGE);
            String seleccion = (String) comboBox.getSelectedItem();

            if ("Agregar nueva marca...".equals(seleccion)) {
                String nuevaMarca = JOptionPane.showInputDialog("Ingrese el nombre de la nueva marca:");
                if (nuevaMarca != null && !nuevaMarca.trim().isEmpty()) {
                    model.insertElementAt(nuevaMarca, model.getSize() - 1); // Insertar antes de la opción "Agregar nueva marca..."
                    comboBox.setSelectedItem(nuevaMarca); // Seleccionar la nueva marca
                }
            } else {
                return seleccion;
            }
        }
    }

    private Auto solicitarDatosAuto() {
        String color = seleccionarColor("Seleccione el color del auto:");
        String marca = seleccionarMarca("Seleccione la marca del auto:", MARCAS_AUTOS);
        String patente = JOptionPane.showInputDialog("Ingrese la patente del auto:");
        int kilometraje = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el kilometraje del auto:"));
        String numeroChasis = JOptionPane.showInputDialog("Ingrese el número de chasis del auto:");
        String numeroMotor = JOptionPane.showInputDialog("Ingrese el número de motor del auto:");
        int cantidadPuertas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de puertas del auto:"));
        int cantidadRuedas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cuántas ruedas se deben cambiar del auto:"));
        return new Auto(nextId++, color, marca, patente, kilometraje, numeroChasis, numeroMotor, cantidadPuertas, cantidadRuedas);
    }
    
    private Moto solicitarDatosMoto() {
        String color = seleccionarColor("Seleccione el color de la moto:");
        String marca = seleccionarMarca("Seleccione la marca de la moto:", MARCAS_MOTOS);
        String patente = JOptionPane.showInputDialog("Ingrese la patente de la moto:");
        int kilometraje = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el kilometraje de la moto:"));
        String numeroChasis = JOptionPane.showInputDialog("Ingrese el número de chasis de la moto:");
        String numeroMotor = JOptionPane.showInputDialog("Ingrese el número de motor de la moto:");
        String cilindrada = JOptionPane.showInputDialog("Ingrese la cilindrada de la moto:");
        int cantidadRuedas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cuántas ruedas se deben cambiar de la moto:"));
        return new Moto(nextId++, color, marca, kilometraje, numeroChasis, numeroMotor, patente, cilindrada, cantidadRuedas);
    }
private void retirarVehiculo() {
    // Crear el modelo de la tabla
    String[] columnNames = {"ID", "Patente", "Marca", "Color"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

    // Llenar el modelo con los datos de los vehículos
    for (Vehiculo vehiculo : vehiculos) {
        model.addRow(new Object[]{vehiculo.getId(), vehiculo.getPatente(), vehiculo.getMarca(), vehiculo.getColor()});
    }

    // Crear la tabla y el sorter para filtrar
    JTable table = new JTable(model);
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    table.setRowSorter(sorter);

    // Crear el campo de texto para la búsqueda
    JTextField searchField = new JTextField();
    searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            filter();
        }
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            filter();
        }
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            filter();
        }
        private void filter() {
            String text = searchField.getText();
            if (text.trim().length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1)); // Filtrar por la columna de patente
            }
        }
    });

    // Crear el panel y agregar componentes
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(new JLabel("Buscar por patente:"), BorderLayout.NORTH);
    panel.add(searchField, BorderLayout.CENTER);
    panel.add(new JScrollPane(table), BorderLayout.SOUTH);

    // Mostrar el diálogo
    int result = JOptionPane.showConfirmDialog(null, panel, "Seleccione el vehículo a retirar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = table.convertRowIndexToModel(selectedRow);
            int idVehiculo = (int) model.getValueAt(modelRow, 0);
            retirarVehiculoPorId(idVehiculo);
        } else {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún vehículo.");
        }
    }
}

    private void retirarVehiculoPorId(int idVehiculo) {
        Vehiculo vehiculoARetirar = null;
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getId() == idVehiculo) {
                vehiculoARetirar = vehiculo;
                break;
            }
        }

        if (vehiculoARetirar != null) {
            boolean exito;
            double precio = 0;
            if (vehiculoARetirar instanceof Auto) {
                exito = quitarAuto.ejecutar(vehiculoARetirar.getId());
                precio = precios.getPrecioAuto();
            } else {
                exito = quitarMoto.ejecutar(vehiculoARetirar.getId());
                precio = precios.getPrecioMoto();
            }

            if (exito) {
                vehiculos.remove(vehiculoARetirar);
                totalCobrado += precio;
                JOptionPane.showMessageDialog(null, "Vehículo retirado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al retirar el vehículo.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vehículo no encontrado.");
        }
    }

    private void consultarVehiculos() {
        // Definir las columnas para el JTable
        String[] columnNames = {
            "ID", "Color", "Marca", "Patente", "Kilometraje", 
            "Número de Chasis", "Número de Motor", "Tipo", "Detalles Específicos"
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    
        // Llenar el modelo con los datos de los vehículos
        for (Vehiculo vehiculo : vehiculos) {
            String tipo = vehiculo instanceof Auto ? "Auto" : "Moto";
            String detallesEspecificos = vehiculo instanceof Auto
                ? "Puertas: " + ((Auto) vehiculo).getCantidadPuertas() + ", Ruedas: " + ((Auto) vehiculo).getCantidadRuedas()
                : "Cilindrada: " + ((Moto) vehiculo).getCilindrada() + ", Ruedas: " + ((Moto) vehiculo).getCantidadRuedas();
    
            model.addRow(new Object[]{
                vehiculo.getId(), vehiculo.getColor(), vehiculo.getMarca(), vehiculo.getPatente(),
                vehiculo.getKilometraje(), vehiculo.getNumeroChasis(), vehiculo.getNumeroMotor(),
                tipo, detallesEspecificos
            });
        }
    
        // Crear la tabla y el panel de desplazamiento
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
    
        // Mostrar el diálogo con el JTable
        JOptionPane.showMessageDialog(null, scrollPane, "Vehículos en el Garaje", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarCaja() {
        String[] columnNames = {"ID", "Patente", "Cantidad de Ruedas", "Costo"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        double total = 0;

        for (Vehiculo vehiculo : vehiculos) {
            int cantidadRuedas = (vehiculo instanceof Auto) ? ((Auto) vehiculo).getCantidadRuedas() : ((Moto) vehiculo).getCantidadRuedas();
            double costo = (vehiculo instanceof Auto) ? precios.getPrecioAuto() * cantidadRuedas : precios.getPrecioMoto() * cantidadRuedas;
            total += costo;
            model.addRow(new Object[]{vehiculo.getId(), vehiculo.getPatente(), cantidadRuedas, formatCurrency(costo)});
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JOptionPane.showMessageDialog(null, scrollPane, "TOTAL: $" + formatCurrency(total), JOptionPane.INFORMATION_MESSAGE);
    }

    private String formatCurrency(double amount) {
        @SuppressWarnings("deprecation")
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
        return currencyFormat.format(amount);
    }

    private void mostrarEstadisticas() {
        double kilometrajeMedio = estadisticas.calcularKilometrajeMedio();
        JOptionPane.showMessageDialog(null, "Kilometraje medio de todos los vehículos en el garaje: " + kilometrajeMedio);
    }
}
