package com.biathlon.action;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.biathlon.action.InterfaceGraphique;

public class JPanelJauge extends InterfaceGraphique{

	//Image de la jauge pleine
	ImageIcon ico_image_jauge;
	Image img_image_jauge;
	//Image de la jauge vide
	ImageIcon ico_image_vide;
	Image img_image_vide;
	//Pourcentage de remplissage de la barre
	int pourcentage_jauge;
	
	public JPanelJauge(String URL, String type) {
		super("");
		//On créé l'image de jauge
		this.ico_image_jauge = new ImageIcon(getClass().getResource(URL));
		this.img_image_jauge = ico_image_jauge.getImage();
		
		//On créé l'image vide
		this.ico_image_vide = new ImageIcon(getClass().getResource("/images/gestion/jauge_vide.png"));
		this.img_image_vide = ico_image_vide.getImage();
		
		//On initialise a 100
		this.pourcentage_jauge = this.calculPourcentage(type);
	}
	
	private int calculPourcentage(String type) {
		//Faire une requete pour récuperer la forme de mon biathlete (ou la fatigue )
		//ICI C'EST INVERSé 
		//SOIT ON MET 100 - POURCENTAGE SOIT ON REMODIFIE LES FONCTIONS
		if (type == "forme") {
			return (int) 17;
		}else {
			return (int) 90;
		}
	}

	protected void paintComponent(Graphics g) {
		int taille_barre = (int)Math.round((double)ico_image_jauge.getIconWidth() * ((double)this.pourcentage_jauge /100));
		
		//Permet de centrer l'image dans le panel
		int x_vide;
		int x_jauge;
		int y = (this.getHeight() - ico_image_jauge.getIconHeight()) / 2;
		//On centre la barre
		x_jauge = (this.getWidth() - ico_image_jauge.getIconWidth()) / 2;
		g.drawImage(img_image_jauge, x_jauge, y, null);
		//On rajoute la barre vide
		x_vide = (ico_image_vide.getIconWidth() - taille_barre) + (this.getWidth() - ico_image_vide.getIconWidth()) / 2;
		g.drawImage(img_image_vide, x_vide, y,taille_barre, ico_image_vide.getIconHeight(), null);
	}

}
