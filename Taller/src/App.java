import main.controlador.GarajeController;
import main.modelo.DatabaseSetup;

public class App {
    
    public static void main(String[] args) {
        //crea la base de datos
        DatabaseSetup.setupDatabase();

        //corre el programa
        String[] menuOptions = {"Ingresar Vehículo", "Retirar Vehículo", "Consultar Vehículos", "Caja", "Estadísticas", "Modificar Vehículo", "Salir"};
        GarajeController controller = new GarajeController(menuOptions, false); // false indica que no es modo de prueba
        controller.iniciar();
    }
}