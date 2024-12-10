package test;

import main.controlador.GarajeController;

public class GarajeTest {
    public static void main(String[] args) {
        String[] menuOptions = {"Importar Vehículos", "Consultar Vehículos", "Caja", "Estadísticas", "Salir"};
        GarajeController controller = new GarajeController(menuOptions, true); // true indica que es modo de prueba
        controller.iniciar();
    }
}