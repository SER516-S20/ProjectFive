package Model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.*;

import java.io.Serializable;

/**
 * This class consists of Model.AtTheRate shape with two input and two output dots.
 *
 * @author Sandya
 * @version 1.0
 */
public class AtTheRate extends Shapes implements Serializable {

    private static final long serialVersionUID = 1L;
    private int x, y;
    private Shapes dot1 = null, dot2 = null, dot3= null, dot4= null;
    private Shape attherate = null;
    private boolean isLineDrawnDot1 = false, isLineDrawnDot2 = false, isLineDrawnDot3 = false, isLineDrawnDot4 = false;
    public AtTheRate(int x, int y) {
        int OFFSET = 50;
        this.x = x - OFFSET;
        this.y = y - OFFSET;
    }

    @Override
    public void drawShape(Graphics graphic) {

        attherate = new Rectangle2D.Double(x, y, 200, 100);
        Graphics2D g2 = (Graphics2D) graphic;
        dot1 = new Dot(x, y);
        dot1 = new Dot(x + 30, y + 20);
        dot1.drawShape(g2);
        dot2 = new Dot(x + 165, y + 20);
        dot2.drawShape(g2);
        dot3 = new Dot(x + 165, y + 80);
        dot3.drawShape(g2);
        dot4 = new Dot(x + 30, y + 80);
        dot4.drawShape(g2);

        Font font = new Font("Serif", Font.PLAIN, 40);
        g2.setFont(font);
        g2.drawString("@", x + 80, y + 65);

        g2.draw(attherate);

    }

    @Override
    public int getX() {

        return x;
    }

    @Override
    public void setX(int x) {

        this.x = x -50 ;
    }

    @Override
    public int getY() {

        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y + 50;
    }

    public boolean isLineDrawnDot1() {

        return isLineDrawnDot1;
    }

    public void setLineDrawnDot1(boolean isLineDrawnDot1) {

        this.isLineDrawnDot1 = isLineDrawnDot1;
    }

    public boolean isLineDrawnDot2() {

        return isLineDrawnDot2;
    }

    public void setLineDrawnDot2(boolean isLineDrawnDot2) {

        this.isLineDrawnDot2 = isLineDrawnDot2;
    }

    public boolean isLineDrawnDot3() {

        return isLineDrawnDot3;
    }

    public void setLineDrawnDot3(boolean isLineDrawnDot3) {

        this.isLineDrawnDot3 = isLineDrawnDot3;
    }

    public boolean isLineDrawnDot4() {

        return isLineDrawnDot4;
    }

    public void setLineDrawnDot4(boolean isLineDrawnDot3) {

        this.isLineDrawnDot4 = isLineDrawnDot4;
    }

    @Override
    public boolean containsPoint(int x, int y) {

        return attherate.contains(x, y);
    }
    
    public Shapes getDot1() {

        return dot1;
    }

    public Shapes getDot2() {

        return dot2;
    }

    public Shapes getDot3() {

        return dot3;
    }

    public Shapes getDot4() {

        return dot4;
    }

}
