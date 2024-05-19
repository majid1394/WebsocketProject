package com.luv2code.springsecurity.demo.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

@Entity
@Table(name="user_roles")
public class User_roles {

	@Id
	@NotNull
	@Column(name="user_role_id")
	private String id;

	@ManyToOne                  /*@JsonIgnore*/
	@JoinColumn(name = "fk_username",referencedColumnName = "username")
	private Users users;


	@Column(name="role")
	private String role;






	public User_roles() {

	}


	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}





