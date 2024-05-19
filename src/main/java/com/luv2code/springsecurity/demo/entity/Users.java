package com.luv2code.springsecurity.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luv2code.springsecurity.demo.entity.User_roles;
import com.sun.istack.internal.NotNull;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({
        @NamedQuery(name = "getUserByUsername", query = "SELECT m FROM Users m where m.username = :username")
})



@Table(name="users")
public class Users {

    @Id
    @NotNull
    @Column(name="username" )
    private String username;



    @NotNull
    @Column(name="password")


    private String password;


    @Column(name="name")
    private String name;


    @Column(name="family")
    private String family;


    @Column(name="email")
    private String email;

    @Column(name="score")
    private int score;



    @Column(name="enabled", columnDefinition="tinyint(1) default 1")
    private boolean enabled;





    @OneToMany(mappedBy = "users",cascade = CascadeType.ALL,targetEntity = User_roles.class,fetch = FetchType.EAGER)  //name of property in account  , targetEntity=Account.class,fetch=FetchType.EAGER
    private List<User_roles> user_roles= new ArrayList<User_roles>();




    public Users() {

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<User_roles> getUser_roles() {
        return user_roles;
    }

    public void setUser_roles(List<User_roles> user_roles) {
        this.user_roles = user_roles;
    }

    public void addUserRole(User_roles user_roles) {
        this.user_roles.add(user_roles);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}





