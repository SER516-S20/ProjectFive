package Model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import java.io.Serializable;

/**
 * This class consists of Model.AtTheRate shape with one output dot.
 *
 * @author Anusha Singh
 * @version 1.0
 */

public class OpenBracket extends Shapes implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int OFFSET = 50;
    private int x, y;
    private Shape openbracket = null;
    private Shapes dot = null;
    private boolean isLineDrawn = false;

    public OpenBracket(int x, int y) {
        this.x = x - OFFSET;
        this.y = y - OFFSET;
    }

    public Shapes getDot() {
        return dot;
    }

    @Override
    public void drawShape(Graphics graphic) {

        openbracket = new Rectangle2D.Double(x, y, 200, 100);
        Graphics2D graphics2 = (Graphics2D) graphic;
        dot = new Dot(x + 165, y + 100 - 50);
        dot.drawShape(graphics2);
        
        Font font = new Font("Serif", Font.PLAIN, 40);
        graphics2.setFont(font);
        graphics2.drawString("(", x + 105, y + 60);
        graphics2.draw(openbracket);

    }

    public boolean containsPoint(int x, int y) {
        return openbracket.contains(x, y);
    }

    @Override
    public int getX() {
        return (int) x;
    }

    @Override
    public void setX(int x) {
        this.x = x - OFFSET;
    }

    @Override
    public int getY() {
        return (int) y;
    }

    @Override
    public void setY(int y) {
        this.y = y - OFFSET;
    }

    public boolean isLineDrawn() {
        return isLineDrawn;
    }

    public void setLineDrawn(boolean isLineDrawn) {
        this.isLineDrawn = isLineDrawn;
    }

}
