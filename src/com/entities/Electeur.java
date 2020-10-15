package com.entities;

public class Electeur {

	private String cin;
	private String nom;
	private String prenom;

	public Electeur() {
		super();
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	@Override
	public String toString(){
		return cin + " "+prenom  + " " + nom;
	}

}
