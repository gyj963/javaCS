package ztb;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;


//这样使用
//JTable table = new JTable(new CustomModel(data, columnNames));
//table中的boolean会被渲染成checkbox

class CustomModel extends DefaultTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomModel(Vector<?> data, Vector<?> columnNames) {
		super(data, columnNames);
	}

	public Class<? extends Object> getColumnClass(int col) {
		// dataVector is a protected member of DefaultTableModel

//		Vector v = (Vector) dataVector.elementAt(0);
//		return v.elementAt(col).getClass();
		 return getValueAt(0, col).getClass();
	}

	public boolean isCellEditable(int row, int col) {
		Class<? extends Object> columnClass = getColumnClass(col);
		return columnClass == Boolean.class;
	}
}
