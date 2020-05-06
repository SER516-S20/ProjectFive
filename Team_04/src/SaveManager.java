import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
/**
 * 
 * @author Ashwath Reddy Koppala
 * @since  02-21-2020
 *
 */
public class SaveManager implements ActionListener {
	private String fileName;
	//private JTabbedPane pane;
	private Frame frame;

	public SaveManager(Frame frame) {
		//this.pane = pane;
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		JTabbedPane pane = frame.getTabbedPane();
		int index = pane.getSelectedIndex();
		ArrayList<DrawingCanvas> canvasArray = frame.getCanvasArray();
		DrawingCanvas	canvas = canvasArray.get(index);
		FileOutputStream fileOutStream = null;
		ObjectOutputStream objectOutStream = null;
		Component[] tabs = Frame.tabbedPane.getComponents();
		try {
			JFileChooser chosenFile = new JFileChooser();
			int showSaveDialog = chosenFile.showSaveDialog(null);
			if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
				fileName = chosenFile.getSelectedFile().getAbsolutePath().toString() + ".ser";
			}
			fileOutStream = new FileOutputStream(new File(fileName));
			objectOutStream = new ObjectOutputStream(fileOutStream);
			objectOutStream.writeObject(tabs);
			fileOutStream.flush();
			if (objectOutStream != null) {
				objectOutStream.close();
			}
			if (fileOutStream != null) {
				fileOutStream.close();
			}
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}