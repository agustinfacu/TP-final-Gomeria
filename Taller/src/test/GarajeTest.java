package test;

import main.controlador.GarajeController;
import main.modelo.DatabaseSetup;

public class GarajeTest {
    public static void main(String[] args) {
        //crea la base de datos
        DatabaseSetup.setupDatabase();

        //corre el programa de testeo
        String[] menuOptions = {"Importar Vehículos", "Consultar Vehículos", "Caja", "Estadísticas", "Salir"};
        GarajeController controller = new GarajeController(menuOptions, true); // true indica que es modo de prueba
        controller.iniciar();
    }
}