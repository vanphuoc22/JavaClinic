/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.service;

import com.owen.pojo.Role;
import java.util.List;

/**
 *
 * @author Trinh Bao Duy
 */
public interface RoleService {
    List<Role> getRoles();
    Role getRoleById(int id);
}
