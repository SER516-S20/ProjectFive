import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.util.ArrayList;


/**
 * This class consists of adding load file
 * functionality to load the saved file into the application.
 * 
 * @author Kartik Mathpal
 * @version 1.0
 */
public class LoadDocument extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;

	public LoadDocument(String label) {
		super(label);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		try {
			JFileChooser chosenFile = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".ser", "ser");
			chosenFile.setFileFilter(filter);
			int showOpenDialog = chosenFile.showOpenDialog(null);
			
			if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
				String fileName = chosenFile.getSelectedFile().getAbsolutePath();
				fileIn = new FileInputStream(fileName);
				in = new ObjectInputStream(fileIn);
				RightPanel.setLines((ArrayList<Connections>) in.readObject());
				RightPanel.setRightPanelShapes((ArrayList<Shapes>) in.readObject());
				Frame.rightPanel.repaint();
			}
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException ignored) {

		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fileIn != null) {
				try {
					fileIn.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
