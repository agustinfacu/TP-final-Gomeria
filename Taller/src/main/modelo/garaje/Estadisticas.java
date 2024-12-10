package main.modelo.garaje;

import main.modelo.vehiculos.Vehiculo;
import java.util.List;

public class Estadisticas {

    // Método para calcular el kilometraje medio basado en los vehículos en memoria
    public double calcularKilometrajeMedio(List<Vehiculo> vehiculos) {
        double kilometrajeTotal = 0;
        int cantidadVehiculos = 0;

        for (Vehiculo vehiculo : vehiculos) {
            kilometrajeTotal += vehiculo.getKilometraje();  // Suponiendo que getKilometraje() es el método que obtiene el kilometraje
            cantidadVehiculos++;
        }

        if (cantidadVehiculos > 0) {
            return kilometrajeTotal / cantidadVehiculos;  // Kilometraje medio
        }
        return 0;  // Si no hay vehículos, retorna 0
    }

    // Método para calcular la suma total de los kilometrajes desde los vehículos en memoria
    public double calcularSumaKilometrajes(List<Vehiculo> vehiculos) {
        double kilometrajeTotal = 0;

        for (Vehiculo vehiculo : vehiculos) {
            kilometrajeTotal += vehiculo.getKilometraje();  // Suponiendo que getKilometraje() es el método que obtiene el kilometraje
        }

        return kilometrajeTotal;
    }
}
