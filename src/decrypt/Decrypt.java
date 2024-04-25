package decrypt;

import homepage.HomePage;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import MiddleLayer.StegoClient;
import login.Login;
import encrypt.Encrypt;

class MyDecrypt extends JFrame {
	
	TopPanel tp;
	DownPanel dp;
	
	MyDecrypt(String s) {
		super(s);
		
		Container cont=getContentPane();
		cont.setLayout(null);
		
		//Border white=new LineBorder(Color.white,4,true);
		
		tp=new TopPanel();
		tp.setBounds(0,0,1500,120);
		//tp.setBorder(white);
	
		dp=new DownPanel();
		dp.setBounds(0,100,1500,900);
		
		cont.add(tp);
		cont.add(dp);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
}

class TopPanel extends JPanel implements ActionListener {
	JMenuBar mb;
	JMenu menu;
	JMenuItem enc,hp,logout;
	Encrypt e;
	JLabel l1;

	TopPanel()  {
		setLayout(null);
		
		Border white=new LineBorder(Color.white,1,true);
		
		setBackground(new Color(0,153,153));
		l1=new JLabel();
		l1.setIcon(new ImageIcon("D:/CSA/images/logo1.jpg"));
		l1.setBounds(80,12,120,100);
		
		JLabel l2=new JLabel("Welcome "+Login.getUser1());
		l2.setFont(new Font("Tahoma",0,18));
		l2.setForeground(new Color(255,255,255));
		l2.setBounds(870,0,200,50);
		
		add(l2);
		
		mb=new JMenuBar();
		mb.setBackground(new Color(255,255,255));
		mb.setBounds(900,40,60,50);
		menu=new JMenu();
		menu.setIcon(new ImageIcon("D:/CSA/images/icon.png"));
		menu.setFont(new Font("Tahoma",0,22));
		
		enc=new JMenuItem("Encryption");
		enc.setBackground(new Color(0,0,0));
		enc.setForeground(new Color(255,255,255));
		enc.setFont(new Font("Tahoma",0,25));
		enc.setBorder(white);
		enc.addActionListener(this);
		
		hp=new JMenuItem("Home Page");
		hp.setBackground(new Color(0,0,0));
		hp.setForeground(new Color(255,255,255));
		hp.setFont(new Font("Tahoma",0,25));
		hp.setBorder(white);
		hp.addActionListener(this);
		
		logout=new JMenuItem("Log out");
		logout.setFont(new Font("Tahoma",0,25));
		logout.setBackground(new Color(0,0,0));
		logout.setForeground(new Color(255,255,255));
		logout.setBorder(white);
		logout.addActionListener(this);
		
		menu.add(hp);
		menu.add(enc);
		menu.add(logout);
		
		add(l1);
		mb.add(menu);
		add(mb);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String lab=ae.getActionCommand();
		
		if(lab.equals("Encryption"))  {
			Encrypt.encryptUser(null);
			Decrypt.me.setVisible(false);
		}
		
		else if(lab.equals("Home Page"))  {
			HomePage.homePageUser(null);
			Decrypt.me.setVisible(false);
		}
		
		else if(lab.equals("Log out"))  {
//			LogOut.logoutUser(null);
//			Decrypt.me.setVisible(false);
			try {
				StegoClient.dos.writeUTF("o");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			System.exit(0);
		}
		
	}
}

class DownPanel extends JPanel implements ActionListener {
		JLabel l1,l2,l4;
		JTextField t1,t2;
		JSeparator s1,s2,s4;
		JButton b1,b2,b3,b4,b5;
		JTextArea t4;
		
	DownPanel()  {
		setLayout(null);
		setBackground(new Color(0,0,0));
		
		l1=new JLabel("Sender Id : ");
		l1.setFont(new Font("Tahoma",0,19));
		l1.setForeground(new Color(255,255,255));
		l1.setBounds(110,60,150,50);
		
		t1=new JTextField();
		t1.setBounds(110,110,600,30);
		t1.setFont(new Font("Tahoma",0,19));
		t1.setForeground(new Color(255,255,255));
		t1.setBackground(new Color(0,0,0));
		t1.setCaretColor(new Color(255,255,255));
		t1.setBorder(null);
		
		s1=new JSeparator();
		s1.setOrientation(SwingConstants.HORIZONTAL);
		s1.setBounds(110,150,600,10);
		
		l2=new JLabel("Key: ");
		l2.setFont(new Font("Tahoma",0,19));
		l2.setForeground(new Color(255,255,255));
		l2.setBounds(110,170,150,50);
		
		t2=new JTextField();
		t2.setBounds(110,220,600,30);
		t2.setFont(new Font("Tahoma",0,19));
		t2.setForeground(new Color(255,255,255));
		t2.setBackground(new Color(0,0,0));
		t2.setCaretColor(new Color(255,255,255));
		t2.setBorder(null);
		
		s2=new JSeparator();
		s2.setOrientation(SwingConstants.HORIZONTAL);
		s2.setBounds(110,260,600,10);
		
//		l3=new JLabel("Output Image File : ");
//		l3.setFont(new Font("Tahoma",0,19));
//		l3.setForeground(new Color(255,255,255));
//		l3.setBounds(110,280,200,50);
//		
//		t3=new JTextField();
//		t3.setBounds(110,330,600,30);
//		t3.setFont(new Font("Tahoma",0,19));
//		t3.setForeground(new Color(255,255,255));
//		t3.setBackground(new Color(0,0,0));
//		t3.setCaretColor(new Color(255,255,255));
//		t3.setBorder(null);
//		t3.addActionListener(this);
//		
//		s3=new JSeparator();
//		s3.setOrientation(SwingConstants.HORIZONTAL);
//		s3.setBounds(110,370,600,10);
		
		s4=new JSeparator();
		s4.setOrientation(SwingConstants.VERTICAL);
		s4.setBounds(750,50,10,750);
		
		b2=new JButton("show list");
		b2.setBounds(900,80,150,40);
		b2.setBackground(new Color(0,153,153));
		b2.setForeground(new Color(255,255,255));
		b2.setFont(new Font("Tahoma",0,19));
		b2.addActionListener(this);
		
		
		/*l3=new JLabel("Choose Image : ");
		l3.setFont(new Font("Tahoma",0,22));
		l3.setForeground(new Color(255,255,255));
		l3.setBounds(110,340,200,80);
		
		t3=new JTextField();
		t3.setBounds(110,400,600,50);
		t3.setFont(new Font("Tahoma",0,22));
		t3.setForeground(new Color(255,255,255));
		t3.setBackground(new Color(0,0,0));
		t3.setBorder(null);
		
		s3=new JSeparator();
		s3.setOrientation(SwingConstants.HORIZONTAL);
		s3.setBounds(110,460,600,10);
		
		b4=new JButton("Browse...");
		b4.setForeground(new Color(255,255,255));
		b4.setBackground(new Color(0,153,153));
		b4.setBounds(740,400,150,50);
		b4.setFont(new Font("Tahoma",0,22));
		b4.addActionListener(this);*/
		
		l4=new JLabel("Message: ");
		l4.setFont(new Font("Tahoma",0,19));
		l4.setForeground(new Color(255,255,255));
		l4.setBounds(110,300,150,50);
		
		t4=new JTextArea();
		t4.setBounds(110,350,600,100);
		t4.setFont(new Font("Tahoma",0,19));
		t4.setForeground(new Color(0,0,0));
		t4.setBackground(new Color(255,255,255));
		t4.setBorder(null);
		
		b1=new JButton("decrypt");
		b1.setBounds(250,570,120,40);
		b1.setBackground(new Color(0,153,153));
		b1.setForeground(new Color(255,255,255));
		b1.setFont(new Font("Tahoma",0,19));
		b1.addActionListener(this);
		
		b3=new JButton("cancel");
		b3.setBounds(450,570,120,40);
		b3.setBackground(new Color(0,153,153));
		b3.setForeground(new Color(255,255,255));
		b3.setFont(new Font("Tahoma",0,19));
		b3.addActionListener(this);
		
		add(l1);
		add(t1);
		add(s1);
		add(l2);
		add(t2);
		add(s2);
		//add(s3);
		//add(l3);
		//add(t3);
		add(s4);
		add(b2);
		add(l4);
		add(t4);
		add(b1);
		add(b3);
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String lab=ae.getActionCommand();
		Blob blob;
		
		if(lab.equals("decrypt"))  {
			
			try {
			String s=t1.getText();
			String r=Login.getUser1();
			String key=t2.getText();
//			int flag=0;
//			String opFile=t3.getText();
//			String exten=opFile.substring(opFile.lastIndexOf("."));
//			
//			if(!exten.equals(".png"))  {
//				JOptionPane.showMessageDialog(this, "Please enter output image as png only !");
//			}
			
			if(t1.getText().equals("") || t2.getText().equals(""))  {
				JOptionPane.showMessageDialog(this, "Please enter all fields !");
			}
			
			else  {
				String enkey="trankey";
				key=encryption(key,enkey);
				
				StegoClient.dos.writeUTF("dd|"+s+"|"+r+"|"+key);
				String response=StegoClient.dis.readUTF();
				
				if(response.equals("InvalidKey"))
					JOptionPane.showMessageDialog(this, "Your key is invalid");
				else if(response.equals("NoData"))
					JOptionPane.showMessageDialog(this, "Wrong sender Id or You have already seen all messages!!!");
				else {
					response=decryption(response,enkey);
					t4.setText(response);
					b2.doClick();
					
				}
					
				
				/*Class.forName("com.mysql.jdbc.Driver");
				
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
				PreparedStatement ps=con.prepareStatement("select * from SERE"); 
				ResultSet rs=ps.executeQuery();
				
				File file=new File(opFile);
				FileOutputStream fos=new FileOutputStream(file);
				byte b[];
				
				
				while(rs.next()){
					
					if(s.equals(rs.getString(2)) && r.equals(rs.getString(3)) && !rs.getBoolean(6))  {
						flag=1;
						if(key.equals(rs.getString(4)))  {
							
							blob=rs.getBlob("IMG");
							b=blob.getBytes(1,(int)blob.length());
							fos.write(b);
							
							BufferedImage yImage=readImageFile(file);
							String b_msg=DecodeTheMessage(yImage);
							
							String msg="";
							
							for(int i=0;i<b_msg.length();i=i+8){
								
								String sub=b_msg.substring(i,i+8);
								
								int m=Integer.parseInt(sub,2);
								char ch=(char) m;
								//System.out.println("m "+m+" c "+ch);
								msg+=ch;
							}
							
							t4.setText(msg);
							
							Statement st=con.createStatement();
							
							st.executeUpdate("update SERE set SEEN=1 where IND="+rs.getInt(1));
							
							st.close();
							break;
							
						
						}
						
						else {
							JOptionPane.showMessageDialog(this, "Your key is invalid");
						}
					}
				}
				
				if(flag==0)   {
					JOptionPane.showMessageDialog(this, "Wrong sender Id !!!");
				}
				
				ps.close();
				fos.close();
				con.close();*/
			
			}
			}
			catch(Exception e)  {
				System.out.print("DECRYPTION ERROR : "+e.getMessage());
				if(t1.getText().equals("") || t2.getText().equals(""))  {
					JOptionPane.showMessageDialog(this, "Please enter all fields !");
				}
			}
		}
		
		else if(lab.equals("cancel"))  {
			t1.setText("");
			t2.setText("");
			//t3.setText("");
			t4.setText("");
		}
		
		else if(lab.equals("show list"))  {
			JScrollPane jsp;
			JTable jt;
			
			try {
				StegoClient.dos.writeUTF("ds|"+Login.getUser1());
				
				int row=StegoClient.dis.readInt();
				int column=2;
				
				String head[]={"SENDER","SEEN"};
				String data[][]=new String[row][column];
				
				int i=0;
				while(i<row)  {
					String[] result=(StegoClient.dis.readUTF()).split("\\|");
					data[i][0]=result[0];
					data[i][1]=result[1];
					i++;
				}
				
				jt=new JTable(data,head);
				jt.setBackground(new Color(0,153,153));
				jt.setFont(new Font("Tahoma",0,15));
				jt.setForeground(new Color(255,255,255));
				jt.setAlignmentX(CENTER_ALIGNMENT);

				jsp=new JScrollPane(jt);
				jsp.setBounds(850,150,250,500);
				jsp.setVisible(true);
				
				add(jsp);
				
			}
			
			catch(Exception e)  {
				System.out.print("\n SHOW ERROR : "+e.getMessage());
			}
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
	
	public String decryption(String ct,String key) {
		int order[]=getOrder(key);
		int col=key.length();
		int row=ct.length()/col;
		
		if(ct.length()%col != 0)
			row++;
		
		//int order[]=getOrder(key);
		
		char original[][]=new char[row][col];
		
		int l=0;
		for(int i=0;i<col;i++) {
			for(int t=0;t<row;t++) {
				original[t][order[i]-1]=ct.charAt(l++);
			}
		}
		
		String pt="";
		for(int i=0;i<original.length;i++) {
			for(int n=0;n<original[i].length;n++) {
				if(original[i][n]!='_')
					pt=pt+original[i][n];
			}
		}
		
		
		return pt;
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

public class Decrypt {
	static MyDecrypt me;
	
	public static void decryptUser(String[] args) {
		me=new MyDecrypt("Decrypt");
		me.setBounds(200,0,1200,800);
		me.setVisible(true);

	}
}
