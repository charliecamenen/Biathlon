package com.biathlon.courses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import com.biathlon.action.Biathlete;
import com.biathlon.action.Classement;
import com.biathlon.action.Performance;
import com.biathlon.action.Tir;
import com.biathlon.jeu.Main;

public class Relais extends Course implements Runnable{

	//Objet pour les relay
	private ArrayList<ArrayList<Performance>> liste_equipe;
	//Info sur les pointage et tirs
	private ArrayList<Float> list_km_tir_h;
	private ArrayList<Float> list_km_tir_f;
	private ArrayList<Float> list_km_pointage_h;
	private ArrayList<Float> list_km_pointage_f;
	private String sexe;
	private int nombre_par_sexe;

	//Relais simple
	public Relais (ResultSet equipes, ArrayList<Float> list_km_tir , ArrayList<Float> list_km_pointage, ArrayList<String> list_type_tir_cd,  int nombre_par_sexe ,String sexe) {
		super(list_type_tir_cd);
		//Nombre de relayeur par sexe
		this.nombre_par_sexe = nombre_par_sexe;

		//Initialise le sexe des relayeurs
		this.sexe = sexe;
		//Liste des km de tirs
		this.list_km_tir_h = list_km_tir;

		//Liste des km pointage
		this.list_km_pointage_h = list_km_pointage;

		//On créé la liste des equipes
		this.liste_equipe = new ArrayList<>();

		//Création de la liste de classement
		this.creerListeClassement();
		
		//On rempli la liste de performances
		try {
			//boucle sur les equipes
			while (equipes.next()){

				//On rempli l'équipe correspondant a l'ID
				this.liste_equipe.add(this.remplirEquipe(equipes.getInt("id_equipe")));

			}
		} catch (SQLException e) {e.printStackTrace();}
	}

	//Relai mixte
	public Relais (ResultSet equipes, ArrayList<Float> list_km_tir_h,  ArrayList<Float> list_km_tir_f, ArrayList<Float> list_km_pointage_h, ArrayList<Float> list_km_pointage_f ,ArrayList<String> list_type_tir,  int nombre_par_sexe ) {
		super(list_type_tir);
		//Nombre de relayeur par sexe
		this.nombre_par_sexe = nombre_par_sexe;

		//Liste des km pointage des femmes
		this.list_km_pointage_f = list_km_pointage_f;
		//Liste des km pointage des hommes
		this.list_km_pointage_h = list_km_pointage_h;

		//Liste des km de tirs des hommes
		this.list_km_tir_h = list_km_tir_h;
		//Liste des km de tirs des femmes
		this.list_km_tir_f = list_km_tir_f;

		//On créé la liste de performances
		this.liste_equipe = new ArrayList<>();

		//Création de la liste de classement
		this.creerListeClassement();
		
		//On rempli la liste de performances
		try {
			//boucle sur les equipes
			while (equipes.next()){

				//On rempli l'équipe correspondant a l'ID
				this.liste_equipe.add(this.remplirEquipe(equipes.getInt("id_equipe")));

			}
		} catch (SQLException e) {e.printStackTrace();}
	}


	//Fonction qui renvoie une equipe de 4 participant
	public ArrayList<Performance> remplirEquipe(int id_equipe ) {
		ArrayList<Performance> membre_equipe = new ArrayList<>();
		ResultSet participant;
		
		int num_relayeur = 1;
		
		System.out.println("--------------");
		
		switch(this.nombre_par_sexe) {
		case 1:
			Performance performance_h;
			Performance performance_f;
			ResultSet participant_f;
			ResultSet participant_h;
			
			
			try {
				for (int i = 0; i<2; i++) {
					//La filles
					participant_f = Main.database.requete("select * from biathletes where biathletes.sexe_biathlete = 'F' and biathletes.id_equipe = " +  id_equipe + " order by REN DESC limit 1");
					//Le mecs 
					participant_h = Main.database.requete("select * from biathletes where biathletes.sexe_biathlete = 'H' and biathletes.id_equipe = " +  id_equipe + " order by REN DESC limit 1");

					while(participant_f.next()) {
						
						//constructeur de biathlete
						Biathlete biathlete = new Biathlete(participant_f.getInt("id_biathlete"), participant_f.getString("nom_biathlete"), participant_f.getString("prenom_biathlete"), participant_f.getString("libelle_biathlete"), participant_f.getInt("id_equipe"),participant_f.getInt("age_biathlete"),participant_f.getString("sexe_biathlete"),participant_f.getString("statut_biathlete"),participant_f.getInt("point_biathlete"), participant_f.getInt("END"), participant_f.getInt("ACC"),participant_f.getInt("COU"), participant_f.getInt("DEB") ,participant_f.getInt("VIT"),participant_f.getInt("SKI"),participant_f.getInt("REC"),participant_f.getInt("REG"),participant_f.getInt("POT"),participant_f.getInt("REN"));
						
						//Création de l'objet performance
						performance_f = new Performance(biathlete, "relais", "tour", list_km_tir_f, list_km_pointage_f,this.list_type_tir_cd, this.vitesse_jeu);
						
						//ON lui donne un numero de relayeur
						performance_f.setNumero_relayeur(num_relayeur);
						num_relayeur+=1;
						
						System.out.println(biathlete.getLibelle());
						membre_equipe.add(performance_f);
					}
					while(participant_h.next()) {
						
						//constructeur de biathlete
						Biathlete biathlete = new Biathlete(participant_h.getInt("id_biathlete"), participant_h.getString("nom_biathlete"), participant_h.getString("prenom_biathlete"), participant_h.getString("libelle_biathlete"), participant_h.getInt("id_equipe"),participant_h.getInt("age_biathlete"),participant_h.getString("sexe_biathlete"),participant_h.getString("statut_biathlete"),participant_h.getInt("point_biathlete"), participant_h.getInt("END"), participant_h.getInt("ACC"),participant_h.getInt("COU"), participant_h.getInt("DEB") ,participant_h.getInt("VIT"),participant_h.getInt("SKI"),participant_h.getInt("REC"),participant_h.getInt("REG"),participant_h.getInt("POT"),participant_h.getInt("REN"));
						
						//Création de l'objet performance
						performance_h = new Performance(biathlete, "relais", "tour", list_km_tir_h, list_km_pointage_h,this.list_type_tir_cd, this.vitesse_jeu);
						
						//ON lui donne un numero de relayeur
						performance_h.setNumero_relayeur(num_relayeur);
						num_relayeur+=1;
						
						System.out.println(biathlete.getLibelle());
						membre_equipe.add(performance_h);
					}
				}
			} catch (SQLException e) {e.printStackTrace();}

			return membre_equipe ;
			
		case 2:
			//Les deux filles
			participant = Main.database.requete("select * from biathletes where biathletes.sexe_biathlete = 'F' and biathletes.id_equipe = " +  id_equipe + " order by REN DESC limit 2");
			try {
				while(participant.next()) {
					
					//constructeur de biathlete
					Biathlete biathlete = new Biathlete(participant.getInt("id_biathlete"), participant.getString("nom_biathlete"), participant.getString("prenom_biathlete"), participant.getString("libelle_biathlete"), participant.getInt("id_equipe"),participant.getInt("age_biathlete"),participant.getString("sexe_biathlete"),participant.getString("statut_biathlete"),participant.getInt("point_biathlete"), participant.getInt("END"), participant.getInt("ACC"),participant.getInt("COU"), participant.getInt("DEB") ,participant.getInt("VIT"),participant.getInt("SKI"),participant.getInt("REC"),participant.getInt("REG"),participant.getInt("POT"),participant.getInt("REN"));
					
					//Création de l'objet performance
					Performance performance = new Performance(biathlete, "relais", "tour", list_km_tir_f, list_km_pointage_f,this.list_type_tir_cd,this.vitesse_jeu);
					
					//ON lui donne un numero de relayeur
					performance.setNumero_relayeur(num_relayeur);
					num_relayeur+=1;
					
					System.out.println(biathlete.getLibelle());
					membre_equipe.add(performance);
				}
			} catch (SQLException e) {e.printStackTrace();}

			//Les deux mecs 
			participant = Main.database.requete("select * from biathletes where biathletes.sexe_biathlete = 'H' and biathletes.id_equipe = " +  id_equipe + " order by REN DESC limit 2");
			try {
				while(participant.next()) {

					//constructeur de biathlete
					Biathlete biathlete = new Biathlete(participant.getInt("id_biathlete"), participant.getString("nom_biathlete"), participant.getString("prenom_biathlete"), participant.getString("libelle_biathlete"), participant.getInt("id_equipe"),participant.getInt("age_biathlete"),participant.getString("sexe_biathlete"),participant.getString("statut_biathlete"),participant.getInt("point_biathlete"), participant.getInt("END"), participant.getInt("ACC"),participant.getInt("COU"), participant.getInt("DEB") ,participant.getInt("VIT"),participant.getInt("SKI"),participant.getInt("REC"),participant.getInt("REG"),participant.getInt("POT"),participant.getInt("REN"));
					
					//Création de l'objet performance
					Performance performance = new Performance(biathlete, "relais", "tour", list_km_tir_h, list_km_pointage_h, this.list_type_tir_cd,this.vitesse_jeu);
					
					//ON lui donne un numero de relayeur
					performance.setNumero_relayeur(num_relayeur);
					num_relayeur+=1;
					
					System.out.println(biathlete.getLibelle());
					
					membre_equipe.add(performance);
				}
			} catch (SQLException e) {e.printStackTrace();}


			return membre_equipe;
		case 4:
			participant = Main.database.requete("select * from biathletes where biathletes.sexe_biathlete = '" + this.sexe + "' and biathletes.id_equipe = " +  id_equipe + " order by REN DESC limit 4");

			try {
				while(participant.next()) {
					//constructeur de biathlete
					Biathlete biathlete = new Biathlete(participant.getInt("id_biathlete"), participant.getString("nom_biathlete"), participant.getString("prenom_biathlete"), participant.getString("libelle_biathlete"), participant.getInt("id_equipe"),participant.getInt("age_biathlete"),participant.getString("sexe_biathlete"),participant.getString("statut_biathlete"),participant.getInt("point_biathlete"), participant.getInt("END"), participant.getInt("ACC"),participant.getInt("COU"), participant.getInt("DEB") ,participant.getInt("VIT"),participant.getInt("SKI"),participant.getInt("REC"),participant.getInt("REG"),participant.getInt("POT"),participant.getInt("REN"));
					//Création de l'objet performance
					Performance performance = new Performance(biathlete, "relais", "tour", list_km_tir_h, list_km_pointage_h, this.list_type_tir_cd,this.vitesse_jeu);
					
					//ON lui donne un numero de relayeur
					performance.setNumero_relayeur(num_relayeur);
					num_relayeur+=1;
					
					System.out.println(biathlete.getLibelle());
					membre_equipe.add(performance);
				}
			} catch (SQLException e) {e.printStackTrace();}


			return membre_equipe;

		default: return null;
		}

	}

	//ON fait démarrer la course
	public void demarrer() {
		//On fait partir tout les premiers relayeur
		for(ArrayList<Performance> equipe : liste_equipe) {
			equipe.get(0).depart(0);
		}
		//On démarre le chronometre
		Thread car = new Thread(this);
		car.start();
	}

	public void passageRelais(Performance participant) {
		//Pour chaque equipe
		
		for(ArrayList<Performance> equipe : liste_equipe) {
			//Pour chaque membre de l'equipe
			for (int i = 0 ; i< equipe.size() -1; i++) {

				//Si le biathlete qui a terminé correspond et que le numero de relayeur = i
				if(equipe.get(i).getBiathlete().getId() == participant.getBiathlete().getId() & participant.getNumero_relayeur() == i+1) {
					
					//Alors on fait partir le prochain (si num relayeur =1 on fait partir pos 1 donc le deuxieme)
					equipe.get(participant.getNumero_relayeur()).depart(participant.getChrono_perf());
					
					System.out.println("Depart de  : " + equipe.get(i+1).getBiathlete().getLibelle());
				}
			}
		}
	}

	public void run() {

		while(true) {
			//On incrémente le temps de course
			chrono_course += PAUSE;
			//On met a jour la distance du premier de la course
			this.updateDistancePremier();
			
			//On fait dormir le programme PAUSE ms
			try{
				Thread.sleep(PAUSE/this.vitesse_jeu);
			}catch (InterruptedException e) {}

			System.out.println("---------------");
			
			
		}
	}

	public void modifierVitesseJeu(int vitesse){
		//On appel la methodes mere
		super.modifierVitesseJeu(vitesse);
		
		//On parcour tout les equipes
		for(ArrayList<Performance> equipe : this.liste_equipe) {
			
			//On parcour toutes les biathletes
			for(Performance participant : equipe) {
				
				//On actualise la vitesse de biathlete
				participant.setVitesse_jeu(vitesse);
				
				//On actualise la vitesse de tir (pour chaque tir)
				for(Tir tir : participant.getList_resultat_tir()) {
					tir.setVitesse_jeu(vitesse);
				}
			}
		}
	}

	@Override
	public void creerListeClassement() {
		//Instanciation de la classe
		this.list_classement = new ArrayList<>();
		switch(this.nombre_par_sexe) {
		case 1:
			for (float km : this.list_km_pointage_f) {
				//On créé un classement
				Classement classement = new Classement("Pointage intermediaire" , km );
				//On ajoute le classement a la liste
				this.list_classement.add(classement);
			}
			for (float km : this.list_km_pointage_h) {
				//On créé un classement
				Classement classement = new Classement("Pointage intermediaire" , km );
				//On ajoute le classement a la liste
				this.list_classement.add(classement);
			}

		case 2:
			//On boucle sur tout les pointages intermédiaires 2 fois (FEMMES)
			for (int i = 0 ; i<2 ;i++ ) {
				for (float km : this.list_km_pointage_f) {
					//On créé un classement
					Classement classement = new Classement("Pointage intermediaire" , km );
					//On ajoute le classement a la liste
					this.list_classement.add(classement);
				}
			}

			//On boucle sur tout les pointages intermédiaires 2 fois (HOMMES)
			for (int i = 0 ; i<2 ;i++ ) {
				for (float km : this.list_km_pointage_h) {
					//On créé un classement
					Classement classement = new Classement("Pointage intermediaire" , km );
					//On ajoute le classement a la liste
					this.list_classement.add(classement);
				}
			}


		case 4:
			//On boucle sur tout les pointages intermédiaires 4 fois
			for (int i = 0 ; i<4 ;i++ ) {
				for (float km : this.list_km_pointage_h) {
					//On créé un classement
					Classement classement = new Classement("Pointage intermediaire" , km );
					//On ajoute le classement a la liste
					this.list_classement.add(classement);
				}
			}
		}
	}

	@Override
	public void updateDistancePremier() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Performance> getPerformancesSort() {
		// TODO Auto-generated method stub
		return null;
	}

}
