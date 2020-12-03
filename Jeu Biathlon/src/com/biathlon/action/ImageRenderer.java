package com.biathlon.action;

import java.awt.Component;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.biathlon.jeu.Main;

public class ImageRenderer extends DefaultTableCellRenderer {

    public ImageRenderer() {
        super();
    }
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        JLabel label = (JLabel) component;
       
       // System.out.println(String.valueOf(value).replace("%20", " ").length());
        if(String.valueOf(value) != "") {
        	String cheminImage = String.valueOf(value).replace("%20", " ").substring(6, String.valueOf(value).replace("%20", " ").length());
			//cheminImage = "C:/Users/Charlie/git/Biathlon/Jeu Biathlon/bin/images/gestion/3etoiles.png";
			ImageIcon icon = scaleImage(new ImageIcon(cheminImage),20,20);
	
			label.setIcon(icon);
		
        }
        //Icon test = new ImageIcon(getClass().getResource("/images/gestion/1etoile.png"));
        setText("");
        return this;
    }

  //Renvoie la taille réelle adapté a la résolution de l'écran du joueur
  	//Prend en parametre l'iconeimage afin de modifier la taille prédéfini par rapport aux ref
  	public int propTailleImage(int ico_size, char orientation) {

  		int taille = 0;
  		//Si c'est en paysage
  		if(orientation == 'w') {
  			taille = (int)Math.round(((float)ico_size / Main.ref_w)*Main.dimension.getWidth());
  		}else {
  			taille = (int)Math.round(((float)ico_size / Main.ref_h)*Main.dimension.getHeight());
  		}

  		return taille;
  	}

  	public int propTaillePolice(float ico_size) {
  		int taille_police;
  		//Calcul la taille de la nouvelle police
  		taille_police = (int)Math.round((ico_size / Main.ref_w)*this.getWidth());

  		return taille_police;
  	}

  	public ImageIcon scaleImage(ImageIcon icon, int w, int h)
  	{
		//With de l'image
		int nw = icon.getIconWidth();
		//Height de l'image
		int nh = icon.getIconHeight();

		//Si la taille est supérieur a la nouvelle
		//if(nw > w)
		//{
			//On remplace la taille par la nouuvelle taille
			nw = w;
			//Calcul de la height a partir du width
			nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
		//}

		//si la taille entrée est encore plus grande que la calculé
		if(nh > h)
		{
			//On redimensionne la H
			nh = h;
			//On redimensionne w a partir du nouveau H
			nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
		}

		return new ImageIcon(icon.getImage().getScaledInstance(propTailleImage(nw,'w'), propTailleImage(nh,'h'), Image.SCALE_DEFAULT));
	}
  	
}
