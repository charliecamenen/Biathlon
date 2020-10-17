package com.biathlon.jeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Accueil extends JPanel{
	JButton button_course;
	JButton button_carriere;
	JLabel titre;
	JLabel instruction;
	JLabel vide;

	public Accueil() {
		super();

		titre = new JLabel("MENU PRINCIPAL");
		titre.setFont(new Font("calibri", Font.PLAIN, 38));
		titre.setForeground(Color.white);

		vide = new JLabel("                                          ");
		vide.setFont(new Font("calibri", Font.PLAIN, 15));
		vide.setForeground(Color.white);	

		button_course = new JButton("COURSE SIMPLE");
		button_course.setPreferredSize(new Dimension(350, 50));
		button_course.setFont(new Font("calibri", Font.PLAIN, 20));
		button_course.setBackground(Color.white);

		button_carriere = new JButton("CARRIERE");
		button_carriere.setPreferredSize(new Dimension(350, 50));	
		button_carriere.setFont(new Font("calibri", Font.PLAIN, 20));
		button_carriere.setBackground(Color.white);

		instruction = new JLabel("Choisissez un mode de jeu");
		instruction.setFont(new Font("calibri", Font.PLAIN, 14));
		instruction.setForeground(Color.white);

		this.setBackground(new Color(40,40,40));
		this.add(titre);
		this.add(vide);
		this.add(button_course);
		this.add(button_carriere);
		this.add(instruction);

		button_course.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Main.typePartieFenetre(1);
			}
		});
		
		button_carriere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Main.typePartieFenetre(2);
			}
		});
	}

}
