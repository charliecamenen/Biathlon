package com.biathlon.action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.biathlon.jeu.Accueil;
import com.biathlon.jeu.Joueur;
import com.biathlon.jeu.Main;

public class GestionAccueil extends InterfaceGraphique {

	//Panel droite milieu gauche
	private JPanel panel_joueur;
	private JPanel panel_mail;
	private JPanel panel_infos;

	//Element de gauche
	private JPanel panel_joueur_homme;
	private JPanel panel_joueur_femme;

	//Sous panel FEMME
	private JPanel panel_joueur_femme_nom_drapeau;
	private JPanel panel_joueur_femme_header_entrainement;
	private JPanel panel_joueur_femme_center_entrainement;
	private JPanel panel_joueur_femme_footer_entrainement;
	private JPanel panel_joueur_femme_entrainement;
	//Element de gauche femme
	private JLabel label_joueur_femme_photo;
	private JLabel label_joueur_femme_nom;
	private JLabel label_joueur_femme_drapeau;
	private JPanelJauge panel_joueur_femme_forme;
	private JPanelJauge panel_joueur_femme_fatigue;
	private JTable table_joueur_femme_infos;
	private JTable table_joueur_femme_notes;
	private ArrayList<JPanelGraphisme> list_joueur_femme_barres_entrainement;
	private ArrayList<JButton> list_joueur_femme_button_entrainement_haut;
	private ArrayList<JButton> list_joueur_femme_button_entrainement_bas;

	//Sous panel HOMME
	private JPanel panel_joueur_homme_nom_drapeau;
	private JPanel panel_joueur_homme_header_entrainement;
	private JPanel panel_joueur_homme_center_entrainement;
	private JPanel panel_joueur_homme_footer_entrainement;
	private JPanel panel_joueur_homme_entrainement;
	//Element de gauche Homme
	private JLabel label_joueur_homme_photo;
	private JLabel label_joueur_homme_nom;
	private JLabel label_joueur_homme_drapeau;
	private JPanelJauge panel_joueur_homme_forme;
	private JPanelJauge panel_joueur_homme_fatigue;
	private JTable table_joueur_homme_infos;
	private JTable table_joueur_homme_notes;
	private ArrayList<JPanelGraphisme> list_joueur_homme_barres_entrainement;
	private ArrayList<JButton> list_joueur_homme_button_entrainement_haut;
	private ArrayList<JButton> list_joueur_homme_button_entrainement_bas;

	//Panel des mails
	private JPanel panel_head_mail;
	private JPanel panel_list_mail;
	private JPanel panel_content_mail;
	private JPanel panel_button_mail;
	//Sous panel
	private JPanel panel_equipe;
	private JPanel panel_equipe_homme;
	private JPanel panel_equipe_femme;
	private JPanel panel_titre_list_mail;
	private JPanel panel_objet_mail;
	//Element des mails
	private JLabel label_titre;
	private JLabel label_titre_equipe_homme;
	private JLabel label_titre_equipe_femme;
	private JLabel label_titre_list_mail;
	private JLabel label_objet_mail;
	private JTable table_equipe_homme;
	private JTable table_equipe_femme;
	private JTable table_list_mail;
	private JTextArea area_content_mail;
	private JButton button_suppr_mail;
	private JCheckBox checkbox_select_all_mail;


	//panel de droite
	private JPanel panel_resume_classement;
	private JPanel panel_resume_calendrier;
	private JPanel panel_button_retour_menu;
	private JPanel panel_button_avancer;
	//Sous panel de droite
	private JPanel panel_header_classement_week;
	private JPanel panel_header_resume_calendrier;
	//Element de droite
	private JLabel label_classement_week_homme;
	private JLabel label_classement_week_femme;
	private JLabel label_titre_resume_calendrier;
	private JTable table_resume_clendrier;
	private JButton button_droite_calendrier;
	private JButton button_gauche_calendrier;
	private JLabel label_titre_classement_week;
	private JTable table_classement_week_homme;
	private JTable table_classement_week_femme;
	private JButton button_droite_classement;
	private JButton button_gauche_classement;
	private JButton button_avancer;
	private JLabel date_du_jour;
	private JButton button_retour_menu;

	private final int taille_entrainement = 18;
	private Container panel_checkbox_button_mail;
	private Container panel_button_suppr_mail;


	public GestionAccueil() {
		super("/images/background/novemesto.png");

		//On instancie les objets
		this.creerObjet();
		//On ajoute les objets a leur panel
		this.afficheElement();

		//bouton du bas
		button_avancer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Cherche les courses de la journée
				ResultSet select_course_courrante = Main.database.requete(""
						+ "SELECT * "
						+ "FROM courses "
						+ "WHERE id_joueur = "+ Main.joueur.getId_joueur()
						+ " and annee = "+ Main.joueur.getAnnee()
						+ " and id_date_course = " + Main.joueur.getId_date_courrante()
						);

				//Boolean de changement de jour
				boolean changement_jour = true;
				//On parcour les courses de la journee
				try {while(select_course_courrante.next()) {
					//si elles est n'est pas termineé
					if(select_course_courrante.getString("statut_course") == "a venir") {
						//Alors on ne change plus de jour
						changement_jour = false;
						//Et l'id course est mit a jour
						Main.joueur.setId_course_courrante(select_course_courrante.getInt("id_course"));
					}
				}} catch (SQLException ex) {ex.printStackTrace();}


				//si il n'y a pas de course
				if(changement_jour){
					avancerCarriere();
				}
				else { //sinon on avance dans la carrière
					Main.joueur.simulationCourse();
				}
			}
		});

		//bouton du bas
		button_retour_menu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Accueil acceuil = new Accueil();
				actuFenetre(acceuil);
			}
		});

		//Initialise les valeurs des niveaux d'entrainement
		for(int i = 0 ; i < taille_entrainement ; i++) {
			/**** Homme ****/
			//Bouton du haut
			list_joueur_homme_button_entrainement_bas.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton2 b = (JButton2)e.getSource(); 
					updateNiveauEntrainement(b.getIndice_list(),-1,list_joueur_homme_barres_entrainement);
				}
			});
			//bouton du bas
			list_joueur_homme_button_entrainement_haut.get(i).addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					JButton2 b = (JButton2)e.getSource(); 
					updateNiveauEntrainement(b.getIndice_list(),1,list_joueur_homme_barres_entrainement);
				}
			});

			/**** femme ****/
			//Bouton du haut
			list_joueur_femme_button_entrainement_bas.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton2 b = (JButton2)e.getSource(); 
					updateNiveauEntrainement(b.getIndice_list(),-1,list_joueur_femme_barres_entrainement);
				}
			});
			//bouton du bas
			list_joueur_femme_button_entrainement_haut.get(i).addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					JButton2 b = (JButton2)e.getSource(); 
					updateNiveauEntrainement(b.getIndice_list(),1,list_joueur_femme_barres_entrainement);
				}
			});
		}
	}

	//mettre cette methode dans la classe SQL
	public void avancerCarriere() {


		//Update la date(joeuur)
		Main.database.updateDateJoueur(Main.joueur.getId_joueur());

		//Si fin de saison
		if(true) {

			//Update année joueur courrant(joeuur)
			Main.database.updateAnneeJoueur();
			//Generer les nouveau biathletes(Biathlete Carriere)
			//Generation des potentiel(Biathlete Carriere)
			//Mettre a la retraite les vieux(Biathlete Carriere)

			//Le planning des courses
			Main.database.initPlanningCourse();

			//Creation des planning d'entrainement((Biathlete Carriere)

			//Mail de début de saison(mailcarriere)
		}
		//Update les blessures (Biathlete Carriere)

		//Update des notes(Biathlete Carriere)



		//Update des partiipants a la prochaine course (participants/membre)
		//SEULEMENT SI CHANGEMENT DE SITE SINON METTRE DANS LA FIN DE LA COURSE
		//Cherche les courses de la journée suivante
		ResultSet select_course_suivante = Main.database.requete(""
				+ "SELECT * "
				+ "FROM courses "
				+ "WHERE id_joueur = "+ Main.joueur.getId_joueur()
				+ " and annee = "+ Main.joueur.getAnnee()
				+ " and id_date_course = " + Main.joueur.getId_date_courrante()
				);

		
		//On parcour les courses de la journee
		try {while(select_course_suivante.next()) {
		
				//Met a jour les participants de la courses
				Main.database.initCourse(select_course_suivante);
				
				//Et l'id course est mit a jour
				Main.joueur.setId_course_courrante(select_course_suivante.getInt("id_course"));
			}
		} catch (SQLException ex) {ex.printStackTrace();}

	}

	private void creerObjet() {

		//panel principaux du borderLayout
		panel_content = panelSansBgStyle(new JPanel());

		//Panel droite milieu gauche
		panel_joueur = panelTransparentStyle(new JPanel());
		panel_mail = panelTransparentStyle(new JPanel());
		panel_infos = panelTransparentStyle(new JPanel());

		/**** JOUEUR ****/

		panel_joueur_homme =  panelSansBgStyle(new JPanel());
		panel_joueur_femme = panelSansBgStyle(new JPanel());

		//HOMME
		panel_joueur_homme_nom_drapeau = panelSansBgStyle(new JPanel()); 
		panel_joueur_homme_entrainement = panelStyle(new JPanel(),Color.WHITE,Color.BLACK);
		panel_joueur_homme_entrainement.setBackground(new Color(0,52,95));
		panel_joueur_homme_header_entrainement = panelSansBgStyle(new JPanel());
		panel_joueur_homme_center_entrainement = panelSansBgStyle(new JPanel());
		panel_joueur_homme_footer_entrainement = panelSansBgStyle(new JPanel());

		//Element homme
		//Liste pour les entrainement homme
		list_joueur_homme_button_entrainement_bas = new ArrayList<>();
		list_joueur_homme_button_entrainement_haut = new ArrayList<>();
		list_joueur_homme_barres_entrainement = new ArrayList<>();

		//On ajoute les elements dans les liste d'entrainement
		for (int i = 0; i<taille_entrainement; i++) {
			list_joueur_homme_button_entrainement_bas.add(entrainementButtonStyle(new JButton2("",i)));
			list_joueur_homme_button_entrainement_haut.add(entrainementButtonStyle(new JButton2("",i)));
			list_joueur_homme_barres_entrainement.add((JPanelGraphisme) panelSansBgStyle(new JPanelGraphisme(5)));
		}
		label_joueur_homme_photo= new JLabel(getImageJoueur("homme",200,200));
		label_joueur_homme_nom= labelStyle(new JLabel("Homme"));
		label_joueur_homme_drapeau = this.reccupereDrapeauBiathlete();
		panel_joueur_homme_forme = new JPanelJauge("/images/gestion/jauge_forme.png","forme");
		panel_joueur_homme_forme.setLayout(new FlowLayout(FlowLayout.CENTER,10, 10));
		panel_joueur_homme_fatigue = new JPanelJauge("/images/gestion/jauge_fatigue.png","fatigue");
		panel_joueur_homme_fatigue.setLayout(new FlowLayout(FlowLayout.CENTER,10, 10));
		//Tableau des notes Hommes
		table_joueur_homme_infos = this.genererTableauInfos();
		table_joueur_homme_notes = this.genererTableauNotes();



		//femme
		panel_joueur_femme_nom_drapeau = panelSansBgStyle(new JPanel()); 
		panel_joueur_femme_entrainement = panelStyle(new JPanel(),Color.WHITE,Color.BLACK);
		panel_joueur_femme_entrainement.setBackground(new Color(0,52,95));
		panel_joueur_femme_header_entrainement = panelSansBgStyle(new JPanel());
		panel_joueur_femme_center_entrainement = panelSansBgStyle(new JPanel());
		panel_joueur_femme_footer_entrainement = panelSansBgStyle(new JPanel());

		//Element femme
		//Liste pour les entrainement femme
		list_joueur_femme_button_entrainement_bas = new ArrayList<>();
		list_joueur_femme_button_entrainement_haut = new ArrayList<>();
		list_joueur_femme_barres_entrainement = new ArrayList<>();

		//On ajoute les elements dans les liste d'entrainement
		for (int i = 0; i<taille_entrainement; i++) {
			list_joueur_femme_button_entrainement_bas.add(entrainementButtonStyle(new JButton2("",i)));
			list_joueur_femme_button_entrainement_haut.add(entrainementButtonStyle(new JButton2("",i)));
			list_joueur_femme_barres_entrainement.add((JPanelGraphisme) panelSansBgStyle(new JPanelGraphisme(5)));
		}
		label_joueur_femme_photo= new JLabel(getImageJoueur("femme",200,200));
		label_joueur_femme_nom= labelStyle(new JLabel("Femme"));
		label_joueur_femme_drapeau = this.reccupereDrapeauBiathlete();
		panel_joueur_femme_forme = new JPanelJauge("/images/gestion/jauge_forme.png","forme");
		panel_joueur_femme_forme.setLayout(new FlowLayout(FlowLayout.CENTER,10, 10));
		panel_joueur_femme_fatigue = new JPanelJauge("/images/gestion/jauge_fatigue.png","fatigue");
		panel_joueur_femme_fatigue.setLayout(new FlowLayout(FlowLayout.CENTER,10, 10));
		//Tableau des notes femmes
		table_joueur_femme_infos = this.genererTableauInfos();
		table_joueur_femme_notes = this.genererTableauNotes();


		/***** MAIL  ******/
		panel_head_mail = panelSansBgStyle(new JPanel());
		panel_equipe  = panelSansBgStyle(new JPanel());
		panel_equipe_homme  = panelSansBgStyle(new JPanel());
		panel_equipe_femme  = panelSansBgStyle(new JPanel());
		panel_list_mail = panelSansBgStyle(new JPanel());
		panel_content_mail = panelSansBgStyle(new JPanel());
		panel_button_mail = panelBorderStyle(new JPanel());
		panel_checkbox_button_mail= panelSansBgStyle(new JPanel());
		panel_button_suppr_mail= panelSansBgStyle(new JPanel());
		panel_titre_list_mail = panelStyle(new JPanel(),Color.WHITE,Color.BLACK);
		panel_objet_mail= panelStyle(new JPanel(),Color.WHITE,Color.WHITE);

		//Element des mails
		label_titre = new JLabel(this.scaleImage(this.titreSite(),700,700));
		label_titre_equipe_homme = labelStyle(new JLabel("Equipe masculine"));
		table_equipe_homme = this.genererTableauEquipe('h');
		label_titre_equipe_femme = labelStyle(new JLabel("Equipe féminine"));
		table_equipe_femme = this.genererTableauEquipe('f');
		label_titre_list_mail = labelStyle(new JLabel("Boite mail"),20, 0,Color.BLACK);
		table_list_mail = genererListMail();
		label_objet_mail = labelStyle(new JLabel(this.genererObjetMail()),18, SwingConstants.LEFT,Color.BLACK);
		area_content_mail = textAreaStyle(genererContentMail());
		button_suppr_mail = smallButtonStyle(new JButton("Supprimer"));
		checkbox_select_all_mail = checkBoxStyle(new JCheckBox("Tout sélectionner"));


		/**** INFOS ****/
		panel_resume_classement = panelSansBgStyle(new JPanel());
		panel_button_retour_menu  = panelSansBgStyle(new JPanel());
		panel_button_avancer = panelSansBgStyle(new JPanel());
		panel_resume_calendrier  = panelSansBgStyle(new JPanel());
		//Sous panel
		panel_header_classement_week = panelSansBgStyle(new JPanel());
		panel_header_resume_calendrier= panelSansBgStyle(new JPanel());

		//Element de droite
		//Suivant + date
		button_avancer= mediumButtonStyle(new JButton("Prochaine course"));
		date_du_jour = labelStyle(this.dateDuJour());
		//Calendrier
		label_titre_resume_calendrier = labelStyle(this.titreResumeCalendrier(),20, 0,null);
		button_droite_calendrier= this.infosFlecheButtonStyle(new JButton(">"));
		button_gauche_calendrier= this.infosFlecheButtonStyle(new JButton("<"));
		table_resume_clendrier = this.genererTableauResumeCalendrier();
		//Classement
		label_classement_week_homme = labelStyle(new JLabel("Hommes"),15, SwingConstants.LEFT,null);
		label_classement_week_femme = labelStyle(new JLabel("Femmes"),15, SwingConstants.LEFT,null);
		label_titre_classement_week = labelStyle(new JLabel("Top 5 Semaine") ,20, 0,null);
		button_droite_classement= this.infosFlecheButtonStyle(new JButton(">"));
		button_gauche_classement= this.infosFlecheButtonStyle(new JButton("<"));
		table_classement_week_homme = this.genererTableauClassementWeek('h');
		table_classement_week_femme = this.genererTableauClassementWeek('f');
		//retour au menu
		button_retour_menu= mediumButtonStyle(new JButton("Retour au menu"));

	}

	private void afficheElement() {

		/***** JOUEUR *****/

		//HOMME

		//Ajoute un grid layout pour éttirer le contenue
		panel_joueur_homme_header_entrainement.setLayout(new GridLayout(1,list_joueur_homme_button_entrainement_bas.size()));
		panel_joueur_homme_footer_entrainement.setLayout(new GridLayout(1,list_joueur_homme_button_entrainement_bas.size()));
		panel_joueur_homme_center_entrainement.setLayout(new GridLayout(1,list_joueur_homme_button_entrainement_bas.size()));

		//On ajoute les fleches du haut et bas au panel d'entrainement
		for(int i = 0; i < this.taille_entrainement; i++) {
			panel_joueur_homme_header_entrainement.add(list_joueur_homme_button_entrainement_haut.get(i));
			panel_joueur_homme_footer_entrainement.add(list_joueur_homme_button_entrainement_bas.get(i));
			panel_joueur_homme_center_entrainement.add(list_joueur_homme_barres_entrainement.get(i));
		}
		//Panel d'entrainement
		panel_joueur_homme_entrainement.setLayout(new BorderLayout());
		panel_joueur_homme_entrainement.add(panel_joueur_homme_header_entrainement, BorderLayout.NORTH);
		panel_joueur_homme_entrainement.add(panel_joueur_homme_center_entrainement, BorderLayout.CENTER);
		panel_joueur_homme_entrainement.add(panel_joueur_homme_footer_entrainement, BorderLayout.SOUTH);

		//Panel drapeaunom
		panel_joueur_homme_nom_drapeau.add(label_joueur_homme_drapeau);
		panel_joueur_homme_nom_drapeau.add(label_joueur_homme_nom);
		//Ajouter au panel de joueur homme
		panel_joueur_homme.setLayout(new GridBagLayout());
		panel_joueur_homme.add(label_joueur_homme_photo, gbc(0,0,1,1,0,0,null,0));
		panel_joueur_homme.add(panel_joueur_homme_nom_drapeau, gbc(1,0,1,1,0,0,null,0));
		panel_joueur_homme.add(panel_joueur_homme_forme , gbc(2,0,1,1,0,0,null,GridBagConstraints.BOTH));
		panel_joueur_homme.add(panel_joueur_homme_fatigue, gbc(3,0,1,1,0,0,null,GridBagConstraints.BOTH));
		panel_joueur_homme.add(table_joueur_homme_infos, gbc(4,0,1,1,0,0,new Insets(10, 10, 10, 10),GridBagConstraints.BOTH));
		panel_joueur_homme.add(table_joueur_homme_notes, gbc(5,0,1,1,0,0,new Insets(10, 10, 10, 10),GridBagConstraints.BOTH));
		panel_joueur_homme.add(panel_joueur_homme_entrainement, gbc(6,0,60,1,0,0,new Insets(10, 10, 10, 10),GridBagConstraints.BOTH));


		//femme

		//Ajoute un grid layout pour éttirer le contenue
		panel_joueur_femme_header_entrainement.setLayout(new GridLayout(1,list_joueur_femme_button_entrainement_bas.size()));
		panel_joueur_femme_footer_entrainement.setLayout(new GridLayout(1,list_joueur_femme_button_entrainement_bas.size()));
		panel_joueur_femme_center_entrainement.setLayout(new GridLayout(1,list_joueur_femme_button_entrainement_bas.size()));

		//On ajoute les fleches du haut et bas au panel d'entrainement
		for(int i = 0; i < this.taille_entrainement; i++) {
			panel_joueur_femme_header_entrainement.add(list_joueur_femme_button_entrainement_haut.get(i));
			panel_joueur_femme_footer_entrainement.add(list_joueur_femme_button_entrainement_bas.get(i));
			panel_joueur_femme_center_entrainement.add(list_joueur_femme_barres_entrainement.get(i));
		}
		//Panel d'entrainement
		panel_joueur_femme_entrainement.setLayout(new BorderLayout());
		panel_joueur_femme_entrainement.add(panel_joueur_femme_header_entrainement, BorderLayout.NORTH);
		panel_joueur_femme_entrainement.add(panel_joueur_femme_center_entrainement, BorderLayout.CENTER);
		panel_joueur_femme_entrainement.add(panel_joueur_femme_footer_entrainement, BorderLayout.SOUTH);

		//Panel drapeaunom
		panel_joueur_femme_nom_drapeau.add(label_joueur_femme_drapeau);
		panel_joueur_femme_nom_drapeau.add(label_joueur_femme_nom);
		//Ajouter au panel de joueur femme
		panel_joueur_femme.setLayout(new GridBagLayout());
		panel_joueur_femme.add(label_joueur_femme_photo, gbc(0,0,1,1,0,0,null,0));
		panel_joueur_femme.add(panel_joueur_femme_nom_drapeau, gbc(1,0,1,1,0,0,null,0));
		panel_joueur_femme.add(panel_joueur_femme_forme , gbc(2,0,1,1,0,0,null,GridBagConstraints.BOTH));
		panel_joueur_femme.add(panel_joueur_femme_fatigue, gbc(3,0,1,1,0,0,null,GridBagConstraints.BOTH));
		panel_joueur_femme.add(table_joueur_femme_infos, gbc(4,0,1,1,0,0,new Insets(10, 10, 10, 10),GridBagConstraints.BOTH));
		panel_joueur_femme.add(table_joueur_femme_notes, gbc(5,0,1,1,0,0,new Insets(10, 10, 10, 10),GridBagConstraints.BOTH));
		panel_joueur_femme.add(panel_joueur_femme_entrainement, gbc(6,0,60,1,0,0,new Insets(10, 10, 10, 10),GridBagConstraints.BOTH));

		//Ajouter homme et femme
		panel_joueur.setLayout(new GridLayout(1,2));
		panel_joueur.add(panel_joueur_homme);
		panel_joueur.add(panel_joueur_femme);

		/***** MAIL *****/
		//panel d'equipe homme et femme
		panel_equipe_homme.setLayout(new BorderLayout());
		panel_equipe_homme.add(label_titre_equipe_homme,BorderLayout.NORTH);
		panel_equipe_homme.add(scrollPaneStyle(new JScrollPane(table_equipe_homme),null,true),BorderLayout.CENTER);
		panel_equipe_femme.setLayout(new BorderLayout());
		panel_equipe_femme.add(label_titre_equipe_femme,BorderLayout.NORTH);
		panel_equipe_femme.add(scrollPaneStyle(new JScrollPane(table_equipe_femme),null,true), BorderLayout.CENTER);
		//panel equipe
		panel_equipe.setLayout(new GridBagLayout());
		panel_equipe.add(panel_equipe_homme, gbc(0,0,1,1,0,0,new Insets(10, 10, 10, 10),GridBagConstraints.BOTH));
		panel_equipe.add(panel_equipe_femme, gbc(0,1,1,1,0,0,new Insets(10, 10, 10, 10),GridBagConstraints.BOTH));
		//panel header
		//panel_head_mail.setLayout(new GridBagLayout());
		panel_head_mail.setLayout(new BorderLayout());
		panel_head_mail.add(label_titre,BorderLayout.NORTH);
		panel_head_mail.add(panel_equipe,BorderLayout.CENTER);
		//Liste mails
		panel_titre_list_mail.add(label_titre_list_mail);
		panel_list_mail.setLayout(new BorderLayout());
		panel_list_mail.add(panel_titre_list_mail,BorderLayout.NORTH);
		panel_list_mail.add(scrollPaneStyle(new JScrollPane(table_list_mail), null, false),BorderLayout.CENTER);

		//Option mail
		panel_checkbox_button_mail.setLayout(new FlowLayout(FlowLayout.LEFT,25,0));
		panel_checkbox_button_mail.add(checkbox_select_all_mail);
		panel_button_suppr_mail.setLayout(new FlowLayout(FlowLayout.RIGHT,25,0));
		panel_button_suppr_mail.add(button_suppr_mail);
		panel_button_mail.setLayout(new GridLayout(1,2));
		panel_button_mail.add(panel_checkbox_button_mail);
		panel_button_mail.add(panel_button_suppr_mail);
		panel_button_mail.setPreferredSize(new Dimension(30,30));
		//Objet du mail
		panel_objet_mail.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		panel_objet_mail.add(label_objet_mail);
		//Content
		panel_content_mail.setLayout(new BorderLayout());
		panel_content_mail.add(area_content_mail,BorderLayout.CENTER);


		//Ajouter au panel de mail
		panel_mail.setLayout(new GridBagLayout());
		panel_mail.add(panel_head_mail,
				gbc(0, 0, 20, 1, 1, 0, null, GridBagConstraints.BOTH));
		panel_mail.add(panel_list_mail,
				gbc(1, 0, 30, 1, 1, 0, null, GridBagConstraints.BOTH));
		panel_mail.add(panel_button_mail,
				gbc(2, 0, 2, 1, 1, 0, null, GridBagConstraints.BOTH));
		panel_mail.add(panel_objet_mail,
				gbc(3, 0, 1, 1, 1, 0, null, GridBagConstraints.BOTH));
		panel_mail.add(scrollPaneStyle(new JScrollPane(panel_content_mail),null,false),
				gbc(4, 0, 60, 1, 1, 0, null, GridBagConstraints.BOTH));



		/***** INFOS *****/
		//SOUS panel calendrier
		panel_header_resume_calendrier.setLayout(new GridBagLayout());
		panel_header_resume_calendrier.add(button_gauche_calendrier,gbc(0,0,1,1,0,0,new Insets(5, 5, 5, 5),0));
		panel_header_resume_calendrier.add(label_titre_resume_calendrier,gbc(0,1,1,1,0,0,new Insets(5, 5, 5, 5),GridBagConstraints.BOTH));
		panel_header_resume_calendrier.add(button_droite_calendrier,gbc(0,2,1,1,0,0,new Insets(5, 5, 5, 5),0));
		//sous panel classement week
		panel_header_classement_week.setLayout(new GridBagLayout());
		panel_header_classement_week.add(button_gauche_classement,gbc(0,0,1,1,0,0,new Insets(5, 5, 5, 5),0));
		panel_header_classement_week.add(label_titre_classement_week,gbc(0,1,1,1,0,0,new Insets(5, 5, 5, 5),GridBagConstraints.BOTH));
		panel_header_classement_week.add(button_droite_classement,gbc(0,2,1,1,0,0,new Insets(5, 5, 5, 5),0));

		//Panel boutton avancer + date
		panel_button_avancer.setLayout(new GridBagLayout());
		panel_button_avancer.add(button_avancer,gbc(0,0,1,1,0,0,new Insets(0, 5, 5, 5),GridBagConstraints.BOTH));
		panel_button_avancer.add(date_du_jour,gbc(1,0,1,1,0,0,new Insets(5, 5, 5, 5),GridBagConstraints.BOTH));
		//Panel resume calendrier
		panel_resume_calendrier.setLayout(new GridBagLayout());
		panel_resume_calendrier.add(panel_header_resume_calendrier,gbc(0,0,1,1,0,0,null,GridBagConstraints.HORIZONTAL));
		panel_resume_calendrier.add(table_resume_clendrier,gbc(1,0,30,1,0,0,null,GridBagConstraints.BOTH));
		//panel classement week
		panel_resume_classement.setLayout(new GridBagLayout());
		panel_resume_classement.add(panel_header_classement_week, gbc(0,0,1,1,0,0,null,GridBagConstraints.HORIZONTAL));
		panel_resume_classement.add(label_classement_week_homme, gbc(1,0,1,1,0,0,new Insets(0, 10, 0, 10),GridBagConstraints.HORIZONTAL));
		panel_resume_classement.add(table_classement_week_homme,gbc(2,0,10,1,0,0,new Insets(5, 5, 5, 5),GridBagConstraints.BOTH));
		panel_resume_classement.add(label_classement_week_femme, gbc(3,0,1,1,0,0,new Insets(0, 10, 0, 10),GridBagConstraints.HORIZONTAL));
		panel_resume_classement.add(table_classement_week_femme,gbc(4,0,10,1,0,0,new Insets(5, 5, 5, 5),GridBagConstraints.BOTH));

		//panel retour au menu
		panel_button_retour_menu.add(button_retour_menu);


		//Ajouter au panel infos
		panel_infos.setLayout(new GridBagLayout());
		panel_infos.add(panel_button_avancer,gbc(0,0,1,1,0,0,new Insets(10, 30, 0, 30),GridBagConstraints.BOTH));
		panel_infos.add(panel_resume_calendrier,gbc(1,0,220,1,0,0,new Insets(10, 10, 10, 10),GridBagConstraints.BOTH));
		panel_infos.add(panel_resume_classement,gbc(2,0,220,1,0,0,new Insets(10, 10, 10, 10),GridBagConstraints.BOTH));
		panel_infos.add(panel_button_retour_menu,gbc(3,0,1,1,0,0,new Insets(10, 10, 10, 10),GridBagConstraints.BOTH));


		panel_joueur.setPreferredSize(new Dimension(600,0));
		panel_infos.setPreferredSize(new Dimension(300,0));

		//On ajoute les différentes partie au content de la page
		panel_content.setLayout(new BorderLayout());
		panel_content.add(panel_joueur, BorderLayout.WEST);
		panel_content.add(panel_mail, BorderLayout.CENTER);
		panel_content.add(panel_infos, BorderLayout.EAST);

		//On affiche les 4 elements du border
		super.afficheBorderElement();
	}

	private JLabel dateDuJour() {
		String date_chaine = "11/09/2001";

		return new JLabel(date_chaine);
	}

	private JLabel titreResumeCalendrier() {
		String site = "Ostersund";


		return new JLabel(site);
	}

	private ImageIcon titreSite() {
		ImageIcon ico_titre_site = new ImageIcon(getClass().getResource("/images/gestion/titre_site/antholz.png"));

		return ico_titre_site;
	}


	private JTable genererListMail() {
		Object[][] donnees = {
				{false, "Fourcade Blessé !", "01/12"},
				{false,"Vittozi reprend la tête du classement !", "28/11"},
				{false,"Première victoire pour Doll", "28/11"},
				{false,"Resultat du Sprint de Ostersund", "27/11"},
				{false, "Fourcade Blessé !", "01/12"},
				{false,"Vittozi reprend la tête du classement !", "28/11"},
				{false,"Première victoire pour Doll", "28/11"},
				{false,"Resultat du Sprint de Ostersund", "27/11"},
				{false, "Fourcade Blessé !", "01/12"},
				{false,"Vittozi reprend la tête du classement !", "28/11"},
				{false,"Première victoire pour Doll", "28/11"},
				{false,"Resultat du Sprint de Ostersund", "27/11"},
				{false,"Première victoire pour Doll", "28/11"},
				{false,"Resultat du Sprint de Ostersund", "27/11"},
				{false, "Fourcade Blessé !", "01/12"},
				{false,"Vittozi reprend la tête du classement !", "28/11"},
				{false,"Première victoire pour Doll", "28/11"},
				{false,"Resultat du Sprint de Ostersund", "27/11"},
		};

		String[] entetes = {"","Mail","Date"};

		//Tableau des notes Hommes
		JTable return_table = new JTable(new MonTableModel(
				donnees,
				entetes,
				new ArrayList<Integer>(){{
					add(0);
				}}
				));

		return_table.setDefaultRenderer(Object.class, new MonTableRenderer(
				donnees,
				entetes,
				new Color[] {color_transparent,color_transparent,color_transparent},//couleur des colonnes 
				new Color[] {Color.WHITE,Color.WHITE,Color.WHITE},//couleur du texte
				new int[] {1,1000,300}, //largeur des colonnes
				new int[] {JLabel.CENTER,JLabel.LEFT,JLabel.RIGHT}, //alignement des colonnes
				new Font[] {new Font("calibri", Font.PLAIN, 15), 
						new Font("calibri", Font.PLAIN, 15),
						new Font("calibri", Font.PLAIN, 15)
				},//Font des lignes
				false, //lignes horizontales
				false, // lignes verticales
				20, //Largeur des lignes
				null, // font du header
				null, // couleur de la grid
				color_transparent, // couleur du GB grobal
				false,//focus
				false //select
				));

		//Obligé de faire ca pour enlever le header
		return_table.setTableHeader(null);

		return return_table;
	}

	private String genererObjetMail() {
		String objet_mail = "Présentation de la vie de primoz roglic !";
		return objet_mail;
	}

	private JTable genererTableauEquipe(char sexe) {
		Object[][] donnees;
		String[] entetes = {"Biathlete","Forme", "Note"};;

		if (sexe == 'h') {
			donnees = new Object[][]{
				{"S.DESTHIEUX",64, 78},
				{"M.FOURCADE",95, 77},
				{"Q.FILLON MAILLET",90, 75},
				{"E.JACQUELIN",63, 70},
				{"F.CLAUDE", 77, 68}
			};

		}else {
			donnees =  new Object[][]{
				{"J.BRAISAZ", 93, 80},
				{"A.CHEVALIER",95, 75},
				{"A.BESCOND", 68,74},
				{"C.COLOMBO",79, 72},
			};
		}


		//Tableau des notes Hommes
		JTable return_table = new JTable(new MonTableModel(
				donnees,
				entetes,
				null
				));

		return_table.setDefaultRenderer(Object.class, new MonTableRenderer(
				donnees,
				entetes,
				new Color[] {color_tableau_bg,color_tableau_bg,color_tableau_bg},//couleur des colonnes 
				new Color[] {Color.WHITE,Color.WHITE,Color.WHITE},//couleur du texte
				new int[] {200,10,10}, //largeur des colonnes
				new int[] {JLabel.LEFT,JLabel.CENTER,JLabel.CENTER}, //alignement des colonnes
				new Font[] {new Font("calibri", Font.PLAIN, 15), 
						new Font("calibri", Font.PLAIN, 15),
						new Font("calibri", Font.PLAIN, 15)
				},//Font des lignes
				true, //lignes horizontales
				false, // lignes verticales
				20, //Largeur des lignes
				null, // font du header
				null, // couleur de la grid
				color_tableau_bg, // couleur du GB grobal
				false,//focus
				false //select
				));

		//Obligé de faire ca pour enlever le header
		return_table.setTableHeader(null);

		return return_table;
	}


	private JTextArea genererContentMail() {
		String str = "Primož Roglič (Écouter), né le 29 octobre 1989 à Trbovlje, est un sauteur à ski et coureur cycliste slovène. Il est membre de l'équipe Jumbo-Visma. Roglič a commencé sa carrière en tant que sauteur à ski, avant de passer sur le tard au cyclisme. À l'aise en contre-la-montre et dans les ascensions, c'est un spécialiste des courses par étapes. Il est classé numéro 1 mondial à l'issue des années 2019 et 2020.\n" + 
				"Il a remporté plusieurs courses par étapes d'une semaine : le Tour du Pays basque 2018\n"
				+ "1. M.Fourcade\n"
				+ "2. J.Boe\n"
				+ "3. B.Doll\n";
		return new JTextArea(ajouterRetourLigne(str));
	}

	private JTable genererTableauNotes() {
		Object[][] donnees = {
				{"REC : ", 80, "END : ",90 , "COU : ", 87  },
				{"TIR : ", 89, "ACC : ",75, "REG : ",81 },
				{"DEB : ", 77, "SKI : ",66, "REL : ",90 }
		};

		String[] entetes = {"label1", "note1","label2","note2", "label3","note3"};		


		//Tableau des notes Hommes
		JTable return_table = new JTable(new MonTableModel(
				donnees,
				entetes,
				null
				));

		return_table.setDefaultRenderer(Object.class, new MonTableRenderer(
				donnees,
				entetes,
				new Color[] {color_transparent,color_transparent,color_transparent,color_transparent,color_transparent,color_transparent},//couleur des colonnes 
				new Color[] {Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE},//couleur du texte
				new int[] {1,1,1,1,1,1}, //largeur des colonnes
				new int[] {JLabel.RIGHT,JLabel.LEFT,JLabel.RIGHT,JLabel.LEFT,JLabel.RIGHT,JLabel.LEFT}, //alignement des colonnes
				new Font[] {new Font("calibri", Font.PLAIN, 15), 
						new Font("calibri", Font.BOLD, 15),
						new Font("calibri", Font.PLAIN, 15),
						new Font("calibri", Font.BOLD, 15), 
						new Font("calibri", Font.PLAIN, 15),
						new Font("calibri", Font.BOLD, 15)
				},//Font des lignes
				false, //lignes horizontales
				false, // lignes verticales
				20, //Largeur des lignes
				null, // font du header
				null, // couleur de la grid
				color_transparent, // couleur du GB grobal
				false,//focus
				false //select
				));

		//Obligé de faire ca pour enlever le header
		return_table.setTableHeader(null);

		return return_table;
	}

	private JTable genererTableauInfos() {
		Object[][] donnees = {
				{"Site favoris : ","Nove mesto" , scaleImage(new ImageIcon(getClass().getResource("/images/gestion/3etoiles.png")),60,60)},
				{" ", "Hochfilzen" , scaleImage(new ImageIcon(getClass().getResource("/images/gestion/2etoiles.png")),40,40) },
				{" ", "Ostersund", scaleImage(new ImageIcon(getClass().getResource("/images/gestion/1etoile.png")),20,20) },
				{"Spécialité : ", "Tir couché",""},
				{"Course favorite : ", "Sprint",""}
		};

		String[] entetes = {"label", "infos","bonus"};	



		JTable return_table = new JTable(donnees, entetes);


		return_table.setDefaultRenderer(Object.class, new MonTableRenderer(
				donnees,
				entetes,
				new Color[] {color_transparent,color_transparent,color_transparent}, 
				new Color[] {Color.WHITE,Color.WHITE,Color.WHITE},
				new int[] {50,20,2}, 
				new int[] {JLabel.RIGHT,JLabel.LEFT,JLabel.LEFT}, 
				new Font[] {new Font("calibri", Font.PLAIN, 15),
						new Font("calibri", Font.PLAIN, 15),
						new Font("calibri", Font.PLAIN, 15)
				},
				false,
				false,
				20,
				null,
				null,
				color_transparent,
				false,//focus
				false //select
				));

		return return_table;
	}

	private JTable genererTableauClassementWeek(char sexe) {
		Object[][] donnees;
		String[] entetes;

		if (sexe == 'h') {
			donnees = new Object[][]{
				{"1", "J.BOE", 77},
				{"2", "M.FOURCADE", 75},
				{"3", "Q.FILLON MAILLET", 62},
				{"4", "B.DOLL", 55},
				{"5", "J.DALE", 51}
			};

			entetes =  new String[]{"Cls", "Biathlete","Pts"};	

		}else {
			donnees =  new Object[][]{
				{"1", "H.OEBERG", 80},
				{"2", "D.WEIRER", 72},
				{"3", "A.BESCOND", 60},
				{"4", "L.VITTOZZI", 59},
				{"5", "M.ROISELAND", 50}
			};

			entetes =  new String[]{"Cls", "Biathlete","Pts"};	

		}

		//renderer des notes hommes
		DefaultTableCellRenderer[] list_rend_notes = new DefaultTableCellRenderer[] {
				rendererTable(JLabel.CENTER),
				rendererTable(JLabel.LEFT),
				rendererTable(JLabel.RIGHT),
		};

		//Tableau des notes Hommes
		JTable return_table = new JTable(new MonTableModel(
				donnees,
				entetes,
				null));


		return_table.setDefaultRenderer(Object.class, new MonTableRenderer(
				donnees,
				entetes,
				new Color[] {color_tableau_bg_second,color_tableau_bg,color_tableau_bg}, 
				new Color[] {Color.BLACK,Color.WHITE,Color.WHITE},
				new int[] {2,180,10}, 
				new int[] {JLabel.CENTER,JLabel.LEFT,JLabel.CENTER}, 
				new Font[] {new Font("calibri", Font.BOLD, 14),new Font("calibri", Font.PLAIN, 13),new Font("calibri", Font.PLAIN, 13)},
				true,
				false,
				19,
				null,
				null,
				color_tableau_bg,
				false,//focus
				false //select
				));

		return return_table;
	}

	private JTable genererTableauResumeCalendrier() {
		Object[][] donnees = {
				{"Individuelle Homme", "04/02"},
				{"Relai Homme", "04/02"},
				{"Sprint Homme", "05/02"},
				{"Poursuite Homme", "05/02"},
				{"Mass Start Homme", "06/02"},
				{"Single Mixte", "06/02"},
				{"Individuelle Femme", "07/02"},
				{"Relai Femme", "08/02"},
				{"Sprint Femme", "10/02"},
				{"Poursuite Femme", "11/02"},
				{"Mass Start Femme", "12/02"},
				{"Relai Mixte", "12/02"}
		};

		String[] entetes = {"Epreuve", "Date"};		

		//Tableau des notes Hommes
		JTable return_table = new JTable(new MonTableModel(
				donnees,
				entetes,
				null
				));

		return_table.setDefaultRenderer(Object.class, new MonTableRenderer(
				donnees,
				entetes,
				new Color[] {color_tableau_bg,color_tableau_bg},//couleur des colonnes 
				new Color[] {Color.WHITE,Color.WHITE},//couleur du texte
				new int[] {10,1}, //largeur des colonnes
				new int[] {JLabel.LEFT,JLabel.RIGHT}, //alignement des colonnes
				new Font[] {new Font("calibri", Font.PLAIN, 15), 
						new Font("calibri", Font.PLAIN, 15)
				},//Font des lignes
				true, //lignes horizontales
				false, // lignes verticales
				20, //Largeur des lignes
				null, // font du header
				null, // couleur de la grid
				color_tableau_bg, // couleur du GB grobal
				false,//focus
				false //select
				));

		//Obligé de faire ca pour enlever le header
		return_table.setTableHeader(null);


		return return_table;
	}


	//Fonction récursive appellée lors des clics sur les boutons + et - des entrainement
	public void updateNiveauEntrainement(int indice, int incr,ArrayList<JPanelGraphisme>  list_joueur) {
		//On raccouric le nom
		JPanelGraphisme barre = list_joueur.get(indice);

		//Si on est encore dans l'echelle d'effort
		if(barre.getNiveau_effort_entrainement() + incr <= 8 && barre.getNiveau_effort_entrainement() + incr >=0) {

			//On vérifie qu'on est pas en bout de ligne a droite
			if(indice+1 < this.taille_entrainement) {
				//SI on monte
				if(incr >0) {
					//si celui de droite est au moins 1 fois plus petit que celui actuel alors on lui mets la fonction
					if(list_joueur.get(indice+1).getNiveau_effort_entrainement() < barre.getNiveau_effort_entrainement()) {
						updateNiveauEntrainement(indice+1,incr,list_joueur);
					}
				}else {
					//SI on descend
					//si celui de droite est au moins 1 fois plus petit que celui actuel alors on lui mets la fonction
					if(list_joueur.get(indice+1).getNiveau_effort_entrainement() > barre.getNiveau_effort_entrainement()) {
						updateNiveauEntrainement(indice+1,incr,list_joueur);
					}
				}

			}

			//On vérifie qu'on est pas en bout de ligne a gauche
			if(indice-1 >= 0) {
				//SI on monte
				if(incr >0) {
					//si celui de droite est au moins 1 fois plus petit que celui actuel alors on lui mets la fonction
					if(list_joueur.get(indice-1).getNiveau_effort_entrainement() < barre.getNiveau_effort_entrainement()) {
						updateNiveauEntrainement(indice-1,incr,list_joueur);
					}
				}else {
					//SI on descend
					//si celui de droite est au moins 1 fois plus petit que celui actuel alors on lui mets la fonction
					if(list_joueur.get(indice-1).getNiveau_effort_entrainement() > barre.getNiveau_effort_entrainement()) {
						updateNiveauEntrainement(indice-1,incr,list_joueur);
					}
				}
			}

			//Alors on actualise l'effort
			barre.setNiveau_effort_entrainement(barre.getNiveau_effort_entrainement() + incr);

			//Calcul de la longueur de la barre selon l'effort
			barre.setLongueur_bar((barre.getNiveau_effort_entrainement() * barre.getTaille_prop_un_niveau()));

			//calcul de la couleur du rectangle selon l'effort
			barre.setRectangle_color_fill(barre.getList_couleur_effort()[barre.getNiveau_effort_entrainement()]);

			barre.repaint();
		}
	}

	public JButton entrainementButtonStyle(JButton button) {
		button.setPreferredSize(new Dimension(10,20));
		button.setBackground(Color.WHITE);
		button.setFont(new Font("calibri", Font.PLAIN, 5));

		return button;
	}

	public JButton infosFlecheButtonStyle(JButton button) {
		button.setPreferredSize(new Dimension(30,30));
		button.setBackground(Color.WHITE);
		button.setFont(new Font("calibri", Font.BOLD, 15));

		return button;
	}

	private JLabel reccupereDrapeauBiathlete() {
		//Requete sur le drapeau

		return new JLabel(scaleImage(new ImageIcon(getClass().getResource("/images/drapeau/icone/france.png")),30,30));
	}

}
