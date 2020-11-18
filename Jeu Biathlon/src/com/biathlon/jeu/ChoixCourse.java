package com.biathlon.jeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.biathlon.action.InterfaceGraphique;

public class ChoixCourse extends InterfaceGraphique {
	
	JButton button_sprint;
	JButton button_poursuite;
	JButton button_massstart;
	JButton button_individuel;
	JButton button_relai;
	JButton button_mixte;
	JButton button_mixtesimple;
	JComboBox combobox_sexe;
	JComboBox combobox_compet;
	
	JLabel label_titre;
	JLabel label_instruction;
	JLabel label_compet;
	JLabel label_sexe;
	JLabel label_compet_descr;
	JLabel label_course_mixte;
	JLabel label_course_genree;

	public ChoixCourse() {
		super();
		//Structure de la page
		this.setLayout(new GridLayout(15,1,0,5));
		
		//Boutons du choix de type de courses
		label_titre = new JLabel("CHOIX DE LA COURSE");
		label_titre.setFont(new Font("calibri", Font.PLAIN, 38));
		label_titre.setHorizontalAlignment(JLabel.CENTER);
		label_titre.setForeground(Color.white);

		button_sprint = new JButton("SPRINT");
		button_sprint.setPreferredSize(new Dimension(350, 50));	
		button_sprint.setFont(new Font("calibri", Font.PLAIN, 20));
		button_sprint.setBackground(Color.white);
		
		button_poursuite = new JButton("SPRINT + POURSUITE");
		button_poursuite.setPreferredSize(new Dimension(350, 50));
		button_poursuite.setFont(new Font("calibri", Font.PLAIN, 20));
		button_poursuite.setBackground(Color.white);

		button_massstart = new JButton("MASS START");
		button_massstart.setPreferredSize(new Dimension(350, 50));	
		button_massstart.setFont(new Font("calibri", Font.PLAIN, 20));
		button_massstart.setBackground(Color.white);
		
		button_individuel = new JButton("INDIVIDUEL");
		button_individuel.setPreferredSize(new Dimension(350, 50));
		button_individuel.setFont(new Font("calibri", Font.PLAIN, 20));
		button_individuel.setBackground(Color.white);

		button_mixte = new JButton("RELAIS MIXTE");
		button_mixte.setPreferredSize(new Dimension(350, 50));
		button_mixte.setFont(new Font("calibri", Font.PLAIN, 20));
		button_mixte.setBackground(Color.white);

		button_mixtesimple = new JButton("RELAIS MIXTE SIMPLE");
		button_mixtesimple.setPreferredSize(new Dimension(350, 50));	
		button_mixtesimple.setFont(new Font("calibri", Font.PLAIN, 20));
		button_mixtesimple.setBackground(Color.white);
		
		button_relai = new JButton("RELAIS");
		button_relai.setPreferredSize(new Dimension(350, 50));	
		button_relai.setFont(new Font("calibri", Font.PLAIN, 20));
		button_relai.setBackground(Color.white);

		label_instruction = new JLabel("Choisissez un mode de jeu");
		label_instruction.setFont(new Font("calibri", Font.PLAIN, 14));
		label_instruction.setForeground(Color.white);

		//Liste de choix du sexe
		combobox_sexe = new JComboBox(new Object[] {"Homme","Femme"});
		
		label_sexe = new JLabel("Choix du genre : ");
		label_sexe.setFont(new Font("calibri", Font.PLAIN, 22));
		label_sexe.setHorizontalAlignment(JLabel.RIGHT);
		label_sexe.setForeground(Color.white);
		
		JPanel sexe_choix = new JPanel();
		sexe_choix.setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
		sexe_choix.add(label_sexe);
		sexe_choix.add(combobox_sexe);
		sexe_choix.setBackground(new Color(40,40,40));
		
		
		//Vecteur des différentes competitions
		Vector vecteur_compet = new Vector();
		//Toutes les compatitions
		ResultSet resultat = Main.database.requete("SELECT * FROM competitions");
		
		try {
			//Parcour de la requete
			while(resultat.next()) {
				//On rempli le vecteur
				vecteur_compet.add(resultat.getString("lieu_compet"));
			}
			
		} catch (SQLException e) {e.printStackTrace();}		
		
		//Liste des choix de competition
		combobox_compet = new JComboBox(vecteur_compet);
		
		label_compet = new JLabel("Choix du lieu : ");
		label_compet.setFont(new Font("calibri", Font.PLAIN, 22));
		label_compet.setHorizontalAlignment(JLabel.RIGHT);
		label_compet.setForeground(Color.white);
		
		JPanel compet_choix = new JPanel();
		compet_choix.setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
		compet_choix.add(label_compet);
		compet_choix.add(combobox_compet);
		compet_choix.setBackground(new Color(40,40,40));
		
		label_compet_descr  = new JLabel("Parcour difficile, pour les bons skieurs !");
		label_compet_descr.setFont(new Font("calibri", Font.PLAIN, 14));
		label_compet_descr.setForeground(Color.white);
		
		label_course_mixte = new JLabel("COURSES MIXTE : ");
		label_course_mixte.setFont(new Font("calibri", Font.PLAIN, 26));
		label_course_mixte.setHorizontalAlignment(JLabel.LEFT);
		label_course_mixte.setForeground(Color.white);
		
		label_course_genree = new JLabel("COURSES PAR GENRE : ");
		label_course_genree.setFont(new Font("calibri", Font.PLAIN, 26));
		label_course_genree.setHorizontalAlignment(JLabel.LEFT);
		label_course_genree.setForeground(Color.white);
		
		
		this.setBackground(new Color(40,40,40));
		
		//Titre 
		this.add(label_titre);
		//Liste des choix sexe/lieu
		this.add(sexe_choix);
		this.add(compet_choix);
		
		//Description du lieu de course
		this.add(label_compet_descr);
		
		//Course genrée label
		this.add(label_course_genree);
		//Boutons des courses genrée
		this.add(button_sprint);
		this.add(button_poursuite);
		this.add(button_massstart);
		this.add(button_individuel);
		this.add(button_relai);
		
		//courses mixte label
		this.add(label_course_mixte);
		//Boutons des courses mixtes
		this.add(button_mixtesimple);
		this.add(button_mixte);
		
		//Instruction pour les cons
		this.add(label_instruction);

		
		//BOUTTON INDIVIDUEL
		button_sprint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//Actualise la competition
				Main.joueur.setLieu(combobox_compet.getSelectedItem().toString());
				//Lance la fenetre de choix de participant
				Main.choixParticipantFenetre("i", combobox_sexe.getSelectedItem().toString());
				if(combobox_sexe.getSelectedItem().toString() == "Homme") {
					Main.joueur.setType_course(5);//rajouter le lieu
				}else {
					Main.joueur.setType_course(6);//rajouter le lieu
				}
			}
		});
		
		button_poursuite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Main.choixParticipantFenetre("i", combobox_sexe.getSelectedItem().toString());
				//Actualise la competition
				Main.joueur.setLieu(combobox_compet.getSelectedItem().toString());
				//On signale que la poursuite doit etre effectée apres le sprint
				Main.joueur.setFaire_poursuite(true);
				if(combobox_sexe.getSelectedItem().toString() == "Homme") {
					Main.joueur.setType_course(5);//rajouter le lieu
				}else {
					Main.joueur.setType_course(6);//rajouter le lieu
				}
			}
		});
		
		button_massstart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Main.choixParticipantFenetre("i", combobox_sexe.getSelectedItem().toString());
				//Actualise la competition
				Main.joueur.setLieu(combobox_compet.getSelectedItem().toString());
				if(combobox_sexe.getSelectedItem().toString() == "Homme") {
					Main.joueur.setType_course(9);//rajouter le lieu
				}else {
					Main.joueur.setType_course(10);//rajouter le lieu
				}
				
			}
		});
		
		button_individuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Main.choixParticipantFenetre("i", combobox_sexe.getSelectedItem().toString());
				//Actualise la competition
				Main.joueur.setLieu(combobox_compet.getSelectedItem().toString());
				if(combobox_sexe.getSelectedItem().toString() == "Homme") {
					Main.joueur.setType_course(11);//rajouter le lieu
				}else {
					Main.joueur.setType_course(12);//rajouter le lieu
				}
			}
		});
		
		//BOUTTON RELAI
		button_relai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//Actualise la competition
				Main.joueur.setLieu(combobox_compet.getSelectedItem().toString());
				
				//On fixe le sexe et du format de course choisi
				Main.choixParticipantFenetre("m", combobox_sexe.getSelectedItem().toString());
				
				if(combobox_sexe.getSelectedItem().toString() == "Homme") {
					Main.joueur.setType_course(3);//rajouter le lieu
				}else {
					Main.joueur.setType_course(4);//rajouter le lieu
				}
			}
		});
		
		button_mixtesimple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//Actualise la competition
				Main.joueur.setLieu(combobox_compet.getSelectedItem().toString());

				//On fixe le sexe et du format de course choisi
				Main.choixParticipantFenetre("m", "Mixte");
				
				//on fixe le type de course
				Main.joueur.setType_course(2);
			}
		});
		
		button_mixte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//Actualise la competition
				Main.joueur.setLieu(combobox_compet.getSelectedItem().toString());

				//On fixe le sexe et du format de course choisi
				Main.choixParticipantFenetre("m", "Mixte");
				
				//on fixe le type de course
				Main.joueur.setType_course(1);
			}
		});
	}


}
