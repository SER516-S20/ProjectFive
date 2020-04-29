import java.awt.Dimension;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * @author Sarvansh
 * @created on 03-18-2020
 * @version 1.0
 * @author Rohit
 * @modified on 03-18-2020
 * @version 2.0
 */
public class Shapes implements ActionListener {
	private JToolBar shapesToolBar;
	private JToolBar leftShapesToolBar;
	private JButton openParanthesis = new JButton();
	private JButton closedParanthesis = new JButton();
	private JButton lessThanOperator = new JButton();
	private JButton greaterThanOperator = new JButton();
	private JButton atTheRateOperator = new JButton();
	private JButton barOperator = new JButton();
	private JButton dashOperator = new JButton();
	private JButton hashOperator = new JButton();
	private java.util.List<JButton> buttons = new ArrayList<JButton>();
	private java.util.List<JButton> leftButtons = new ArrayList<JButton>();

	String[] images = new String[] { 
			"images/shape1.png", "images/shape2.png", 
			"images/shape3.png", "images/shape4.png",
			"images/shape5.png", "images/shape6.png", 
			"images/shape7.png","images/shape8.png" };
	private Panel panel;
	
	public Shapes()
	{
		shapesToolBar = new JToolBar(); 
		shapesToolBar.setSize(400, 100);
		
		leftShapesToolBar = new JToolBar(JToolBar.VERTICAL); 
		leftShapesToolBar.setPreferredSize(new Dimension(1500, 30));
		leftShapesToolBar.setBounds(0, 0, 100, 200);
		leftShapesToolBar.setFloatable(false);
		
	}

	public JToolBar CreateShapes() {
		//panel = new Panel();
		setUpButtons();
		addButtons();
		//panel.setSize(400, 100);
		//shapesToolBar.add(panel);
		return shapesToolBar;
	}
	
	public JToolBar CreateLeftShapes() {
		//panel = new Panel();
		setLeftButtons();
		addLeftButtons();
		//panel.setSize(400, 100);
		//shapesToolBar.add(panel);
		return leftShapesToolBar;
	}
	

	private void addButtons() {
		for (int i = 0; i < buttons.size(); i++) {
			JButton button = buttons.get(i);
			button.addActionListener(this);	
			button.setPreferredSize(new Dimension(150, 70));
			button.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(images[i])).getImage()
					.getScaledInstance(160, 70, Image.SCALE_SMOOTH)));
			shapesToolBar.add(button);
			shapesToolBar.addSeparator();
		}

	}
	
	private void addLeftButtons() {
		for (int i = 0; i < leftButtons.size(); i++) {
			JButton button = leftButtons.get(i);
			button.addActionListener(this);
			button.setPreferredSize(new Dimension(50, 100));
			button.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(images[i])).getImage()
					.getScaledInstance(120, 60, Image.SCALE_SMOOTH)));
			leftShapesToolBar.add(button);
			leftShapesToolBar.addSeparator();
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
		buttons.add(hashOperator);
	}
	
	private void setLeftButtons() {
		leftButtons.add(openParanthesis);
		leftButtons.add(closedParanthesis);
		leftButtons.add(lessThanOperator);
		leftButtons.add(greaterThanOperator);
		leftButtons.add(atTheRateOperator);
		leftButtons.add(barOperator);
		leftButtons.add(dashOperator);
		leftButtons.add(hashOperator);
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
		} else if (e.getSource() == dashOperator) {
			ClickedShape.shapeName = "hashOperator";
		} else if (e.getSource() == hashOperator) {
			ClickedShape.shapeName = "hashOperator";
		}
	}
}
