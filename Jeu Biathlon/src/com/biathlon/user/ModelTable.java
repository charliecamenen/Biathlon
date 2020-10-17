package com.biathlon.user;

import javax.swing.table.AbstractTableModel;

public class ModelTable extends AbstractTableModel {

	ListeEnregistrement list;
	
	public ModelTable(ListeEnregistrement list) {
		this.list = list;		
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getRowCount() {
		return list.getNbEnregistrement();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Enregistrement e = list.getEnregistrement(rowIndex);
		switch(columnIndex)
		{
		case 0: return e.getId_Nationalite();
		case 1: return e.getNationalite();
		default: return null;
		}
				
	}

}
