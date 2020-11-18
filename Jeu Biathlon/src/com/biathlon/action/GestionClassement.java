package com.biathlon.action;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class GestionClassement extends InterfaceGraphique {

//	private JPanel panel_content;
	private JPanel panel_filtre;
	
	private JComboBox combobox_annee;
	private JComboBox combobox_sexe;
	private JComboBox combobox_type_course;
	
	private JTable tableau;

	public GestionClassement() {
		super();
		Color tableau_bg_color = new Color(32,56,100);
		Color tableau_txt_color = new Color(255,255,255);
		Color tableau_grid_color = new Color(255,255,255);
		Color combobox_bg_color = new Color(255,255,255);
		
		//panel_content = new JPanel();
		panel_filtre = new JPanel();
		
		//Combobox de filtre
		combobox_annee = comboboxStyle(new JComboBox(new Object[] {"2020","2021","2022"}));
		combobox_sexe = comboboxStyle(new JComboBox(new Object[] {"Homme","Femme","Nation"}));
		combobox_type_course = comboboxStyle(new JComboBox(new Object[] {"Général" , "Sprint","Poursuite","Mass Start",  "Individuel" , "Relai", "Relai Mixte", "Relain Mixte Simple" }));
		

		panel_filtre.add(combobox_annee);
		panel_filtre.add(combobox_sexe);
		panel_filtre.add(combobox_type_course);
		
		
		Object[][] donnees = {
				{"1", "J.BOE", ' ', 756},
				{"2", "M.FOURCADE", ' ', 720},
				{"3", "Q.FILLON MAILLET", ' ', 630},
				{"1", "J.BOE", ' ', 756},
				{"2", "M.FOURCADE", ' ', 720},
				{"3", "Q.FILLON MAILLET", ' ', 630},
				{"1", "J.BOE", ' ', 756},
				{"2", "M.FOURCADE", ' ', 720},
				{"3", "Q.FILLON MAILLET", ' ', 630},
				{"1", "J.BOE", ' ', 756},
				{"2", "M.FOURCADE", ' ', 720},
				{"3", "Q.FILLON MAILLET", ' ', 630},
				{"1", "J.BOE", ' ', 756},
				{"2", "M.FOURCADE", ' ', 720},
				{"3", "Q.FILLON MAILLET", ' ', 630},
				{"1", "J.BOE", ' ', 756},
				{"2", "M.FOURCADE", ' ', 720},
				{"3", "Q.FILLON MAILLET", ' ', 630},
				{"1", "J.BOE", ' ', 756},
				{"2", "M.FOURCADE", ' ', 720},
				{"3", "Q.FILLON MAILLET", ' ', 630},
				{"1", "J.BOE", ' ', 756},
				{"2", "M.FOURCADE", ' ', 720},
				{"3", "Q.FILLON MAILLET", ' ', 630},
				{"1", "J.BOE", ' ', 756},
				{"2", "M.FOURCADE", ' ', 720},
				{"3", "Q.FILLON MAILLET", ' ', 630},
				{"1", "J.BOE", ' ', 756},
				{"2", "M.FOURCADE", ' ', 720},
				{"3", "Q.FILLON MAILLET", ' ', 630},
				{"1", "J.BOE", ' ', 756},
				{"2", "M.FOURCADE", ' ', 720},
				{"3", "Q.FILLON MAILLET", ' ', 630},
				{"1", "J.BOE", ' ', 756},
				{"2", "M.FOURCADE", ' ', 720},
				{"3", "Q.FILLON MAILLET", ' ', 630},
		};

		String[] entetes = {"Classement", "Biathlete","Nat", "Points"};		
		tableau = new JTable(donnees, entetes);
		/*DefaultTableCellRenderer test = new DefaultTableCellRenderer();
		test.setFont(new Font("calibri", Font.BOLD, 20));
		test.setIcon(new ImageIcon(getClass().getResource("/images/drapeau/allemagne.png")));
		
	
		tableau.getColumnModel().getColumn(2).setCellRenderer(test);
		*/
		//Font de l'entete
		tableau.getTableHeader().setFont(new Font("calibri", Font.BOLD, 25));
		//Font du contenue
		tableau.setFont(new Font("calibri", Font.PLAIN, 20));
		//Couleur du texte
		tableau.setForeground(tableau_txt_color);
		
		//Hauteur des cellules
		tableau.setRowHeight(30);
		//Largeur de la colonne 0
		
		
		tableau.getColumnModel().getColumn(0).setWidth(20);
		//tableau.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		//tableau.setGridColor(new Color(34,42,53));
		tableau.setGridColor(tableau_grid_color);
		//tableau.setBackground(new Color(34,42,53));
		tableau.setBackground(tableau_bg_color);
		
		
		//	this.setBackground(tableau_bg_color);
		/*
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridy = 3;
		*/
		
		this.setLayout(new BorderLayout());
		this.add(panel_filtre,BorderLayout.NORTH);
		/*
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 15;
		gbc.gridy = 4;
		*/
		//panel_content.setLayout(new FlowLayout(FlowLayout.CENTER, 20 , 20));
		//panel_content.setLayout(new FlowLayout(FlowLayout.CENTER));
		//panel_content.add(new JScrollPane(tableau));
		this.add(new JScrollPane(tableau),BorderLayout.CENTER);
	}
	
	
}
