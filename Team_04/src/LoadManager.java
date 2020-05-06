import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * 
 * @author Tarun Snehith Kishore Reddy Karna
 * @since 03-16-2020
 * @version 1.0
 *
 */
class LoadManager implements ActionListener {
	private String fileName;
	//private JTabbedPane pane;
	private Frame frame;

	public LoadManager(Frame frame) {
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		FileInputStream fileInStream = null;
		ObjectInputStream objectInStream = null;
		Component[] tabsToOpen;
		try {
			JFileChooser chosenFile = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("SER516", "ser");
			chosenFile.setFileFilter(filter);
			int showOpenDialog = chosenFile.showOpenDialog(null);
			if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
				fileName = chosenFile.getSelectedFile().getAbsolutePath();
				fileInStream = new FileInputStream(fileName);
				objectInStream = new ObjectInputStream(fileInStream);
	            tabsToOpen = (Component[]) objectInStream.readObject();
	            
	    		Container content = frame.getContentPane();
	    		content.removeAll();
	    		content.add(new OptionsPanel(), BorderLayout.NORTH);
	    		content.add(new LeftPanel(), BorderLayout.WEST);
	    		JTabbedPane pane = new JTabbedPane();
	    		frame.setTabbedPane(pane);
	    		content.add(pane, BorderLayout.CENTER);
	    		StoreClickPoints.link1=null;
	    		StoreClickPoints.link2=null;
	    		Frame.canvasArray = new ArrayList<DrawingCanvas>();
	    		int i = 1;
	            for (Component component : tabsToOpen) {
	                DrawingCanvas tab = (DrawingCanvas) component;
	                Frame.tabbedPane.add("Tab"+i , tab);
	                Frame.canvasArray.add(tab);
	                tab.repaint();
	                i++;
	            }
			}
			if (objectInStream != null) {
				objectInStream.close();
			}
			if (fileInStream != null) {
				fileInStream.close();
			}
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException e1) {
		}
	}
}