package GUImodel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Welcome extends JFrame {
	
	private JPanel panel;
	private ImageIcon img;
	private JLabel imgLabel,header;
	private JButton btnEnter;
	
	
	public Welcome() {
		super("Shop Management");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500,200,800,550);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0,153,153),15));	    
		panel.setBounds(0,0,780,500);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		this.add(this.panel);
		
		
		header = new JLabel("Welcome to the Shop");
	    header.setBounds(200, 00, 450, 150);
		header.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,40));
	    panel.add(header);
	    		
		
		
		//---gif
		
	    Icon icon = new ImageIcon("F:\\cart.gif");
        JLabel label = new JLabel(icon);
        label.setBounds(250, 70, 300, 300);
        panel.add(label);   
	
        
				
		//--button
		
		btnEnter = new JButton("Enter the Shop");
		btnEnter.setBounds(270, 410, 250, 50);
		btnEnter.setBackground(new Color(178,255,102));
//		btnEnter.setBorder(new LineBorder(Color.CYAN,5));	
		btnEnter.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,20));
		panel.add(btnEnter);
		
		btnEnter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LogIn login = new LogIn();
				setVisible(false);
				login.setVisible(true);
				
			}
		});
		
		
		
	}

}
