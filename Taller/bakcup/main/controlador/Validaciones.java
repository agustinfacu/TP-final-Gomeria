package main.controlador;
//las validaciones del input de usuario al ingresar vehiculos
public class Validaciones {

    public static boolean validarColor(String color) {  //color
        return color != null && !color.trim().isEmpty();
    }

    public static boolean validarMarca(String marca) {  //marca
        return marca != null && !marca.trim().isEmpty();
    }

    public static boolean validarPatente(String patente) {  
        return patente != null && patente.matches("[A-Z]{3}[0-9]{4}");  //patente
    }

    public static boolean validarNumeroChasis(String numeroChasis) {
        return numeroChasis != null && numeroChasis.matches("[A-HJ-NPR-Z0-9]{17}"); //chasis
    }

    public static boolean validarNumeroMotor(String numeroMotor) {
        return numeroMotor != null && numeroMotor.matches("[A-Z0-9]{5,20}");    //motor
    }

    public static boolean validarCilindrada(String cilindrada) {
        return cilindrada != null && !cilindrada.trim().isEmpty();  //cilindrada
    }

    public static boolean validarCantidadPuertas(int cantidadPuertas) { //puertas
        return cantidadPuertas >= 2 && cantidadPuertas <= 5;
    }

    public static boolean validarCantidadRuedasAuto(int cantidadRuedas) {   //ruedas auto
        return cantidadRuedas >= 4 && cantidadRuedas <= 10;
    }

    public static boolean validarCantidadRuedasMoto(int cantidadRuedas) {   //ruedas moto
        return cantidadRuedas >= 2 && cantidadRuedas <= 3;
    }

    public static boolean validarCapacidadGaraje(int capacidad) {   //capacidad garaje
        return capacidad > 0;
    }

    public static boolean validarPrecio(double precio) {    //monto del precio
        return precio > 0;
    }
}