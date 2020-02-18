package com.nbrown.quizmanager.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id", nullable=false)
	private int id;
	
	@Column(name="username", nullable=false)
	private String username;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="active", nullable=false)
	private int active;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="user_permissions", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="perm_id"))
	private List<Permission> permissions;
	
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

	public boolean isActive() {
		if(this.active == 1) {
			return true;
		}
		return false;
	} 
}
