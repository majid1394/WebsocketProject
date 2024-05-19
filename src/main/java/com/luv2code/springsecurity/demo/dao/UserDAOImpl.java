package com.luv2code.springsecurity.demo.dao;

import com.luv2code.springsecurity.demo.entity.Users;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    EntityManager entityManager;



    private JdbcTemplate jdbcTemplate;



    @Override
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Users getUserInfo(String username) {
   /*     String sql = "SELECT u.username name, u.password pass FROM " +
                "users u INNER JOIN user_roles a on u.username=a.fk_username WHERE " +
                "u.username = ?";*/
/*

        Query query = entityManager.createQuery("SELECT u.username name, u.password pass FROM " +
                "users u INNER JOIN user_roles a on u.username=a.fk_username WHERE " +
                "u.username="+ username).setParameter("username",username);
*/

        Query query = entityManager.createNamedQuery("getUserByUsername").setParameter("username",username);



        List<Users> users = query.getResultList();

        if (users.size() > 0) {
            return users.get(0);
        }
        return null;

        /*Users users=new Users();
        //users.setName("majid");
        users.setUsername("majid");
        users.setPassword("123");
        users.setRole("ROLE_ADMIN");
        return users;*/


    }

    @Override
    public void save(Users user) {
        entityManager.persist(user);
    }
}


/*

        *//*String sql = "SELECT u.username name, u.password pass, a.authority role FROM "+
                "comp_users u INNER JOIN comp_authorities a on u.username=a.username WHERE "+
                "u.enabled =1 and u.username = ?";*//*
        Users userInfo = (Users) jdbcTemplate.queryForObject(sql, new Object[]{username},
                new RowMapper<Users>() {
                    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Users user = new Users();


                        user.setUsername(rs.getString("name"));
                        user.setPassword(rs.getString("pass"));


                        List<User_roles> user_roles=new ArrayList<User_roles>();
                      //  user_roles.get(0).setRole("role");
                      //  user_roles.get(0).setId("24");
                    //    user_roles.get(0).setUsers(user);

                     //   user.setUser_roles(user_roles);


                        return user;
                    }
                });
        return userInfo;
    }
}*/
