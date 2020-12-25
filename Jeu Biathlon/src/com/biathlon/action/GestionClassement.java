package com.biathlon.action;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.biathlon.jeu.Main;

public class GestionClassement extends InterfaceGraphique {

	//	private JPanel panel_content;
	private JPanel panel_filtre;

	private JComboBox combobox_annee;
	private JComboBox combobox_sexe;
	private JComboBox combobox_type_course;

	private JTable tableau;

	public GestionClassement() {
		super("/images/background/novemesto.png");

		//On instancie les objets
		this.creerObjet();
		//On ajoute les objets a leur panel
		this.afficheElement();
	}

	private void creerObjet() {
		// TODO Auto-generated method stub

		//panel principaux du borderLayout
		panel_content = panelSansBgStyle(new InterfaceGraphique(""));
		panel_footer = panelSansBgStyle(new JPanel());
		panel_header = panelSansBgStyle(new JPanel());
		panel_est = panelSansBgStyle(new JPanel());
		panel_ouest = panelSansBgStyle(new JPanel());

		this.setDimensionOfBorderElement(50, 100, 150, 150);

		//Panel des filtres pour le tableau
		panel_filtre = panelSansBgStyle(new JPanel());
		panel_filtre.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel_filtre.setPreferredSize(new Dimension(0,50));
		//Combobox de filtre
		combobox_annee = comboboxStyle(new JComboBox(new Object[] {"2020","2021","2022"}));
		combobox_sexe = comboboxStyle(new JComboBox(new Object[] {"Homme","Femme","Nation"}));
		combobox_type_course = comboboxStyle(new JComboBox(new Object[] {"Général" , "Sprint","Poursuite","Mass Start",  "Individuel" , "Relai", "Relai Mixte", "Relain Mixte Simple" }));

	}

	protected void afficheElement() {
		// TODO Auto-generated method stub


		panel_filtre.add(combobox_annee);
		panel_filtre.add(combobox_sexe);
		panel_filtre.add(combobox_type_course);


		tableau = this.genererTableau();

		panel_content.setLayout(new BorderLayout());
		panel_content.add(panel_filtre,BorderLayout.NORTH);
		panel_content.add(new JScrollPane(tableau),BorderLayout.CENTER);

		//Affiche les element du boerder latour global
		super.afficheBorderElement();

	}

	private JTable genererTableau() {
		DefaultTableCellRenderer[] list_rend = new DefaultTableCellRenderer[] {
				rendererTable(JLabel.CENTER),
				rendererTable(JLabel.LEFT),
				rendererTable(JLabel.CENTER),
				rendererTable(JLabel.RIGHT)
		};

		ResultSet select_classement = Main.database.requete(	
				Main.database.selectClassement(Main.joueur.getAnnee(), 0, "H", Main.joueur.getId_course_courrante(), 0, false)
		);
		
		ArrayList<Object[]> donnees = new ArrayList<>();
		int classement = 1;
		//Parcour les biathletes pour réccupérer l'ID
		try {while(select_classement.next()) {
			donnees.add(new Object[]{
				" " + classement,
				" " + select_classement.getString("nom_biathlete") +" "+ select_classement.getString("prenom_biathlete"),
				" ",
				" "+ select_classement.getInt("pts")}
			);
			classement +=1;

		}} catch (SQLException ex) {ex.printStackTrace();}
		

		String[] entetes = {"Cls", "Biathlete","Nat", "Pts"};		

		//Tableau des notes Hommes
		JTable return_table = new JTable(new MonTableModel(
				donnees,
				entetes,
				null
				));

		return_table.setDefaultRenderer(Object.class, new MonTableRenderer(
				new Color[] {color_tableau_bg_second,color_tableau_bg,color_tableau_bg,color_tableau_bg},//couleur des colonnes 
				new Color[] {Color.BLACK,Color.WHITE,Color.WHITE,Color.WHITE},//couleur du texte
				new int[] {10,1000,100,50}, //largeur des colonnes
				new int[] {JLabel.CENTER,JLabel.LEFT,JLabel.CENTER,JLabel.RIGHT}, //alignement des colonnes
				new Font[] {new Font("calibri", Font.PLAIN, 25), 
						new Font("calibri", Font.PLAIN, 25),
						new Font("calibri", Font.PLAIN, 25), 
						new Font("calibri", Font.PLAIN, 25)
				},//Font des lignes
				true, //lignes horizontales
				false, // lignes verticales
				40, //Largeur des lignes
				null, // font du header
				null, // couleur de la grid
				color_tableau_bg, // couleur du GB grobal
				false,//focus
				false //select
				));

		return return_table;
	}


}
