package icon;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JButton;

import frame.Constants;
import shape.HashBlock;

/**
 * HashBlockIcon .java - a class for loading hash shape Icon inside
 * HashBlockIcon  Button on the left panel
 * 
 * @author Srinivasan Sundar
 * @version 1.0
 * 
 */
public class HashBlockIcon implements Icon{
	JButton hashBlockButton;

	public HashBlockIcon(JButton hashBlockButton) {
		this. hashBlockButton =  hashBlockButton;
	}

	public int getIconWidth() {
		return 0;
	}

	public int getIconHeight() {
		return 0;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		HashBlock hashBlock = new HashBlock();
		hashBlock.setPosition(x - Constants.X_DEVIATION, y - Constants.Y_DEVIATION);
		hashBlock.draw(g);
	}
}
