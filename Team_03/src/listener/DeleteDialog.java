package listener;

import frame.Frame;
import shape.DefaultShapes;
import shape.Line;
import shape.Shape;
import java.awt.BorderLayout;
import java.awt.Point;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * DeleteDialog.java - a class for deleteDialogBox pop up confirmation
 * 
 * @author Nachiappan Lakshmanan
 * @version 1.0
 *
 */
public class DeleteDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	// empty constructor as a part of over loading
	DeleteDialog() {

	}

	public DeleteDialog(JFrame parent, String title, boolean modality, Point displayPoint) {
		super(parent, title, modality);
		setLocation(displayPoint.x, displayPoint.y);
		JPanel dialogPanel = new JPanel();

		JButton buttonEnter = new JButton("DELETE");
		buttonEnter.addActionListener(new DeleteActionListener());
		dialogPanel.add(buttonEnter);

		getContentPane().add(dialogPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public boolean isLineClicked(int coordinateX, int coordinateY) {

		List<Shape> listOfShapes = new DefaultShapes()
				.removeDeafultShapesFromCompile(Frame.mapDrawingAreas.get(Frame.currentTab).listOfShapes);
		int size = listOfShapes.size();

		int max = 0;
		for (int i = 0; i < size; i++) {
			Shape object = listOfShapes.get(i);
			if (object.toString().contains("Line")) {
				Line line = (Line) object;
				if (Math.abs(line.getCoordinateX2() - line.getCoordinateX1()) != 0
						&& Math.abs(line.getCoordinateY2() - line.getCoordinateY1()) != 0
						&& Math.abs(line.getCoordinateX2() - coordinateX) != 0
						&& Math.abs(line.getCoordinateY2() - coordinateY) != 0) {
					int trueSlope = Math.abs(line.getCoordinateX2() - line.getCoordinateX1())
							/ Math.abs(line.getCoordinateY2() - line.getCoordinateY1());
					int slope = Math.abs(line.getCoordinateX2() - coordinateX)
							/ Math.abs(line.getCoordinateY2() - coordinateY);
					int percent = 0;
					if (slope != 0 && trueSlope != 0) {
						if (slope > trueSlope) {
							percent = (trueSlope * 100) / slope;
						} else {
							percent = (slope * 100) / trueSlope;
						}
					}
					if (percent > max) {
						max = percent;
						MouseListener.selectedShape = line;
					}
				}
			}
		}
		return true;
	}

}
