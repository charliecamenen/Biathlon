
package com.biathlon.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.biathlon.jeu.Main;

public class MonTableModel extends DefaultTableModel{

	//colonne du checkbox (-1 si rien)
	private ArrayList<Integer> colonne_cb_list; 

	public MonTableModel(Object[][] donnees, String[] entetes, ArrayList<Integer> colonne_cb_list) {
		super(donnees, entetes);
		//liste des colonnes contenant des checkbox
		this.colonne_cb_list = colonne_cb_list;
	}
	
	public MonTableModel(ArrayList<Object[]> donnees, String[] entetes, ArrayList<Integer> colonne_cb_list) {
		super(arrayListToTableau(donnees), entetes);
		//liste des colonnes contenant des checkbox
		this.colonne_cb_list = colonne_cb_list;
	}
	
	private static Object[][] arrayListToTableau(ArrayList<Object[]> list_donnees) {
		
		Object[][] data = new Object[list_donnees.size()][list_donnees.get(0).length];
		
		//parcour l'arraylist
		for(int i = 0 ; i < list_donnees.size(); i++)
			//On rempli chaque ligne du tableau
			data[i] = list_donnees.get(i);
		
		return data;
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		boolean editable = false;
		if(colonne_cb_list!=null) {
			if(colonne_cb_list.contains(column)) {
				editable = true;
			}
		}		
		return editable;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		//Si il y a des colonne mettre en checkbox
		if(colonne_cb_list!=null) {
			//Si il s'agit de la colonne en question
			if(colonne_cb_list.contains(columnIndex)) {
				return Boolean.class;
			}
		}
		return super.getColumnClass(columnIndex);
	}


}

