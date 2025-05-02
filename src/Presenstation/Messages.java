package Presenstation;

import javax.swing.*;

public class Messages {

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
    public int confirmDelete(){
        return JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to delete your account?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
    }
    public void deleteUser(){
        JOptionPane.showMessageDialog(
                null,
                "Account successfully deleted.",
                "Deletion Successful",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
    public void seleccionaPartida(){
        JOptionPane.showMessageDialog(null, "Selecciona una partida primero.");
    }
    public int deleteGame(){
        return JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to delete the selected game?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
    }
    public void deleteGameSucces(){
        JOptionPane.showMessageDialog(null, "Game deleted successfully.");
    }
    public void gameNameUsed(){
        JOptionPane.showMessageDialog(
                null,
                "You need to put another game name",
                "Error game name used",
                JOptionPane.WARNING_MESSAGE
        );
    }
}
