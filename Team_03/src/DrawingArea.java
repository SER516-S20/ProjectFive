import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * DrawingArea.java - a class for drawing different shapes and save,load
 * the files and draw it respectively.
 * 
 * @author Nachiappan Lakshmanan
 * @version 1.0
 * 
 */
public class DrawingArea extends JPanel implements Serializable {

	private static final long serialVersionUID = 1L;
	ArrayList<Shape> listOfShapes = new ArrayList<>();
	ArrayList<Integer> tabs = new ArrayList<>();
	private static final int DRAWING_AREA_HEIGHT = 600;
	private static final int DRAWING_AREA_WIDTH = 800;
	private static final int ArrayList = 0;
	JTabbedPane tabbedPane = new JTabbedPane();

	DrawingArea() {
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(DRAWING_AREA_WIDTH, DRAWING_AREA_HEIGHT));
		MouseListener mouseListener = new MouseListener();
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < listOfShapes.size(); i++) {
			Object object = listOfShapes.get(i);
			if (object instanceof FunctionBlockBegin) {
				FunctionBlockBegin functionBlockBegin = (FunctionBlockBegin) object;
				functionBlockBegin.draw(g);
			} else if (object instanceof FunctionBlockEnd) {
				FunctionBlockEnd functionBlockEnd = (FunctionBlockEnd) object;
				functionBlockEnd.draw(g);
			} else if (object instanceof IfBlockBegin) {
				IfBlockBegin ifBlockBegin = (IfBlockBegin) object;
				ifBlockBegin.draw(g);
			} else if (object instanceof IfBlockEnd) {
				IfBlockEnd ifBlockEnd = (IfBlockEnd) object;
				ifBlockEnd.draw(g);
			} else if (object instanceof ForLoop) {
				ForLoop forLoop = (ForLoop) object;
				forLoop.draw(g);
			} else if (object instanceof BarShape) {
				BarShape barShape = (BarShape) object;
				barShape.draw(g);
			} else if (object instanceof ValueHolderBlock) {
				ValueHolderBlock valueHolder = (ValueHolderBlock) object;
				valueHolder.draw(g);
			} else if (object instanceof Line) {
				Line line = (Line) object;
				line.draw(g);
			}
		}
	}

	public void save() {
		try {
			int temp = Frame.currentTab;
			tabs.add(temp);
			FileOutputStream fos = new FileOutputStream("listData" + temp);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			DrawingArea temp1 = Frame.mapDrawingAreas.get(Frame.currentTab);
			System.out.println(temp1.listOfShapes.size());
			ArrayList<Shape> list_temp = new ArrayList<>();
			list_temp = temp1.listOfShapes;
			oos.writeObject(list_temp);
			FileOutputStream fos_file = new FileOutputStream("tab_data");
			ObjectOutputStream oos_file = new ObjectOutputStream(fos_file);
			oos_file.writeObject(tabs);
			oos.close();
			fos.close();
			oos_file.close();
			fos_file.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void load(JTabbedPane tabbedPane) {
		System.out.println("Loaded");
		try {
			FileInputStream fis = new FileInputStream("tab_data");
			ObjectInputStream ois = new ObjectInputStream(fis);
			tabs = (ArrayList) ois.readObject();
			ois.close();
			fis.close();
			for (Integer i : tabs) {
				System.out.println(i);
			}
			int inc = 0;
			for (Integer i : tabs) {
				String temp = "Tab" + i;
				System.out.println(temp);
				DrawingArea obj = new DrawingArea();
				tabbedPane.addTab(temp, obj);
				FileInputStream fis_file = new FileInputStream("listData" + i);
				ObjectInputStream ois_file = new ObjectInputStream(fis_file);
				ArrayList<Shape> xlist = new ArrayList<>();
				xlist = (ArrayList) ois_file.readObject();
				System.out.println(xlist.size());
				obj.listOfShapes = xlist;
				Frame.mapDrawingAreas.put(inc, obj);
				ois_file.close();
				fis_file.close();
				inc = inc+1;
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			return;
		}
		repaint();
	}

	void addFunctionBlockBegin(FunctionBlockBegin square) {
		listOfShapes.add(square);
		repaint();
	}

	void addFunctionBlockEnd(FunctionBlockEnd square) {
		listOfShapes.add(square);
		repaint();
	}

	void addIfBlockBegin(IfBlockBegin square) {
		listOfShapes.add(square);
		repaint();
	}

	void addIfBlockEnd(IfBlockEnd square) {
		listOfShapes.add(square);
		repaint();
	}

	void addForLoop(ForLoop square) {
		listOfShapes.add(square);
		repaint();
	}

	void addBarShape(BarShape square) {
		listOfShapes.add(square);
		repaint();
	}

	void addValueHolderBlock(ValueHolderBlock square) {
		listOfShapes.add(square);
		repaint();
	}

	void addLine(Line line) {
		listOfShapes.add(line);
		repaint();
	}

	void repaintOnDrag() {
		repaint();
	}

}
