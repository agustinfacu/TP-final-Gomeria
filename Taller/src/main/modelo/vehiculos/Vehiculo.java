package main.modelo.vehiculos;

// CLASE ABSTRACTA

public abstract class Vehiculo {
    private final int id;
    private String color;
    private String marca;
    private String patente;
    protected int kilometraje;
    private String numeroChasis;
    private String numeroMotor;
    
        public Vehiculo(int id, String color, String marca, String patente, int kilometraje, String numeroChasis, String numeroMotor) {
            this.id = id;
            this.color = color;
            this.marca = marca;
            this.patente = patente;
            this.kilometraje = kilometraje;
            this.numeroChasis = numeroChasis;
            this.numeroMotor = numeroMotor;
        }
    
        // Métodos getters y setters para los demás atributos
        public String getColor() { return color; }
        public String getMarca() { return marca; }
        public String getPatente() { return patente; }
        public int getKilometraje() { return kilometraje; }
        public String getNumeroChasis() { return numeroChasis; }
        public String getNumeroMotor() { return numeroMotor; }
        public int getId(){return id;}
        public void setPatente(String patente) {this.patente = patente;}
        public void setKilometraje(int kilometraje) {this.kilometraje = kilometraje;}
        public void setNumeroChasis(String numeroChasis) {this.numeroChasis = numeroChasis;}
        public void setNumeroMotor(String numeroMotor) {this.numeroMotor = numeroMotor;}

}
