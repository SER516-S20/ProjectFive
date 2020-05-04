package model;

import javax.swing.JComponent;

/**
 *
 * @author Rahul
 * @since 03-12-2020
 * @Description: This class is a subclass of Symbol & handles drawing Or symbol button.
 */
public class OrSymbol extends Symbol {

	public OrSymbol(JComponent panel, int x, int y) {
		super("||", panel, x, y);
		
		int width = this.getPreferredSize().width;
		int height = this.getPreferredSize().height;
		
		new Bar(10, 10, this, true);
		new Bar(width - 25, 10, this, false);
	}

}
