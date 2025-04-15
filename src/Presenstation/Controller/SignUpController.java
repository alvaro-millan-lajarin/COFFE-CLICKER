package Presenstation.Controller;

import Business.Entidades.User;
import Persistence.sql.SQLUserDAO;
import Presenstation.View.LoginScene;
import Presenstation.View.Scene;
import Presenstation.View.Scenes;
import Presenstation.View.SignUpScene;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SignUpController extends Controller {
    private Integer errorOcurred = 0;

    public SignUpController(Scene view, MainController mainController) {
        super(view, mainController);

    }
    public SignUpScene getScene() {

        return (SignUpScene) super.getView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("SIGNUP")) {
            SQLUserDAO sqlUserDAO = new SQLUserDAO();
            mainController.resetGameManagement();

            if(!ErroreOcurred()){
                if(sqlUserDAO.findUserByEmail(getScene().getEmail()) == null && sqlUserDAO.findUserByUsername(getScene().getName()) == null){
                    User user = new User();
                    user.setEmail(getScene().getEmail());
                    user.setPassword(getScene().getPassword());
                    user.setUsername(getScene().getName());

                    sqlUserDAO.insertUser(user);
                    mainController.nextScene(Scenes.GAME_MANAGEMENT);
                }else{
                    if(sqlUserDAO.findUserByEmail(getScene().getEmail()) != null){
                        JOptionPane.showMessageDialog(
                                getScene().addAccesButton(),
                                "Email already exists",
                                "Email Error",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }else{
                        JOptionPane.showMessageDialog(
                                getScene().addAccesButton(),
                                "Username already exists",
                                "Username Error",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }

                }
            }
        }

    }
    public boolean ErroreOcurred() {
        if (getScene().getEmail().isEmpty()) {
            JOptionPane.showMessageDialog(
                    getScene().addAccesButton(),
                    "Please you need to enter your email",
                    "Email is empty",
                    JOptionPane.WARNING_MESSAGE
            );
            return true;
        }

        String email = getScene().getEmail();

            if (!email.contains("@")) {
                JOptionPane.showMessageDialog(
                        getScene().addAccesButton(),
                        "You need to put a real email with: @ ",
                        "Not a valid email",
                        JOptionPane.WARNING_MESSAGE
                );
                return true;
            }


        if (getScene().getPassword().isEmpty()) {
            JOptionPane.showMessageDialog(
                    getScene().addAccesButton(),
                    "Please you need to enter a password",
                    "Password is empty",
                    JOptionPane.WARNING_MESSAGE
            );
            return true;
        }
            if(getScene().getPassword().equals(getScene().getPasswordAgain())){
                String password = getScene().getPassword();

                boolean hasMinLength = password.length() >= 8;
                boolean hasUpperCase = password.matches(".*[A-Z].*");
                boolean hasLowerCase = password.matches(".*[a-z].*");
                boolean hasDigit = password.matches(".*\\d.*");

                if (!hasMinLength) {
                    JOptionPane.showMessageDialog(
                            getScene().addAccesButton(),
                            "Please you need to enter a password that contains at least 8 characters",
                            "Missing characters",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return true;
                }
                if (!hasUpperCase) {
                    JOptionPane.showMessageDialog(
                            getScene().addAccesButton(),
                            "Please you need to enter a password that contains capital letter",
                            "Missing capital letter",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return true;
                }
                if (!hasLowerCase) {
                    JOptionPane.showMessageDialog(
                            getScene().addAccesButton(),
                            "Please you need to enter a password that contains lowercase letter",
                            "Missing lower case",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return true;
                }
                if (!hasDigit) {
                    JOptionPane.showMessageDialog(
                            getScene().addAccesButton(),
                            "Please you need to enter a password that contains al least one number",
                            "Missing Number",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return true;
                }
                return false;

            }else{
                JOptionPane.showMessageDialog(
                        getScene().addAccesButton(),
                        "Please you need to enter a password that matches the password again",
                        "Don't match",
                        JOptionPane.WARNING_MESSAGE
                );
                return true;
            }
    }
    public String getEmail() {
        return getScene().getEmail();
    }
}
