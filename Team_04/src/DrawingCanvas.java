import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.HashMap;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Snehith karna, Rohith Varma Gaddam,Ashwath Reddy Koppala
 * @since 1-28-2020
 * @updated 04-27-2020
 * @version 1.0
 */
public class DrawingCanvas extends JPanel implements MouseListener, java.io.Serializable{
	private transient SelectedShape shape;
	public ArrayList<Linkage[]> lineArray = new ArrayList<Linkage[]>();
	public ArrayList<DrawShape> shapeObject = new ArrayList<DrawShape>();

	public DrawingCanvas() {
		shape = new SelectedShape();
		this.setLayout(null);
		addMouseListener(this);
	}

	public void load() {
		for (int i = 0; i < shapeObject.size(); i++) {
			DrawShape label = (DrawShape) shapeObject.get(i);
			label.canvas = this;
			this.add(label);
			this.repaint();
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < lineArray.size(); i++) {
			Linkage[] links = lineArray.get(i);
			Point panelLocation = getLocationOnScreen();
            int x1 =
                    links[0].getLocationOnScreen().x - panelLocation.x + links[0].getWidth();
            int y1 =
                    links[0].getLocationOnScreen().y - panelLocation.y + links[0].getHeight() / 2;
            int x2 =
                    links[1].getLocationOnScreen().x - panelLocation.x - 2;
            int y2 =
                    links[1].getLocationOnScreen().y - panelLocation.y + links[1].getHeight() / 2;
			//System.out.println(points[0].x);
			//System.out.println(points[0].y);
			//System.out.println(points[1].x);
			//System.out.println(points[1].y);
			g.drawLine(x1, y1, x2, y2);
		}
	}
	
	public void addShapes(int x, int y, int No) {
		Point panelLocation = getLocationOnScreen();
		x = x -  panelLocation.x;
		y = y - panelLocation.y;
		if(this.contains(x, y)) {
			switch(No){
			case 1: DrawShape1 figure = new DrawShape1(x - 50, y, this);
			this.add((DrawShape) figure);
			shapeObject.add(figure);
			this.repaint();
			this.revalidate();
			break;
			
			case 2: DrawShape2 figure2 = new DrawShape2(x - 50, y, this);
			this.add((JPanel) figure2);
			shapeObject.add(figure2);
			this.repaint();
			this.revalidate();
			break;
			
			case 3: DrawShape3 figure3 = new DrawShape3(x - 50, y, this);
			this.add((JPanel) figure3);
			shapeObject.add(figure3);
			this.repaint();
			this.revalidate();
			break;

			case 4: DrawShape4 figure4 = new DrawShape4(x - 50, y, this);
			this.add((JPanel) figure4);
			shapeObject.add(figure4);
			this.repaint();
			this.revalidate();
			break;

			case 5: DrawShape5 figure5 = new DrawShape5(x - 50, y, this);
			this.add((JPanel) figure5);
			shapeObject.add(figure5);
			this.repaint();
			this.revalidate();
			break;
			
			case 6: DrawShape6 figure6 = new DrawShape6(x - 50, y, this);
			this.add((JPanel) figure6);
			shapeObject.add(figure6);
			this.repaint();
			this.revalidate();
			break;

			case 7: DrawShape7 figure7 = new DrawShape7(x - 50, y, this);
			this.add((JPanel) figure7);
			shapeObject.add(figure7);
			this.repaint();
			this.revalidate();
			break;
			
			case 8: DrawShape8 figure8 = new DrawShape8(x - 50, y, this);
			this.add((JPanel) figure8);
			shapeObject.add(figure8);
			this.repaint();
			this.revalidate();
			break;
		}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch(shape.No){
			case 1: DrawShape1 figure = new DrawShape1(e.getX() - 50, e.getY(), this);
			this.add((DrawShape) figure);
			shapeObject.add(figure);
			this.repaint();
			this.revalidate();
			break;
			
			case 2: DrawShape2 figure2 = new DrawShape2(e.getX() - 50, e.getY(), this);
			this.add((JPanel) figure2);
			shapeObject.add(figure2);
			this.repaint();
			this.revalidate();
			break;
			
			case 3: DrawShape3 figure3 = new DrawShape3(e.getX() - 50, e.getY(), this);
			this.add((JPanel) figure3);
			shapeObject.add(figure3);
			this.repaint();
			this.revalidate();
			break;

			case 4: DrawShape4 figure4 = new DrawShape4(e.getX() - 50, e.getY(), this);
			this.add((JPanel) figure4);
			shapeObject.add(figure4);
			this.repaint();
			this.revalidate();
			break;

			case 5: DrawShape5 figure5 = new DrawShape5(e.getX() - 50, e.getY(), this);
			this.add((JPanel) figure5);
			shapeObject.add(figure5);
			this.repaint();
			this.revalidate();
			break;
			
			case 6: DrawShape6 figure6 = new DrawShape6(e.getX() - 50, e.getY(), this);
			this.add((JPanel) figure6);
			shapeObject.add(figure6);
			this.repaint();
			this.revalidate();
			break;

			case 7: DrawShape7 figure7 = new DrawShape7(e.getX() - 50, e.getY(), this);
			this.add((JPanel) figure7);
			shapeObject.add(figure7);
			this.repaint();
			this.revalidate();
			break;
			
			case 8: DrawShape8 figure8 = new DrawShape8(e.getX() - 50, e.getY(), this);
			this.add((JPanel) figure8);
			shapeObject.add(figure8);
			this.repaint();
			this.revalidate();
			break;
		}

	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}
}
