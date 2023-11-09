package Class;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

import Gui.GuiPacket;


public class CustomCell implements ListCellRenderer{
	boolean ok = true ;
	int index1 = -1; 
	@Override
	public JPanel getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,boolean cellHasFocus) {
		// TODO Auto-generated method stub
		JPanel panel = new GuiPacket((Packet)value);

		return panel;

	}
	
	
	
}



