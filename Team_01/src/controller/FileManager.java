package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Connections;
import model.Tab;
import model.TabList;
import view.Dot;
import view.Icons;
import view.Pound;
import view.VerticalBar;
import view.Workspace;

/**
 * This class provides functionality to save and load workspace.
 * 
 * @author Raghavan
 * @version 3.0
 */
public class FileManager {
	private final String FILE_EXT = ".ser";
	private final String SUCCESS_MESSAGE = "File Saved!";
	private final String ERROR_MESSAGE_SAVE = "Error occured! Could Not Save your file";
	private final String ERROR_MESSAGE_LOAD = "Could not load the file. Please select only .ser files!";

	/**
	 * Function to save the workspace to a file.
	 */
	public void saveFile() {
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		String fileName = null;
		try {
			JFileChooser chosenFile = new JFileChooser();
			int showSaveDialog = chosenFile.showSaveDialog(null);
			if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
				fileName = chosenFile.getSelectedFile().getAbsolutePath().toString() + FILE_EXT;
			}
			if (fileName != null) {
				fileOut = new FileOutputStream(new File(fileName));
				out = new ObjectOutputStream(fileOut);
				List<Tab> tabList = TabList.getInstance().getTabList();
				out.writeInt(tabList.size());
				for (Tab tab : tabList) {
					out.writeObject(tab.getWorkspace());
					out.writeObject(tab.getIconList());
					out.writeObject(tab.getConnectionList());
				}
				fileOut.flush();
				JOptionPane.showMessageDialog(null, SUCCESS_MESSAGE);
			}
		} catch (Exception i) {
			JOptionPane.showMessageDialog(null, ERROR_MESSAGE_SAVE);
			i.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * Function to load workspace from a file.
	 */
	public void loadFile(JTabbedPane jTabbedPane) {
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		String fileName = null;
		try {
			JFileChooser chosenFile = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(FILE_EXT, FILE_EXT.replace(".", ""));
			chosenFile.setFileFilter(filter);
			int showOpenDialog = chosenFile.showOpenDialog(null);
			if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
				fileName = chosenFile.getSelectedFile().getAbsolutePath();
				fileIn = new FileInputStream(fileName);
				in = new ObjectInputStream(fileIn);
				int numberOfTabs = in.readInt();
				TabList tabList = TabList.getInstance();
				int currentTabIndex = tabList.getCurrentTabIndex();
				for (int i = 0; i < numberOfTabs; i++) {
					Workspace workspace = (Workspace) in.readObject();
					jTabbedPane.add("Tab " + (tabList.getSize() + 1), workspace);
					tabList.setCurrentTabIndex(tabList.getSize());
					tabList.addTab(workspace);
					tabList.getTab().setWorkspace(workspace);
					WorkspaceController workspaceController = new WorkspaceController();
					workspaceController.setTabbedPane(jTabbedPane);
					tabList.getTab().addObserver(workspaceController);
					tabList.getTab().setIconList((ArrayList<Icons>) in.readObject());
					tabList.getTab().setConnectionList((List<Connections>) in.readObject());
					addActionListeners(tabList, workspaceController);
				}
				tabList.setCurrentTabIndex(currentTabIndex);
			}
		} catch (IOException i) {
			JOptionPane.showMessageDialog(null, ERROR_MESSAGE_LOAD);
		} catch (Exception e) {
			e.printStackTrace();
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

	/**
	 * Adding action listeners to the dots while loading them back from the file.
	 * 
	 * @param tabList
	 * @param workspaceController
	 */
	private void addActionListeners(TabList tabList, WorkspaceController workspaceController) {
		for (Icons icon : tabList.getTab().getIconList()) {
			if(icon instanceof Pound) {
				((Pound) icon).setTabIndex(TabList.getInstance().getSize());
				icon.addObserver(workspaceController);
			}
			if (icon.getDots() != null) {
				for (Dot dot : icon.getDots()) {
					dot.addActionListener(icon);
				}
			}
			if (icon.getBars() != null) {
				for (VerticalBar bar : icon.getBars()) {
					bar.addActionListener(icon);
				}
			}
		}
	}
}
