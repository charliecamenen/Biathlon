package com.biathlon.jeu;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.biathlon.action.CacheCible;

public class CacheCibleJoueur extends CacheCible {
	
	public CacheCibleJoueur(int x , int y) {
		super(x,y);
		//On créé l'image du cache cible de simulation
		this.ico_cache_cible = new ImageIcon(getClass().getResource("/images/cacheciblejoueur.png"));
		this.img_cache_cible = this.ico_cache_cible.getImage();
		//On lui donne une position
		this.y = y;
		this.x = x;
	}
}
