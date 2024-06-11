package org.decadev.ecommerce.dao;

import org.decadev.ecommerce.entity.User;

public interface UserDao {
//    void persistUser(User user);

    boolean registerUser(User user);

    boolean authenticateUser(String username, String password);

    boolean doesUserExist(String username, String email);
}
