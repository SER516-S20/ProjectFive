import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 * @author Sarvansh
 * @created on 03-18-2020
 * @version 1.0
 * @author Rohit
 * @modified on 03-18-2020
 * @version 2.0 * @author Kunal
 * @modified on 04-30-2020
 * @version 3.0
 */
public class Shapes extends JPanel implements ActionListener, MouseListener {
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
	private JLabel openParanthesis1 = new JLabel();
	private JLabel closedParanthesis1 = new JLabel();
	private JLabel lessThanOperator1 = new JLabel();
	private JLabel greaterThanOperator1 = new JLabel();
	private JLabel atTheRateOperator1 = new JLabel();
	private JLabel barOperator1 = new JLabel();
	private JLabel dashOperator1 = new JLabel();
	private JLabel hashOperator1 = new JLabel();
	private java.util.List<JButton> buttons = new ArrayList<JButton>();
	private java.util.List<JLabel> leftButtons = new ArrayList<JLabel>();

	String[] images = new String[] {
			"images/shape1.png", "images/shape2.png", "images/shape3.png", "images/shape4.png",
			"images/shape5.png", "images/shape6.png", "images/shape7.png", "images/shape8.png" };

	public Shapes() {
		shapesToolBar = new JToolBar();
		shapesToolBar.setSize(400, 100);
		leftShapesToolBar = new JToolBar(JToolBar.VERTICAL);
		leftShapesToolBar.setPreferredSize(new Dimension(1500, 30));
		leftShapesToolBar.setBounds(0, 0, 100, 200);
		leftShapesToolBar.setFloatable(false);

	}

	public JToolBar CreateShapes() {
		setUpButtons();
		addButtons();
		return shapesToolBar;
	}

	public JToolBar CreateLeftShapes() {
		setLeftButtons();
		addLeftButtons();
		return leftShapesToolBar;
	}

	private void addButtons() {
		for (int i = 0; i < buttons.size(); i++) {
			JButton button = buttons.get(i);
			button.addActionListener(this);
			button.setPreferredSize(new Dimension(150, 70));
			button.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(images[i]))
					.getImage().getScaledInstance(160, 70, Image.SCALE_SMOOTH)));
			shapesToolBar.add(button);
			shapesToolBar.addSeparator();
		}

	}

	private void addLeftButtons() {
		for (int i = 0; i < leftButtons.size(); i++) {
			JLabel button = leftButtons.get(i);
			button.addMouseListener(this);
			button.setPreferredSize(new Dimension(50, 120));
			button.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(images[i]))
					.getImage().getScaledInstance(140, 70, Image.SCALE_SMOOTH)));
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
		leftButtons.add(openParanthesis1);
		leftButtons.add(closedParanthesis1);
		leftButtons.add(lessThanOperator1);
		leftButtons.add(greaterThanOperator1);
		leftButtons.add(atTheRateOperator1);
		leftButtons.add(barOperator1);
		leftButtons.add(dashOperator1);
		leftButtons.add(hashOperator1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(" Action Pressed");
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
		} else if (e.getSource() == hashOperator) {
			ClickedShape.shapeName = "hashOperator";
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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

	/*
	 * X and Y coordinates are manipulated as event is captured in left panel and
	 * shaper are drawn in right panel
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		String temp = ClickedShape.shapeName;
		System.out.println(e.getSource());
		if (e.getSource() == openParanthesis1) {
			ClickedShape.shapeName = "openParanthesis";
			RightPanelMouseListener.dataProcessor.addNewIcon(e.getX() - 165, e.getY() - 25);
		} else if (e.getSource() == closedParanthesis1) {
			ClickedShape.shapeName = "closedParanthesis";
			RightPanelMouseListener.dataProcessor.addNewIcon(e.getX() - 165, e.getY() + 62);
		} else if (e.getSource() == lessThanOperator1) {
			ClickedShape.shapeName = "lessThanOperator";
			RightPanelMouseListener.dataProcessor.addNewIcon(e.getX() - 165, e.getY() + 150);
		} else if (e.getSource() == greaterThanOperator1) {
			ClickedShape.shapeName = "greaterThanOperator";
			RightPanelMouseListener.dataProcessor.addNewIcon(e.getX() - 165, e.getY() + 238);
		} else if (e.getSource() == atTheRateOperator1) {
			ClickedShape.shapeName = "atTheRateOperator";
			RightPanelMouseListener.dataProcessor.addNewIcon(e.getX() - 165, e.getY() + 325);
		} else if (e.getSource() == barOperator1) {
			ClickedShape.shapeName = "barOperator";
			RightPanelMouseListener.dataProcessor.addNewIcon(e.getX() - 165, e.getY() + 412);
		} else if (e.getSource() == dashOperator1) {
			ClickedShape.shapeName = "dashOperator";
			RightPanelMouseListener.dataProcessor.addNewIcon(e.getX() - 165, e.getY() + 500);
		} else if (e.getSource() == hashOperator1) {
			ClickedShape.shapeName = "hashOperator";
			RightPanelMouseListener.dataProcessor.addNewIcon(e.getX() - 165, e.getY() + 587);
			MainWindow.obj.AddPanel();
		}

		RightPanelMouseListener.dataProcessor.customNotify();
		ClickedShape.shapeName = temp;

	}
}
