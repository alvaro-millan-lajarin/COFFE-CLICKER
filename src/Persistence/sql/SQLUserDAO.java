package Persistence.sql;

import Business.Entidades.User;
import Persistence.UserDAO;


import java.util.List;

public class SQLUserDAO implements UserDAO {

    @Override
    public void insertUser(User user) {
        String query = "INSERT INTO User(id_usuario, nombre_usuario, email, contrasena) VALUES ('" +
                user.getId() + "', '" + user.getUsername() + "', '" + user.getEmail() + "', '" + user.getPassword() + "')";

        SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public void updateUser(User user) {
        String query = "UPDATE User SET nombre_usuario = '" + user.getUsername() +
                "', email = '" + user.getEmail() +
                "', contrasena = '" + user.getPassword() +
                "' WHERE id_usuario = '" + user.getId() + "'";

        SQLConnector.getInstance().updateQuery(query);
    }

    @Override
    public void deleteUser(User user) {
        String query = "DELETE FROM User WHERE id_usuario = '" + user.getId() + "'";
        SQLConnector.getInstance().deleteQuery(query);
    }

    @Override
    public User findUserByEmail(String email) {
        String query = "SELECT * FROM User WHERE email = '" + email + "'";
        var rs = SQLConnector.getInstance().selectQuery(query);

        try {
            if (rs != null && rs.next()) {
                return new User(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre_usuario"),
                        rs.getString("email"),
                        rs.getString("contrasena")
                );
            }
        } catch (Exception e) {
            System.err.println("Error retrieving user by email: " + e.getMessage());
        }

        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        String query = "SELECT * FROM User WHERE nombre_usuario = '" + username + "'";
        var rs = SQLConnector.getInstance().selectQuery(query);

        try {
            if (rs != null && rs.next()) {
                return new User(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre_usuario"),
                        rs.getString("email"),
                        rs.getString("contrasena")
                );
            }
        } catch (Exception e) {
            System.err.println("Error retrieving user by username: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<User> findAllUsers() {
        String query = "SELECT * FROM User";
        var rs = SQLConnector.getInstance().selectQuery(query);
        List<User> users = new java.util.ArrayList<>();

        try {
            while (rs != null && rs.next()) {
                User user = new User(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre_usuario"),
                        rs.getString("email"),
                        rs.getString("contrasena")
                );
                users.add(user);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving all users: " + e.getMessage());
        }

        return users;
    }
}
