package login;

import java.awt.Color;


import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import MiddleLayer.StegoClient;
import homepage.HomePage;
import register.Register;


class MyLogin extends JFrame {
	LeftPanel lf;
	RightPanel rp;
	MyLogin(String s) {
		super(s);
		
		Container cont=getContentPane();
		cont.setLayout(null);
		
		Border white=new LineBorder(Color.white,4,true);
		
		lf=new LeftPanel();
		lf.setBounds(0,0,400,800);
		lf.setBorder(white);
	
		rp=new RightPanel();
		rp.setBounds(400,0,600,800);
		
		cont.add(lf);
		cont.add(rp);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
}

class LeftPanel extends JPanel implements ActionListener  {
	JButton b3;
	JLabel l1;
	
	LeftPanel() {
		setLayout(null);
		
		setBackground(new Color(0,153,153));
		
		l1=new JLabel();
		l1.setIcon(new ImageIcon("D:/CSA/images/logo2.jpg"));
		l1.setBounds(80,200,210,200);
		
		b3=new JButton("Register");
		b3.setBounds(80,450,210,50);
		b3.setBackground(new Color(0,0,0));
		b3.setForeground(new Color(255,255,255));
		b3.setFont(new Font("Tahoma",0,22));
		b3.addActionListener(this);
		
		add(l1);
		add(b3);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String lab=ae.getActionCommand();
		
		if(lab.equals("Register"))  {
			Register.registerUser(null);
			Login.ml.setVisible(false);
		}
		
	}
	
}


class RightPanel extends JPanel implements ActionListener {
	JLabel l1,l2,l3;
	static JTextField t1;
	JPasswordField t2;
	JTextField t3;
	JSeparator s1,s2;
	JButton b1,b2,b3;
	
	RightPanel() {
		setLayout(null);
		setBackground(new Color(0,0,0));
		
		l3=new JLabel();
		l3.setIcon(new ImageIcon("D:/CSA/images/images (4).png"));
		l3.setBounds(200,10,200,200);
		
		l1=new JLabel("Username : ");
		l1.setFont(new Font("Tahoma",0,22));
		l1.setForeground(new Color(255,255,255));
		l1.setBounds(80,200,150,80);
		
		t1=new JTextField();
		t1.setBounds(80,270,400,50);
		t1.setFont(new Font("Tahoma",0,22));
		t1.setForeground(new Color(255,255,255));
		t1.setBackground(new Color(0,0,0));
		t1.setBorder(null);
		t1.setCaretColor(new Color(255,255,255));
		
		s1=new JSeparator();
		s1.setOrientation(SwingConstants.HORIZONTAL);
		s1.setBounds(80,330,400,10);
		
		l2=new JLabel("Password : ");
		l2.setFont(new Font("Tahoma",0,22));
		l2.setForeground(new Color(255,255,255));
		l2.setBounds(80,360,150,80);
		
		t2=new JPasswordField();
		t2.setBounds(80,430,400,50);
		t2.setFont(new Font("Tahoma",0,22));
		t2.setForeground(new Color(255,255,255));
		t2.setBackground(new Color(0,0,0));
		t2.setCaretColor(new Color(255,255,255));
		t2.setBorder(null);
		
		t3=new JTextField();
		t3.setBounds(80,430,400,50);
		t3.setFont(new Font("Tahoma",0,22));
		t3.setForeground(new Color(255,255,255));
		t3.setBackground(new Color(0,0,0));
		t3.setCaretColor(new Color(255,255,255));
		t3.setBorder(null);
		t3.setVisible(false);
		
		b3=new JButton();
		b3.setIcon(new ImageIcon("D:/CSA/images/eye.png"));
		b3.setActionCommand("eye");
		b3.setBackground(new Color(255,255,255));
		b3.setBounds(500,430,40,40);
		b3.addActionListener(this);
		
		s2=new JSeparator();
		s2.setOrientation(SwingConstants.HORIZONTAL);
		s2.setBounds(80,490,400,10);
		
		b1=new JButton("login");
		b1.setBounds(125,580,100,40);
		b1.setBackground(new Color(0,153,153));
		b1.setForeground(new Color(255,255,255));
		b1.setFont(new Font("Tahoma",0,22));
		b1.addActionListener(this);
		
		b2=new JButton("cancel");
		b2.setBounds(320,580,100,40);
		b2.setBackground(new Color(0,153,153));
		b2.setForeground(new Color(255,255,255));
		b2.setFont(new Font("Tahoma",0,22));
		b2.addActionListener(this);
		
		add(l3);
		add(l1);
		add(t1);
		add(s1);
		add(l2);
		add(t2);
		add(s2);
		add(b1);
		add(b2);
		add(b3);
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
			String lab=ae.getActionCommand();
		
		if(lab.equals("login")) {

			String username=t1.getText();
			String pass=t2.getText();
			String pass1=t3.getText();
			
			if(pass1.equals(""))
				pass1=pass;
			
			
			try {
				
				/*if(!t3.getText().equals("")) {
					pass1=t3.getText();
				}*/
				
//				Class.forName("com.mysql.jdbc.Driver");
//				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
				String enkey="trankey";
				pass=encryption(pass,enkey);
				pass1=encryption(pass1,enkey);
				
				StegoClient.dos.writeUTF("l|"+username+"|"+pass+"|"+pass1);
				
				String status=StegoClient.dis.readUTF();
				
				if(status.equals("success")) {
					HomePage.homePageUser(null);
					Login.ml.setVisible(false);
				}
				
				else if(status.equals("failure")) {
					JOptionPane.showMessageDialog(this, "Invalid username or password");
					t2.setText("");
					t3.setText("");
				}
					
		
			}
			catch(Exception e) {
				System.out.print("LOGIN ERROR : "+e.getMessage());
			}
		}
		
		else if(lab.equals("cancel"))  {
			t1.setText("");
			t2.setText("");
			t3.setText("");
		}
		
		else if(lab.equals("eye")) {
			String p=t2.getText();
			
			t3.setText(p);
			
			t2.setVisible(false);
			t3.setVisible(true);
			
			b3.setIcon(new ImageIcon("D:/CSA/images/crosseye.png"));
			b3.setActionCommand("crosseye");
			
			add(t3);
		}
		
		else if(lab.equals("crosseye")) {
			String p=t3.getText();
			
			t2.setText(p);
			
			t2.setVisible(true);
			t3.setVisible(false);
			
			b3.setIcon(new ImageIcon("D:/CSA/images/eye.png"));
			b3.setActionCommand("eye");
		}
		
	}
	
	public static String getUser()  {
		return t1.getText();
	}
	
	public String encryption(String pt,String key) {
		
		int order[]=getOrder(key);
		int col=key.length();
		int row=pt.length()/col;
		
		if(pt.length()%col != 0)
			row++;
		
		char msgMat[][]=new char[row][col];
		
		int j=0,k=0;
		for(int i=0;i<pt.length();i++) {
			msgMat[k][j++]=pt.charAt(i);
			if(j==col) {
				j=0; k++;
			}
		}
		
		if(k==row-1 && j!=col) {
			while(j!=col)
				msgMat[k][j++]='_';
		}
		
		String ct="";
			
			for(int i=0;i<col;i++) {
				for(int t=0;t<row;t++) {
					ct=ct+msgMat[t][order[i]-1];
				}
			}
			
			return ct;
	}
			
		public int[] getOrder(String key) {
			int temp[]=new int[key.length()];
			
			SortedMap<String, Integer> sm= new TreeMap<String, Integer>();
			
			int j=1;
			for(int i=0;i<key.length();i++) {
				sm.put(""+key.charAt(i), new Integer(j++));
			}
			
			j=0;
			Set s = sm.entrySet();
			Iterator i = s.iterator();
			  
	        while (i.hasNext()) {
	            Map.Entry m = (Map.Entry)i.next();
	  
	            temp[j++] = (Integer)m.getValue();
	        }
	        
			return temp;
		}
	}


public class Login {
	static MyLogin ml;

	public static void loginUser(String[] args) {
		ml=new MyLogin("Login");
		ml.setBounds(300,30,1000,700);
		ml.setVisible(true);

	}
	
	public static String getUser1() {	
		return RightPanel.getUser();
	}
}
