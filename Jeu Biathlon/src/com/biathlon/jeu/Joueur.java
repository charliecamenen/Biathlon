package com.biathlon.jeu;

import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.biathlon.action.Carriere;
import com.biathlon.action.Cible;
import com.biathlon.courses.ContreLaMontre;
import com.biathlon.courses.Course;
import com.biathlon.courses.MassStart;
import com.biathlon.courses.Poursuite;
import com.biathlon.courses.Relais;

public class Joueur {
	
	//La course auquel le joueur paricipe
	public static Course course;
	
	//La cible sur laquelle le joueur va tirer
	public static CibleJoueur cible_joueur;
	
	//Type de jeu
	private String type_jeu;
	
	//PROVISOIRE JE PENSES
	//Es ce qu'il y a une poursuite apres le sprint ? 
	private boolean faire_poursuite = false;
	
	//Mon biathlete actuel
	public static int id_biathlete;
	private ArrayList<Integer> list_id_biathlete;

	//Type de course 
	private int type_course;
	
	//Id d'equipe de l'athlete
	private String id_equipe;
	
	//Sexe de l'athlete
	private String sexe;
	
	//Fond d'écran image
	private ImageIcon ico_bg;
	private Image img_bg;
	
	//Competition courrante
	private ResultSet compet_courrante;
	
	//Compteur d'iteration de performance avant de blanchir la cible
	private int compteurBlanchirCible = 0 ;
	
	public Joueur(String type_jeu) {
		this.type_jeu = type_jeu;
	}
	
	//Course simple : individuelles
	public void monBiathleteIndiv(int id_biathlete) {
		//On met le meme au mec et a la meuf
		this.id_biathlete = id_biathlete;
	}
	
	//Course simple equipe
	public void mesBiathleteEquipe(ArrayList<Integer> list_id_biathlete) {
		this.list_id_biathlete = list_id_biathlete;
	}
	
	
	
	public void setLieu(String lieu_compet) {
		//On met a jour la competition courrante
		compet_courrante = Main.database.requete("SELECT * FROM competitions where lieu_compet = '" + lieu_compet + "'");
		//On met a jour le background
		try {
			while(compet_courrante.next()) {
				this.actuBg(compet_courrante.getString("background_file"));
			}
			
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	

	public void actuBg(String bg_file) {
		//Creation de l'image de fond d'ecran
		ico_bg = new ImageIcon(getClass().getResource(bg_file));
		this.img_bg = this.ico_bg.getImage();
	}
	
	
	
	public static void removeListCacheCible() {
		cible_joueur.getList_cache_cible().clear();
	}
	
	public void demarrerCourseSimple() {
		System.out.println(type_course);
		ResultSet participant;
		ArrayList<Float> list_km_tir;
		ArrayList<Float> list_km_pointage;
		ArrayList<Float> list_km_tir_h;
		ArrayList<Float> list_km_pointage_h;
		ArrayList<Float> list_km_tir_f;
		ArrayList<Float> list_km_pointage_f;
		ArrayList<String> list_type_tir_cd;
		
		cible_joueur = new CibleJoueur(this.id_biathlete, 220);
		
		switch(type_course) {
		case 1:
			
			//Liste des equipes qui ont au moins 1 femmes et 1 hommes
			participant = Main.database.requete(""
					+ "SELECT id_equipe from equipes WHERE id_equipe in "
					+ "(select id_equipe from biathletes where sexe_biathlete = 'H' "
					+ "group by biathletes.id_equipe having count(*) > 1) "
					+ "AND "
					+ "id_equipe in "
					+ "(select id_equipe from biathletes where sexe_biathlete = 'F'"
					+ " group by biathletes.id_equipe having count(*) > 1) "
					+ "");
			
			
			//Pointages intermediaires
			list_km_pointage_h = new ArrayList<>();
			list_km_pointage_h.add(new Float(900));
			list_km_pointage_h.add(new Float(1800));
			//Pointage du tir
			list_km_pointage_h.add(new Float(2520));
			list_km_pointage_h.add(new Float(3400));
			list_km_pointage_h.add(new Float(4200));
			//Pointage du tir
			list_km_pointage_h.add(new Float(5020));
			list_km_pointage_h.add(new Float(5800));
			list_km_pointage_h.add(new Float(6800));
			//Arrivée de la course
			list_km_pointage_h.add(new Float(7500));
			
			//Pointages intermediaires
			list_km_pointage_f = new ArrayList<>();
			list_km_pointage_f.add(new Float(700));
			list_km_pointage_f.add(new Float(1400));
			//Pointage du tir
			list_km_pointage_f.add(new Float(2020));
			list_km_pointage_f.add(new Float(2700));
			list_km_pointage_f.add(new Float(3400));
			//Pointage du tir
			list_km_pointage_f.add(new Float(4020));
			list_km_pointage_f.add(new Float(4700));
			list_km_pointage_f.add(new Float(5400));
			//Arrivée de la course
			list_km_pointage_f.add(new Float(6000));
			
			
			//2 tirs
			list_km_tir_h = new ArrayList<>();
			list_km_tir_h.add(new Float(2500));
			list_km_tir_h.add(new Float(5000));
			//2 tirs
			list_km_tir_f = new ArrayList<>();
			list_km_tir_f.add(new Float(2000));
			list_km_tir_f.add(new Float(4000));
			
			//Type des tirs
			list_type_tir_cd = new ArrayList<>();
			list_type_tir_cd.add("couche");
			list_type_tir_cd.add("debout");
			
			
			course = new Relais(participant, list_km_tir_h, list_km_tir_f,list_km_pointage_h,list_km_pointage_f ,list_type_tir_cd, 2);
			course.demarrer();
			
			break;//relais mixte
			
		case 2:
			
			//Liste des equipes qui ont au moins 2 femmes et deux hommes
			participant = Main.database.requete(""
					+ "SELECT id_equipe from equipes WHERE id_equipe in "
					+ "(select id_equipe from biathletes where sexe_biathlete = 'H' "
					+ "group by biathletes.id_equipe having count(*) > 0) "
					+ "AND "
					+ "id_equipe in "
					+ "(select id_equipe from biathletes where sexe_biathlete = 'F'"
					+ " group by biathletes.id_equipe having count(*) > 0) "
					+ "");
			
			//Pointages intermediaires
			list_km_pointage_h = new ArrayList<>();
			list_km_pointage_h.add(new Float(900));
			list_km_pointage_h.add(new Float(1800));
			//Pointage du tir
			list_km_pointage_h.add(new Float(2520));
			list_km_pointage_h.add(new Float(3400));
			list_km_pointage_h.add(new Float(4200));
			//Pointage du tir
			list_km_pointage_h.add(new Float(5020));
			list_km_pointage_h.add(new Float(5800));
			list_km_pointage_h.add(new Float(6800));
			//Arrivée de la course
			list_km_pointage_h.add(new Float(7500));
			
			//Pointages intermediaires
			list_km_pointage_f = new ArrayList<>();
			list_km_pointage_f.add(new Float(700));
			list_km_pointage_f.add(new Float(1400));
			//Pointage du tir
			list_km_pointage_f.add(new Float(2020));
			list_km_pointage_f.add(new Float(2700));
			list_km_pointage_f.add(new Float(3400));
			//Pointage du tir
			list_km_pointage_f.add(new Float(4020));
			list_km_pointage_f.add(new Float(4700));
			list_km_pointage_f.add(new Float(5400));
			//Arrivée de la course
			list_km_pointage_f.add(new Float(6000));
			
			
			//2 tirs
			list_km_tir_h = new ArrayList<>();
			list_km_tir_h.add(new Float(2500));
			list_km_tir_h.add(new Float(5000));
			//2 tirs
			list_km_tir_f = new ArrayList<>();
			list_km_tir_f.add(new Float(2000));
			list_km_tir_f.add(new Float(4000));
			
			//Type des tirs
			list_type_tir_cd = new ArrayList<>();
			list_type_tir_cd.add("couche");
			list_type_tir_cd.add("debout");
			
			course = new Relais(participant, list_km_tir_h, list_km_tir_f,list_km_pointage_h,list_km_pointage_f ,list_type_tir_cd, 1);
			
			course.demarrer();
			
			break;//relais mixte simple
		
		
			
		case 3: //relais homme
			
			//Identifiant d'equipes qui compte au moins 4 hommes
			participant = Main.database.requete("select id_equipe, count(*) as cpt from biathletes where sexe_biathlete = 'H' group by biathletes.id_equipe having cpt > 3 ");
			
			//Pointages intermediaires
			list_km_pointage = new ArrayList<>();
			list_km_pointage.add(new Float(900));
			list_km_pointage.add(new Float(1800));
			//Pointage du tir
			list_km_pointage.add(new Float(2520));
			list_km_pointage.add(new Float(3400));
			list_km_pointage.add(new Float(4200));
			//Pointage du tir
			list_km_pointage.add(new Float(5020));
			list_km_pointage.add(new Float(5800));
			list_km_pointage.add(new Float(6800));
			//Arrivée de la course
			list_km_pointage.add(new Float(7500));
			
			//2 tirs
			list_km_tir = new ArrayList<>();
			list_km_tir.add(new Float(2500));
			list_km_tir.add(new Float(5000));
			
			//Type des tirs
			list_type_tir_cd = new ArrayList<>();
			list_type_tir_cd.add("couche");
			list_type_tir_cd.add("debout");
			
			course = new Relais(participant, list_km_tir, list_km_pointage, list_type_tir_cd,4, "H");
			course.demarrer();
			break;
		
		
		
		
		case 4: //relais femme
			
			//Identifiant d'equipes qui compte au moins 4 femmes
			participant = Main.database.requete("select id_equipe, count(*) as cpt from biathletes where sexe_biathlete = 'F' group by biathletes.id_equipe having cpt > 3 ");
					
			//Pointages intermediaires
			list_km_pointage = new ArrayList<>();
			list_km_pointage.add(new Float(700));
			list_km_pointage.add(new Float(1400));
			//Pointage du tir
			list_km_pointage.add(new Float(2020));
			list_km_pointage.add(new Float(2700));
			list_km_pointage.add(new Float(3400));
			//Pointage du tir
			list_km_pointage.add(new Float(4020));
			list_km_pointage.add(new Float(4700));
			list_km_pointage.add(new Float(5400));
			//Arrivée de la course
			list_km_pointage.add(new Float(6000));
			
			//2 tirs
			list_km_tir = new ArrayList<>();
			list_km_tir.add(new Float(2000));
			list_km_tir.add(new Float(4000));
			
			//Type des tirs
			list_type_tir_cd = new ArrayList<>();
			list_type_tir_cd.add("couche");
			list_type_tir_cd.add("debout");
			
			course = new Relais(participant, list_km_tir, list_km_pointage,list_type_tir_cd,  4, "H");
			course.demarrer();
			
			break;
		
			
		case 5: //sprint homme

			// requete sur les biathletes
			participant = Main.database.requete("SELECT biathletes.* FROM biathletes where sexe_biathlete = 'H'");
			
			//Pointages intermediaires
			list_km_pointage = new ArrayList<>();
			list_km_pointage.add(new Float(1200));
			list_km_pointage.add(new Float(2400));
			//Pointage du tir
			list_km_pointage.add(new Float(3220));
			list_km_pointage.add(new Float(4200));
			list_km_pointage.add(new Float(5400));
			//Pointage du tir
			list_km_pointage.add(new Float(6620));
			list_km_pointage.add(new Float(7600));
			list_km_pointage.add(new Float(8400));
			list_km_pointage.add(new Float(9200));
			//Arrivée de la course
			list_km_pointage.add(new Float(10000));
			
			//2 tirs
			list_km_tir = new ArrayList<>();
			list_km_tir.add(new Float(3200));
			list_km_tir.add(new Float(6600));
			
			//Type des tirs
			list_type_tir_cd = new ArrayList<>();
			list_type_tir_cd.add("couche");
			list_type_tir_cd.add("debout");
			
			course = new ContreLaMontre(participant, list_km_tir, list_km_pointage,list_type_tir_cd, "tour");
			
			//Démarrer la course
			course.demarrer();
			break;
			
		case 6: //sprint femme

			// requete sur les biathletes
			participant = Main.database.requete("SELECT biathletes.* FROM biathletes where sexe_biathlete = 'F'");
			
			//Pointages intermediaires
			list_km_pointage = new ArrayList<>();
			list_km_pointage.add(new Float(900));
			list_km_pointage.add(new Float(1800));
			//Pointage du tir
			list_km_pointage.add(new Float(2520));
			list_km_pointage.add(new Float(3400));
			list_km_pointage.add(new Float(4200));
			//Pointage du tir
			list_km_pointage.add(new Float(5020));
			list_km_pointage.add(new Float(5800));
			list_km_pointage.add(new Float(6500));
			list_km_pointage.add(new Float(7100));
			//Arrivée de la course
			list_km_pointage.add(new Float(7500));
			
			//2 tirs
			list_km_tir = new ArrayList<>();
			list_km_tir.add(new Float(2500));
			list_km_tir.add(new Float(5000));
			
			//Type des tirs
			list_type_tir_cd = new ArrayList<>();
			list_type_tir_cd.add("couche");
			list_type_tir_cd.add("debout");
			
			course = new ContreLaMontre(participant, list_km_tir, list_km_pointage,list_type_tir_cd, "tour");
		
			//Démarrer la course
			course.demarrer();
			break;
			
		case 7: //poursuite homme
			// requete sur les biathletes
			participant = Main.database.requete("");

			//Pointages intermediaires
			list_km_pointage = new ArrayList<>();
			list_km_pointage.add(new Float(900));
			list_km_pointage.add(new Float(1700));
			//Pointage du tir
			list_km_pointage.add(new Float(2520));
			list_km_pointage.add(new Float(3400));
			list_km_pointage.add(new Float(4600));
			//Pointage du tir
			list_km_pointage.add(new Float(5020));
			list_km_pointage.add(new Float(5900));
			list_km_pointage.add(new Float(6700));
			//Pointage du tir
			list_km_pointage.add(new Float(7520));
			list_km_pointage.add(new Float(8400));
			list_km_pointage.add(new Float(9200));
			//Pointage du tir
			list_km_pointage.add(new Float(10020));
			list_km_pointage.add(new Float(10900));
			list_km_pointage.add(new Float(11700));
			//Arrivée de la course
			list_km_pointage.add(new Float(12500));
			
			//4 tirs
			list_km_tir = new ArrayList<>();
			list_km_tir.add(new Float(2500));
			list_km_tir.add(new Float(5000));
			list_km_tir.add(new Float(7500));
			list_km_tir.add(new Float(10000));
			
			//Type des tirs
			list_type_tir_cd = new ArrayList<>();
			list_type_tir_cd.add("couche");
			list_type_tir_cd.add("couche");
			list_type_tir_cd.add("debout");
			list_type_tir_cd.add("debout");
			
			//ON lance la course
			course = new Poursuite(participant, list_km_tir, list_km_pointage,list_type_tir_cd);
			
			//Démarrer la course
			course.demarrer();

			break;
			
		case 8: //poursuite femme
			//requete sur les biathletes
			participant = Main.database.requete("");
			
			//Pointages intermediaires
			list_km_pointage = new ArrayList<>();
			list_km_pointage.add(new Float(800));
			list_km_pointage.add(new Float(1600));
			//Pointage du tir
			list_km_pointage.add(new Float(2020));
			list_km_pointage.add(new Float(2800));
			list_km_pointage.add(new Float(3600));
			//Pointage du tir
			list_km_pointage.add(new Float(4020));
			list_km_pointage.add(new Float(4800));
			list_km_pointage.add(new Float(5600));
			//Pointage du tir
			list_km_pointage.add(new Float(6020));
			list_km_pointage.add(new Float(6800));
			list_km_pointage.add(new Float(7600));
			//Pointage du tir
			list_km_pointage.add(new Float(8020));
			list_km_pointage.add(new Float(8800));
			list_km_pointage.add(new Float(9600));
			//Arrivée de la course
			list_km_pointage.add(new Float(10000));
			
			//4 tirs
			list_km_tir = new ArrayList<>();
			list_km_tir.add(new Float(2000));
			list_km_tir.add(new Float(4000));
			list_km_tir.add(new Float(6000));
			list_km_tir.add(new Float(8000));
			
			//Type des tirs
			list_type_tir_cd = new ArrayList<>();
			list_type_tir_cd.add("couche");
			list_type_tir_cd.add("couche");
			list_type_tir_cd.add("debout");
			list_type_tir_cd.add("debout");
			
			//ON créé la course
			course = new Poursuite(participant, list_km_tir, list_km_pointage,list_type_tir_cd);
			
			//Démarrer la course
			course.demarrer();
			break;
			
		case 9: //mass start homme
			
			//requete sur les biathletes
			participant = Main.database.requete("SELECT * FROM biathletes where biathletes.sexe_biathlete = 'H' order by biathletes.REN DESC LIMIT 30");
			//Pointages intermediaires
			list_km_pointage = new ArrayList<>();
			list_km_pointage.add(new Float(1000));
			list_km_pointage.add(new Float(2000));
			//Pointage du tir
			list_km_pointage.add(new Float(3020));
			list_km_pointage.add(new Float(4000));
			list_km_pointage.add(new Float(5000));
			//Pointage du tir
			list_km_pointage.add(new Float(6020));
			list_km_pointage.add(new Float(7000));
			list_km_pointage.add(new Float(8000));
			//Pointage du tir
			list_km_pointage.add(new Float(9020));
			list_km_pointage.add(new Float(10000));
			list_km_pointage.add(new Float(11000));
			//Pointage du tir
			list_km_pointage.add(new Float(12020));
			list_km_pointage.add(new Float(13000));
			list_km_pointage.add(new Float(14000));
			//Arrivée de la course
			list_km_pointage.add(new Float(15000));
			
			//4 tirs
			list_km_tir = new ArrayList<>();
			list_km_tir.add(new Float(3000));
			list_km_tir.add(new Float(6000));
			list_km_tir.add(new Float(9000));
			list_km_tir.add(new Float(12000));
			
			//Type des tirs
			list_type_tir_cd = new ArrayList<>();
			list_type_tir_cd.add("couche");
			list_type_tir_cd.add("couche");
			list_type_tir_cd.add("debout");
			list_type_tir_cd.add("debout");
			
			//On créé la course
			course = new MassStart(participant, list_km_tir, list_km_pointage,list_type_tir_cd);
			
			//Démarrer la course
			course.demarrer();
			break;
		
		case 10:  //mass start femme
			
			//requete sur les biathletes
			participant = Main.database.requete("SELECT * FROM biathletes where biathletes.sexe_biathlete = 'F' order by biathletes.REN DESC LIMIT 30");
			
			//Pointages intermediaires
			list_km_pointage = new ArrayList<>();
			list_km_pointage.add(new Float(900));
			list_km_pointage.add(new Float(1700));
			//Pointage du tir
			list_km_pointage.add(new Float(2520));
			list_km_pointage.add(new Float(3400));
			list_km_pointage.add(new Float(4600));
			//Pointage du tir
			list_km_pointage.add(new Float(5020));
			list_km_pointage.add(new Float(5900));
			list_km_pointage.add(new Float(6700));
			//Pointage du tir
			list_km_pointage.add(new Float(7520));
			list_km_pointage.add(new Float(8400));
			list_km_pointage.add(new Float(9200));
			//Pointage du tir
			list_km_pointage.add(new Float(10020));
			list_km_pointage.add(new Float(10900));
			list_km_pointage.add(new Float(11700));
			//Arrivée de la course
			list_km_pointage.add(new Float(12500));
			
			//4 tirs
			list_km_tir = new ArrayList<>();
			list_km_tir.add(new Float(2500));
			list_km_tir.add(new Float(5000));
			list_km_tir.add(new Float(7500));
			list_km_tir.add(new Float(10000));
			
			//Type des tirs
			list_type_tir_cd = new ArrayList<>();
			list_type_tir_cd.add("couche");
			list_type_tir_cd.add("couche");
			list_type_tir_cd.add("debout");
			list_type_tir_cd.add("debout");
			
			course = new MassStart(participant, list_km_tir, list_km_pointage,list_type_tir_cd);
			//Démarrer la course
			course.demarrer();
			break;
		
		case 11: //individuel homme
			
			// Tout les biathletes masculin
			participant = Main.database.requete("SELECT biathletes.* FROM biathletes where sexe_biathlete = 'H' ");
			
			//Pointages intermediaires
			list_km_pointage = new ArrayList<>();
			list_km_pointage.add(new Float(1600));
			list_km_pointage.add(new Float(3200));
			//Pointage du tir
			list_km_pointage.add(new Float(4020));
			list_km_pointage.add(new Float(5600));
			list_km_pointage.add(new Float(7200));
			//Pointage du tir
			list_km_pointage.add(new Float(8020));
			list_km_pointage.add(new Float(9600));
			list_km_pointage.add(new Float(11200));
			//Pointage du tir
			list_km_pointage.add(new Float(12020));
			list_km_pointage.add(new Float(13600));
			list_km_pointage.add(new Float(15200));
			//Pointage du tir
			list_km_pointage.add(new Float(16020));
			list_km_pointage.add(new Float(17600));
			list_km_pointage.add(new Float(19200));
			//Arrivée de la course
			list_km_pointage.add(new Float(20000));
			
			//4 tirs
			list_km_tir = new ArrayList<>();
			list_km_tir.add(new Float(4000));
			list_km_tir.add(new Float(8000));
			list_km_tir.add(new Float(12000));
			list_km_tir.add(new Float(16000));
			
			//Type des tirs
			list_type_tir_cd = new ArrayList<>();
			list_type_tir_cd.add("couche");
			list_type_tir_cd.add("debout");
			list_type_tir_cd.add("couche");
			list_type_tir_cd.add("debout");
			
			//Création de mla course
			course = new ContreLaMontre(participant, list_km_tir, list_km_pointage,list_type_tir_cd, "minute");
			//Démarrer la course
			course.demarrer();
			break;
			
		case 12: //individuel femme
			
			// Tout les biathletes féminin
			participant = Main.database.requete("SELECT biathletes.* FROM biathletes where sexe_biathlete = 'F' ");
			
			//Pointages intermediaires
			list_km_pointage = new ArrayList<>();
			list_km_pointage.add(new Float(1000));
			list_km_pointage.add(new Float(2000));
			//Pointage du tir
			list_km_pointage.add(new Float(3020));
			list_km_pointage.add(new Float(4000));
			list_km_pointage.add(new Float(5000));
			//Pointage du tir
			list_km_pointage.add(new Float(6020));
			list_km_pointage.add(new Float(7000));
			list_km_pointage.add(new Float(8000));
			//Pointage du tir
			list_km_pointage.add(new Float(9020));
			list_km_pointage.add(new Float(10000));
			list_km_pointage.add(new Float(11000));
			//Pointage du tir
			list_km_pointage.add(new Float(12020));
			list_km_pointage.add(new Float(13000));
			list_km_pointage.add(new Float(14000));
			//Arrivée de la course
			list_km_pointage.add(new Float(15000));
			
			//4 tirs
			list_km_tir = new ArrayList<>();
			list_km_tir.add(new Float(3000));
			list_km_tir.add(new Float(6000));
			list_km_tir.add(new Float(9000));
			list_km_tir.add(new Float(12000));
			
			//Type des tirs
			list_type_tir_cd = new ArrayList<>();
			list_type_tir_cd.add("couche");
			list_type_tir_cd.add("debout");
			list_type_tir_cd.add("couche");
			list_type_tir_cd.add("debout");
			
			course = new ContreLaMontre(participant, list_km_tir, list_km_pointage,list_type_tir_cd, "minute");
			
			//Démarrer la course
			course.demarrer();
			break;
		}
	}
	
	
	
	/*
	//Carriere : 1 homme + 1 femme
	public void mesBiathleteCarriere(int id_biathlete_h, int id_biathlete_f) {
		this.id_biathlete_h = id_biathlete_h;
		this.id_biathlete_f = id_biathlete_f;
	}
	*/
	
	
	
	/*  GET/SET  */
	public String getId_equipe() {
		return id_equipe;
	}

	public void setId_equipe(String id_equipe) {
		this.id_equipe = id_equipe;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	public void chargerCarriere() {
		
	}
	
	public static Course getCourse() {
		return course;
	}

	public static void setCourse(Course course) {
		Joueur.course = course;
	}

	public static CibleJoueur getCible_joueur() {
		return cible_joueur;
	}

	public static void setCible_joueur(CibleJoueur cible_joueur) {
		Joueur.cible_joueur = cible_joueur;
	}

	public static int getId_biathlete() {
		return id_biathlete;
	}

	public static void setId_biathlete(int id_biathlete) {
		Joueur.id_biathlete = id_biathlete;
	}

	public ImageIcon getIco_bg() {
		return ico_bg;
	}

	public void setIco_bg(ImageIcon ico_bg) {
		this.ico_bg = ico_bg;
	}

	public Image getImg_bg() {
		return img_bg;
	}

	public void setImg_bg(Image img_bg) {
		this.img_bg = img_bg;
	}

	public ResultSet getCompet_courrante() {
		return compet_courrante;
	}

	public void setCompet_courrante(ResultSet compet_courrante) {
		this.compet_courrante = compet_courrante;
	}

	public void creerCourse() {
	}
	
	public boolean isFaire_poursuite() {
		return faire_poursuite;
	}

	public void setFaire_poursuite(boolean faire_poursuite) {
		this.faire_poursuite = faire_poursuite;
	}

	public ArrayList<Integer> getList_id_biathlete() {
		return list_id_biathlete;
	}

	public void setList_id_biathlete(ArrayList<Integer> list_id_biathlete) {
		this.list_id_biathlete = list_id_biathlete;
	}

	public String getType_jeu() {
		return type_jeu;
	}
	
	public void setType_jeu(String type_jeu) {
		this.type_jeu = type_jeu;
	}

	public int getType_course() {
		return type_course;
	}

	public void setType_course(int type_course) {
		this.type_course = type_course;
	}
	
}
