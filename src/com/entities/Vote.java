package com.entities;

public class Vote {

	private int idV;
	private String lieu;
	private int bureau;
	private String bulletin;
	private int annee;
	private Electeur electeur;
	
	public Vote() {
		super();
	}
	public int getIdV() {
		return idV;
	}
	public void setIdV(int idV) {
		this.idV = idV;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public int getBureau() {
		return bureau;
	}
	public void setBureau(int bureau) {
		this.bureau = bureau;
	}
	public String getBulletin() {
		return bulletin;
	}
	public void setBulletin(String bulletin) {
		this.bulletin = bulletin;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public Electeur getElecteur() {
		return electeur;
	}
	public void setElecteur(Electeur electeur) {
		this.electeur = electeur;
	}
	
	
}
