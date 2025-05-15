import Persistence.sql.SQLConnector;
import Presenstation.Controller.MainController;

public class Main {
    public static void main(String[] args) {
        if (!SQLConnector.getInstance().isConnected()) {
            System.err.println("❌ No se pudo establecer conexión con la base de datos. Cerrando aplicación.");
            System.exit(1);
        }
        MainController mainController = new MainController();
        mainController.run();

    }
}