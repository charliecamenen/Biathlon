package com.biathlon.courses;


import java.util.ArrayList;
import java.util.Collections;
import com.biathlon.action.CibleSimulation;
import com.biathlon.action.Classement;
import com.biathlon.action.Performance;
import com.biathlon.action.Tir;
import com.biathlon.jeu.Joueur;
import com.biathlon.jeu.MembreClassement;

public abstract class Course  {

	protected ArrayList<String> list_type_tir_cd;

	//Liste des participants
	protected ArrayList<Performance> list_participants;
	protected ArrayList<Float> list_km_pointage;
	
	//Liste des groupes
	protected ArrayList<ArrayList<Performance>> liste_groupe;

	//Temps de pause entre deux actualisation
	protected final int PAUSE = 1000;

	//Vitesse du jeu
	protected int vitesse_jeu = 1;

	//Liste de classement
	protected ArrayList<Classement> list_classement;

	//Chrono de la course
	protected int chrono_course;

	//Distance parcouru par le premier
	protected float distance_course;

	//Ordoné de la simultion
	private int y_cible_simulation;

	//Liste des biathletes empilé
	protected ArrayList<CibleSimulation> list_simulation_tir;

	//Création de la course
	public Course(ArrayList<String> list_type_tir_cd) {

		//On initialise la liste de type de tirs 
		this.list_type_tir_cd = list_type_tir_cd;

		//On créé la liste de simulation de tir (FIFO)
		this.list_simulation_tir = new ArrayList<>();

		//Chronometre de la course (A AMELIORER)
		this.chrono_course = 0;

		//Ordonnée de la simulation tir
		this.y_cible_simulation = 600;

		this.distance_course = 0;

	}

	public void demarrer() {

	}

	public void passageRelais(Performance participant) {}



	public void ajouterMembreClassement(Performance participant) {

		//Boucle sur la liste des classmeent pour mettre a jour le bon
		for(int i = 0; i < this.list_classement.size()  ; i++){

			//Si c'est le bon (Calcul assez coriace ici je l'admet)
			if (participant.getPointage_courrant() + participant.getList_km_pointage().size() * (participant.getNumero_relayeur()-1)  == i) {

				//Si il a le meilleur temps
				if (participant.getChrono_perf() < this.list_classement.get(i).getTemps_premier()) {

					//Alors on modifie la valeur du meilleur temps
					this.list_classement.get(i).setTemps_premier(participant.getChrono_perf());

					//Et on recalcul les retard de tout le monde
					this.list_classement.get(i).updateRetard();


				}

				//On créé le membre
				MembreClassement membre_classement = new MembreClassement(participant.getBiathlete(), participant.getChrono_perf(), participant.getNombre_fautes(),this.list_classement.get(i).getRef_pos_x(),this.list_classement.get(i).getRef_pos_y());

				//On calcul son retard
				membre_classement.calculRetard(this.list_classement.get(i).getTemps_premier());

				//On l'ajoute au classement
				this.list_classement.get(i).getList_membre().add(membre_classement);

				//On tri le classement en fonction du temps
				Collections.sort(this.list_classement.get(i).getList_membre(),MembreClassement.triTemps);
				Collections.reverse(this.list_classement.get(i).getList_membre());

				//On actualise les numero de classement
				this.list_classement.get(i).updateNumClassement();

				//On modifie pas le y de classement car l'affichage se fait dynamiquement avec le scroll de la souris
			}

		}

	}

	public void ajouterBalleDePioche(int id_biathlete) {
		//on parcour la liste de simulation
		for(int i =0; i<list_simulation_tir.size(); i++) {

			//Si c'est le bon biathlete
			if( list_simulation_tir.get(i).getId_biathlete() == id_biathlete) {

				//On créé les balle de pioche 
				list_simulation_tir.get(i).creerBallesPioches();
			}
		}
	}

	public void ajouterSimulationTir(int id_biathlete) {

		CibleSimulation cible_simulation = new CibleSimulation(id_biathlete);

		//on ajoute l'objet dans la liste
		list_simulation_tir.add(cible_simulation);

	}

	public void retirerBallePioche(int id_biathlete) {
		//On parcour tout les tirs
		for(int i =0; i<list_simulation_tir.size(); i++) {
			//si c'est le bon biathlete
			if( list_simulation_tir.get(i).getId_biathlete() == id_biathlete) {
				//Suppression de la balle de pioche tiré
				list_simulation_tir.get(i).retirerBallePioche();
			}
		}
	}

	public void supprimerSimulationTir(int id_biathlete) {
		int indice_suppr = 0;
		//Supprimer l'element de la liste
		for(int i =0; i<list_simulation_tir.size(); i++) {
			if( list_simulation_tir.get(i).getId_biathlete() == id_biathlete) {
				list_simulation_tir.remove(i);
				indice_suppr = i;
			}
		}
		//Modifier le Y de tout les tirerur au dessus de lui pour tout decaler vers le bas
		for(int i = indice_suppr; i<list_simulation_tir.size(); i++) {
			list_simulation_tir.get(i).decrY();
		}
		//AJouter 30 a la valeur du Y des cibles
		y_cible_simulation += 30;
	}

	public void ajouterResultatTir(int id_biathlete, boolean resultat) {
		//Parcour de la liste de tirreur pour ajouter un resultat au tireur selectionné
		for(int i =0; i<list_simulation_tir.size(); i++) {
			if( list_simulation_tir.get(i).getId_biathlete() == id_biathlete) {
				list_simulation_tir.get(i).ajouterResultatTir(resultat);
			}
		}

	}

	public void blanchirCible(int id_biathlete) {
		for(int i =0; i<list_simulation_tir.size(); i++) {
			if( list_simulation_tir.get(i).getId_biathlete() == id_biathlete) {
				list_simulation_tir.get(i).blanchirCible();
			}
		}
	}

	public void modifierVitesseJeu(int vitesse){
		//On appel la methodes mere
		this.vitesse_jeu = vitesse;
		System.out.println("Nouvelles vitesse : " + vitesse);

		//On parcour tout les biathletes
		for(Performance participant : this.list_participants) {
			//On actualise la vitesse de biathlete
			participant.setVitesse_jeu(vitesse);

			//On actualise la vitesse de tir (pour chaque tir)
			for(Tir tir : participant.getList_resultat_tir()) {
				tir.setVitesse_jeu(vitesse);
			}
		}
	}

	public void creerListeClassement() {
		//Instanciation de la classe
		this.list_classement = new ArrayList<>();

		//On boucle sur tout les pointages intermédiaires
		for (float km : this.list_km_pointage) {

			//On créé un classement
			Classement classement = new Classement("Pointage intermédiaire" , km );

			//On ajoute le classement a la liste
			this.list_classement.add(classement);
		}

	}

	//Distance de l'arrivé par rapport au premier
	public void updateDistancePremier() {
		for (Performance participant : this.list_participants) {
			if(this.distance_course < participant.getDistance()) {
				this.distance_course = participant.getDistance();
			}
		}

	}

	//Calcul le x des physique en fonction de leurs avancée dans la course
	public void updatePositionPhysique(int id_position_cible) {
		int pos_cible = id_position_cible;
		//Liste des participant courrant
		ArrayList<Performance> etat_liste_participante = this.list_participants;

		//Calcul de la position pour tout le monde
		for(Performance perf : etat_liste_participante) {

			//Si on est a la meme iteration
			if(perf.getNombre_iter() == etat_liste_participante.get(pos_cible).getNombre_iter()) {
				//alors on affiche normal
				perf.getPhysique().setX_silhouette((int) Math.round((etat_liste_participante.get(pos_cible).getPhysique().getX_silhouette() + 50 * (etat_liste_participante.get(pos_cible).getDistance() - perf.getDistance()))));
			} else if (perf.getNombre_iter() > etat_liste_participante.get(pos_cible).getNombre_iter()){
				if(Joueur.id_biathlete == perf.getBiathlete().getId()) {
					//System.out.println((int) Math.round((etat_liste_participante.get(pos_cible).getPhysique().getX_silhouette() + 50 * (etat_liste_participante.get(pos_cible).getDistance() - perf.getDistance() + ( perf.getDistance()/perf.getNombre_iter() ) ) )));
				}
				perf.getPhysique().setX_silhouette((int) Math.round((etat_liste_participante.get(pos_cible).getPhysique().getX_silhouette() + 50 * (etat_liste_participante.get(pos_cible).getDistance() - perf.getDistance() + ( perf.getDistance()/perf.getNombre_iter() ) ) )));
			} else {
				if(Joueur.id_biathlete == perf.getBiathlete().getId()) {
					//System.out.println((int) Math.round((etat_liste_participante.get(pos_cible).getPhysique().getX_silhouette() + 50 * (etat_liste_participante.get(pos_cible).getDistance() - perf.getDistance() - ( perf.getDistance()/perf.getNombre_iter() )      )   )));
				}
				perf.getPhysique().setX_silhouette((int) Math.round((etat_liste_participante.get(pos_cible).getPhysique().getX_silhouette() + 50 * (etat_liste_participante.get(pos_cible).getDistance() - perf.getDistance() - ( perf.getDistance()/perf.getNombre_iter() )      )   )));

			}
		}
	}

	
	//Renvoie une liste trié des performances courante
	public void performancesSort() {
		//On copie la liste des perf
		//ArrayList<Performance> list_sort = list_participants;
		//On la rie selon le num relayeur(inchangé ici) , et la distance parcouru.
		Collections.sort(this.list_participants,Performance.triPhysique);
	}

	public void terminer(){
	}

	public int getY_cible_simulation() {
		return y_cible_simulation;
	}

	public ArrayList<String> getList_type_tir_cd() {
		return list_type_tir_cd;
	}

	public void setList_type_tir_cd(ArrayList<String> list_type_tir_cd) {
		this.list_type_tir_cd = list_type_tir_cd;
	}


	public void setY_cible_simulation(int y_cible_simulation) {
		this.y_cible_simulation = y_cible_simulation;
	}


	public ArrayList<ArrayList<Performance>> getListe_groupe() {
		return liste_groupe;
	}

	public void setListe_groupe(ArrayList<ArrayList<Performance>> liste_groupe) {
		this.liste_groupe = liste_groupe;
	}

	public ArrayList<Performance> getList_participants() {
		return list_participants;
	}

	public void setList_participants(ArrayList<Performance> list_participants) {
		this.list_participants = list_participants;
	}

	public ArrayList<Float> getList_km_pointage() {
		return list_km_pointage;
	}

	public void setList_km_pointage(ArrayList<Float> list_km_pointage) {
		this.list_km_pointage = list_km_pointage;
	}

	public ArrayList<CibleSimulation> getList_simulation_tir() {
		return list_simulation_tir;
	}

	public void setList_simulation_tir(ArrayList<CibleSimulation> list_simulation_tir) {
		this.list_simulation_tir = list_simulation_tir;
	}

	public ArrayList<Classement> getList_classement() {
		return list_classement;
	}

	public void setList_classement(ArrayList<Classement> list_classement) {
		this.list_classement = list_classement;
	}

	public int getVitesse_jeu() {
		return vitesse_jeu;
	}

	public void setVitesse_jeu(int vitesse_jeu) {
		this.vitesse_jeu = vitesse_jeu;
	}

	public int getChrono_course() {
		return chrono_course;
	}

	public void setChrono_course(int chrono_course) {
		this.chrono_course = chrono_course;
	}

	public float getDistance_course() {
		return distance_course;
	}

	public void setDistance_course(float distance_course) {
		this.distance_course = distance_course;
	}


}
