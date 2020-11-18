package com.biathlon.action;


import javax.swing.JButton;
import javax.swing.JPanel;

public class GestionResultat extends InterfaceGraphique {

	private JButton test;

	public GestionResultat() {
		super();

		test = new JButton("REsultat !");
		this.add(test);
	}

	public JButton getTest() {
		return test;
	}

	public void setTest(JButton test) {
		this.test = test;
	}
	
	
	
}
