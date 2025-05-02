package Presenstation;

import javax.swing.*;

public class ErrorMessages {

    public void emptyEmail(){
        JOptionPane.showMessageDialog(
                null,
                "Please you need to enter your email",
                "Email is empty",
                JOptionPane.WARNING_MESSAGE
        );

    }
    public void emailAlreadyExists(){
        JOptionPane.showMessageDialog(
                null,
                "Email already exists",
                "Email Error",
                JOptionPane.WARNING_MESSAGE
        );
    }
    public void usernameAlreadyExists(){
        JOptionPane.showMessageDialog(
                null,
                "Username already exists",
                "Username Error",
                JOptionPane.WARNING_MESSAGE
        );
    }
    public void notValidEmail(){
        JOptionPane.showMessageDialog(
                null,
                "You need to put a real email with: @ ",
                "Not a valid email",
                JOptionPane.WARNING_MESSAGE
        );
    }
    public void emptyPassword(){
        JOptionPane.showMessageDialog(
                null,
                "Please you need to enter a password",
                "Password is empty",
                JOptionPane.WARNING_MESSAGE
        );
    }
    public void missingCharacters(){
        JOptionPane.showMessageDialog(
                null,
                "Please you need to enter a password that contains at least 8 characters",
                "Missing characters",
                JOptionPane.WARNING_MESSAGE
        );
    }
    public void missingCapitalLetters(){
        JOptionPane.showMessageDialog(
                null,
                "Please you need to enter a password that contains capital letter",
                "Missing capital letter",
                JOptionPane.WARNING_MESSAGE
        );
    }
    public void missingLowercaseLetters(){
        JOptionPane.showMessageDialog(
                null,
                "Please you need to enter a password that contains lowercase letter",
                "Missing lower case",
                JOptionPane.WARNING_MESSAGE
        );
    }
    public void missingNumber(){
        JOptionPane.showMessageDialog(
                null,
                "Please you need to enter a password that contains al least one number",
                "Missing Number",
                JOptionPane.WARNING_MESSAGE
        );
    }
    public void dontMatch(){
        JOptionPane.showMessageDialog(
                null,
                "Please you need to enter a password that matches the password again",
                "Don't match",
                JOptionPane.WARNING_MESSAGE
        );
    }
}
