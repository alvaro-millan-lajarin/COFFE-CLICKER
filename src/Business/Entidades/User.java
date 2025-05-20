package Business.Entidades;

/**
 * Clase que representa a un usuario del sistema.
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String email;

    /**
     * Constructor con parámetros para crear un usuario.
     *
     * @param id ID del usuario.
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     * @param email Correo electrónico del usuario.
     */
    public User(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Constructor vacío de la clase User.
     */
    public User() {}

    /**
     * Devuelve el ID del usuario.
     *
     * @return ID del usuario.
     */
    public int getId() {
        return id;
    }

    /**
     * Devuelve el nombre de usuario.
     *
     * @return Nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param username Nuevo nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Devuelve la contraseña del usuario.
     *
     * @return Contraseña.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password Nueva contraseña.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Devuelve el correo electrónico del usuario.
     *
     * @return Email del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email Nuevo email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
