package com.biathlon.action;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

public class JPanelGraphisme extends InterfaceGraphique {

	private Rectangle rectangle;
	private Color rectangle_color_fill;
	private Color rectangle_color_tour;
	private int x;
	private int y;
	private final int largeur_bar = 8;
	private int taille_prop_un_niveau = 19;
	private int longueur_bar;
	private int niveau_effort_entrainement ;
	private Color[] list_couleur_effort = new Color[]{
			new Color(0,0,0), //noir
			new Color(186,0,0),
			new Color(234,16,16),
			new Color(235,103,16),
			new Color(255,184,32),
			new Color(255,217,0),
			new Color(226,255,38),
			new Color(178,255,0),
			new Color(42,172,0),
			
		};
	
	
	
	public JPanelGraphisme(int niveau_effort_entrainement) {
		//Niveau d'effort
		this.niveau_effort_entrainement = niveau_effort_entrainement;
		//Taille du rectangle en longueure
		longueur_bar = taille_prop_un_niveau * niveau_effort_entrainement;
		//Position par defaut
		x = 0;
		y = 0;
		//Création de la forme
		rectangle = new Rectangle(x, y, largeur_bar, longueur_bar );
		//Couleur du rectangle
		rectangle_color_fill = list_couleur_effort[this.niveau_effort_entrainement];
		rectangle_color_tour = Color.BLACK;
	}
	
	protected void paintComponent(Graphics g) {
		//super.paintComponent(g);
		//On centre la forme horrizontallement
		x = (int) ((this.getWidth() - largeur_bar) / 2);
		//On calcul la position du rectangle
		y = (int) ((this.getHeight() - longueur_bar));
		
		//détermine la couleur du tour
		g.setColor( rectangle_color_tour );
		g.drawRect( x, y, largeur_bar, longueur_bar );
		
		//On détermine la couleur du remplissage
		g.setColor( rectangle_color_fill );
		g.fillRect (x, y, largeur_bar, longueur_bar);    


	}

	public Color getRectangle_color_fill() {
		return rectangle_color_fill;
	}

	public void setRectangle_color_fill(Color rectangle_color_fill) {
		this.rectangle_color_fill = rectangle_color_fill;
	}

	public int getLongueur_bar() {
		return longueur_bar;
	}

	public void setLongueur_bar(int longueur_bar) {
		this.longueur_bar = longueur_bar;
	}

	public int getNiveau_effort_entrainement() {
		return niveau_effort_entrainement;
	}

	public void setNiveau_effort_entrainement(int niveau_effort_entrainement) {
		this.niveau_effort_entrainement = niveau_effort_entrainement;
	}

	public Color[] getList_couleur_effort() {
		return list_couleur_effort;
	}

	public void setList_couleur_effort(Color[] list_couleur_effort) {
		this.list_couleur_effort = list_couleur_effort;
	}

	public int getTaille_prop_un_niveau() {
		return taille_prop_un_niveau;
	}

	public void setTaille_prop_un_niveau(int taille_prop_un_niveau) {
		this.taille_prop_un_niveau = taille_prop_un_niveau;
	}


}
