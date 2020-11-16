package com.biathlon.action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.biathlon.jeu.Main;

public class Gestion extends JPanel {

	private JButton button_resultat;
	private JButton button_classement;
	private JButton button_gestion;
	
	private JPanel panel_button;
	private JPanel panel_content;
	private JPanel panel_filtre;
	private JPanel panel_header;
	private JTable tableau;
	
	private JComboBox combobox_annee;
	private JComboBox combobox_sexe;
	private JComboBox combobox_type_course;
	
	private GridBagConstraints gbc;
	//Image image;


	public Gestion() {
		super();
		Color tableau_bg_color = new Color(32,56,100);
		Color tableau_txt_color = new Color(255,255,255);
		Color tableau_grid_color = new Color(255,255,255);
		Color combobox_bg_color = new Color(255,255,255);
		Font combobox_font = new Font("calibri", Font.BOLD, 20);
		
		//  image =(new javax.swing.ImageIcon(getClass().getResource("/img/background.jpg"))).getImage();
		panel_button = new JPanel();
		panel_content = new JPanel();
		panel_filtre = new JPanel();
		panel_header = new JPanel();
		button_gestion = new JButton("GESTION");
		button_classement = new JButton("CLASSEMENT");
		button_resultat = new JButton("CALENDRIER / RESULTAT");

		combobox_annee = new JComboBox(new Object[] {"2020","2021","2022"});
		combobox_sexe = new JComboBox(new Object[] {"Homme","Femme","Nation"});
		combobox_type_course = new JComboBox(new Object[] {"Général" , "Sprint","Poursuite","Mass Start",  "Individuel" , "Relai", "Relai Mixte", "Relain Mixte Simple" });
		
		combobox_annee.setBackground(combobox_bg_color);
		combobox_sexe.setBackground(combobox_bg_color);
		combobox_type_course.setBackground(combobox_bg_color);

		combobox_annee.setFont(combobox_font);
		combobox_sexe.setFont(combobox_font);
		combobox_type_course.setFont(combobox_font);
		
		combobox_annee.setPreferredSize(new Dimension(200, 35));
		combobox_sexe.setPreferredSize(new Dimension(200, 35));
		combobox_type_course.setPreferredSize(new Dimension(200, 35));
		//panel_filtre.setLayout(new FlowLayout(FlowLayout.LEFT, 20 , 20));
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
		
		
		DefaultTableCellRenderer test = new DefaultTableCellRenderer();
		test.setFont(new Font("calibri", Font.BOLD, 20));
		test.setIcon(new ImageIcon(getClass().getResource("/images/drapeau/allemagne.png")));
		
	
		
		tableau.getColumnModel().getColumn(2).setCellRenderer(test);
		
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
		//Container mainContainer = this.getContentPain();
		//mainContainer.setLayout(new BorderLayout(8,6));
		//mainContainer.setBackground(Color.YELLOW);
		this.setLayout(new GridBagLayout());
		/*gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridy = 0;
		*/
		panel_button.add(button_gestion);
		/*
		gbc.gridx = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridy = 0;*/
		panel_button.add(button_classement);
		/*
		gbc.gridx = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridy = 0;*/
		panel_button.add(button_resultat);
		
	//	panel_button.setLayout(new FlowLayout(2));
		
		
		//gbc.fill = GridBagConstraints.BOTH;

		/*
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 3;
		gbc.gridy = 0;
		*/
		panel_header.add(panel_button, BorderLayout.CENTER);
	//	this.setBackground(tableau_bg_color);
		/*
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridy = 3;
		*/
		panel_header.add(panel_filtre,BorderLayout.SOUTH);
		
		this.add(panel_header,BorderLayout.NORTH);
		/*
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 15;
		gbc.gridy = 4;
		*/
		panel_content.setLayout(new FlowLayout(FlowLayout.CENTER, 20 , 20));
		//panel_content.setLayout(new FlowLayout(FlowLayout.CENTER));
		//panel_content.add(new JScrollPane(tableau));
		this.add(new JScrollPane(tableau),BorderLayout.CENTER);
		/*
		button_course.setPreferredSize(new Dimension(350, 50));
		button_course.setFont(new Font("calibri", Font.PLAIN, 20));
		button_course.setBackground(Color.white);

		instruction = new JLabel("Choisissez un mode de jeu");
		instruction.setFont(new Font("calibri", Font.PLAIN, 14));
		instruction.setForeground(Color.white);

		 */
		
		button_gestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				panel_content.removeAll();
			}
		}); 
		
		
		button_classement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				panel_content.removeAll();
				Object[][] donnees = {
						{"Johnathan", "Sykes", Color.red, true},
						{"Nicolas", "Van de Kampf", Color.black, true},
						{"Damien", "Cuthbert", Color.cyan, true},
						{"Corinne", "Valance", Color.blue, false},
						{"Emilie", "Schrödinger", Color.magenta, false},
						{"Delphine", "Duke", Color.yellow, false},
						{"Eric", "Trump", Color.pink, true},
						{"Johnathan", "Sykes", Color.red, true},
						{"Nicolas", "Van de Kampf", Color.black, true},
						{"Damien", "Cuthbert", Color.cyan, true},
						{"Corinne", "Valance", Color.blue, false},
						{"Emilie", "Schrödinger", Color.magenta, false},
						{"Delphine", "Duke", Color.yellow, false},
						{"Eric", "Trump", Color.pink, true},
						{"Johnathan", "Sykes", Color.red, true},
						{"Nicolas", "Van de Kampf", Color.black, true},
						{"Damien", "Cuthbert", Color.cyan, true},
						{"Corinne", "Valance", Color.blue, false},
						{"Emilie", "Schrödinger", Color.magenta, false},
						{"Delphine", "Duke", Color.yellow, false},
						{"Eric", "Trump", Color.pink, true},
						{"Johnathan", "Sykes", Color.red, true},
						{"Nicolas", "Van de Kampf", Color.black, true},
						{"Damien", "Cuthbert", Color.cyan, true},
						{"Corinne", "Valance", Color.blue, false},
						{"Emilie", "Schrödinger", Color.magenta, false},
						{"Delphine", "Duke", Color.yellow, false},
						{"Eric", "Trump", Color.pink, true},
						{"Johnathan", "Sykes", Color.red, true},
						{"Nicolas", "Van de Kampf", Color.black, true},
						{"Damien", "Cuthbert", Color.cyan, true},
						{"Corinne", "Valance", Color.blue, false},
						{"Emilie", "Schrödinger", Color.magenta, false},
						{"Delphine", "Duke", Color.yellow, false},
						{"Eric", "Trump", Color.pink, true},
				};

				String[] entetes = {"Prénom", "Nom", "Couleur favorite", "Homme"};

				tableau = new JTable(donnees, entetes);
				panel_content.add(new JScrollPane(tableau), gbc);
				//Main.gestion.getPanel_content().removeAll();
				//Main.gestion.getPanel_content().add(new JScrollPane(tableau), gbc);
			}
		});

		button_resultat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				panel_content.removeAll();
			}
		});
	}


	public JButton getButton_resultat() {
		return button_resultat;
	}


	public void setButton_resultat(JButton button_resultat) {
		this.button_resultat = button_resultat;
	}


	public JButton getButton_classement() {
		return button_classement;
	}


	public void setButton_classement(JButton button_classement) {
		this.button_classement = button_classement;
	}


	public JButton getButton_gestion() {
		return button_gestion;
	}


	public void setButton_gestion(JButton button_gestion) {
		this.button_gestion = button_gestion;
	}


	public JPanel getPanel_button() {
		return panel_button;
	}


	public void setPanel_button(JPanel panel_button) {
		this.panel_button = panel_button;
	}


	public JPanel getPanel_content() {
		return panel_content;
	}


	public void setPanel_content(JPanel panel_content) {
		this.panel_content = panel_content;
	}

	/* public void paintComponent(Graphics g)
	   {
	     g.drawImage (image, 0, 0, null); // elle doit etre avant
	     super.paintComponent(g); // lui il s'occupe de redessiner les composant enfant.
	   }*/
	
	
	
}
