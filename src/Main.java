import Persistence.sql.SQLConnector;
import Presenstation.Controller.MainController;

/**
 * Clase principal del programa. Punto de entrada de la aplicación Coffee Clicker.
 * Verifica la conexión con la base de datos antes de lanzar la interfaz gráfica principal.
 */
public class Main {
    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     * <p>
     * Verifica si la conexión a la base de datos puede establecerse correctamente.
     * Si la conexión falla, muestra un mensaje de error y finaliza la ejecución.
     * Si tiene éxito, instancia y lanza el controlador principal de la aplicación.
     *
     * @param args argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        if (!SQLConnector.getInstance().isConnected()) {
            System.err.println(" No se pudo establecer conexión con la base de datos. Cerrando aplicación.");
            System.exit(1);
        }
        MainController mainController = new MainController();
        mainController.run();

    }
}