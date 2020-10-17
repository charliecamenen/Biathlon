package com.biathlon.action;

import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class CacheCible {

	//Cache cible lorsque la cible est blanchi
	protected ImageIcon ico_cache_cible;
	protected Image img_cache_cible;
	protected int x;
	protected int y;
	
	public CacheCible(int x , int y) {
		super();
		//On lui donne une position
		this.y = y;
		this.x = x;
	}


	public ImageIcon getIco_cache_cible() {
		return ico_cache_cible;
	}


	public void setIco_cache_cible(ImageIcon ico_cache_cible) {
		this.ico_cache_cible = ico_cache_cible;
	}


	public Image getImg_cache_cible() {
		return img_cache_cible;
	}

	public void setImg_cache_cible(Image img_cache_cible) {
		this.img_cache_cible = img_cache_cible;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	
	
}
