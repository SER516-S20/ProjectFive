package frame;

import shape.DefaultShapes;

/**
 * @author srinivasan sundar
 * @since 01/26/2020
 * @version 1.0
 */
public class Main {
    
   public static Frame frame;
    
    public static void main(String[] args) {
        frame = Frame.getInstance();
        frame.setVisible(true);  
        new DefaultShapes().addDefaultShapes();
    }

}
