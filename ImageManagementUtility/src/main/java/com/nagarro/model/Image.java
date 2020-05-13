package com.nagarro.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "imageDetails")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sno")
	private int sno;

	@Column(name = "imagename")
	private String name;

	@Column(name = "size")
	private double size;
	
	
	@Column(columnDefinition = "mediumblob")
	@Basic(fetch = FetchType.LAZY)
	private byte[] img;

	@ManyToOne
	private User user;

	public Image() {

	}

	public Image(String name, double size, byte[] img) {
		super();
		this.name = name;
		this.size = size;
		this.img = img;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size2) {
		this.size = size2;
	}
	
	
	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] image) {
		this.img = image;
	}

}
