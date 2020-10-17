package com.biathlon.action;

import java.awt.Image;

import javax.swing.ImageIcon;

public class CacheCibleSimulation extends CacheCible{
	
	
	public CacheCibleSimulation(int x , int y) {
		super(x,y);
		//On créé l'image du cache cible de simulation
		this.ico_cache_cible = new ImageIcon(getClass().getResource("/images/cacheCibleSimulation.png"));
		this.img_cache_cible = this.ico_cache_cible.getImage();

	}
}
