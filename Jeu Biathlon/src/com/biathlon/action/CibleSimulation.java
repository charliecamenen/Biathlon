package com.biathlon.action;

import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.biathlon.jeu.Main;

public class CibleSimulation extends Cible{
	
	
	
	//Drapeau
	protected ImageIcon ico_drapeau;
	protected Image img_drapeau;
	protected final int x_drapeau = ref_pos_x + 270;
	
	//Point faute
	protected ArrayList<PointFauteSimulation> list_point_faute;
	protected int y_point_faute;
	protected int y_point_faute_dyn; //Pour calculer l'espace entre les points
	protected int x_point_faute = ref_pos_x + 133;
	
	//libelle du biathlete
	protected String libelle_biathlete;
	protected int y_libelle_biathlete;
	protected final int x_libelle_biathlete = ref_pos_x + 140;
	
	//emplacement des balles de pioches
	protected ImageIcon ico_emp_pioche;
	protected Image img_emp_pioche;
	protected int y_emp_pioche;
	protected final int x_emp_pioche = ref_pos_x - 45;
	
	//balle de pioche
	protected ArrayList<BalleDePioche> list_balle_pioche;
	protected int y_balle_pioche;
	protected int x_balle_pioche = ref_pos_x - 40;
	
	
	//Décalage entre deux simulation en y
	protected final int y_decalage = 30;
	
	public CibleSimulation(int id_biathlete) {
		//Création des cibles de simulation
		ico_cible_blanc = new ImageIcon(getClass().getResource("/images/ciblesSimulation.png"));
		this.img_cible_blanc = this.ico_cible_blanc.getImage();

		//Création des cibles de simulation
		ico_cible_rouge = new ImageIcon(getClass().getResource("/images/ciblesSimulationRouge.png"));
		this.img_cible_rouge = this.ico_cible_rouge.getImage();

		//On initialise la cible a blanc
		this.ico_cible = this.ico_cible_blanc;
		this.img_cible = this.img_cible_blanc;

		//Création du drapeau
		ResultSet resultset = Main.database.requete(""
				+ "SELECT biathletes.libelle_biathlete, pays.drapeau_file " 
				+ "FROM biathletes join equipes on (biathletes.id_equipe = equipes.id_equipe) " 
				+ "join pays on (equipes.id_pays = pays.id_pays) " 
				+ "WHERE id_biathlete = " + id_biathlete);

		try {
			while(resultset.next()) {
				this.libelle_biathlete = resultset.getString("libelle_biathlete").toUpperCase();
				this.ico_drapeau = new ImageIcon(getClass().getResource(resultset.getString("drapeau_file")));
				this.img_drapeau = this.ico_drapeau.getImage();
			}
		} catch (SQLException e) {e.printStackTrace();}

		list_cache_cible = new ArrayList<>();
		list_point_faute = new ArrayList<>();
		this.y_point_faute_dyn = 0;
		this.id_biathlete = id_biathlete;
		this.cible_visee = 0;
		this.vec_boool_blanchi = new ArrayList<>();

		//On initialise toute les cibles à false
		for ( int i = 0 ; i <5 ; i++) {
			this.vec_boool_blanchi.add(new Boolean(false));
		}
	}
	
	public void decrY() {
		this.y_cache_cible += y_decalage;
		this.y_point_faute += y_decalage;
		this.y_cible += y_decalage;
		this.y_libelle_biathlete +=y_decalage;
		this.y_balle_pioche +=y_decalage;
		this.y_emp_pioche +=y_decalage;
		
		for (int i = 0 ; i < list_cache_cible.size(); i++) {
			list_cache_cible.get(i).setY(list_cache_cible.get(i).getY() + y_decalage);
		}
		/*
		for (int i = 0 ; i < list_point_faute.size(); i++) {
			list_point_faute.get(i).setY(list_point_faute.get(i).getY() + 30);
		}*/
		if(list_balle_pioche !=null) {
			for (int i = 0 ; i < list_balle_pioche.size(); i++) {
				list_balle_pioche.get(i).setY(list_balle_pioche.get(i).getY() + y_decalage);
			}
		}
	}
	

	public void creerBallesPioches() {
		//Création de l'emplacement de balle de pioche
		ico_emp_pioche = new ImageIcon(getClass().getResource("/images/emplacementBallePioche.png"));;
		img_emp_pioche = this.ico_emp_pioche.getImage();

		this.list_balle_pioche = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			BalleDePiocheSimulation bdp = new BalleDePiocheSimulation(this.x_balle_pioche,  this.y_balle_pioche - i*8  );
			this.list_balle_pioche.add(bdp);
		}
	}
	
	public void retirerBallePioche() {
		//On supprime le dernier element de la liste
		this.list_balle_pioche.remove(this.list_balle_pioche.size()-1);
	}
	
	/*permet d'ajouter un cache sur la cible*/
	public void ajouterResultatTir(boolean resultat) {
		//decalage horrizontale du cache
		int cache_decalage = 25;
		
		//On déplace la position du cache a ajouter
		x_cache_cible += cache_decalage;
		
		//Si on arrive au dela du tableau (cela signifie que toute les cibles n(ont pas été blanchie)
		if (cible_visee > 4) {
			//alors on revient a la premiere cible
			cible_visee = 0;
			x_cache_cible = ref_pos_x -22 + cache_decalage;
		}
		
		//on charche une cible non blanchis a partir de la cible visée
		while(vec_boool_blanchi.get(cible_visee) == true) {
			
			//On passe a la cible suivante
			cible_visee = cible_visee +1;
			//On incrémente la position du cache
			x_cache_cible += cache_decalage;
			
			//Si on arrive sur la derniere cible
			if (cible_visee > 4) {
				//alors on revient a la premiere cible
				cible_visee = 0;
				x_cache_cible = ref_pos_x-22 + cache_decalage;
			}
		}
		
		
		//Si tir reussi
		if (resultat == true) {
			//On passe a true la cible en question
			this.vec_boool_blanchi.set(cible_visee, new Boolean(true));
			
			//On ajoute un cache a la simulation
			CacheCibleSimulation cache_cible = new CacheCibleSimulation(x_cache_cible, this.y_cache_cible);
			this.list_cache_cible.add(cache_cible);
		} else {
			//si non :
			//on affiche la cible en rouge
			this.img_cible = this.img_cible_rouge;
			
			y_point_faute_dyn += 4;
			PointFauteSimulation point_faute = new PointFauteSimulation(x_point_faute, y_point_faute_dyn);			//On ajoute un point jaune 
			this.list_point_faute.add(point_faute);
		}
		
		cible_visee += 1;
	}

	public ImageIcon getIco_drapeau() {
		return ico_drapeau;
	}

	public void setIco_drapeau(ImageIcon ico_drapeau) {
		this.ico_drapeau = ico_drapeau;
	}

	public Image getImg_drapeau() {
		return img_drapeau;
	}

	public void setImg_drapeau(Image img_drapeau) {
		this.img_drapeau = img_drapeau;
	}

	public ImageIcon getIco_emp_pioche() {
		return ico_emp_pioche;
	}

	public void setIco_emp_pioche(ImageIcon ico_emp_pioche) {
		this.ico_emp_pioche = ico_emp_pioche;
	}

	public Image getImg_emp_pioche() {
		return img_emp_pioche;
	}

	public void setImg_emp_pioche(Image img_emp_pioche) {
		this.img_emp_pioche = img_emp_pioche;
	}

	public int getY_emp_pioche() {
		return y_emp_pioche;
	}

	public void setY_emp_pioche(int y_emp_pioche) {
		this.y_emp_pioche = y_emp_pioche;
	}

	public ArrayList<BalleDePioche> getList_balle_pioche() {
		return list_balle_pioche;
	}

	public void setList_balle_pioche(ArrayList<BalleDePioche> list_balle_pioche) {
		this.list_balle_pioche = list_balle_pioche;
	}

	public int getY_balle_pioche() {
		return y_balle_pioche;
	}

	public void setY_balle_pioche(int y_balle_pioche) {
		this.y_balle_pioche = y_balle_pioche;
	}

	public int getX_balle_pioche() {
		return x_balle_pioche;
	}

	public void setX_balle_pioche(int x_balle_pioche) {
		this.x_balle_pioche = x_balle_pioche;
	}

	public int getX_emp_pioche() {
		return x_emp_pioche;
	}

	public ArrayList<PointFauteSimulation> getList_point_faute() {
		return list_point_faute;
	}

	public void setList_point_faute(ArrayList<PointFauteSimulation> list_point_faute) {
		this.list_point_faute = list_point_faute;
	}

	public int getY_point_faute() {
		return y_point_faute;
	}

	public void setY_point_faute(int y_point_faute) {
		this.y_point_faute = y_point_faute;
	}

	public int getY_point_faute_dyn() {
		return y_point_faute_dyn;
	}

	public void setY_point_faute_dyn(int y_point_faute_dyn) {
		this.y_point_faute_dyn = y_point_faute_dyn;
	}

	public int getX_point_faute() {
		return x_point_faute;
	}

	public void setX_point_faute(int x_point_faute) {
		this.x_point_faute = x_point_faute;
	}

	public int getCible_visee() {
		return cible_visee;
	}

	public void setCible_visee(int cible_visee) {
		this.cible_visee = cible_visee;
	}

	public ArrayList<Boolean> getVec_boool_blanchi() {
		return vec_boool_blanchi;
	}

	public void setVec_boool_blanchi(ArrayList<Boolean> vec_boool_blanchi) {
		this.vec_boool_blanchi = vec_boool_blanchi;
	}

	public String getLibelle_biathlete() {
		return libelle_biathlete;
	}

	public void setLibelle_biathlete(String libelle_biathlete) {
		this.libelle_biathlete = libelle_biathlete;
	}

	public int getY_libelle_biathlete() {
		return y_libelle_biathlete;
	}

	public void setY_libelle_biathlete(int y_libelle_biathlete) {
		this.y_libelle_biathlete = y_libelle_biathlete;
	}

	public int getX_drapeau() {
		return x_drapeau;
	}

	public int getX_libelle_biathlete() {
		return x_libelle_biathlete;
	}

	@Override
	public void ajouterResultatTir(boolean resultat, int num_cible) {
		// TODO Auto-generated method stub
		
	}

	
}
