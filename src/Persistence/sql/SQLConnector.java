package Persistence.sql;

import Business.Entidades.Config;
import Persistence.configJsonDAO;

import java.sql.*;

/**
 * Clase que gestiona la conexión a la base de datos MySQL.
 * Implementa el patrón Singleton y ofrece métodos básicos para ejecutar consultas SQL.
 */
public class SQLConnector {
    private static SQLConnector instance = null;
    private Connection conn;

    /**
     * Constructor privado que establece la conexión a partir de una configuración dada.
     *
     * @param config Objeto de configuración con los datos de conexión.
     */
    private SQLConnector(Config config) {
        String url = "jdbc:mysql://" + config.getPortConexionBD() + ":" + config.getIpBD() + "/" + config.getNomBD();
        try {
            conn = DriverManager.getConnection(url, config.getAccesUserBD(), config.getPasswordBD());
        } catch (SQLException e) {
            if (e.getMessage().contains("Unknown database")) {
                System.err.println(" La base de datos '" + config.getNomBD() + "' no existe. ");
            } else {
                System.err.println(" Error al conectar con la base de datos: " + e.getMessage());
            }
            conn = null;
        }
    }

    /**
     * Devuelve la instancia única de SQLConnector.
     * Si no existe, la crea usando la configuración del archivo JSON.
     *
     * @return Instancia única de SQLConnector.
     */
    public static SQLConnector getInstance() {
        if (instance == null) {
            try {
                configJsonDAO jsonDAO = new configJsonDAO();
                Config config = jsonDAO.getConfigDAO();  // Usamos la config única del JSON
                instance = new SQLConnector(config);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * Ejecuta una consulta SQL de inserción (INSERT).
     *
     * @param query Consulta SQL a ejecutar.
     */
    public void insertQuery(String query){
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when inserting --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
    }

    /**
     * Ejecuta una consulta SQL de actualización (UPDATE).
     *
     * @param query Consulta SQL a ejecutar.
     */
    public void updateQuery(String query){
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problema when updating --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
    }

    /**
     * Ejecuta una consulta SQL de eliminación (DELETE).
     *
     * @param query Consulta SQL a ejecutar.
     */
    public void deleteQuery(String query){
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when deleting --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
    }

    /**
     * Ejecuta una consulta SQL de selección (SELECT) y devuelve el resultado.
     *
     * @param query Consulta SQL a ejecutar.
     * @return ResultSet con los datos seleccionados, o null si hubo error.
     */
    public ResultSet selectQuery(String query){
        ResultSet rs = null;
        if (conn == null) {
            System.err.println(" No hay conexión a la base de datos. " );
            return null;
        }
        try {
            Statement s = conn.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
        return rs;
    }

    /**
     * Indica si la conexión a la base de datos está activa.
     *
     * @return true si hay conexión, false en caso contrario.
     */
    public boolean isConnected() {
        return conn != null;
    }

}
