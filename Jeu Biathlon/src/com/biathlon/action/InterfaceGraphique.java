package com.biathlon.action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

import com.biathlon.jeu.Main;

public class InterfaceGraphique extends JPanel implements Runnable {

	//Différents panel de la page border layout
	protected JPanel panel_footer;
	protected JPanel panel_header;
	protected JPanel panel_est;
	protected JPanel panel_ouest;
	protected JPanel panel_content;
	//Couleur prédéfinies
	//Couleur de bg des combobox
	protected Color color_combobox_bg = new Color(255,255,255);
	//Couleur gris transparent
	protected Color color_gris_transparent = new Color(41,41,41,150);
	//Couleur des boutons
	protected Color color_button = new Color(206,233,236);
	//Couleur de la police
	protected Color color_font = new Color(255,255,255);
	//Couleur transparante
	protected Color color_transparent = new Color(0,0,0,0);
	//Couleur des bg de tableau
	protected Color color_tableau_bg = new Color(0,80,150);
	
	//Couleur des bg de tableau 2
	protected Color color_tableau_bg_second = new Color(  252, 180, 20  );
	
	protected Color color_bg;
	protected ImageIcon ico_logo;
	protected Image img_logo;
	protected Image image_background;

	public InterfaceGraphique(String URL_img_bg) {
		super();
		ImageIcon ico;
		ico_logo = new ImageIcon(getClass().getResource("/images/logo.png"));
		img_logo = ico_logo.getImage();

		if(URL_img_bg !="") {
			ico = new ImageIcon(getClass().getResource(URL_img_bg));
			image_background = ico.getImage();
		}
		
		color_combobox_bg = new Color(255,255,255);
		color_bg = new Color(40,40,40);

		this.setBackground(color_bg);
		
		//Chrono qui permet d'actualiser l'écran
		Thread chrono = new Thread(this);
		chrono.start();
	}
	public InterfaceGraphique() {
		this("");
	}


	protected void setDimensionOfBorderElement(int north, int south, int east, int west) {

		//Modifie les dimesions les autres composants vide
		if(panel_est!=null) panel_est.setPreferredSize(new Dimension(east,0));
		if(panel_ouest!=null)panel_ouest.setPreferredSize(new Dimension(west,0));
		if(panel_header!=null)panel_header.setPreferredSize(new Dimension(0,north));
		if(panel_footer!=null)panel_footer.setPreferredSize(new Dimension(0,south));
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

	//A mettre dans la classe mere pour l'utiliser de partout
	public ImageIcon getImageJoueur(String sexe, int refWeight, int refHeight) {
		//Récuperer le path du fichier avec une requete SQL (chemin ecrit dans la table joueur)

		//Remplis la liste de photos
		return scaleImage(new ImageIcon(getClass().getResource("/images/photo/"+sexe+"/photo1.png")), refWeight,refHeight );

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

	public GridBagConstraints gbc(int num_ligne, int num_col, int poids_verticale, int poids_horizontale,int nombre_ligne, int nombre_col, Insets insets, int gbc_remplissage) {
		GridBagConstraints c = new GridBagConstraints();

		c.gridy = num_ligne;
		c.gridx = num_col;

		if(poids_verticale != 0)c.weighty = poids_verticale;
		if(poids_horizontale != 0)c.weightx = poids_horizontale;

		if(nombre_ligne != 0)c.gridheight = nombre_ligne ;
		if(nombre_col != 0)c.gridwidth = nombre_col ;




		if(insets!=null) c.insets = insets;
		if(gbc_remplissage != 0) c.fill = gbc_remplissage;
		return c;
	}

	protected void updateInterface(JPanel jpanel, Component comp, int pos) {
		jpanel.remove(pos);
		jpanel.add(comp, pos);
		jpanel.validate();
		/*componentList = interface_graphique.getComponents();
		for(Component c : componentList){System.out.println(c);}
		 */
	}

	//Return l'objet de la classe
	public JPanel getClassInstance() {
		return this;
	}

	protected void actuFenetre(InterfaceGraphique interface_graphique) {
		Main.fenetre.setContentPane(interface_graphique);
		Main.fenetre.setVisible(true);
	}

	protected void afficheBorderElement() {
		this.setLayout(new BorderLayout());
		//Ajoute a l'interface
		if(panel_header != null) this.add(panel_header, BorderLayout.NORTH);
		if(panel_content != null) this.add(panel_content, BorderLayout.CENTER);
		if(panel_est != null) this.add(panel_est, BorderLayout.EAST);
		if(panel_ouest != null) this.add(panel_ouest, BorderLayout.WEST);
		if(panel_footer != null) this.add(panel_footer, BorderLayout.SOUTH);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x;
		int y;
		//Permet de centrer l'image dans le panel
		if(image_background != null) {
			x = (this.getWidth() - image_background.getWidth(null)) / 2;
			y = (this.getHeight() - image_background.getHeight(null)) / 2;
			g.drawImage(image_background, x, y, null);
		}


	}




	protected String ajouterRetourLigne(String chaine) {
		int cpt = 0;
		String sortie = "";
		String dernier_mot= "";
		//Parcour la chaine d'entrée
		for(int i = 0; i < chaine.length(); i++) {
			//si c'est un espace
			if(chaine.charAt(i) == ' ') {
				//On ajoute le dernier mot a la sortie avec son espace
				sortie += dernier_mot + chaine.charAt(i);
				//On réinitialise le dernier mot
				dernier_mot = "";
			}else {
				//Si c'est un retour a la ligne
				if (chaine.charAt(i) == '\n') {
					//On affiche le mot
					sortie += dernier_mot;
					//Et retour à la ligne
					sortie += chaine.charAt(i);
					//Et on remet le cpt a 0
					cpt =0;
					//Et on initialise le dernier mots
					dernier_mot = "";
				}else {
					//Sinon on ajoute au dernier mot le caractere qui correspond
					dernier_mot += chaine.charAt(i);
				}
			}
			//Si on arrive au bout de la ligne
			if(cpt > 80 ) {
				//Retour à la ligne au dernier espace
				sortie += '\n';
				//ajout de la taille du mot a cpt
				cpt = dernier_mot.length();
			}
			//on incrémente le compteur de la ligne
			cpt = cpt+1;
		}
		return sortie;
	}

	protected DefaultTableCellRenderer rendererTable(int allignement) {
		DefaultTableCellRenderer rend = new DefaultTableCellRenderer();
		rend.setHorizontalAlignment(allignement);
		//rend.setSize(d);
		return rend;
	}



	/****** METHODE DE STYLES *****/

	protected JPanel panelSansBgStyle(JPanel panel) {
		panel.setOpaque(false);
		return panel;
	}

	protected JPanel panelStyle(JPanel panel,Color color_bg,Color color_border ) {
		panel.setBackground(color_bg);
		panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, color_border));
		return panel;
	}

	protected JPanel panelTransparentStyle(JPanel panel) {
		panel.setBackground(this.color_gris_transparent);
		panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
		return panel;
	}

	protected JPanel panelBorderStyle(JPanel panel) {
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
		return panel;
	}
	protected JTextField textFieldStyle(JTextField textfield) {
		textfield.setPreferredSize(new Dimension(160, 30));
		textfield.setFont(new Font("calibri", Font.BOLD, 18));
		textfield.setForeground(new Color(40,40,40));
		textfield.setBackground(new Color(255,255,255));
		return textfield;
	}

	protected JLabel titreLabelStyle(JLabel label) {
		//label.setPreferredSize(new Dimension(200, 35));
		label.setHorizontalAlignment(SwingConstants.CENTER); 
		label.setFont(new Font("calibri", Font.BOLD, 38));
		label.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
		label.setForeground(new Color(255,255,255));
		return label;
	}
	
	protected JLabel labelStyle(JLabel label) {
		label.setFont(new Font("calibri", Font.BOLD, 25));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(color_font);
		return label;
	}

	protected JLabel labelStyle(JLabel label, int taille_police, int alignement,Color color_fg ) {
		label.setFont(new Font("calibri", Font.BOLD, taille_police));
		if (alignement != 0) label.setHorizontalAlignment(alignement);
		else label.setHorizontalAlignment(SwingConstants.CENTER);
		if (color_fg != null)label.setForeground(color_fg);
		else label.setForeground(color_font);
		return label;
	}


	protected JComboBox<String> comboboxStyle(JComboBox<String> combobox) {
		combobox.setPreferredSize(new Dimension(180, 35));
		combobox.setFont(new Font("calibri", Font.BOLD, 18));
		combobox.setForeground(new Color(40,40,40));
		combobox.setBackground(new Color(255,255,255));
		return combobox;
	}

	protected JList listStyle(JList list) {
		list.setPreferredSize(new Dimension(350,100));
		list.setFont(new Font("calibri", Font.PLAIN, 20));
		list.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, color_bg));
		list.setForeground(new Color(0,0,0));
		return list;
	}


	protected JButton headerButtonStyle(JButton button) {
		button.setPreferredSize(new Dimension(400,50));
		button.setFont(new Font("calibri", Font.BOLD, 30));
		//button.setOpaque(false);
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
		button.setBackground(color_button);
		button.setBorderPainted(false);
		return button;
	}

	protected JButton smallButtonStyle(JButton button) {
		button.setPreferredSize(new Dimension(100,30));
		button.setFont(new Font("calibri", Font.BOLD, 15));
		//button.setOpaque(false);
		//button.setContentAreaFilled(false);
		button.setForeground(new Color(40,40,40));
		button.setBackground(color_button);
		button.setBorderPainted(false);
		return button;
	}

	protected JTextArea textAreaStyle(JTextArea textArea) {
		textArea.setBackground(color_gris_transparent);
		textArea.setForeground(Color.WHITE);
		textArea.setEditable(false);
		textArea.setFont(new Font("calibri", Font.BOLD, 15));
		return textArea;
	}
	
	protected JScrollPane scrollPaneStyle(JScrollPane scrollPane, Color bg_color, boolean opaque) {
		if(bg_color !=null)scrollPane.setBackground(bg_color);
		else scrollPane.setBackground(color_transparent);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		 //Code To make transparent
		scrollPane.getViewport().setOpaque(false);
		return scrollPane;
	}
	
	protected JCheckBox checkBoxStyle(JCheckBox checkBox) {
		//checkBox.setBackground(gris_transparent);
		checkBox.setForeground(Color.WHITE);
		checkBox.setFont(new Font("calibri", Font.BOLD, 15));
		checkBox.setOpaque(false);
		return checkBox;
	}
	/***** Getter et setter *****/

	public JPanel getPanel_footer() {
		return panel_footer;
	}
	public void setPanel_footer(JPanel panel_footer) {
		this.panel_footer = panel_footer;
	}
	public JPanel getPanel_header() {
		return panel_header;
	}
	public void setPanel_header(JPanel panel_header) {
		this.panel_header = panel_header;
	}
	public JPanel getPanel_content() {
		return panel_content;
	}
	public void setPanel_content(JPanel panel_content) {
		this.panel_content = panel_content;
	}

	@Override
	public void run() {
		while(true) {
			//On repaint le content
			if(this.panel_content !=null)this.panel_content.repaint();
			
			try {
				Thread.sleep(10);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
