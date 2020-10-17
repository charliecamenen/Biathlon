package com.biathlon.jeu;

import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;

import javax.swing.ImageIcon;

import com.biathlon.action.Biathlete;
import com.biathlon.action.Performance;

public class MembreClassement {

	//Position dans le classement
	private int num_classement;
	private int y_num_classement;
	private final int x_num_classement = 463; 

	//Libelle du biathlete
	private String libelle_biathlete;
	private int y_libelle_biathlete;
	private final int x_libelle_biathlete = 508; 
	
	//Nombre d'erreur du biathlete
	private int erreur;
	private int y_erreur;
	private final int x_erreur =635;
	
	//fond du nombre d'erreur du biathlete
	private ImageIcon ico_bg_erreur;
	private Image img_bg_erreur;
	private int y_bg_erreur;
	private final int x_bg_erreur = 625;
	
	//Temps ou retard de l'athlete
	private long temps;
	private int y_temps;
	private final int x_temps = 663;

	//Retard de l'athlete
	private long retard;
	private int y_retard;
	private final int x_retard = 673;

	//Background membre
	private ImageIcon ico_bg;
	private Image img_bg;
	private int y_bg;
	private final int x_bg = 453; 

	//Drapeau
	private ImageIcon ico_drapeau;
	private Image img_drapeau;
	private int y_drapeau;
	private final int x_drapeau = 486;


	public MembreClassement(Biathlete biathlete, long temps, int erreur) {
		super();
		//On créé l'image de background
		this.ico_bg = new ImageIcon(getClass().getResource("/images/backgroundMembreClassement.png"));
		this.img_bg = this.ico_bg.getImage();
		
		//On créé l'image de background
		this.ico_bg_erreur = new ImageIcon(getClass().getResource("/images/erreurBg.png"));
		this.img_bg_erreur = this.ico_bg_erreur.getImage();

		//Création du drapeau
		ResultSet resultset = Main.database.requete(""
				+ "SELECT pays.drapeau_file " 
				+ "FROM biathletes join equipes on (biathletes.id_equipe = equipes.id_equipe) " 
				+ "join pays on (equipes.id_pays = pays.id_pays) " 
				+ "WHERE id_biathlete = " + biathlete.getId());

		try {
			while(resultset.next()) {
				//ImageIcon icon = new ImageIcon(new ImageIcon("tonImage.jpg").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)); 
				this.ico_drapeau = new ImageIcon(getClass().getResource(resultset.getString("drapeau_file")));
				this.img_drapeau = this.ico_drapeau.getImage();
			}
		} catch (SQLException e) {e.printStackTrace();}

		//Nom du biathlete
		this.libelle_biathlete = biathlete.getLibelle().toUpperCase();

		//Temps de passage
		this.temps = temps;
		
		//nombre de fautes
		this.erreur = erreur;
	}

	public void calculRetard(long temps_premier) {
		//Le retard est actualisé 
		this.retard = this.temps - temps_premier;
	}

	
	public String getStrNumClassement() {
		return "" + this.num_classement;
	}
	
	public String getStrErreur() {
		return "" + this.erreur;
	}


	public int getY_num_classement() {
		return y_num_classement;
	}

	public void setY_num_classement(int y_num_classement) {
		this.y_num_classement = y_num_classement;
	}

	public int getY_libelle_biathlete() {
		return y_libelle_biathlete;
	}

	public void setY_libelle_biathlete(int y_libelle_biathlete) {
		this.y_libelle_biathlete = y_libelle_biathlete;
	}

	public int getY_temps() {
		return y_temps;
	}

	public long getErreur() {
		return erreur;
	}

	public void setErreur(int erreur) {
		this.erreur =  erreur;
	}

	public int getY_erreur() {
		return y_erreur;
	}

	public void setY_erreur(int y_erreur) {
		this.y_erreur = y_erreur;
	}

	public ImageIcon getIco_bg_erreur() {
		return ico_bg_erreur;
	}

	public void setIco_bg_erreur(ImageIcon ico_bg_erreur) {
		this.ico_bg_erreur = ico_bg_erreur;
	}

	public Image getImg_bg_erreur() {
		return img_bg_erreur;
	}

	public void setImg_bg_erreur(Image img_bg_erreur) {
		this.img_bg_erreur = img_bg_erreur;
	}

	public int getY_bg_erreur() {
		return y_bg_erreur;
	}

	public void setY_bg_erreur(int y_bg_erreur) {
		this.y_bg_erreur = y_bg_erreur;
	}

	public static Comparator<MembreClassement> getTriTemps() {
		return triTemps;
	}

	public static void setTriTemps(Comparator<MembreClassement> triTemps) {
		MembreClassement.triTemps = triTemps;
	}

	public int getX_erreur() {
		return x_erreur;
	}

	public int getX_bg_erreur() {
		return x_bg_erreur;
	}

	public void setY_temps(int y_temps) {
		this.y_temps = y_temps;
	}

	public float getRetard() {
		return retard;
	}

	public void setRetard(long retard) {
		this.retard = retard;
	}

	public int getY_retard() {
		return y_retard;
	}

	public void setY_retard(int y_retard) {
		this.y_retard = y_retard;
	}

	public int getX_num_classement() {
		return x_num_classement;
	}

	public int getX_libelle_biathlete() {
		return x_libelle_biathlete;
	}

	public int getX_temps() {
		return x_temps;
	}

	public int getX_retard() {
		return x_retard;
	}

	public int getNum_classement() {
		return num_classement;
	}
	public void setNum_classement(int num_classement) {
		this.num_classement = num_classement;
	}
	public String getLibelle_biathlete() {
		return libelle_biathlete;
	}
	public void setLibelle_biathlete(String libelle_biathlete) {
		this.libelle_biathlete = libelle_biathlete;
	}
	public float getTemps() {
		return temps;
	}
	public void setTemps(long temps) {
		this.temps = temps;
	}
	public ImageIcon getIco_bg() {
		return ico_bg;
	}
	public void setIco_bg(ImageIcon ico_bg) {
		this.ico_bg = ico_bg;
	}
	public Image getImg_bg() {
		return img_bg;
	}
	public void setImg_bg(Image img_bg) {
		this.img_bg = img_bg;
	}
	public int getY_bg() {
		return y_bg;
	}
	public void setY_bg(int y_bg) {
		this.y_bg = y_bg;
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
	public int getY_drapeau() {
		return y_drapeau;
	}
	public void setY_drapeau(int y_drapeau) {
		this.y_drapeau = y_drapeau;
	}
	public int getX_bg() {
		return x_bg;
	}
	public int getX_drapeau() {
		return x_drapeau;
	} 

	public static Comparator<MembreClassement> triTemps = new Comparator<MembreClassement>() {
		public int compare (MembreClassement p2,MembreClassement p1) {
			Float nbp1 = p1.getTemps();
			Float nbp2 = p2.getTemps();
			int resultat;
			resultat=nbp1.compareTo(nbp2);
			return resultat;
		}
	};


}
