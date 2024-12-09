package main.modelo.vehiculos;

// CLASE ABSTRACTA e INMUTABLE
// Esta clase es inmutable para evitar que se modifiquen datos que no deberian ser modificados 

public abstract class Vehiculo {
    private final int id;
    private final String color;
    private final String marca;
    private final String patente;
    private final int kilometraje;
    private final String numeroChasis;
    private final String numeroMotor;

    public Vehiculo(int id, String color, String marca, String patente, int kilometraje, String numeroChasis, String numeroMotor) {
        this.id = id;
        this.color = color;
        this.marca = marca;
        this.patente = patente;
        this.kilometraje = kilometraje;
        this.numeroChasis = numeroChasis;
        this.numeroMotor = numeroMotor;
    }

    // Métodos getters para los demás atributos
    public String getColor() { return color; }
    public String getMarca() { return marca; }
    public String getPatente() { return patente; }
    public int getKilometraje() { return kilometraje; }
    public String getNumeroChasis() { return numeroChasis; }
    public String getNumeroMotor() { return numeroMotor; }
    public int getId(){return id;}
}
