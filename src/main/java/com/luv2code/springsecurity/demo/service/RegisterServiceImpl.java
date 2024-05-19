package com.luv2code.springsecurity.demo.service;

import com.luv2code.springsecurity.demo.dao.UserDAOImpl;
import com.luv2code.springsecurity.demo.entity.User_roles;
import com.luv2code.springsecurity.demo.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserDAOImpl repo;
    @Override
    public void save(Users user) {
        user.setEnabled(true);

        List<User_roles> list=new ArrayList<>();
        User_roles user_role = new User_roles();
        String roleId=UUID.randomUUID().toString();
        user_role.setId(roleId);
        user_role.setRole("ROLE_ADMIN");
        user_role.setUsers(user);
        list.add(user_role);



        /*for (int i=0; i<user.getUser_roles().size(); i++) {
            User_roles user_role = user.getUser_roles().get(i);
            String roleId=UUID.randomUUID().toString();
            user_role.setId(roleId);
            user_role.setRole("ROLE_ADMIN");
            user_role.setUsers(user);
            list.add(user_role);
        }*/

        user.setUser_roles(list);

        repo.save(user);
    }
}
