package Presenstation.Controller;

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
        if (e.getActionCommand().equalsIgnoreCase("ACCES")) {
            if (getScene().getEmail().isEmpty()) {
                JOptionPane.showMessageDialog(
                        getScene().addAccesButton(),
                        "Please you need to enter your email",
                        "Email is empty",
                        JOptionPane.WARNING_MESSAGE
                );
                errorOcurred =1;
            } else {
                String email = getScene().getEmail();

                if (email.contains("@")) {

                } else {
                    JOptionPane.showMessageDialog(
                            getScene().addAccesButton(),
                            "You need to put a real email with: @ ",
                            "Not a valid email",
                            JOptionPane.WARNING_MESSAGE
                    );
                    errorOcurred =1;
                }
            }
            if (getScene().getPassword().isEmpty()) {
                JOptionPane.showMessageDialog(
                        getScene().addAccesButton(),
                        "Please you need to enter a password",
                        "Password is empty",
                        JOptionPane.WARNING_MESSAGE
                );
                errorOcurred =1;
            }else{
                if(getScene().getPassword().equals(getScene().getPasswordAgain())){

                }else{
                    JOptionPane.showMessageDialog(
                            getScene().addAccesButton(),
                            "Please you need to enter a password that matches the password again",
                            "Don't match",
                            JOptionPane.WARNING_MESSAGE
                    );
                    errorOcurred =1;
                }
            }
            if(errorOcurred == 0){
                //mainController.nextScene(Scenes.LOGIN);
            }
            errorOcurred = 0;
        }

    }
}
