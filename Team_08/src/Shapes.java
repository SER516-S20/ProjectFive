import java.awt.Graphics;

/**
 * This is an abstract class where all the shapes are declared.
 *
 * @author Anusha Singh
 * @version 1.0
 */

public abstract class Shapes {

    public abstract void drawShape(Graphics graphic);

    public abstract boolean containsPoint(int x, int y);

    public abstract int getX();

    public abstract void setX(int x);

    public abstract int getY();

    public abstract void setY(int y);


}
