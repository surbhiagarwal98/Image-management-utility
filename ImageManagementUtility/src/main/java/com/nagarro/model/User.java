package com.nagarro.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "userDetails")
public class User {
	@Id @GeneratedValue
	@Column(name = "username")
	private String userName;
	@Column(name = "password")
	private String password;
	@OneToMany(mappedBy = "user", cascade = CascadeType.DETACH, fetch = FetchType.EAGER, orphanRemoval=true)
	public Set<Image> images = new HashSet<Image>();
	public User() {
		
	}
	public User(String userName, String password,Set<Image> images) {
		super();
		this.userName = userName;
		this.password = password;
		this.images =  images;
	}
	public Set<Image> getImages() {
		return images;
	}
	public void setImages(Set<Image> images) {
		this.images = images;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
