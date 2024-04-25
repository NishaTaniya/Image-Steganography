package MiddleLayer;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import homepage.HomePage;
import login.Login;

public class StegoServerThread extends Thread {
	private static String STEGIMAGEFILE = null;
	Socket s;
	DataInputStream in;
	DataOutputStream out;
	String result[];
	
	public StegoServerThread(Socket soc) throws IOException {
		this.s=soc;
		in=new DataInputStream(s.getInputStream());
		out=new DataOutputStream(s.getOutputStream());
	}
	
	@Override
	public void run() {
		try {	
			Class.forName("com.mysql.jdbc.Driver");			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");	
		
		do {
			String msg=in.readUTF();
			//System.out.println("Client "+this.getName()+"==>  "+(char)i);
			
			String result[]=msg.split("\\|");
			String flag=result[0];
			
			if(flag.equals("o"))
				break;
			
			else if(flag.equals("r")) {
				Statement st=con.createStatement();			
				ResultSet rs=st.executeQuery("select username from user1");
				boolean registerFlag=false;
				
				while(rs.next()) {
					if(rs.getString(1).equals(result[1])) {
						registerFlag=true;
					}
				}
				
				
				if(registerFlag) {
					out.writeUTF("AlreadyUser");
				}
				
				else {
					st.executeUpdate("insert into user1 values(NULL,'"+result[1]+"',"+result[2]+",'"+result[3]+"','"+result[4]+"')");
					out.writeUTF("Success");
				}
				
				rs.close();
				st.close();
				
			}
			
			else if(flag.equals("l")) {
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select username,password from user1");
				boolean loginFlag=false;
				
				while(rs.next())  {
					if(result[1].equals(rs.getString(1)) && (result[2].equals(rs.getString(2))) || result[3].equals(rs.getString(2)))  {
						out.writeUTF("success");
						loginFlag=true;
						break;
					}
				}
				
				if(loginFlag==false) {
					out.writeUTF("failure");
				}
				
				rs.close();
				st.close();
				
			}
			
			else if(flag.equals("e")) {
				String r=result[1];  String s=result[2];   String key=result[3];  String message=result[4];
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select username from user1");
				boolean existUserFlag=false;
				
				while(rs.next())  {
					if(r.equals(rs.getString(1)))  {
						existUserFlag=true;
						break;
					}
				}
				
				rs.close();
				
				if(existUserFlag) {
					out.writeUTF("Exist");
					PreparedStatement pstmt = con.prepareStatement("INSERT INTO SERE VALUES(NULL,'"+s+"','"+r+"','"+key+"',?,false)");
					OutputStream fos=new FileOutputStream("D:/CSA/images/input.jpeg");
					STEGIMAGEFILE="D:/CSA/images/output.png";
					
					while(in.available()!=0) {
						fos.write(in.read());
					}
					
					int[] bits=bit_Msg(message);
					BufferedImage theImage=readImageFile("D:/CSA/images/input.jpeg");
					
					try  {
						hideTheMessage(bits, theImage);
					}
					catch(Exception e)  {
						//System.out.println("ENCRYPTION ERROR : "+e.getMessage());
					}
					
					
				    FileInputStream in = new FileInputStream(STEGIMAGEFILE);
				     pstmt.setBlob(1,in);
				     
				    pstmt.execute(); 
					pstmt.close();
					
				}
				
				else {
					out.writeUTF("NotExist");
				}		
			}
			
			else if(flag.equals("ds")) {
				int row=0;
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select SENDERID,SEEN from SERE where RECIEVERID='"+result[1]+"'");
				
				rs.last();
				out.writeInt(rs.getRow());
				rs.beforeFirst();
				
				while(rs.next()) {
					out.writeUTF(rs.getString(1)+"|"+rs.getString(2));
				}
				
				rs.close();  st.close();
			}
			
			else if(flag.equals("dd")) {
				String s=result[1]; String r=result[2]; String key=result[3];
				PreparedStatement ps=con.prepareStatement("select * from SERE where senderId='"+s+"' and recieverId='"+r+"'"); 
				ResultSet rs=ps.executeQuery();
				
				
				String opFile="D:/CSA/images/output1.png";
				File file=new File(opFile);
				FileOutputStream fos=new FileOutputStream(file);
				byte b[];
				boolean empty=true,decryptFlag=false;
				
				while(rs.next()){
					empty=false;
					
					if(s.equals(rs.getString(2)) && r.equals(rs.getString(3)) && !rs.getBoolean(6))  {
						decryptFlag=true;
						if(key.equals(rs.getString(4)))  {
							
							Blob blob=rs.getBlob("IMG");
							b=blob.getBytes(1,(int)blob.length());
							fos.write(b);
							
							BufferedImage yImage=readImageFile(opFile);
							String b_msg=DecodeTheMessage(yImage);
							
							String resultMsg="";
							
							for(int i=0;i<b_msg.length();i=i+8){
								
								String sub=b_msg.substring(i,i+8);
								
								int m=Integer.parseInt(sub,2);
								char ch=(char) m;
								//System.out.println("m "+m+" c "+ch);
								resultMsg+=ch;
							}
							
							out.writeUTF(resultMsg);
							
							Statement st=con.createStatement();
							st.executeUpdate("update SERE set SEEN=1 where IND="+rs.getInt(1));
							st.close();
							break;
						}
						
						else {
							out.writeUTF("InvalidKey");
						}
					}
				}
				
				if(empty || !decryptFlag)   {
					out.writeUTF("NoData");
				}
				
				ps.close();
				fos.close();	
			}
			
		}while(true);
		
		con.close();
		out.close();
		in.close();
		s.close();	
		
		} catch (IOException | SQLException | ClassNotFoundException e) {
			try {
				out.close();
				in.close();
				s.close();	
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			
			//e.printStackTrace();
			
		} catch (Exception e) {
			try {
				out.close();
				in.close();
				s.close();	
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			
			//e.printStackTrace();
		}
	}

	
	
	int[] bit_Msg(String msg){
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
	}
	
	
	String DecodeTheMessage (BufferedImage yImage) throws Exception{

		int j=0;
		int currentBitEntry=0;
		String bx_msg="";
		String b_msg="";
		int len = 0;
		
		for (int x = 0; x < yImage.getWidth(); x++){
		for ( int y = 0; y < yImage.getHeight(); y++){
		if(x==0&&y<8){
			//System.out.println("enc "+yImage.getRGB(x, y)+" dec "+yImage.getRGB(x, y)+" "+b_msg);
			int currentPixel = yImage.getRGB(x, y);	
			int red = currentPixel>>16;
			red = red & 255;
			int green = currentPixel>>8;
			green = green & 255;
			int blue = currentPixel;
			blue = blue & 255;
			String x_s=Integer.toBinaryString(blue);
			bx_msg+=x_s.charAt(x_s.length()-1);
			len=Integer.parseInt(bx_msg,2);
			
		}
		else if(currentBitEntry<len*8){
		//System.out.println("enc "+yImage.getRGB(x, y)+" dec "+yImage.getRGB(x, y)+" "+b_msg);
			int currentPixel = yImage.getRGB(x, y);	
			int red = currentPixel>>16;
			red = red & 255;
			int green = currentPixel>>8;
			green = green & 255;
			int blue = currentPixel;
			blue = blue & 255;
			String x_s=Integer.toBinaryString(blue);
			b_msg+=x_s.charAt(x_s.length()-1);

			
			currentBitEntry++;	
			//System.out.println("curre "+currentBitEntry);
		}
		}
		}
		//System.out.println("bin value of msg hided in img is "+b_msg);
		return b_msg;
		}
}
