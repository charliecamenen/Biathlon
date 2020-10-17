package com.biathlon.action;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.biathlon.jeu.Main;

public class Biathlete {

	private int id;
	private String nom;
	private String prenom;
	private String libelle;
	private int id_equipe;
	//private String libelle_equipe;
	private int age;
	private String sexe;
	private String statut;
	private int point;
	private int end;
	private int acc;
	private int cou;
	private int deb;
	private int vit;
	private int ski;
	private int rec;
	private int reg;
	private int pot;
	private int ren;
	
	public Biathlete(int id, String nom, String prenom, String libelle, int id_equipe, int age,
			String sexe, String statut,int point, int end, int acc, int cou, int deb , int vit, int ski, int rec, int reg, int pot, int ren) {
		super();
		ResultSet resultat = Main.database.requete("select * from biathletes where id_biathlete =  " + id);
		//System.out.println("select * from Biathlete where id_biathlete = " + id);
		try {
			while(resultat.next()) {
				//System.out.println(resultat.getString("nom_biathlete"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.libelle = libelle;
		this.id_equipe = id_equipe;
		//this.libelle_equipe = libelle_equipe;
		this.age = age;
		this.sexe = sexe;
		this.statut = statut;
		this.point =point;
		this.end = end;
		this.acc = acc;
		this.cou = cou;
		this.deb = deb;
		this.vit = vit;
		this.ski = ski;
		this.rec = rec;
		this.reg = reg;
		this.pot = pot;
		this.ren = ren;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getId_equipe() {
		return id_equipe;
	}

	public void setId_equipe(int id_equipe) {
		this.id_equipe = id_equipe;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getAcc() {
		return acc;
	}

	public void setAcc(int acc) {
		this.acc = acc;
	}


	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getCou() {
		return cou;
	}

	public void setCou(int cou) {
		this.cou = cou;
	}

	public int getDeb() {
		return deb;
	}

	public void setDeb(int deb) {
		this.deb = deb;
	}

	public int getVit() {
		return vit;
	}

	public void setVit(int vit) {
		this.vit = vit;
	}

	public int getSki() {
		return ski;
	}

	public void setSki(int ski) {
		this.ski = ski;
	}

	public int getRec() {
		return rec;
	}

	public void setRec(int rec) {
		this.rec = rec;
	}

	public int getReg() {
		return reg;
	}

	public void setReg(int reg) {
		this.reg = reg;
	}

	public int getPot() {
		return pot;
	}

	public void setPot(int pot) {
		this.pot = pot;
	}

	public int getRen() {
		return ren;
	}

	public void setRen(int ren) {
		this.ren = ren;
	}
	
	
	
}
