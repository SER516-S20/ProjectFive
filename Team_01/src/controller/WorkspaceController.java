package controller;

import java.awt.Component;
import java.awt.Point;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTabbedPane;

import model.Connections;
import model.Tab;
import model.TabList;
import view.CloseBracket;
import view.DoubleBar;
import view.Icons;
import view.OpenBracket;
import view.Workspace;

/**
 * This is the Controller class for workspace. This class observes changes in
 * Model class Tab and performs the required actions.
 * 
 * @author Parikshith Kedilaya Mallar
 * @version 4.0
 *
 */
public class WorkspaceController implements Observer {

	private IconFactory iconFactory;
	private JTabbedPane tabbedPane;

	public WorkspaceController() {
		iconFactory = new IconFactory();
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	/**
	 * Create Workspace when a new tab is added on Pound
	 */
	private void createWorkspace() {
		TabList tabList = TabList.getInstance();
		Workspace workspace = new Workspace();
		tabList.addTab(workspace);
		WorkspaceController workspaceController = new WorkspaceController();
		workspaceController.setTabbedPane(tabbedPane);
		tabList.getRecentTab().setWorkspaceController(workspaceController);
		tabList.getRecentTab().addObserver(workspaceController);
		tabbedPane.add("Tab " + tabList.getSize(), workspace);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o.getClass().getName().equals("view.Pound")) {
			String[] args = (String[]) arg;
			tabbedPane.setTitleAt(Integer.parseInt(args[0]), args[1]);
		} else {
			if (arg == "Clicked") {
				newShape();
			} else if (arg == "Pressed") {
				setSelectedIcon();
			} else if (arg == "Dragged") {
				iconDragged();
			} else if (arg == "Drawline") {
				drawLine(true);
			} else if (arg == "DrawTempLine") {
				drawLine(false);
			} else if (arg == "DoubleClicked") {
				doubleClick();
			} else if (arg == "RightClick") {
				rightClick();
			} else if (arg == "DotClicked") {
				deleteConnection();
			} else if (arg == "BarClicked") {
				deleteAllConnections();
			}
		}
		repaint();
	}

	private void rightClick() {
		deleteIcons();
	}

	/**
	 * On right click Delete an Icon after User selects Yes on prompt
	 */
	private void deleteIcons() {
		Tab tab = TabList.getInstance().getTab();
		Icons icon = searchIcons(tab);
		if (icon != null && tab.getWorkspace().prompt("Delete Node?")) {
			tab.getIconList().remove(icon);
			icon.removeDots();
			removeConnections(icon);
			checkSingleInstanceIcons(tab, icon);
		}
	}

	/**
	 * On right click Delete a connection after User selects Yes on prompt
	 */
	private void deleteConnection() {
		Tab tab = TabList.getInstance().getTab();
		ListIterator<Connections> iterator = tab.getConnectionList().listIterator();
		while (iterator.hasNext()) {
			Connections conn = iterator.next();
			if ((conn.getDestPoint().distance(tab.getPoint()) <= 10
					|| conn.getOriginPoint().distance(tab.getPoint()) <= 10)
					&& tab.getWorkspace().prompt("Delete Connection?")) {
				enableButtons(conn.getDestPoint());
				enableButtons(conn.getOriginPoint());
				iterator.remove();
			}
		}
	}

	/**
	 * On right click Delete all connections on that bar after User selects Yes on
	 * prompt
	 */
	private void deleteAllConnections() {
		Tab tab = TabList.getInstance().getTab();
		ListIterator<Connections> iterator = tab.getConnectionList().listIterator();
		if (tab.getWorkspace().prompt("Delete All Connections?")) {
			while (iterator.hasNext()) {
				Connections conn = iterator.next();
				if ((conn.getDestPoint().distance(tab.getPoint()) <= 30
						|| conn.getOriginPoint().distance(tab.getPoint()) <= 30)) {
					enableButtons(conn.getDestPoint());
					enableButtons(conn.getOriginPoint());
					iterator.remove();
				}
			}
		}
	}

	/**
	 * Check if Open Bracket or Close Bracket is deleted and enable the dots
	 * 
	 * @param tab  - Current tab properties
	 * @param icon - current icon to be deleted
	 */
	private void checkSingleInstanceIcons(Tab tab, Icons icon) {
		if (icon instanceof OpenBracket) {
			tab.setOpenBracketAdded(false);
		} else if (icon instanceof CloseBracket) {
			tab.setCloseBracketAdded(false);
		}
	}

	/**
	 * Remove connections associated with the removed icon
	 * 
	 * @param removedIcon - the icon user selected to be deleted
	 */
	private void removeConnections(Icons removedIcon) {
		Iterator<Connections> connectionList = TabList.getInstance().getTab().getConnectionList().iterator();
		while (connectionList.hasNext()) {
			Connections connection = connectionList.next();
			if (connection.getOriginIcon() == removedIcon) {
				enableButtons(connection.getDestPoint());
				connectionList.remove();
			} else if (connection.getDestIcon() == removedIcon) {
				enableButtons(connection.getOriginPoint());
				connectionList.remove();
			}
		}
	}

	/**
	 * Enable dots for open bracket/ close bracket when user deletes the connection
	 * 
	 * @param connection
	 */
	private void enableButtons(Point connection) {
		Component[] componentAt = TabList.getInstance().getTab().getWorkspace().getComponents();
		for (Component c : componentAt) {
			if (c.getX() == connection.getX() && c.getY() == connection.getY()) {
				c.setEnabled(true);
			}
		}
	}

	/**
	 * This searches for the presence of the shape in the clicked point and sets the
	 * icon bounded by that region. This method is executed when the mouse is
	 * pressed on workspace.
	 */
	private void setSelectedIcon() {
		Tab tab = TabList.getInstance().getTab();
		Icons icon = searchIcons(tab);
		if (icon != null) {
			tab.setSelectedIcon(icon);
		}
	}

	/**
	 * This method calls the paintComponent() method of the workspace and redraws
	 * all the icons in the current workspace
	 */
	private void repaint() {
		TabList.getInstance().getTab().getWorkspace().repaint();
	}

	/**
	 * This method sets the new co-ordinates for the icons on drag. This method is
	 * called on mouse drag in the workspace.
	 */
	private void iconDragged() {
		Tab tab = TabList.getInstance().getTab();
		Icons selected = tab.getSelectedIcon();
		if (selected != null) {
			selected.setLocation(tab.getPoint());
		}
	}

	/**
	 * This method creates a new shape on click in the workspace. This method is
	 * called on mouse click in the workspace
	 */
	private void newShape() {
		Tab tab = TabList.getInstance().getTab();
		if (searchIcons(tab) != null) {
			return;
		}
		if (!tab.isMoving()) {
			Icons drawnIcon = iconFactory.getIconObject(tab.getPoint(), tab.getSelectedOption());
			if (drawnIcon != null) {
				drawnIcon.drawShape(tab.getWorkspace().getGraphics());
				tab.addIcon(drawnIcon);
				if (drawnIcon.getClass().getName().equals("view.Pound")) {
					createWorkspace();
					drawnIcon.addObserver(TabList.getInstance().getRecentTab().getWorkspaceController());
				}
			}
		} else {
			tab.setFirstDotClicked(false);
			tab.setMoving(false);
			tab.getWorkspace().setDefaultCursor();
		}

	}

	/**
	 * This method creates a line between the clicked dot and current position of
	 * mouse. This method calls setLine() that creates a line between two dots when
	 * the @param value is true.
	 * 
	 * @param isFinalLine - boolean indicating whether second dot is clicked or not
	 */
	private void drawLine(boolean isFinalLine) {
		Tab tab = TabList.getInstance().getTab();
		Connections connection = new Connections();
		connection.setOriginIcon(tab.getOriginIcon());
		connection.setDestIcon(tab.getDestIcon());
		connection.setOriginPoint(tab.getOriginPoint());
		connection.setDestPoint(tab.getDestPoint());
		if (isFinalLine) {
			setLine(tab, connection);
			tab.setMoving(false);
			tab.getWorkspace().setDefaultCursor();
		} else {
			tab.setMoving(true);
			tab.getWorkspace().setCrossHairCursor();
		}
	}

	/**
	 * This method draws a line between two dots. Called from drawLine() when
	 * isFinalLine param is true.
	 * 
	 * @param tab
	 * @param connection
	 */
	private void setLine(Tab tab, Connections connection) {
		if ((tab.isOriginInput() && !tab.isDestInput()) || (!tab.isOriginInput() && tab.isDestInput())) {
			tab.getConnectionList().add(connection);
			tab.setFirstDotClicked(false);
			if (!(tab.getOriginIcon() instanceof DoubleBar)) {
				tab.getOriginDot().setEnabled(false);
			}
			if (!(tab.getDestIcon() instanceof DoubleBar)) {
				tab.getDestDot().setEnabled(false);
			}
		}
	}

	/**
	 * This method takes input from user and stores it in instance of respective
	 * icon This method is called on mouse double click.
	 */
	private void doubleClick() {
		Tab tab = TabList.getInstance().getTab();
		Icons icon = searchIcons(tab);
		if (icon != null) {
			String description = tab.getWorkspace().getInputString(icon.getDescription());
			icon.setDescription(description);
		}
	}

	/**
	 * Searches the icons list and returns the object of the clicked icon, else
	 * return null
	 * 
	 * @param tab - Tab instance
	 * @return
	 */
	private Icons searchIcons(Tab tab) {
		ListIterator<Icons> listIterator = tab.getIconList().listIterator();
		while (listIterator.hasNext()) {
			Icons icon = listIterator.next();
			if (icon.containsIcon(tab.getPoint())) {
				return icon;
			}
		}
		return null;
	}

}