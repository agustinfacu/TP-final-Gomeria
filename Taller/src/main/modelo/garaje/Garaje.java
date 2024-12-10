package main.modelo.garaje;

import main.modelo.garaje.ABMbase.AgregarAuto;
import main.modelo.garaje.ABMbase.AgregarMoto;
import main.modelo.garaje.ABMbase.QuitarAuto;
import main.modelo.garaje.ABMbase.QuitarMoto;
import main.modelo.vehiculos.Auto;
import main.modelo.vehiculos.Moto;

public class Garaje {
    private CapacidadMaxima capacidadMaxima;
    private AgregarAuto agregarAuto;
    private QuitarAuto quitarAuto;
    private AgregarMoto agregarMoto;
    private QuitarMoto quitarMoto;
    private Caja precios;
    private Estadisticas estadisticas;

    // Modificación: ahora se reciben dos precios, uno para autos y otro para motos
    public Garaje(int capacidadMaxima, double precioCambioRuedaAuto, double precioCambioRuedaMoto) {
        this.capacidadMaxima = new CapacidadMaxima(capacidadMaxima);
        this.agregarAuto = new AgregarAuto();
        this.quitarAuto = new QuitarAuto();
        this.agregarMoto = new AgregarMoto();
        this.quitarMoto = new QuitarMoto();
        this.precios = new Caja(precioCambioRuedaAuto, precioCambioRuedaMoto); // Inicialización corregida
        this.estadisticas = new Estadisticas();
    }

    public boolean agregarAuto(Auto auto) {
        if (capacidadMaxima.hayEspacioDisponible()) {
            return agregarAuto.ejecutar(auto);
        } else {
            System.out.println("No hay espacio disponible en el garaje.");
            return false;
        }
    }

    public boolean quitarAuto(int idVehiculo) {
        return quitarAuto.ejecutar(idVehiculo);
    }

    public boolean agregarMoto(Moto moto) {
        if (capacidadMaxima.hayEspacioDisponible()) {
            return agregarMoto.ejecutar(moto);
        } else {
            System.out.println("No hay espacio disponible en el garaje.");
            return false;
        }
    }

    public boolean quitarMoto(int idVehiculo) {
        return quitarMoto.ejecutar(idVehiculo);
    }

    // Modificación: calcular el precio dependiendo del tipo de vehículo
    public double calcularPrecioCambioRuedas(String tipoVehiculo) {
        if ("auto".equalsIgnoreCase(tipoVehiculo)) {
            return precios.getPrecioAuto(); // Precio para autos
        } else if ("moto".equalsIgnoreCase(tipoVehiculo)) {
            return precios.getPrecioMoto(); // Precio para motos
        } else {
            System.out.println("Tipo de vehículo no reconocido.");
            return 0;
        }
    }

   
}
