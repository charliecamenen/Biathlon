package com.biathlon.action;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.biathlon.jeu.MembreClassement;

public class Classement {
	
	//Liste des membre classé
	private ArrayList<MembreClassement> list_membre;
	
	//reference de la position 
	private final int ref_pos_x = 20;
	private final int ref_pos_y = 170;
	
	//Intitulé du titre
	private String titre;
	private final int y_titre = ref_pos_y - 5;
	private final int x_titre = ref_pos_x +30;
	
	//Kilometre du pointage
	private float distance;
	private final int y_distance = ref_pos_y -5;
	private final int x_distance = ref_pos_x + 270;
	
	//Encadré du titre du classement
	private ImageIcon ico_bg_titre;
	private Image img_bg_titre;
	private final int y_bg_titre = ref_pos_y;
	private final int x_bg_titre = ref_pos_x;
	
	//Temps du premier au pointage
	private long temps_premier;
	
	
	public Classement( String titre, float distance) {
		super();
		
		//On créé l'image de background
		this.ico_bg_titre = new ImageIcon(getClass().getResource("/images/backgroundTitreClassement.png"));
		this.img_bg_titre = this.ico_bg_titre.getImage();
				
		//On instancie la liste des membre 		
		this.list_membre = new ArrayList<>();
		
		//En tete du classement
		this.titre = titre.toUpperCase();
		
		//Distance a laquelle est fait le pointage
		this.distance = distance;
		
		this.temps_premier = 500000000;
	}
	
	//Fonction qui actualise le retard de tout le monde
	public void updateRetard() {
		//Parcour de la liste des membre pour mettre a jour leur retard
		for(MembreClassement m : this.list_membre) {
			m.calculRetard(this.temps_premier);
		}
	}
	
	//Fonction qui actualise le classement de tout le monde
	public void updateNumClassement() {
		//Parcour la liste pour mettre a jour le numero de classement
		for (int i =0 ; i< this.list_membre.size(); i++ ) {
			this.list_membre.get(i).setNum_classement(i+1);
		}
	}


	public ArrayList<MembreClassement> getList_membre() {
		return list_membre;
	}


	public void setList_membre(ArrayList<MembreClassement> list_membre) {
		this.list_membre = list_membre;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public int getRef_pos_x() {
		return ref_pos_x;
	}

	public int getRef_pos_y() {
		return ref_pos_y;
	}

	public float getDistance() {
		return distance;
	}


	public int getY_distance() {
		return y_distance;
	}

	public int getX_distance() {
		return x_distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}


	public int getY_titre() {
		return y_titre;
	}


	public int getX_titre() {
		return x_titre;
	}

	public ImageIcon getIco_bg_titre() {
		return ico_bg_titre;
	}

	public void setIco_bg_titre(ImageIcon ico_bg_titre) {
		this.ico_bg_titre = ico_bg_titre;
	}

	public Image getImg_bg_titre() {
		return img_bg_titre;
	}

	public void setImg_bg_titre(Image img_bg_titre) {
		this.img_bg_titre = img_bg_titre;
	}

	public int getY_bg_titre() {
		return y_bg_titre;
	}

	public int getX_bg_titre() {
		return x_bg_titre;
	}

	public long getTemps_premier() {
		return temps_premier;
	}

	public void setTemps_premier(long l) {
		this.temps_premier = l;
	}
	

}
