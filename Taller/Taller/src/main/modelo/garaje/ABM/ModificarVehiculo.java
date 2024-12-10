package main.modelo.garaje.ABM;

import java.util.List;

import javax.swing.JOptionPane;

import main.modelo.vehiculos.Auto;
import main.modelo.vehiculos.Moto;
import main.modelo.vehiculos.Vehiculo;

public class ModificarVehiculo {
    private static List<Vehiculo> vehiculos;
    
        public static void modificarVehiculo() {
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

    //getter y setters
    public static void setVehiculos(List<Vehiculo> vehiculosList) {vehiculos = vehiculosList;}

    public static void setMotos(List<Moto> motos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMotos'");
    }
}
