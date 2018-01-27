package TableModel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import EntityLayer.Product;

public class ProductTable extends AbstractTableModel {
	
	private String[] colNames = {"ID","Name","Date","Category","Price"};
	private ArrayList<Product> productTable = new ArrayList<Product>();
	
	public ProductTable(ArrayList<Product> products) {
	
		productTable = products;
		
	}

	@Override
	public int getColumnCount() {
		
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		
		return productTable.size();
	}
	
//	public String geColumnName(int col) {
//		return colNames[col];
//	} 
	

	@Override
	public String getColumnName(int col) {
		
		return colNames[col];
	}


	@Override
	public Object getValueAt(int row, int col) {
		Product product = productTable.get(row);
		
		switch(col) {
		case 0 :
			return product.getId();
		case 1 :
			return product.getName();
		case 2 :
			return product.getDate();
		case 3 :
			return product.getCategory();
		case 4 :
			return product.getPrice();
		default: 
		    return product.getId();
		
		}
		
	}


	

}
