package Persistence.sql;

import Business.Entidades.Config;
import Persistence.configJsonDAO;

import java.sql.*;

public class SQLConnector {
    private static SQLConnector instance = null;
    private String username;
    private String password;
    private String url;
    private Connection conn;

    private SQLConnector(Config config) {
        String url = "jdbc:mysql://" + config.getPortConexionBD() + ":" + config.getIpBD() + "/" + config.getNomBD();
        try {
            conn = DriverManager.getConnection(url, config.getAccesUserBD(), config.getPasswordBD());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    private SQLConnector(String username, String password, String ip, int port, String database) {
        this.username = username;
        this.password = password;
        this.url = "jdbc:mysql://" + ip + ":" + port + "/" + database;
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch(SQLException e) {
            System.err.println("Couldn't connect to --> " + url + " (" + e.getMessage() + ")");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertQuery(String query){
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when inserting --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
    }

    public void updateQuery(String query){
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problema when updating --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
    }



    public void deleteQuery(String query){
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when deleting --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
    }

    public ResultSet selectQuery(String query){
        ResultSet rs = null;
        try {
            Statement s = conn.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
        return rs;
    }

    public void disconnect(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem when closing the connection --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
    }
}
