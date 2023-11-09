package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Class.Packet;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JLabel;
import java.awt.Color;

public class GuiContent extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiContent frame = new GuiContent(new Packet("phan","",  "phjfl", "jlskadjf", "Mail", null, null));
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
	public GuiContent(Packet packet) {
		super(packet.getContent()); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(3,2));
		
		JLabel lblUserName = new JLabel("User:");
		panel.add(lblUserName);
		
		JLabel lblNewLabel_1 = new JLabel(packet.getName_send());
		panel.add(lblNewLabel_1);
		
		JLabel lblTo = new JLabel("To");
		panel.add(lblTo);
		
		JLabel lblNewLabel = new JLabel(packet.getName_recerive());
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("date");
		panel.add(lblNewLabel_3);
		
		JLabel lblDate = new JLabel(packet.getDate());
		panel.add(lblDate);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel(packet.getTitle());
		
		lblNewLabel_2.setBackground(Color.WHITE);
		panel_1.add(lblNewLabel_2);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				System.exit(0);
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
