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
    private static Shapes shp;
    private List<Line> linesList = new ArrayList<>();
    private TextBox textBox = new TextBox();
    private int index = 0;

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
        OpenBracket openbracket = null;
        CloseBracket closeBracket = null;
        LessThan lessThan = null;
        GreaterThan greaterThan = null;
        AtTheRate atTheRate = null;
        //TwoBars twoBars = null;
        Hyphen hyphen = null;
        //List<Connections> lst = RightPanel.getLines();
        //Add Re-Link Feature Will opush in next commit, some changes remaninong
//        if (Dot.isDotClicked || Dot.isBarClicked) {
//
//            System.out.println("---------");
//            //List<Connections> lst = RightPanel.getLines();
//            if (!RightPanel.getLines().isEmpty()) {
//                List<Connections> lst = RightPanel.getLines();
//                if (RightPanel.getOriginShape().containsPoint(e.getX(), e.getY())) {
//                    Shapes dest = null;
//                    Shapes shp = RightPanel.getOriginShape(); //current click
//                    if (shp instanceof OpenBracket)
//                        openbracket = (OpenBracket) shp;
//                    else if (shp instanceof CloseBracket)
//                        closeBracket = (CloseBracket) shp;
//                    else if (shp instanceof LessThan)
//                        lessThan = (LessThan) shp;
//                    else if (shp instanceof GreaterThan)
//                        greaterThan = (GreaterThan) shp;
//                    else if (shp instanceof AtTheRate)
//                        atTheRate = (AtTheRate) shp;
//                    else if (shp instanceof Hyphen)
//                        hyphen = (Hyphen) shp;
//
//                    for (Connections c : lst) {
//                        if (c.getOriginShape().equals(openbracket)) {
//                            System.out.println("Match found.....\n");
//                            openbracket.setLineDrawn(false);
//                            RightPanel.getLines().remove(index);
//                            dest = c.getDestShape();
//                            if (dest instanceof OpenBracket){
//                                openbracket = (OpenBracket) dest;
//                                openbracket.setLineDrawn(false);
//                            } else if (dest instanceof CloseBracket){
//                                closeBracket = (CloseBracket) dest;
//                                closeBracket.setLineDrawn(false);
//                            } else if (dest instanceof LessThan) {
//                                lessThan = (LessThan) dest;
//                                lessThan.setLineDrawnDot1(false);
//                                lessThan.setLineDrawnDot2(false);
//                                lessThan.setLineDrawnDot3(false);
//                            } else if (dest instanceof GreaterThan){
//                                greaterThan = (GreaterThan) dest;
//                                greaterThan.setLineDrawnDot1(false);
//                                greaterThan.setLineDrawnDot2(false);
//                                greaterThan.setLineDrawnDot3(false);
//                            } else if (dest instanceof AtTheRate){
//                                atTheRate = (AtTheRate) dest;
//                                atTheRate.setLineDrawnDot1(false);
//                                atTheRate.setLineDrawnDot2(false);
//                                atTheRate.setLineDrawnDot3(false);
//                                atTheRate.setLineDrawnDot4(false);
//                            }
//                            else if (dest instanceof Hyphen){
//                                hyphen = (Hyphen) dest;
//                                hyphen.setLineDrawnDot1(false);
//                                hyphen.setLineDrawnDot2(false);
//                            }
//                            break;
//                        }else if (c.getOriginShape().equals(closeBracket)) {
//                            System.out.println("Match found.....\n");
//                            closeBracket.setLineDrawn(false);
//                            RightPanel.getLines().remove(index);
//                            break;
//                        }else if (c.getOriginShape().equals(atTheRate)) {
//                            System.out.println("Match found.....\n");
//                            atTheRate.setLineDrawnDot1(false);
//                            atTheRate.setLineDrawnDot2(false);
//                            atTheRate.setLineDrawnDot3(false);
//                            RightPanel.getLines().remove(index);
//                            break;
//                        }else if (c.getOriginShape().equals(greaterThan)) {
//                            System.out.println("Match found.....\n");
//                            greaterThan.setLineDrawnDot1(false);
//                            greaterThan.setLineDrawnDot2(false);
//                            greaterThan.setLineDrawnDot3(false);
//                            RightPanel.getLines().remove(index);
//                            break;
//                        }else if(c.getOriginShape().equals(lessThan)){
//                            lessThan.setLineDrawnDot1(false);
//                            lessThan.setLineDrawnDot2(false);
//                            lessThan.setLineDrawnDot3(false);
//                            RightPanel.getLines().remove(index);
//                            break;
//                        }else if(c.getOriginShape().equals(hyphen)){
//                            hyphen.setLineDrawnDot1(false);
//                            hyphen.setLineDrawnDot2(false);
//                            RightPanel.getLines().remove(index);
//                            break;
//                        }
//                        //RightPanel.getLines().remove(index);
//                        index++;
//                    }
//                }
//
//                Frame.rightPanel.repaint();
//            }
//
//
//        }

        if (e.getClickCount() == 2 && !e.isConsumed()/*&&(Dot.isDotClicked || Dot.isBarClicked)*/) {
            Shapes currentshape = null;
            for (Shapes shape : RightPanel.getRightPanelShapes()) {
                if (shape.containsPoint(e.getX(), e.getY())) {
                    currentshape = shape;
                    if (!textBox.isPresentInMap(currentshape)) {
                        String input = textBox.getUserInput();
                        textBox.setTextValue(input, currentshape);
                    } else {
                        textBox.showUserInput(currentshape);
                    }
                }
            }
//            if (!textBox.isPresentInMap(currentshape)) {
//                String input = textBox.getUserInput();
//                textBox.setTextValue(input, currentshape);
//            } else {
//                textBox.showUserInput(currentshape);
//            }

        }
        if (!Dot.isDotClicked && !RightPanel.isMoved() && !Dot.isBarClicked && e.getClickCount() == 1) {
            int x = e.getX();
            int y = e.getY();
            ShapesEnum selectedShape = LeftPanelMouseListener.getSelectedShape();
            if (selectedShape == ShapesEnum.OPENBRACKET) {
                RightPanel.getRightPanelShapes().add(new OpenBracket(x, y));
                shp = new OpenBracket(x, y);
            } else if (selectedShape == ShapesEnum.CLOSEBRACKET) {
                RightPanel.getRightPanelShapes().add(new CloseBracket(x, y));
                shp = new CloseBracket(x, y);
            } else if (selectedShape == ShapesEnum.LESSTHAN) {
                RightPanel.getRightPanelShapes().add(new LessThan(x, y));
                shp = new LessThan(x, y);
            } else if (selectedShape == ShapesEnum.GREATERTHAN) {
                RightPanel.getRightPanelShapes().add(new GreaterThan(x, y));
                shp = new GreaterThan(x, y);
            } else if (selectedShape == ShapesEnum.ATTHERATE) {
                RightPanel.getRightPanelShapes().add(new AtTheRate(x, y));
                shp = new AtTheRate(x, y);
            } else if (selectedShape == ShapesEnum.TWOBARS) {
                RightPanel.getRightPanelShapes().add(new TwoBars(x, y));
                shp = new TwoBars(x, y);
            } else if (selectedShape == ShapesEnum.HYPHEN) {
                RightPanel.getRightPanelShapes().add(new Hyphen(x, y));
                shp = new Hyphen(x, y);
            } else if (selectedShape == ShapesEnum.POUND) {
                RightPanel.getRightPanelShapes().add(new Pound(x, y));
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
