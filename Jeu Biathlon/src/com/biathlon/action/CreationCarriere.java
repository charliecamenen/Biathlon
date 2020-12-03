package com.biathlon.action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.rmi.CORBA.Util;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;

import com.biathlon.jeu.Accueil;
import com.biathlon.jeu.Main;

public class CreationCarriere extends InterfaceGraphique {



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
	private JPanel panel_pays_gauche;
	private JPanel panel_pays_droite;	
	private JLabel label_pays_image;
	private JButton button_alea;
	
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

	//dimensions images femme/homme
	private final int dim_photo_w = 250;
	private final int dim_photo_h = 250;
	//Contenue des listes déroulantes
	private final Object[] list_age = new Object[] {"18","19","20","21"};
	private final Object[] list_specialite = new Object[] {"Tirs couché","Tirs debout","Ski","Vitesse de tirs"};
	private final Object[] list_favorite = new Object[] {"Sprint","Mass Start", "Poursuite", "Individuelle","Relay"};
	//indices des images homme femme dans la liste
	private int ind_photo_homme = 0;
	private int ind_photo_femme = 0;

	//footer
	private JButton button_valider;
	private JButton button_retour;

	public CreationCarriere() {
		super("/images/background/novemesto.png");

		//On instancie les objets
		this.creerObjet();
		//On ajoute les objets a leur panel
		this.afficheBorderElement();

		button_homme_photo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//Fenetre de choix d'image
				JFileChooser dialogue = new JFileChooser(new File("."));
				//Ajoute un filtre pour les PNG uniquement
				String[] extensions_filtre = new String[] {"png"};
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images (.png)", extensions_filtre);                                
				dialogue.addChoosableFileFilter(filter);
				//Supprime la possibilité d'ouvrir tout les types
				dialogue.setAcceptAllFileFilterUsed(false);
				//Affichage du chein dans la barre de recherhe
				PrintWriter sortie;
				//Chemin du fichier
				String fichier_path;

				if (dialogue.showOpenDialog(null)== JFileChooser.APPROVE_OPTION) {
					//On réccupere le chemin
					fichier_path = dialogue.getSelectedFile().getPath();
					
			//		System.out.println(fichier_path);
					try {
						//On print le chemin
						sortie = new PrintWriter(new FileWriter(fichier_path, true));
						
						//Image chargé
						ImageIcon image_charge = new ImageIcon(fichier_path);
						
						//Ajoute l'image a la liste 
						list_ico_homme_photo.add(image_charge);
						
						//On la converti en BufferImage
						BufferedImage bi = ImageIO.read(new File(fichier_path));
						
						//On defini le chemin dans lequel enregistrer
						String path = getClass().getResource("/images/photo/homme/").getPath().replace("%20", " ");
		
						//Enregistre l'image dans le dossier bin
						ImageIO.write(bi, "png", new File(path+"/photo"+list_ico_homme_photo.size()+".png"));

						//Mise a jour l'interface panel homme photo
						ind_photo_homme = list_ico_homme_photo.size()-1;
						
						//Met a jour l'interface
						updateInterface(panel_homme_photo,new JLabel( scaleImage(list_ico_homme_photo.get(ind_photo_homme), dim_photo_w,dim_photo_h)) ,0);

						//On ferme la fenetre a la validation
						sortie.close();
					} catch (IOException e) {e.printStackTrace();}

				}
			}
		}); 

		button_femme_photo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//Fenetre de choix d'image
				JFileChooser dialogue = new JFileChooser(new File("."));
				//Ajoute un filtre pour les PNG uniquement
				String[] extensions_filtre = new String[] {"png"};
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images (.png)", extensions_filtre);                                
				dialogue.addChoosableFileFilter(filter);
				//Supprime la possibilité d'ouvrir tout les types
				dialogue.setAcceptAllFileFilterUsed(false);
				//Affichage du chein dans la barre de recherhe
				PrintWriter sortie;
				//Chemin du fichier
				String fichier_path;

				if (dialogue.showOpenDialog(null)== JFileChooser.APPROVE_OPTION) {
					//On réccupere le chemin
					fichier_path = dialogue.getSelectedFile().getPath();
					
			//		System.out.println(fichier_path);
					try {
						//On print le chemin
						sortie = new PrintWriter(new FileWriter(fichier_path, true));
						
						//Image chargé
						ImageIcon image_charge = new ImageIcon(fichier_path);
						
						//Ajoute l'image a la liste 
						list_ico_femme_photo.add(image_charge);
						
						//On la converti en BufferImage
						BufferedImage bi = ImageIO.read(new File(fichier_path));
						
						//On defini le chemin dans lequel enregistrer
						String path = getClass().getResource("/images/photo/femme/").getPath().replace("%20", " ");
		
						//Enregistre l'image dans le dossier bin
						ImageIO.write(bi, "png", new File(path+"/photo"+list_ico_femme_photo.size()+".png"));

						//Mise a jour l'interface panel homme photo
						ind_photo_femme = list_ico_femme_photo.size()-1;
						
						//Met a jour l'interface
						updateInterface(panel_femme_photo,new JLabel( scaleImage(list_ico_femme_photo.get(ind_photo_femme), dim_photo_w,dim_photo_h)) ,0);

						//On ferme la fenetre a la validation
						sortie.close();
					} catch (IOException e) {e.printStackTrace();}

				}
			}
		}); 

		button_femme_image_droite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				updateInterface(panel_femme_photo,new JLabel(scaleImage(list_ico_femme_photo.get(updateIndicePhoto("f","+")), dim_photo_w,dim_photo_h)),0);
			}
		}); 
		button_femme_image_gauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				updateInterface(panel_femme_photo,new JLabel(scaleImage(list_ico_femme_photo.get(updateIndicePhoto("f","-")), dim_photo_w,dim_photo_h)),0);
			}
		}); 
		button_homme_image_droite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				updateInterface(panel_homme_photo,new JLabel(scaleImage(list_ico_homme_photo.get(updateIndicePhoto("h","+")), dim_photo_w,dim_photo_h)),0);
			}
		}); 
		button_homme_image_gauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				updateInterface(panel_homme_photo,new JLabel(scaleImage(list_ico_homme_photo.get(updateIndicePhoto("h","-")), dim_photo_w,dim_photo_h)),0);

			}
		}); 

		button_alea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				updateInterface(panel_femme_photo,new JLabel(scaleImage(list_ico_femme_photo.get(updateIndicePhoto("f","+")), dim_photo_w,dim_photo_h)),0);
			}
		});
		
		button_retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Accueil acceuil = new Accueil();
				actuFenetre(acceuil);
			}
		});
		
		button_valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				updateInterface(panel_femme_photo,new JLabel(scaleImage(list_ico_femme_photo.get(updateIndicePhoto("f","+")), dim_photo_w,dim_photo_h)),0);
			}
		});
	}


	public void creerObjet() {
		//3 borderlayout
		panel_content = panelSansBgStyle(new JPanel());
		panel_header = panelSansBgStyle(new JPanel());
		panel_footer = panelSansBgStyle(new JPanel());
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
		panel_pays_gauche = new JPanel();
		panel_pays_droite = new JPanel();
		
		//Header
		label_titre = titreLabelStyle(new JLabel("Créez vos Biathlètes"));
		label_description = labelStyle(new JLabel(" "));
		label_choix_pays = sousTitreLabelStyle(new JLabel("Nationalité que vous dirigerez : "));
		combobox_pays = comboboxStyle(this.creerComboBoxPays());
		label_pays_image = new JLabel(new ImageIcon(getClass().getResource("/images/drapeau/grand/allemagne.png")));
		button_alea = mediumButtonStyle(new JButton("Aléatoire"));
		
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

		label_femme_photo = sousTitreLabelStyle(new JLabel("Charger une photo : "));
		button_femme_photo = smallButtonStyle(new JButton("Parcourir"));
		button_femme_image_gauche = new JButton("<");
		list_ico_femme_photo = this.setListPhotoDefaut("femme");
		button_femme_image_droite = new JButton(">");
	}
	
	
	public void afficheBorderElement() {
		
		//On rempli le label de pays 
		panel_pays_gauche.setLayout(new FlowLayout(FlowLayout.LEFT, 30,10));
		panel_pays_gauche.add(label_choix_pays);
		panel_pays_gauche.add(combobox_pays);
		panel_pays_gauche.add(label_pays_image);
		panel_pays_droite.setLayout(new FlowLayout(FlowLayout.RIGHT, 30,10));
		panel_pays_droite.add(button_alea);
		panel_pays.setLayout(new GridLayout(1,2));
		panel_pays.add(panel_pays_gauche);
		panel_pays.add(panel_pays_droite);
		panel_pays_gauche.setBackground(color_bg);
		panel_pays_droite.setBackground(color_bg);
		
		//On rempli le header
		panel_header.setLayout(new GridLayout(3,1));
		panel_header.add(label_titre);
		panel_header.add(label_description);

		//on rempli le footer
		panel_footer.setLayout(new FlowLayout(FlowLayout.CENTER , 50,10));
		panel_footer.add(button_valider);
		panel_footer.add(button_retour);

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
		panel_homme_photo.add(new JLabel(this.scaleImage(list_ico_homme_photo.get(1), dim_photo_w,dim_photo_h))  , gbc(0,0,4,1,0,0,null,0));
		panel_homme_photo.add(panel_homme_button_fleche, gbc(5,0,1,1,0,0, null,0));
		panel_homme_photo.add(panel_homme_parcourir_photo,gbc(6,0,1,1,0,0,null,0));
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
		panel_femme_favorite.add(label_femme_favorite);
		panel_femme_favorite.add(combobox_femme_favorite);
		//sous panel pour photo
		panel_femme_button_fleche.setLayout(new FlowLayout(FlowLayout.RIGHT, 10,10));
		panel_femme_button_fleche.add(button_femme_image_gauche);
		panel_femme_button_fleche.add(button_femme_image_droite);
		panel_femme_parcourir_photo.setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
		panel_femme_parcourir_photo.add(label_femme_photo);
		panel_femme_parcourir_photo.add(button_femme_photo);

		//panel femme photo
		panel_femme_photo.setLayout(new GridBagLayout());
		panel_femme_photo.add(new JLabel(this.scaleImage(list_ico_femme_photo.get(1), dim_photo_w,dim_photo_h))  , gbc(0,0,4,1,0,0,null,0));
		panel_femme_photo.add(panel_femme_button_fleche, gbc(5,0,1,1,0,0,null,0));
		panel_femme_photo.add(panel_femme_parcourir_photo,gbc(6,0,1,1,0,0,null,0));
		//Panel femme info
		panel_femme_info.setLayout(new GridLayout(5,1));
		panel_femme_info.add(panel_femme_prenom);
		panel_femme_info.add(panel_femme_nom);
		panel_femme_info.add(panel_femme_age);
		panel_femme_info.add(panel_femme_specialite);
		panel_femme_info.add(panel_femme_favorite);

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
		panel_femme_favorite.setBackground(color_bg);
		panel_femme_prenom.setBackground(color_bg);
		panel_femme_nom.setBackground(color_bg);
		panel_femme_info.setBackground(color_bg);
		panel_femme.setBackground(color_bg);
		panel_femme_button_fleche.setBackground(color_bg);
		panel_femme_parcourir_photo.setBackground(color_bg);

		
		
		//Content
		panel_content.setLayout(new GridBagLayout());
		panel_content.add(panelStyle(panel_pays), gbc(0,0,0,0,1,2, new Insets(10,10,10,10) ,GridBagConstraints.BOTH) );
		panel_content.add(panelStyle(panel_homme),gbc(1,0,0,0,1,1, new Insets(10,10,10,10) ,0) ) ;
		panel_content.add(panelStyle(panel_femme),gbc(1,1,0,0,1,1, new Insets(10,10,10,10) ,0) );


		super.afficheBorderElement();
		
	}

	public int updateIndicePhoto(String sexe, String incr) {
		if(sexe == "h") {
			if(incr=="+") {
				if(ind_photo_homme+1 >= list_ico_homme_photo.size()) {
					ind_photo_homme=0;
				}else {
					ind_photo_homme++;
				}
			}else {
				if(ind_photo_homme-1 < 0) {
					ind_photo_homme = list_ico_homme_photo.size()-1;
				}else {
					ind_photo_homme--;
				}
			}
			return ind_photo_homme;
		}else {
			if(incr=="+") {
				if(ind_photo_femme+1 >= list_ico_femme_photo.size()) {
					ind_photo_femme=0;
				}else {
					ind_photo_femme++;
				}

			}else {
				if(ind_photo_femme-1 < 0) {
					ind_photo_femme = list_ico_femme_photo.size()-1;
				}else {
					ind_photo_femme--;
				}

			}
			return ind_photo_femme;
		}
	}



	public ArrayList<ImageIcon> setListPhotoDefaut(String sexe) {
		//Liste des photo
		ArrayList<ImageIcon> list_photo = new ArrayList<ImageIcon>();
		//Dossier des photos
		File file = new File(getClass().getResource("/images/photo/"+sexe+"/").getPath().replaceAll("%20", " "));
		//Taille du fichier
		int taille_fic = file.list().length;
		//Parcour des elements du fichier 
		for(int i = 1 ; i <= taille_fic; i++) {
			//Remplis la liste de photos
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
