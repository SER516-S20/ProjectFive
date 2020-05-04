package listener;

import frame.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * DeleteActionListener.java - a class to trigger delete links/nodes after
 * confirmation from the user from input dialog box
 * 
 * @author Ashutosh Dey
 * @version 1.0
 *
 */
public class DeleteActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		new Delete().performDelete();
		Frame.mapDrawingAreas.get(Frame.currentTab).repaintOnDrag();
	}

}
