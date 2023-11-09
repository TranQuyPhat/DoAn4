package Server;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;

import java.net.DatagramSocket;

import java.net.InetAddress;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import Class.HandleFile;
import Class.Packet;
import constant.Constant;

public class Server {
	String path = "D:\\new_email" ; 
	DatagramSocket serverSocket ; 
	int port = new Constant().PORT_SERVER;
	byte[] byte_recerive = new byte[1024]; 
	byte[] byte_send = new byte[1024]; 
	DatagramPacket datagramPacket_receive ; 
	Constant constant = new Constant();
	HandleFile handleFile = new HandleFile();
	public Server() {
        try {
			serverSocket = new DatagramSocket(port); 
			datagramPacket_receive = new DatagramPacket(byte_recerive, byte_recerive.length); 
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       System.out.println("server is running");
       new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
			       receiveMail();

			}
		}
	}).start(); 

      
	}
    public static void main(String args[]) throws Exception {
    	new Server(); 
    	
    }
    

    public void receiveMail() {
    	try {
			serverSocket.receive(datagramPacket_receive);
			Packet packet_receive = (Packet)deserialize(datagramPacket_receive.getData());
			
			int require = packet_receive.getDefine_require(); 
			
			if( require == constant.DEFINE_REQUIRE_LOGIN ) {
				loginServer(packet_receive);
			}
			if( require == constant.DEFINE_REQUIRE_REGISTER) {
				registerServer(packet_receive.getName_send());
			}
		
			if( require == constant.DEFINE_REQUIRE_SENDMAIL) {
				
				int  p = Integer
							.parseInt(new HandleFile()
							.readFile("server_DB\\"+ packet_receive.getName_send()+"\\infor.txt"));
				System.out.println("port cua client" +p);
				receiveAndSendMail(packet_receive, datagramPacket_receive.getAddress(), p);
			}
				if( require == constant.DEFINE_REQUIRE_GETMESS) {
					String name= packet_receive.getName_send();
					String ip= packet_receive.getIP_client();
				getMail(name,ip,8442 ,"sendMail.txt");
				
			}
				if( require == constant.DEFINE_REQUIRE_GETMESSRe) {
					String name= packet_receive.getName_send();
					String ip= packet_receive.getIP_client();
					getMail(name,ip,8442 ,"me.txt");
					
				}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void sendMail(Packet packet, InetAddress inetAddress, int port) {
    	try {
			byte_send = serialize((Object) packet) ;
	       	DatagramPacket sendPacket =   new DatagramPacket(byte_send, byte_send.length, InetAddress.getByName(packet.getIP_client()), port);
	       	
	       	//Gui dl lai cho client
	       	serverSocket.send(sendPacket);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	

    }
    public void getMail(String nameu,String ip,int port,String filename) {
    
	    
		 String path = "server_DB" + File.separator + nameu + File.separator + filename;
		 System.out.println("Path : "+path);

	        try {
	            File file = new File(path);
	            if(file.exists()) {
	            	if(file.length()>0) {
	            		BufferedReader br = new BufferedReader(new FileReader(file)); // Sá»­ dá»¥ng BufferedReader Ä‘á»ƒ Ä‘á»�c tá»‡p

			            String st;
			            while ((st = br.readLine()) != null) { // Ä�á»�c tá»«ng dÃ²ng trong tá»‡p
			               
			                byte[] sendData = new byte[1024];
							String stt= filename+"||"+st;
			  	          System.out.println(stt);
							Packet packet = new Packet(stt); 
							
						     sendData = serialize((Object) packet); 

							DatagramPacket sendPacket = 
							            new DatagramPacket(sendData, sendData.length, InetAddress.getByName(ip) , port);
							serverSocket.send(sendPacket);
	            	}
	            	
	            }
	            
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	   
		
		

		
    }
    
    public void registerServer(String name) {
    	String[] arr = name.split(constant.SPLIT_S);
    //	System.out.print(setPortClient());
    	//System.out.println(arr.length); 
    	if( !handleFile.checkFileExist(constant.LINK_PATH_SERVER +"" + arr[0])) {
    	//	System.out.println("port ahiii"+setPortClient());
    		handleFile.CreateDirectory(constant.LINK_PATH_SERVER, arr[0]);
    		handleFile.createFile(constant.LINK_PATH_SERVER +"" + arr[0], "newEmail.txt");
    		handleFile.writeFile(constant.LINK_PATH_SERVER +"" + arr[0] + "\\newEmail.txt", "Thank you for using this service. we hope that you will feel comfortabl........");
    		handleFile.createFile(constant.LINK_PATH_SERVER +"" + arr[0], "pass.txt");
    		handleFile.writeFile(constant.LINK_PATH_SERVER +"" + arr[0] + "\\pass.txt", arr[1]);
    		handleFile.createFile(constant.LINK_PATH_SERVER +"" + arr[0], "infor.txt");
    		handleFile.writeFile(constant.LINK_PATH_SERVER +"" + arr[0] + "\\infor.txt", setPortClient()+"");
    		handleFile.createFile(constant.LINK_PATH_SERVER +"" + arr[0], "me.txt");
    		handleFile.writeFile(constant.LINK_PATH_SERVER + "portUsed.txt", constant.SPLIT_S + setPortClient() );


    	}
    	
    }
    public void loginServer(Packet packet) {
    	String[] arr = packet.getName_send().split(constant.SPLIT_S);
    	String ip= packet.getIP_client();
    	System.out.println(ip);
    	if( handleFile.checkFileExist(constant.LINK_PATH_SERVER +"" + arr[0])) {
    		if( arr[1].equals(handleFile.readFile(constant.LINK_PATH_SERVER +"" + arr[0] + "\\pass.txt"))) {
    			System.out.println("login success");
    			
  
    			// Viet du lieu tra ve client :>
    			
    		}else {
    			System.out.println("login fail");
    		}
    	}else {
    		System.out.println("User or password incorrect! :(");
    	}
    }

    
    public void receiveAndSendMail(Packet packet, InetAddress inetAddress, int port) {
    	System.out.println("Nhan mail server");

    	if( !handleFile.checkFileExist( constant.LINK_PATH_SERVER + "" + packet.getName_recerive()) ) {
    		System.out.println("Khong ton tai tai khoan nay : " + constant.LINK_PATH_SERVER + "" + packet.getName_recerive());
    	}else {
    		String content = packet.getName_send()+ constant.SPLIT_S + packet.getName_recerive() + constant.SPLIT_S
    				+ packet.getContent()+ constant.SPLIT_S + packet.getDate() + constant.SPLIT_S + packet.getTitle() ; 
    		handleFile.createAndWriteFile(constant.LINK_PATH_SERVER + packet.getName_recerive() + "\\", "me", content+  "\r\n");
    		handleFile.createAndWriteFile(constant.LINK_PATH_SERVER + packet.getName_send() + "\\", "sendMail", content+  "\r\n");
    		sendMail(packet, inetAddress, port);
    	}
    }
    
    
    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }
    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
    public int setPortClient() {
    	int port = 0; 
    	String[] s = handleFile.readFile(constant.LINK_PATH_SERVER + "portUsed.txt").split(constant.SPLIT_S) ; 
    	//if( s.length > 1) {
        	do {
        		port = (int) (Math.random() * ((9999 - 7777) + 1) + 7777); 
        	//	System.out.print(port);
        		for (String ss : s) {	
        			System.out.print(ss+"-");
    				if( Integer.parseInt(ss) == port ) port = 0 ; 
        		}
        	}while(port == 0) ; 
//    	}else {
//    		port = (int) (Math.random() * ((9999 - 8888) + 1) + 8888); 
//    	}

    	return port ; 
    }
}