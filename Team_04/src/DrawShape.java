import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * This class implements the drawing of shapes drag and drop within canvas.
 * 
 * @author Ashwath Reddy Koppala, Nikitha
 * @created on 1-29-2020
 * @updated on 04-26-2020
 * @version 1.0
 *
 */
public class DrawShape extends JPanel implements java.io.Serializable {
	int positionX;
	int positionY;
	int HEIGHT = 60;
	int WIDTH = 200;
	DrawingCanvas canvas;
	int shapeNo;
	String operator;
	String value;
	JPanel inputLinkage, outputLinkage;

	public DrawShape() {
		Dimension size = new Dimension(HEIGHT, WIDTH);
		setPreferredSize(size);
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 3));
		Border blackline = BorderFactory.createLineBorder(Color.black,3);
		setBorder(blackline);
	}
	
    JLabel getOpLabel() {
        JLabel operatorLabel = new JLabel(operator);
        operatorLabel.setForeground(Color.BLACK);
        operatorLabel.setPreferredSize(new Dimension(140, 50));
        operatorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        operatorLabel.setVerticalAlignment(SwingConstants.CENTER);
        operatorLabel.setFont(new Font("Serif", Font.BOLD, 30));
        return operatorLabel;
    }
	
    JPanel getConnectorPanel() {
        JPanel connector = new JPanel();
        connector.setLayout(new BoxLayout(connector, BoxLayout.Y_AXIS));
        connector.setBackground(Color.BLACK);
        return connector;
    }

    void packOperator() {
    	System.out.println("hi");
        inputLinkage = getConnectorPanel();
        this.add(inputLinkage);
        this.add(getOpLabel());
        outputLinkage = getConnectorPanel();
        this.add(outputLinkage);
    }

    void addToConnector(Linkage linkage) {
        if (linkage.type.equals("I")) {
            inputLinkage.add(Box.createVerticalGlue());
            inputLinkage.add(linkage);
            inputLinkage.add(Box.createVerticalGlue());
        } else if (linkage.type.equals("O")) {
            outputLinkage.add(Box.createVerticalGlue());
            outputLinkage.add(linkage);
            outputLinkage.add(Box.createVerticalGlue());
        } else
            System.out.println("Invalid connector type");
    }

}