package com.biathlon.jeu;

import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.biathlon.action.CacheCibleSimulation;
import com.biathlon.action.Cible;
import com.biathlon.action.PointFauteSimulation;

public class CibleJoueur extends Cible {

	//Nombre de cibles restante a blanchir
	private int compteurBlanchirCible = 0 ;
	
	public CibleJoueur(int id_biathlete, int y) {

		//Création de l'imge de simulation normale (Blanche)
		ico_cible_blanc = new ImageIcon(getClass().getResource("/images/ciblesJoueur.png"));
		this.img_cible_blanc = this.ico_cible_blanc.getImage();
		
		//Création de l'image de simulation en cas d'erreur (Rouge)
		ico_cible_rouge = new ImageIcon(getClass().getResource("/images/ciblesJoueurRouge.png"));
		this.img_cible_rouge = this.ico_cible_rouge.getImage();
		
		//On initialise la cible a blanc
		this.ico_cible = this.ico_cible_blanc;
		this.img_cible = this.img_cible_blanc;
		
		//Initialise la liste des caches cibles
		list_cache_cible = new ArrayList<>();
		
		
		this.y_cache_cible = y+3;
		this.y_cible = y;

		//Initialisation de l'id biathlete de cette cible
		this.id_biathlete = id_biathlete;
		
		//Vecteur de blanchissement des cibles (False, True , True, True ,False ) => 2 fautes
		this.vec_boool_blanchi = new ArrayList<>();
		
		//On initialise toute les cibles à false
		for ( int i = 0 ; i <5 ; i++) {
			this.vec_boool_blanchi.add(new Boolean(false));
		}
		
	}

	@Override
	public void ajouterResultatTir(boolean resultat, int num_cible) {
		
		//Si tir reussi et que la cible n'a pas été abbatue(par sécurité on met la verification)
		if (resultat == true && this.vec_boool_blanchi.get(num_cible) == false) {
			
			//On passe a true la cible en question
			this.vec_boool_blanchi.set(num_cible, new Boolean(true));
			
			//On ajoute un cache a la simulation
			CacheCibleJoueur cache_cible = new CacheCibleJoueur(285 + num_cible * 46, 150 );
			
			//On ajoute le cache cible
			this.list_cache_cible.add(cache_cible);
			
		} else {
			//si non :
			//on affiche la cible en rouge
			this.img_cible = this.img_cible_rouge;
		}
		
	}

	//Pas compris a quoi ca sert
	public void ajouterResultatTir(boolean resultat) {}
	
	//Blanchi la cible
	public void blanchirCible() {
		compteurBlanchirCible -=1;
		if (compteurBlanchirCible <= 0) {
			super.blanchirCible();
		}
	}
	
	//Repasse a false toute la cible
	public void updateCibleBlanchisToFalse() {
		//On remet tour le vecteur de cible a false
		for ( int i = 0 ; i <5 ; i++) {
			this.vec_boool_blanchi.set(i, new Boolean(false));
		}
	}

	public int getCompteurBlanchirCible() {
		return compteurBlanchirCible;
	}

	public void setCompteurBlanchirCible(int compteurBlanchirCible) {
		this.compteurBlanchirCible = compteurBlanchirCible;
	}
	
}
