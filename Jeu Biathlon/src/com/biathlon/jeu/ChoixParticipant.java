package com.biathlon.jeu;

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
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.biathlon.action.InterfaceGraphique;

public class ChoixParticipant extends InterfaceGraphique {

	JButton button_valide;
	
	JPanel biathlete_choix;

	JComboBox combobox_pays;
	JComboBox combobox_biathlete;
	JCheckBox checkbox_biathlete;
	
	JLabel label_titre;
	JLabel label_instruction;
	
	JLabel label_biathlete_selectionne;
	JLabel label_choix_equipe;
	
	//Pour les courses en equipe
	public ChoixParticipant(ArrayList<String> list_equipe, String sexe) {
		super();
		
		//Structure de la page
		this.setLayout(new GridLayout(5,1,0,5));

		//TITRE DE LA PAGE
		label_titre = new JLabel("CHOIX DE L'EQUIPE");
		label_titre.setFont(new Font("calibri", Font.PLAIN, 38));
		label_titre.setHorizontalAlignment(JLabel.CENTER);
		label_titre.setForeground(Color.white);

		//label CONSTRUIR EQUIPE
		label_choix_equipe = new JLabel("CONSTITUEZ UNE EQUIPE : ");
		label_choix_equipe.setFont(new Font("calibri", Font.PLAIN, 26));
		label_choix_equipe.setHorizontalAlignment(JLabel.LEFT);
		label_choix_equipe.setForeground(Color.white);

		
		
		//Listes des equipe possible
		//On créé un vecteur pour faciliter l'insertion dans le combobox
		Vector vecteur_pays = new Vector();
		
		//Boucle sur la liste des equipes
		for (int i = 0;i < list_equipe.size() ;i++) {
			
			//On rempli le vecteur
			vecteur_pays.add(list_equipe.get(i));
		}		
		
		//ON rempli le combobox avec le vecteur
		combobox_pays = new JComboBox(vecteur_pays);
		
		
		
		//Choix des membre de lequipe grace a des checkbox (dependant du combobox_pays)
		biathlete_choix = new JPanel();
				
		//Partie choix de l'equipe
		JPanel equipe_choix = new JPanel();
		biathlete_choix.setLayout(new GridLayout(10,1));
		equipe_choix.setLayout(new GridLayout(1,2));
		
		//LIste deroulante de l'equipe
		equipe_choix.add(combobox_pays);
		
		//Check box des membre de l'equipe
		equipe_choix.add(biathlete_choix);
		
		//Couleur du background
		equipe_choix.setBackground(new Color(40,40,40));
		


		//Label VOTRE EQUIPE
		label_biathlete_selectionne = new JLabel("VOTRE EQUIPE : ");
		label_biathlete_selectionne.setFont(new Font("calibri", Font.PLAIN, 26));
		label_biathlete_selectionne.setHorizontalAlignment(JLabel.LEFT);
		label_biathlete_selectionne.setForeground(Color.white);

		//Tableau des selection
		/*PAS ENCORE CREE*/

		
		//Bouton validé
		button_valide = new JButton("VALIDER");
		button_valide.setPreferredSize(new Dimension(350, 50));	
		button_valide.setFont(new Font("calibri", Font.PLAIN, 20));
		button_valide.setBackground(Color.white);

		//Couleur de fonc (rajouter un drapeau)
		this.setBackground(new Color(40,40,40));

		//Titre 
		this.add(label_titre);

		//Label Choix de l'equipe
		this.add(label_choix_equipe);
		
		//Choix de l'equipe et des membres
		this.add(equipe_choix);

		//Label biathlete choisi pour l'equipe
		this.add(label_biathlete_selectionne);

		//Boutton validé
		this.add(button_valide);

		//BOUTTON VALIDE
		button_valide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				//On instancie une liste de biathlete(Elle sera rempli apres grace aux checkbox !!!)
				ArrayList<Integer> list_biathlete = new ArrayList<>();
				
				//ON ajoute cette liste au joueur
				Main.joueur.mesBiathleteEquipe(list_biathlete);
				
				//Ouverture de la vraie fenetre
				Main.lancerJeu();
				
				//on démarre la course
				Main.joueur.demarrerCourseSimple();
				
			}
		});
		
		//ACtion sur la liste deroulante des equipes
		combobox_pays.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				//On supprime les biathletes deja choisi
				biathlete_choix.removeAll();
				//System.out.println(combobox_pays.getSelectedItem().toString());
				
				//Resultat de la requete sur les biathlete
				ResultSet resultat ;
				//Lorsque le sexe est mixte
				if ( sexe == "M") {
					
					resultat = Main.database.requete(""
							+ "SELECT libelle_biathlete from biathletes "
							+ "join equipes on (biathletes.id_equipe = equipes.id_equipe) "
							+ "join pays on (pays.id_pays = equipes.id_pays) "
							+ "where pays.libelle_pays = '" + combobox_pays.getSelectedItem().toString() + "'");
				}else { //Sinon on filtre par sexe
					
					resultat = Main.database.requete(""
							+ "SELECT libelle_biathlete from biathletes "
							+ "join equipes on (biathletes.id_equipe = equipes.id_equipe) "
							+ "join pays on (pays.id_pays = equipes.id_pays) "
							+ "where pays.libelle_pays = '" + combobox_pays.getSelectedItem().toString() + "'" + " and biathletes.sexe_biathlete = '" + sexe + "'");
				}
				
				try {
					//On rempli le choix biathlete
					while(resultat.next()) {
						checkbox_biathlete = new JCheckBox(resultat.getString("libelle_biathlete"));
						biathlete_choix.add(checkbox_biathlete);
					}
				
				} catch (SQLException e) {e.printStackTrace();}
				
				//On ajoute la nouvelle liste de choix
				equipe_choix.add(biathlete_choix);
				
				//On met a jour le JPANEL
				updateUI();
			}
		});


	}
	
	
	
	
	//Pour les courses individuelles
	public ChoixParticipant(String sexe) {
		super();
		
		//Resultat de la requete
		ResultSet resultat ;
		
		//Structure de la page
		this.setLayout(new GridLayout(8,1,0,5));

		//TITRE DE LA PAGE
		label_titre = new JLabel("CHOIX DU BIATHLETE");
		label_titre.setFont(new Font("calibri", Font.PLAIN, 38));
		label_titre.setHorizontalAlignment(JLabel.CENTER);
		label_titre.setForeground(Color.white);

		
		//Filtres possible par equipe---------------------------------------------------------------
		//Requete non dynamique (elle prend juste les nationalité qui ont au moins 1 biathlete
		resultat = Main.database.requete("SELECT equipes.libelle_equipe FROM biathletes JOIN equipes on (equipes.id_equipe = biathletes.id_equipe) GROUP BY equipes.libelle_equipe ");
		//ON créé le vecteur des pays 
		Vector vecteur_pays = new Vector();
		
		//On rempli le vecteur de pays
		try {
			while(resultat.next()) {
				vecteur_pays.add(resultat.getString("libelle_equipe"));
			}
		} catch (SQLException e1) {e1.printStackTrace();}
		
		//ON rempli le combobox avec le vecteur
		combobox_pays = new JComboBox(vecteur_pays);
		//combobox_pays.setSelectedIndex(1); 
		System.out.println(combobox_pays.getSelectedItem().toString());
		//Requetes sur les biathlete du pays selectioné------------------------------------------------
		resultat = Main.database.requete("SELECT biathletes.libelle_biathlete from biathletes join equipes on equipes.id_equipe = biathletes.id_equipe WHERE biathletes.sexe_biathlete = '" + sexe + "' and libelle_equipe = '" + combobox_pays.getSelectedItem().toString() + "'");
		
		
		//LIste des bithlete passé dans un vecteur
		Vector vecteur_biathlete = new Vector();
		try {
			while(resultat.next()) {
				vecteur_biathlete.add(resultat.getString("libelle_biathlete"));
			}
		} catch (SQLException e1) {e1.printStackTrace();}
		
		//le vecteur est mit en paraetre de la list deroulante
		combobox_biathlete = new JComboBox(vecteur_biathlete);

		
		//Partie choix de l'equipe
		JPanel equipe_choix = new JPanel();
		//biathlete_choix.setLayout(new GridLayout(10,1));
		equipe_choix.setLayout(new GridLayout(1,2));
		//On rempli le JPanele avec les combobox
		equipe_choix.add(combobox_pays);
		equipe_choix.add(combobox_biathlete);
		
		//equipe_choix.add(biathlete_choix);
		equipe_choix.setBackground(new Color(40,40,40));
		
		
		
		//Bouton validé
		button_valide = new JButton("VALIDER");
		button_valide.setPreferredSize(new Dimension(350, 50));	
		button_valide.setFont(new Font("calibri", Font.PLAIN, 20));
		button_valide.setBackground(Color.white);

		//Couleur de fonc (rajouter un drapeau)
		this.setBackground(new Color(40,40,40));

		
		//Titre 
		this.add(label_titre);

		this.add(equipe_choix);

		//Boutton validé
		this.add(button_valide);

		//BOUTTON VALIDE
		button_valide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.out.println(combobox_biathlete.getSelectedItem().toString());
				
				ResultSet resultat = Main.database.requete("select id_biathlete from biathletes where libelle_biathlete = '" + combobox_biathlete.getSelectedItem().toString() + "'");
				//On lance la course
				
				try {
					
					while(resultat.next()) {
						Main.joueur.monBiathleteIndiv(resultat.getInt("id_biathlete"));
					}
					
					//on démarre la course
					Main.joueur.demarrerCourseSimple();
					
				} catch (SQLException e) {e.printStackTrace();}
				
				//DEMARRAGE DE LA COURSE LANCEMENT DE LA VRAI FENETRE
				Main.lancerJeu();
			}
		});

		combobox_pays.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//On vide la liste de biathlete
				equipe_choix.remove(combobox_biathlete);
				//System.out.println(combobox_pays.getSelectedItem().toString());
				
				ResultSet resultat = Main.database.requete("SELECT biathletes.libelle_biathlete from biathletes join equipes on equipes.id_equipe = biathletes.id_equipe WHERE biathletes.sexe_biathlete = '" + sexe + "' and libelle_equipe = '" + combobox_pays.getSelectedItem().toString() + "'");
				//LIste des bithlete passé dans un vecteur
				Vector vecteur_biathlete = new Vector();
				try {
					while(resultat.next()) {
						vecteur_biathlete.add(resultat.getString("libelle_biathlete"));
					}
				} catch (SQLException e1) {e1.printStackTrace();}
				//le vecteur est mit en paraetre de la list deroulante
				combobox_biathlete = new JComboBox(vecteur_biathlete);
				
				//On applique le nouveau filtre
				equipe_choix.add(combobox_biathlete);
				updateUI();
			}
		});
	}
}
