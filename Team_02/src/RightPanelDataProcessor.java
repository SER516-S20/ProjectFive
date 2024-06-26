import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.HashMap;

/**
 * @author abhinaw sarang
 * @modified on 03-16-2020
 * @version 1.0
 * @author Suryadeep
 * @modified on 03-18-2020
 * @version 2.0
 */
public class RightPanelDataProcessor extends Observable {

	private HashMap<String, List<Icon>> iconMap;
	private List<Dot> dotList;
	private List<Line> lineList;
	private List<Dot> barCenterList;
	private boolean lineStartPointSelected = false;
	private Dot lineStartPoint;
	public List<Observer> observers = new ArrayList<Observer>();

	public RightPanelDataProcessor(RightPanel rp) {
		this.iconMap = new HashMap<String, List<Icon>>();
		this.dotList = new ArrayList<Dot>();
		this.setLineList(new ArrayList<Line>());
		this.setBarCenterList(new ArrayList<Dot>());
		observers.add(rp);
		addObs();
	}

	public void onClick(int x, int y) {
		System.out.println("Inside onClick method");
		boolean isLineAdded = false;
		Dot tempDot = new Dot(x, y, false, false);
		List<Dot> temp = new ArrayList<Dot>();
		temp.addAll(dotList);
		temp.addAll(getBarCenterList());
		for (Dot eachDot : temp) {
			if (eachDot.equals(tempDot)) {
				if (lineStartPointSelected) {
					addNewLine(lineStartPoint, eachDot);
					lineStartPointSelected = false;
				} else {
					lineStartPoint = eachDot;
					lineStartPointSelected = true;
				}
				isLineAdded = true;
			}
		}
		if (!isLineAdded) {
			addNewIcon(x, y);
		}
		setChanged();
		notifyObservers(this);
	}
	
	public void dragDrop(Dot dragPoint, Dot dropPoint) {
		String draggedShape;
		changeLine(dragPoint, dropPoint);
		for(String key: iconMap.keySet()) {
			for(Icon eachIcon: iconMap.get(key)) {
				if (isClickedIcon(eachIcon, dragPoint.getX(), dragPoint.getY())) {
					draggedShape = key;
					iconMap.get(key).remove(eachIcon);
					removeDot(draggedShape, eachIcon);
					ClickedShape.shapeName = draggedShape;
					addNewIcon(dropPoint.getX(), dropPoint.getY());
					moveLinesWithIcon(draggedShape, eachIcon);
					break;
				}
			}
		}
		setChanged();
		notifyObservers(this);
	}
	
	public void doubleClick(int x, int y, String value) {
		for(String key: iconMap.keySet()) {
			for(Icon eachIcon: iconMap.get(key)) {
				if (isClickedIcon(eachIcon, x, y)) {
					eachIcon.setText(value);
					if (key.equalsIgnoreCase("hashOperator")){
						MainWindow.obj.AddPanel(value);
					}
					break;
				}
			}
		}
	}
	
	public void rightClick(int x, int y) {
		System.out.println("Removing Icon1");
		for(String key: iconMap.keySet()) {
			System.out.println("Removing Icon2");
			for(Icon eachIcon: iconMap.get(key)) {
				System.out.println("Removing Icon3");
				if (isClickedIcon(eachIcon, x, y)) {
					System.out.println("Removing Icon4");
					iconMap.get(key).remove(eachIcon);
					removeIconLines(key ,eachIcon);
					removeDot(key, eachIcon);
					break;
				}
			}
		}
		setChanged();
		notifyObservers(this);
	}
	
	public String getTextValue(int x, int y) {
		for(String key: iconMap.keySet()) {
			for(Icon eachIcon: iconMap.get(key)) {
				if (isClickedIcon(eachIcon, x, y)) {
					return eachIcon.getText();
				}
			}
		}
		return null;
	}
	
	private void removeIconLines(String shapeName, Icon icon) {
		
		List<Line> allLines = new ArrayList<Line>();
		for(Line l : getLineList()) {
			allLines.add(l);
		}
		if (shapeName.equalsIgnoreCase("openParanthesis")) {
			ShapeOpenParan s = (ShapeOpenParan) icon;
			removeLine(s.getRightDot());
		} else if (shapeName.equalsIgnoreCase("closedParanthesis")) {
			ShapeClosedParan s = (ShapeClosedParan) icon;
			removeLine(s.getLeftDot());
		} else if (shapeName.equalsIgnoreCase("lessThanOperator")) {
			ShapeLessThan s = (ShapeLessThan) icon;
			removeLine(s.getRightLowerDot());
			removeLine(s.getRightUpperDot());
			removeLine(s.getLeftDot());
		} else if (shapeName.equalsIgnoreCase("greaterThanOperator")) {
			ShapeGreaterThan s = (ShapeGreaterThan) icon;
			removeLine(s.getLeftLowerDot());
			removeLine(s.getLeftUpperDot());
			removeLine(s.getRightDot());
		} else if (shapeName.equalsIgnoreCase("atTheRateOperator")) {
			ShapeAtTheRate s = (ShapeAtTheRate) icon;
			removeLine(s.getLeftLowerDot());
			removeLine(s.getLeftUpperDot());
			removeLine(s.getRightLowerDot());
			removeLine(s.getRightUpperDot());
		} else if (shapeName.equalsIgnoreCase("barOperator")) {
			ShapeBar s = (ShapeBar) icon;
			Dot oldLDot = new Dot(s.getLeftLowerDot().getX(),
					(s.getLeftLowerDot().getY() + s.getLeftUpperDot().getY())/2,true,false);
			Dot oldRDot = new Dot(s.getRightLowerDot().getX(),
					(s.getRightLowerDot().getY() + s.getRightUpperDot().getY())/2,false,true);
			removeLine(oldRDot);
			removeLine(oldLDot);
		} else if (shapeName.equalsIgnoreCase("dashOperator")) {
			ShapeDash s = (ShapeDash) icon;
			removeLine(s.getRightDot());
			removeLine(s.getLeftDot());
		} else if (shapeName.equalsIgnoreCase("hashOperator")) {
			ShapeHash s = (ShapeHash) icon;
			removeLine(s.getRightDot());
			removeLine(s.getLeftDot());
		}
	}
	
	private void changeLine(Dot dragPoint, Dot dropPoint) {
		List<Line> allLines = new ArrayList<Line>();
		for(Line l : getLineList()) {
			allLines.add(l);
		}
		for (Line eachLine: allLines) {
			if (eachLine.getStartDot().equals(dragPoint)) {
				Dot lineEndPoint = eachLine.getEndDot();
				getLineList().remove(eachLine);
				for (Dot eachDot: dotList) {
					if (eachDot.equals(dropPoint)) {
						addNewLine(eachDot, lineEndPoint);
					}
				}
				break;
			} else if (eachLine.getEndDot().equals(dragPoint)) {
				Dot lineStartPoint = eachLine.getStartDot();
				getLineList().remove(eachLine);
				for (Dot eachDot: dotList) {
					if (eachDot.equals(dropPoint)) {
						addNewLine(lineStartPoint, eachDot);
					}
				}
				break;
			}
		}
	}
	
	private void removeLine(Dot lineEndPoint) {
		List<Line> allLines = new ArrayList<Line>();
		for(Line l : getLineList()) {
			allLines.add(l);
		}
		for (Line eachLine: allLines) {
			if (eachLine.getStartDot().equals(lineEndPoint) ||
					eachLine.getEndDot().equals(lineEndPoint)) {
				getLineList().remove(eachLine);
			}
		}
	}
	
	private void addNewLine(Dot startPoint, Dot endPoint) {
		Line newLine = new Line(startPoint, endPoint);
		if (Rules.isValidLine(newLine, getLineList(), getBarCenterList())) {
			getLineList().add(newLine);
		}
	}

	public void addNewIcon(int x, int y) {

		System.out.println("Adding new ICON" + x + ", " + y);
		String selectedIcon = ClickedShape.shapeName;
		if (selectedIcon.equalsIgnoreCase("openParanthesis")) {
			List<Icon> iconList = iconMap.get(selectedIcon);
			if (iconList == null) {
				iconList = new ArrayList<Icon>();
			}
			ShapeOpenParan newShape = new ShapeOpenParan(x, y);
			iconList.add(newShape);
			iconMap.put(selectedIcon, iconList);
			dotList.add(newShape.getRightDot());
		} else if (selectedIcon.equalsIgnoreCase("closedParanthesis")) {
			List<Icon> iconList = iconMap.get(selectedIcon);
			if (iconList == null) {
				iconList = new ArrayList<Icon>();
			}
			ShapeClosedParan newShape = new ShapeClosedParan(x, y);
			iconList.add(newShape);
			iconMap.put(selectedIcon, iconList);
			dotList.add(newShape.getLeftDot());
		} else if (selectedIcon.equalsIgnoreCase("lessThanOperator")) {
			List<Icon> iconList = iconMap.get(selectedIcon);
			if (iconList == null) {
				iconList = new ArrayList<Icon>();
			}
			ShapeLessThan newShape = new ShapeLessThan(x, y);
			iconList.add(newShape);
			iconMap.put(selectedIcon, iconList);
			dotList.add(newShape.getLeftDot());
			dotList.add(newShape.getRightLowerDot());
			dotList.add(newShape.getRightUpperDot());
		} else if (selectedIcon.equalsIgnoreCase("greaterThanOperator")) {
			List<Icon> iconList = iconMap.get(selectedIcon);
			if (iconList == null) {
				iconList = new ArrayList<Icon>();
			}
			ShapeGreaterThan newShape = new ShapeGreaterThan(x, y);
			iconList.add(newShape);
			iconMap.put(selectedIcon, iconList);
			dotList.add(newShape.getRightDot());
			dotList.add(newShape.getLeftLowerDot());
			dotList.add(newShape.getLeftUpperDot());
		} else if (selectedIcon.equalsIgnoreCase("atTheRateOperator")) {
			List<Icon> iconList = iconMap.get(selectedIcon);
			if (iconList == null) {
				iconList = new ArrayList<Icon>();
			}
			ShapeAtTheRate newShape = new ShapeAtTheRate(x, y);
			iconList.add(newShape);
			iconMap.put(selectedIcon, iconList);
			dotList.add(newShape.getRightLowerDot());
			dotList.add(newShape.getRightUpperDot());
			dotList.add(newShape.getLeftLowerDot());
			dotList.add(newShape.getLeftUpperDot());
		} else if (selectedIcon.equalsIgnoreCase("barOperator")) {
			List<Icon> iconList = iconMap.get(selectedIcon);
			if (iconList == null) {
				iconList = new ArrayList<Icon>();
			}
			ShapeBar newShape = new ShapeBar(x, y);
			iconList.add(new ShapeBar(x, y));
			iconMap.put(selectedIcon, iconList);
			barCenterList.add(new Dot(newShape.getLeftLowerDot().getX(),
					(newShape.getLeftLowerDot().getY() +
							newShape.getLeftUpperDot().getY())/2,true,false));
			barCenterList.add(new Dot(newShape.getRightLowerDot().getX(),
					(newShape.getRightLowerDot().getY() +
							newShape.getRightUpperDot().getY())/2,false,true));
		} else if (selectedIcon.equalsIgnoreCase("dashOperator")) {
			List<Icon> iconList = iconMap.get(selectedIcon);
			if (iconList == null) {
				iconList = new ArrayList<Icon>();
			}
			ShapeDash newShape = new ShapeDash(x, y);
			iconList.add(newShape);
			iconMap.put(selectedIcon, iconList);
			dotList.add(newShape.getRightDot());
			dotList.add(newShape.getLeftDot());
		} else if (selectedIcon.equalsIgnoreCase("hashOperator")) {
			List<Icon> iconList = iconMap.get(selectedIcon);
			if (iconList == null) {
				iconList = new ArrayList<Icon>();
			}
			ShapeHash newShape = new ShapeHash(x, y);
			iconList.add(newShape);
			iconMap.put(selectedIcon, iconList);
			dotList.add(newShape.getRightDot());
			dotList.add(newShape.getLeftDot());
		}
	}

	private void removeDot(String shapeName, Icon icon) {
		if (shapeName.equalsIgnoreCase("openParanthesis")) {
			ShapeOpenParan s = (ShapeOpenParan) icon;
			this.dotList.remove(s.getRightDot());
		} else if (shapeName.equalsIgnoreCase("closedParanthesis")) {
			ShapeClosedParan s = (ShapeClosedParan) icon;
			this.dotList.remove(s.getLeftDot());
		} else if (shapeName.equalsIgnoreCase("lessThanOperator")) {
			ShapeLessThan s = (ShapeLessThan) icon;
			this.dotList.remove(s.getLeftDot());
			this.dotList.remove(s.getRightLowerDot());
			this.dotList.remove(s.getRightUpperDot());
		} else if (shapeName.equalsIgnoreCase("greaterThanOperator")) {
			ShapeGreaterThan s = (ShapeGreaterThan) icon;
			this.dotList.remove(s.getRightDot());
			this.dotList.remove(s.getLeftLowerDot());
			this.dotList.remove(s.getLeftUpperDot());
		} else if (shapeName.equalsIgnoreCase("atTheRateOperator")) {
			ShapeAtTheRate s = (ShapeAtTheRate) icon;
			this.dotList.remove(s.getRightLowerDot());
			this.dotList.remove(s.getRightUpperDot());
			this.dotList.remove(s.getLeftLowerDot());
			this.dotList.remove(s.getLeftUpperDot());
		} else if (shapeName.equalsIgnoreCase("barOperator")) {
			ShapeBar s = (ShapeBar) icon;
			barCenterList.remove(new Dot(s.getLeftLowerDot().getX(),
					(s.getLeftLowerDot().getY() + s.getLeftUpperDot().getY())/2,true,false));
			barCenterList.remove(new Dot(s.getRightLowerDot().getX(),
					(s.getRightLowerDot().getY() + s.getRightUpperDot().getY())/2,false,true));
		} else if (shapeName.equalsIgnoreCase("dashOperator")) {
			ShapeDash s = (ShapeDash) icon;
			this.dotList.remove(s.getRightDot());
			this.dotList.remove(s.getLeftDot());
		} else if (shapeName.equalsIgnoreCase("hashOperator")) {
			ShapeHash s = (ShapeHash) icon;
			this.dotList.remove(s.getRightDot());
			this.dotList.remove(s.getLeftDot());
		}
	}
	
	private void moveLinesWithIcon(String shapeName, Icon icon) {
		
		List<Line> allLines = new ArrayList<Line>();
		for(Line l : getLineList()) {
			allLines.add(l);
		}
		if (shapeName.equalsIgnoreCase("openParanthesis")) {
			ShapeOpenParan s = (ShapeOpenParan) icon;
			int newIconIndex = iconMap.get(shapeName).size()-1;
			ShapeOpenParan newIcon = (ShapeOpenParan) iconMap.get(shapeName).get(newIconIndex);
			for (Line eachLine: allLines) {
				if (eachLine.getStartDot().equals(s.getRightDot())) {
					Dot end = eachLine.getEndDot();
					getLineList().remove(eachLine);
					addNewLine(newIcon.getRightDot(), end);
					break;
				}
			}
		} else if (shapeName.equalsIgnoreCase("closedParanthesis")) {
			ShapeClosedParan s = (ShapeClosedParan) icon;
			int newIconIndex = iconMap.get(shapeName).size()-1;
			ShapeClosedParan newIcon = (ShapeClosedParan) iconMap.get(shapeName).get(newIconIndex);
			for (Line eachLine: allLines) {
				if (eachLine.getEndDot().equals(s.getLeftDot())) {
					Dot start = eachLine.getStartDot();
					getLineList().remove(eachLine);
					addNewLine(start, newIcon.getLeftDot());
					break;
				}
			}
		} else if (shapeName.equalsIgnoreCase("lessThanOperator")) {
			ShapeLessThan s = (ShapeLessThan) icon;
			int newIconIndex = iconMap.get(shapeName).size()-1;
			ShapeLessThan newIcon = (ShapeLessThan) iconMap.get(shapeName).get(newIconIndex);
			for (Line eachLine: allLines) {
				if (eachLine.getStartDot().equals(s.getRightLowerDot())) {
					Dot end = eachLine.getEndDot();
					getLineList().remove(eachLine);
					addNewLine(newIcon.getRightLowerDot(), end);
				} else if (eachLine.getStartDot().equals(s.getRightUpperDot())) {
					Dot end = eachLine.getEndDot();
					getLineList().remove(eachLine);
					addNewLine(newIcon.getRightUpperDot(), end);
				} else if (eachLine.getEndDot().equals(s.getLeftDot())) {
					Dot start = eachLine.getStartDot();
					getLineList().remove(eachLine);
					addNewLine(start, newIcon.getLeftDot());
				}
			}
		} else if (shapeName.equalsIgnoreCase("greaterThanOperator")) {
			ShapeGreaterThan s = (ShapeGreaterThan) icon;
			int newIconIndex = iconMap.get(shapeName).size()-1;
			ShapeGreaterThan newIcon = (ShapeGreaterThan) iconMap.get(shapeName).get(newIconIndex);
			for (Line eachLine: allLines) {
				if (eachLine.getEndDot().equals(s.getLeftLowerDot())) {
					Dot start = eachLine.getStartDot();
					getLineList().remove(eachLine);
					addNewLine(start, newIcon.getLeftLowerDot());
				} else if (eachLine.getEndDot().equals(s.getLeftUpperDot())) {
					Dot start = eachLine.getStartDot();
					getLineList().remove(eachLine);
					addNewLine(start, newIcon.getLeftUpperDot());
				} else if (eachLine.getStartDot().equals(s.getRightDot())) {
					Dot end = eachLine.getEndDot();
					getLineList().remove(eachLine);
					addNewLine(newIcon.getRightDot(), end);
				}
			}
		} else if (shapeName.equalsIgnoreCase("atTheRateOperator")) {
			ShapeAtTheRate s = (ShapeAtTheRate) icon;
			int newIconIndex = iconMap.get(shapeName).size()-1;
			ShapeAtTheRate newIcon = (ShapeAtTheRate) iconMap.get(shapeName).get(newIconIndex);
			for (Line eachLine: allLines) {
				if (eachLine.getEndDot().equals(s.getLeftLowerDot())) {
					Dot start = eachLine.getStartDot();
					getLineList().remove(eachLine);
					addNewLine(start, newIcon.getLeftLowerDot());
				} else if (eachLine.getEndDot().equals(s.getLeftUpperDot())) {
					Dot start = eachLine.getStartDot();
					getLineList().remove(eachLine);
					addNewLine(start, newIcon.getLeftUpperDot());
				} else if (eachLine.getStartDot().equals(s.getRightLowerDot())) {
					Dot end = eachLine.getEndDot();
					getLineList().remove(eachLine);
					addNewLine(newIcon.getRightLowerDot(), end);
				} else if (eachLine.getStartDot().equals(s.getRightUpperDot())) {
					Dot end = eachLine.getEndDot();
					getLineList().remove(eachLine);
					addNewLine(newIcon.getRightUpperDot(), end);
				}
			}
		} else if (shapeName.equalsIgnoreCase("barOperator")) {
			ShapeBar s = (ShapeBar) icon;
			int newIconIndex = iconMap.get(shapeName).size()-1;
			ShapeBar newIcon = (ShapeBar) iconMap.get(shapeName).get(newIconIndex);
			Dot oldLDot = new Dot(s.getLeftLowerDot().getX(),
					(s.getLeftLowerDot().getY() + s.getLeftUpperDot().getY())/2,true,false);
			Dot oldRDot = new Dot(s.getRightLowerDot().getX(),
					(s.getRightLowerDot().getY() + s.getRightUpperDot().getY())/2,false,true);
			Dot newLDot = new Dot(newIcon.getLeftLowerDot().getX(),
					(newIcon.getLeftLowerDot().getY() + newIcon.getLeftUpperDot().getY())/2,true,false);
			Dot newRDot = new Dot(newIcon.getRightLowerDot().getX(),
					(newIcon.getRightLowerDot().getY() + newIcon.getRightUpperDot().getY())/2,false,true);
			for (Line eachLine: allLines) {
				if (eachLine.getStartDot().equals(oldRDot)) {
					Dot end = eachLine.getEndDot();
					getLineList().remove(eachLine);
					addNewLine(newRDot, end);
				} else if (eachLine.getEndDot().equals(oldLDot)) {
					Dot start = eachLine.getStartDot();
					getLineList().remove(eachLine);
					addNewLine(start, newLDot);
				}
			}
		} else if (shapeName.equalsIgnoreCase("dashOperator")) {
			ShapeDash s = (ShapeDash) icon;
			int newIconIndex = iconMap.get(shapeName).size()-1;
			ShapeDash newIcon = (ShapeDash) iconMap.get(shapeName).get(newIconIndex);
			for (Line eachLine: allLines) {
				if (eachLine.getStartDot().equals(s.getRightDot())) {
					Dot end = eachLine.getEndDot();
					getLineList().remove(eachLine);
					addNewLine(newIcon.getRightDot(), end);
				} else if (eachLine.getEndDot().equals(s.getLeftDot())) {
					Dot start = eachLine.getStartDot();
					getLineList().remove(eachLine);
					addNewLine(start, newIcon.getLeftDot());
				}
			}
		} else if (shapeName.equalsIgnoreCase("hashOperator")) {
			ShapeHash s = (ShapeHash) icon;
			int newIconIndex = iconMap.get(shapeName).size()-1;
			ShapeHash newIcon = (ShapeHash) iconMap.get(shapeName).get(newIconIndex);
			for (Line eachLine: allLines) {
				if (eachLine.getStartDot().equals(s.getRightDot())) {
					Dot end = eachLine.getEndDot();
					getLineList().remove(eachLine);
					addNewLine(newIcon.getRightDot(), end);
				} else if (eachLine.getEndDot().equals(s.getLeftDot())) {
					Dot start = eachLine.getStartDot();
					getLineList().remove(eachLine);
					addNewLine(start, newIcon.getLeftDot());
				}
			}
		}
	}
	
	private boolean isClickedIcon(Icon icon, int x, int y) {
		if (x >= icon.getMiddlePointX() - 80 &&
			x <= icon.getMiddlePointX() + 80 &&
			y >= icon.getMiddlePointY() - 30 &&
			y <= icon.getMiddlePointY() + 30) {
			return true;
		} else {
			return false;
		}
	}

	private void addObs() {
		System.out.println("Obs size" +this.observers.size());
		for (Observer each : this.observers) {
			this.addObserver(each);
		}
	}

	public List<Line> getLineList() {
		return this.lineList;
	}

	public void setLineList(List<Line> lineList) {
		this.lineList = lineList;
	}

	public HashMap<String, List<Icon>> getIconMap() {
		return iconMap;
	}

	public void setIconMap(HashMap<String, List<Icon>> iconMap) {
		this.iconMap = iconMap;
	}

	public List<Dot> getBarCenterList() {
		return barCenterList;
	}

	public void setBarCenterList(List<Dot> barCenterList) {
		this.barCenterList = barCenterList;
	}

	public List<Dot> getDotList() {
		return dotList;
	}

	public void setDotList(List<Dot> dotList) {
		this.dotList = dotList;
	}
	
	public void customNotify() {
		setChanged();
		notifyObservers(this);
	}
}
