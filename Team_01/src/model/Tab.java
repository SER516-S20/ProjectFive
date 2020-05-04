package model;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.swing.JButton;

import controller.WorkspaceController;
import view.Icons;
import view.Workspace;

/**
 * This is a model class having all the shareable items of the tab
 * 
 * @author Rishika Bera
 * @version 1.0
 */


public class Tab extends Observable implements Serializable {
	private static final long serialVersionUID = 1L;
	private String selectedOption = "";
	private ArrayList<Icons> iconList;
	private Point point, originPoint, destPoint;
	private List<Icons> icons;
	private Workspace workspace;
	private WorkspaceController workspaceController;
	private boolean isFirstDotClicked = false, isMoving = false;
	private JButton originDot, destDot;
	private boolean isOriginInput, isDestInput;
	private List<Connections> connectionList;
	private boolean openBracketAdded = false, closeBracketAdded = false;

	public Tab(Workspace workspace) {
		iconList = new ArrayList<Icons>();
		connectionList = new ArrayList<Connections>();
		this.workspace = workspace;
		icons = new ArrayList<Icons>();
		for (int i=0;i<3;i++) {
			icons.add(null);
		}
	}

	public String getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}

	public void addIcon(Icons icon) {
		iconList.add(icon);
	}

	public ArrayList<Icons> getIconList() {
		return iconList;
	}

	public void setIconList(ArrayList<Icons> iconList) {
		this.iconList = iconList;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point, String operation) {
		this.point = point;
		this.notifyMethod(operation);
	}

	public Icons getSelectedIcon() {
		return icons.get(0);
	}

	public void setSelectedIcon(Icons selectedIcon) {
		this.icons.set(0, selectedIcon);
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	public boolean isFirstDotClicked() {
		return isFirstDotClicked;
	}

	public void setFirstDotClicked(boolean isFirstDotClicked) {
		this.isFirstDotClicked = isFirstDotClicked;
	}

	public Icons getOriginIcon() {
		return icons.get(1);
	}

	public void setOriginIcon(Icons originIcon) {
		this.icons.set(1, originIcon);
	}

	public Icons getDestIcon() {
		return icons.get(2);
	}

	public void setDestIcon(Icons destIcon) {
		this.icons.set(2, destIcon);
	}

	public List<Connections> getConnectionList() {
		return connectionList;
	}

	public void setConnectionList(List<Connections> connectionList) {
		this.connectionList = connectionList;
	}

	public Point getOriginPoint() {
		return originPoint;
	}

	public void setOriginPoint(Point originPoint) {
		this.originPoint = originPoint;
	}

	public Point getDestPoint() {
		return destPoint;
	}

	public void setDestPoint(Point destPoint, String operation) {
		this.destPoint = destPoint;
		this.notifyMethod(operation);
	}

	public JButton getOriginDot() {
		return originDot;
	}

	public void setOriginDot(JButton originDot) {
		this.originDot = originDot;
	}

	public JButton getDestDot() {
		return destDot;
	}

	public void setDestDot(JButton destDot) {
		this.destDot = destDot;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}


	public boolean isDestInput() {
		return isDestInput;
	}

	public void setDestInput(boolean isDestInput) {
		this.isDestInput = isDestInput;
	}

	public boolean isOriginInput() {
		return isOriginInput;
	}

	public void setOriginInput(boolean isOriginInput) {
		this.isOriginInput = isOriginInput;
	}
	
	public boolean isOpenBracketAdded() {
		return openBracketAdded;
	}

	public void setOpenBracketAdded(boolean openBracketAdded) {
		this.openBracketAdded = openBracketAdded;
	}

	public boolean isCloseBracketAdded() {
		return closeBracketAdded;
	}

	public void setCloseBracketAdded(boolean closeBracketAdded) {
		this.closeBracketAdded = closeBracketAdded;
	}
	
	public WorkspaceController getWorkspaceController() {
		return workspaceController;
	}

	public void setWorkspaceController(WorkspaceController workspaceController) {
		this.workspaceController = workspaceController;
	}

	public void notifyMethod(String operation) {
		setChanged();
		notifyObservers(operation);
	}
}
