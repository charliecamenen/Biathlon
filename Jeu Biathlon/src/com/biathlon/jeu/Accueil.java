package com.biathlon.jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.biathlon.action.CreationCarriere;
import com.biathlon.action.Gestion;
import com.biathlon.action.InterfaceGraphique;

public class Accueil extends InterfaceGraphique{

	private JLabel label_titre;
	private JButton button_course;
	private JButton button_nouvelle_carriere;
	private JButton button_charger_carriere;
	private JButton button_quitter;
	private JButton button_paraletres;

	private JList liste_parties;
	private ImageIcon ico_bg_boutton_panel;

	private JPanel panel_content_course;
	private JPanel panel_content_nouvelle_carriere;
	private JPanel panel_content_charger_carriere;
	private JPanel panel_list_parties;
	private JPanel panel_quitter;
	private JPanel panel_parametres;
	private JPanel panel_content_quitter_parametres;

	public Accueil() {
		super("/images/background/novemesto.png");

		//Charge la liste des parties existanntes
		liste_parties = this.creerListeParties();
		
		
		//Element de la page
		label_titre = titreLabelStyle(new JLabel("Menu Principal"));
		label_titre.setLayout(new FlowLayout(FlowLayout.LEADING));
		label_titre.setForeground(Color.black);
		button_course = headerButtonStyle(new JButton("COURSE SIMPLE"));
		button_nouvelle_carriere =  headerButtonStyle(new JButton("NOUVELLE CARRIERE"));
		button_charger_carriere =  headerButtonStyle(new JButton("CHARGER CARRIERE"));
		button_quitter =  mediumButtonStyle(new JButton("Quitter"));
		button_paraletres = mediumButtonStyle(new JButton("Paramètres"));
		
		//panel principaux du borderLayout
		panel_content = panelSansBgStyle(new InterfaceGraphique("/images/accueil/bg_bouton_panel.png"));
		panel_footer = panelSansBgStyle(new JPanel());
		panel_header = panelSansBgStyle(new JPanel());
		panel_est = panelSansBgStyle(new JPanel());
		panel_ouest = panelSansBgStyle(new JPanel());
		
		//panel_list_parties= panelSansBgStyle(new JPanel());
		panel_content_course = panelSansBgStyle(new InterfaceGraphique("/images/accueil/bg_bouton.png"));
		panel_content_nouvelle_carriere = panelSansBgStyle(new InterfaceGraphique("/images/accueil/bg_bouton.png"));
		panel_content_charger_carriere =  panelSansBgStyle(new InterfaceGraphique("/images/accueil/bg_bouton.png"));
		panel_list_parties = new JPanel();
		panel_content_quitter_parametres = panelSansBgStyle(new JPanel());
		panel_quitter = panelSansBgStyle(new InterfaceGraphique("/images/accueil/bg_bouton_petit.png"));
		panel_parametres  = panelSansBgStyle(new InterfaceGraphique("/images/accueil/bg_bouton_petit.png"));
		
		this.setDimensionOfBorderElement(290, 100, 530, 530);
		
		//panel_list_parties.add(this.listStyle(liste_parties));	
		panel_content_course.add(button_course);
		panel_content_nouvelle_carriere.add(button_nouvelle_carriere);
		panel_content_charger_carriere.add(button_charger_carriere);
		panel_quitter.add(button_quitter);
		panel_parametres.add(button_paraletres);
		panel_list_parties.add(listStyle(liste_parties));
		panel_content_quitter_parametres.setLayout(new GridLayout(1,2));
		panel_content_quitter_parametres.add(panel_quitter);
		panel_content_quitter_parametres.add(panel_parametres);
		
		this.setLayout(new FlowLayout(FlowLayout.LEADING, 30,10));
		
		panel_content.setLayout(new GridBagLayout());
		panel_content.add(label_titre,gbc(0,0,1,1,0,0,null,0));
		panel_content.add(panel_content_course,gbc(1,0,1,1,0,0,null,0));
		panel_content.add(panel_content_nouvelle_carriere,gbc(2,0,1,1,0,0,null,0));
		panel_content.add(panel_content_charger_carriere,gbc(3,0,1,1,0,0,null,0));
		panel_content.add(panel_list_parties,gbc(4,0,1,1,0,0,null,0));
		panel_content.add(panel_content_quitter_parametres,gbc(5,0,1,1,0,0,null,0));
		//panel_content.setBackground(color_bg);

		//Ajoute le pied de page

		//panel_footer.setLayout(new FlowLayout(FlowLayout.LEFT));
		//panel_footer.add(button_quitter);
	
		this.afficheBorderElement();

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

		
		button_course.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Main.joueur = new Joueur("course");
				ChoixCourse choix_course = new ChoixCourse();
				actuFenetre(choix_course);
			}
		});

		button_quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Main.fenetre.dispose();
			}
		});


	}




	public JList creerListeParties() {

		//Vecteur a remplir lors de la requette
		Vector element_list = new Vector();

		// Résultat de la rquete sur les parties existantes
		ResultSet resultat = Main.database.requete("Select * from Joueur");
		element_list.add("Carriere 2               TESTTTT");
		element_list.add("Carriere 2               TESTTTT");
		element_list.add("Carriere 2               TESTTTT");
		element_list.add("Carriere 2               TESTTTT");
		try {
			while(resultat.next()) {
				//Remplire le vecteur de partie a charger
				element_list.add(resultat.getString("nom_joueur") + " - " + resultat.getInt("annee") + " ( M.Fourcade - C.Aymonier ) " + "      16/11/2020");

			}
		} catch (SQLException e) {e.printStackTrace();}

		return new JList(element_list);

	}


}
