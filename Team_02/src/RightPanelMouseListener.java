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
	public static RightPanelDataProcessor dataProcessor;
	private boolean isDragDropEvent = false;
	private Dot startDot;

	public RightPanelMouseListener(RightPanel rp) {
		this.setPreferredSize(new Dimension(1600, 800));
		this.setVisible(true);
		addMouseListener(new DrawBoardMouseListener());
		addMouseMotionListener(new DrawBoardMouseMotion());
		RightPanelDataProcessor.observers.add((Observer) rp);
		dataProcessor = new RightPanelDataProcessor();
	}

	private class DrawBoardMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent event) {
			if (!isDragDropEvent) {
				if (event.getClickCount() == 2) {
					int index = dataProcessor.getIconMap().get(ClickedShape.shapeName).size() - 1;
					dataProcessor.getIconMap().get(ClickedShape.shapeName).remove(index);
					String value = null;
					String textValue = dataProcessor.getTextValue(event.getX() - 150, event.getY());
					if (textValue != null) {
						value = JOptionPane.showInputDialog("Value of Shape", textValue);
					} else {
						value = JOptionPane.showInputDialog("Value of Shape");
					}
					dataProcessor.doubleClick(event.getX() - 150, event.getY(), value);
				} else {
					try {
						Thread.sleep(200); // To wait for double click.
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Inside mouse clicked");
					dataProcessor.onClick(event.getX() - 150, event.getY());
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent event) {
			System.out.println("Inside mouse pressed");
			startDot = new Dot(event.getX() - 150, event.getY(), false, false);
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			if (isDragDropEvent) {
				System.out.println("Inside mouse released");
				dataProcessor.dragDrop(startDot, new Dot(event.getX() - 150, event.getY(), false, false));
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