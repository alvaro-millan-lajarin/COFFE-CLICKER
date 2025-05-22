package Business.Entidades;
/**
 * Clase que representa la configuración de conexión a una base de datos.
 */
public class Config {
    private final String portConexionBD;
    private final int ipBD;
    private final String nomBD;
    private final String accesUserBD;
    private final String passwordBD;

    /**
     * Constructor de la clase Config.
     *
     * @param portConexion Puerto de conexión a la base de datos.
     * @param ip Dirección IP de la base de datos.y
     * @param nom Nombre de la base de datos.
     * @param acc Usuario de acceso a la base de datos.
     * @param pwd Contraseña del usuario.
     */
    public Config( String portConexion, int ip, String nom, String acc, String pwd) {
        portConexionBD = portConexion;
        ipBD = ip;
        nomBD = nom;
        accesUserBD = acc;
        passwordBD = pwd;
    }

    /**
     * Devuelve el puerto de conexión a la base de datos.
     *
     * @return Puerto de conexión.
     */
    public String getPortConexionBD() {
        return portConexionBD;
    }


    /**
     * Devuelve la dirección IP de la base de datos.
     *
     * @return Dirección IP.
     */
    public int getIpBD() {
        return ipBD;
    }

    /**
     * Devuelve el nombre de la base de datos.
     *
     * @return Nombre de la base de datos.
     */
    public String getNomBD() {
        return nomBD;
    }

    /**
     * Devuelve el nombre de usuario de acceso a la base de datos.
     *
     * @return Usuario de acceso.
     */
    public String getAccesUserBD() {
        return accesUserBD;
    }

    /**
     * Devuelve la contraseña del usuario de acceso a la base de datos.
     *
     * @return Contraseña del usuario.
     */
    public String getPasswordBD() {
        return passwordBD;
    }

}
