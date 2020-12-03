package com.biathlon.jeu;

import com.biathlon.action.Gestion;

public class Temps implements Runnable{

	//Durée entre 2 actualisation d'écran en miliseconde
	private final int PAUSE =20;	

	@Override
	public void run() {
		while(true) {
			//On réccupere l'objet Scene dans le main
			//On appel la fonction PaintCoponnent() a chaque tour de boucle
			Main.scene.repaint();
			//Main.useraction.actualise();
			//System.out.println("ok");
			//On attend la durée que l'on souhaite
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}