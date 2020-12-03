package com.biathlon.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.biathlon.jeu.Main;

public class JTableUnmodifiable extends JTable {

	protected Color tableau_bg_color = new Color(32,56,100);
	protected Color tableau_txt_color = new Color(255,255,255);
	protected Color tableau_grid_color = new Color(255,255,255);

	private Object[][] donnees;
	private String[] entetes;

	public JTableUnmodifiable(Object[][] donnees, String [] entetes, Font headerFont , Font contentFont, int rowHeight, int[] columnSize,  boolean verticalLine, boolean horizontalLine,Color bg_color, DefaultTableCellRenderer[] list_rend  ) {
		super(donnees,entetes);

		this.donnees = donnees;
		this.entetes = entetes;

		//Font de l'entete en vérifiant qu'il y a bien un entete
		if(headerFont !=null)this.getTableHeader().setFont(headerFont);
		else this.setTableHeader(null);

		//Font du contenue
		this.setFont(contentFont);
		//Hauteur des cellules
		this.setRowHeight(rowHeight);

		for( int i =0; i<columnSize.length; i++ ) {
			this.getColumnModel().getColumn(i).setPreferredWidth(columnSize[i]);
			this.getColumnModel().getColumn(i).setCellRenderer(list_rend[i]);
		}
		//Ligen vertical
		this.setShowVerticalLines(verticalLine);
		//Ligne horizontales
		this.setShowHorizontalLines(horizontalLine);


		//Couleur du texte
		this.setForeground(tableau_txt_color);


		//Couleur des lignes
		this.setGridColor(tableau_grid_color);
		//Couleur du fond
		if (bg_color != null) {
			this.setBackground(bg_color);
		}else {
			this.setBackground(tableau_bg_color);
		}
		
		//On ne peut pas selectionner la ligne
		this.setFocusable(false);
		this.setRowSelectionAllowed(false);
	}

	public JTableUnmodifiable(Object[][] donnees, String [] entetes, Font headerFont , Font contentFont, int rowHeight, int[] columnSize,  boolean verticalLine, boolean horizontalLine,Color bg_color, DefaultTableCellRenderer[] list_rend ,DefaultTableModel tableModel ) {
		super(tableModel);

		this.donnees = donnees;
		this.entetes = entetes;

		//Font de l'entete en vérifiant qu'il y a bien un entete
		if(headerFont !=null)this.getTableHeader().setFont(headerFont);
		else this.setTableHeader(null);

		//Font du contenue
		this.setFont(contentFont);
		//Hauteur des cellules
		this.setRowHeight(rowHeight);

		for( int i =0; i<columnSize.length; i++ ) {
			this.getColumnModel().getColumn(i).setPreferredWidth(columnSize[i]);
			this.getColumnModel().getColumn(i).setCellRenderer(list_rend[i]);
		}
		
		
		
		//Ligen vertical
		this.setShowVerticalLines(verticalLine);
		//Ligne horizontales
		this.setShowHorizontalLines(horizontalLine);


		//Couleur du texte
		this.setForeground(tableau_txt_color);


		//Couleur des lignes
		this.setGridColor(tableau_grid_color);
		
		//Couleur du fond
		if (bg_color != null) {
			this.setBackground(bg_color);
		}else {
			this.setBackground(tableau_bg_color);
		}
		
		this.setFocusable(false);
		this.setRowSelectionAllowed(false);
	
	}
	
	
	//On ne rend pas éditable toutes les cellules 
	/*public boolean isCellEditable(int row, int col) {
		return false;
	}*/

   


}
