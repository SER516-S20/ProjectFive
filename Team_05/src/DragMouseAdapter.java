import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.TransferHandler;

public class DragMouseAdapter extends MouseAdapter {
	public void mousePressed(MouseEvent e) {
		JButton button = (JButton)e.getSource();
		TransferHandler handler = button.getTransferHandler();
		handler.exportAsDrag(button, e, TransferHandler.COPY);
		System.out.println(button.getActionCommand());
	}
	public void mouseDragged(MouseEvent e) {
		
	}
	public void mouseReleased(MouseEvent e) {
		JButton btn = (JButton)e.getSource();
		String cmd = btn.getActionCommand();
		
	}
}
