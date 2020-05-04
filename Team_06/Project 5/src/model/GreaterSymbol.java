package model;

import javax.swing.JComponent;

/**
 *
 * @author Somesh
 * @since 04-23-2020
 * @Description: This class is a subclass of Symbol and handles drawing > symbol button.
 */
public class GreaterSymbol extends Symbol{

	public GreaterSymbol(JComponent panel, int x, int y) {
		super(">", panel, x, y);
		
		int width = this.getPreferredSize().width;
		int height = this.getPreferredSize().height;
		
		new Dot(10, 10, this, true);
		new Dot(10, height - 25, this, true);
		new Dot(width - 25, height / 2 - 7, this, false);
	}

}
