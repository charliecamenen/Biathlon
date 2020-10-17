package com.biathlon.action;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.biathlon.courses.ContreLaMontre;
import com.biathlon.courses.Course;
import com.biathlon.courses.MassStart;
import com.biathlon.courses.Poursuite;
import com.biathlon.jeu.Main;

public class Saison {
	
	public static Course course;

	private ArrayList<Course> list_course;

	public void prochaineCourse() {
		int id_course = 11;
		
		ResultSet participant;
		ArrayList<Float> list_km_tir;
		
		switch(id_course) {
		case 1: break;//relais mixte
		case 2: break;//relais mixte simple
		case 3: break;//relais homme
		case 4: break;//relais femme
		
		case 5: //sprint homme
			//On créé les kilometre des tirs
			list_km_tir = new ArrayList<>();
			list_km_tir.add(new Float(3400));
			list_km_tir.add(new Float(6800));
			// requete sur les biathletes
			participant = Main.database.requete("SELECT biathletes.* FROM biathletes join membres on (membres.id_biathlete = biathletes.id_biathlete) JOIN participants on (membres.id_participant = participants.id_participant) WHERE `id_course` = 5 ");
			course = new ContreLaMontre(participant, list_km_tir, list_km_tir, "tour");
			break;
			
		case 6: //sprint femme
			//On créé les kilometre des tirs
			list_km_tir = new ArrayList<>();
			list_km_tir.add(new Float(2500));
			list_km_tir.add(new Float(5000));
			// requete sur les biathletes
			participant = Main.database.requete("SELECT biathletes.* FROM biathletes join membres on (membres.id_biathlete = biathletes.id_biathlete) JOIN participants on (membres.id_participant = participants.id_participant) WHERE `id_course` = 5 ");
			course = new ContreLaMontre(participant, list_km_tir, list_km_tir, "tour");
			break;
			
		case 7: //poursuite homme
			// requete sur les biathletes
			participant = Main.database.requete("SELECT biathletes.*, participants.temps FROM biathletes join membres on (membres.id_biathlete = biathletes.id_biathlete) JOIN participants on (membres.id_participant = participants.id_participant) WHERE `id_course` = 5 ");
			
			list_km_tir = new ArrayList<>();
			list_km_tir.add(new Float(500));
			list_km_tir.add(new Float(5000));
			list_km_tir.add(new Float(7500));
			list_km_tir.add(new Float(10000));
			course = new Poursuite(participant, list_km_tir, list_km_tir);

			break;
			
		case 8: //poursuite femme
			//requete sur les biathletes
			participant = Main.database.requete("SELECT biathletes.* , participants.temps FROM biathletes join membres on (membres.id_biathlete = biathletes.id_biathlete) JOIN participants on (membres.id_participant = participants.id_participant) WHERE `id_course` = 5 ");
			
			list_km_tir = new ArrayList<>();
			list_km_tir.add(new Float(500));
			list_km_tir.add(new Float(5000));
			list_km_tir.add(new Float(7500));
			list_km_tir.add(new Float(10000));
			course = new Poursuite(participant, list_km_tir, list_km_tir);
			break;
			
		case 9: 
			//requete sur les biathletes
			participant = Main.database.requete("SELECT biathletes.* , participants.temps FROM biathletes join membres on (membres.id_biathlete = biathletes.id_biathlete) JOIN participants on (membres.id_participant = participants.id_participant) WHERE `id_course` = 5 ");
			
			list_km_tir = new ArrayList<>();
			list_km_tir.add(new Float(500));
			list_km_tir.add(new Float(5000));
			list_km_tir.add(new Float(7500));
			list_km_tir.add(new Float(10000));
			course = new MassStart(participant, list_km_tir, list_km_tir);
			
			break;//mass start homme
		
		case 10: 
			//requete sur les biathletes
			participant = Main.database.requete("SELECT biathletes.* , participants.temps FROM biathletes join membres on (membres.id_biathlete = biathletes.id_biathlete) JOIN participants on (membres.id_participant = participants.id_participant) WHERE `id_course` = 5 ");
			
			list_km_tir = new ArrayList<>();
			list_km_tir.add(new Float(500));
			list_km_tir.add(new Float(5000));
			list_km_tir.add(new Float(7500));
			list_km_tir.add(new Float(10000));
			course = new MassStart(participant, list_km_tir, list_km_tir);
			
			break;//mass start femme
		
		case 11: //individuel homme
			// requete sur les biathletes
			participant = Main.database.requete("SELECT biathletes.* FROM biathletes join membres on (membres.id_biathlete = biathletes.id_biathlete) JOIN participants on (membres.id_participant = participants.id_participant) WHERE `id_course` = 5 ");
			
			list_km_tir = new ArrayList<>();
			list_km_tir.add(new Float(500));
			list_km_tir.add(new Float(5000));
			list_km_tir.add(new Float(7500));
			list_km_tir.add(new Float(10000));
			course = new ContreLaMontre(participant, list_km_tir, list_km_tir, "minute");
			break;
			
		case 12: //individuel femme
			// requete sur les biathletes
			participant = Main.database.requete("SELECT biathletes.* FROM biathletes join membres on (membres.id_biathlete = biathletes.id_biathlete) JOIN participants on (membres.id_participant = participants.id_participant) WHERE `id_course` = 5 ");
			
			list_km_tir = new ArrayList<>();
			list_km_tir.add(new Float(500));
			list_km_tir.add(new Float(5000));
			list_km_tir.add(new Float(7500));
			list_km_tir.add(new Float(10000));
			course = new ContreLaMontre(participant, list_km_tir, list_km_tir, "minute");
			break;
		}




	}
}
