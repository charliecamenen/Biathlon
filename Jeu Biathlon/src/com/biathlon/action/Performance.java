package com.biathlon.action;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import com.biathlon.jeu.Joueur;
import com.biathlon.jeu.Main;
import com.biathlon.jeu.MembreClassement;
import com.biathlon.jeu.Physique;
import com.biathlon.jeu.Souris;

public class Performance implements Runnable {

	//Duree entre deux actualisation
	private final int PAUSE = 100;
	//Vitesse de jeu x1, x2, x4
	private int vitesse_jeu = 1;

	//Objet de silhouette du biathlete
	private Physique physique;
	//Tableau de resultat aux tirs
	private ArrayList<Tir> list_resultat_tir;
	//Liste des km des tirs
	private ArrayList<Float> list_km_tir;
	//Liste des km des tirs
	private ArrayList<Float> list_km_pointage;
	//Biathlete qui fais la performance
	private Biathlete biathlete;
	//Type de tir qu'il va réaliser
	private String type_tir;
	//Distance qu'il a parcouru
	private float distance;
	//Autorisation pour avancer (progresser sur la piste)
	private boolean avancer;
	//Temps depuis lequel il est parti (CHANGER LE TYPE)
	private long chrono_perf;
	//Chronomettre au depart de la course
	private long chrono_init;
	//Retard de l'athlete au depart de la course
	private int retard;
	//penalité en cas de faute
	private String penalite;
	//nombre de tir a réaliser au maximum (5 ou 8)
	private int tir_a_realiser;
	//distance a realiser sur l'anneau de penalite
	private float penalite_distance;
	//a quel tir on est rendu
	private int tir_courrant;
	//a quel pointage on est ?
	private int pointage_courrant;
	//Boolean qui passe a true si l'athlete a terminé sa course
	private boolean course_fini;
	//Niveau aleatoire de ski
	private float alea_niveau;
	//Compteur d'iteration avant la suppression de la simulation
	private int CompteurSupprSimu;
	//Autorisation de supprimer la simulation
	private boolean supprSimu;
	//nombre de fautes
	private int nombre_fautes;
	//En quel position le relayeur part
	private int numero_relayeur = 1;
	//Liste de tirs couche/debout
	private ArrayList<String> list_type_tir_cd;
	//Nombre d'iteration avant de reblanchir la cible joueur
	private int CompteurCibleRougeJoueur;
	//Autorisation de blanchir ou pas
	private boolean rougeSimu ;
	//Nombre d'iteration effectuée (Pour faire debeuger les physiques je crois)
	private int nombre_iter;
	
	//Position dans le classement
	private int pos_classement;
	//Taux d'energie restant
	private float taux_energie;
	//Taux d'energie Maximal
	private float taux_energie_max;
	//Taux d'acceleration restant
	private float taux_acceleration;
	//effort
	private int effort;
	//Pulsation
	private int pulsation;
	
	//Direction vent
	private int dir_vent;
	//vitesse vent
	private float vitesse_vent;
	//vitesse
	private float vitesse;
	//pente
	private float pente;

	//Constructeur de poursuite
	public Performance(Biathlete biathlete, String type_tir, String penalite, ArrayList<Float> list_km_tir, ArrayList<Float> list_km_pointage,   ArrayList<String> list_type_tir_cd, int vitesse_jeu, int retard) {
		super();
		this.list_type_tir_cd =list_type_tir_cd;
		this.biathlete = biathlete;
		this.type_tir = type_tir;
		this.retard = retard;
		this.distance = 0;
		this.penalite_distance = 0;
		this.avancer = false;
		this.list_resultat_tir = new ArrayList<>();
		this.penalite = penalite;
		this.tir_a_realiser = init_nb_tir(type_tir);
		this.tir_courrant = 0;
		this.pointage_courrant = 0;
		this.course_fini = false;
		this.list_km_tir = list_km_tir;
		this.list_km_pointage = list_km_pointage;
		this.vitesse_jeu = vitesse_jeu;
		this.nombre_fautes = 0;
		this.physique = new Physique(biathlete.getId_equipe(),800,705);
		//Création des tirs de l'athlete
		this.creationTir();
	}

	//Constructeur par default 
	public Performance(Biathlete biathlete, String type_tir, String penalite, ArrayList<Float> list_km_tir, ArrayList<Float> list_km_pointage,  ArrayList<String> list_type_tir_cd, int vitesse_jeu) {
		super();
		this.biathlete = biathlete;
		this.type_tir = type_tir;
		this.list_type_tir_cd =list_type_tir_cd;
		this.retard = 0;
		this.distance = 0;
		this.penalite_distance = 0;
		this.avancer = false;
		this.list_resultat_tir = new ArrayList<>();
		this.penalite = penalite;
		this.tir_a_realiser = init_nb_tir(type_tir);
		this.tir_courrant = 0;
		this.pointage_courrant = 0;
		this.course_fini = false;
		this.list_km_tir = list_km_tir;
		this.list_km_pointage = list_km_pointage;
		this.nombre_fautes = 0;
		this.physique = new Physique(biathlete.getId_equipe(),800,705);
		
		//PROVISOIR !
		//Calcul du taux maximal d'energie (faire une fonction) FDJ, Forme , Fatigue ...
		this.taux_energie_max = (float) 78;
		this.taux_energie = (float) 50;
		this.taux_acceleration = 100;
		this.pulsation = 144;
		this.effort = 75;
		this.dir_vent = 46;
		this.vitesse_vent = (float) 23.4;
		this.vitesse = (float) 35.9;
		this.pente = (float) 0.3;

		Random random_ski = new Random();
		this.alea_niveau = (float) (this.biathlete.getSki() + random_ski.nextGaussian() * 2);
		this.vitesse_jeu = vitesse_jeu;
		//Création des tirs de l'athlete
		this.creationTir();
	}

	private void creationTir() {
		//Boucle sur la liste des km de tir 
		for(int i =0;  i < this.list_km_tir.size() ; i++) {

			//Si il s'agit de notre biathlete
			if (biathlete.getId() == Joueur.id_biathlete) {

				//On créé un objet tir
				TirJoueur tir = new TirJoueur(biathlete.getId(), biathlete.getCou(), biathlete.getReg(), biathlete.getVit(),0, tir_a_realiser, this.list_type_tir_cd.get(i));
				//On l'ajoute a la liste des tir de la performance
				this.list_resultat_tir.add(tir);

			} else {

				//On créé un objet tir
				Tir tir = new Tir(biathlete.getId(), biathlete.getCou(), biathlete.getReg(), biathlete.getVit(),0, tir_a_realiser, this.list_type_tir_cd.get(i));
				//On l'ajoute a la liste des tir de la performance
				this.list_resultat_tir.add(tir);
			}
		}
	}



	public void depart(long retard) {

		this.nombre_iter = 0;
		//Pour gerer les retard (plus facile pour les relays) de plus ca permet de pas faire 2 constructeurs
		this.chrono_perf = retard;
		//On initialise le chrono de debut
		//this.chrono_init = System.currentTimeMillis();

		//On autorise a avancer
		this.avancer = true;

		//On lance le chrono
		Thread chrono = new Thread(this);
		chrono.start();

	}

	public void tir(){

		//On execute le dernier tir créé
		this.list_resultat_tir.get(this.tir_courrant).execution();

		//Si il s'agit du biathlete du joueur
		if(Joueur.id_biathlete == this.biathlete.getId()) {

			Main.scene.addMouseMotionListener((TirJoueur)  this.list_resultat_tir.get(this.tir_courrant));
			Main.scene.addMouseListener((TirJoueur) this.list_resultat_tir.get(this.tir_courrant));
		}

	}

	private int init_nb_tir(String type_tir) {
		switch(type_tir) {
		case "classique": return 5; 
		case "relais": return 8; 
		default : return 0;
		}
	}

	public void ajouterPenalite(int nbr_fautes) {
		System.out.println(this.chrono_perf);
		System.out.println(penalite);
		//en fonction de la pénalité a réaliser
		switch(this.penalite) {
		case "tour":
			this.penalite_distance += nbr_fautes * 150 ;
			break; 
		case "minute":
			this.chrono_perf += nbr_fautes *  60000;
			break; 
		}
		System.out.println(this.chrono_perf);
		this.nombre_fautes += nbr_fautes;
		//On l'autorise a avance
		setAvancer(true);
	}

	//Permet de calculer la vitesse pour faire avancer l'athlete
	public void avancer() {
		//0.000397x − y + 0.11903 = 0
		// 0.001984 x + 0.5952
		float distance_par_ms = (float) (0.001984 * alea_niveau  + 0.5952);

		//si il a des tour de penalité a parcourir ?
		if (penalite_distance > 0) {
			penalite_distance -= distance_par_ms;
		} else {
			//On incrémente la distance
			distance += distance_par_ms;
		}

	}

	public void run() {

		//Tant que la course n'est pas fini
		while(course_fini == false) {
			
			this.nombre_iter +=1;
			
			//On fait dormir le programme PAUSE 
			try{
				Thread.sleep((int) PAUSE/vitesse_jeu);
			}catch (InterruptedException e) {}

			//On incrémente le chrono
			this.chrono_perf += PAUSE;

			//Si le joueur est autorisé a avancer
			if (this.avancer == true) {			

				//Si il reste des tirs 
				if(this.list_km_tir.size() > this.tir_courrant) {

					//Si le prochain tir est inferieur a la distance parcouru
					if (this.list_km_tir.get(this.tir_courrant).floatValue() <= distance) {

						//alors on créé le tir
						this.tir();

						//On auorise plus a avancer
						this.setAvancer(false);

						//Si il est pas encore arrivé au pas de tir on avance
					} else {
						
						this.physique.changeApparance();

						//Si ca n'est pas le cas il peut avancer tranquillement
						this.avancer();
					}

					//Si il ne reste plus de tirs on avance
				} else {
					this.avancer();
				}

				//Si il reste des pointage
				if(this.list_km_pointage.size() > this.pointage_courrant) {

					// Si le prochain pointage est inferieur a la distance parcouru
					if(this.list_km_pointage.get(this.pointage_courrant).floatValue() <= distance) {

						//On ajoute le biathlete au classement du pointage courrant
						Joueur.course.ajouterMembreClassement(this);

						//On passe au pointage d'apres
						this.pointage_courrant +=1;
					}

				} else {// sinon c'est que la course est terminée on arrete le chrono

					System.out.println("COURSE FINI POUR : " + this.biathlete.getLibelle());

					//On indique que la course est fini (utile pour les passage de relais)
					course_fini = true;

					//On passe le relais
					Joueur.course.passageRelais(this);
				}

				//PEUT ETRE UN AUTRE MOYEN IL FAUT REFLECHIR 		

				//Si c'est le biathlete du joueur
				if (this.biathlete.getId() == Joueur.id_biathlete) {

					//On reblanchis la cible
					Joueur.cible_joueur.blanchirCible();
				}

				//On décrémente le compteur
				this.CompteurSupprSimu -=1;

				//Si on arrive a 0 alors on autorise
				if(this.CompteurSupprSimu == 0) {
					this.supprSimu = true;
				}

				if(this.supprSimu == true) {

					//Si c'est le biathlete du joueur
					if (this.biathlete.getId() == Joueur.id_biathlete) {

						//On supprime l'affichage de la cible joueur
						Main.scene.setTir_joueur(false);

						//On supprime les caches cibles pour ne plus les afficher
						Joueur.removeListCacheCible();
					}

					//On supprime la simulation
					Joueur.course.supprimerSimulationTir(biathlete.getId());

					this.supprSimu = false;
				}

			} else {//Si il est sur le pas de tir (non autorisé a avancer)

				//Si c'est le biathlete du joueur
				if (this.biathlete.getId() == Joueur.id_biathlete) {

					//On reblanchis la cible
					Joueur.cible_joueur.blanchirCible();
				}

				//Si le tir est terminé
				if (this.list_resultat_tir.get(this.tir_courrant).getCompteurTir() == this.tir_a_realiser | this.list_resultat_tir.get(this.tir_courrant).getNb_reussi() == 5 ) {
					System.out.println("Partir du pas de tir");

					//On ajoute les penalité qu'il doit recevoir (Nb d'erreur dans le tir courrant) + on l'autorise a repartir
					this.ajouterPenalite(5 - list_resultat_tir.get(this.tir_courrant).getNb_reussi());

					//On passe au suivant
					this.tir_courrant += 1;

					//On attend 30 itteration avant de supprimer la simulation
					this.CompteurSupprSimu = 30;

				}
			}

		}

	}



	public ArrayList<String> getList_type_tir_cd() {
		return list_type_tir_cd;
	}

	public void setList_type_tir_cd(ArrayList<String> list_type_tir_cd) {
		this.list_type_tir_cd = list_type_tir_cd;
	}

	public int getCompteurCibleRougeJoueur() {
		return CompteurCibleRougeJoueur;
	}

	public void setCompteurCibleRougeJoueur(int compteurCibleRougeJoueur) {
		CompteurCibleRougeJoueur = compteurCibleRougeJoueur;
	}

	public boolean isRougeSimu() {
		return rougeSimu;
	}

	public int getNombre_iter() {
		return nombre_iter;
	}

	public int getEffort() {
		return effort;
	}

	public void setEffort(int effort) {
		this.effort = effort;
	}

	public int getPulsation() {
		return pulsation;
	}
	
	public void setPulsation(int pulsation) {
		this.pulsation = pulsation;
	}

	public void setNombre_iter(int nombre_iter) {
		this.nombre_iter = nombre_iter;
	}

	public void setRougeSimu(boolean rougeSimu) {
		this.rougeSimu = rougeSimu;
	}

	public int getVitesse_jeu() {
		return vitesse_jeu;
	}

	public void setVitesse_jeu(int vitesse_jeu) {
		this.vitesse_jeu = vitesse_jeu;
	}

	public ArrayList<Tir> getList_tir() {
		return list_resultat_tir;
	}

	public void setList_tir(ArrayList<Tir> list_tir) {
		this.list_resultat_tir = list_tir;
	}

	public void setChrono_perf(int chrono_perf) {
		this.chrono_perf = chrono_perf;
	}

	public int getRetard() {
		return retard;
	}

	public void setRetard(int retard) {
		this.retard = retard;
	}

	public long getChrono_perf() {
		return chrono_perf;
	}

	public void setChrono_perf(long chrono_perf) {
		this.chrono_perf = chrono_perf;
	}

	public long getChrono_init() {
		return chrono_init;
	}

	public void setChrono_init(long chrono_init) {
		this.chrono_init = chrono_init;
	}

	public ArrayList<Tir> getList_resultat_tir() {
		return list_resultat_tir;
	}

	public void setList_resultat_tir(ArrayList<Tir> list_resultat_tir) {
		this.list_resultat_tir = list_resultat_tir;
	}

	public float getAlea_niveau() {
		return alea_niveau;
	}

	public void setAlea_niveau(float alea_niveau) {
		this.alea_niveau = alea_niveau;
	}

	public int getNumero_relayeur() {
		return numero_relayeur;
	}

	public void setNumero_relayeur(int numero_relayeur) {
		this.numero_relayeur = numero_relayeur;
	}

	public int getCompteurSupprSimu() {
		return CompteurSupprSimu;
	}

	public void setCompteurSupprSimu(int compteurSupprSimu) {
		CompteurSupprSimu = compteurSupprSimu;
	}

	public boolean isSupprSimu() {
		return supprSimu;
	}

	public void setSupprSimu(boolean supprSimu) {
		this.supprSimu = supprSimu;
	}

	public int getNombre_fautes() {
		return nombre_fautes;
	}

	public void setNombre_fautes(int nombre_fautes) {
		this.nombre_fautes = nombre_fautes;
	}

	public ArrayList<Float> getList_km_tir() {
		return list_km_tir;
	}

	public void setList_km_tir(ArrayList<Float> list_km_tir) {
		this.list_km_tir = list_km_tir;
	}

	public ArrayList<Float> getList_km_pointage() {
		return list_km_pointage;
	}

	public void setList_km_pointage(ArrayList<Float> list_km_pointage) {
		this.list_km_pointage = list_km_pointage;
	}

	public String getPenalite() {
		return penalite;
	}

	public void setPenalite(String penalite) {
		this.penalite = penalite;
	}

	public int getTir_a_realiser() {
		return tir_a_realiser;
	}

	public void setTir_a_realiser(int tir_a_realiser) {
		this.tir_a_realiser = tir_a_realiser;
	}

	public float getPenalite_distance() {
		return penalite_distance;
	}

	public void setPenalite_distance(float penalite_distance) {
		this.penalite_distance = penalite_distance;
	}

	public int getTir_courrant() {
		return tir_courrant;
	}

	public void setTir_courrant(int tir_courrant) {
		this.tir_courrant = tir_courrant;
	}

	public int getPointage_courrant() {
		return pointage_courrant;
	}

	public void setPointage_courrant(int pointage_courrant) {
		this.pointage_courrant = pointage_courrant;
	}

	public boolean isCourse_fini() {
		return course_fini;
	}

	public void setCourse_fini(boolean course_fini) {
		this.course_fini = course_fini;
	}

	public static Comparator<Performance> getTriRetard() {
		return triRetard;
	}

	public static void setTriRetard(Comparator<Performance> triRetard) {
		Performance.triRetard = triRetard;
	}

	public int getPAUSE() {
		return PAUSE;
	}

	public Biathlete getBiathlete() {
		return biathlete;
	}

	public void setBiathlete(Biathlete biathlete) {
		this.biathlete = biathlete;
	}

	public String getType_tir() {
		return type_tir;
	}

	public void setType_tir(String type_tir) {
		this.type_tir = type_tir;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public boolean isAvancer() {
		return avancer;
	}

	
	public float getTaux_energie() {
		return taux_energie;
	}

	public void setTaux_energie(float taux_energie) {
		this.taux_energie = taux_energie;
	}

	public float getTaux_energie_max() {
		return taux_energie_max;
	}

	public void setTaux_energie_max(float taux_energie_max) {
		this.taux_energie_max = taux_energie_max;
	}

	public float getTaux_acceleration() {
		return taux_acceleration;
	}

	public void setTaux_acceleration(float taux_acceleration) {
		this.taux_acceleration = taux_acceleration;
	}

	public static Comparator<Performance> getTriPhysique() {
		return triPhysique;
	}

	public static void setTriPhysique(Comparator<Performance> triPhysique) {
		Performance.triPhysique = triPhysique;
	}

	public int getPos_classement() {
		return pos_classement;
	}

	public void setPos_classement(int pos_classement) {
		this.pos_classement = pos_classement;
	}

	public int getDir_vent() {
		return dir_vent;
	}

	public void setDir_vent(int dir_vent) {
		this.dir_vent = dir_vent;
	}


	public float getVitesse_vent() {
		return vitesse_vent;
	}

	public void setVitesse_vent(float vitesse_vent) {
		this.vitesse_vent = vitesse_vent;
	}

	public float getVitesse() {
		return vitesse;
	}

	public void setVitesse(float vitesse) {
		this.vitesse = vitesse;
	}

	public float getPente() {
		return pente;
	}

	public void setPente(float pente) {
		this.pente = pente;
	}

	public void setAvancer(boolean avancer) {
		this.avancer = avancer;
	}

	public void setchrono_perf(int chrono_perf) {
		this.chrono_perf = chrono_perf;
	}


	public Physique getPhysique() {
		return physique;
	}

	public void setPhysique(Physique physique) {
		this.physique = physique;
	}


	public static Comparator<Performance> triRetard = new Comparator<Performance>() {
		public int compare (Performance p2,Performance p1) {
			Integer nbp1 = p1.getRetard();
			Integer nbp2 = p2.getRetard();
			int resultat;
			resultat=nbp1.compareTo(nbp2);
			return resultat;
		}
	};

	public static Comparator<Performance> triPhysique = new Comparator<Performance>() {
		public int compare (Performance p2,Performance p1) {
			Integer r1 = p1.getNumero_relayeur();
			Integer r2 = p1.getNumero_relayeur();
			Float d1 = p1.getDistance();
			Float d2 = p2.getDistance();
			int resultat;


			if(r1.compareTo(r2) == 0){
				resultat=d1.compareTo(d2);
			} else {
				resultat=r1.compareTo(r2);
			}
			
			System.out.println();
			return resultat;
		}
	};


}
