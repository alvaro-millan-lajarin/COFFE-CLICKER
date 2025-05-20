package Presenstation;

import javax.swing.*;

/**
 * Clase que agrupa todos los diálogos de mensajes mostrados al usuario mediante JOptionPane.
 * Incluye advertencias, confirmaciones e información general del flujo de la aplicación.
 */
public class Messages {

    /**
     * Muestra un mensaje de advertencia si el nombre de la partida es demasiado largo.
     */
    public void tooLong() {
        JOptionPane.showMessageDialog(
                null,
                "El nombre de la partida es demasiado largo",
                "Too Long",
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Muestra un mensaje indicando que el campo de correo está vacío.
     */
    public void emptyEmail(){
        JOptionPane.showMessageDialog(
                null,
                "Please you need to enter your email",
                "Email is empty",
                JOptionPane.WARNING_MESSAGE
        );

    }

    /**
     * Muestra un mensaje indicando que el email ya está registrado en el sistema.
     */
    public void emailAlreadyExists(){
        JOptionPane.showMessageDialog(
                null,
                "Email already exists",
                "Email Error",
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Muestra un mensaje indicando que el nombre de usuario ya está en uso.
     */
    public void usernameAlreadyExists(){
        JOptionPane.showMessageDialog(
                null,
                "Username already exists",
                "Username Error",
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Muestra un mensaje de error cuando el email introducido no es válido (sin @).
     */
    public void notValidEmail(){
        JOptionPane.showMessageDialog(
                null,
                "You need to put a real email with: @ ",
                "Not a valid email",
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Muestra un mensaje indicando que el campo de contraseña está vacío.
     */
    public void emptyPassword(){
        JOptionPane.showMessageDialog(
                null,
                "Please you need to enter a password",
                "Password is empty",
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Muestra un mensaje indicando que la contraseña necesita al menos 8 caracteres.
     */
    public void missingCharacters(){
        JOptionPane.showMessageDialog(
                null,
                "Please you need to enter a password that contains at least 8 characters",
                "Missing characters",
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Muestra un mensaje indicando que la contraseña debe contener al menos una letra mayúscula.
     */
    public void missingCapitalLetters(){
        JOptionPane.showMessageDialog(
                null,
                "Please you need to enter a password that contains capital letter",
                "Missing capital letter",
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Muestra un mensaje indicando que la contraseña debe contener al menos una letra minúscula.
     */
    public void missingLowercaseLetters(){
        JOptionPane.showMessageDialog(
                null,
                "Please you need to enter a password that contains lowercase letter",
                "Missing lower case",
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Muestra un mensaje indicando que la contraseña debe contener al menos un número.
     */
    public void missingNumber(){
        JOptionPane.showMessageDialog(
                null,
                "Please you need to enter a password that contains al least one number",
                "Missing Number",
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Muestra un mensaje cuando la confirmación de contraseña no coincide con la original.
     */
    public void dontMatch(){
        JOptionPane.showMessageDialog(
                null,
                "Please you need to enter a password that matches the password again",
                "Don't match",
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Muestra un diálogo de confirmación para borrar la cuenta del usuario.
     *
     * @return JOptionPane.YES_OPTION si el usuario confirma; JOptionPane.NO_OPTION en caso contrario.
     */
    public int confirmDelete(){
        return JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to delete your account?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Muestra un mensaje de éxito al eliminar la cuenta del usuario.
     */
    public void deleteUser(){
        JOptionPane.showMessageDialog(
                null,
                "Account successfully deleted.",
                "Deletion Successful",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Muestra un mensaje pidiendo al usuario que seleccione una partida primero.
     */
    public void seleccionaPartida(){
        JOptionPane.showMessageDialog(null, "Selecciona una partida primero.");
    }

    /**
     * Muestra un mensaje indicando que las estadísticas no están disponibles para partidas no finalizadas.
     */
    public void stadisticasNoDisponibles(){
        JOptionPane.showMessageDialog(null, "Estadisticas no disponibles para partidas no finalizadas.");
    }

    /**
     * Muestra un mensaje indicando que la partida ha finalizado y no se puede reanudar.
     */
    public void gameFinishedCantResume(){
        JOptionPane.showMessageDialog(
                null,
                "Game has ended, you cannot resume the game.",
                "Game Finished",
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Muestra un cuadro de confirmación para eliminar una partida seleccionada.
     *
     * @return JOptionPane.YES_OPTION si el usuario confirma; JOptionPane.NO_OPTION en caso contrario.
     */
    public int deleteGame(){
        return JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to delete the selected game?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Muestra un mensaje indicando que la partida se ha eliminado correctamente.
     */
    public void deleteGameSucces(){
        JOptionPane.showMessageDialog(null, "Game deleted successfully.");
    }

    /**
     * Muestra un mensaje cuando el nombre de partida ya está en uso y se requiere otro.
     */
    public void gameNameUsed(){
        JOptionPane.showMessageDialog(
                null,
                "You need to put another game name",
                "Error game name used",
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Muestra un mensaje indicando que no hay suficientes cafés para realizar una acción.
     */
    public void needCoffe(){
        JOptionPane.showMessageDialog(
                null,
                "Need to generate more coffe",
                "Need more coffes",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Muestra un mensaje de error cuando el login es incorrecto (usuario o contraseña).
     */
    public void incorrectLogin(){
        JOptionPane.showMessageDialog(
                null,
                "Incorrect email, username or password",
                "Error login",
                JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Muestra un mensaje cuando no hay suficientes datos aún para generar una gráfica.
     */
    public void noHayDatosGraficar(){
        JOptionPane.showMessageDialog(null, "Necesitas estar mas tiempo en la partida.");
    }
}
