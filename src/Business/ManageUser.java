package Business;

import Business.Entidades.User;
import Persistence.sql.SQLUserDAO;
import Presenstation.Messages;
import Presenstation.View.Scenes.Scene;

public class ManageUser {
    private SQLUserDAO sqlUserDAO = new SQLUserDAO();
    private User currentUser;
    private Messages messages = new Messages();


    public boolean userLoginCorrect(String userOrEmail, String password) {
        boolean flag = false;

        currentUser = sqlUserDAO.findUserByEmail(userOrEmail);
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
                sqlUserDAO.insertUser(user);
                currentUser = sqlUserDAO.findUserByEmail(email);
                flag = true;
            }else{
                if(sqlUserDAO.findUserByEmail(email) != null){
                    messages.emailAlreadyExists();
                }else{
                    messages.usernameAlreadyExists();
                }

            }
        }

        return flag;
    }
    public boolean ErroreOcurred(String name, String email, String password, Scene scene, String password2) {
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
        sqlUserDAO.deleteUser(currentUser);
    }
    public User getCurrentUser() {
        return currentUser;
    }


}
