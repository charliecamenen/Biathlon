package com.biathlon.action;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.biathlon.jeu.Main;

public abstract class InterfaceGraphique extends JPanel {

	protected Color color_bg;
	
	
	public InterfaceGraphique() {
		super();
		color_bg = new Color(40,40,40);
		this.setBackground(color_bg);
	}
	
	public ImageIcon scaleImage(ImageIcon icon, int w, int h)
    {
		//Dimensions de l'image 
        int nw = icon.getIconWidth();
        int nh = icon.getIconHeight();

        //Si la taille est supérieur a la nouvelle
        if(icon.getIconWidth() > w)
        {
          nw = w;
          nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
        }
        
        if(nh > h)
        {
          nh = h;
          nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
        }

        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
    }
	
	protected JPanel panelStyle(JPanel panel) {
		panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
		return panel;
	}
	
	protected JTextField textFieldStyle(JTextField textfield) {
		textfield.setPreferredSize(new Dimension(200, 35));
		textfield.setFont(new Font("calibri", Font.BOLD, 20));
		textfield.setForeground(new Color(40,40,40));
		textfield.setBackground(new Color(255,255,255));
		return textfield;
	}
	
	protected JLabel titreLabelStyle(JLabel label) {
		//label.setPreferredSize(new Dimension(200, 35));
		label.setHorizontalAlignment(SwingConstants.CENTER); 
		label.setFont(new Font("calibri", Font.BOLD, 38));
		label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		label.setForeground(new Color(255,255,255));
		return label;
	}
	protected JLabel sousTitreLabelStyle(JLabel label) {
		//label.setPreferredSize(new Dimension(200, 35));
		label.setFont(new Font("calibri", Font.BOLD, 25));
		label.setForeground(new Color(255,255,255));
		return label;
	}
	protected JLabel labelStyle(JLabel label) {
		//label.setPreferredSize(new Dimension(200, 35));
		label.setFont(new Font("calibri", Font.BOLD, 18));
		label.setForeground(new Color(255,255,255));
		return label;
	}
	
	protected JComboBox<String> comboboxStyle(JComboBox<String> combobox) {
		combobox.setPreferredSize(new Dimension(200, 35));
		combobox.setFont(new Font("calibri", Font.BOLD, 20));
		combobox.setForeground(new Color(40,40,40));
		combobox.setBackground(new Color(255,255,255));
		return combobox;
	}
	
	protected JList listStyle(JList list) {
		list.setPreferredSize(new Dimension(500,30));
		list.setFont(new Font("calibri", Font.PLAIN, 20));
		//list.setOpaque(false);
		list.setForeground(new Color(0,0,0));
		return list;
	}

	protected JButton headerButtonStyle(JButton button) {
		button.setPreferredSize(new Dimension(300,100));
		button.setFont(new Font("calibri", Font.BOLD, 30));
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setForeground(new Color(255,255,255));
		button.setBorderPainted(false);
		return button;
	}
	
	protected JButton mediumButtonStyle(JButton button) {
		button.setPreferredSize(new Dimension(200,50));
		button.setFont(new Font("calibri", Font.BOLD, 20));
		//button.setOpaque(false);
		//button.setContentAreaFilled(false);
		button.setForeground(new Color(40,40,40));
		button.setBackground(new Color(255,212,126));
		button.setBorderPainted(false);
		return button;
	}
	
	protected JButton smallButtonStyle(JButton button) {
		button.setPreferredSize(new Dimension(100,30));
		button.setFont(new Font("calibri", Font.BOLD, 15));
		//button.setOpaque(false);
		//button.setContentAreaFilled(false);
		button.setForeground(new Color(40,40,40));
		button.setBackground(new Color(255,212,126));
		button.setBorderPainted(false);
		return button;
	}


	protected void actuFenetre(InterfaceGraphique interface_graphique) {
		Main.fenetre.setContentPane(interface_graphique);
		Main.fenetre.setVisible(true);
	}
}
