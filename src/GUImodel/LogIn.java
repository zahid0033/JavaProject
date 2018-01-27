package GUImodel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import GUImodel.LogIn;


public class LogIn extends JFrame implements ActionListener{
	
	private JLabel titel,titel1,titel2,imgLabel,note,id,pass;
	private JTextField idTF;
	private JPasswordField passPF;
	private JButton login ;
	private JPanel panel;
	private ImageIcon img;
	private JRadioButton Customer, Admin;
	private ButtonGroup bg;
	private boolean flag;
	
	public LogIn()
	{
		super("Shop Management");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,550);
		this.setBackground(Color.BLACK);
		this.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0,153,153));
		
		titel = new JLabel("LOG IN HERE");
		titel.setBounds(270,50,500,30);
		titel.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,40));
		panel.add(titel);
		
		
		titel1 = new JLabel("Choose an option");
		titel1.setBounds(530,200,500,35);
		titel1.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		panel.add(titel1);
	
		
		id = new JLabel("User Name");
		id.setBounds(160,230,100,30);
		id.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		panel.add(id);
		
		idTF = new JTextField();
		idTF.setBounds(290,230,200,30);
		panel.add(idTF);
		
		pass = new JLabel("Password");
		pass.setBounds(160,300,100,30);
		pass.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		//pass.setBackground(100,200,100,30);
		panel.add(pass);
		
		passPF = new JPasswordField();
		passPF.setBounds(290,300,200,30);
		passPF.setBackground(Color.WHITE);
		passPF.setEchoChar('*');
		panel.add(passPF);
		
		login = new JButton("LogIn");
		login.setBounds(370,380,150,40);
		login.setBackground(new Color(178,255,102));
		login.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		panel.add(login);
			

//		img = new ImageIcon("F:\\index1.png");
//		imgLabel = new JLabel(img);
//		imgLabel.setBounds(280,50,200,200);
//		panel.add(imgLabel);
		
		
		Icon icon = new ImageIcon("F:\\cart2.gif");
        JLabel label = new JLabel(icon);
        label.setBounds(305,100,160,100);
        panel.add(label);
		
		Customer = new JRadioButton("Customer");
		Customer.setBounds(600,250,150,25);
		Customer.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		Customer.setSelected(true);
		panel.add(Customer);
		
		Admin = new JRadioButton("Admin");
		Admin.setBounds(600,280,150,25);
		Admin.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		panel.add(Admin);
		
		bg = new ButtonGroup();
		bg.add(Customer);
		bg.add(Admin);
		
		
		
		this.add(panel);
		//this.setVisible(true);
		login.addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		if(elementText.equals(login.getText()))
		{
			flag=true; 
			logintoDB();
		} 
	}
	
	public void logintoDB()
	{
		String query1 =  "SELECT `Name`, `Password`,`Id`,`Email` FROM `customer`";     
        String query2 =  "SELECT `Name`, `Password`,`Id`,`Email` FROM `admin`"; 
        Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
		//System.out.println(query);
		if(Customer.isSelected())
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
				st = con.createStatement();
				rs = st.executeQuery(query1);
								
				while(rs.next())
				{
					String name = rs.getString("Name");
					int id = rs.getInt("ID");
					String Email = rs.getString("Email");
					String pass = rs.getString("Password");
					
					
					if(name.equals(idTF.getText()))
					{
						flag=false;
						if(pass.equals(passPF.getText()))
						{
//							UserProductList user = new UserProductList();
//							this.setVisible(false);
//							user.setVisible(true);
							
							CustomerProductList  customer = new CustomerProductList();
							this.setVisible(false);
							customer.setVisible(true);
							

						}
						else
						{
							JOptionPane.showMessageDialog(this,"Invalid pass"); 
						}
					}
				}
			}
			catch(Exception ex)
			{
				System.out.println("Exception : " +ex.getMessage());
			}
			finally
			{
				try
				{
					if(rs!=null)
					rs.close();

					if(st!=null)
					st.close();

					if(con!=null)
					con.close();
				}
				catch(Exception e){}
			}
		}
		
		
		
		

		else if(Admin.isSelected())
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
//				System.out.println("driver loaded");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
//				System.out.println("connection done");
				st = con.createStatement();
//				System.out.println("statement created");
				rs = st.executeQuery(query2);
//				System.out.println("results received");
					
				while(rs.next())
				{
					String name = rs.getString("Name");
					int id = rs.getInt("ID");
					String Email = rs.getString("Email");
					String pass = rs.getString("Password");
					
					if(name.equals(idTF.getText()))
					{
						flag=false;
						if(pass.equals(passPF.getText()))
						{
							AdminProductList l = new AdminProductList();
							this.setVisible(false);
							l.setVisible(true);

						}
						else
						{
							JOptionPane.showMessageDialog(this,"Invalid pass"); 
						}
					}
				}
			}
			catch(Exception ex)
			{
				System.out.println("Exception : " +ex.getMessage());
			}
			finally
			{
				try
				{
					if(rs!=null)
					rs.close();

					if(st!=null)
					st.close();

					if(con!=null)
					con.close();
				}
				catch(Exception e){}
			}
		}
		if(flag)
		{
			JOptionPane.showMessageDialog(this,"Invalid name"); 
		}
    } 
}



