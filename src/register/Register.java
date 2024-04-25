package register;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
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


import login.Login;
import MiddleLayer.StegoClient;

class MyRegister  extends JFrame {
	LeftPanel lf;
	RightPanel rp;
	MyRegister(String s) {
		super(s);
		
		Container cont=getContentPane();
		cont.setLayout(null);
		
		Border white=new LineBorder(Color.white,4,true);
		
		lf=new LeftPanel();
		lf.setBounds(0,0,400,800);
		lf.setBorder(white);
	
		rp=new RightPanel();
		rp.setBounds(400,0,800,800);
		
		cont.add(lf);
		cont.add(rp);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
}

class LeftPanel extends JPanel implements ActionListener {
	JButton b4;
	JLabel l1;
	
	
	LeftPanel() {
		setLayout(null);
		
		setBackground(new Color(0,153,153));
		
		l1=new JLabel();
		l1.setIcon(new ImageIcon("D:/CSA/images/logo2.jpg"));
		l1.setBounds(100,250,210,200);
		
		b4=new JButton("login");
		b4.setBounds(100,500,200,50);
		b4.setBackground(new Color(0,0,0));
		b4.setForeground(new Color(255,255,255));
		b4.setFont(new Font("Tahoma",0,22));
		b4.addActionListener(this);
		
		add(l1);
		add(b4);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String lab=ae.getActionCommand();
		
		if(lab.equals("login"))  {
			Login.loginUser(null);
			Register.rl.setVisible(false);
		}
		
	}
	
}

class RightPanel extends JPanel implements ActionListener  {
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11;
	JTextField t1,t2,t3,t4;
	JPasswordField t5,t6;
	JSeparator s1,s2,s3,s4,s5,s6;
	JButton b1,b2;
	
	RightPanel() {
		setLayout(null);
		setBackground(new Color(0,0,0));
		
		l3=new JLabel();
		l3.setIcon(new ImageIcon("D:/CSA/images/images (4).png"));
		l3.setBounds(300,10,150,150);
		
		l1=new JLabel("Name : ");
		l1.setFont(new Font("Tahoma",0,19));
		l1.setForeground(new Color(255,255,255));
		l1.setBounds(110,160,150,50);
		
		t1=new JTextField();
		t1.setBounds(110,200,600,30);
		t1.setFont(new Font("Tahoma",0,19));
		t1.setForeground(new Color(255,255,255));
		t1.setBackground(new Color(0,0,0));
		t1.setCaretColor(new Color(255,255,255));
		t1.setBorder(null);
		
		l7=new JLabel("*");
		l7.setFont(new Font("Tahoma",0,15));
		l7.setForeground(new Color(255,204,203));
		l7.setBounds(100,160,100,20);
		
		s1=new JSeparator();
		s1.setOrientation(SwingConstants.HORIZONTAL);
		s1.setBounds(110,240,600,10);
		
		l2=new JLabel("Contact Number : ");
		l2.setFont(new Font("Tahoma",0,19));
		l2.setForeground(new Color(255,255,255));
		l2.setBounds(110,260,200,50);
		
		t2=new JTextField();
		t2.setBounds(110,300,600,30);
		t2.setFont(new Font("Tahoma",0,19));
		t2.setForeground(new Color(255,255,255));
		t2.setBackground(new Color(0,0,0));
		t2.setCaretColor(new Color(255,255,255));
		t2.setBorder(null);
		
		l8=new JLabel("*");
		l8.setFont(new Font("Tahoma",0,15));
		l8.setForeground(new Color(255,204,203));
		l8.setBounds(100,260,100,20);
		
		s2=new JSeparator();
		s2.setOrientation(SwingConstants.HORIZONTAL);
		s2.setBounds(110,340,600,10);
		
		l4=new JLabel("Username : ");
		l4.setFont(new Font("Tahoma",0,19));
		l4.setForeground(new Color(255,255,255));
		l4.setBounds(110,360,150,50);
		
		t4=new JTextField();
		t4.setBounds(110,400,600,30);
		t4.setFont(new Font("Tahoma",0,19));
		t4.setForeground(new Color(255,255,255));
		t4.setBackground(new Color(0,0,0));
		t4.setCaretColor(new Color(255,255,255));
		t4.setBorder(null);
		
		l11=new JLabel("*");
		l11.setFont(new Font("Tahoma",0,15));
		l11.setForeground(new Color(255,204,203));
		l11.setBounds(100,360,100,20);
		
		s4=new JSeparator();
		s4.setOrientation(SwingConstants.HORIZONTAL);
		s4.setBounds(110,440,600,10);
		
		l5=new JLabel("Password : ");
		l5.setFont(new Font("Tahoma",0,19));
		l5.setForeground(new Color(255,255,255));
		l5.setBounds(110,460,150,50);
		
		t5=new JPasswordField();
		t5.setBounds(110,500,600,30);
		t5.setFont(new Font("Tahoma",0,19));
		t5.setForeground(new Color(255,255,255));
		t5.setBackground(new Color(0,0,0));
		t5.setCaretColor(new Color(255,255,255));
		t5.setBorder(null);
		
		l9=new JLabel("*");
		l9.setFont(new Font("Tahoma",0,15));
		l9.setForeground(new Color(255,204,203));
		l9.setBounds(100,460,100,20);
		
		s5=new JSeparator();
		s5.setOrientation(SwingConstants.HORIZONTAL);
		s5.setBounds(110,540,600,10);
		
		l6=new JLabel("Confirm Password : ");
		l6.setFont(new Font("Tahoma",0,19));
		l6.setForeground(new Color(255,255,255));
		l6.setBounds(110,560,200,50);
		
		t6=new JPasswordField();
		t6.setBounds(110,600,600,30);
		t6.setFont(new Font("Tahoma",0,19));
		t6.setForeground(new Color(255,255,255));
		t6.setBackground(new Color(0,0,0));
		t6.setCaretColor(new Color(255,255,255));
		t6.setBorder(null);
		
		l10=new JLabel("*");
		l10.setFont(new Font("Tahoma",0,15));
		l10.setForeground(new Color(255,204,203));
		l10.setBounds(100,560,100,20);
		
		s6=new JSeparator();
		s6.setOrientation(SwingConstants.HORIZONTAL);
		s6.setBounds(110,640,600,10);
		
		b1=new JButton("register");
		b1.setBounds(250,670,150,40);
		b1.setBackground(new Color(0,153,153));
		b1.setForeground(new Color(255,255,255));
		b1.setFont(new Font("Tahoma",0,22));
		b1.addActionListener(this);
		
		b2=new JButton("cancel");
		b2.setBounds(450,670,150,40);
		b2.setBackground(new Color(0,153,153));
		b2.setForeground(new Color(255,255,255));
		b2.setFont(new Font("Tahoma",0,22));
		b2.addActionListener(this);
		
		add(l3);    add(l7);
		add(l1);  	add(l8);
		add(t1);	add(l9);
		add(s1);	add(l10);	
		add(l2);	add(l11);
		add(t2);
		add(s2);
		add(b1);
		add(b2);
		add(l4);
		add(t4);
		add(s4);
		add(l5);
		add(t5);
		add(s5);
		add(l6);
		add(t6);
		add(s6);
		
	}

	public void actionPerformed(ActionEvent ae) {
		
		String lab=ae.getActionCommand();
		
		if(lab.equals("register")) {
			try {
			String username=t4.getText();
			BigInteger cont=new BigInteger(t2.getText());  
			String name=t1.getText();
			String pass=t5.getText();
			String cpass=t6.getText();
			
			int a=cont.toString().length();
			int b=pass.length();
			
			if(name.equals("") || cont.toString().equals("") || username.equals("") || pass.equals("") || t6.equals(""))  {
				JOptionPane.showMessageDialog(this, "Please Enter all required fields!");
			}
			
			else if(a!=10)  {
				JOptionPane.showMessageDialog(this, "Please Enter proper contact number!");
			}
			
			else if(b<8)  {
				JOptionPane.showMessageDialog(this, "Please Enter password atlest of 8 characters!");
			}
			
			else if(!pass.equals(cpass))  {
				JOptionPane.showMessageDialog(this, "Please Enter confirm password same as password!");
			}
			
			else {
				
				String enkey="trankey";
				pass=encryption(pass,enkey);
			
				StegoClient.dos.writeUTF("r|"+username+"|"+cont+"|"+name+"|"+pass);
				String status=StegoClient.dis.readUTF();
				
				if(status.equals("AlreadyUser"))
					JOptionPane.showMessageDialog(this, "Username Already Exist!!!"); 
				else if(status.equals("Success")) {
					t1.setText("");
					t2.setText("");
					t4.setText("");
					t5.setText("");
					t6.setText("");
					JOptionPane.showMessageDialog(this, "Your Account is created successfully");
				}
			
			}
		}
			
			catch(Exception e) {
				System.out.print("REGISTER ERROR : "+e.getMessage());
				if(t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("") || t4.getText().equals("") || t5.getText().equals(""))  {
					JOptionPane.showMessageDialog(this, "Please Enter all required fields!");
				}
			}
			
		}
		
		else if(lab.equals("cancel"))  {
			t1.setText("");
			t2.setText("");
			t4.setText("");
			t5.setText("");
			t6.setText("");
		}
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


public class Register {
	static MyRegister rl;

	public static void registerUser(String[] args) {
		rl=new MyRegister("Register");
		rl.setBounds(250,0,1200,800);
		rl.setVisible(true);

	}

}
