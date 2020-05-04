package listener;

import frame.Constants;
import frame.DrawingArea;
import frame.Frame;
import frame.Main;
import shape.DefaultShapes;
import shape.Shape;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * TranslatePackage.java a class for translating the diagram constructed to
 * source code so that later can be visualized in graph viz
 * 
 * @author Ashwin Srinivasan
 * @version 1.0
 * @since 03/20/2020
 */
public class TranslatePackage {
	Map<String, String> mapSymbols = new HashMap<>();
	static int inc = 0;
	static Shape startShape = null;
	static String program = "";
	Map<String, String> mapHashBlocks = new HashMap<>();
	Map<String, Boolean> mapValidTabs = new HashMap<>();

	public void translateDiagram() {
		Map<Integer, DrawingArea> mapDrawingAreas = Frame.mapDrawingAreas;
		Map<String, List<String>> mapTabs = new HashMap<>();
		Map<String, List<String>> mapConnections = new HashMap<>();
		char nodeIdentifier = 'a';
		for (int i = 0; i < mapDrawingAreas.size(); i++) {
			List<String> connectedTabs = new ArrayList<>();
			List<String> connections = new ArrayList<>();
			List<Shape> lstOfShapes = new DefaultShapes()
					.removeDeafultShapesFromCompile(mapDrawingAreas.get(i).listOfShapes);
			lstOfShapes = refineListOfShapes(lstOfShapes);
			createSparseMatrix(i, lstOfShapes, nodeIdentifier, connectedTabs, connections);
			nodeIdentifier = (char) (nodeIdentifier + 1);
			mapTabs.put(Main.frame.getTabbedPane().getTitleAt(i), connectedTabs);
			mapConnections.put(Main.frame.getTabbedPane().getTitleAt(i), connections);
			if (mapDrawingAreas.get(i).listOfShapes.size() > 0) {
				mapValidTabs.put(Main.frame.getTabbedPane().getTitleAt(i), true);
			}
		}
		buildProgram(mapTabs, mapConnections);
	}

	public void startDfs(Map<String, List<String>> mapTabs, Map<String, List<String>> mapConnections,
			String currentTab) {
		program = addVariables(mapConnections, currentTab);
		if (isSafeToProceed(mapTabs.get(currentTab))) {
			for (int i = 0; i < mapTabs.get(currentTab).size(); i++) {
				if (i + 1 < mapTabs.get(currentTab).size()) {
					program = program.replace(currentTab + "^" + String.valueOf(i),
							currentTab + "^" + String.valueOf(i) + " " + currentTab + "^" + String.valueOf(i + 1));
				}
				for (int j = 0; j < 50; j++) {
					String prev = program;
					program = program.replace(currentTab + "^" + String.valueOf(j),
							"subgraph " + mapTabs.get(currentTab).get(i) + " { " + mapTabs.get(currentTab).get(i) + "! "
									+ mapTabs.get(currentTab).get(i) + "^" + String.valueOf(i) + "}");
					if (!prev.equals(program)) {
						break;
					}
				}
				startDfs(mapTabs, mapConnections, mapTabs.get(currentTab).get(i));
			}
		}
	}

	public String addVariables(Map<String, List<String>> mapConnections, String currentTab) {
		Set<String> uniqueVariables = new HashSet<>();
		List<String> connections = mapConnections.get(currentTab);
		for (String each : connections) {
			uniqueVariables.add(each.split("->")[0]);
			uniqueVariables.add(each.split("->")[1]);
		}
		String s = "";
		for (String each : uniqueVariables) {
			if (!each.equals("start")) {
				s += each + " ";
			}
		}
		if (!s.equals("none")) {
			program = program.replace(currentTab + "!", s);
		}
		return program;
	}

	public boolean isSafeToProceed(List<String> lst) {
		if (lst.size() > 0) {
			return true;
		}
		return false;
	}

	public void buildProgram(Map<String, List<String>> mapTabs, Map<String, List<String>> mapConnections) {
		program = "digraph Tab0 { Tab0! Tab0^0 Tab0& }";
		startDfs(mapTabs, mapConnections, "Tab0");
		makeFinalConnections(mapTabs, mapConnections);
	}

	public void makeFinalConnections(Map<String, List<String>> mapTabs, Map<String, List<String>> mapConnections) {
		Set<String> finalConnections = new HashSet<>();
		Map<String, String> mapOriginalTabsStart = new HashMap<>();
		Map<String, String> mapOriginalTabsEnd = new HashMap<>();
		for (String each : mapConnections.keySet()) {
			List<String> lst = mapConnections.get(each);
			String max = "none";
			int maxValue = 0;
			for (String y : lst) {
				if (mapValidTabs.containsKey(each)) {
					if (!y.contains("start")) {
						finalConnections.add(y);
					}
				}
				if (!y.contains("start") && y.contains("0")) {
					mapOriginalTabsStart.put(each, y.split("->")[0]);
				}
				String end = y.split("->")[1];
				int value = Integer.valueOf(String.valueOf(end.charAt(1)));
				if (value >= maxValue) {
					maxValue = value;
					max = end;
				}
			}
			mapOriginalTabsEnd.put(each, max);

		}
		Set<String> resultConnections = new HashSet<>();
		for (String eachHashBlock : mapHashBlocks.keySet()) {
			for (String each : finalConnections) {
				if (each.contains(eachHashBlock)) {
					String split[] = each.split("->");
					if (split[1].contains(eachHashBlock)) {
						each = each.replace(eachHashBlock, mapOriginalTabsStart.get(mapHashBlocks.get(eachHashBlock)));
					} else if (split[0].contains(eachHashBlock)) {
						each = each.replace(eachHashBlock, mapOriginalTabsEnd.get(mapHashBlocks.get(eachHashBlock)));
					}
				}
				resultConnections.add(each);
			}
		}
		for (String each : finalConnections) {
			resultConnections.add(each);
		}
		String replaceConn = "";
		for (String each : resultConnections) {
			replaceConn += each + ";" + " ";
		}
		program = program.replace("Tab0&", replaceConn);
		for (String each : mapTabs.keySet()) {
			for (int j = 0; j < 100; j++) {
				program = program.replace(each + "^" + String.valueOf(j), " ");
			}
		}
		int increment = 0;
		program = program.replace("Tab0", "G");
		for (String each : mapTabs.keySet()) {
			program = program.replace(each, "cluster" + each);
		}
		new InputDialog(Main.frame, Constants.DIALOG_BOX_INPUT, true,
				new Point(Constants.FIRST_INDEX, Constants.FIRST_INDEX), program);
	}

	public List<Shape> refineConnectedShapes(List<Shape> connectedShapes) {
		Set<Shape> uniqueLstValues = new HashSet<Shape>(connectedShapes);
		List<Shape> aList = new ArrayList<Shape>();
		aList.addAll(uniqueLstValues);
		return aList;
	}

	public void dfs(Shape startShape, Map<String, String> exists, char nodeIdentifier, String parent,
			List<String> connections) {
		if (!exists.containsKey(startShape.toString())) {
			exists.put(startShape.toString(), nodeIdentifier + String.valueOf(inc));
		}
		if (startShape.toString().contains("HashBlock")) {
			mapHashBlocks.put(exists.get(startShape.toString()), startShape.getMessage());
		}
		connections.add(parent + "->" + exists.get(startShape.toString()));
		inc = inc + 1;
		if (isSafe(startShape)) {
			for (int i = 0; i < startShape.getConnectedShapes().size(); i++) {
				dfs(startShape.getConnectedShapes().get(i), exists, nodeIdentifier, exists.get(startShape.toString()),
						connections);
			}
		}
	}

	public boolean isSafe(Shape shape) {
		if (shape.getConnectedShapes().size() > 0) {
			return true;
		}
		return false;
	}

	public void createSparseMatrix(int currentTab, List<Shape> lstOfShapes, char nodeIdentifier,
			List<String> connectedTabs, List<String> connections) {
		if (startShape != null) {
			Map<String, String> exists = new HashMap<>();
			for (int i = 0; i < lstOfShapes.size(); i++) {
				if (lstOfShapes.get(i).toString().contains("HashBlock")) {
					connectedTabs.add(lstOfShapes.get(i).getMessage());
				}
			}
			inc = 0;
			dfs(startShape, exists, nodeIdentifier, "start", connections);
		}
	}

	public List<Shape> refineListOfShapes(List<Shape> lstOfShapes) {
		List<Shape> refLstOfShapes = new ArrayList<>();
		for (int i = 0; i < lstOfShapes.size(); i++) {
			if (!lstOfShapes.get(i).toString().contains("Line") && !lstOfShapes.get(i).toString().contains("Dot")) {
				lstOfShapes.get(i).setConnectedShapes(refineConnectedShapes(lstOfShapes.get(i).getConnectedShapes()));
				refLstOfShapes.add(lstOfShapes.get(i));
				if (lstOfShapes.get(i).toString().contains("FunctionBlockBegin")) {
					startShape = lstOfShapes.get(i);
				}
			}
		}
		return refLstOfShapes;
	}
}
