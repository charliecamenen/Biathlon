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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.biathlon.jeu.Main;

public class GestionAccueil extends InterfaceGraphique {

	//Panel droite milieu gauche
	private JPanel panel_joueur;
	private JPanel panel_mail;
	private JPanel panel_infos;

	//panel de droite
	private JPanel panel_resume_classement;
	private JPanel panel_resume_calendrier;
	private JPanel panel_button_retour_menu;
	private JPanel panel_button_avancer;

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
	//Element des mails
	private JLabel titre;
	private JTable table_mail;
	private JTextArea area_content_mail;
	private JButton button_suppr_mail;
	private JCheckBox checkbox_select_all_mail;


	//Element de droite
	private JTable table_resume_clendrier;
	private JButton button_droite_calendrier;
	private JButton button_gauche_calendrier;
	private JTable table_classement_week;
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
		panel_joueur_homme_entrainement = panelStyle(new JPanel());
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
		label_joueur_homme_nom= labelStyle(new JLabel("W.Van Aert"));
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
		panel_joueur_femme_entrainement = panelStyle(new JPanel());
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
		label_joueur_femme_nom= labelStyle(new JLabel("W.Van Aert"));
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
		panel_list_mail = panelSansBgStyle(new JPanel());
		panel_content_mail = panelSansBgStyle(new JPanel());
		panel_button_mail = panelSansBgStyle(new JPanel());
		panel_checkbox_button_mail= panelSansBgStyle(new JPanel());
		panel_button_suppr_mail= panelSansBgStyle(new JPanel());

		//Element des mails
		titre = new JLabel();
		table_mail = genererListMail();
		area_content_mail = textAreaStyle(genererContentMail());
		button_suppr_mail = smallButtonStyle(new JButton("Supprimer"));
		checkbox_select_all_mail = checkBoxStyle(new JCheckBox("Tout sélectionner"));
		/**** INFOS ****/

		panel_resume_classement = panelSansBgStyle(new JPanel());
		panel_button_retour_menu  = panelSansBgStyle(new JPanel());
		panel_button_avancer = panelSansBgStyle(new JPanel());
		panel_resume_calendrier  = panelSansBgStyle(new JPanel());

		//Element de droite
		table_resume_clendrier= this.genererTableauResumeCalendrier();
		table_classement_week = this.genererTableauClassementWeek();
		button_droite_classement= new JButton();
		button_gauche_classement= new JButton();
		button_avancer= new JButton("Prochaine course");
		button_retour_menu= new JButton("Retour au menu");

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

		panel_content_mail.setLayout(new BorderLayout());
		panel_content_mail.add(area_content_mail,BorderLayout.CENTER);
		panel_checkbox_button_mail.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
		panel_checkbox_button_mail.add(checkbox_select_all_mail);
		panel_button_suppr_mail.setLayout(new FlowLayout(FlowLayout.RIGHT,20,20));
		panel_button_suppr_mail.add(button_suppr_mail);
		panel_button_mail.setLayout(new GridLayout(1,2));
		panel_button_mail.add(panel_checkbox_button_mail);
		panel_button_mail.add(panel_button_suppr_mail);
		panel_list_mail.add(table_mail);



		//Ajouter au panel de mail
		panel_mail.setLayout(new GridBagLayout());
		panel_mail.add(panel_head_mail,
				gbc(0, 0, 1, 1, 5, 0, null, 0));
		panel_mail.add(scrollPaneStyle(new JScrollPane(table_mail)),
				gbc(1, 0, 1, 1, 1, 0, null, GridBagConstraints.BOTH));
		panel_mail.add(panel_button_mail,
				gbc(2, 0, 1, 1, 1, 0, null, GridBagConstraints.BOTH));
		panel_mail.add(panel_content_mail,
				gbc(3, 0, 1, 1, 1, 0, null, GridBagConstraints.BOTH));



		/***** INFOS *****/

		panel_button_retour_menu.add(button_retour_menu);

		panel_resume_calendrier.setLayout(new BorderLayout());
		panel_resume_calendrier.add(table_resume_clendrier,BorderLayout.CENTER);

		panel_resume_classement.setLayout(new BorderLayout());
		panel_resume_classement.add(new JScrollPane(table_classement_week), BorderLayout.CENTER);

		panel_button_avancer.setLayout(new BorderLayout());
		panel_button_avancer.add(button_avancer,BorderLayout.CENTER);

		//Ajouter au panel infos
		panel_infos.setLayout(new GridLayout(4,1));
		panel_infos.add(panel_button_avancer);
		panel_infos.add(panel_resume_classement);
		panel_infos.add(panel_resume_calendrier);
		panel_infos.add(panel_button_retour_menu);


		panel_joueur.setPreferredSize(new Dimension(600,0));
		//	panel_mail.setPreferredSize(new Dimension(0,0));
		panel_infos.setPreferredSize(new Dimension(300,0));
		//panel_infos.setBackground(Color.GREEN);

		//On ajoute les différentes partie au content de la page
		panel_content.setLayout(new BorderLayout());
		panel_content.add(panel_joueur, BorderLayout.WEST);
		panel_content.add(panel_mail, BorderLayout.CENTER);
		panel_content.add(panel_infos, BorderLayout.EAST);

		//On affiche les 4 elements du border
		super.afficheBorderElement();
	}



	private JTable genererListMail() {
		Object[][] donnees = {
				{false, "Fourcade Blessé !", "01/12"},
				{false,"Vittozi reprend la tête du classement !", "28/11"},
				{false,"Première victoire pour Doll", "28/11"},
				{false,"Resultat du Sprint de Ostersund", "27/11"},
		};

		String[] entetes = {"","Mail","Date"};

		//renderer des notes hommes
		DefaultTableCellRenderer[] list_rend_notes = new DefaultTableCellRenderer[] {
				rendererTable(JLabel.CENTER),
				rendererTable(JLabel.LEFT),
				rendererTable(JLabel.RIGHT)
		};

		/*	DefaultTableModel tableModel = new DefaultTableModel(donnees, entetes)
		{
		@Override
		public boolean isCellEditable(int row, int column) {
			super.isCellEditable(row, column);
		return column==0;
		}

		@Override
		public Class<?> getColumnClass(int columnIndex)
		{
			super.getColumnClass(columnIndex);
		if(columnIndex==0)
		return Boolean.class;
		return super.getColumnClass(columnIndex);
		}
		};*/

		DefaultTableModel model = new DefaultTableModel(donnees, entetes);

		JTable table = new JTable(model); 

		//Tableau des notes Hommes
		JTable return_table = new JTableUnmodifiable(
				donnees,
				entetes,
				new Font("calibri", Font.PLAIN, 15),
				new Font("calibri", Font.PLAIN, 15),
				20,
				new int[] {1,10,1},
				false,
				false,
				new Color(0,0,0,0),
				list_rend_notes,
				model
				){
			public Class getColumnClass(int column) {
				//renvoie Boolean.class
				return getValueAt(0, column).getClass(); 
			}
		};

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


		//renderer des notes hommes
		DefaultTableCellRenderer[] list_rend_notes = new DefaultTableCellRenderer[] {
				rendererTable(JLabel.RIGHT),
				rendererTable(JLabel.LEFT),
				rendererTable(JLabel.RIGHT),
				rendererTable(JLabel.LEFT),
				rendererTable(JLabel.RIGHT),
				rendererTable(JLabel.LEFT)
		};

		//Tableau des notes Hommes
		JTable return_table = new JTableUnmodifiable(
				donnees,
				entetes,
				null,
				new Font("calibri", Font.PLAIN, 15),
				20,
				new int[] {10,10,10,10,10,10},
				false,
				false,
				new Color(0,0,0,0),
				list_rend_notes
				);

		return return_table;
	}

	private JTable genererTableauInfos() {
		Object[][] donnees = {
				{"Site favoris : ","Nove mesto" ,getClass().getResource("/images/gestion/3etoiles.png")},
				{" ", "Hochfilzen" ,getClass().getResource("/images/gestion/2etoiles.png") },
				{" ", "Ostersund",getClass().getResource("/images/gestion/1etoile.png") },
				{"Spécialité : ", "Tir couché",""},
				{"Course favorite : ", "Sprint",""}
		};

		String[] entetes = {"label", "infos","bonus"};	

		//renderer des notes hommes
		DefaultTableCellRenderer[] list_rend_infos = new DefaultTableCellRenderer[] {
				rendererTable(JLabel.RIGHT),
				rendererTable(JLabel.LEFT),
				rendererTable(JLabel.LEFT)
		};

		JTableUnmodifiable return_table = new JTableUnmodifiable(
				donnees, 
				entetes,
				null,
				new Font("calibri", Font.PLAIN, 15),
				20,
				new int[] {50,20,2},
				false,
				false,
				new Color(0,0,0,0),
				list_rend_infos
				);

		return_table.getColumnModel().getColumn(2).setCellRenderer(new ImageRenderer());

		return return_table;
	}

	private JTable genererTableauClassementWeek() {
		Object[][] donnees = {
				{"1.", "J.BOE", 77},
				{"2.", "M.FOURCADE", 75},
				{"3.", "Q.FILLON MAILLET", 62},
				{"4.", "B.DOLL", 55},
				{"5.", "J.DALE", 51}
		};

		String[] entetes = {"Cls", "Biathlete","Pts"};	

		//renderer des notes hommes
		DefaultTableCellRenderer[] list_rend_notes = new DefaultTableCellRenderer[] {
				rendererTable(JLabel.RIGHT),
				rendererTable(JLabel.LEFT),
				rendererTable(JLabel.RIGHT),
		};

		//Tableau des notes Hommes
		JTable return_table = new JTableUnmodifiable(
				donnees,
				entetes,
				null,
				new Font("calibri", Font.PLAIN, 15),
				20,
				new int[] {10,30,10},
				false,
				false,
				new Color(0,0,0,0),
				list_rend_notes
				);


		return new JTable(donnees, entetes);
	}

	private JTable genererTableauResumeCalendrier() {
		Object[][] donnees = {
				{"Individuelle Homme", "04/02"},
				{"Relai Homme", "04/02"},
				{"Sprint Homme", "05/02"},
				{"Poursuite Homme", "05/02"},
				{"Mass Start Homme", "06/02E"},
				{"Single Mixte", "06/02"},
				{"Individuelle Femme", "07/02"},
				{"Relai Femme", "08/02"},
				{"Sprint Femme", "10/02"},
				{"Poursuite Femme", "11/02"},
				{"Mass Start Femme", "12/02"},
				{"Relai Mixte", "12/02"}
		};

		String[] entetes = {"Epreuve", "Date"};		

		//renderer des notes hommes
		DefaultTableCellRenderer[] list_rend_notes = new DefaultTableCellRenderer[] {
				rendererTable(JLabel.RIGHT),
				rendererTable(JLabel.LEFT),
				rendererTable(JLabel.RIGHT),
				rendererTable(JLabel.LEFT),
				rendererTable(JLabel.RIGHT),
				rendererTable(JLabel.LEFT)
		};

		//Tableau des notes Hommes
		JTable return_table = new JTableUnmodifiable(
				donnees,
				entetes,
				null,
				new Font("calibri", Font.PLAIN, 15),
				20,
				new int[] {10,10,10,10,10,10},
				false,
				false,
				new Color(0,0,0,0),
				list_rend_notes
				);


		return new JTable(donnees, entetes);
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

	private JLabel reccupereDrapeauBiathlete() {
		//Requete sur le drapeau

		return new JLabel(scaleImage(new ImageIcon(getClass().getResource("/images/drapeau/icone/france.png")),30,30));
	}

}
