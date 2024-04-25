package encrypt;

import homepage.HomePage;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import MiddleLayer.StegoClient;
import login.Login;
import decrypt.Decrypt;

class MyEncrypt  extends JFrame {
	
	TopPanel tp;
	DownPanel dp;
	
	MyEncrypt(String s) {
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
	JMenuItem hp,dec,logout;
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
		l2.setBounds(670,0,200,50);
		
		add(l2);
		
		mb=new JMenuBar();
		mb.setBackground(new Color(255,255,255));
		mb.setBounds(700,40,60,50);
		menu=new JMenu();
		menu.setIcon(new ImageIcon("D:/CSA/images/icon.png"));
		menu.setFont(new Font("Tahoma",0,22));
		
		hp=new JMenuItem("Home Page");
		hp.setBackground(new Color(0,0,0));
		hp.setForeground(new Color(255,255,255));
		hp.setFont(new Font("Tahoma",0,25));
		hp.setBorder(white);
		hp.addActionListener(this);
		
		dec=new JMenuItem("Decryption");
		dec.setFont(new Font("Tahoma",0,25));
		dec.setBackground(new Color(0,0,0));
		dec.setForeground(new Color(255,255,255));
		dec.setBorder(white);
		dec.addActionListener(this);
		
		logout=new JMenuItem("Log out");
		logout.setFont(new Font("Tahoma",0,25));
		logout.setBackground(new Color(0,0,0));
		logout.setForeground(new Color(255,255,255));
		logout.setBorder(white);
		logout.addActionListener(this);
		
		menu.add(hp);
		menu.add(dec);
		menu.add(logout);
		
		mb.add(menu);
		add(mb);
		add(l1);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String lab=ae.getActionCommand();
		
		if(lab.equals("Decryption"))  {
			Decrypt.decryptUser(null);
			Encrypt.me.setVisible(false);
		}
		
		else if(lab.equals("Home Page"))  {
			HomePage.homePageUser(null);
			Encrypt.me.setVisible(false);
		}
		
		else if(lab.equals("Log out"))  {
			//LogOut.logoutUser(null);
			try {
				StegoClient.dos.writeUTF("o");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			System.exit(0);
			//Encrypt.me.setVisible(false);
		}
		
	}
}

class DownPanel extends JPanel implements ActionListener {
		JLabel l1,l2,l3,l4;
		JTextField t1,t2,t3;
		JSeparator s1,s2,s3,s5;
		JButton b2,b3,b4;
		JTextArea t4;
		//String STEGIMAGEFILE;
		
	DownPanel()  {
		setLayout(null);
		setBackground(new Color(0,0,0));
		
		l1=new JLabel("Reciever Id : ");
		l1.setFont(new Font("Tahoma",0,19));
		l1.setForeground(new Color(255,255,255));
		l1.setBounds(110,20,150,50);
		
		t1=new JTextField();
		t1.setBounds(110,60,600,30);
		t1.setFont(new Font("Tahoma",0,19));
		t1.setForeground(new Color(255,255,255));
		t1.setBackground(new Color(0,0,0));
		t1.setCaretColor(new Color(255,255,255));
		t1.setBorder(null);
		
		s1=new JSeparator();
		s1.setOrientation(SwingConstants.HORIZONTAL);
		s1.setBounds(110,100,600,10);
		
		l2=new JLabel("Key: ");
		l2.setFont(new Font("Tahoma",0,19));
		l2.setForeground(new Color(255,255,255));
		l2.setBounds(110,110,150,50);
		
		t2=new JTextField();
		t2.setBounds(110,150,600,30);
		t2.setFont(new Font("Tahoma",0,19));
		t2.setForeground(new Color(255,255,255));
		t2.setBackground(new Color(0,0,0));
		t2.setCaretColor(new Color(255,255,255));
		t2.setBorder(null);
		
		s2=new JSeparator();
		s2.setOrientation(SwingConstants.HORIZONTAL);
		s2.setBounds(110,190,600,10);
		
		l3=new JLabel("Choose Image : ");
		l3.setFont(new Font("Tahoma",0,19));
		l3.setForeground(new Color(255,255,255));
		l3.setBounds(110,210,200,50);
		
		t3=new JTextField();
		t3.setBounds(110,250,600,30);
		t3.setFont(new Font("Tahoma",0,19));
		t3.setForeground(new Color(255,255,255));
		t3.setBackground(new Color(0,0,0));
		t3.setCaretColor(new Color(255,255,255));
		t3.setBorder(null);
		
		s3=new JSeparator();
		s3.setOrientation(SwingConstants.HORIZONTAL);
		s3.setBounds(110,290,600,10);
		
		b4=new JButton("Browse...");
		b4.setForeground(new Color(255,255,255));
		b4.setBackground(new Color(0,153,153));
		b4.setBounds(740,250,150,40);
		b4.setFont(new Font("Tahoma",0,19));
		b4.addActionListener(this);
		
//		l5=new JLabel("Output File : ");
//		l5.setFont(new Font("Tahoma",0,19));
//		l5.setForeground(new Color(255,255,255));
//		l5.setBounds(110,310,150,50);
//		
//		t5=new JTextField();
//		t5.setBounds(110,350,600,30);
//		t5.setFont(new Font("Tahoma",0,19));
//		t5.setForeground(new Color(255,255,255));
//		t5.setBackground(new Color(0,0,0));
//		t5.setBorder(null);
//		t5.setCaretColor(new Color(255,255,255));
//		t5.addActionListener(this);
		
//		s5=new JSeparator();
//		s5.setOrientation(SwingConstants.HORIZONTAL);
//		s5.setBounds(110,390,600,10);
		
		l4=new JLabel("Message: ");
		l4.setFont(new Font("Tahoma",0,19));
		l4.setForeground(new Color(255,255,255));
		l4.setBounds(110,310,150,50);
		
		t4=new JTextArea();
		t4.setBounds(110,360,600,100);
		t4.setFont(new Font("Tahoma",0,19));
		t4.setForeground(new Color(0,0,0));
		t4.setBackground(new Color(255,255,255));
		/*JScrollPane scroll = new JScrollPane(t4);
		scroll.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);*/        
		//scrollPane.setBounds(10,20,10,20);
		t4.setBorder(null);
		
		//add(scrollPane);
		
//		b1=new JButton("encrypt");
//		b1.setBounds(250,600,110,40);
//		b1.setBackground(new Color(0,153,153));
//		b1.setForeground(new Color(255,255,255));
//		b1.setFont(new Font("Tahoma",0,19));
//		b1.addActionListener(this);
		
		b2=new JButton("send");
		b2.setBounds(410,500,100,40);
		b2.setBackground(new Color(0,153,153));
		b2.setForeground(new Color(255,255,255));
		b2.setFont(new Font("Tahoma",0,19));
		b2.addActionListener(this);
		
		b3=new JButton("cancel");
		b3.setBounds(560,500,100,40);
		b3.setBackground(new Color(0,153,153));
		b3.setForeground(new Color(255,255,255));
		b3.setFont(new Font("Tahoma",0,19));
		b3.addActionListener(this);
		
		add(l1); 	//add(l5);
		add(t1);	//add(t5);
		add(s1);	//add(s5);
		add(l2);
		add(t2);
		add(s2);
		add(l3);
		add(t3);
		add(s3);
		add(b4);
		add(l4);
		add(t4);
		//add(scroll);
		//add(b1);
		add(b2);
		add(b3);
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String lab=ae.getActionCommand();
		String path="";
		File selectedFile;
		//final String MESSAGE = t4.getText();
		// InputStream in = null;
		
		if(lab.equals("Browse..."))   {
		
			JFileChooser fc=new JFileChooser();
			fc.setCurrentDirectory(new File(System.getProperty("user.home")));
			FileNameExtensionFilter filter=new FileNameExtensionFilter("*.images","jpeg");
			fc.addChoosableFileFilter(filter);
		
			int result=fc.showOpenDialog(null);
		
			if(result==JFileChooser.APPROVE_OPTION)  {
				selectedFile=fc.getSelectedFile();
				
				path=selectedFile.getAbsolutePath(); 
				
				String ex=path.substring(path.lastIndexOf("."));
				//System.out.print(path);
				t3.setText(path);
				
				if(!ex.equals(".jpeg"))   {
					JOptionPane.showMessageDialog(this, "Please Enter only JPEG images as input !");
					t3.setText("");
				}
				
			}
		}
		
		/*else if(lab.equals("encrypt"))  {
			try {
			//String name=t5.getText();
			String exten=name.substring(name.lastIndexOf("."));
			
				if(!exten.equals(".png"))  {
					JOptionPane.showMessageDialog(this, "Please enter only png image as output. !");
				}
				
				else {
					
					if(t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("") || t4.getText().equals("") || t5.getText().equals(""))  {
						JOptionPane.showMessageDialog(this, "Please enter all fields !");
					}
					
					else {
					
					STEGIMAGEFILE=name;
					
					int[] bits=bit_Msg(MESSAGE);
					BufferedImage theImage=readImageFile(t3.getText());
					
					try  {
						hideTheMessage(bits, theImage);
					}
					catch(Exception e)  {
						System.out.println("ENCRYPTION ERROR : "+e.getMessage());
					}
				}	
				}
			}
		
			catch(Exception e)  {
				System.out.println("ENCRYPTION ERROR : "+e.getMessage());
				if(t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("") || t4.getText().equals("") || t5.getText().equals(""))  {
					JOptionPane.showMessageDialog(this, "Please enter all fields !");
				}
			}
		}*/
		
		else if(lab.equals("send"))  {
			String s=Login.getUser1();
			String r=t1.getText();
			String key=t2.getText();
			File file=new File(t3.getText());
			String msg=t4.getText();
			
			try {
				
				
				if(t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("") || t4.getText().equals(""))  {
					JOptionPane.showMessageDialog(this, "Please enter all fields !");
				}
				
				else {
					String enkey="trankey";
					key=encryption(key,enkey);
					msg=encryption(msg,enkey);
					System.out.print(msg);
					
					StegoClient.dos.writeUTF("e|"+r+"|"+s+"|"+key+"|"+msg);
					String exist=StegoClient.dis.readUTF();
					
					if(exist.equals("NotExist")) {
						JOptionPane.showMessageDialog(this, "Such Reciever not exist! \n Please enter valid Reciever");
					}
					
					else if(exist.equals("Exist")) {
						//InputStream fis=new FileInputStream(STEGIMAGEFILE);
						InputStream fis=new FileInputStream(file);
						
						while(fis.available()!=0) {
							StegoClient.dos.write(fis.read());
						}
						
						
							t1.setText("");
							t2.setText("");
							t3.setText("");
							t4.setText("");
							//t5.setText("");
							JOptionPane.showMessageDialog(this, "Your Data has sent successfully...");
						
					}
				}
				
				}
				
				catch(Exception e)  {
					System.out.println("ENCRYPTION ERROR : "+e.getMessage());
				}	
		}
		
		else if(lab.equals("cancel"))  {
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			
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
	
	/*String getCoPath(String s) {
		s.replace("\\","\\\\");
		return s;
	}*/
	
	/*int[] bit_Msg(String msg){
		int j=0;
		int[] b_msg=new int[msg.length()*8];
		for(int i=0;i<msg.length();i++){
			int x=msg.charAt(i);
			String x_s=Integer.toBinaryString(x);
			while(x_s.length()!=8){
				x_s='0'+x_s;
			}
			//System.out.println("dec value for "+x +" is "+x_s);

			for(int i1=0;i1<8;i1++) {
			    b_msg[j] = Integer.parseInt(String.valueOf(x_s.charAt(i1)));
			    j++;
			  };
		}
		
		return b_msg;
	}
	
	BufferedImage readImageFile(String COVERIMAGEFILE){
		BufferedImage theImage = null;
		File p = new File (COVERIMAGEFILE);
		try{
		theImage = ImageIO.read(p);
		}catch (IOException e){
		e.printStackTrace();
		System.exit(1);
		}
		return theImage;
		}
	
	void hideTheMessage (int[] bits, BufferedImage theImage) throws Exception{
		File f = new File (STEGIMAGEFILE);
		BufferedImage sten_img=null;
		int bit_l=bits.length/8;
		int[] bl_msg=new int[8];
		//System.out.println("bit lent "+bit_l);
		String bl_s=Integer.toBinaryString(bit_l);
		while(bl_s.length()!=8){
			bl_s='0'+bl_s;
		}
		for(int i1=0;i1<8;i1++) {
			bl_msg[i1] = Integer.parseInt(String.valueOf(bl_s.charAt(i1)));
		  };
	int j=0;
	int b=0;
	int currentBitEntry=8;

	for (int x = 0; x < theImage.getWidth(); x++){
	for ( int y = 0; y < theImage.getHeight(); y++){
		if(x==0&&y<8){
			int currentPixel = theImage.getRGB(x, y);	
			int ori=currentPixel;
			int red = currentPixel>>16;
			red = red & 255;
			int green = currentPixel>>8;
			green = green & 255;
			int blue = currentPixel;
			blue = blue & 255;
			String x_s=Integer.toBinaryString(blue);
			String sten_s=x_s.substring(0, x_s.length()-1);
			sten_s=sten_s+Integer.toString(bl_msg[b]);

			//j++;
			int temp=Integer.parseInt(sten_s,2);
			int s_pixel=Integer.parseInt(sten_s, 2);
			int a=255;
			int rgb = (a<<24) | (red<<16) | (green<<8) | s_pixel;
			theImage.setRGB(x, y, rgb);
			//System.out.println("original "+ori+" after "+theImage.getRGB(x, y));
			ImageIO.write(theImage, "png", f);
	b++;

		}
		else if (currentBitEntry < bits.length+8 ){

		int currentPixel = theImage.getRGB(x, y);	
		int ori=currentPixel;
		int red = currentPixel>>16;
		red = red & 255;
		int green = currentPixel>>8;
		green = green & 255;
		int blue = currentPixel;
		blue = blue & 255;
		String x_s=Integer.toBinaryString(blue);
		String sten_s=x_s.substring(0, x_s.length()-1);
		sten_s=sten_s+Integer.toString(bits[j]);
		j++;
		int temp=Integer.parseInt(sten_s,2);
		int s_pixel=Integer.parseInt(sten_s, 2);
		
		int a=255;
		int rgb = (a<<24) | (red<<16) | (green<<8) | s_pixel;
		theImage.setRGB(x, y, rgb);
		//System.out.println("original "+ori+" after "+theImage.getRGB(x, y));
		ImageIO.write(theImage, "png", f);

		currentBitEntry++;	
		//System.out.println("curre "+currentBitEntry);
		}
	}
	}
	}*/
		


public class Encrypt {
	static MyEncrypt me;

	public static void encryptUser(String[] args) {
		me=new MyEncrypt("Encrypt");
		me.setBounds(200,0,1000,800);
		me.setVisible(true);

	}

}
