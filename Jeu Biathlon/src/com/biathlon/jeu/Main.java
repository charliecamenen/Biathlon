package com.biathlon.jeu;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.biathlon.action.Biathlete;
import com.biathlon.action.ChargementPartie;
import com.biathlon.action.Gestion;
import com.biathlon.action.InterfaceGraphique;
import com.biathlon.action.Performance;
import com.biathlon.action.Saison;
import com.biathlon.courses.Course;
import com.biathlon.user.UserAction;

// Import
import java.awt.Dimension;

public class Main {

	//création de la scene (visible de partout parce qu'elle est static)
	public static Scene scene;
	public static Accueil acceuil;
	public static Gestion gestion;
	public static Joueur joueur;
	public static SqlBase database;
	public static UserAction useraction;
	public static Saison saison;
	public static Course course_simple;
	public static Performance performance;
	public static JFrame fenetre;

	public static Chrono chrono;

	public static void main(String[] args) {

		fenetre = new JFrame("Jeu Biathlon");
		//Ferme la fenetre si on clique sur la croix
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//On utilise la taille maximale de l'ecran
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		// Taille de l'écran 
		System.out.println(dimension.getWidth() + " " + dimension.getHeight() );
		if(dimension.getWidth() > 1.84*dimension.getHeight()) {
			fenetre.setSize((int)Math.round(dimension.getHeight() * 1.78), (int) dimension.getHeight());	
		}else {
			fenetre.setSize((int) dimension.getWidth(), (int)Math.round(dimension.getWidth()/1.78));
		}

		fenetre.setLocationRelativeTo(null);
		//Peut on modifier les dimensions?
		fenetre.setResizable(true);

		//Doit elles etre en premier plan tout le temps ?
		fenetre.setAlwaysOnTop(false);

		//Acceuil du jeu (au lencement)
		acceuil = new Accueil();

		//le choix du menu est affiché
		fenetre.setContentPane(acceuil);
		fenetre.setVisible(true);

		//-------------------------------------------------------------------------//
		/*

		saison = new Saison();
		saison.prochaineCourse();
		//Biathlete biathlete = new Biathlete(1);
		//performance = new Performance(biathlete,"Classique", 0);

		 */

	}

	public static void typePartieFenetre(int choix){
		//Instanciation de la partie utilisateur
		//useraction = new UserAction();

		//Instanciation de la base de donnée
		database = new SqlBase();

		switch(choix){
		case 1://On lance une course simple

			//On créé l'objet joueur
			joueur = new Joueur("course");

			//Ouvre la fenetre choix course
			ChoixCourse choix_course = new ChoixCourse();
			fenetre.setContentPane(choix_course);
			fenetre.setVisible(true);
			break;

		case 2://On lance une carriere

			ChargementPartie chargement_partie = new ChargementPartie();
			fenetre.setContentPane(chargement_partie);
			
			//gestion = new Gestion();
			//fenetre.setContentPane(gestion);
			fenetre.setVisible(true);
			//LocalDateTime test = LocalDateTime.now();
			//System.out.println(test.getHour() + 5);
			//On créée l'objet joueur
			//joueur = new Joueur("carriere");

			//On appel la fonction qui a le switch case
			//lancerJeu();

			//saison = new Saison();
			break;
		}
	}

	public static void choixParticipantFenetre(String ind_mult, String sexe) {

		//Change le sexe du joueur
		joueur.setSexe(sexe);

		ResultSet resultat;
		ArrayList<String> list_equipe = new ArrayList<>();
		Container choix_participant;

		switch(ind_mult) {
		case "m": //RELAIS

			//Requete qui rempli list_equipe
			resultat = database.requete("SELECT equipes.libelle_equipe FROM biathletes JOIN equipes on (equipes.id_equipe = biathletes.id_equipe) GROUP BY equipes.libelle_equipe");

			try {
				while (resultat.next()){
					//On ajoute les pays a la liste
					list_equipe.add(resultat.getString("libelle_equipe"));
				}

			} catch (SQLException e) {e.printStackTrace();}


			switch(sexe) {
			case "Homme":

				//Ouvre la fenetre choix d'equipe 
				choix_participant = new ChoixParticipant(list_equipe, "H");

				break;

			case "Femme":

				//Ouvre la fenetre choix d'equipe 
				choix_participant = new ChoixParticipant(list_equipe, "F");

				break;

			case "Mixte":

				//Ouvre la fenetre choix d'equipe 
				choix_participant = new ChoixParticipant(list_equipe, "M");

			default:
				//On ajoute a la fenetre
				choix_participant = new ChoixParticipant("H");
			}


			break;

		case "i": //INDIVIDUEL
			switch(sexe) {
			case "Homme":

				//Ouvre la fenetre choix d'equipe 
				choix_participant = new ChoixParticipant("H");

				break;

			case "Femme":

				//Ouvre la fenetre choix d'equipe 
				choix_participant = new ChoixParticipant("F");

				break;
			default:
				//On ajoute a la fenetre
				choix_participant = new ChoixParticipant("H");
				
			}
			break;
			
		default:
			//On ajoute a la fenetre
			choix_participant = new ChoixParticipant("H");

		}

		//On ajoute a la fenetre
		fenetre.setContentPane(choix_participant);
		fenetre.setVisible(true);
	}

	public static void lancerJeu() {
		//Instanciation de la scene

		fenetre.dispose();
		scene = new Scene();
		fenetre.setContentPane(scene);
		fenetre.setVisible(true);
	}



}
