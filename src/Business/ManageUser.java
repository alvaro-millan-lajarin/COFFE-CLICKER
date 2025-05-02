package Business;

import Business.Entidades.User;
import Persistence.sql.SQLUserDAO;
import Presenstation.View.Scenes.Scenes;

public class ManageUser {
    private SQLUserDAO sqlUserDAO = new SQLUserDAO();
    private User currentUser;


    public boolean userLoginCorrect(String userOrEmail, String password) {
        boolean flag = false;

        User currentUser = sqlUserDAO.findUserByEmail(userOrEmail);
        if (currentUser == null) {
            currentUser = sqlUserDAO.findUserByUsername(userOrEmail);
        }

        if (currentUser != null && currentUser.getPassword().equals(password)) {
            flag = true;
        }

        return flag;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
