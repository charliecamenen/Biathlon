package com.biathlon.action;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;

import com.biathlon.jeu.Joueur;
import com.biathlon.jeu.Main;

public class Tir implements Runnable{

	protected int id_biathlete;
	protected int compteurTir;
	protected int nb_reussi;
	private int temps;
	private int PAUSE = 2000;
	private int vitesse_jeu = 1;

	private double proba_reussite;
	private double VIT_biathlete;
	protected int tir_a_realiser;
	protected String type_tir_cd;

	public Tir(int id_biathlete, int PRE, int REG, int VIT, float fraicheur, int tir_a_realiser, String type_tir_cd) {
		this.compteurTir = 0;
		this.temps = 0;
		this.type_tir_cd = type_tir_cd;
		this.nb_reussi = 0;
		this.id_biathlete = id_biathlete;
		Random random_vit = new Random();
		this.VIT_biathlete = (double) (VIT + random_vit.nextGaussian() * (-0.09 * REG + 9.5));
		
		this.tir_a_realiser = tir_a_realiser;
		/*Prendre en parametre :
		 *  REG (Pour faire varier la precision +/- ) ca va influencer la forme du jour
		 *  PRE
		 *  Pays de la compet(si meme que le siens)
		 *  Fraicheur
		 *  Type de tir (si relai prendre + de risque, si pioche, faire gaffe)
		 *  Couché/debout (sauf si on donne une variable pour chaque)
		 */
		Random random_pre = new Random();
		//Trouver comment gerer la fraicheur
		this.proba_reussite = (double) (PRE + random_pre.nextGaussian() * (-0.09 * REG + 9.5));
		//System.out.println(proba_reussite);

	}
	
	public void execution() {
		Thread chrono = new Thread(this);
		chrono.start();
	}
	
	public int calculSleep() {
		int sleepTime = 0;
		Random random_sleep = new Random();
		/**
		 * Dépend de :
		 * VIT :
		 * Fatigue : 
		 * Intention : (A l'attaque, assurer le coup, normal) > predire grace a l'IA ?
		 * 
		 */
		sleepTime = (int) ( 1000 * (8 - this.VIT_biathlete /20));
		//Faire une fonction mieux
		//System.out.println(this.VIT_biathlete);
		//float temp = (float) (8- (this.VIT_biathlete / 20));
		//On réapplique l'alea en fonction de 
		//sleepTime = (int) Math.round((temp + random_sleep.nextGaussian() * (0.43 * temp -0.15) * 1000));
		return sleepTime;
	}
	

	@Override
	public void run() {
		//On ajoute le biathlete a la simulation
		Main.joueur.getCourse().ajouterSimulationTir(id_biathlete);
		
		while(compteurTir < tir_a_realiser & nb_reussi < 5) {
			//ON attend le temps entre deux tirs
			try{
				Thread.sleep((int)calculSleep()/vitesse_jeu);
			}catch (InterruptedException e) {}
			temps  += 2000;
			
			//Alea de 0 a 1 pour savoir si il reussi
			double proba = (double) 100 * Math.random();
			
			
			//si il reussi
			if (proba < proba_reussite) {
				//alors on envoie le resultat True
				Main.joueur.getCourse().ajouterResultatTir(id_biathlete, true);
				this.nb_reussi +=1;
			} else {
				//sinon on envoie le resultat false
				Main.joueur.getCourse().ajouterResultatTir(id_biathlete, false );

			}
			
			if(compteurTir >4) {
				Main.joueur.getCourse().retirerBallePioche(id_biathlete);
			}
			//sinon on avance
			compteurTir +=1;
			
			//Si on est en relai et qu'on arrive au 6 eme tir
			if(compteurTir == 5 & tir_a_realiser == 8 & this.nb_reussi<5) {
				//Alors on ajoute les balles de pioches
				Main.joueur.getCourse().ajouterBalleDePioche(id_biathlete);
			}
			
			try {
				Thread.sleep((int) 500/vitesse_jeu);
			} catch (InterruptedException e) {e.printStackTrace();}
			Main.joueur.getCourse().blanchirCible(id_biathlete);
			
		}
	}

	public int getCompteurTir() {
		return compteurTir;
	}

	public int getId_biathlete() {
		return id_biathlete;
	}

	public void setId_biathlete(int id_biathlete) {
		this.id_biathlete = id_biathlete;
	}

	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}

	public int getPAUSE() {
		return PAUSE;
	}

	public void setPAUSE(int pAUSE) {
		PAUSE = pAUSE;
	}

	public int getVitesse_jeu() {
		return vitesse_jeu;
	}

	public void setVitesse_jeu(int vitesse_jeu) {
		this.vitesse_jeu = vitesse_jeu;
	}

	public double getProba_reussite() {
		return proba_reussite;
	}

	public void setProba_reussite(double proba_reussite) {
		this.proba_reussite = proba_reussite;
	}

	public double getVIT_biathlete() {
		return VIT_biathlete;
	}

	public void setVIT_biathlete(double vIT_biathlete) {
		VIT_biathlete = vIT_biathlete;
	}

	public int getTir_a_realiser() {
		return tir_a_realiser;
	}

	public void setTir_a_realiser(int tir_a_realiser) {
		this.tir_a_realiser = tir_a_realiser;
	}

	public void setCompteurTir(int compteurTir) {
		this.compteurTir = compteurTir;
	}

	public int getNb_reussi() {
		return nb_reussi;
	}

	public void setNb_reussi(int nb_reussi) {
		this.nb_reussi = nb_reussi;
	}

	
}
