import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * 
 * @author Tarun Snehith Kishore Reddy Karna
 * @since 05-01-2021
 * @version 1.0
 *
 */
public class OptionsPanel extends JToolBar {
	public OptionsPanel() {
		Border blackline = BorderFactory.createLineBorder(Color.black);
		setBorder(blackline);
		setLayout(new GridLayout(1, 8));
		for(int i=0; i<8; i++) {
	        try {
	          Class<?> ButtonClass = Class.forName("Button"+(i+1));
	          add((JButton) ButtonClass.getDeclaredConstructor().newInstance());
	        } catch (Exception e) {
	        	System.out.println(i+1);
	        }
		}
	}
}
