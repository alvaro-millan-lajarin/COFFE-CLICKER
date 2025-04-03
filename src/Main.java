import Business.Config;
import Business.entity.Game;
import Business.entity.User;
import Persistence.configJsonDAO;
import Persistence.sql.SQLConnector;
import Persistence.sql.SQLGameDAO;
import Persistence.sql.SQLUserDAO;
import Presenstation.Controller.MainController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //MainController mainController = new MainController();
        //mainController.run();

        SQLUserDAO dao = new SQLUserDAO();
        User user = new User(3, "juan", "1234", "aaa@.com");

        dao.deleteUser(user);
    }

}
