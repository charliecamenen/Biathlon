package com.biathlon.action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;

import com.biathlon.jeu.Accueil;
import com.biathlon.jeu.Main;

public class ChargementPartie extends InterfaceGraphique {

	private JButton button_nouvelle_carriere;
	private JButton button_charger_carriere;
	private JButton button_retour;
	private JList liste_parties;
	private JPanel panel_footer;
	
	public ChargementPartie() {
		super();
		
		//Element de la page
		button_nouvelle_carriere =  headerButtonStyle(new JButton("NOUVELLE CARRIERE"));
		button_charger_carriere =  headerButtonStyle(new JButton("CHARGER CARRIERE"));
		button_retour =  mediumButtonStyle(new JButton("Retour"));
		panel_footer = new JPanel();

		//Positionnement de la page
		//this.setLayout(new GridLayout(6,1));
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
	    gbc.weightx = 1;
	    gbc.weighty = 1;
		//gbc.gridwidth = 2;
		//gbc.gridheight = 2;
	    //gbc.fill = GridBagConstraints.BOTH;
		//Ajoute a l'interface
		this.add(button_nouvelle_carriere, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
	    gbc.weightx = 1;
	    gbc.weighty = 1;
		this.add(button_charger_carriere, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
	    gbc.weightx = 1;
	    gbc.weighty = 1;
	    liste_parties = this.creerListeParties();
		this.add(this.listStyle(liste_parties),gbc);		
		
		//Ajoute le pied de page
		panel_footer.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel_footer.add(button_retour, gbc);
		panel_footer.setBackground(new Color(40,40,40));
		
		gbc.gridx = 0;
		gbc.gridy = 4;
	    gbc.weightx = 1;
	    gbc.weighty = 1;
		//Ajoute a l'interface
		this.add(panel_footer, gbc);
		
		button_nouvelle_carriere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				CreationCarriere creation_carriere = new CreationCarriere();
				actuFenetre(creation_carriere);
			}
		});
		
		button_charger_carriere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Gestion gestion = new Gestion();
				actuFenetre(gestion);
			}
		});
		
		button_retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Accueil acceuil = new Accueil();
				actuFenetre(acceuil);
			}
		});
		
	}
	
	public JList creerListeParties() {
		 
		//Vecteur a remplir lors de la requette
		Vector element_list = new Vector();
		 
		// Résultat de la rquete sur les parties existantes
		ResultSet resultat = Main.database.requete("Select * from Joueur");

		try {
			while(resultat.next()) {
				//Remplire le vecteur de partie a charger
				element_list.add(resultat.getString("nom_joueur") + " - " + resultat.getInt("annee") + " ( M.Fourcade - C.Aymonier ) " + "      16/11/2020");
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		return new JList(element_list);
		
	}

	
	
}
