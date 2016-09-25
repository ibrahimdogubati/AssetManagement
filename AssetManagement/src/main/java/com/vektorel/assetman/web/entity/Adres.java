package com.vektorel.assetman.web.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Adres implements Serializable{

	String acikAdres;
	String email;
	String tel;
	Yerlesim il;
	Yerlesim ilce;

	public String getAcikAdres() {
		return acikAdres;
	}

	public void setAcikAdres(String acikAdres) {
		this.acikAdres = acikAdres;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Yerlesim getIl() {
		return il;
	}

	public void setIl(Yerlesim il) {
		this.il = il;
	}

	public Yerlesim getIlce() {
		return ilce;
	}

	public void setIlce(Yerlesim ilce) {
		this.ilce = ilce;
	}

}
