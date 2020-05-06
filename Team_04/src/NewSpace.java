import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTabbedPane;
/**
 * 
 * @author Rohith Varma Gaddam
 * @since  03-14-2020
 * @updated on 04-2-2020
 *
 */
public class NewSpace implements ActionListener {
	private Frame frame;
	static private int newCount = 0;
	public NewSpace(Frame frame) {
		this.frame = frame;
	}

	@Override
	
	public void actionPerformed(ActionEvent e) {
		Container content = frame.getContentPane();
		content.removeAll();
		content.add(new OptionsPanel(), BorderLayout.NORTH);
		content.add(new LeftPanel(), BorderLayout.WEST);
		JTabbedPane pane = new JTabbedPane();
		frame.setTabbedPane(pane);
		content.add(pane, BorderLayout.CENTER);
		Frame.canvasArray = new ArrayList<DrawingCanvas>();
		DrawingCanvas canvas = new DrawingCanvas();
		pane.add(canvas);
		Frame.canvasArray.add(canvas);
		StoreClickPoints.numPoints=0;
	}

	public static void addActionListener(NewSpace newspace) {
		// TODO Auto-generated method stub
	}
}