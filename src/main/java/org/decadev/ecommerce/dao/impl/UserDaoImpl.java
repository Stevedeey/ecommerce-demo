package org.decadev.ecommerce.dao.impl;

import org.decadev.ecommerce.dao.UserDao;
import org.decadev.ecommerce.entity.User;
import org.decadev.ecommerce.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

//    @Override
//    public void persistUser(User user) {
//        try (Connection connection = DatabaseUtil.getConnection()) {
//
//            String sql = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, user.getUsername());
//            preparedStatement.setString(2, user.getEmail());
//            preparedStatement.setString(3, user.getPassword());
//            preparedStatement.setString(4, user.getRole().name());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public boolean registerUser(User user) {
        boolean registrationSuccess = false;

        String query = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole().name());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                registrationSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registrationSuccess;
    }


    @Override
    public boolean authenticateUser(String username, String password) {
        boolean isValidUser = false;

        System.out.println("GOT INTO AUTH USER METHOD");

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        System.out.println("Auth was successful");

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    isValidUser = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isValidUser;
    }

    @Override
    public boolean doesUserExist(String username, String email) {
        boolean userExists = false;

        String query = "SELECT * FROM users WHERE username = ? OR email = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userExists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userExists;
    }


}
