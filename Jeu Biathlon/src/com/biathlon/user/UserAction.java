package com.biathlon.user;

import java.awt.Button;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;

public class UserAction extends JPanel {
	//Bouton test
	private Button button_test;
	private ListeEnregistrement list;
	private ModelTable table;
	private JTable tableau;
	
	public UserAction() {
		super();
		//button_test = new Button("TEST");
		/*Vider tout avant de reaficher*/
		
		//On effectue la requete qu'on stock la liste d'enregistrement
		//list = Main.database.requete();
		
		//On créé le modele de tableaua  a partir de la liste d'enregistrement
		//table = new ModelTable(list);
		
		//On créé l'objet tableau dans lequel on ajoute l'objet ModelTableau
		//tableau = new JTable(table);
		//list.remplire();
		//On ajoute le tableau au panel UserAction
		//this.add(tableau);
		//this.add(button_test);
		//Chrono qui permet d'actualiser l'écran
		//Thread chrono_ecran = new Thread(new Chrono());
		//chrono_ecran.start();
	}
	
	public void actualise() {

	}
}
