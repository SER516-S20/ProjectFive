package listener;

import frame.Frame;
import frame.Main;
import shape.HashBlock;
import shape.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * ActionListenerEnter.java - A class invoked when an action occurs.
 *
 * @author Ashutosh Dey
 * @version 1.0
 * @param
 */
public class ActionListenerEnter implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Shape shape = MouseListener.selectedShape;
		String message = InputDialog.dialogText.getText();
		shape.setMessage( message);
		if (shape instanceof HashBlock) {
			Main.frame.getTabbedPane().setTitleAt(Frame.currentTab+1, message);
		}
	}
}
