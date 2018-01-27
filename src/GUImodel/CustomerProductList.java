package GUImodel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import DbModel.ProductData;
import EntityLayer.Product;
import TableModel.CustomerTable;
import TableModel.ProductTable;

public class CustomerProductList extends JFrame{
	
	private JTable ProductsTable;
	private JPanel panel;	
	private JButton btnBuy,btnBack;
	private JLabel lblHeading;

	private ProductData productData;
	public AdminProductList adminProductList;
	private ProductTable ProductTable;
	
	
	
	public CustomerProductList() {
		super("Shop Management");
		this.setSize(800,550);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(0,153,153));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		try {
			
			adminProductList = new AdminProductList();
		    productData = new ProductData();
			
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		
		this.AddingComponent();
		
	}

	
	private void AddingComponent() {
		
		panel = new JPanel();
		//this.panel.setBorder(new LineBorder(Color.BLACK,1));
		panel.setBounds(5,60,780,400);
		panel.setBackground(Color.cyan);
		//add(this.panel);
		//this.setVisible(true);
		
		
		lblHeading = new JLabel("Customer Panel");
		lblHeading.setBounds(270,15,300,60);
		lblHeading.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,30));
		this.add(lblHeading);
		
		btnBuy = new JButton("Buy");
		btnBuy.setBounds(320,420,150,40);
		btnBuy.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		btnBuy.setBackground(new Color(178,255,102));
		btnBuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
               int row = ProductsTable.getSelectedRow();
				
				if (row<0) {
					JOptionPane.showMessageDialog(null, "Please Select a row ");
					return;			
				}
				
				int id = (int)ProductsTable.getValueAt(row, 0);
				
				
				//DeleteProductData();
				showDetailsFrame(id);
				
			}
		});
		this.add(btnBuy);
		
		
		btnBack=new JButton(new ImageIcon("F:\\logout.png"));
		btnBack.setBackground(Color.GRAY);
		btnBack.setForeground(Color.GRAY);
		btnBack.setOpaque(true);  
		btnBack.setBounds(700,20,40,40);
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LogIn log = new LogIn();
				setVisible(false);
				log.setVisible(true);
				
				
			}
		});
		this.add(btnBack);
	
		
		
		ProductsTable = new JTable();
		ProductsTable.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane sp = new JScrollPane();
		sp.setBounds(5, 85, 770, 300);
		sp.setBorder(new LineBorder(Color.GREEN,2));
		sp.setVisible(true);
		this.add(sp);
		sp.setViewportView(ProductsTable);
		
		
	    
		populateTable();
		
	}
	
	public void populateTable() {
			
        ArrayList<Product> product = new ArrayList<Product>();
	
        	
        	product = productData.getProduct();
        
       
        
        ProductTable productTable = new ProductTable(product);
		ProductsTable.setModel(productTable);
	
		}
	
	public void DeleteProductData() {
		
		int row = ProductsTable.getSelectedRow();
		
		if (row<0) {
			JOptionPane.showMessageDialog(null, "Please Select a row ");
			return;			
		}
		
		int id = (int)ProductsTable.getValueAt(row, 0);
		
		String name = (String)ProductsTable.getValueAt(row, 1);
		JOptionPane.showMessageDialog(null, name);
		

		
		if(productData.DeleteProduct(id)) {
//			JOptionPane.showMessageDialog(null, "Deleted successsfully");
		}
		else 
			JOptionPane.showMessageDialog(null, "unsuccess delete");
		
		this.populateTable();
		
	}
	
    private void showDetailsFrame(int id) {
		
		BuyProduct productDetails = new BuyProduct(this,id,productData);
		productDetails.setVisible(true);
				
	}

}
