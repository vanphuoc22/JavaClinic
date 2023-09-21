/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.owen.service;

import com.owen.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author admin
 */

public interface UserService extends UserDetailsService{

    List<User> getUsers(Map<String, String> params);

    Long countUser();

    boolean deleteUser(int id);

    List<User> searchUsersByName(Map<String, String> name);

    boolean addOrUpdateUser(User d);

    User getUserById(int id);
    
    User getUserByUsername(String username);
    
    List<User> getBacSi();

    boolean authUser(String username, String password);
    
    User addUser(Map<String, String> params, MultipartFile avatar);
    List<User> getBacSi(int id);
    User getUserByEmail(String mail);
    boolean changePassword(User user, String newPassword);
    User registerUserGoogle(Map<String, String> params);
}
