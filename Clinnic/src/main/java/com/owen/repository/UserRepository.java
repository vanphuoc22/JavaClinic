/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.repository;

import com.owen.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Trinh Bao Duy
 */
public interface UserRepository {
    List<User> getUsers(Map<String, String> params);
    Long countUser();
    boolean deleteUser(int id);
    List<User> searchUsersByName(Map<String, String> name);
    boolean addOrUpdateUser(User d);
    User getUserById(int id);
    User getUserByUsername(String username);
    List<User> getBacSi();
    boolean authUser(String username, String password);
    User addUser(User user);
    List<User> getBacSi(int id);
    User getUserByEmail(String mail);
    boolean changePassword(User user, String newPassword);
    User registerUserGoogle(Map<String, String> params);
    List<User> getUsersByUsername(String username);
}
