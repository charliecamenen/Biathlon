package com.biathlon.action;

import javax.swing.ImageIcon;

public class BalleDePiocheSimulation extends BalleDePioche {

	public BalleDePiocheSimulation(int x , int y) {
		super(x,y);
		//On créé l'image du cache cible de simulation
		this.ico_balle_pioche = new ImageIcon(getClass().getResource("/images/ballePioche.png"));
		this.img_balle_pioche = this.ico_balle_pioche.getImage();

	}
}
