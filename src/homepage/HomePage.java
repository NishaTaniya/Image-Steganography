package homepage;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import MiddleLayer.StegoClient;
import login.Login;
import decrypt.Decrypt;
import encrypt.Encrypt;


class MyHomePage  extends JFrame {
	
	TopPanel tp;
	DownPanel dp;
	//JScrollPane scroll;
	
	MyHomePage(String s) {
		super(s);
		
		Container cont=getContentPane();
		cont.setLayout(null);
		
		tp=new TopPanel();
		tp.setBounds(0,0,1200,120);
	
		dp=new DownPanel();
		dp.setBounds(0,100,1200,900);
		
		/*scroll=new JScrollPane(dp);
		scroll.setBounds(1180,120,20,900);
		scroll.setVisible(true);*/
		
		//cont.add(scroll);
		
		cont.add(tp);
		cont.add(dp);
		pack();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
}

class TopPanel extends JPanel implements ActionListener {
	JMenuBar mb;
	JMenu menu;
	JMenuItem enc,dec,logout;
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
		
		menu.add(enc);
		menu.add(dec);
		menu.add(logout);
		
		add(l1);
		mb.add(menu);
		add(mb);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String lab=ae.getActionCommand();
		
		if(lab.equals("Decryption"))  {
			Decrypt.decryptUser(null);
			HomePage.mh.setVisible(false);
		}
		
		else if(lab.equals("Encryption"))  {
			Encrypt.encryptUser(null);
			HomePage.mh.setVisible(false);
		}
		
		else if(lab.equals("Log out"))  {
			//LogOut.logoutUser(null);
			try {
				StegoClient.dos.writeUTF("o");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			//HomePage.mh.setVisible(false);
			System.exit(0);
		}
	}
}

class DownPanel extends JPanel  {
	JSeparator s1;
	JLabel l1,l2,l3,l4,l5,l6;
	ImagePanel i;
	
DownPanel()  {
	setLayout(null);
	setBackground(new Color(0,0,0));
	
	s1=new JSeparator();
	s1.setOrientation(SwingConstants.HORIZONTAL);
	s1.setBounds(10,400,1160,10);
	
	l1=new JLabel("Features in our project :  ");
	l1.setFont(new Font("Tahoma",0,19));
	l1.setForeground(new Color(255,255,255));
	l1.setBounds(50,420,1000,20);
	
	l2=new JLabel("1. Encrypt the given message in image  ");
	l2.setFont(new Font("Tahoma",0,19));
	l2.setForeground(new Color(255,255,255));
	l2.setBounds(50,450,1000,30);
	
	l3=new JLabel("2. Decrpy the image into message  ");
	l3.setFont(new Font("Tahoma",0,19));
	l3.setForeground(new Color(255,255,255));
	l3.setBounds(50,490,1000,30);
	
	l4=new JLabel("Contact Information : ");
	l4.setFont(new Font("Tahoma",0,19));
	l4.setForeground(new Color(255,255,255));
	l4.setBounds(50,550,1000,30);
	
	l5=new JLabel("Number : 9427890342");
	l5.setFont(new Font("Tahoma",0,19));
	l5.setForeground(new Color(255,255,255));
	l5.setBounds(50,590,1000,30);
	
	l6=new JLabel("Email Id : stego@gmail.com");
	l6.setFont(new Font("Tahoma",0,19));
	l6.setForeground(new Color(255,255,255));
	l6.setBounds(50,620,1000,30);
	
	i=new ImagePanel();
	i.setBounds(60,50,1050,300);
	i.setBackground(new Color(0,0,0));
	i.setVisible(true);
	
	add(i);
	add(l1);
	add(l2);
	add(l3);
	add(l4);
	add(l5);
	add(l6);
	add(s1);
	
	
}
}

class ImagePanel extends JPanel {
	JLabel pic;
	Timer tm;
	int x=0;
	String[] list={ "D:/CSA/images/h1.jpeg",
					"D:/CSA/images/s2.png",
					"D:/CSA/images/s3.png",
					"D:/CSA/images/h2.jpeg"
				  };
	
	ImagePanel()   {
		pic=new JLabel();
		pic.setBounds(10,10,1000,300);
		pic.setBackground(new Color(0,0,0));
		setImageSize(3);
		
		tm=new Timer(2500,new ActionListener() {
		
		public void actionPerformed(ActionEvent ae)  {
			setImageSize(x);
			
			x+=1;
			if(x>=list.length)  {
				x=0;
			}
		}
		});
		add(pic);
		tm.start();
		setLayout(null);
		
	}
	
	public void setImageSize(int i)  {
		ImageIcon icon=new ImageIcon(list[i]);
		Image img=icon.getImage();
		Image newImag=img.getScaledInstance(1000,300,Image.SCALE_SMOOTH);
		ImageIcon newImc=new ImageIcon(newImag);
		pic.setIcon(newImc);
		
	}
}



public class HomePage {
	static MyHomePage mh;

	public static void homePageUser(String[] args) {
		mh=new MyHomePage("HomePage");
		mh.setBounds(200,0,1200,800);
		mh.setVisible(true);


	}

}
