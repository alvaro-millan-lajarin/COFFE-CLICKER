package Business;

import Business.Entidades.User;
import Persistence.UserDAO;
import Persistence.sql.SQLUserDAO;
import Presenstation.Messages;
import Presenstation.View.Scenes.Scene;

public class ManageUser {
    private final UserDAO userDAO;
    private User currentUser;
    private final Messages messages;
    private final static int MAX_LENGTH = 50;

    public ManageUser(UserDAO userDAO) {

        this.userDAO = userDAO;
        messages = new Messages();
    }


    public boolean userLoginCorrect(String userOrEmail, String password) {
        boolean flag = false;

        currentUser = userDAO.findUserByEmail(userOrEmail);
        if (currentUser == null) {
            currentUser = userDAO.findUserByUsername(userOrEmail);
        }

        if (currentUser != null && currentUser.getPassword().equals(password)) {
            flag = true;
        }

        return flag;
    }
    public boolean signUp(String name, String email, String password, Scene scene, String passwordConfirmation) {
        boolean flag = false;
        if(!ErroreOcurred(name , email, password, scene, passwordConfirmation)){
            if(userDAO.findUserByEmail(email) == null && userDAO.findUserByUsername(name) == null){
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setUsername(name);
                userDAO.insertUser(user);
                currentUser = userDAO.findUserByEmail(email);
                flag = true;
            }else{
                if(userDAO.findUserByEmail(email) != null){
                    messages.emailAlreadyExists();
                }else{
                    messages.usernameAlreadyExists();
                }

            }
        }

        return flag;
    }
    public boolean ErroreOcurred(String name, String email, String password, Scene scene, String password2) {

        if (name.length() > MAX_LENGTH || email.length() > MAX_LENGTH || password.length() > MAX_LENGTH || password2.length() > MAX_LENGTH) {
            System.out.println("❌ Error: Uno o más campos exceden el límite de " + MAX_LENGTH + " caracteres.");
            return true;
        }
        if (email == null || email.isEmpty()) {
            messages.emptyEmail();
            return true;
        }

        if (!email.contains("@")) {
            messages.notValidEmail();
            return true;
        }


        if (password.isEmpty()) {
            messages.emptyPassword();
            return true;
        }
        if(password.equals(password2)){

            boolean hasMinLength = password.length() >= 8;
            boolean hasUpperCase = password.matches(".*[A-Z].*");
            boolean hasLowerCase = password.matches(".*[a-z].*");
            boolean hasDigit = password.matches(".*\\d.*");

            if (!hasMinLength) {
                messages.missingCharacters();
                return true;
            }
            if (!hasUpperCase) {
                messages.missingCapitalLetters();
                return true;
            }
            if (!hasLowerCase) {

                messages.missingLowercaseLetters();
                return true;
            }
            if (!hasDigit) {
                messages.missingNumber();
                return true;
            }
            return false;

        }else{
           messages.dontMatch();
            return true;
        }
    }
    public void deleteUser() {
        userDAO.deleteUser(currentUser);
    }
    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        this.currentUser = null;
    }
}
