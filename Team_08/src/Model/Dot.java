package Model;

import Controller.CompileFile;
import View.Frame;
import View.RightPanel;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

import java.io.Serializable;

/**
 * This class consists of Model.Dot
 *
 * @author Amudhan Manisekaran
 * @version 1.0
 */

public class Dot extends Shapes implements MouseListener, MouseMotionListener, Serializable {

    private static final long serialVersionUID = 1L;
    private double x, y;
    private Shape square = null;
    private int sourceX;
    private int sourceY;
    private boolean firstDotClicked = false;
    private Shapes firstShape;
    public static boolean isBarClicked = false, isDotClicked = false;
    CompileFile compileFile = new CompileFile();

    public Dot(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Dot() {

    }

    @Override
    public void drawShape(Graphics graphic) {
        square = new Rectangle2D.Double(x, y, 10, 10);
        Graphics2D graphics2 = (Graphics2D) graphic;
        graphics2.fill(square);
    }

    public boolean containsPoint(int x, int y) {
        return square.contains(x, y);
    }

    @Override
    public int getX() {
        return (int) x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return (int) y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        CompileFile.setTrackShapes();
        int destinationX;
        int destinationY;
        Shapes secondShape;
        if (!RightPanel.isSelected() && isDotClicked) {
            for (Shapes shape : RightPanel.getRightPanelShapes()) {
                if (shape.containsPoint(e.getX(), e.getY()) && getIsLineDrawn(shape, e.getX(), e.getY())) {
                    RightPanel.setOriginShape(shape);
                    firstShape = shape;
                    sourceX = e.getX();
                    sourceY = e.getY();
                    isDotClicked = false;
                    firstDotClicked = true;
                    RightPanel.setMoved(true);
                    Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
                    Frame.rightPanel.setCursor(cursor);
                    Frame.rightPanel.setVisible(true);
                    break;
                }
            }
            RightPanel.setSelected(true);

        } else if (!RightPanel.isSelected() && isBarClicked) {
            for (Shapes shape : RightPanel.getRightPanelShapes()) {
                if (shape.containsPoint(e.getX(), e.getY()) && getIsLineDrawn(shape, e.getX(), e.getY())) {
                    RightPanel.setOriginShape(shape);
                    firstShape = shape;
                    sourceX = e.getX();
                    sourceY = e.getY();
                    isBarClicked = false;
                    firstDotClicked = true;
                    RightPanel.setMoved(true);
                    Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
                    Frame.rightPanel.setCursor(cursor);
                    Frame.rightPanel.setVisible(true);
                    break;
                }
            }
            RightPanel.setSelected(true);

        } else if (RightPanel.isSelected() && isDotClicked) {
            if (!RightPanel.getOriginShape().containsPoint(e.getX(), e.getY())) {
                for (Shapes shape : RightPanel.getRightPanelShapes()) {
                    if (shape.containsPoint(e.getX(), e.getY()) && getIsLineDrawn(shape, e.getX(), e.getY())
                            && getIsLineDrawn(firstShape, e.getX(), e.getY())) {
                        destinationX = e.getX();
                        destinationY = e.getY();

                        secondShape = shape;
                        Connections line = new Connections();
                        line.setSourceX(sourceX);
                        line.setDestX(destinationX);
                        line.setSourceY(sourceY);
                        line.setDestY(destinationY);
                        line.setOriginShape(firstShape);
                        line.setDestShape(secondShape);
                        RightPanel.getLines().add(line);
                        setIsLineDrawn(firstShape, sourceX, sourceY);
                        setIsLineDrawn(secondShape, e.getX(), e.getY());
                        firstDotClicked = false;
                        RightPanel.setMoved(false);
                        Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
                        Frame.rightPanel.setCursor(cursor);
                        Frame.rightPanel.setVisible(true);
                        updateHashMap(firstShape, secondShape);

                        if (CompileFile.trackShapes.containsKey(firstShape))
                            CompileFile.removeConnectedShapesFromMap(firstShape);

                        if (CompileFile.trackShapes.containsKey(secondShape))
                            CompileFile.removeConnectedShapesFromMap(secondShape);

                        RightPanel.setSelected(false);
                        break;
                    }

                }

                Frame.rightPanel.repaint();
            }
            isDotClicked = false;
        } else if (RightPanel.isSelected() && isBarClicked) {
            if (!RightPanel.getOriginShape().containsPoint(e.getX(), e.getY())) {
                for (Shapes shape : RightPanel.getRightPanelShapes()) {
                    if (shape.containsPoint(e.getX(), e.getY()) && getIsLineDrawn(firstShape, e.getX(), e.getY())) {
                        destinationX = e.getX();
                        destinationY = e.getY();
                        secondShape = shape;
                        Connections line = new Connections();
                        line.setSourceX(sourceX);
                        line.setDestX(destinationX);
                        line.setSourceY(sourceY);
                        line.setDestY(destinationY);
                        line.setOriginShape(firstShape);
                        line.setDestShape(secondShape);
                        RightPanel.getLines().add(line);
                        setIsLineDrawn(firstShape, sourceX, sourceY);
                        firstDotClicked = false;
                        RightPanel.setMoved(false);
                        RightPanel.setSelected(false);
                        Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
                        Frame.rightPanel.setCursor(cursor);
                        Frame.rightPanel.setVisible(true);
                        updateHashMap(firstShape, secondShape);
                        if (CompileFile.trackShapes.containsKey(firstShape))
                            CompileFile.removeConnectedShapesFromMap(firstShape);
                        break;
                    }
                }
                Frame.rightPanel.repaint();
            }
            isDotClicked = false;
            isBarClicked = false;
        } else {
            Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
            Frame.rightPanel.setCursor(cursor);
            Frame.rightPanel.setVisible(true);
            RightPanel.setMoved(false);
            RightPanel.setSelected(false);
        }

    }

    private void setIsLineDrawn(Shapes shape, int x, int y) {
        if (shape instanceof OpenBracket) {
            OpenBracket openbracket = (OpenBracket) shape;
            openbracket.setLineDrawn(true);
        } else if (shape instanceof CloseBracket) {
            CloseBracket closebracket = (CloseBracket) shape;
            closebracket.setLineDrawn(true);
        } else if (shape instanceof LessThan) {
            LessThan lessThan = (LessThan) shape;
            if (lessThan.getDot1().containsPoint(x, y)) {
                lessThan.setLineDrawnDot1(true);
            } else if (lessThan.getDot2().containsPoint(x, y)) {
                lessThan.setLineDrawnDot2(true);
            } else if (lessThan.getDot3().containsPoint(x, y)) {
                lessThan.setLineDrawnDot3(true);
            }
        } else if (shape instanceof GreaterThan) {
            GreaterThan greaterThan = (GreaterThan) shape;
            if (greaterThan.getDot1().containsPoint(x, y)) {
                greaterThan.setLineDrawnDot1(true);
            } else if (greaterThan.getDot2().containsPoint(x, y)) {
                greaterThan.setLineDrawnDot2(true);
            } else if (greaterThan.getDot3().containsPoint(x, y)) {
                greaterThan.setLineDrawnDot3(true);
            }
        } else if (shape instanceof AtTheRate) {
            AtTheRate atTheRate = (AtTheRate) shape;
            if (atTheRate.getDot1().containsPoint(x, y)) {
                atTheRate.setLineDrawnDot1(true);
            } else if (atTheRate.getDot2().containsPoint(x, y)) {
                atTheRate.setLineDrawnDot2(true);
            } else if (atTheRate.getDot3().containsPoint(x, y)) {
                atTheRate.setLineDrawnDot3(true);
            } else if (atTheRate.getDot4().containsPoint(x, y)) {
                atTheRate.setLineDrawnDot4(true);
            }
        } else if (shape instanceof Hyphen) {
            Hyphen hyphen = (Hyphen) shape;
            if (hyphen.getDot1().containsPoint(x, y)) {
                hyphen.setLineDrawnDot1(true);
            } else if (hyphen.getDot2().containsPoint(x, y)) {
                hyphen.setLineDrawnDot2(true);
            }
        }
    }

    private boolean getIsLineDrawn(Shapes shape, int x, int y) {
        if (shape instanceof OpenBracket) {
            OpenBracket openbracket = (OpenBracket) shape;
            return !openbracket.isLineDrawn();
        } else if (shape instanceof CloseBracket) {
            CloseBracket closebracket = (CloseBracket) shape;
            return !closebracket.isLineDrawn();
        } else if (shape instanceof LessThan) {
            LessThan lessThan = (LessThan) shape;
            if (lessThan.getDot1().containsPoint(x, y)) {
                return !lessThan.isLineDrawnDot1();
            } else if (lessThan.getDot2().containsPoint(x, y)) {
                return !lessThan.isLineDrawnDot2();
            } else if (lessThan.getDot3().containsPoint(x, y)) {
                return !lessThan.isLineDrawnDot3();
            }
        } else if (shape instanceof GreaterThan) {
            GreaterThan greaterThan = (GreaterThan) shape;
            if (greaterThan.getDot1().containsPoint(x, y)) {
                return !greaterThan.isLineDrawnDot1();
            } else if (greaterThan.getDot2().containsPoint(x, y)) {
                return !greaterThan.isLineDrawnDot2();
            } else if (greaterThan.getDot3().containsPoint(x, y)) {
                return !greaterThan.isLineDrawnDot3();
            }
        } else if (shape instanceof AtTheRate) {
            AtTheRate atTheRate = (AtTheRate) shape;
            if (atTheRate.getDot1().containsPoint(x, y)) {
                return !atTheRate.isLineDrawnDot1();
            } else if (atTheRate.getDot2().containsPoint(x, y)) {
                return !atTheRate.isLineDrawnDot2();
            } else if (atTheRate.getDot3().containsPoint(x, y)) {
                return !atTheRate.isLineDrawnDot3();
            } else if (atTheRate.getDot4().containsPoint(x, y)) {
                return !atTheRate.isLineDrawnDot4();
            }
        } else if (shape instanceof Hyphen) {
            Hyphen hyphen = (Hyphen) shape;
            if (hyphen.getDot1().containsPoint(x, y)) {
                return !hyphen.isLineDrawnDot1();
            } else if (hyphen.getDot2().containsPoint(x, y)) {
                return !hyphen.isLineDrawnDot2();
            }
        }
        return true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (Shapes sh : RightPanel.getRightPanelShapes()) {
            if (sh.containsPoint(e.getX(), e.getY())) {
                if (sh instanceof OpenBracket && ((OpenBracket) sh).getDot().containsPoint(e.getX(), e.getY())) {
                    isDotClicked = true;
                    break;
                } else if (sh instanceof CloseBracket && ((CloseBracket) sh).getDot().containsPoint(e.getX(), e.getY())) {
                    isDotClicked = true;
                    break;
                } else if (sh instanceof GreaterThan && (((GreaterThan) sh).getDot1().containsPoint(e.getX(), e.getY())
                        || ((GreaterThan) sh).getDot2().containsPoint(e.getX(), e.getY())
                        || ((GreaterThan) sh).getDot3().containsPoint(e.getX(), e.getY()))) {
                    isDotClicked = true;
                    break;
                } else if (sh instanceof LessThan && (((LessThan) sh).getDot1().containsPoint(e.getX(), e.getY())
                        || ((LessThan) sh).getDot2().containsPoint(e.getX(), e.getY())
                        || ((LessThan) sh).getDot3().containsPoint(e.getX(), e.getY()))) {
                    isDotClicked = true;
                    break;
                } else if (sh instanceof AtTheRate && (((AtTheRate) sh).getDot1().containsPoint(e.getX(), e.getY())
                        || ((AtTheRate) sh).getDot2().containsPoint(e.getX(), e.getY())
                        || ((AtTheRate) sh).getDot3().containsPoint(e.getX(), e.getY())
                        || ((AtTheRate) sh).getDot4().containsPoint(e.getX(), e.getY()))) {
                    isDotClicked = true;
                    break;
                } else if (sh instanceof Hyphen && (((Hyphen) sh).getDot1().containsPoint(e.getX(), e.getY())
                        || ((Hyphen) sh).getDot2().containsPoint(e.getX(), e.getY()))) {
                    isDotClicked = true;
                    break;
                } else if (sh instanceof TwoBars && (((TwoBars) sh).getLeftBar().containsPoint(e.getX(), e.getY())
                        || ((TwoBars) sh).getRightBar().containsPoint(e.getX(), e.getY()))) {
                    isBarClicked = true;
                    break;
                } else {
                    isDotClicked = false;
                    isBarClicked = false;
                }

            }

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (firstDotClicked) {
            RightPanel.setOriginX(sourceX);
            RightPanel.setOriginY(sourceY);
            RightPanel.setDestinationX(e.getX());
            RightPanel.setDestinationY(e.getY());
            Frame.rightPanel.repaint();
        }

    }

    public void updateHashMap(Shapes firstShape, Shapes secondShape) {
        //Makes note of the shapes connected to one another
        if (firstShape.toString().contains("Model.OpenBracket"))
            compileFile.push('(', 1);
        if (firstShape.toString().contains("Model.LessThan"))
            compileFile.push('<', 1);
        if (firstShape.toString().contains("Model.CloseBracket"))
            compileFile.push(')', 1);
        if (firstShape.toString().contains("Model.GreaterThan"))
            compileFile.push('>', 1);
        if (secondShape.toString().contains("Model.CloseBracket"))
            compileFile.push(')', 1);
        if (secondShape.toString().contains("Model.GreaterThan"))
            compileFile.push('>', 1);
        if (secondShape.toString().contains("Model.OpenBracket"))
            compileFile.push('(', 1);
        if (secondShape.toString().contains("Model.LessThan"))
            compileFile.push('<', 1);
        if (firstShape.toString().contains("Model.TwoBars"))
            compileFile.push('|', 1);
        if (secondShape.toString().contains("Model.TwoBars"))
            compileFile.push('|', 1);
        if (firstShape.toString().contains("Model.AtTheRate"))
            compileFile.push('@', 1);
        if (secondShape.toString().contains("Model.AtTheRate"))
            compileFile.push('@', 1);

    }

}
