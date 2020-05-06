import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Bar Connector
 *
 * @author Rohith Varma Gaddam
 * @since April 26, 2020
 */
public class LinkageBar extends Linkage {
	final Dimension BAR_DIMENSION = new Dimension(10, 30);

	LinkageBar(DrawShape shape, String type) {
		super(shape, type);
		setPreferredSize(BAR_DIMENSION);
		setMaximumSize(BAR_DIMENSION);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (StoreClickPoints.link1 == null && StoreClickPoints.link2 == null && type == "O") {
			StoreClickPoints.link1 = this;
			this.setBackground(Color.BLACK);
		} else if (StoreClickPoints.link1 != null && StoreClickPoints.link2 == null && type == "I") {
			if (!shape.equals(StoreClickPoints.link1.shape)) {
				StoreClickPoints.link2 = this;
				this.setBackground(Color.BLACK);
				StoreClickPoints.StorePoints(this);
			}
		} else if (StoreClickPoints.link2 == null && StoreClickPoints.link1 == null && type == "I") {
			StoreClickPoints.link2 = this;
			this.setBackground(Color.BLACK);
		} else if (StoreClickPoints.link2 != null && StoreClickPoints.link1 == null && type == "O") {
			if (!shape.equals(StoreClickPoints.link2.shape)) {
				StoreClickPoints.link1 = this;
				this.setBackground(Color.BLACK);
				StoreClickPoints.StorePoints(this);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
