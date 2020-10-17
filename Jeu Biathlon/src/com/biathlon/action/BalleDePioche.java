package com.biathlon.action;

import java.awt.Image;

import javax.swing.ImageIcon;

public class BalleDePioche {
			//Cache cible lorsque la cible est blanchi
		protected ImageIcon ico_balle_pioche;
		protected Image img_balle_pioche;
		protected int x;
		protected int y;
		
		public BalleDePioche(int x , int y) {
			super();
			//On lui donne une position
			this.y = y;
			this.x = x;
		}

		public ImageIcon getIco_balle_pioche() {
			return ico_balle_pioche;
		}

		public void setIco_balle_pioche(ImageIcon ico_balle_pioche) {
			this.ico_balle_pioche = ico_balle_pioche;
		}

		public Image getImg_balle_pioche() {
			return img_balle_pioche;
		}

		public void setImg_balle_pioche(Image img_balle_pioche) {
			this.img_balle_pioche = img_balle_pioche;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
		
}
