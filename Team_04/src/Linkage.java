import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Connector for the operators
 *
 * @author Rohith Varma Gaddam
 * @since April 26, 2020
 */
public abstract class Linkage extends JPanel implements MouseListener {
	String type;
	Boolean connected = false;
	DrawShape shape;

	Linkage(DrawShape shape, String type) {
		Border blackline = BorderFactory.createLineBorder(Color.black);
		setBorder(blackline);
		this.shape = shape;
		this.type = type;
		addMouseListener(this);
	}

}
