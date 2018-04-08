package com.mongcent.tnaot.service;

import com.mongcent.tnaot.model.Permission;
import com.mongcent.tnaot.model.Role;
import com.mongcent.tnaot.model.User;
import com.mongcent.tnaot.repository.PermissionRepository;
import com.mongcent.tnaot.repository.RoleRepository;
import com.mongcent.tnaot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceV1 {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    public User getUserByName(String name){
        User user = userRepository.getUserByName(name);
        return user;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public Role addRole(Role role){
        return roleRepository.save(role);
    }

    public void addPermission(Permission permission){
        permissionRepository.save(permission);
    }
}
