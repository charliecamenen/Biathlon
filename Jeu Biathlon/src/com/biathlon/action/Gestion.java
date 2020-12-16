package com.biathlon.action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import com.biathlon.jeu.Temps;

public class Gestion extends InterfaceGraphique implements Runnable {

	//Bouton du header
	private JButton button_resultat;
	private JButton button_classement;
	private JButton button_gestion;
	private JButton button_calendrier;

	//Panel de contenue
	private GestionResultat gestion_resultat;
	private GestionAccueil gestion_accueil;
	private GestionCalendrier gestion_calendrier;
	private GestionClassement gestion_classement;

	public Gestion() {
		super();
		//Créaion du panel du header
		//panel_header = new InterfaceGraphique("/images/gestion/header_bg.png");
		panel_header = new InterfaceGraphique("");
		panel_header.setBackground(new Color( 230, 180, 120 ));

		//Création des boutons de header
		button_classement = this.headerButtonStyle(new JButton("CLASSEMENT"));
		button_calendrier = this.headerButtonStyle(new JButton("CALENDRIER"));
		button_resultat = this.headerButtonStyle(new JButton("RESULTAT"));
		button_gestion = this.headerButtonStyle(new JButton("GESTION"));
		button_classement.setForeground(Color.black);
		button_calendrier.setForeground(Color.black);
		button_resultat.setForeground(Color.black);
		button_gestion.setForeground(Color.black);

		panel_header.setLayout(new GridLayout());
		//On ajoute les boutons dans le header
		panel_header.add(new JLabel(scaleImage(ico_logo,250,250)));
		panel_header.add(button_gestion);
		panel_header.add(button_classement);
		panel_header.add(button_calendrier);	
		panel_header.add(button_resultat);	
		this.setDimensionOfBorderElement(100, 0, 0, 0);

		//Affichage d'une page par défaut
		panel_content = new GestionAccueil();
		this.afficheBorderElement();


		button_gestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				gestion_accueil = new GestionAccueil();
				updateInterface(getClassInstance(),gestion_accueil,1);
			}
		}); 

		button_classement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				gestion_classement = new GestionClassement();
				updateInterface(getClassInstance(),gestion_classement,1);
			}
		});

		button_resultat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				gestion_resultat = new GestionResultat();
				updateInterface(getClassInstance(),gestion_resultat,1);
			}
		});

		button_calendrier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				gestion_calendrier = new GestionCalendrier();
				updateInterface(getClassInstance(),gestion_calendrier,1);
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

}
