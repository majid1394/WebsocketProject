package com.luv2code.springsecurity.demo.dao;
import javax.sql.DataSource;

import com.luv2code.springsecurity.demo.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
    public void setDataSource(DataSource dataSource);
    public Users getUserInfo(String username);
    public  void save(Users user);
}
