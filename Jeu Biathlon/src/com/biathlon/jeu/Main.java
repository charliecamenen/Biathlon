package com.biathlon.jeu;

import java.awt.Button;
import java.awt.Color;
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
import com.biathlon.action.Performance;
import com.biathlon.action.Saison;
import com.biathlon.courses.Course;
import com.biathlon.user.UserAction;

// Import
import java.awt.Dimension;

public class Main {

	//création de la scene (visible de partout parce qu'elle est static)
	public static Scene scene;
	public static Joueur joueur;
	public static SqlBase database;
	public static UserAction useraction;
	public static Saison saison;
	public static Course course_simple;
	public static Performance performance;
	static JFrame acceuil;
	public static JFrame fenetre;
	static JFrame menu_course;
	static JFrame menu_participant;

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
		fenetre.setAlwaysOnTop(true);


		// Création de la fenetre d'acceuil
		acceuil = new JFrame("Jeu Biathlon");
		//Ferme la fenetre si on clique sur la croix
		acceuil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		acceuil.setSize(400,250);
		acceuil.setLocationRelativeTo(null);
		//Peut on modifier les dimensions?
		acceuil.setResizable(false);
		//Doit elles etre en premier plan tout le temps ?
		acceuil.setAlwaysOnTop(true);

		Accueil menu_choix = new Accueil();

		acceuil.setContentPane(menu_choix);
		acceuil.setVisible(true);

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


			//ON créé l'objet joueur
			joueur = new Joueur("course");

			//-----------------------------------------//
			// Création de la fenetre choix de la course
			menu_course = new JFrame("Jeu Biathlon");
			//Ferme la fenetre si on clique sur la croix
			menu_course.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			menu_course.setSize(500,600);
			menu_course.setLocationRelativeTo(null);
			//Peut on modifier les dimensions?
			menu_course.setResizable(false);
			//Doit elles etre en premier plan tout le temps ?
			menu_course.setAlwaysOnTop(true);
			//-----------------------------------------//

			//Ouvre la fenetre choix course
			ChoixCourse choix_course = new ChoixCourse();
			menu_course.setContentPane(choix_course);
			//Supprime la fenetre acceuil
			acceuil.dispose();
			menu_course.setVisible(true);
			break;







			//chrono = new Chrono();
			// 1h 12:33.6
			//4353600
		case 2://On lance une carriere

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

		//-----------------------------------------//
		// Création de la choix des membres de l'equipe
		menu_participant = new JFrame("Jeu Biathlon");
		//Ferme la fenetre si on clique sur la croix
		menu_participant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu_participant.setSize(400,600);
		menu_participant.setLocationRelativeTo(null);
		//Peut on modifier les dimensions?
		menu_participant.setResizable(false);
		//Doit elles etre en premier plan tout le temps ?
		menu_participant.setAlwaysOnTop(true);
		//-----------------------------------------//
		menu_course.dispose();

		joueur.setSexe(sexe);

		ResultSet resultat;
		ArrayList<String> list_equipe = new ArrayList<>();
		ChoixParticipant choix_participant;

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

				//On ajoute a la fenetre
				menu_participant.setContentPane(choix_participant);

				//Supprime la fenetre acceuil
				menu_participant.setVisible(true);
				break;

			case "Femme":

				//Ouvre la fenetre choix d'equipe 
				choix_participant = new ChoixParticipant(list_equipe, "F");

				//On ajoute a la fenetre
				menu_participant.setContentPane(choix_participant);

				//Supprime la fenetre acceuil
				menu_participant.setVisible(true);
				break;

			case "Mixte":

				//Ouvre la fenetre choix d'equipe 
				choix_participant = new ChoixParticipant(list_equipe, "M");

				//On ajoute a la fenetre
				menu_participant.setContentPane(choix_participant);

				//Supprime la fenetre acceuil
				menu_participant.setVisible(true);

			}



			break;

		case "i": //INDIVIDUEL
			switch(sexe) {
			case "Homme":

				//Ouvre la fenetre choix d'equipe 
				choix_participant = new ChoixParticipant("H");

				//On ajoute a la fenetre
				menu_participant.setContentPane(choix_participant);

				//Supprime la fenetre acceuil
				menu_participant.setVisible(true);
				break;

			case "Femme":

				//Ouvre la fenetre choix d'equipe 
				choix_participant = new ChoixParticipant("F");

				//On ajoute a la fenetre
				menu_participant.setContentPane(choix_participant);

				//Supprime la fenetre acceuil
				menu_participant.setVisible(true);
				break;

			}
			break;


		}

	}

	public static void lancerJeu() {
		//Instanciation de la scene
		scene = new Scene();
		menu_participant.dispose();
		fenetre.setContentPane(scene);
		fenetre.setVisible(true);
	}



}
