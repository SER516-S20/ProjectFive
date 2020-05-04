package model;

import javax.swing.JComponent;

/**
 *
 * @author Somesh
 * @since 04-23-2020
 * @Description: This class is a subclass of Symbol and handles drawing @ symbol button.
 */
public class AtSymbol extends Symbol{

	public AtSymbol(JComponent panel, int x, int y) {
		super("@", panel, x, y);
		
		int width = this.getPreferredSize().width;
		int height = this.getPreferredSize().height;
		
		new Dot(10, 10, this, true);
		new Dot(10, height - 25, this, true);
		new Dot(width - 25, 10, this, false);
		new Dot(width - 25, height -25, this, false);
		
	}

}
