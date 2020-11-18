package com.biathlon.action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.biathlon.jeu.Main;

public class CreationCarriere extends InterfaceGraphique {


	//PANEL de la page
	private JPanel panel_header;
	private JPanel panel_content;
	private JPanel panel_footer;
	//Homme et femme
	private JPanel panel_homme;
	private JPanel panel_femme;
	//homme femme sous panel
	private JPanel panel_homme_info_photo;
	private JPanel panel_femme_info_photo;
	//homme femme sous sous panel
	private JPanel panel_homme_photo;
	private JPanel panel_homme_info;
	private JPanel panel_femme_photo;
	private JPanel panel_femme_info;

	//Panel homme
	private JPanel panel_homme_nom;
	private JPanel panel_homme_prenom;
	private JPanel panel_homme_age;
	private JPanel panel_homme_specialite;
	private JPanel panel_homme_favorite;
	private JPanel panel_homme_button_fleche;
	private JPanel panel_homme_parcourir_photo;

	//Panel Femme
	private JPanel panel_femme_nom;
	private JPanel panel_femme_prenom;
	private JPanel panel_femme_age;
	private JPanel panel_femme_specialite;
	private JPanel panel_femme_favorite;
	private JPanel panel_femme_button_fleche;
	private JPanel panel_femme_parcourir_photo;

	//Header
	private JLabel label_titre;
	private JLabel label_description;
	private JLabel label_choix_pays;
	private JComboBox combobox_pays;
	private JPanel panel_pays;
	private JLabel label_pays_image;


	//homme
	private JLabel label_homme_titre;
	private JLabel label_homme_nom;
	private JLabel label_homme_prenom;
	private JTextField textfield_homme_nom;
	private JTextField textfield_homme_prenom;
	private JLabel label_homme_age;
	private JLabel label_homme_specialite;
	private JLabel label_homme_favorite;
	private JLabel label_homme_photo;
	private JComboBox combobox_homme_age;
	private JComboBox combobox_homme_specialite;
	private JComboBox combobox_homme_favorite;
	private JButton button_homme_photo;
	private JButton button_homme_image_droite;
	private JButton button_homme_image_gauche;
	private ArrayList<ImageIcon> list_ico_homme_photo;
	private JButton button_homme_alea;

	//femme
	private JLabel label_femme_titre;
	private JLabel label_femme_nom;
	private JLabel label_femme_prenom;
	private JTextField textfield_femme_nom;
	private JTextField textfield_femme_prenom;
	private JLabel label_femme_age;
	private JLabel label_femme_specialite;
	private JLabel label_femme_favorite;
	private JLabel label_femme_photo;
	private JComboBox combobox_femme_age;
	private JComboBox combobox_femme_specialite;
	private JComboBox combobox_femme_favorite;
	private JButton button_femme_photo;
	private JButton button_femme_image_droite;
	private JButton button_femme_image_gauche;
	private ArrayList<ImageIcon> list_ico_femme_photo;
	private JButton button_femme_alea;

	//dimensions images femme/homme
	private final int dim_photo_w = 250;
	private final int dim_photo_h = 250;

	private final Object[] list_age = new Object[] {"18","19","20","21"};
	private final Object[] list_specialite = new Object[] {"Tirs couché","Tirs debout","Ski","Vitesse de tirs"};
	private final Object[] list_favorite = new Object[] {"Sprint","Mass Start", "Poursuite", "Individuelle","Relay"};
	
	//footer
	private JButton button_valider;
	private JButton button_retour;



	public CreationCarriere() {
		super();

		//On instancie les objets
		this.creerObjet();
		//On ajoute les objets a leur panel
		this.positionnerObjet();

	}



	public void creerObjet() {
		//3 borderlayout
		panel_content = new JPanel();
		panel_header = new JPanel();
		panel_footer = new JPanel();
		//sous panel homme et femme
		panel_homme_info_photo = new JPanel();
		panel_femme_info_photo = new JPanel();
		//sous sous panel homme et femme
		panel_homme_photo = new JPanel();
		panel_femme_photo = new JPanel();
		panel_homme_info = new JPanel();
		panel_femme_info = new JPanel();

		//panel homme
		panel_homme_nom = new JPanel();
		panel_homme_prenom = new JPanel();
		panel_homme_age = new JPanel();
		panel_homme_specialite = new JPanel();
		panel_homme_favorite = new JPanel();
		panel_homme_button_fleche= new JPanel();
		panel_homme_parcourir_photo= new JPanel();
		//Panel Femme
		panel_femme_nom = new JPanel();
		panel_femme_prenom = new JPanel();
		panel_femme_age = new JPanel();
		panel_femme_specialite = new JPanel();
		panel_femme_favorite = new JPanel();
		panel_femme_button_fleche= new JPanel();
		panel_femme_parcourir_photo= new JPanel();

		//Homme femme
		panel_homme = new JPanel();
		panel_femme = new JPanel();
		//Panel pour les pays
		panel_pays = new JPanel();

		//Header
		label_titre = titreLabelStyle(new JLabel("Créez vos Biathlètes"));
		label_description = labelStyle(new JLabel("Test"));
		label_choix_pays = sousTitreLabelStyle(new JLabel("Nationalité que vous dirigerez : "));
		combobox_pays = comboboxStyle(this.creerComboBoxPays());
		label_pays_image = new JLabel(new ImageIcon(getClass().getResource("/images/drapeau/grand/allemagne.png")));

		//footer
		button_valider = mediumButtonStyle(new JButton("Valider"));
		button_retour = mediumButtonStyle(new JButton("Retour"));

		//homme
		label_homme_titre = titreLabelStyle(new JLabel("Mon biathlète homme"));
		label_homme_prenom = sousTitreLabelStyle(new JLabel("Prénom : "));
		label_homme_nom = sousTitreLabelStyle(new JLabel("Nom : "));
		label_homme_age = sousTitreLabelStyle(new JLabel("Age : "));
		label_homme_specialite = sousTitreLabelStyle(new JLabel("Spécialité : "));
		label_homme_favorite = sousTitreLabelStyle(new JLabel("Course favorite : "));
		textfield_homme_prenom = textFieldStyle(new JTextField());
		textfield_homme_nom = textFieldStyle(new JTextField());
		combobox_homme_age = comboboxStyle(new JComboBox(list_age));
		combobox_homme_specialite = comboboxStyle(new JComboBox(list_specialite));
		combobox_homme_favorite = comboboxStyle(new JComboBox(list_favorite));
		button_homme_alea = mediumButtonStyle(new JButton("Aléatoire"));

		label_homme_photo = sousTitreLabelStyle(new JLabel("Charger une photo : "));
		button_homme_image_gauche = new JButton("<");
		list_ico_homme_photo = this.setListPhotoDefaut("homme");
		button_homme_image_droite = new JButton(">");
		button_homme_photo = smallButtonStyle(new JButton("Parcourir"));

		//Femme
		label_femme_titre = titreLabelStyle(new JLabel("Mon biathlète femme"));
		label_femme_prenom = sousTitreLabelStyle(new JLabel("Prénom : "));
		label_femme_nom = sousTitreLabelStyle(new JLabel("Nom : "));
		label_femme_age = sousTitreLabelStyle(new JLabel("Age : "));
		label_femme_specialite = sousTitreLabelStyle(new JLabel("Spécialité : "));
		label_femme_favorite = sousTitreLabelStyle(new JLabel("Course favorite : "));
		textfield_femme_prenom = textFieldStyle(new JTextField());
		textfield_femme_nom = textFieldStyle(new JTextField());
		combobox_femme_age = comboboxStyle(new JComboBox(list_age));
		combobox_femme_specialite = comboboxStyle(new JComboBox(list_specialite));
		combobox_femme_favorite = comboboxStyle(new JComboBox(list_favorite));
		button_femme_alea = mediumButtonStyle(new JButton("Aléatoire"));

		label_femme_photo = sousTitreLabelStyle(new JLabel("Charger une photo : "));
		button_femme_photo = smallButtonStyle(new JButton("Parcourir"));
		button_femme_image_gauche = new JButton("<");
		list_ico_femme_photo = this.setListPhotoDefaut("femme");
		button_femme_image_droite = new JButton(">");
	}

	public void positionnerObjet() {
		//On rempli le label de pays 
		panel_pays.setLayout(new FlowLayout(FlowLayout.LEFT, 30,10));
		panel_pays.add(label_choix_pays);
		panel_pays.add(combobox_pays);
		panel_pays.add(label_pays_image);


		panel_pays.setBackground(color_bg);
		//On rempli le header
		panel_header.setLayout(new GridLayout(3,1));
		panel_header.add(label_titre);
		panel_header.add(label_description);
		panel_header.add(panel_pays);
		panel_header.setBackground(color_bg);


		//on rempli le footer
		panel_footer.setLayout(new FlowLayout(FlowLayout.CENTER , 50,10));
		panel_footer.add(button_valider);
		panel_footer.add(button_retour);
		panel_footer.setBackground(color_bg);


		//Rempli chaque panel homme
		panel_homme_prenom.setLayout(new FlowLayout(FlowLayout.RIGHT, 10,10));
		panel_homme_prenom.add(label_homme_prenom);
		panel_homme_prenom.add(textfield_homme_prenom);
		panel_homme_nom.setLayout(new FlowLayout(FlowLayout.RIGHT, 10,10));
		panel_homme_nom.add(label_homme_nom);
		panel_homme_nom.add(textfield_homme_nom);
		panel_homme_age.setLayout(new FlowLayout(FlowLayout.RIGHT, 10,10));
		panel_homme_age.add(label_homme_age);
		panel_homme_age.add(combobox_homme_age);
		panel_homme_specialite.setLayout(new FlowLayout(FlowLayout.RIGHT, 10,10));
		panel_homme_specialite.add(label_homme_specialite);
		panel_homme_specialite.add(combobox_homme_specialite);
		panel_homme_favorite.add(label_homme_favorite);
		panel_homme_favorite.add(combobox_homme_favorite);
		//sous panel pour photo
		panel_homme_button_fleche.setLayout(new FlowLayout(FlowLayout.RIGHT, 10,10));
		panel_homme_button_fleche.add(button_homme_image_gauche);
		panel_homme_button_fleche.add(button_homme_image_droite);
		panel_homme_parcourir_photo.setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
		panel_homme_parcourir_photo.add(label_homme_photo);
		panel_homme_parcourir_photo.add(button_homme_photo);

		//panel homme photo
		panel_homme_photo.setLayout(new GridBagLayout());
		panel_homme_photo.add(new JLabel(this.scaleImage(list_ico_homme_photo.get(1), dim_photo_w,dim_photo_h))  , gbc(0,0,1,4));
		panel_homme_photo.add(panel_homme_button_fleche, gbc(0,5,1,1));
		panel_homme_photo.add(panel_homme_parcourir_photo,gbc(0,6,1,1));
		panel_homme_photo.add(button_homme_alea, gbc(0,7,1,1));
		//Panel Homme info
		panel_homme_info.setLayout(new GridLayout(5,1));
		panel_homme_info.add(panel_homme_prenom);
		panel_homme_info.add(panel_homme_nom);
		panel_homme_info.add(panel_homme_age);
		panel_homme_info.add(panel_homme_specialite);
		panel_homme_info.add(panel_homme_favorite);

		//On rempli homme
		panel_homme_info_photo.setLayout(new GridLayout(1,2));
		panel_homme_info_photo.add(panel_homme_photo);
		panel_homme_info_photo.add(panel_homme_info);
		panel_homme.setLayout(new BorderLayout());
		panel_homme.add(label_homme_titre,BorderLayout.NORTH);
		panel_homme.add(panel_homme_info_photo,BorderLayout.CENTER);

		//On Change le background
		panel_homme_age.setBackground(color_bg);
		panel_homme_photo.setBackground(color_bg);
		panel_homme_specialite.setBackground(color_bg);
		panel_homme_favorite.setBackground(color_bg);
		panel_homme_prenom.setBackground(color_bg);
		panel_homme_nom.setBackground(color_bg);
		panel_homme_info.setBackground(color_bg);
		panel_homme.setBackground(color_bg);
		panel_homme_button_fleche.setBackground(color_bg);
		panel_homme_parcourir_photo.setBackground(color_bg);



		//Rempli chaque panel femme
		panel_femme_prenom.setLayout(new FlowLayout(FlowLayout.RIGHT, 10,10));
		panel_femme_prenom.add(label_femme_prenom);
		panel_femme_prenom.add(textfield_femme_prenom);
		panel_femme_nom.setLayout(new FlowLayout(FlowLayout.RIGHT, 10,10));
		panel_femme_nom.add(label_femme_nom);
		panel_femme_nom.add(textfield_femme_nom);
		panel_femme_age.setLayout(new FlowLayout(FlowLayout.RIGHT, 10,10));
		panel_femme_age.add(label_femme_age);
		panel_femme_age.add(combobox_femme_age);
		panel_femme_specialite.setLayout(new FlowLayout(FlowLayout.RIGHT, 10,10));
		panel_femme_specialite.add(label_femme_specialite);
		panel_femme_specialite.add(combobox_femme_specialite);
		//sous panel pour photo
		panel_femme_button_fleche.setLayout(new FlowLayout(FlowLayout.RIGHT, 10,10));
		panel_femme_button_fleche.add(button_femme_image_gauche);
		panel_femme_button_fleche.add(button_femme_image_droite);
		panel_femme_parcourir_photo.setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
		panel_femme_parcourir_photo.add(label_femme_photo);
		panel_femme_parcourir_photo.add(button_femme_photo);

		//panel femme photo
		panel_femme_photo.setLayout(new GridBagLayout());
		panel_femme_photo.add(new JLabel(this.scaleImage(list_ico_femme_photo.get(0), dim_photo_w,dim_photo_h))  , gbc(0,0,1,4));
		panel_femme_photo.add(panel_femme_button_fleche, gbc(0,5,1,1));
		panel_femme_photo.add(panel_femme_parcourir_photo,gbc(0,6,1,1));
		panel_femme_photo.add(button_femme_alea, gbc(0,7,1,1));
		//Panel femme info
		panel_femme_info.setLayout(new GridLayout(4,1));
		panel_femme_info.add(panel_femme_prenom);
		panel_femme_info.add(panel_femme_nom);
		panel_femme_info.add(panel_femme_age);
		panel_femme_info.add(panel_femme_specialite);

		//On rempli femme
		panel_femme_info_photo.setLayout(new GridLayout(1,2));
		panel_femme_info_photo.add(panel_femme_photo);
		panel_femme_info_photo.add(panel_femme_info);
		panel_femme.setLayout(new BorderLayout());
		panel_femme.add(label_femme_titre,BorderLayout.NORTH);
		panel_femme.add(panel_femme_info_photo,BorderLayout.CENTER);

		//On Change le background
		panel_femme_age.setBackground(color_bg);
		panel_femme_photo.setBackground(color_bg);
		panel_femme_specialite.setBackground(color_bg);
		panel_femme_prenom.setBackground(color_bg);
		panel_femme_nom.setBackground(color_bg);
		panel_femme_info.setBackground(color_bg);
		panel_femme.setBackground(color_bg);
		panel_femme_button_fleche.setBackground(color_bg);
		panel_femme_parcourir_photo.setBackground(color_bg);

		//Content
		panel_content.setLayout(new GridLayout(1,2));
		panel_content.add(panelStyle(panel_homme));
		panel_content.add(panelStyle(panel_femme));
		panel_content.setBackground(color_bg);

		this.setLayout(new BorderLayout());
		this.add(panel_header,BorderLayout.NORTH);
		this.add(panel_content,BorderLayout.CENTER);
		this.add(panel_footer,BorderLayout.SOUTH);
	}

	public GridBagConstraints gbc(int x, int y, int poidsx, int poidsy) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = poidsy ;
		c.gridwidth = poidsx ;
		c.gridy = y;
		c.gridx = x;
		return c;
	}
	
	public ArrayList<ImageIcon> setListPhotoDefaut(String sexe) {
		ArrayList<ImageIcon> list_photo = new ArrayList<ImageIcon>();
		int taille_fic;
		/*
		File file = new File("C:/Users/Charlie/git/Biathlon/Jeu Biathlon/src/images/drapeau/icone");
		taille_fic = file.list().length;
		 */

		taille_fic = 3 ;
		for(int i = 1 ; i <= taille_fic; i++) {
			System.out.println("/images/photo/"+sexe+"/photo"+i+".png");
			list_photo.add(new ImageIcon(getClass().getResource("/images/photo/"+sexe+"/photo"+i+".png")));
		}

		return list_photo;
	}

	public JComboBox creerComboBoxPays() {
		//Vecteur a remplir lors de la requette
		Vector list_pays = new Vector();

		// Résultat de la rquete sur les parties existantes
		ResultSet resultat = Main.database.requete("SELECT libelle_equipe FROM Equipes");

		try {
			while(resultat.next()) {
				//Remplire le vecteur de partie a charger
				list_pays.add(resultat.getString("libelle_equipe"));
			}
		} catch (SQLException e) {e.printStackTrace();}

		return new JComboBox(list_pays);
	}

}
