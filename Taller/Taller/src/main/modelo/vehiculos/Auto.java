package main.modelo.vehiculos;

// CLASE INSTANCIABLE

public class Auto extends Vehiculo {
    private int cantidadPuertas;
    private int cantidadRuedas;

    public Auto(int id, String color, String marca, String patente, int kilometraje, String numeroChasis, String numeroMotor, int cantidadPuertas, int cantidadRuedas) {
        super(id, color, marca, patente, kilometraje, numeroChasis, numeroMotor);
        this.cantidadPuertas = cantidadPuertas;
        this.cantidadRuedas = cantidadRuedas;
    }

    // Getters y setters para cantidadPuertas y cantidadRuedas

    public int getCantidadPuertas() {return this.cantidadPuertas;}
    public void setCantidadPuertas(int cantidadPuertas) {this.cantidadPuertas = cantidadPuertas;}
    public int getCantidadRuedas() {return this.cantidadRuedas;}
    public void setCantidadRuedas(int cantidadRuedas) {this.cantidadRuedas = cantidadRuedas;}
}