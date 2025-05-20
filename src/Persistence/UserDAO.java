package Persistence;
import Business.Entidades.User;

import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para usuarios del sistema.
 */
public interface UserDAO {

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param user Usuario a insertar.
     */
    void insertUser(User user);

    /**
     * Elimina un usuario de la base de datos, junto con sus datos relacionados.
     *
     * @param user Usuario a eliminar.
     */
    void deleteUser(User user);

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param email Correo electrónico del usuario.
     * @return Usuario encontrado o null si no existe.
     */
    User findUserByEmail(String email);

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username Nombre del usuario.
     * @return Usuario encontrado o null si no existe.
     */
    User findUserByUsername(String username);

}
