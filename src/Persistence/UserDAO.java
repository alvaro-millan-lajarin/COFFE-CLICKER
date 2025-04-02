package Persistence;

import Business.entity.User;

import java.util.List;

public interface UserDAO {
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User findUserByEmail(String email);
    User findUserByUsername(String username);
    List<User> findAllUsers();
}
