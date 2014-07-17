package ztb;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

class ComboModel extends DefaultTableModel {
	  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ComboModel(Vector<?> data, Vector<?> columnNames) {
		super(data, columnNames);
	}

	public boolean isCellEditable(int row, int col) {
		if(col==this.getColumnCount()-1) return true; //最后一列可编辑其他列不可编辑
		return false;
	}
}
