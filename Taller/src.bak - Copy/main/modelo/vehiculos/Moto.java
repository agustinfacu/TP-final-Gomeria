package main.modelo.vehiculos;

public class Moto extends Vehiculo {
    private String cilindrada;
    private int cantidadRuedas;

    public Moto(int id, String color, String marca, int kilometraje, String numeroChasis, String numeroMotor, String patente, String cilindrada, int cantidadRuedas) {
        super(id, color, marca, patente, kilometraje, numeroChasis, numeroMotor);
        this.cilindrada = cilindrada;
        this.cantidadRuedas = cantidadRuedas;
    }

    // Getters y setters para cilindrada y cantidadRuedas
    public String getCilindrada() {return this.cilindrada;}
    public void setCilindrada(String cilindrada) {this.cilindrada = cilindrada;}
    public int getCantidadRuedas() {return this.cantidadRuedas;}
    public void setCantidadRuedas(int cantidadRuedas) {this.cantidadRuedas = cantidadRuedas;}
}