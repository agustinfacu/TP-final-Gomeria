package main.controlador;

public class Validaciones {

    public static boolean validarColor(String color) {
        return color != null && !color.trim().isEmpty();
    }

    public static boolean validarMarca(String marca) {
        return marca != null && !marca.trim().isEmpty();
    }

    public static boolean validarPatente(String patente, boolean esNueva) {
        if (patente == null) {
            return false;
        }
        patente = patente.toUpperCase(); // Convertir a mayúsculas para la validación
        if (esNueva) {
            return patente.matches("[A-Z]{2}[0-9]{3}[A-Z]{2}");
        } else {
            return patente.matches("[A-Z]{3}[0-9]{3}");
        }
    }

    public static boolean validarNumeroChasis(String numeroChasis) {
        return numeroChasis != null && numeroChasis.length() == 17;
    }

    public static boolean validarNumeroMotor(String numeroMotor) {
        return numeroMotor != null && numeroMotor.length() == 20;
    }

    public static boolean validarCilindrada(String cilindrada) {
        return cilindrada != null && !cilindrada.trim().isEmpty();
    }

    public static boolean validarCantidadPuertas(int cantidadPuertas) {
        return cantidadPuertas >= 2 && cantidadPuertas <= 5;
    }

    public static boolean validarCantidadRuedasAuto(int cantidadRuedas) {
        return cantidadRuedas >= 4 && cantidadRuedas <= 10;
    }

    public static boolean validarCantidadRuedasMoto(int cantidadRuedas) {
        return cantidadRuedas >= 2 && cantidadRuedas <= 3;
    }

    public static boolean validarCapacidadGaraje(int capacidad) {
        return capacidad > 0;
    }

    public static boolean validarPrecio(double precio) {
        return precio > 0;
    }
}