import java.awt.Dimension;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author Sarvansh
 * @created on 03-18-2020
 * @version 1.0
 * @author Rohit
 * @modified on 03-18-2020
 * @version 2.0
 */
public class Shapes implements ActionListener {

	private JButton openParanthesis = new JButton();
	private JButton closedParanthesis = new JButton();
	private JButton lessThanOperator = new JButton();
	private JButton greaterThanOperator = new JButton();
	private JButton atTheRateOperator = new JButton();
	private JButton barOperator = new JButton();
	private JButton dashOperator = new JButton();
	private java.util.List<JButton> buttons = new ArrayList<JButton>();

	String[] images = new String[] { 
			"images/shape1.png", "images/shape2.png", 
			"images/shape3.png", "images/shape4.png",
			"images/shape5.png", "images/shape6.png", 
			"images/shape7.png" };
	private Panel panel;

	public Panel CreateShapes() {
		panel = new Panel();
		setUpButtons();
		addButtons();
		panel.setSize(400, 100);
		return panel;
	}

	private void addButtons() {
		for (int i = 0; i < buttons.size(); i++) {
			JButton button = buttons.get(i);
			button.addActionListener(this);
			button.setPreferredSize(new Dimension(180, 70));
			button.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(images[i])).getImage()
					.getScaledInstance(190, 70, Image.SCALE_SMOOTH)));
			panel.add(button);
		}

	}

	private void setUpButtons() {
		buttons.add(openParanthesis);
		buttons.add(closedParanthesis);
		buttons.add(lessThanOperator);
		buttons.add(greaterThanOperator);
		buttons.add(atTheRateOperator);
		buttons.add(barOperator);
		buttons.add(dashOperator);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openParanthesis) {
			ClickedShape.shapeName = "openParanthesis";
		} else if (e.getSource() == closedParanthesis) {
			ClickedShape.shapeName = "closedParanthesis";
		} else if (e.getSource() == lessThanOperator) {
			ClickedShape.shapeName = "lessThanOperator";
		} else if (e.getSource() == greaterThanOperator) {
			ClickedShape.shapeName = "greaterThanOperator";
		} else if (e.getSource() == atTheRateOperator) {
			ClickedShape.shapeName = "atTheRateOperator";
		} else if (e.getSource() == barOperator) {
			ClickedShape.shapeName = "barOperator";
		} else if (e.getSource() == dashOperator) {
			ClickedShape.shapeName = "dashOperator";
		}
	}
}
