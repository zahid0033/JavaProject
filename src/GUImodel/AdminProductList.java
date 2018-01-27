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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import DbModel.ProductData;
import EntityLayer.Product;
import TableModel.ProductTable;

public class AdminProductList extends JFrame{
	
	private JPanel LowerPanel;
	private JPanel UpperPanel;
	private JPanel HeadingPanel;
	
	private JLabel lblSearch,lblHeading;
	private JTextField txtSearch;
	private JButton btnSearch,btnAdd,btnEdit,btnDelete,btnBack;
	
	private JTable ProductTable;
	private ProductData productData;
	
	
	
	public AdminProductList() {
		super("Shop Management");
		this.setSize(800,550);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(0,153,153));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		
		
		try {
			productData = new ProductData();
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		
		
		HeadingPanel = new JPanel();
//		this.HeadingPanel.setBorder(new LineBorder(Color.BLACK,1));
		this.HeadingPanel.setBounds(0,10,800,30);
		//this.HeadingPanel.setBackground(Color.CYAN);
		this.HeadingPanel.setLayout(null);
//		add(this.HeadingPanel);
		
		
		
		UpperPanel = new JPanel();
		this.UpperPanel.setBorder(new LineBorder(Color.BLACK,1));
		this.UpperPanel.setBounds(2,60,800,40);
		this.UpperPanel.setBackground(new Color(178,255,102));
		add(this.UpperPanel);
		
		
		
//		LowerPanel = new JPanel();
//		this.LowerPanel.setBorder(new LineBorder(Color.GREEN,1));
//		this.LowerPanel.setBackground(Color.BLUE);
//		this.LowerPanel.setBounds(5,130,800,340);
//		add(this.LowerPanel);
		
		
		
		this.UpperComponent();
		this.LowerComponent();
		this.Heading();
		
		
	}
	
	private void Heading() {
		
		lblHeading = new JLabel("Admin Panel");
		lblHeading.setBounds(300,15,250,30);
		lblHeading.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,30));
		this.add(lblHeading);
		
		
		//----back button
		btnBack=new JButton(new ImageIcon("F:\\logout.png"));
		//btnBack.setBackground(Color.GRAY);
		//btnBack.setForeground(Color.GRAY);
		//btnBack.setOpaque(true);  
		btnBack.setBounds(700,10,35,35);
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LogIn log = new LogIn();
				setVisible(false);
				log.setVisible(true);
				
				
			}
		});
		this.add(btnBack);
		
	}
	
	private void UpperComponent() {
		
		// heading panel part
		
//		lblHeading = new JLabel("Admin Panel");
//		lblHeading.setBounds(300,5,250,30);
//		lblHeading.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,30));
//		this.HeadingPanel.add(lblHeading);
		
		
		//----back button
//		btnBack=new JButton(new ImageIcon("F:\\logout.png"));
//		btnBack.setBackground(Color.GRAY);
//		btnBack.setForeground(Color.GRAY);
//		btnBack.setOpaque(true);  
//		btnBack.setBounds(730,10,30,30);
//		btnBack.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				LogIn log = new LogIn();
//				setVisible(false);
//				log.setVisible(true);
//				
//				
//			}
//		});
//		this.add(btnBack);
		
		//end of heading
		
		lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,15));
		this.UpperPanel.add(lblSearch);
		
		txtSearch = new JTextField(10);
		this.UpperPanel.add(txtSearch);
		
		
		//-----search button-----
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				populateTable();
			}
		});
		this.UpperPanel.add(btnSearch);
		
		//----- edit button------
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
								
               int row = ProductTable.getSelectedRow();
				
				if (row<0) {
					JOptionPane.showMessageDialog(null, "Please Select a row ");
					return;			
				}
				
				int id = (int)ProductTable.getValueAt(row, 0);

				showDetailsFrame(id);
				
				
			}
		});
		this.UpperPanel.add(btnEdit);
		
		
		//----add button -------
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
								
				showDetailsFrame(-1);
				
			}

			
		});
		this.UpperPanel.add(btnAdd);
		
		
		
		//------delete button-----
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteProductData();				
			}

			
		});
		this.UpperPanel.add(btnDelete);
		
		
	}
	
	public void LowerComponent() {
		
		ProductTable = new JTable();
		ProductTable.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane sp = new JScrollPane();
		sp.setBounds(5, 150, 770, 300);
		sp.setBorder(new LineBorder(Color.GREEN,2));	  
		this.add(sp);
		sp.setViewportView(ProductTable);
		
		
		this.populateTable();
		
	}

	public void populateTable() {
		
		ArrayList<Product> product = new ArrayList<Product>();
		
		if (txtSearch.getText().isEmpty()) {
			product = productData.getProduct();
		}
		else 
			product = productData.SearchProduct(txtSearch.getText());
		
		
		ProductTable productTable = new ProductTable(product);
		ProductTable.setModel(productTable);
		
	}
	
	public void DeleteProductData() {
		
		int row = ProductTable.getSelectedRow();
		
		if (row<0) {
			JOptionPane.showMessageDialog(null, "Please Select a row ");
			return;			
		}
		
		int id = (int)ProductTable.getValueAt(row, 0);
		
		String name = (String)ProductTable.getValueAt(row, 1);
		JOptionPane.showMessageDialog(null, name);
		

		
		if(productData.DeleteProduct(id)) {
			JOptionPane.showMessageDialog(null, "Deleted successsfully");
		}
		else 
			JOptionPane.showMessageDialog(null, "unsuccess delete");
		
		this.populateTable();
		
	}
	
	private void showDetailsFrame(int id) {
		
		ProductDetails productDetails = new ProductDetails(this,id,productData);
		productDetails.setVisible(true);
				
	}
	

}
