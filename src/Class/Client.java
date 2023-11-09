package Class;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Vector;

import constant.Constant;

public class Client {
	DatagramSocket clientSocket;
	byte[] sendData = new byte[1024];
	byte[] receiveData = new byte[1024];
	InetAddress IPAddress ; 
	int port_client ; 
	int PORT = new Constant().PORT_SERVER; 
	String nameSend  ; 
	DatagramPacket receivePacket ;
	public Client(String nameSend) {
		this.nameSend = nameSend ; 
//		try {
//			int  p = Integer.parseInt(new HandleFile().readFile("server_DB\\"+ nameSend+"\\infor.txt"));
//			clientSocket = new DatagramSocket(p);
//			IPAddress = InetAddress.getByName(new Constant().IPAdrress);
//			receivePacket = new DatagramPacket(receiveData, receiveData.length);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}	
	public Client() {
		try {
			clientSocket = new DatagramSocket(8442);
			receivePacket = new DatagramPacket(receiveData, receiveData.length);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendMess(Packet packet) {
		try {
			sendData = serialize((Object)packet);
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,InetAddress.getByName(new Constant().IPAdrress), PORT);
			
			clientSocket.send(sendPacket);
			Packet packet1 = (Packet) deserialize(sendData); 
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public Packet receiveMess() {
		
		try {
		
			clientSocket.receive(receivePacket);
			Object obj_receive = deserialize(receivePacket.getData()) ; 
			Packet packet_receive = (Packet) obj_receive ; 
			
			return  packet_receive; 
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//nhan dl tu server
		return null ; 
	}
	public void closeClient() {
		clientSocket.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Packet packet = new Packet( new Constant().DEFINE_REQUIRE_SENDMAIL,"phan" ,"duc",  "phjfl", "jlskadjf", "Jlkjsf", "kgd", "jl"); 
//				
//		Client client = new Client("phan");
//		client.sendMess(packet);
		

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


}
