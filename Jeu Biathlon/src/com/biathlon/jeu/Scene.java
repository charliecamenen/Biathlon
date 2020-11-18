package com.biathlon.jeu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.text.AttributedString;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.biathlon.action.Biathlete;
import com.biathlon.action.CacheCible;
import com.biathlon.action.CibleSimulation;
import com.biathlon.action.Classement;
import com.biathlon.action.InterfaceGraphique;
import com.biathlon.action.InterfaceJoueur;
import com.biathlon.action.Performance;
import com.biathlon.action.Saison;

@SuppressWarnings("serial")
public class Scene extends InterfaceGraphique {

	private Graphics g2;

	private BufferedImage bf_img;

	//Ref de mon ordi
	final int ref_w = 1594;
	final int ref_h = 870;

	/*** VARIABLE QUI CONCERNENT LES OBJET A AFFICHER SUR LA SCENE EN HAUT A GAUCHE ***/

	//Circuit
	private ImageIcon ico_circuit;
	private Image img_circuit;

	//lunette
	private ImageIcon ico_lunette;
	private Image img_lunette;
	private int x_lunette;
	private int y_lunette;

	//Frise 1
	private ImageIcon ico_bg_frise;
	private Image img_bg_frise;
	private int x_bg_frise1 =0;
	private int y_bg_frise1 = 560;
	//Frise2
	private int x_bg_frise2 = -1555;
	private int y_bg_frise2 = 560;
	//Frise continue
	private ImageIcon ico_bg_frise_continue;
	private Image img_bg_frise_continue;
	private int x_bg_frise_continue = -10;
	private int y_bg_frise_continue = 560;

	private int indice_classement;

	//Cede provisoire : Absice de la fonction sinusoidale
	private int xAlea = 0;
	private int yAlea = 50;

	//Position de la souris modifié a chaque evenement mouseMoved
	private int x_souris = 50;
	private int Y_souris = 50;

	private int X_reel;
	private int Y_reel;

	private int y_membre;

	private boolean tir_joueur = false;

	//Taux de mouvement de la lunette
	private double mouvement_lunette; 

	//Numero du dernier element a aafficher sur le classement
	private int dernier_affiche_classement;

	//Position du biathlete a observer sur la frise
	private int id_vue_biathlete_courrant;

	//Indice du biathlete courrant (id_vue_biathlete_courrant) dans la liste trié
	private int pos_vue_biathlete_courrant;
	
	//Distance réel entre deux biathlete au moment du scroll
	private int distance_entre_deux = 0;

	//Interface graphique pour controler le biathlete
	InterfaceJoueur interfaceJoueur;

	/***** CONSTRUCTEUR *****/
	public Scene() {
		super();
		interfaceJoueur = new InterfaceJoueur();

		//On créé la buffered image pour pouvoir gerer les pixels
		bf_img = new BufferedImage(2000,2000, BufferedImage.TYPE_3BYTE_BGR);

		//ON créé le graphique sur lequel on ajoute les images
		g2 = bf_img.getGraphics();

		//le dernier a afficher est le 10 eme
		this.dernier_affiche_classement = 10;

		//le premier classement de la course a l'indice 0
		this.indice_classement = 0;

		this.id_vue_biathlete_courrant = Joueur.id_biathlete;
		interfaceJoueur.setDrapeauPhysique(this.id_vue_biathlete_courrant);
		interfaceJoueur.setDrapeauMoi(Joueur.id_biathlete);
		//Circuit 
		ico_circuit = new ImageIcon(getClass().getResource("/images/circuit.png"));
		this.img_circuit = this.ico_circuit.getImage();

		//Lunette de tir
		this.y_lunette = 50;
		this.x_lunette = 50;
		ico_lunette = new ImageIcon(getClass().getResource("/images/lunette.png"));
		this.img_lunette = this.ico_lunette.getImage();

		//Reccuperer le focus
		this.setFocusable(true);
		this.requestFocusInWindow();

		Souris s = new Souris();
		//On ajoute un evennement de souris geré par la classe souris
		this.addMouseMotionListener(s);
		this.addMouseListener(s);
		this.addMouseWheelListener(s);
		this.addKeyListener(s);

		//Chrono qui permet d'actualiser l'écran
		Thread chrono_ecran = new Thread(new Temps());
		chrono_ecran.start();
	}

	//Transforme le temps en miliseconde en hh mm:ss.ms
	public String msToHMS(float temps) {
		String str_temps = "";

		int heure = (int) (temps / 3600000);
		int minute = (int) ((temps - (heure * 3600000)) / 60000);
		int seconde = (int) ((temps - (heure * 3600000) - (minute * 60000)) /1000) ; 
		int dixieme = (int) ((temps - (heure * 3600000) - (minute * 60000) - (seconde * 1000)) /100) ; 

		if(heure > 0 |seconde > 0 | minute > 0) {
			str_temps = seconde + "."+ dixieme;
			if(heure > 0 | minute > 0) {
				if (seconde < 10) {
					str_temps = "0" + str_temps;
				}
				str_temps = minute + ":" + str_temps;
				if(heure > 0) {
					if (minute < 10) {
						str_temps = "0" + str_temps;
					}
					str_temps = heure +"h "+ str_temps ;
				}
			}
		} else {
			str_temps = 0 + "."+ dixieme;
		}

		return str_temps;
	}

	public String metreToKm(float metres) {
		String distance_km;
		//NumberFormat format = Number.getInstance();
		//format.setMinimumFractionDigits(2); //nb de chiffres apres la virgule
		//String s=format.format(metres); 

		double dist = (double) Math.round(metres);
		distance_km =  dist/1000 + " km";
		return distance_km;
	}

	//Modifie la position réel de la souris
	public void positionReel(int x_mouse, int y_mouse) {
		setX_souris(x_mouse);
		setY_souris(y_mouse);
	}

	//Permet de bouger la lunette de tir aleatoirement (fonction sinusoidale)
	public void aleaLunette() {
		//Prendre en parametre le vent, la fraicheur et le niveau en tir

		//On incrémente les abscisses de la fonction de maniere aléatoire
		this.setyAlea(getyAlea() + (int) Math.round(Math.random() * (2)));
		this.setxAlea(getxAlea() + (int) Math.round(Math.random() * (2)));
		//On actualise la position de la lunette
		this.setY_lunette((int) Math.round(12*Math.sin(this.mouvement_lunette * this.getyAlea()))+ getY_souris());
		this.setX_lunette((int) Math.round(12*Math.sin(this.mouvement_lunette * this.getxAlea()))+ getX_souris());

		//On actualise la position du centre
		this.setX_reel(this.x_lunette + 51);
		this.setY_reel(this.y_lunette + 51);
	}


	//Renvoie la taille réelle adapté a la résolution de l'écran du joueur
	//Prend en parametre l'iconeimage afin de modifier la taille prédéfini par rapport aux ref
	public int propTailleImage(int ico_size, char orientation) {
		int taille = 0;
		//Si c'est en paysage
		if(orientation == 'w') {
			taille = (int)Math.round(((float)ico_size / ref_w)*this.getWidth());
		}else {
			taille = (int)Math.round(((float)ico_size / ref_h)*this.getHeight());
		}

		return taille;
	}

	public int propTaillePolice(float ico_size) {
		int taille_police;
		//Calcul la taille de la nouvelle police
		taille_police = (int)Math.round((ico_size / ref_w)*this.getWidth());

		return taille_police;
	}

	public void deplacementFond() {
		this.x_bg_frise1 += 4;
		this.x_bg_frise2 += 4; 

		if(this.x_bg_frise1 >= 1555 ) {
			this.x_bg_frise1 = -1550;
		}else if(this.x_bg_frise1 <= -1555) {
			this.x_bg_frise1 = 1550;
		}else if(this.x_bg_frise2 >= 1555) {
			this.x_bg_frise2 = -1550;
		}else if(this.x_bg_frise2 <=-1555) {
			this.x_bg_frise2 = 1550;
		}

	}

	public void navigationPhysique() {

		//Liste des performances
		ArrayList<Performance> list_performances_courrant = Main.joueur.getCourse().getList_participants();


		//Parcour des performances pour trouver la position 
		for(int i = 0; i < list_performances_courrant.size() ; i++) {

			//Si c'est la vue visée
			if(list_performances_courrant.get(i).getBiathlete().getId() == this.id_vue_biathlete_courrant) {

				//Alors on actualise la position de son indice dans la liste
				this.pos_vue_biathlete_courrant = i;
			}

			//On update le classement
			list_performances_courrant.get(i).setPos_classement(i+1);;
		}


		//On met à jour la position de tout le monde par rapport à la selection
		Main.joueur.getCourse().updatePositionPhysique(this.pos_vue_biathlete_courrant);

		//-***********WARNING !!!!!!!!***************//
		//Mettre ca direct dans update physique ?
		int dist_cible_but;
		int decalage;
		//Si on avance vers l'avant
		if(list_performances_courrant.get(this.pos_vue_biathlete_courrant).getPhysique().getX_silhouette() < 500) {

			dist_cible_but = 500-list_performances_courrant.get(this.pos_vue_biathlete_courrant).getPhysique().getX_silhouette();
			//Nombre de pixel a decaler

			if (distance_entre_deux >50) {
				//Nombre de pixel a decaler
				decalage = Math.min(distance_entre_deux/5, dist_cible_but);
			} else {
				//Nombre de pixel a decaler
				decalage = Math.min(20, dist_cible_but);
			}
			//Décalage de la frise
			this.x_bg_frise1 += 20;
			this.x_bg_frise2 += 20;

			//Parcour des performances pour actualiser la position
			for(Performance perf : list_performances_courrant) {

				//Alors on décale l'abscice du biatltete
				perf.getPhysique().setX_silhouette(perf.getPhysique().getX_silhouette() + decalage);
			}

		}else if(list_performances_courrant.get(this.pos_vue_biathlete_courrant).getPhysique().getX_silhouette() > 500) {//déplacement arriere
			dist_cible_but = list_performances_courrant.get(this.pos_vue_biathlete_courrant).getPhysique().getX_silhouette()-500;
			if (distance_entre_deux >50) {
				//Nombre de pixel a decaler
				decalage = Math.min(distance_entre_deux/5, dist_cible_but);
			} else {
				//Nombre de pixel a decaler
				decalage = Math.min(20, dist_cible_but);
			}
			

			this.x_bg_frise1 -= 20;
			this.x_bg_frise2 -= 20; 

			//Parcour des performances pour actualiser els positions
			for( Performance perf : list_performances_courrant) {

				//alors on decale l'abscice de tout le monde
				perf.getPhysique().setX_silhouette(perf.getPhysique().getX_silhouette() - decalage);
			}

		}

	}

	//Définir quel biathlete doit etre observé dans la frise (défini lors du scroll
	public void updateIdentifiantCourrant(int scroll_value) {
		
		//Calcul du x entre les deux consécutif lors du scroll
		//Le courrant - le prochain
		this.distance_entre_deux =  Math.abs((int)Main.joueur.getCourse().getList_participants().get(this.pos_vue_biathlete_courrant).getPhysique().getX_silhouette() -  Main.joueur.getCourse().getList_participants().get(this.pos_vue_biathlete_courrant + scroll_value).getPhysique().getX_silhouette());
		
		//Met a jour l'identifiant du biathlete regardé
		this.id_vue_biathlete_courrant = Main.joueur.getCourse().getList_participants().get(this.pos_vue_biathlete_courrant + scroll_value).getBiathlete().getId();
		
		//ON update le drapeau du physique courrant
		this.interfaceJoueur.setDrapeauPhysique(this.id_vue_biathlete_courrant);
	}

	//Methode qui ajoute un cache si une cible es abatue
	public void ajouterCacheCibleJoueur(int indice, boolean bool) {

		//On ajoute le resultat a la s+imulation
		Main.joueur.getCourse().ajouterResultatTir(Joueur.id_biathlete, bool );

		//On ajoute le resultat a la cible de joueur
		Joueur.cible_joueur.ajouterResultatTir(bool, indice);

	}

	/******************* METHODEs QUI AFFICHE LES ELEMENTS A L'ECRAN ****************************/

	//Affiche le background de la course
	public void afficherBackGround() {
		//Fond d'écran
		//g2.drawImage(Main.joueur.getImg_bg(),0,-30,null);
		ImageIcon ico_bg_course;
		Image img_bg_course;
		ico_bg_course = new ImageIcon(getClass().getResource("/images/backgroundCourse.png"));
		img_bg_course = ico_bg_course.getImage();
		g2.drawImage(img_bg_course,0,0,propTailleImage(ico_bg_course.getIconWidth(),'w'),propTailleImage(ico_bg_course.getIconHeight(),'h'), null);
	}

	public void afficherInterface() {

		//Affiche le background de l'interface 
		g2.drawImage(interfaceJoueur.getImg_bg(), propTailleImage(interfaceJoueur.getX_bg(), 'w'), propTailleImage(interfaceJoueur.getY_bg(), 'h'),propTailleImage(interfaceJoueur.getIco_bg().getIconWidth(),'w'),propTailleImage(interfaceJoueur.getIco_bg().getIconHeight(),'h'), null);
		//Affiche le symbole d'acceleration 
		g2.drawImage(interfaceJoueur.getImg_acc_symbole(), propTailleImage(interfaceJoueur.getX_acc_symbole(), 'w'), propTailleImage(interfaceJoueur.getY_acc_symbole(), 'h'),propTailleImage(interfaceJoueur.getIco_acc_symbole().getIconWidth(),'w'),propTailleImage(interfaceJoueur.getIco_acc_symbole().getIconHeight(),'h'), null);
		//Affiche le sumbole d'energie 
		g2.drawImage(interfaceJoueur.getImg_energie_symbole(), propTailleImage(interfaceJoueur.getX_energie_symbole(), 'w'), propTailleImage(interfaceJoueur.getY_energie_symbole(), 'h'),propTailleImage(interfaceJoueur.getIco_energie_symbole().getIconWidth(),'w'),propTailleImage(interfaceJoueur.getIco_energie_symbole().getIconHeight(),'h'), null);
		//affichage des jauges vide  
		g2.drawImage(interfaceJoueur.getImg_jauge_acceleration_vide(), propTailleImage(interfaceJoueur.getX_jauge_acceleration(), 'w'), propTailleImage(interfaceJoueur.getY_jauge_acceleration_vide(), 'h'),propTailleImage(interfaceJoueur.getIco_jauge_acceleration_vide().getIconWidth(),'w'),propTailleImage(interfaceJoueur.getIco_jauge_acceleration_vide().getIconHeight(),'h'), null);
		g2.drawImage(interfaceJoueur.getImg_jauge_energie_vide(), propTailleImage(interfaceJoueur.getX_jauge_energie(), 'w'), propTailleImage(interfaceJoueur.getY_jauge_energie(), 'h'),propTailleImage(interfaceJoueur.getIco_jauge_energie_vide().getIconWidth(),'w'),propTailleImage(interfaceJoueur.getIco_jauge_energie_vide().getIconHeight(),'h'), null);

		//Affichage des boutons
		//g2.drawImage(interfaceJoueur.getImg_jauge_energie_vide(), propTailleImage(interfaceJoueur.getX_jauge_energie(), 'w'), propTailleImage(interfaceJoueur.getY_jauge_energie(), 'h'),propTailleImage(interfaceJoueur.getIco_jauge_energie_vide().getIconWidth(),'w'),propTailleImage(interfaceJoueur.getIco_jauge_energie_vide().getIconHeight(),'h'), null);
		for(int i =0;i<Main.joueur.getCourse().getList_participants().size();i++) {
			//affiche les infos spécifique a mon biathlete
			if(Main.joueur.getCourse().getList_participants().get(i).getBiathlete().getId() == Joueur.getId_biathlete()) {
				float taux_en_max = Main.joueur.getCourse().getList_participants().get(i).getTaux_energie_max();
				float taux_en = Main.joueur.getCourse().getList_participants().get(i).getTaux_energie();
				float taux_acc = Main.joueur.getCourse().getList_participants().get(i).getTaux_acceleration();
				int pulsation = Main.joueur.getCourse().getList_participants().get(i).getPulsation();
				int effort = Main.joueur.getCourse().getList_participants().get(i).getEffort();
				String mon_nom = Main.joueur.getCourse().getList_participants().get(i).getBiathlete().getLibelle();
				//float forme = Main.joueur.getCourse().getList_participants().get(i);

				//Affiche le curseur de la jauge 
				g2.drawImage(interfaceJoueur.getImg_curseur_jauge(), propTailleImage((int)Math.round(interfaceJoueur.getX_curseur_jauge() + ((float)effort * 0.01) * interfaceJoueur.getLongueur_jauge()) , 'w'), propTailleImage(interfaceJoueur.getY_curseur_jauge(), 'h'),propTailleImage(interfaceJoueur.getIco_curseur_jauge().getIconWidth(),'w'),propTailleImage(interfaceJoueur.getIco_curseur_jauge().getIconHeight(),'h'), null);
				//energie
				g2.drawImage(interfaceJoueur.getImg_jauge_energie_max(), propTailleImage(interfaceJoueur.getX_jauge_energie(), 'w'), propTailleImage(Math.round(interfaceJoueur.getY_jauge_energie() +  interfaceJoueur.getIco_jauge_energie_max().getIconHeight() * (1-(taux_en_max/100))), 'h'),propTailleImage(interfaceJoueur.getIco_jauge_energie_max().getIconWidth(),'w'),propTailleImage(Math.round(interfaceJoueur.getIco_jauge_energie_max().getIconHeight() * (taux_en_max/100)),'h'), null);			
				g2.drawImage(interfaceJoueur.getImg_jauge_energie(), propTailleImage(interfaceJoueur.getX_jauge_energie(), 'w'), propTailleImage(Math.round(interfaceJoueur.getY_jauge_energie() +  interfaceJoueur.getIco_jauge_energie_max().getIconHeight() * (1-(taux_en/100))), 'h'),propTailleImage(interfaceJoueur.getIco_jauge_energie().getIconWidth(),'w'),propTailleImage(Math.round(interfaceJoueur.getIco_jauge_energie().getIconHeight() * (taux_en/100)),'h'), null);			
				g2.drawImage(interfaceJoueur.getImg_jauge_acceleration(), propTailleImage(interfaceJoueur.getX_jauge_acceleration(), 'w'), propTailleImage(Math.round(interfaceJoueur.getY_jauge_energie() +  interfaceJoueur.getIco_jauge_energie_max().getIconHeight() * (1-(taux_acc/100))), 'h'),propTailleImage(interfaceJoueur.getIco_jauge_acceleration().getIconWidth(),'w'),propTailleImage(Math.round(interfaceJoueur.getIco_jauge_acceleration().getIconHeight() * (taux_acc/100)),'h'), null);			

				//effort
				g2.setFont(new Font("calibri", Font.BOLD, propTaillePolice(25)));
				g2.drawString(effort+"",propTailleImage(interfaceJoueur.getX_effort(),'w'),propTailleImage(interfaceJoueur.getY_effort(),'h'));

				//Pulsation
				g2.setFont(new Font("calibri", Font.BOLD, propTaillePolice(19)));
				g2.setColor(new Color(200,200,0));
				g2.drawString(pulsation +"",propTailleImage(interfaceJoueur.getX_pulsation(),'w'),propTailleImage(interfaceJoueur.getY_pulsation(),'h'));

				//Nom de mon biathlete
				g2.setFont(new Font("calibri", Font.BOLD, propTaillePolice(19)));
				g2.setColor(new Color(255,255,255));
				g2.drawString(mon_nom +"",propTailleImage(interfaceJoueur.getX_nom_mon_biathlete(),'w'),propTailleImage(interfaceJoueur.getY_nom_mon_biathlete(),'h'));

				//drapeau mon biathlete
				g2.drawImage(interfaceJoueur.getImg_drapeau_moi(), propTailleImage(interfaceJoueur.getX_drapeau_moi(), 'w'), propTailleImage(interfaceJoueur.getY_drapeau_moi(), 'h'),propTailleImage((int)Math.round(interfaceJoueur.getIco_drapeau_moi().getIconWidth()*1.6),'w'),propTailleImage((int)Math.round(interfaceJoueur.getIco_drapeau_moi().getIconHeight()*1.6),'h'), null);			
				//Pour chaque bouton
				for(int k = 0; k<3 ;k++) {
					g2.drawImage(interfaceJoueur.boutonSelect(k), propTailleImage(interfaceJoueur.getX_bouton() + k* interfaceJoueur.getX_bouton_decalage() , 'w'), propTailleImage(interfaceJoueur.getY_bouton(), 'h'),propTailleImage(interfaceJoueur.getIco_bouton().getIconWidth(),'w'),propTailleImage(interfaceJoueur.getIco_bouton().getIconHeight(),'h'), null);

				}
			}


			//affiche les infos spécifique au physique selectioné
			String nom_phy = Main.joueur.getCourse().getList_participants().get(this.pos_vue_biathlete_courrant).getBiathlete().getLibelle();
			Biathlete biathlete_phy = Main.joueur.getCourse().getList_participants().get(this.pos_vue_biathlete_courrant).getBiathlete();
			int cla = Main.joueur.getCourse().getList_participants().get(this.pos_vue_biathlete_courrant).getPos_classement();

			AttributedString classement = new AttributedString(Main.joueur.getCourse().getList_participants().get(this.pos_vue_biathlete_courrant).getPos_classement()+"e");
			int exp = 1;
			if(cla >9) { exp = 2;}

			classement.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER, exp, exp+1);
			classement.addAttribute(TextAttribute.SIZE, propTaillePolice(28));
			g2.drawString(classement.getIterator(),propTailleImage(interfaceJoueur.getX_classement(),'w'),propTailleImage(interfaceJoueur.getY_classement(),'h'));

			//drapeau physique
			g2.drawImage(interfaceJoueur.getImg_drapeau_physique(), propTailleImage(interfaceJoueur.getX_drapeau_physique(), 'w'), propTailleImage(interfaceJoueur.getY_drapeau_physique(), 'h'),propTailleImage((int)Math.round(interfaceJoueur.getIco_drapeau_physique().getIconWidth()*1.6),'w'),propTailleImage((int)Math.round(interfaceJoueur.getIco_drapeau_physique().getIconHeight()*1.6),'h'), null);			
			//Nom de mon biathlete
			g2.setFont(new Font("calibri", Font.BOLD, propTaillePolice(19)));
			g2.setColor(new Color(255,255,255));
			g2.drawString(nom_phy,propTailleImage(interfaceJoueur.getX_nom_biathlete(),'w'),propTailleImage(interfaceJoueur.getY_nom_biathlete(),'h'));

			//FAIRE VARIER EN FONCTION DU NOMBRE DE TIRS
			g2.setColor(new Color(255,255,255));
			g2.setFont(new Font("calibri", Font.BOLD, propTaillePolice(19)));
			//Affichage des background de resultat au tirs
			for(int j = 0; j < 8;j++) {
				if(j < 4) {
					g2.drawImage(interfaceJoueur.getImg_res_tir(), propTailleImage(interfaceJoueur.getX_res_tir() + j*interfaceJoueur.getX_res_tir_decalage(), 'w'), propTailleImage(interfaceJoueur.getY_res_tir(), 'h'),propTailleImage(interfaceJoueur.getIco_res_tir().getIconWidth(),'w'),propTailleImage(interfaceJoueur.getIco_res_tir().getIconHeight(),'h'), null);			
					g2.drawString("1",propTailleImage(interfaceJoueur.getX_text_faute() + j*interfaceJoueur.getX_text_faute_decalage(),'w'),propTailleImage(interfaceJoueur.getY_text_faute() ,'h'));
				}else {
					g2.drawImage(interfaceJoueur.getImg_res_tir(), propTailleImage(interfaceJoueur.getX_res_tir() + (j-4)*interfaceJoueur.getX_res_tir_decalage(), 'w'), propTailleImage(interfaceJoueur.getY_res_tir() + interfaceJoueur.getY_res_tir_decalage(), 'h'),propTailleImage(interfaceJoueur.getIco_res_tir().getIconWidth(),'w'),propTailleImage(interfaceJoueur.getIco_res_tir().getIconHeight(),'h'), null);				
					g2.drawString("-",propTailleImage(interfaceJoueur.getX_text_faute() + (j-4)*interfaceJoueur.getX_text_faute_decalage(),'w'),propTailleImage(interfaceJoueur.getY_text_faute()+ interfaceJoueur.getY_text_faute_decalage() ,'h'));
				}
			}

			//On affiche les notes du biathlete physique
			g2.setFont(new Font("calibri", Font.BOLD, propTaillePolice(18)));
			g2.drawString("SKI",propTailleImage(interfaceJoueur.getX_note_lib(),'w'),propTailleImage(interfaceJoueur.getY_note_ski(),'h'));
			g2.drawString("DEB",propTailleImage(interfaceJoueur.getX_note_lib(),'w'),propTailleImage(interfaceJoueur.getY_note_deb(),'h'));
			g2.drawString("COU",propTailleImage(interfaceJoueur.getX_note_lib(),'w'),propTailleImage(interfaceJoueur.getY_note_cou(),'h'));
			g2.drawString("END",propTailleImage(interfaceJoueur.getX_note_lib(),'w'),propTailleImage(interfaceJoueur.getY_note_end(),'h'));
			g2.drawString("ACC",propTailleImage(interfaceJoueur.getX_note_lib(),'w'),propTailleImage(interfaceJoueur.getY_note_acc(),'h'));
			g2.setFont(new Font("calibri", Font.PLAIN, propTaillePolice(18)));
			g2.drawString(""+biathlete_phy.getSki(),propTailleImage(interfaceJoueur.getX_note(),'w'),propTailleImage(interfaceJoueur.getY_note_ski(),'h'));
			g2.drawString(""+biathlete_phy.getDeb(),propTailleImage(interfaceJoueur.getX_note(),'w'),propTailleImage(interfaceJoueur.getY_note_deb(),'h'));
			g2.drawString(""+biathlete_phy.getCou(),propTailleImage(interfaceJoueur.getX_note(),'w'),propTailleImage(interfaceJoueur.getY_note_cou(),'h'));
			g2.drawString(""+biathlete_phy.getEnd(),propTailleImage(interfaceJoueur.getX_note(),'w'),propTailleImage(interfaceJoueur.getY_note_end(),'h'));
			g2.drawString(""+biathlete_phy.getAcc(),propTailleImage(interfaceJoueur.getX_note(),'w'),propTailleImage(interfaceJoueur.getY_note_acc(),'h'));

			//On ffiche les information du classement generale
			//Actuel
			AttributedString pos_class = new AttributedString("12e");
			pos_class.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER, 2, 3);
			pos_class.addAttribute(TextAttribute.SIZE, propTaillePolice(17));
			g2.drawString(pos_class.getIterator(),propTailleImage(interfaceJoueur.getX_position_classement(),'w'),propTailleImage(interfaceJoueur.getY_position_classement(),'h'));
			AttributedString pos_point = new AttributedString("847pts");
			pos_point.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB, 3, 6);
			pos_point.addAttribute(TextAttribute.SIZE, propTaillePolice(17));
			g2.drawString(pos_point.getIterator(),propTailleImage(interfaceJoueur.getX_point_classement(),'w'),propTailleImage(interfaceJoueur.getY_point_classement(),'h'));

			//prediction
			g2.setColor(new Color(50,230,20));
			AttributedString pos_class_pred = new AttributedString("8e");
			pos_class_pred.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER, 1, 2);
			pos_class_pred.addAttribute(TextAttribute.SIZE, propTaillePolice(17));
			g2.drawString(pos_class_pred.getIterator(),propTailleImage(interfaceJoueur.getX_position_predict(),'w'),propTailleImage(interfaceJoueur.getY_position_predict(),'h'));
			AttributedString pos_point_pred = new AttributedString("1030pts");
			pos_point_pred.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB, 4, 7);
			pos_point_pred.addAttribute(TextAttribute.SIZE, propTaillePolice(17));
			g2.drawString(pos_point_pred.getIterator(),propTailleImage(interfaceJoueur.getX_point_predict(),'w'),propTailleImage(interfaceJoueur.getY_point_predict(),'h'));

			float vitesse_phy = Main.joueur.getCourse().getList_participants().get(this.pos_vue_biathlete_courrant).getVitesse();
			float dist_arr_phy = Main.joueur.getCourse().getList_participants().get(this.pos_vue_biathlete_courrant).getList_km_pointage().get(Main.joueur.getCourse().getList_participants().get(this.pos_vue_biathlete_courrant).getList_km_pointage().size() -1) - Main.joueur.getCourse().getList_participants().get(this.pos_vue_biathlete_courrant).getDistance();
			float dist_tir_phy = Main.joueur.getCourse().getList_participants().get(this.pos_vue_biathlete_courrant).getList_km_tir().get(Main.joueur.getCourse().getList_participants().get(this.pos_vue_biathlete_courrant).getTir_courrant()) - Main.joueur.getCourse().getList_participants().get(this.pos_vue_biathlete_courrant).getDistance();
			float vent_phy = Main.joueur.getCourse().getList_participants().get(this.pos_vue_biathlete_courrant).getVitesse_vent();
			float pente_phy = Main.joueur.getCourse().getList_participants().get(this.pos_vue_biathlete_courrant).getPente();

			g2.setColor(new Color(255,255,255));
			g2.setFont(new Font("calibri", Font.PLAIN, propTaillePolice(22)));
			//vitesse
			g2.drawString(vitesse_phy+" km/h",propTailleImage(interfaceJoueur.getX_vitesse(),'w'),propTailleImage(interfaceJoueur.getY_vitesse(),'h'));
			//Distance tir
			g2.drawString(Math.rint(dist_tir_phy/100)/10 + " km",propTailleImage(interfaceJoueur.getX_distance_tir(),'w'),propTailleImage(interfaceJoueur.getY_distance_tir(),'h'));
			//distance arrivée
			g2.drawString(Math.rint(dist_arr_phy/100)/10 + " km",propTailleImage(interfaceJoueur.getX_distance_arrive(),'w'),propTailleImage(interfaceJoueur.getY_distance_arrive(),'h'));
			//pente
			g2.drawString(pente_phy+ " %",propTailleImage(interfaceJoueur.getX_pente(),'w'),propTailleImage(interfaceJoueur.getY_pente(),'h'));
			//vent
			g2.drawString(vent_phy+" km/h",propTailleImage(interfaceJoueur.getX_vent(),'w'),propTailleImage(interfaceJoueur.getY_vent(),'h'));

			//vent direction
			double rotationRequired = Math.toRadians (45);

			/*AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, interfaceJoueur.getX_vent_direction(), interfaceJoueur.getY_vent_direction());
				AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
				AffineTransform rotation = new AffineTransform();
				rotation = AffineTransform.getRotateInstance(rotationRequired, interfaceJoueur.getX_vent_direction(), interfaceJoueur.getY_vent_direction());
				g2.drawImage(interfaceJoueur.getImg_vent_direction(), rotation, null);*/
			g2.drawImage(interfaceJoueur.getImg_vent_direction(), propTailleImage(interfaceJoueur.getX_vent_direction(), 'w'), propTailleImage(Math.round(interfaceJoueur.getY_vent_direction()), 'h'),propTailleImage(interfaceJoueur.getIco_vent_direction().getIconWidth(),'w'),propTailleImage(Math.round(interfaceJoueur.getIco_vent_direction().getIconHeight()),'h'), null);			


		}

		//On affiche les text
		g2.setColor(new Color(255,255,255));
		g2.setFont(new Font("calibri", Font.BOLD, propTaillePolice(19)));
		g2.drawString(this.msToHMS(Main.joueur.getCourse().getChrono_course()),propTailleImage(interfaceJoueur.getX_chrono(),'w'),propTailleImage(interfaceJoueur.getY_chrono(),'h'));

	}

	//Affiche le classement
	public void afficherClassement(Classement classement) {

		y_membre = classement.getRef_pos_y();
		//On affiche l'en tete de classement

		//ON affiche le background du titre
		//g2.drawImage(classement.getImg_bg_titre(), propTailleImage(classement.getX_bg_titre(),'w' ), propTailleImage(classement.getY_bg_titre(),'h' ) ,propTailleImage(classement.getIco_bg_titre().getIconWidth(),'w' ),propTailleImage(classement.getIco_bg_titre().getIconHeight(),'h' ), null);

		//Affiche le titre
		g2.setColor(new Color(255,255,255));

		g2.setFont(new Font("calibri", Font.BOLD, propTaillePolice(16)));

		g2.drawString(classement.getTitre(), propTailleImage(classement.getX_titre(),'w' ), propTailleImage(classement.getY_titre(),'h' ));

		g2.drawString(this.metreToKm(classement.getDistance()), propTailleImage(classement.getX_distance(),'w' ), propTailleImage(classement.getY_distance(),'h' ));

		//Si le classement contient des membres alors on affiche le premier
		if(classement.getList_membre().size() > 0) {

			//On passe le membre actuel dans une variable
			MembreClassement membre = classement.getList_membre().get(0);

			//Afficher le background de l'athlete classé
			g2.drawImage(membre.getImg_bg(), propTailleImage(membre.getX_bg(),'w' ),propTailleImage(y_membre,'h' ), propTailleImage(membre.getIco_bg().getIconWidth(),'w' ),propTailleImage(membre.getIco_bg().getIconHeight(),'h' ), null);

			//Affiché le drapeau 
			g2.drawImage(membre.getImg_drapeau(), propTailleImage(membre.getX_drapeau(),'w' ),propTailleImage(y_membre+5,'h' ), propTailleImage(membre.getIco_drapeau().getIconWidth(),'w' ),propTailleImage(membre.getIco_drapeau().getIconHeight(),'h' ), null);

			//Affiché le backgroud d'erreur 
			//g2.drawImage(membre.getImg_bg_erreur(), propTailleImage(membre.getX_bg_erreur(),'w' ),propTailleImage(y_membre+3,'h' ), propTailleImage(membre.getIco_bg_erreur().getIconWidth(),'w' ),propTailleImage(membre.getIco_bg_erreur().getIconHeight(),'h' ), null);


			//Nombre de fautes
			g2.setFont(new Font("calibri", Font.BOLD, propTaillePolice(15)));
			g2.drawString(membre.getStrErreur(),propTailleImage(membre.getX_erreur(),'w' ),propTailleImage(y_membre + 19,'h' ));

			//Affiche le nom
			g2.setFont(new Font("calibri", Font.PLAIN, propTaillePolice(15)));
			g2.drawString(membre.getLibelle_biathlete(),propTailleImage(membre.getX_libelle_biathlete(),'w' ),propTailleImage(y_membre + 19,'h' ));

			//Afficher le classement
			g2.drawString(membre.getStrNumClassement(), propTailleImage(membre.getX_num_classement(),'w' ),propTailleImage(y_membre + 19,'h' ));

			//Affiche le temps du premier
			g2.drawString(this.msToHMS(membre.getTemps()), propTailleImage(membre.getX_temps(),'w' ),propTailleImage(y_membre + 19,'h' ));

			//On incremente le y
			y_membre +=31;
		}
		int size_list;
		int deb_list;
		if (classement.getList_membre().size() >= 10) {
			//La fin est l'element max
			size_list = this.dernier_affiche_classement;
			//Le debut est la fin  -8
			deb_list = size_list - 9;
		}else {
			//La fin de la liste est la maximum
			size_list = classement.getList_membre().size();
			//Le debut de la liste est l'element 1
			deb_list = 1;
		}


		//On affiche les membres du classement a partir du i selectionné dynamiuement grace au scoll de la souris
		for (int i = deb_list; i < size_list; i++) {

			//On passe le membre actuel dans une variable
			MembreClassement membre = classement.getList_membre().get(i);

			//Afficher le background de l'athlete classé
			g2.drawImage(membre.getImg_bg(), propTailleImage(membre.getX_bg(),'w' ),propTailleImage(y_membre,'h' ), propTailleImage(membre.getIco_bg().getIconWidth(),'w' ),propTailleImage(membre.getIco_bg().getIconHeight(),'h' ), null);

			//Affiché le drapeau 
			g2.drawImage(membre.getImg_drapeau(), propTailleImage(membre.getX_drapeau(),'w' ),propTailleImage(y_membre+5,'h' ), propTailleImage(membre.getIco_drapeau().getIconWidth(),'w' ),propTailleImage(membre.getIco_drapeau().getIconHeight(),'h' ), null);

			//Affiché le backgroud d'erreur 
			//g2.drawImage(membre.getImg_bg_erreur(), propTailleImage(membre.getX_bg_erreur(),'w' ),propTailleImage(y_membre+3,'h' ), propTailleImage(membre.getIco_bg_erreur().getIconWidth(),'w' ),propTailleImage(membre.getIco_bg_erreur().getIconHeight(),'h' ), null);

			//Nombre de fautes
			g2.setFont(new Font("calibri", Font.BOLD, propTaillePolice(15)));
			g2.drawString(membre.getStrErreur(),propTailleImage(membre.getX_erreur(),'w' ),propTailleImage(y_membre + 19,'h' ));


			g2.setFont(new Font("calibri", Font.PLAIN, propTaillePolice(15)));
			//Afficher le classement
			g2.drawString(membre.getStrNumClassement(), propTailleImage(membre.getX_num_classement(),'w' ),propTailleImage(y_membre+19,'h' ));

			//Affiche le nom
			g2.drawString(membre.getLibelle_biathlete(),propTailleImage(membre.getX_libelle_biathlete(),'w' ),propTailleImage(y_membre+19,'h' ));

			//Affiche le retard
			g2.drawString("+ " + this.msToHMS(membre.getRetard()), propTailleImage(membre.getX_retard(),'w' ),propTailleImage(y_membre+19,'h' ));

			//On incrémente le y
			y_membre +=31;
		}

	}

	//Fonction qui affiche la liste des caches cibles
	public void afficherCacheCibleJoueur(){
		//ON affiche la liste des cache cible
		for (CacheCible cache_cible : Joueur.cible_joueur.getList_cache_cible()) {
			g2.drawImage(cache_cible.getImg_cache_cible(),propTailleImage(cache_cible.getX(),'w'),propTailleImage(cache_cible.getY(),'h'),propTailleImage(cache_cible.getIco_cache_cible().getIconWidth(), 'w'),propTailleImage(cache_cible.getIco_cache_cible().getIconHeight(), 'h') , null);
		}
	}


	//Ajoute un tireur a la file sur le pas de tir
	public void afficherSimulationTir(ArrayList<CibleSimulation> liste_simulation_tir) {
		//System.out.println("Nombre tireur : " + liste_simulation_tir.size());
		int taille; 
		int y_simu_dyn = 0;
		if(liste_simulation_tir.size() >0) {
			y_simu_dyn = liste_simulation_tir.get(0).getRef_pos_y();
		}


		if(liste_simulation_tir.size() < 16) {
			taille = liste_simulation_tir.size();
		}else {
			taille = 16;
		}



		for(int i = 0; i < taille; i++) {

			CibleSimulation simu = liste_simulation_tir.get(i);


			//Si sur un relai on depasse le5 eme tir alors on affiche les balle de pioche
			if(simu.getList_balle_pioche() != null) {

				//Emplacement de balles d pioches
				g2.drawImage(simu.getImg_emp_pioche(), propTailleImage(simu.getX_emp_pioche(),'w'),propTailleImage(y_simu_dyn,'h'),propTailleImage(simu.getIco_emp_pioche().getIconWidth(),'w'),propTailleImage(simu.getIco_emp_pioche().getIconHeight(),'h'), null);

				//Pioche restante
				for(int j = 0; j < (simu.getList_balle_pioche().size()) ; j++) {
					g2.drawImage(simu.getList_balle_pioche().get(j).getImg_balle_pioche(),propTailleImage(simu.getList_balle_pioche().get(j).getX(),'w'),propTailleImage(simu.getList_balle_pioche().get(j).getY(),'h'),propTailleImage(simu.getList_balle_pioche().get(j).getIco_balle_pioche().getIconWidth(),'w'),propTailleImage(simu.getList_balle_pioche().get(j).getIco_balle_pioche().getIconHeight(),'h'), null);
				}
			}

			//Affiche la cible
			g2.drawImage(simu.getImg_cible(), propTailleImage(simu.getX_cible(),'w'),propTailleImage(y_simu_dyn,'h'),propTailleImage(simu.getIco_cible().getIconWidth(),'w'),propTailleImage(simu.getIco_cible().getIconHeight(),'h'), null);

			//Affiche le drapeau
			g2.drawImage(simu.getImg_drapeau(), propTailleImage(simu.getX_drapeau(),'w'),propTailleImage(y_simu_dyn+5,'h'),propTailleImage(simu.getIco_drapeau().getIconWidth(),'w'),propTailleImage(simu.getIco_drapeau().getIconHeight(),'h'), null);

			//Affiche le nom
			g2.setColor(new Color(255,255,255));
			g2.setFont(new Font("calibri", Font.PLAIN, propTaillePolice(16)));
			g2.drawString(simu.getLibelle_biathlete(), propTailleImage(simu.getX_libelle_biathlete(),'w'),propTailleImage(y_simu_dyn +21,'h'));

			for(int j = 0; j < (simu.getList_cache_cible().size()) ; j++) {
				g2.drawImage(simu.getList_cache_cible().get(j).getImg_cache_cible(),propTailleImage(simu.getList_cache_cible().get(j).getX(),'w'),propTailleImage(y_simu_dyn+3,'h'),propTailleImage(simu.getList_cache_cible().get(j).getIco_cache_cible().getIconWidth(),'w'),propTailleImage(simu.getList_cache_cible().get(j).getIco_cache_cible().getIconHeight(),'h'), null);
			}
			for(int j = 0; j < (simu.getList_point_faute().size()) ; j++) {
				g2.drawImage(simu.getList_point_faute().get(j).getImg_point_faute(),propTailleImage(simu.getList_point_faute().get(j).getX(),'w'),propTailleImage(simu.getList_point_faute().get(j).getY_reel(y_simu_dyn),'h'),propTailleImage(simu.getList_point_faute().get(j).getIco_point_faute().getIconWidth(),'w'),propTailleImage(simu.getList_point_faute().get(j).getIco_point_faute().getIconHeight(),'h'), null);
			}
			y_simu_dyn -=30;


		}
	}

	//On affiche les cibles pour le joueur
	public void afficherJoueurTir() {
		//on affiche la cible
		if(this.tir_joueur == true) {
			//ON affiche les cibles du joueur
			g2.drawImage(Joueur.cible_joueur.getImg_cible(),propTailleImage(280, 'w'),propTailleImage(119, 'h'), propTailleImage(Joueur.cible_joueur.getIco_cible().getIconWidth(), 'w'),propTailleImage(Joueur.cible_joueur.getIco_cible().getIconHeight(),'h'),null);
		}

	}

	//Affichage des physique de tout les biathletes
	public void afficherPhysique() {
		//On parcour la liste des performances
		ArrayList<Performance> list_performances_courrant_sort = Main.joueur.getCourse().getList_participants();
		for( Performance perf : list_performances_courrant_sort) {
			//On affiche (simple basique)
			g2.drawImage(perf.getPhysique().getImg_silhouette_courant(),propTailleImage(perf.getPhysique().getX_silhouette(), 'w'),propTailleImage(perf.getPhysique().getY_silhouette(), 'h'),propTailleImage(perf.getPhysique().getIco_silhouette().getIconWidth(), 'w'),propTailleImage(perf.getPhysique().getIco_silhouette().getIconHeight(), 'h'),null);
		}
	}






	@Override
	//Permet de dessiner les images
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//On utilise la taille maximale de l'ecran
		//Main.fenetre.setSize(arg0, arg1);

		//pas trop compris mais on va faire comme ca
		g.drawImage(bf_img, 0, 0, null);

		//PROVISOIR CA SERAIT BIEN DE LE METTRE DANS TIRJOUEUR
		//Actualisation de la position de la lunette
		this.aleaLunette();

		//Afficher le fond d'ecran
		this.afficherBackGround();

		this.afficherInterface();
		//Circuit
		//g2.drawImage(this.img_circuit,20,50,null);
		this.afficherInterface();

		//On affiche la cible du joueur
		this.afficherJoueurTir();

		//ON affiche les cache cibles du joueur
		this.afficherCacheCibleJoueur();

		//On actualise la position du fond
		this.deplacementFond();

		//Tri la liste des participants
		Main.joueur.getCourse().performancesSort();

		//PROVISOIR ***************************
		ico_bg_frise = new ImageIcon(getClass().getResource("/images/bgFrise.png"));
		this.img_bg_frise = this.ico_bg_frise.getImage();
		ico_bg_frise_continue = new ImageIcon(getClass().getResource("/images/bgFriseContinue.png"));
		this.img_bg_frise_continue = this.ico_bg_frise_continue.getImage();

		g2.drawImage(this.img_bg_frise_continue,propTailleImage(this.x_bg_frise_continue,'w'),propTailleImage(this.y_bg_frise_continue,'h'),propTailleImage(this.ico_bg_frise_continue.getIconWidth(),'w'),propTailleImage(this.ico_bg_frise_continue.getIconHeight(),'h'),null);
		g2.drawImage(this.img_bg_frise,propTailleImage(this.x_bg_frise1,'w'),propTailleImage(this.y_bg_frise1,'h'),propTailleImage(this.ico_bg_frise.getIconWidth(),'w'),propTailleImage(this.ico_bg_frise.getIconHeight(),'h'),null);
		g2.drawImage(this.img_bg_frise,propTailleImage(this.x_bg_frise2,'w'),propTailleImage(this.y_bg_frise2,'h'),propTailleImage(this.ico_bg_frise.getIconWidth(),'w'),propTailleImage(this.ico_bg_frise.getIconHeight(),'h'),null);

		//Determiner la position de tout les biathlete en fonction de la vue courrante
		this.navigationPhysique();

		//Affichage de tout les physiques
		this.afficherPhysique();
		//Lunette de tir		
		g2.drawImage(this.img_lunette,this.x_lunette,this.y_lunette,null);

		if (Main.joueur.getCourse() != null) {

			//Affichage des cibles de simulations
			this.afficherSimulationTir(Main.joueur.getCourse().getList_simulation_tir());

			//Afficher le classement selectionné 
			this.afficherClassement(Main.joueur.getCourse().getList_classement().get(this.indice_classement));
		}

	}








	/***** Getter et setter *****/
	public int getX_lunette() {return x_lunette;}

	public void setX_lunette(int x_lunette) {this.x_lunette = x_lunette;}

	public int getY_lunette() {return y_lunette;}

	public void setY_lunette(int y_lunette) {this.y_lunette = y_lunette;}

	public Graphics getG2() {return g2;}

	public void setG2(Graphics g2) {this.g2 = g2;}

	public int getX_souris() {
		return x_souris;
	}

	public void setX_souris(int x_souris) {
		this.x_souris = x_souris;
	}

	public int getY_souris() {
		return Y_souris;
	}

	public void setY_souris(int y_souris) {
		Y_souris = y_souris;
	}

	public double getMouvement_lunette() {
		return mouvement_lunette;
	}

	public void setMouvement_lunette(double d) {
		this.mouvement_lunette = d;
	}

	public int getX_reel() {return X_reel;}

	public void setX_reel(int x_reel) {X_reel = x_reel;}

	public int getY_reel() {return Y_reel;}

	public void setY_reel(int y_reel) {Y_reel = y_reel;}

	public int getxAlea() {return xAlea;}

	public void setxAlea(int xAlea) {this.xAlea = xAlea;}

	public int getyAlea() {return yAlea;}

	public void setyAlea(int yAlea) {this.yAlea = yAlea;}


	public int getDernier_affiche_classement() {
		return dernier_affiche_classement;
	}

	public void setDernier_affiche_classement(int dernier_affiche_classement) {
		this.dernier_affiche_classement = dernier_affiche_classement;
	}



	public ImageIcon getIco_circuit() {
		return ico_circuit;
	}

	public void setIco_circuit(ImageIcon ico_circuit) {
		this.ico_circuit = ico_circuit;
	}

	public Image getImg_circuit() {
		return img_circuit;
	}

	public void setImg_circuit(Image img_circuit) {
		this.img_circuit = img_circuit;
	}

	public ImageIcon getIco_lunette() {
		return ico_lunette;
	}

	public void setIco_lunette(ImageIcon ico_lunette) {
		this.ico_lunette = ico_lunette;
	}

	public Image getImg_lunette() {
		return img_lunette;
	}

	public void setImg_lunette(Image img_lunette) {
		this.img_lunette = img_lunette;
	}

	public ImageIcon getIco_bg_frise() {
		return ico_bg_frise;
	}

	public void setIco_bg_frise(ImageIcon ico_bg_frise) {
		this.ico_bg_frise = ico_bg_frise;
	}

	public Image getImg_bg_frise() {
		return img_bg_frise;
	}

	public void setImg_bg_frise(Image img_bg_frise) {
		this.img_bg_frise = img_bg_frise;
	}

	public ImageIcon getIco_bg_frise_continue() {
		return ico_bg_frise_continue;
	}

	public void setIco_bg_frise_continue(ImageIcon ico_bg_frise_continue) {
		this.ico_bg_frise_continue = ico_bg_frise_continue;
	}

	public Image getImg_bg_frise_continue() {
		return img_bg_frise_continue;
	}

	public void setImg_bg_frise_continue(Image img_bg_frise_continue) {
		this.img_bg_frise_continue = img_bg_frise_continue;
	}

	public int getX_bg_frise_continue() {
		return x_bg_frise_continue;
	}

	public void setX_bg_frise_continue(int x_bg_frise_continue) {
		this.x_bg_frise_continue = x_bg_frise_continue;
	}

	public int getY_bg_frise_continue() {
		return y_bg_frise_continue;
	}

	public void setY_bg_frise_continue(int y_bg_frise_continue) {
		this.y_bg_frise_continue = y_bg_frise_continue;
	}

	public int getId_vue_biathlete_courrant() {
		return id_vue_biathlete_courrant;
	}

	public void setId_vue_biathlete_courrant(int id_vue_biathlete_courrant) {
		this.id_vue_biathlete_courrant = id_vue_biathlete_courrant;
	}

	public int getPos_vue_biathlete_courrant() {
		return pos_vue_biathlete_courrant;
	}

	public void setPos_vue_biathlete_courrant(int pos_vue_biathlete_courrant) {
		this.pos_vue_biathlete_courrant = pos_vue_biathlete_courrant;
	}

	public int getDistance_entre_deux() {
		return distance_entre_deux;
	}

	public void setDistance_entre_deux(int distance_entre_deux) {
		this.distance_entre_deux = distance_entre_deux;
	}

	public InterfaceJoueur getInterfaceJoueur() {
		return interfaceJoueur;
	}

	public void setInterfaceJoueur(InterfaceJoueur interfaceJoueur) {
		this.interfaceJoueur = interfaceJoueur;
	}

	public int getRef_w() {
		return ref_w;
	}

	public int getRef_h() {
		return ref_h;
	}

	public BufferedImage getBf_img() {
		return bf_img;
	}

	public void setBf_img(BufferedImage bf_img) {
		this.bf_img = bf_img;
	}

	public int getY_membre() {
		return y_membre;
	}

	public void setY_membre(int y_membre) {
		this.y_membre = y_membre;
	}

	public boolean isTir_joueur() {
		return tir_joueur;
	}

	public void setTir_joueur(boolean tir_joueur) {
		this.tir_joueur = tir_joueur;
	}

	public int getIndice_classement() {
		return indice_classement;
	}

	public int getX_bg_frise1() {
		return x_bg_frise1;
	}

	public void setX_bg_frise1(int x_bg_frise1) {
		this.x_bg_frise1 = x_bg_frise1;
	}

	public int getY_bg_frise1() {
		return y_bg_frise1;
	}

	public void setY_bg_frise1(int y_bg_frise1) {
		this.y_bg_frise1 = y_bg_frise1;
	}

	public int getX_bg_frise2() {
		return x_bg_frise2;
	}

	public void setX_bg_frise2(int x_bg_frise2) {
		this.x_bg_frise2 = x_bg_frise2;
	}

	public int getY_bg_frise2() {
		return y_bg_frise2;
	}

	public void setY_bg_frise2(int y_bg_frise2) {
		this.y_bg_frise2 = y_bg_frise2;
	}

	public int getVueBiathleteCourrant() {
		return id_vue_biathlete_courrant;
	}

	public void setVueBiathleteCourrant(int vueBiathleteCourrant) {
		this.id_vue_biathlete_courrant = vueBiathleteCourrant;
	}

	public void setIndice_classement(int indice_classement) {
		this.indice_classement = indice_classement;
	}
}
