package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Class.Packet;
import constant.Constant;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JLabel;

public class GuiPacket extends JPanel {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame b = new JFrame();
					GuiPacket JPanel = new GuiPacket(new Packet("phan"));
					
					
					b.getContentPane().add(JPanel);
					b.setVisible(true);
					b.setSize(500,500);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuiPacket(Packet packet) {
	    String packetData = packet.getMessSent();
	    // Phân tách các giá trị bằng dấu "&&&"
	    String[] values = packetData.split("&&&");

	    // Lấy các giá trị từ mảng
	    String type = values[0];
	    String namerecieve = values[1];
	    String content = values[2];
	    String dateTime = values[3];
	    String subject = values[4];
System.out.println(type);
	    setBounds(100, 100, 450, 300);
	    contentPane = new JPanel();
	    contentPane.setLayout(new GridLayout(1, 4));

	    JPanel panel = new JPanel();
	    contentPane.add(panel);
	    panel.setLayout(new BorderLayout(0, 0));

	    JPanel panel_1 = new JPanel();
	    panel_1.setBackground(Color.orange);
	    panel.add(panel_1);
	    panel_1.setLayout(new BorderLayout(0, 0));

	    JLabel lblImage = new JLabel("");
	    try {
	        BufferedImage bufferImage_hidden = ImageIO.read(new File(new Constant().LINK_PATH_IMAGE + "logoUser.png"));
	        ImageIcon imageIcon_hidden = new ImageIcon(bufferImage_hidden.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
	        lblImage.setIcon(imageIcon_hidden);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    panel_1.add(lblImage, BorderLayout.CENTER);

	    JLabel lblName = new JLabel("Phan V\u0102n PH\u00F9ng");
	    lblName.setBackground(Color.white);

	    contentPane.add(lblName);
	    lblName.setText(namerecieve);

	    JLabel lblSubject = new JLabel("");
	    lblSubject.setBackground(Color.white);
	    contentPane.add(lblSubject);
	    lblSubject.setText(subject);

	    JLabel lblTitle = new JLabel("v\u1EAFn b\u1EA3n n\u00E0y d\u00F9ng \u0111\u1EC3 text");
	    lblTitle.setBackground(Color.white);
	    lblTitle.setAlignmentX(5000);
	    contentPane.add(lblTitle);
	    lblTitle.setText(content);

	    JLabel lblDate = new JLabel("12/5/2001");
	    lblDate.setBackground(Color.white);
	    contentPane.add(lblDate);
	    lblDate.setText(dateTime);
	    // Thêm khoảng trống bên phải cho cột đầu tiên
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));
	    this.setLayout(new BorderLayout());
	    this.add(contentPane, BorderLayout.CENTER);
	    this.setVisible(true);
	}
}