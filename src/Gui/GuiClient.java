package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ScrollPaneUI;

import Class.Client;
import Class.CustomCell;
import Class.HandleFile;
import Class.Packet;
import constant.Constant;


import javax.swing.JList;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;

public class GuiClient extends JFrame implements ActionListener, FocusListener {

	DatagramSocket clientSocket ; 
	byte[] byte_recerive = new byte[1024]; 
	byte[] byte_send = new byte[1024]; 
	DatagramPacket datagramPacket_receive ; 
	Constant constant = new Constant();
	DefaultListModel<Packet> listModel = new DefaultListModel<>();
	JList<Packet> listnhan = new JList<>(listModel);
	JList<Packet> listgui = new JList<>(listModel);
	private JPanel contentPane;
	private Vector<Packet> vectorNhan = new Vector<Packet>();
	private Vector<Packet> vectorGui = new Vector<Packet>();
	JScrollPane scroll1;
	JScrollPane scroll2;
	private Vector<Packet> listData_Send = new Vector<Packet>();
	private JTextField txtAdressSend;
	private JTextField txt_Subject;
	private JButton btnSendMail, btnRefesh, btnMessSend ; 
	private JTextArea text_Content ;
	Client client  = null; 
	String nameSend = ""; 
	String mesent = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiClient frame = new GuiClient("van");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuiClient(String nameSend) {
		this.nameSend = nameSend ; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 842, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		
		try {// set icon giao dien---------------------------
			Image iconmes = ImageIO.read(new File( new Constant().LINK_PATH_IMAGE + "logoMail.jpg"));
			this.setIconImage(iconmes); 
		} catch (IOException e1) {
			// TODO Auto-generated catch block
		
		}
		
		 listgui = new JList(vectorGui);

		listgui.updateUI();
		listgui.setCellRenderer(new CustomCell());
		
		listnhan = new JList(vectorNhan);
		
		listnhan.updateUI();
		listnhan.setCellRenderer(new CustomCell());
		
		
		
		scroll1  = new JScrollPane(listnhan);
	
		scroll1.setPreferredSize(getPreferredSize());
		scroll1.createVerticalScrollBar();
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll1, BorderLayout.CENTER);
		scroll2  = new JScrollPane(listgui);
		
		scroll2.setPreferredSize(getPreferredSize());
		scroll2.createVerticalScrollBar();
		scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll2, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new GridLayout(8, 1));
		
		 btnRefesh = new JButton("Refesh");
		btnRefesh.addActionListener(this);
		panel_1.add(btnRefesh);
		
		btnMessSend = new JButton("mess send");
		btnMessSend.addActionListener(this);
		panel_1.add(btnMessSend);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		
		JLabel lbImageUser = new JLabel("");
		try {
			BufferedImage bufferImage_hidden = ImageIO.read(new File(new Constant().LINK_PATH_IMAGE+ "logoUser.png"));
			ImageIcon imageIcon_hidden = new ImageIcon(bufferImage_hidden.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			lbImageUser.setIcon(imageIcon_hidden);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		panel_2.add(lbImageUser);

		JLabel lblUser = new JLabel("Phan van");
		lblUser.setText(this.nameSend);
		panel_2.add(lblUser);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("New Messeage");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(5,1));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new GridLayout(2,1));
		
		txtAdressSend = new JTextField("To");
		txtAdressSend.addFocusListener(this);
		txtAdressSend.setForeground(Color.gray);
		panel_4.add(txtAdressSend);
		txtAdressSend.setColumns(10);
		
		txt_Subject = new JTextField("Subject");
		txt_Subject.addFocusListener(this);
		txt_Subject.setForeground(Color.gray);
		panel_4.add(txt_Subject);
		txt_Subject.setColumns(10);
		
		text_Content = new JTextArea("Content");
		text_Content.setForeground(Color.gray);
		text_Content.addFocusListener(this);
		panel_3.add(text_Content);
		
		btnSendMail = new JButton("Send");
		panel.add(btnSendMail, BorderLayout.SOUTH);
		btnSendMail.addActionListener(this);
		client = new Client() ;
		InitGui();
		new Thread(new Runnable() {
		    @Override
		    public void run() {
		        while (true) {
		            Packet packet = client.receiveMess(); 
		            System.out.println("Client dang xu li nhan data tu server");
		           
		            if (packet == null) {
		                System.out.println("kh nhan ");
		                // break; // Dừng vòng lặp khi packet là null
		            }

		            if (packet.getMessSent().contains("sendMail.txt")) {
		            	System.out.println("thu da gui :");
		            	  vectorGui.addElement(packet);
		            } else {
		            	System.out.println(" thu nhan");
		                vectorNhan.addElement(packet);
		            }
		           // Thêm dữ liệu vào listModel
		            
		            // Cập nhật giao diện hiển thị
		            SwingUtilities.invokeLater(new Runnable() {
		                @Override
		                public void run() {
		                    listgui.updateUI();
		                    listnhan.updateUI();
		                }
		            });
		        }
		    }
		}).start();
//		new Thread(new Runnable() {
//			   
//			@Override
//			public void run() {
//			    while (true) {
//			        Packet packet = client.receiveMess(); 
//			        if (packet == null) {
//			        	System.out.println("kh nhan ");
////			            break; // Dừng vòng lặp khi packet là null
//			        }
//			        
//			        listData.add(packet);
//			        list.updateUI(); 
//
//			    }
//			}
//		}).start();
		
			
		
//		list2.addMouseListener(new MouseListener() {
//			
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//				JList theList = (JList) e.getSource(); 
//
//				int index = theList.locationToIndex(e.getPoint()); 
//				Packet packetSelect = (Packet)theList.getModel().getElementAt(index);
//				GuiContent content  = new  GuiContent(packetSelect);
//				content.setVisible(true);
//			}
//		});
//		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if( e.getSource().equals(btnSendMail)) {
			Packet packet_send = new Packet(
										new Constant().DEFINE_REQUIRE_SENDMAIL, 
										this.nameSend, txtAdressSend.getText(), 
										txt_Subject.getText(),getDateTime(), 
										text_Content.getText(), null , null
									); 
			
			client.sendMess(packet_send);

			
		}
		if(e.getSource().equals(btnRefesh)) {
			 contentPane.remove(scroll2);
		        contentPane.add(scroll1, BorderLayout.CENTER);
		        contentPane.revalidate();
		        contentPane.repaint();
			InitGui();
		}
		if(e.getSource().equals(btnMessSend)) {
			 contentPane.remove(scroll1);
		        contentPane.add(scroll2, BorderLayout.CENTER);
		        contentPane.revalidate();
		        contentPane.repaint();
			getMessSend();
		}
	}
	public void InitGui() {
		getIp(new Constant().DEFINE_REQUIRE_GETMESSRe);
		vectorNhan.removeAllElements();

	}
	public void getMessSend() {

		vectorGui.removeAllElements();
		getIp(new Constant().DEFINE_REQUIRE_GETMESS);
		
	
	}
	// get thá»�i gian hiá»‡n táº¡i 
	public String getDateTime() {
		String d = String.valueOf( java.time.LocalDate.now());
		String h = String.valueOf(java.time.LocalTime.now());
		String[] timeArr = h.split("\\.");
		System.out.print(h);
		System.out.println(timeArr.length);
		return d + " " + timeArr[0] ; 
	}
public void getIp(int request) {
	InetAddress inetAddress = null;
	String inetAd;
	NetworkInterface networkInterface = null;
	try {
		Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
		  boolean isFirstAddressPrinted = false; // Biến để theo dõi xem đã in ra địa chỉ đầu tiên chưa
        while (networkInterfaces.hasMoreElements()) {
             networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            
            while (inetAddresses.hasMoreElements()) {
                inetAddress = inetAddresses.nextElement();
                if (inetAddress.getHostAddress().contains(".") && !inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
                	 if (!isFirstAddressPrinted) {
                         System.out.println("IPv4 Address: " + inetAddress.getHostAddress());
                         DatagramSocket clientSocket = new DatagramSocket();
                         String username=this.nameSend;
             			byte[] sendData = new byte[1024];
             			 // Sử dụng InetAddress để lấy địa chỉ IP của máy hiện tại
                       
             			Packet packet = new Packet(request,username,inetAddress.getHostAddress()); 
             		     sendData = serialize((Object) packet); 
             			DatagramPacket sendPacket = 
             			            new DatagramPacket(sendData, sendData.length,InetAddress.getByName(new Constant().IPAdrress) , new Constant().PORT_SERVER);
             			clientSocket.send(sendPacket);
             			
                         isFirstAddressPrinted = true; // Đánh dấu là đã in ra địa chỉ đầu tiên
                         break; // Dừng vòng lặp sau khi in ra địa chỉ đầu tiên
                     }
                    
                }
              
            }
        }
	
} catch (Exception ex) {
	// TODO Auto-generated catch block
	ex.printStackTrace();
}//
}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(txtAdressSend)) {
			if( txtAdressSend.getText().equals("To")) {
				txtAdressSend.setText("");
				txtAdressSend.setForeground(Color.black);
			}
			

			
		}
		if(e.getSource().equals(txt_Subject)) {
			if( txt_Subject.getText().equals("Subject")) {
				txt_Subject.setText("");
				txt_Subject.setForeground(Color.black);
			}
			
		}
		if(e.getSource().equals(text_Content)) {
			if( text_Content.getText().equals("Content")) {
				text_Content.setText("");
				text_Content.setForeground(Color.black);
			}

		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(txtAdressSend)) {
			if( txtAdressSend.getText().equals("")) {
				txtAdressSend.setText("To");
				txtAdressSend.setForeground(Color.gray);
			}	
		}
		if(e.getSource().equals(txt_Subject)) {
			if( txt_Subject.getText().equals("")) {
				txt_Subject.setText("Subject");
				txt_Subject.setForeground(Color.gray);
			}
			
		}
		if(e.getSource().equals(text_Content)) {
			if( text_Content.getText().equals("")) {
				text_Content.setText("Content");
				text_Content.setForeground(Color.gray);
			}

		}
	}
	 public static byte[] serialize(Object obj) throws IOException {
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        ObjectOutputStream os = new ObjectOutputStream(out);
	        os.writeObject(obj);
	        return out.toByteArray();
	    }

}




class ContentCell implements ListCellRenderer{
	boolean ok = true ;
	int index1 = -1; 
	@Override
	public JPanel getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,boolean cellHasFocus) {
		// TODO Auto-generated method stub
		Packet packet = (Packet)value;
		JPanel panel = new JPanel();
		JTextArea text = new JTextArea("xin Ã ljasdlkf"); 
		panel.add(text); 
		return panel;

	}
	
	
	
}




