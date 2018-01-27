package GUImodel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import DbModel.ProductData;
import EntityLayer.Product;

public class ProductDetails extends JFrame{

	
	private JLabel lblHeading,lblId,lblName,lblCategory,lblDate,lblPrice;
	private JTextField txtId,txtName,txtCategory,txtDate,txtPrice;
	private JButton btnAdd;
//	private JPanel panel;
	
	private AdminProductList parentFrame;
	private ProductData productData;
	private Product product;
	private boolean isNew;
	
	public ProductDetails(AdminProductList parent,int id,ProductData data) {
		
		productData = data;
		parentFrame = parent;
		product = productData.GetbyID(id);
		
		
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500,200,600,550);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(0,153,153));
		this.setLocationRelativeTo(parentFrame);
		

		AddComponent();
		populateData();
				
	}
    
	private void populateData() {
		
		if (product.getId() == -1) {
			isNew= true;
			return;
		}
		else {
		
			isNew= false;
			txtId.setEditable(false);
			txtId.setText(product.getId()+"");
			txtName.setText(product.getName());
			txtCategory.setText(product.getCategory());
			txtDate.setText(product.getDate());
			txtPrice.setText(product.getPrice()+"");
			
		}
	}


	private void FillProduct() {
		product.setId(Integer.parseInt(txtId.getText()));
		product.setName(txtName.getText());
		product.setDate(txtDate.getText());
		product.setCategory(txtCategory.getText());
		product.setPrice(Integer.parseInt(txtPrice.getText()));
	}

	public void AddComponent() {
		
//		panel = new JPanel();
//		panel.setBorder(new LineBorder(Color.GREEN,5));	    
//		panel.setBounds(0,0,780,500);
//		panel.setBackground(Color.CYAN);
//		panel.setLayout(null);
//		this.add(this.panel);
		
		lblHeading = new JLabel("Add Product Information");
		lblHeading.setBounds(100,10,400,60);
		lblHeading.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,30));
		this.add(lblHeading);
		
		//---ID
		
		lblId = new JLabel("ID");
		lblId.setBounds(20,90,90,40);
		lblId.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		this.add(lblId);
		
		txtId = new JTextField();
		txtId.setBounds(120,90,200,30);
		this.add(txtId);
		
		//----Name
		
		lblName = new JLabel("Name");
		lblName.setBounds(20,140,90,40);
		lblName.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		this.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(120,140,200,30);
		this.add(txtName);
		
		//----Category
		
		lblCategory = new JLabel("Category");
		lblCategory.setBounds(20,190,90,40);
		lblCategory.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		this.add(lblCategory);
		
		txtCategory = new JTextField();
		txtCategory.setBounds(120,190,200,30);
		this.add(txtCategory);
		
		//----Date
		
		lblDate = new JLabel("Date");
		lblDate.setBounds(20,240,90,40);
		lblDate.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		this.add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setBounds(120,240,200,30);
		this.add(txtDate);
		
		//----price
		
		lblPrice = new JLabel("Price");
		lblPrice.setBounds(20,290,90,40);
		lblPrice.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		this.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(120,290,200,30);
		this.add(txtPrice);
		
		//----Button
		
		btnAdd = new JButton("SAVE");
		btnAdd.setBounds(300,380,180,40);
		btnAdd.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		btnAdd.setBackground(new Color(178,255,102));
//		btnAdd.setBorder(new LineBorder(Color.GREEN,5));
		this.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FillProduct();
				
				boolean isSuccess = false ;
				
				if (isNew) {
					isSuccess = productData.InsertProduct(product);
				
				}
				else {
					isSuccess = productData.UpdateProduct(product);
				}
				
				if (isSuccess) {
					setVisible(false);
					parentFrame.populateTable();
				}
			}

			
		});
				

		
	}
	
	
}
