package com.biathlon.user;

public class Enregistrement {

	private int Id_Nationalite;
	private String Nationalite;
	
	public Enregistrement(int id_Nationalite, String nationalite) {
		Id_Nationalite = id_Nationalite;
		Nationalite = nationalite;
	}
	
	public int getId_Nationalite() {
		return Id_Nationalite;
	}
	public void setId_Nationalite(int id_Nationalite) {
		Id_Nationalite = id_Nationalite;
	}
	public String getNationalite() {
		return Nationalite;
	}
	public void setNationalite(String nationalite) {
		Nationalite = nationalite;
	}
	@Override
	public String toString() {
		return "Enregistrement [Id_Nationalite=" + Id_Nationalite + ", Nationalite=" + Nationalite
				+ ", getId_Nationalite()=" + getId_Nationalite() + ", getNationalite()=" + getNationalite() + "]";
	}
}
