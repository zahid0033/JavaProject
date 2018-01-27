package DbModel;

import java.nio.charset.MalformedInputException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import EntityLayer.*;

public class ProductData {
	

	private static Connection conn;
	
	
	public ProductData() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");
			System.out.println("success");
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Connection unsuccessful");
		}
		
	}
	
	public ArrayList<Product> getProduct(){
		
		ArrayList<Product> products = new ArrayList<Product>();
		Statement myStmnt = null;
		ResultSet myResult = null;
		
		
		try {
			if (conn != null ) {
			
				myStmnt = (Statement) conn.createStatement();
				myResult = myStmnt.executeQuery("Select * from product");
				
				while(myResult.next()) {

					Product prodct = fetchAllRowInfo(myResult);
					products.add(prodct);						
				}				
			}			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage());
		}
		finally {
			close(myStmnt,null,myResult);
		}		
		
		return products;
	}
		

	public ArrayList<Product> SearchProduct(String key){
		
		ArrayList<Product> products = new ArrayList<Product>();
		Statement myStmnt = null;
		ResultSet myResult = null;
		
		try {
			if (conn != null ) {
			
				myStmnt = (Statement) conn.createStatement();
				myResult = myStmnt.executeQuery("Select * from product where Name like '%"+key+"%' or Category like '%"+key+"%'");
				
				while(myResult.next()) {

					Product prodct = fetchAllRowInfo(myResult);
					products.add(prodct);						
				}				
			}			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage());
		}
		finally {
			close(myStmnt,null,myResult);
		}		
		
		return products;
	}
	
	
	public boolean DeleteProduct(int id) {
		
		PreparedStatement myStatement = null;
		
		
		try {
			myStatement = (PreparedStatement) conn.prepareStatement("Delete from Product where ID=?");
			myStatement.setInt(1, id);
			int row = myStatement.executeUpdate();
			
			return row>0;
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		finally {
			close(null, myStatement, null);
		}
		
		
		return false;
	}
	
	
	private Product fetchAllRowInfo(ResultSet myresult) throws SQLException {
		
		Product prodct = new Product();
		
		prodct.setName(myresult.getString("Name"));
		prodct.setId(myresult.getInt("Id"));
		prodct.setCategory(myresult.getString("Category"));
		prodct.setDate(myresult.getString("Date"));
		prodct.setPrice(myresult.getInt("Price"));
		
		
		return prodct;
	}
	
	
	private void close (Statement myStmnt,PreparedStatement preparedStatement, ResultSet myResult) {
		try {
			if(myStmnt!=null) {
				myStmnt.close();
			}
			if (myResult != null) {
				myResult.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	
	public Product GetbyID(int id) {
		Product produ = new Product();
		produ.setId(id);
		
		
		Statement myStmnt = null;
		ResultSet myResult = null;
		
		try {
			if (conn != null ) {
			
				myStmnt = (Statement) conn.createStatement();
				myResult = myStmnt.executeQuery("Select * from product where Id="+id);
				
				if (myResult.next()) {
					
					produ = fetchAllRowInfo(myResult);
					
				}
				
			}			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage());
		}
		finally {
			close(myStmnt,null,myResult);
		}
		
			
		return produ;
	}

	
	public boolean InsertProduct(Product prod) {
		
		PreparedStatement myStatement = null;
		
		
		try {
			myStatement =  (PreparedStatement) conn.prepareStatement("Insert into product (Id, Name, Category, Date, Price) values (?,?,?,?,?)");
			
			myStatement.setInt(1,prod.getId());
			myStatement.setString(2,prod.getName());
			myStatement.setString(3,prod.getCategory());
			myStatement.setString(4,prod.getDate());
			myStatement.setInt(5,prod.getPrice());
			
			int i = myStatement.executeUpdate();
			
			return i>0;
			
		}
		catch(Exception ex) {
			
		}
		finally {
			close(null, myStatement, null);
		}
		
		return false;
		
	}
	
	
    public boolean UpdateProduct(Product prod) {
		
		PreparedStatement myStatement = null;
		
		
		try {
			
			myStatement =  (PreparedStatement) conn.prepareStatement("Update product set Name=?,Category=?,Date=?,price=? where Id=?");
			
			myStatement.setInt(5,prod.getId());
			myStatement.setString(1,prod.getName());
			myStatement.setString(2,prod.getCategory());
			myStatement.setString(3,prod.getDate());
			myStatement.setInt(4,prod.getPrice());
			
			int i = myStatement.executeUpdate();
			
			return i>0;
			
		}
		catch(Exception ex) {
			
		}
		finally {
			close(null, myStatement, null);
		}
		
		return false;
		
	}
	
}
