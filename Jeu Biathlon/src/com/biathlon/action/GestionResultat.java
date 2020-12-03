package com.biathlon.action;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class GestionResultat extends InterfaceGraphique {

//	private JPanel panel_content;
	private JPanel panel_filtre;

	private JComboBox combobox_annee;
	private JComboBox combobox_sexe;
	private JComboBox combobox_competition;
	private JComboBox combobox_type_course;

	private JTable tableau;

	public GestionResultat() {
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
		combobox_competition = comboboxStyle(new JComboBox(new Object[] {"Hochfilzen","Ostersund"}));
		combobox_sexe = comboboxStyle(new JComboBox(new Object[] {"Homme","Femme","Nation"}));
		combobox_type_course = comboboxStyle(new JComboBox(new Object[] {"Général" , "Sprint","Poursuite","Mass Start",  "Individuel" , "Relai", "Relai Mixte", "Relain Mixte Simple" }));

	}

	protected void afficheElement() {
		// TODO Auto-generated method stub
		
		
		panel_filtre.add(combobox_annee);
		panel_filtre.add(combobox_sexe);
		panel_filtre.add(combobox_competition);
		panel_filtre.add(combobox_type_course);


		
		tableau = this.genererTableau();
		
		panel_content.setLayout(new BorderLayout());
		panel_content.add(panel_filtre,BorderLayout.NORTH);
		panel_content.add(new JScrollPane(tableau),BorderLayout.CENTER);
		
		//Affiche les element du boerder latour global
		super.afficheBorderElement();
		
	}
	
	private JTable genererTableau() {
		Object[][] donnees = {
				{"1.", "J.BOE", ' ',1 ,"35'23\""},
				{"2.", "M.FOURCADE", ' ',0, "25\""},
				{"3.", "Q.FILLON MAILLET", ' ',3, "44\""},
				{"2.", "M.FOURCADE", ' ',0, "25\""},
				{"3.", "Q.FILLON MAILLET", ' ',3, "44\""},
				{"2.", "M.FOURCADE", ' ',0, "25\""},
				{"3.", "Q.FILLON MAILLET", ' ',3, "44\""},
				{"2.", "M.FOURCADE", ' ',0, "25\""},
				{"3.", "Q.FILLON MAILLET", ' ',3, "44\""},
				{"2.", "M.FOURCADE", ' ',0, "25\""},
				{"3.", "Q.FILLON MAILLET", ' ',3, "44\""},
				{"2.", "M.FOURCADE", ' ',0, "25\""},
				{"3.", "Q.FILLON MAILLET", ' ',3, "44\""},
				{"2.", "M.FOURCADE", ' ',0, "25\""},
				{"3.", "Q.FILLON MAILLET", ' ',3, "44\""},
				{"2.", "M.FOURCADE", ' ',0, "25\""},
				{"3.", "Q.FILLON MAILLET", ' ',3, "44\""},
				
		};

		String[] entetes = {"Cls", "Biathlete","Nat","Fautes", "Temps"};
		

		DefaultTableCellRenderer[] list_rend = new DefaultTableCellRenderer[] {
				rendererTable(JLabel.CENTER),
				rendererTable(JLabel.LEFT),
				rendererTable(JLabel.CENTER),
				rendererTable(JLabel.CENTER),
				rendererTable(JLabel.RIGHT)
			};
		
		JTable return_table = new JTableUnmodifiable(
				donnees,
				entetes,
				new Font("calibri", Font.BOLD, 25),
				new Font("calibri", Font.PLAIN, 25),
				40, 
				new int[] {10,900,10,10,30},
				false,
				true,
				null,
				list_rend);
		
		return return_table;
	}

	
}
