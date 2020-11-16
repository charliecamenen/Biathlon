package com.biathlon.jeu;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Date;

import com.biathlon.action.Performance;

import java.awt.AWTException;
import java.awt.Robot;

public class Souris implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		int scroll = e.getWheelRotation();
		if (e.getY() >580) {
			Main.scene.updateIdentifiantCourrant(scroll);
		}else {
			if(Main.joueur.getCourse().getList_classement().get(Main.scene.getIndice_classement()).getList_membre().size() >= Main.scene.getDernier_affiche_classement() + scroll & Main.scene.getDernier_affiche_classement() + scroll >= 10 ) {
				Main.scene.setDernier_affiche_classement(Main.scene.getDernier_affiche_classement() + scroll);
			}
		}
		if(e.getY() > 508 & e.getY() < 530 & e.getX() > 649 & e.getX() < 849 ) {
			for(int i =0;i<Main.joueur.getCourse().getList_participants().size();i++) {
				//affiche les infos spécifique a mon biathlete
				if(Main.joueur.getCourse().getList_participants().get(i).getBiathlete().getId() == Main.joueur.getId_biathlete()) {
					//On actualise l'effort de notre biathlete
					Performance mon_biathlete = Main.joueur.getCourse().getList_participants().get(i);
					if(mon_biathlete.getEffort() - scroll < 100 & mon_biathlete.getEffort() - scroll >= 0)
					mon_biathlete.setEffort(mon_biathlete.getEffort() - scroll);
				}
			}
			
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("X: " + e.getX() + " Y: " +  e.getY());
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {	
	}

	@Override
	public void mouseReleased(MouseEvent e) {	
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Touche pressée : " + e.getKeyCode() + " (" + e.getKeyChar() + ")");

		switch(e.getKeyCode()) {
		case 39: //Fleche de droite
			//Si on est pas au maximum de la liste
			if(Main.scene.getIndice_classement()+1 < Main.joueur.getCourse().getList_classement().size()) {
				Main.scene.setIndice_classement(Main.scene.getIndice_classement()+1);
				Main.scene.setDernier_affiche_classement(10);
			}
			break;
		case 37: //Fleche de gauche
			//Si on est pas au minimum de la liste
			if(Main.scene.getIndice_classement() > 0) {
				Main.scene.setIndice_classement(Main.scene.getIndice_classement()-1);
				Main.scene.setDernier_affiche_classement(10);
			}
			break;
		case 107: // +
			if(Main.joueur.getCourse().getVitesse_jeu() < 16) {
				Main.joueur.getCourse().modifierVitesseJeu(Main.joueur.getCourse().getVitesse_jeu() *2);
			}
			break;
		case 109: // -
			if(Main.joueur.getCourse().getVitesse_jeu() > 1) {
				Main.joueur.getCourse().modifierVitesseJeu(Main.joueur.getCourse().getVitesse_jeu() /2);
			}
			break;
		}
		System.out.println(Main.joueur.getCourse().getVitesse_jeu());
		System.out.println(Main.scene.getIndice_classement());

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


}
