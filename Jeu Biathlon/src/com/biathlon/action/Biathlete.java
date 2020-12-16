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
	private int age;
	private String sexe;
	private String statut;
	/*
	 * Différents statut
	 * Joueur
	 * Actif
	 * retiaité
	 * regen
	 */
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
			String sexe, String statut, int end, int acc, int cou, int deb , int vit, int ski, int rec, int reg, int pot, int ren) {
		super();	
		this.id = id;
		this.nom = nom.toUpperCase();
		this.prenom = prenom;
		//this.libelle = libelle;
		this.id_equipe = id_equipe;
		this.age = age;
		this.sexe = sexe;
		this.statut = statut;
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
	
	public Biathlete(String nom, String prenom, int id_equipe,String sexe) {
		super();	
		this.nom = nom.toUpperCase();
		this.prenom = prenom;
		this.id_equipe = id_equipe;
		this.sexe = sexe;
	}
	
	public Biathlete(int id) {
		super();
		ResultSet resultat = Main.database.requete("select * from biathletes where id_biathlete =  " + id);
		
		try {while(resultat.next()) {
			this.nom = resultat.getString("nom_biathlete");
			this.prenom = resultat.getString("prenom_biathlete");
			this.libelle = resultat.getString("libelle_biathlete");
			this.id_equipe = resultat.getInt("id_equipe");
			this.age = resultat.getInt("annee_biathlete");
			this.sexe = resultat.getString("sexe_biathlete");
			this.statut = resultat.getString("statut_biathlete");
			this.end = resultat.getInt("END");
			this.acc = resultat.getInt("ACC");
			this.cou = resultat.getInt("COU");
			this.deb = resultat.getInt("DEB");
			this.vit = resultat.getInt("VIT");
			this.ski = resultat.getInt("SKI");
			this.rec = resultat.getInt("REC");
			this.reg = resultat.getInt("REG");
			this.pot = resultat.getInt("POT");
			this.ren = resultat.getInt("REN");
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		this.id = id;
		
	}

	public void createAleaRegen(String spe, String site_fav) {
		
		
		this.age = (int) Math.round((float) 20 + ((Math.random()*4 )- 2 ));
		this.statut = "creation";	
		this.end = (int) Math.round((float) 70 + ((Math.random()*10 )- 5 ));
		this.acc = (int) Math.round((float) 70 + ((Math.random()*10 )- 5 ));
		this.cou = (int) Math.round((float) 70 + ((Math.random()*10 )- 5 ));
		this.deb = (int) Math.round((float) 70 + ((Math.random()*10 )- 5 ));
		this.vit = (int) Math.round((float) 70 + ((Math.random()*10 )- 5 ));
		this.ski = (int) Math.round((float) 70 + ((Math.random()*10 )- 5 ));
		this.rec = (int) Math.round((float) 70 + ((Math.random()*10 )- 5 ));
		this.reg = (int) Math.round((float) 70 + ((Math.random()*10 )- 5 ));
		this.pot = (int) Math.round((float) 70 + ((Math.random()*10 )- 5 ));
		this.ren = (int) Math.round((float) 70 + ((Math.random()*10 )- 5 ));

		
	}
	
	
	public String getLibelle() {
		if(prenom.length() > 0 && nom.length() >0) {
			return this.prenom.substring(0,1) + "." + this.nom;
		}else {
			return "";
		}
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
