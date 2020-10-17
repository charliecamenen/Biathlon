package com.biathlon.action;

import java.awt.Image;

import javax.swing.ImageIcon;

public class PointFauteSimulation {
	//Point faute
	private ImageIcon ico_point_faute;
	private Image img_point_faute;
	private int x;
	private int y_dyn;
	
	
	public PointFauteSimulation(int x, int y_dyn) {
		super();
		//On créé l'image du cache cible de simulation
		this.ico_point_faute = new ImageIcon(getClass().getResource("/images/pointFauteTir.png"));
		this.img_point_faute = this.ico_point_faute.getImage();
		//On lui donne une position
		this.y_dyn = y_dyn;
		this.x = x;
	}
	
	
	public ImageIcon getIco_point_faute() {
		return ico_point_faute;
	}


	public void setIco_point_faute(ImageIcon ico_point_faute) {
		this.ico_point_faute = ico_point_faute;
	}


	public Image getImg_point_faute() {
		return img_point_faute;
	}
	public void setImg_point_faute(Image img_point_faute) {
		this.img_point_faute = img_point_faute;
	}
	public int getY_reel(int y_simu) {
		y_simu += y_dyn;
		return y_simu;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public int getY_dyn() {
		return y_dyn;
	}
	public void setY_dyn(int y_dyn) {
		this.y_dyn = y_dyn;
	}
	public int getX() {
		return x;
	}
	
	
}
