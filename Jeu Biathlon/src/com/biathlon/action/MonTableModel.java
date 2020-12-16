
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

/*public class JTableUnmodifiable extends JTable {

		protected Color tableau_bg_color = new Color(0,80,150);
		protected Color tableau_txt_color = new Color(255,255,255);
		protected Color tableau_grid_color = new Color(255,255,255);

		private Object[][] donnees;
		private String[] entetes;

		public JTableUnmodifiable( Object[][] donnees, String[] entetes) {
			super(donnees,entetes);
		}

		//On ne rend pas éditable toutes les cellules 
		public boolean isCellEditable(int row, int col) {
			return false;
		}




	}*/
