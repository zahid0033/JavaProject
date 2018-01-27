package GUImodel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import DbModel.ProductData;
import EntityLayer.Product;

public class BuyProduct extends JFrame {
	
	private JLabel lblHeading,lblId,lblName,lblCategory,lblDate,lblPrice,txtId,txtName,txtCategory,txtDate,txtPrice;

	private JButton btnAdd;
	private JPanel panel;
	private boolean isNew;
	
	private CustomerProductList parentFrame;
	private ProductData productData;
	private Product product;
	

	public BuyProduct(CustomerProductList parent,int id,ProductData data) {
		
		productData=data;
		parentFrame = parent;
		product = productData.GetbyID(id);
		
		
		this.setBounds(500,200,600,550);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		AddComponent();
		populateData();
//		FillProduct();
		
		try {
			CustomerProductList  customerProductList = new CustomerProductList();
		}
		catch(Exception ex) {
			
		}
		
		
	}
	
	
	
   private void populateData() {
		
		if (product.getId() == -1) {
			isNew= true;
			return;
		}
		else {
		
			isNew= false;
			txtId.setText(product.getId()+"");
			txtName.setText(product.getName()+"");
			txtCategory.setText(product.getCategory()+"");
			txtDate.setText(product.getDate()+"");
			txtPrice.setText(product.getPrice()+"");
			
		}
	}


//	private void FillProduct() {
//		product.setId(Integer.parseInt(txtId.getText()));
//		product.setName(txtName.getText());
//		product.setDate(txtDate.getText());
//		product.setCategory(txtCategory.getText());
//		product.setPrice(Integer.parseInt(txtPrice.getText()));
//	}
	
	



	private void AddComponent() {
		
		panel = new JPanel();
//		panel.setBorder(new LineBorder(Color.GREEN,3));	    
		panel.setBounds(0,0,580,500);
		panel.setBackground(new Color(153,255,153));
//		panel.setBackground(Color.CYAN);
		panel.setLayout(null);
		this.add(this.panel);
		
		lblHeading = new JLabel("Product Token");
		lblHeading.setBounds(200,10,400,60);
		lblHeading.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,30));
		panel.add(lblHeading);
		
		//---ID
		
		lblId = new JLabel("Serial ID #");
		lblId.setBounds(20,90,135,40);
		lblId.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		panel.add(lblId);
		
		txtId = new JLabel();
		txtId.setBounds(160,95,200,30);
		txtId.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		panel.add(txtId);
		
		//----Name
		
		lblName = new JLabel("Name :");
		lblName.setBounds(220,160,90,40);
		lblName.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		panel.add(lblName);
		
		txtName = new JLabel();
		txtName.setBounds(320,165,200,30);
		txtName.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		panel.add(txtName);
		
		//----Category
		
		lblCategory = new JLabel("Category :");
		lblCategory.setBounds(220,215,130,40);
		lblCategory.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		panel.add(lblCategory);
		
		txtCategory = new JLabel();
		txtCategory.setBounds(350,220,200,30);
		txtCategory.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		panel.add(txtCategory);
		
		//----Date
		
		lblDate = new JLabel("Date :");
		lblDate.setBounds(380,90,70,40);
		lblDate.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		panel.add(lblDate);
		
		txtDate = new JLabel();
		txtDate.setBounds(460,95,150,30);
		txtDate.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		panel.add(txtDate);
		
		//----price
		
		lblPrice = new JLabel("Price :");
		lblPrice.setBounds(220,270,90,40);
		lblPrice.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		panel.add(lblPrice);
		
		txtPrice = new JLabel();
		txtPrice.setBounds(320,275,200,30);
		txtPrice.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		panel.add(txtPrice);
		
		//---- Button
		
		btnAdd = new JButton("Print token");
		btnAdd.setBounds(210,370,180,40);
		btnAdd.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		btnAdd.setBackground(new Color(0,153,153));
		panel.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				parentFrame.DeleteProductData();
				
				
				PrinterJob job = PrinterJob.getPrinterJob();
	            job.setJobName("Print Data");
	            
	            job.setPrintable(new Printable(){
	            	
	            public int print(Graphics pg,PageFormat pf, int pageNum){
	                    pf.setOrientation(PageFormat.LANDSCAPE);
	                 if(pageNum>0){
	                    return Printable.NO_SUCH_PAGE;
	                }
	                
	                Graphics2D g2 = (Graphics2D)pg;
	                g2.translate(pf.getImageableX(), pf.getImageableY());
	                g2.scale(0.24,0.24);
	                
	                panel.paint(g2);
//	          
	               
	                return Printable.PAGE_EXISTS;
	                         
	                
	            }

				
	    });
	         
	        boolean ok = job.printDialog();
	        if(ok){
	        try{
	            
	        job.print();
	        }
	        catch (PrinterException ex){}
	        }
				
			}

			
		});
		
		
		
		
		
	}
	
	

}
