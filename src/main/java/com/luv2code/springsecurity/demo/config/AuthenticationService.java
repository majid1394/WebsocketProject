package com.luv2code.springsecurity.demo.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.luv2code.springsecurity.demo.dao.ActiveUserStore.ActiveUserStoreImpl;
import com.luv2code.springsecurity.demo.dao.UserDAO;
import com.luv2code.springsecurity.demo.entity.User_roles;
import com.luv2code.springsecurity.demo.entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class AuthenticationService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    ActiveUserStoreImpl activeUserStore;

    private HttpServletRequest request;

    @Autowired
    private UserDAO userDAO;
    /*https://www.baeldung.com/get-user-in-spring-security*/
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException
    {
        Users userInfo = userDAO.getUserInfo(username);
        if (userInfo!=null)
        {
            List<User_roles> user_roles = new ArrayList<User_roles>();
            for (int i = 0; i <= userInfo.getUser_roles().size(); i++)
            {
                String role = userInfo.getUser_roles().get(i).getRole();
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                UserDetails userDetails = (UserDetails) new User(userInfo.getUsername(),
                        userInfo.getPassword(), Arrays.asList(authority));



            /*GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());*/
                return userDetails;
            }
        }
        /*else*/
           return  null;
    }

    /*Impllimentation of AuthenticationSuccessHandler*/

}
