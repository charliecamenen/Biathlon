package com.biathlon.user;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class ListeEnregistrement {
	
	//on declare un array list d'utilisateurs 
	public ArrayList<Enregistrement> listeenregistrement;
	
	//
	public ListeEnregistrement(){
		listeenregistrement = new ArrayList<Enregistrement>();
	}
	
	//creation de la methode d'ajout
	public void ajouteDonnee(Enregistrement record){
		listeenregistrement.add(record);
	}
	
	//Compte le nombre d'attribut d'une classe
	public void getNbAttribut(){

	}
	
	//Compte le nombre de lignes de la table
	public int getNbEnregistrement(){
		return listeenregistrement.size();
	}
	
	//Renvoi un enregistrement
	public Enregistrement getEnregistrement(int indice) {
		return listeenregistrement.get(indice);
	}
}


