package com.biathlon.action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.biathlon.jeu.Joueur;
import com.biathlon.jeu.Main;

public class TirJoueur extends Tir implements MouseListener, MouseMotionListener{

	public TirJoueur(int id_biathlete, int PRE, int REG, int VIT, float fraicheur, int tir_a_realiser, String type_tir_cd) {
		super(id_biathlete, PRE, REG, VIT, fraicheur, tir_a_realiser, type_tir_cd);
	}

	@Override
	public void execution() {
		if(this.type_tir_cd == "couche") {
			Main.scene.setMouvement_lunette(0.025);
		}else {
			Main.scene.setMouvement_lunette(0.04);
		}

		//On reinitialise le vecteur de boolean
		Joueur.cible_joueur.updateCibleBlanchisToFalse();

		//ON ajoute la simulation de tir 
		Joueur.course.ajouterSimulationTir(this.id_biathlete);

		//On affiche la cible du joueuer
		Main.scene.setTir_joueur(true);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//On actualise la position de la souris
		Main.scene.positionReel(e.getX() -51, e.getY() -51);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int num_cible = 0;

		//Si on est sur la cible
		if(e.getY() > 100 && e.getY() < 250) {

			//Quel cibles est visée
			for (int i = 280; i<511; i +=48) {

				//Si on trouves une cible
				if (e.getX() >= i && e.getX() < i +48) {

					//Si il y a encore des tirs a réaliser
					if(compteurTir < tir_a_realiser & nb_reussi < 5) {
						System.out.println(Main.scene.getBf_img().getRGB(Main.scene.getX_reel(),Main.scene.getY_reel()));

						if (this.type_tir_cd == "couche") {

							//SI le tir est reussi
							if (Main.scene.getBf_img().getRGB(Main.scene.getX_reel(),Main.scene.getY_reel()) == -16777216) {

								//alors on envoie le resultat True
								Main.scene.ajouterCacheCibleJoueur(num_cible, true);

								//On incrémentes les tirs réussi
								this.nb_reussi +=1;

							} else {

								//sinon on envoie le resultat false
								Main.scene.ajouterCacheCibleJoueur(num_cible, false);

								Joueur.cible_joueur.setCompteurBlanchirCible(4);

								//On reblanchi la cible de simulation
								Joueur.course.blanchirCible(this.id_biathlete);
							}
						}else {

							//SI le tir est reussi
							if (Main.scene.getBf_img().getRGB(Main.scene.getX_reel(),Main.scene.getY_reel()) != -1) {

								//alors on envoie le resultat True
								Main.scene.ajouterCacheCibleJoueur(num_cible, true);

								//On incrémentes les tirs réussi
								this.nb_reussi +=1;

							} else {

								//sinon on envoie le resultat false
								Main.scene.ajouterCacheCibleJoueur(num_cible, false);

								//On reblanchis la cible
								Joueur.cible_joueur.setCompteurBlanchirCible(4);

								//On reblanchi la cible de simulation
								Joueur.course.blanchirCible(this.id_biathlete);
							}
						}





						//ON incrémente le compteur de tirs
						this.compteurTir +=1;

					}
				}

				//On ajoute 1 a la cible si c'est pas la bonne 
				num_cible +=1;
			}
		}	
	}


	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
