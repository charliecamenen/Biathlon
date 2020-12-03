package com.biathlon.action;

import javax.swing.JButton;

public class JButton2 extends JButton {

	private int indice_list;


	public JButton2(String txt, int indice_list) {
		super(txt);
		this.indice_list = indice_list;
	}


	public int getIndice_list() {
		return indice_list;
	}


	public void setIndice_list(int indice_list) {
		this.indice_list = indice_list;
	}
	
	

}
