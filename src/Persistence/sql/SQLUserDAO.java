package Persistence.sql;

import Persistence.UserDAO;
import Business.Entidades.User;

import java.util.List;



/**
 * Implementación de UserDAO que gestiona usuarios en una base de datos MySQL mediante SQL.
 */
public class SQLUserDAO implements UserDAO {

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param user Usuario a insertar.
     */
    @Override
    public void insertUser(User user) {
        String query = "INSERT INTO User(nombre_usuario, email, contrasena) VALUES ('" +
                user.getUsername() + "', '" + user.getEmail() + "', '" + user.getPassword() + "')";

        SQLConnector.getInstance().insertQuery(query);
    }

    /**
     * Elimina un usuario de la base de datos junto con sus partidas y registros históricos asociados.
     *
     * @param user Usuario a eliminar.
     */
    @Override
    public void deleteUser(User user) {
        String selectPartidas = "SELECT id_partida FROM Partida WHERE id_usuario = '" + user.getId() + "'";
        var rs = SQLConnector.getInstance().selectQuery(selectPartidas);

        try {
            while (rs != null && rs.next()) {
                int partidaId = rs.getInt("id_partida");

                // Paso 2: Borrar primero los registros dependientes de cada partida
                String deleteHistorico = "DELETE FROM estadisticas WHERE id_partida = '" + partidaId + "'";
                SQLConnector.getInstance().deleteQuery(deleteHistorico);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving partida IDs: " + e.getMessage());
        }

        String deleteGames = "DELETE FROM Partida WHERE id_usuario = '" + user.getId() + "'";
        SQLConnector.getInstance().deleteQuery(deleteGames);

        // Paso 4: Finalmente borrar el usuario
        String deleteUser = "DELETE FROM User WHERE id_usuario = '" + user.getId() + "'";
        SQLConnector.getInstance().deleteQuery(deleteUser);
    }

    /**
     * Busca un usuario en la base de datos por su correo electrónico.
     *
     * @param email Email del usuario a buscar.
     * @return Usuario encontrado o null si no existe.
     */
    @Override
    public User findUserByEmail(String email) {
        String query = "SELECT * FROM User WHERE email = '" + email + "'";
        var rs = SQLConnector.getInstance().selectQuery(query);

        try {
            if (rs != null && rs.next()) {
                return new User(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre_usuario"),
                        rs.getString("contrasena"),
                        rs.getString("email")
                );
            }
        } catch (Exception e) {
            System.err.println("Error retrieving user by email: " + e.getMessage());
        }

        return null;
    }

    /**
     * Busca un usuario en la base de datos por su nombre de usuario.
     *
     * @param username Nombre de usuario a buscar.
     * @return Usuario encontrado o null si no existe.
     */
    @Override
    public User findUserByUsername(String username) {
        String query = "SELECT * FROM User WHERE nombre_usuario = '" + username + "'";
        var rs = SQLConnector.getInstance().selectQuery(query);

        try {
            if (rs != null && rs.next()) {
                return new User(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre_usuario"),
                        rs.getString("contrasena"),
                        rs.getString("email")
                );
            }
        } catch (Exception e) {
            System.err.println("Error retrieving user by username: " + e.getMessage());
        }

        return null;
    }


}