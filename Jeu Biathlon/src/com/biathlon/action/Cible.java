package com.biathlon.action;

import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.biathlon.jeu.Main;

public abstract class Cible {

	protected final int ref_pos_x = 1270;
	protected final int ref_pos_y = 520;
	
	//Cache Cible
	protected ArrayList<CacheCible> list_cache_cible;
	protected int y_cache_cible;
	protected int x_cache_cible = ref_pos_x - 22;
	
	//Cible visee par le tir
	protected int cible_visee;
	//Si faux la cible n'est pas blanchi
	protected ArrayList<Boolean> vec_boool_blanchi;
	
	protected int id_biathlete;

	//Fond de nom sur le pas de tir blanc si rien
	protected ImageIcon ico_cible_blanc;
	protected Image img_cible_blanc;
	
	//Fond de nom sur le pas de tir rouge quand faute
	protected ImageIcon ico_cible_rouge;
	protected Image img_cible_rouge;
	//Position
	
	//Fond de nom sur le pas de tir 
	protected ImageIcon ico_cible;
	protected Image img_cible;
	protected int y_cible;
	protected final int x_cible = ref_pos_x;
	
	
	public abstract void ajouterResultatTir(boolean resultat);
	
	public abstract void ajouterResultatTir(boolean resultat, int num_cible);
	
	
	public void blanchirCible() {
		this.img_cible = this.img_cible_blanc;
	}

	public ArrayList<CacheCible> getList_cache_cible() {
		return list_cache_cible;
	}

	public void setList_cache_cible(ArrayList<CacheCible> list_cache_cible) {
		this.list_cache_cible = list_cache_cible;
	}

	public int getY_cache_cible() {
		return y_cache_cible;
	}

	public void setY_cache_cible(int y_cache_cible) {
		this.y_cache_cible = y_cache_cible;
	}

	public int getX_cache_cible() {
		return x_cache_cible;
	}

	public void setX_cache_cible(int x_cache_cible) {
		this.x_cache_cible = x_cache_cible;
	}

	public int getId_biathlete() {
		return id_biathlete;
	}

	
	
	public int getRef_pos_x() {
		return ref_pos_x;
	}

	public int getRef_pos_y() {
		return ref_pos_y;
	}

	public void setId_biathlete(int id_biathlete) {
		this.id_biathlete = id_biathlete;
	}

	public ImageIcon getIco_cible_blanc() {
		return ico_cible_blanc;
	}

	public void setIco_cible_blanc(ImageIcon ico_cible_blanc) {
		this.ico_cible_blanc = ico_cible_blanc;
	}

	public Image getImg_cible_blanc() {
		return img_cible_blanc;
	}

	public void setImg_cible_blanc(Image img_cible_blanc) {
		this.img_cible_blanc = img_cible_blanc;
	}

	public ImageIcon getIco_cible_rouge() {
		return ico_cible_rouge;
	}

	public void setIco_cible_rouge(ImageIcon ico_cible_rouge) {
		this.ico_cible_rouge = ico_cible_rouge;
	}

	public Image getImg_cible_rouge() {
		return img_cible_rouge;
	}

	public void setImg_cible_rouge(Image img_cible_rouge) {
		this.img_cible_rouge = img_cible_rouge;
	}

	public ImageIcon getIco_cible() {
		return ico_cible;
	}

	public void setIco_cible(ImageIcon ico_cible) {
		this.ico_cible = ico_cible;
	}

	public Image getImg_cible() {
		return img_cible;
	}

	public void setImg_cible(Image img_cible) {
		this.img_cible = img_cible;
	}

	public int getY_cible() {
		return y_cible;
	}

	public void setY_cible(int y_cible) {
		this.y_cible = y_cible;
	}

	public int getX_cible() {
		return x_cible;
	}

	
	

}
