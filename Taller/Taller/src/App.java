import main.controlador.GarajeController;

public class App {
    public static void main(String[] args) {
        String[] menuOptions = {"Ingresar Vehículo", "Retirar Vehículo", "Consultar Vehículos", "Caja", "Estadísticas", "Modificar Vehículo", "Salir"};
        GarajeController controller = new GarajeController(menuOptions, false); // false indica que no es modo de prueba
        controller.iniciar();
    }
}