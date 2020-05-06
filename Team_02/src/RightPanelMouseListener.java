import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Observer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author abhinaw sarang
 * @modified on 03-15-2020
 * @version 1.0
 */
public class RightPanelMouseListener extends JPanel {

	private static final long serialVersionUID = 1L;
	public RightPanelDataProcessor dataProcessor;
	private boolean isDragDropEvent = false;
	private Dot startDot;

	public RightPanelMouseListener(RightPanel rp) {
		this.setPreferredSize(new Dimension(ShapeDimension.panelWidth, ShapeDimension.panelHeight));
		this.setSize(ShapeDimension.panelWidth, ShapeDimension.panelHeight);
		this.setVisible(true);
		addMouseListener(new DrawBoardMouseListener());
		addMouseMotionListener(new DrawBoardMouseMotion());
		dataProcessor = new RightPanelDataProcessor(rp);
	}

	private class DrawBoardMouseListener extends MouseAdapter {
		int shiftX = 15;

		@Override
		public void mouseClicked(MouseEvent event) {
			if (!isDragDropEvent) {
				if (event.getClickCount() == 2) {
					int index = dataProcessor.getIconMap().get(ClickedShape.shapeName).size() - 1;
					dataProcessor.getIconMap().get(ClickedShape.shapeName).remove(index);
					String value = null;
					String textValue = dataProcessor.getTextValue(event.getX() + shiftX, event.getY());
					if (textValue != null) {
						value = JOptionPane.showInputDialog("Value of Shape", textValue);
					} else {
						value = JOptionPane.showInputDialog("Value of Shape");
					}
					dataProcessor.doubleClick(event.getX() + shiftX, event.getY(), value);
				} else if (event.getButton() == MouseEvent.BUTTON3) {
					int input = JOptionPane.showConfirmDialog(null, "Do you want to delete?", "Select an Option...",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
					if (input == 0) {
						dataProcessor.rightClick(event.getX() + shiftX, event.getY());
					}
				} else {
					try {
						Thread.sleep(200); // To wait for double click.
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Inside mouse clicked");
					dataProcessor.onClick(event.getX() + shiftX, event.getY());
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent event) {
			System.out.println("Inside mouse pressed");
			startDot = new Dot(event.getX() + shiftX, event.getY(), false, false);
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			if (isDragDropEvent) {
				System.out.println("Inside mouse released");
				dataProcessor.dragDrop(startDot, new Dot(event.getX() + shiftX, event.getY(), false, false));
			}
			isDragDropEvent = false;
		}
	}

	private class DrawBoardMouseMotion extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent event) {
			System.out.println("Inside mouse dragged");
			isDragDropEvent = true;
		}
	}
}