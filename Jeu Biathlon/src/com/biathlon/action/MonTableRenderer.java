package com.biathlon.action;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.biathlon.jeu.Main;

public class MonTableRenderer extends DefaultTableCellRenderer {

	protected Color tableau_bg_color_default = new Color(0,80,150);
	protected Color tableau_grid_color_default = new Color(255,255,255);

	private Object[][] donnees;
	private String[] entetes;

	private Color[] list_color_column_bg;
	private Color[] list_color_column_fg;
	private int[] list_width_column;
	private int[] list_alignement_column;
	private Font[] list_font_column;

	private boolean horizontalLine;
	private boolean verticalLine;
	private int rowHeight;
	private Font headerFont;
	private Color tableau_grid_color;
	private Color tableau_bg_color;
	private boolean focus;
	private boolean select;


	public MonTableRenderer(Object[][] donnees, String[] entetes, Color[] list_color_column_bg,
			Color[] list_color_column_fg, int[] list_width_column, int[] list_alignement_column,
			Font[] list_font_column, boolean horizontalLine, boolean verticalLine, int rowHeight, Font headerFont,
			Color tableau_grid_color, Color tableau_bg_color, boolean focus, boolean select) {
		super();
		this.donnees = donnees;
		this.entetes = entetes;
		this.list_color_column_bg = list_color_column_bg;
		this.list_color_column_fg = list_color_column_fg;
		this.list_width_column = list_width_column;
		this.list_alignement_column = list_alignement_column;
		this.list_font_column = list_font_column;
		this.horizontalLine = horizontalLine;
		this.verticalLine = verticalLine;
		this.rowHeight = rowHeight;
		this.headerFont = headerFont;
		this.tableau_grid_color = tableau_grid_color;
		this.tableau_bg_color = tableau_bg_color;
		this.focus = focus;
		this.select = select;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		//Font de l'entete en vérifiant qu'il y a bien un entete
		if(headerFont !=null)table.getTableHeader().setFont(headerFont);
		else table.setTableHeader(null);

		//Hauteur des cellules
		table.setRowHeight(rowHeight);

		//Couleur des lignes
		if(tableau_grid_color!=null)table.setGridColor(tableau_grid_color);
		else table.setGridColor(tableau_grid_color_default);

		//Couleur des lignes
		if(tableau_bg_color!=null)table.setBackground(tableau_bg_color);
		else table.setBackground(tableau_bg_color_default);

		//Ligen vertical
		table.setShowVerticalLines(verticalLine);
		//Ligne horizontales
		table.setShowHorizontalLines(horizontalLine);

		//On ne peut pas selectionner la ligne
		table.setFocusable(focus);
		table.setRowSelectionAllowed(select);

		//Largeur de la colonne
		table.getColumnModel().getColumn(column).setPreferredWidth(list_width_column[column]);

		//Couleur du fond de la colonne 
		component.setBackground(list_color_column_bg[column]);

		//couleur du text
		component.setForeground(list_color_column_fg[column]);
		if(value instanceof Integer) component.setForeground(this.colorDynamicNote((int) value));
		
		//Font des colonnes
		component.setFont(list_font_column[column]);

		JLabel label = (JLabel) component;
		//Alignement de la colonne
		label.setHorizontalAlignment(list_alignement_column[column]);


		//Si la valeur est une image on l'ajoute au tableau
		if (value instanceof ImageIcon) {
			ImageIcon ico_photo_cells = (ImageIcon) value;
			JLabel label_icon = (JLabel) component;
			label_icon.setIcon(ico_photo_cells);
			setText("");
		}else {
			JLabel label_icon = (JLabel) component;
			label_icon.setIcon(null);
		}

		return component;
	}
	
	private Color colorDynamicNote(int value) {
		int red;
		int green;
		int blue;
		
		if(value >= 70 && value <=99){
			red = (int) Math.round(173+((float)(value -70) * -5.76));
			green = (int) Math.round(180 + ((float)(value -70) * 2.5));
			blue = (int) Math.round(173+((float)(value -70) * -5.76));
			return new Color(red,green,blue);
		}
		
		return Color.WHITE;
	}
}

