package Persistence;



import Business.Entidades.User;

import java.util.List;

public interface UserDAO {
    void insertUser(User user);
    void deleteUser(User user);
    User findUserByEmail(String email);
    User findUserByUsername(String username);

}
