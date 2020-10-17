package com.biathlon.courses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.biathlon.action.Biathlete;
import com.biathlon.action.Classement;
import com.biathlon.action.Performance;
import com.biathlon.action.Tir;
import com.biathlon.jeu.MembreClassement;

public class ContreLaMontre extends Course implements Runnable {

	public ContreLaMontre (ResultSet participant, ArrayList<Float> list_km_tir , ArrayList<Float> list_km_pointage, ArrayList<String> list_type_tir_cd, String penalite) {
		super(list_type_tir_cd);
		
		//On créé la liste de performances
		this.list_participants = new ArrayList<>();

		//On créé la liste des pointage intermediaire
		this.list_km_pointage = list_km_pointage;

		//Création de la liste de classement
		this.creerListeClassement();

		//On rempli la liste de performances
		try {
			while (participant.next()){
				//constructeur de biathlete
				Biathlete biathlete = new Biathlete(participant.getInt("id_biathlete"), participant.getString("nom_biathlete"), participant.getString("prenom_biathlete"), participant.getString("libelle_biathlete"), participant.getInt("id_equipe"),participant.getInt("age_biathlete"),participant.getString("sexe_biathlete"),participant.getString("statut_biathlete"),participant.getInt("point_biathlete"), participant.getInt("END"), participant.getInt("ACC"),participant.getInt("COU"), participant.getInt("DEB") ,participant.getInt("VIT"),participant.getInt("SKI"),participant.getInt("REC"),participant.getInt("REG"),participant.getInt("POT"),participant.getInt("REN"));
				//Création de l'objet performance
				Performance performance = new Performance(biathlete, "classique", penalite, list_km_tir,list_km_pointage,this.list_type_tir_cd, this.vitesse_jeu);
				//On ajoute le participant a la liste
				list_participants.add(performance);
			}

		} catch (SQLException e) {e.printStackTrace();}

		Collections.shuffle(this.list_participants);

		
	}
	
	public void demarrer() {
		Thread car = new Thread(this);
		car.start();
	}

	public void run() {
		//Indice du prochain partant
		int prochain_depart = 0;

		//Temps du prochain partant
		int temps_prochain_depart = 0;

		while(true) {

			//On incrémente le temps de course
			chrono_course += PAUSE;
		
			//On met a jour la distance du premier de la course
			this.updateDistancePremier();
			
			//On fait dormir le programme PAUSE ms
			try{
				Thread.sleep(PAUSE/this.vitesse_jeu);
			}catch (InterruptedException e) {}

			
			//si il reste du monde a partir
			if (prochain_depart != list_participants.size()) {
				
				//On fait partir le biathlete en fonction du chrono_course
				if (chrono_course >= temps_prochain_depart) {
					
					//Alors il peut partir
					list_participants.get(prochain_depart).depart(0);
					
					//Pour l'instant on fait partir toutes les 10 secondes
					temps_prochain_depart += 20000;
					
					//On passe au prochain concurrent
					prochain_depart +=1;
				}
			}

		}
	}


	

}