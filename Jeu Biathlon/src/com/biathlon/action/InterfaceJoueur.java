package com.biathlon.action;

import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.biathlon.jeu.Main;

public class InterfaceJoueur {

	//reference de la position 
	private final int ref_pos_x = 400;
	private final int ref_pos_y = 270;

	//Background de l'interface
	private ImageIcon ico_bg;
	private Image img_bg;
	private final int y_bg = ref_pos_y;
	private final int x_bg = ref_pos_x;

	/***** PARTIE DU MILIEU *****/
	//Symbole energie
	private ImageIcon ico_energie_symbole;
	private Image img_energie_symbole;
	private final int y_energie_symbole = ref_pos_y + 192;
	private final int x_energie_symbole = ref_pos_x + 440;
	//Symbole acceleration
	private ImageIcon ico_acc_symbole;
	private Image img_acc_symbole;
	private final int y_acc_symbole = ref_pos_y +192;
	private final int x_acc_symbole = ref_pos_x+470;

	//Jauge Acceleration
	private ImageIcon ico_jauge_acceleration;
	private Image img_jauge_acceleration;
	private final int y_jauge_acceleration = ref_pos_y +  50;
	private final int x_jauge_acceleration = ref_pos_x + 473;
	//Jauge Acceleration vide
	private ImageIcon ico_jauge_acceleration_vide;
	private Image img_jauge_acceleration_vide;
	private final int y_jauge_acceleration_vide = ref_pos_y +  50;

	//Jauge energie vide
	private ImageIcon ico_jauge_energie_vide;
	private Image img_jauge_energie_vide;
	//Jauge energie max
	private ImageIcon ico_jauge_energie_max;
	private Image img_jauge_energie_max;
	//Jauge energie
	private ImageIcon ico_jauge_energie;
	private Image img_jauge_energie;
	private final int y_jauge_energie = ref_pos_y +  50;
	private int x_jauge_energie = ref_pos_x + 440;

	//Curseur de la jauge d'effort
	private ImageIcon ico_curseur_jauge;
	private Image img_curseur_jauge;
	private final int y_curseur_jauge = ref_pos_y +  248;
	private int x_curseur_jauge = ref_pos_x + 249;

	//Image des boutons
	private ImageIcon ico_bouton;
	private Image img_bouton;
	//Image des boutons select
	private ImageIcon ico_bouton_select;
	private Image img_bouton_select;
	private final int y_bouton = ref_pos_y + 214;
	private int x_bouton = ref_pos_x + 250;
	private int x_bouton_decalage = 80;
	private int mode_action = 0;

	//Position dans le clasement
	private int x_classement = ref_pos_x + 14;
	private int y_classement = ref_pos_y + 33;
	//pulsation
	private int x_pulsation = ref_pos_x + 260;
	private int y_pulsation = ref_pos_y + 110;
	//forme
	private int x_forme;
	private int y_forme;
	//Drapeau moi
	private ImageIcon ico_drapeau_moi;
	private Image img_drapeau_moi;
	private final int y_drapeau_moi = ref_pos_y + 175;
	private final int x_drapeau_moi = ref_pos_x + 254;
	//Nom de mon biathlete
	private int x_nom_mon_biathlete = ref_pos_x + 285;
	private int y_nom_mon_biathlete = ref_pos_y + 192;
	//Effort
	private int x_effort = ref_pos_x + 457;
	private int y_effort = ref_pos_y + 258;
	//longueur de la jauge d'effort
	private int longueur_jauge = 200;


	/***** PARTIE DE DROITE *****/
	//Chaine a afficher
	//chrono
	private int x_chrono = ref_pos_x + 285;
	private int y_chrono = ref_pos_y + 24;
	//vent
	private int x_vent = ref_pos_x + 550;
	private int y_vent = ref_pos_y + 245;
	//distance de tir
	private int x_distance_tir = ref_pos_x + 620;
	private int y_distance_tir = ref_pos_y + 135;
	//distance de l'arrivée
	private int x_distance_arrive = ref_pos_x + 675;
	private int y_distance_arrive = ref_pos_y + 245;
	//pente
	private int x_pente = ref_pos_x + 710;
	private int y_pente = ref_pos_y + 135;
	//vitesse
	private int x_vitesse = ref_pos_x + 505;
	private int y_vitesse = ref_pos_y + 135;
	//Vent direction
	private ImageIcon ico_vent_direction;
	private Image img_vent_direction;
	private int x_vent_direction = ref_pos_x + 515;
	private int y_vent_direction = ref_pos_y + 220;
	
	/***** PARTIE DE GAUCHE *****/
	//Drapeau physique
	private ImageIcon ico_drapeau_physique;
	private Image img_drapeau_physique;
	private final int y_drapeau_physique= ref_pos_y + 10;
	private final int x_drapeau_physique = ref_pos_x + 45;
	//Nom du biathlete physique
	private int x_nom_biathlete = ref_pos_x + 80;
	private int y_nom_biathlete = ref_pos_y + 28;
	//Image des resultat aux tirs
	private ImageIcon ico_res_tir;
	private Image img_res_tir;
	private final int y_res_tir = ref_pos_y + 190;
	private int x_res_tir = ref_pos_x + 20;
	private int y_res_tir_decalage = 43;
	private int x_res_tir_decalage = 56;
	//Texte resultat aux tirs
	private int x_text_faute = ref_pos_x +34 ;
	private int y_text_faute = ref_pos_y +214;
	private int x_text_faute_decalage = 56;
	private int y_text_faute_decalage = 43;
	
	//Note x
	private int x_note = ref_pos_x +65;
	private int x_note_lib = ref_pos_x +18;
	//Note ski
	private int y_note_ski = ref_pos_y+ 73;
	//Note debout
	private int y_note_deb = ref_pos_y+ 93;
	//Note couché
	private int y_note_cou = ref_pos_y+ 113;
	//Note endurance
	private int y_note_end = ref_pos_y+ 133;
	//Note acceleration
	private int y_note_acc = ref_pos_y+ 153;

	//Classement general
	//Position
	private int y_position_classement = ref_pos_y + 125;
	private int x_position_classement = ref_pos_x + 126;
	//points
	private int y_point_classement = ref_pos_y + 150;
	private int x_point_classement = ref_pos_x + 126;
	//position prédite
	private int y_position_predict = ref_pos_y + 125;
	private int x_position_predict = ref_pos_x + 190;
	//points prédit
	private int y_point_predict = ref_pos_y + 150;
	private int x_point_predict = ref_pos_x + 190;
	
	public InterfaceJoueur() {
		//Background
		ico_bg = new ImageIcon(getClass().getResource("/images/interfaceBg.png"));
		this.img_bg = this.ico_bg.getImage();
		//acceleration symbole
		ico_acc_symbole = new ImageIcon(getClass().getResource("/images/accelerationSymbole.png"));
		this.img_acc_symbole = this.ico_acc_symbole.getImage();
		//energie symbole
		ico_energie_symbole = new ImageIcon(getClass().getResource("/images/energieSymbole.png"));
		this.img_energie_symbole = this.ico_energie_symbole.getImage();

		//Acceleration jauge
		ico_jauge_acceleration = new ImageIcon(getClass().getResource("/images/jaugeAcc.png"));
		this.img_jauge_acceleration = this.ico_jauge_acceleration.getImage();
		ico_jauge_acceleration_vide = new ImageIcon(getClass().getResource("/images/jaugeAccVide.png"));
		this.img_jauge_acceleration_vide = this.ico_jauge_acceleration_vide.getImage();

		//Energie Jauge
		ico_jauge_energie = new ImageIcon(getClass().getResource("/images/jaugeEnergie.png"));
		this.img_jauge_energie = this.ico_jauge_energie.getImage();
		ico_jauge_energie_max = new ImageIcon(getClass().getResource("/images/jaugeEnergieMax.png"));
		this.img_jauge_energie_max = this.ico_jauge_energie_max.getImage();
		ico_jauge_energie_vide = new ImageIcon(getClass().getResource("/images/jaugeEnergieVide.png"));
		this.img_jauge_energie_vide = this.ico_jauge_energie_vide.getImage();

		//Curseur jauge
		ico_curseur_jauge = new ImageIcon(getClass().getResource("/images/jaugeCurseur.png"));
		this.img_curseur_jauge = this.ico_curseur_jauge.getImage();

		//Bouton
		ico_bouton = new ImageIcon(getClass().getResource("/images/boutonInterface.png"));
		this.img_bouton = this.ico_bouton.getImage();
		ico_bouton_select = new ImageIcon(getClass().getResource("/images/boutonInterfaceSelect.png"));
		this.img_bouton_select = this.ico_bouton_select.getImage();

		//Erreur tir interface
		ico_res_tir = new ImageIcon(getClass().getResource("/images/erreurInterfaceBg.png"));
		this.img_res_tir = this.ico_res_tir.getImage();

		//fleche direction du vent
		ico_vent_direction = new ImageIcon(getClass().getResource("/images/orientationVent.png"));
		this.img_vent_direction = this.ico_vent_direction.getImage();

	}


	public void setDrapeauMoi(int id_biathlete) {
		//Création du drapeau
		ResultSet resultset = Main.database.requete(""
				+ "SELECT pays.drapeau_icone_file " 
				+ "FROM biathletes join equipes on (biathletes.id_equipe = equipes.id_equipe) " 
				+ "join pays on (equipes.id_pays = pays.id_pays) " 
				+ "WHERE id_biathlete = " + id_biathlete);

		try {
			while(resultset.next()) {
				//ImageIcon icon = new ImageIcon(new ImageIcon("tonImage.jpg").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)); 
				this.ico_drapeau_moi = new ImageIcon(getClass().getResource(resultset.getString("drapeau_icone_file")));
				this.img_drapeau_moi = this.ico_drapeau_moi.getImage();
			}
		} catch (SQLException e) {e.printStackTrace();}
	}

	public void setDrapeauPhysique(int id_biathlete) {
		//Création du drapeau
		ResultSet resultset = Main.database.requete(""
				+ "SELECT pays.drapeau_icone_file " 
				+ "FROM biathletes join equipes on (biathletes.id_equipe = equipes.id_equipe) " 
				+ "join pays on (equipes.id_pays = pays.id_pays) " 
				+ "WHERE id_biathlete = " + id_biathlete);

		try {
			while(resultset.next()) {
				//ImageIcon icon = new ImageIcon(new ImageIcon("tonImage.jpg").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)); 
				this.ico_drapeau_physique = new ImageIcon(getClass().getResource(resultset.getString("drapeau_icone_file")));
				this.img_drapeau_physique = this.ico_drapeau_physique.getImage();
			}
		} catch (SQLException e) {e.printStackTrace();}
	}


	public int tailleJaugeEnergieMax() {
		return 1;
	}
	public int tailleJaugeEnergie() {
		return 1;
	}
	public int tailleJaugeAcceleration() {
		return 1;
	}

	//Affiche le format du bouton correspondant
	public Image boutonSelect(int indice) {
		Image img;
		//Si c'est le mode selectionné
		if(this.mode_action == indice) {
			img = img_bouton_select;
		}else {
			img = img_bouton;
		}		
		return img;
	}


	public ImageIcon getIco_bouton_select() {
		return ico_bouton_select;
	}



	public void setIco_bouton_select(ImageIcon ico_bouton_select) {
		this.ico_bouton_select = ico_bouton_select;
	}



	public Image getImg_bouton_select() {
		return img_bouton_select;
	}



	public void setImg_bouton_select(Image img_bouton_select) {
		this.img_bouton_select = img_bouton_select;
	}



	public int getX_bouton_decalage() {
		return x_bouton_decalage;
	}



	public void setX_bouton_decalage(int x_bouton_decalage) {
		this.x_bouton_decalage = x_bouton_decalage;
	}



	public int getMode_action() {
		return mode_action;
	}




	public int getX_text_faute() {
		return x_text_faute;
	}


	public void setX_text_faute(int x_text_faute) {
		this.x_text_faute = x_text_faute;
	}


	public int getY_position_classement() {
		return y_position_classement;
	}


	public int getLongueur_jauge() {
		return longueur_jauge;
	}


	public void setLongueur_jauge(int longueur_jauge) {
		this.longueur_jauge = longueur_jauge;
	}


	public void setY_position_classement(int y_position_classement) {
		this.y_position_classement = y_position_classement;
	}


	public int getX_position_classement() {
		return x_position_classement;
	}


	public void setX_position_classement(int x_position_classement) {
		this.x_position_classement = x_position_classement;
	}


	public int getY_point_classement() {
		return y_point_classement;
	}


	public void setY_point_classement(int y_points_classement) {
		this.y_point_classement = y_points_classement;
	}


	public int getX_point_classement() {
		return x_point_classement;
	}


	public void setX_point_classement(int x_points_classement) {
		this.x_point_classement = x_points_classement;
	}


	public int getY_position_predict() {
		return y_position_predict;
	}


	public void setY_position_predict(int y_position_predict) {
		this.y_position_predict = y_position_predict;
	}


	public int getX_position_predict() {
		return x_position_predict;
	}


	public void setX_position_predict(int x_position_predict) {
		this.x_position_predict = x_position_predict;
	}


	public int getY_point_predict() {
		return y_point_predict;
	}


	public void setY_point_predict(int y_point_predict) {
		this.y_point_predict = y_point_predict;
	}


	public int getX_point_predict() {
		return x_point_predict;
	}


	public void setX_point_predict(int x_point_predict) {
		this.x_point_predict = x_point_predict;
	}


	public int getY_text_faute() {
		return y_text_faute;
	}


	public void setY_text_faute(int y_text_faute) {
		this.y_text_faute = y_text_faute;
	}


	public int getX_text_faute_decalage() {
		return x_text_faute_decalage;
	}


	public void setX_text_faute_decalage(int x_text_faute_decalage) {
		this.x_text_faute_decalage = x_text_faute_decalage;
	}


	public int getY_text_faute_decalage() {
		return y_text_faute_decalage;
	}


	public void setY_text_faute_decalage(int y_text_faute_decalage) {
		this.y_text_faute_decalage = y_text_faute_decalage;
	}


	public int getX_note() {
		return x_note;
	}


	public void setX_note(int x_note) {
		this.x_note = x_note;
	}


	public int getY_note_ski() {
		return y_note_ski;
	}


	public void setY_note_ski(int y_note_ski) {
		this.y_note_ski = y_note_ski;
	}



	public int getY_note_deb() {
		return y_note_deb;
	}


	public void setY_note_deb(int y_note_deb) {
		this.y_note_deb = y_note_deb;
	}



	public int getY_note_cou() {
		return y_note_cou;
	}


	public void setY_note_cou(int y_note_cou) {
		this.y_note_cou = y_note_cou;
	}


	public int getY_note_end() {
		return y_note_end;
	}


	public void setY_note_end(int y_note_end) {
		this.y_note_end = y_note_end;
	}




	public int getY_note_acc() {
		return y_note_acc;
	}


	public void setY_note_acc(int y_note_acc) {
		this.y_note_acc = y_note_acc;
	}


	public ImageIcon getIco_drapeau_moi() {
		return ico_drapeau_moi;
	}



	public void setIco_drapeau_moi(ImageIcon ico_drapeau_moi) {
		this.ico_drapeau_moi = ico_drapeau_moi;
	}



	public ImageIcon getIco_res_tir() {
		return ico_res_tir;
	}


	public void setIco_res_tir(ImageIcon ico_res_tir) {
		this.ico_res_tir = ico_res_tir;
	}


	public Image getImg_res_tir() {
		return img_res_tir;
	}


	public void setImg_res_tir(Image img_res_tir) {
		this.img_res_tir = img_res_tir;
	}


	public int getX_res_tir() {
		return x_res_tir;
	}


	public void setX_res_tir(int x_res_tir) {
		this.x_res_tir = x_res_tir;
	}


	public int getY_res_tir_decalage() {
		return y_res_tir_decalage;
	}


	public void setY_res_tir_decalage(int y_res_tir_decalage) {
		this.y_res_tir_decalage = y_res_tir_decalage;
	}


	public int getX_res_tir_decalage() {
		return x_res_tir_decalage;
	}


	public void setX_res_tir_decalage(int x_res_tir_decalage) {
		this.x_res_tir_decalage = x_res_tir_decalage;
	}


	public int getY_res_tir() {
		return y_res_tir;
	}


	public Image getImg_drapeau_moi() {
		return img_drapeau_moi;
	}



	public void setImg_drapeau_moi(Image img_drapeau_moi) {


		this.img_drapeau_moi = img_drapeau_moi;
	}



	public ImageIcon getIco_drapeau_physique() {
		return ico_drapeau_physique;
	}



	public void setIco_drapeau_physique(ImageIcon ico_drapeau_physique) {
		this.ico_drapeau_physique = ico_drapeau_physique;
	}



	public Image getImg_drapeau_physique() {
		return img_drapeau_physique;
	}



	public void setImg_drapeau_physique(Image img_drapeau_physique) {
		this.img_drapeau_physique = img_drapeau_physique;
	}



	public int getY_drapeau_moi() {
		return y_drapeau_moi;
	}



	public int getX_drapeau_moi() {
		return x_drapeau_moi;
	}



	public int getY_drapeau_physique() {
		return y_drapeau_physique;
	}



	public int getX_drapeau_physique() {
		return x_drapeau_physique;
	}



	public void setMode_action(int mode_action) {
		this.mode_action = mode_action;
	}



	public void setX_jauge_energie(int x_jauge_energie) {
		this.x_jauge_energie = x_jauge_energie;
	}





	public int getX_effort() {
		return x_effort;
	}



	public void setX_effort(int x_effort) {
		this.x_effort = x_effort;
	}



	public int getY_effort() {
		return y_effort;
	}



	public void setY_effort(int y_effort) {
		this.y_effort = y_effort;
	}



	public void setX_curseur_jauge(int x_curseur_jauge) {
		this.x_curseur_jauge = x_curseur_jauge;
	}



	public int getX_note_lib() {
		return x_note_lib;
	}


	public void setX_note_lib(int x_note_lib) {
		this.x_note_lib = x_note_lib;
	}


	public int getX_classement() {
		return x_classement;
	}



	public void setX_classement(int x_classement) {
		this.x_classement = x_classement;
	}



	public int getY_classement() {
		return y_classement;
	}



	public void setY_classement(int y_classement) {
		this.y_classement = y_classement;
	}



	public int getX_chrono() {
		return x_chrono;
	}



	public void setX_chrono(int x_chrono) {
		this.x_chrono = x_chrono;
	}



	public int getY_chrono() {
		return y_chrono;
	}



	public void setY_chrono(int y_chrono) {
		this.y_chrono = y_chrono;
	}



	public int getX_vent() {
		return x_vent;
	}



	public void setX_vent(int x_vent) {
		this.x_vent = x_vent;
	}



	public int getY_vent() {
		return y_vent;
	}



	public void setY_vent(int y_vent) {
		this.y_vent = y_vent;
	}



	public int getX_distance_tir() {
		return x_distance_tir;
	}



	public void setX_distance_tir(int x_distance_tir) {
		this.x_distance_tir = x_distance_tir;
	}



	public int getY_distance_tir() {
		return y_distance_tir;
	}



	public void setY_distance_tir(int y_distance_tir) {
		this.y_distance_tir = y_distance_tir;
	}



	public int getX_distance_arrive() {
		return x_distance_arrive;
	}



	public void setX_distance_arrive(int x_distance_arrive) {
		this.x_distance_arrive = x_distance_arrive;
	}



	public int getY_distance_arrive() {
		return y_distance_arrive;
	}



	public void setY_distance_arrive(int y_distance_arrive) {
		this.y_distance_arrive = y_distance_arrive;
	}



	public int getX_pente() {
		return x_pente;
	}



	public void setX_pente(int x_pente) {
		this.x_pente = x_pente;
	}



	public int getY_pente() {
		return y_pente;
	}



	public void setY_pente(int y_pente) {
		this.y_pente = y_pente;
	}



	public int getX_vitesse() {
		return x_vitesse;
	}



	public void setX_vitesse(int x_vitesse) {
		this.x_vitesse = x_vitesse;
	}



	public int getY_vitesse() {
		return y_vitesse;
	}



	public void setY_vitesse(int y_vitesse) {
		this.y_vitesse = y_vitesse;
	}



	public int getX_pulsation() {
		return x_pulsation;
	}



	public void setX_pulsation(int x_pulsation) {
		this.x_pulsation = x_pulsation;
	}



	public int getY_pulsation() {
		return y_pulsation;
	}



	public void setY_pulsation(int y_pulsation) {
		this.y_pulsation = y_pulsation;
	}



	public int getX_forme() {
		return x_forme;
	}



	public void setX_forme(int x_forme) {
		this.x_forme = x_forme;
	}



	public int getY_forme() {
		return y_forme;
	}



	public void setY_forme(int y_forme) {
		this.y_forme = y_forme;
	}



	public int getX_nom_mon_biathlete() {
		return x_nom_mon_biathlete;
	}



	public void setX_nom_mon_biathlete(int x_nom_mon_biathlete) {
		this.x_nom_mon_biathlete = x_nom_mon_biathlete;
	}



	public int getY_nom_mon_biathlete() {
		return y_nom_mon_biathlete;
	}



	public void setY_nom_mon_biathlete(int y_nom_mon_biathlete) {
		this.y_nom_mon_biathlete = y_nom_mon_biathlete;
	}



	public int getX_nom_biathlete() {
		return x_nom_biathlete;
	}



	public void setX_nom_biathlete(int x_nom_biathlete) {
		this.x_nom_biathlete = x_nom_biathlete;
	}



	public int getY_nom_biathlete() {
		return y_nom_biathlete;
	}



	public ImageIcon getIco_vent_direction() {
		return ico_vent_direction;
	}


	public void setIco_vent_direction(ImageIcon ico_vent_direction) {
		this.ico_vent_direction = ico_vent_direction;
	}


	public Image getImg_vent_direction() {
		return img_vent_direction;
	}


	public void setImg_vent_direction(Image img_vent_direction) {
		this.img_vent_direction = img_vent_direction;
	}


	public int getX_vent_direction() {
		return x_vent_direction;
	}


	public void setX_vent_direction(int x_vent_direction) {
		this.x_vent_direction = x_vent_direction;
	}


	public int getY_vent_direction() {
		return y_vent_direction;
	}


	public void setY_vent_direction(int y_vent_direction) {
		this.y_vent_direction = y_vent_direction;
	}


	public void setY_nom_biathlete(int y_nom_biathlete) {
		this.y_nom_biathlete = y_nom_biathlete;
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
	public ImageIcon getIco_energie_symbole() {
		return ico_energie_symbole;
	}
	public void setIco_energie_symbole(ImageIcon ico_energie_symbole) {
		this.ico_energie_symbole = ico_energie_symbole;
	}
	public Image getImg_energie_symbole() {
		return img_energie_symbole;
	}
	public void setImg_energie_symbole(Image img_energie_symbole) {
		this.img_energie_symbole = img_energie_symbole;
	}
	public ImageIcon getIco_acc_symbole() {
		return ico_acc_symbole;
	}
	public void setIco_acc_symbole(ImageIcon ico_acc_symbole) {
		this.ico_acc_symbole = ico_acc_symbole;
	}
	public Image getImg_acc_symbole() {
		return img_acc_symbole;
	}
	public void setImg_acc_symbole(Image img_acc_symbole) {
		this.img_acc_symbole = img_acc_symbole;
	}
	public ImageIcon getIco_curseur_jauge() {
		return ico_curseur_jauge;
	}
	public void setIco_curseur_jauge(ImageIcon ico_curseur_jauge) {
		this.ico_curseur_jauge = ico_curseur_jauge;
	}
	public Image getImg_curseur_jauge() {
		return img_curseur_jauge;
	}
	public void setImg_curseur_jauge(Image img_curseur_jauge) {
		this.img_curseur_jauge = img_curseur_jauge;
	}
	public ImageIcon getIco_bouton() {
		return ico_bouton;
	}
	public void setIco_bouton(ImageIcon ico_bouton) {
		this.ico_bouton = ico_bouton;
	}
	public Image getImg_bouton() {
		return img_bouton;
	}
	public void setImg_bouton(Image img_bouton) {
		this.img_bouton = img_bouton;
	}
	public int getX_bouton() {
		return x_bouton;
	}
	public void setX_bouton(int x_bouton) {
		this.x_bouton = x_bouton;
	}
	public int getY_bg() {
		return y_bg;
	}
	public int getX_bg() {
		return x_bg;
	}
	public int getY_energie_symbole() {
		return y_energie_symbole;
	}
	public int getX_energie_symbole() {
		return x_energie_symbole;
	}
	public int getY_acc_symbole() {
		return y_acc_symbole;
	}
	public int getX_acc_symbole() {
		return x_acc_symbole;
	}
	public int getY_curseur_jauge() {
		return y_curseur_jauge;
	}
	public int getX_curseur_jauge() {
		return x_curseur_jauge;
	}
	public int getY_bouton() {
		return y_bouton;
	}



	public ImageIcon getIco_jauge_acceleration() {
		return ico_jauge_acceleration;
	}



	public void setIco_jauge_acceleration(ImageIcon ico_jauge_acceleration) {
		this.ico_jauge_acceleration = ico_jauge_acceleration;
	}



	public Image getImg_jauge_acceleration() {
		return img_jauge_acceleration;
	}



	public void setImg_jauge_acceleration(Image img_jauge_acceleration) {
		this.img_jauge_acceleration = img_jauge_acceleration;
	}



	public ImageIcon getIco_jauge_acceleration_vide() {
		return ico_jauge_acceleration_vide;
	}



	public void setIco_jauge_acceleration_vide(ImageIcon ico_jauge_acceleration_vide) {
		this.ico_jauge_acceleration_vide = ico_jauge_acceleration_vide;
	}



	public Image getImg_jauge_acceleration_vide() {
		return img_jauge_acceleration_vide;
	}



	public void setImg_jauge_acceleration_vide(Image img_jauge_acceleration_vide) {
		this.img_jauge_acceleration_vide = img_jauge_acceleration_vide;
	}



	public ImageIcon getIco_jauge_energie() {
		return ico_jauge_energie;
	}



	public void setIco_jauge_energie(ImageIcon ico_jauge_energie) {
		this.ico_jauge_energie = ico_jauge_energie;
	}



	public Image getImg_jauge_energie() {
		return img_jauge_energie;
	}



	public void setImg_jauge_energie(Image img_jauge_energie) {
		this.img_jauge_energie = img_jauge_energie;
	}



	public ImageIcon getIco_jauge_energie_vide() {
		return ico_jauge_energie_vide;
	}



	public void setIco_jauge_energie_vide(ImageIcon ico_jauge_energie_vide) {
		this.ico_jauge_energie_vide = ico_jauge_energie_vide;
	}



	public Image getImg_jauge_energie_vide() {
		return img_jauge_energie_vide;
	}



	public void setImg_jauge_energie_vide(Image img_jauge_energie_vide) {
		this.img_jauge_energie_vide = img_jauge_energie_vide;
	}



	public ImageIcon getIco_jauge_energie_max() {
		return ico_jauge_energie_max;
	}



	public void setIco_jauge_energie_max(ImageIcon ico_jauge_energie_max) {
		this.ico_jauge_energie_max = ico_jauge_energie_max;
	}



	public Image getImg_jauge_energie_max() {
		return img_jauge_energie_max;
	}



	public void setImg_jauge_energie_max(Image img_jauge_energie_max) {
		this.img_jauge_energie_max = img_jauge_energie_max;
	}



	public int getRef_pos_x() {
		return ref_pos_x;
	}



	public int getRef_pos_y() {
		return ref_pos_y;
	}



	public int getY_jauge_acceleration() {
		return y_jauge_acceleration;
	}



	public int getX_jauge_acceleration() {
		return x_jauge_acceleration;
	}



	public int getY_jauge_acceleration_vide() {
		return y_jauge_acceleration_vide;
	}



	public int getY_jauge_energie() {
		return y_jauge_energie;
	}



	public int getX_jauge_energie() {
		return x_jauge_energie;
	}






}
