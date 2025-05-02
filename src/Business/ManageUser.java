package Business;

import Business.Entidades.User;
import Persistence.sql.SQLUserDAO;
import Presenstation.Controller.SignUpController;
import Presenstation.ErrorMessages;
import Presenstation.View.Scenes.Scene;
import Presenstation.View.Scenes.Scenes;

import javax.swing.*;

public class ManageUser {
    private SQLUserDAO sqlUserDAO = new SQLUserDAO();
    private User currentUser;
    private ErrorMessages errorMessages = new ErrorMessages();


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
    public boolean signUp(String name, String email, String password, Scene scene, String passwordConfirmation) {
        boolean flag = false;
        if(!ErroreOcurred(name , email, password, scene, passwordConfirmation)){
            if(sqlUserDAO.findUserByEmail(email) == null && sqlUserDAO.findUserByUsername(name) == null){
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setUsername(name);
                currentUser = user;
                sqlUserDAO.insertUser(user);
                flag = true;
            }else{
                if(sqlUserDAO.findUserByEmail(email) != null){
                    errorMessages.emailAlreadyExists();
                }else{
                    errorMessages.usernameAlreadyExists();
                }

            }
        }

        return flag;
    }
    public boolean ErroreOcurred(String name, String email, String password, Scene scene, String password2) {
        if (email == null || email.isEmpty()) {
            errorMessages.emptyEmail();
            return true;
        }

        if (!email.contains("@")) {
            errorMessages.notValidEmail();
            return true;
        }


        if (password.isEmpty()) {
            errorMessages.emptyPassword();
            return true;
        }
        if(password.equals(password2)){

            boolean hasMinLength = password.length() >= 8;
            boolean hasUpperCase = password.matches(".*[A-Z].*");
            boolean hasLowerCase = password.matches(".*[a-z].*");
            boolean hasDigit = password.matches(".*\\d.*");

            if (!hasMinLength) {
                errorMessages.missingCharacters();
                return true;
            }
            if (!hasUpperCase) {
                errorMessages.missingCapitalLetters();
                return true;
            }
            if (!hasLowerCase) {

                errorMessages.missingLowercaseLetters();
                return true;
            }
            if (!hasDigit) {
                errorMessages.missingNumber();
                return true;
            }
            return false;

        }else{
           errorMessages.dontMatch();
            return true;
        }
    }
    public User getCurrentUser() {
        return currentUser;
    }


}
