package Controller;

import View.Frame;
import View.RightPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import Model.*;

/**
 * Adding mouse listeners for providing mouse click functionality
 * to add the shape to the right panel.
 *
 * @author Sayali Tanawade
 * @version 1.0
 */
public class RightPanelMouseListener implements MouseListener, MouseMotionListener {

    private Shapes selectedShape;
    private List<Line> linesList = new ArrayList<>();
    private TextBox textBox = new TextBox();

    @Override
    public void mouseDragged(MouseEvent e) {
        if (selectedShape != null) {
            selectedShape.setX(e.getX());
            selectedShape.setY(e.getY());
        }
        for (Line nextLine : linesList) {
            if (nextLine.isSourceShape()) {
                nextLine.getLine().setSourceX(nextLine.getLineX() - (nextLine.getShapeX() - selectedShape.getX()));
                nextLine.getLine().setSourceY(nextLine.getLineY() - (nextLine.getShapeY() - selectedShape.getY()));
            } else if (nextLine.isDestShape()) {
                nextLine.getLine().setDestX(nextLine.getLineX() - (nextLine.getShapeX() - selectedShape.getX()));
                nextLine.getLine().setDestY(nextLine.getLineY() - (nextLine.getShapeY() - selectedShape.getY()));
            }
        }
        RightPanel.setSelected(false);
        Frame.rightPanel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {


        if ((Dot.isDotClicked || Dot.isBarClicked) && e.getClickCount() == 2 && !e.isConsumed()) {
            Shapes currentshape = null;
            for (Shapes shape : RightPanel.getRightPanelShapes()) {
                if (shape.containsPoint(e.getX(), e.getY())) {
                    currentshape = shape;
                }
            }
            if (!textBox.isPresentInMap(currentshape)) {
                String input = textBox.getUserInput();
                textBox.setTextValue(input, currentshape);
            } else {
                textBox.showUserInput(currentshape);
            }

        }
        if (!Dot.isDotClicked && !RightPanel.isMoved() && !Dot.isBarClicked) {
            int x = e.getX();
            int y = e.getY();
            ShapesEnum selectedShape = LeftPanelMouseListener.getSelectedShape();
            if (selectedShape == ShapesEnum.OPENBRACKET) {
                RightPanel.getRightPanelShapes().add(new OpenBracket(x, y));
            } else if (selectedShape == ShapesEnum.CLOSEBRACKET) {
                RightPanel.getRightPanelShapes().add(new CloseBracket(x, y));
            } else if (selectedShape == ShapesEnum.LESSTHAN) {
                RightPanel.getRightPanelShapes().add(new LessThan(x, y));
            } else if (selectedShape == ShapesEnum.GREATERTHAN) {
                RightPanel.getRightPanelShapes().add(new GreaterThan(x, y));
            } else if (selectedShape == ShapesEnum.ATTHERATE) {
                RightPanel.getRightPanelShapes().add(new AtTheRate(x, y));
            } else if (selectedShape == ShapesEnum.TWOBARS) {
                RightPanel.getRightPanelShapes().add(new TwoBars(x, y));
            } else if (selectedShape == ShapesEnum.HYPHEN) {
                RightPanel.getRightPanelShapes().add(new Hyphen(x, y));
            }
            Frame.rightPanel.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (Shapes sh : RightPanel.getRightPanelShapes()) {
            if (sh.containsPoint(e.getX(), e.getY())) {
                selectedShape = sh;
            }

        }
        for (Connections line : RightPanel.getLines()) {
            if (line.getOriginShape().equals(selectedShape)) {
                Line drawline = new Line();
                drawline.setLineX(line.getSourceX());
                drawline.setLineY(line.getSourceY());
                drawline.setShapeX(selectedShape.getX());
                drawline.setShapeY(selectedShape.getY());
                drawline.setLine(line);
                drawline.setSourceShape(true);
                linesList.add(drawline);
                
            } else if (line.getDestShape().equals(selectedShape)) {
                Line drawline = new Line();
                drawline.setLineX(line.getDestX());
                drawline.setLineY(line.getDestY());
                drawline.setShapeX(selectedShape.getX());
                drawline.setShapeY(selectedShape.getY());
                drawline.setLine(line);
                drawline.setDestShape(true);
                linesList.add(drawline);
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        selectedShape = null;
        linesList.clear();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
