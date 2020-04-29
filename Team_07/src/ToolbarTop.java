import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Top JToolbar
 * Contains the Operators
 *
 * @author Aditya Bajaj
 * @since April 26, 2020
 */
public class ToolbarTop extends JToolBar {
    final static List<String> OPERATORS = new ArrayList<>(Arrays.asList
            ("(", ")", "<", ">", "@", "||", "-", "#"));
    final Dimension DIMENSION_PANEL_TOP = new Dimension(Database.SCREEN_SIZE.width, 100);


    ToolbarTop() {
        super();
        setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20));
        setPreferredSize(DIMENSION_PANEL_TOP);
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.white));
        setBackground(Database.GRAY);
        addOperators();
    }

    /**
     * Add operators to the jTop Toolbar
     */
    void addOperators() {
        for (String opName : OPERATORS) {
            Op op = (Op) add(FactoryOp.getOp(opName));
            ListenersToolbarTop.addShapeListeners(op);

        }
    }

}
