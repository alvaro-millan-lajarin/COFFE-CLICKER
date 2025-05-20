package Business;

import Business.Entidades.User;
import Persistence.UserDAO;
import Persistence.sql.SQLUserDAO;
import Presenstation.Messages;
import Presenstation.View.Scenes.Scene;

/**
 * Clase que gestiona las operaciones relacionadas con los usuarios,
 * como el inicio de sesión, registro, validaciones y eliminación de cuenta.
 */
public class ManageUser {
    private final UserDAO userDAO;
    private User currentUser;
    private final Messages messages;
    private final static int MAX_LENGTH = 50;

    /**
     * Constructor de ManageUser.
     *
     * @param userDAO DAO para el acceso a datos de usuarios.
     */
    public ManageUser(UserDAO userDAO) {

        this.userDAO = userDAO;
        messages = new Messages();
    }

    /**
     * Verifica si los datos de inicio de sesión son correctos.
     * Puede usar nombre de usuario o correo electrónico.
     *
     * @param userOrEmail Nombre de usuario o email introducido.
     * @param password Contraseña proporcionada.
     * @return true si las credenciales son correctas, false en caso contrario.
     */
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

    /**
     * Registra un nuevo usuario si no existen conflictos y si los datos son válidos.
     *
     * @param name Nombre de usuario.
     * @param email Correo electrónico.
     * @param password Contraseña.
     * @param scene Escena actual para mostrar mensajes si hay errores.
     * @param passwordConfirmation Confirmación de la contraseña.
     * @return true si el registro fue exitoso, false si hubo errores.
     */
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

    /**
     * Comprueba si hay algún error en los datos del formulario de registro.
     * Valida campos vacíos, longitud, formato de email y fuerza de la contraseña.
     *
     * @param name Nombre de usuario.
     * @param email Correo electrónico.
     * @param password Contraseña.
     * @param scene Escena para mostrar mensajes (no se usa directamente).
     * @param password2 Confirmación de la contraseña.
     * @return true si hay errores, false si todo es válido.
     */
    public boolean ErroreOcurred(String name, String email, String password, Scene scene, String password2) {

        if (name.length() > MAX_LENGTH || email.length() > MAX_LENGTH || password.length() > MAX_LENGTH || password2.length() > MAX_LENGTH) {
            System.out.println("Error: Uno o más campos exceden el límite de " + MAX_LENGTH + " caracteres.");
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

    /**
     * Elimina el usuario actualmente logueado de la base de datos.
     */
    public void deleteUser() {
        userDAO.deleteUser(currentUser);
    }

    /**
     * Devuelve el usuario actualmente logueado.
     *
     * @return Usuario activo o null si no hay sesión iniciada.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Cierra la sesión actual.
     */
    public void logout() {
        this.currentUser = null;
    }
}
