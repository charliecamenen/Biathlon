package com.biathlon.jeu;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Physique {

	//Fond de nom sur le pas de tir 
	protected ArrayList<Image> list_images_silhouette;
	
	//Icone de l'image courrante
	protected ImageIcon ico_silhouette;
	
	//Image courrante a afficher
	protected Image img_silhouette_courant;
	
	//Numéro de l'image a afficher
	private int num_img_courant;
	
	//Position de la silouhette
	protected int y_silhouette;
	protected int x_silhouette;
	
	
	
	public Physique(int id_equipe,int x_silhouette, int y_silhouette) {
		super();
			
			this.list_images_silhouette =new ArrayList<>();
			
			for(int i = 1; i< 13;i++) {
				ico_silhouette = new ImageIcon(getClass().getResource("/images/physique/physique"+i+".png"));
				Image img_silhouette = ico_silhouette.getImage();
				this.list_images_silhouette.add(img_silhouette);
			}
			
			num_img_courant = (int) Math.round(Math.random() * this.list_images_silhouette.size()-1);
		
		this.y_silhouette = y_silhouette;
		this.x_silhouette = x_silhouette;
	}

	public void changeApparance() {
		
		//Si on a atteint la derniere image 
		if(num_img_courant == this.list_images_silhouette.size()-1) {
			num_img_courant =0;
			//alors on revient au debut
		}else {
			//Sinon on passe a la suivante
			num_img_courant+=1;
		}
		
		//On actualise l'image courrante
		img_silhouette_courant = this.list_images_silhouette.get(num_img_courant);
	}
	


	public ImageIcon getIco_silhouette() {
		return ico_silhouette;
	}

	public void setIco_silhouette(ImageIcon ico_silhouette) {
		this.ico_silhouette = ico_silhouette;
	}

	public Image getImg_silhouette_courant() {
		return img_silhouette_courant;
	}

	public void setImg_silhouette_courant(Image img_silhouette_courant) {
		this.img_silhouette_courant = img_silhouette_courant;
	}

	public int getY_silhouette() {
		return y_silhouette;
	}

	public void setY_silhouette(int y_silhouette) {
		this.y_silhouette = y_silhouette;
	}

	public int getX_silhouette() {
		return x_silhouette;
	}

	public void setX_silhouette(int x_silhouette) {
		this.x_silhouette = x_silhouette;
	}
	
	
}
