import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Left Panel that contains the operators
 *
 * @author Arvind
 * @author Karandeep Singh Grewal
 * @since March 10, 2020
 */
public class PanelLeft extends JPanel {
    final static List<String> PANEL_LEFT_OPERATORS = new ArrayList<>(Arrays.asList
            ("(", ")", "<", ">", "@", "||", "-","#"));


    PanelLeft() {
        super();
        add(Box.createRigidArea(new Dimension(5, 50)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMinimumSize(new Dimension(200,400));
        setPreferredSize(new Dimension(200,530));
        setBackground(Color.DARK_GRAY);
        addOperators();
    }

    /**
     * Add operators to the left panel
     */
    void addOperators() {
        for (String opName : PANEL_LEFT_OPERATORS) {
            Op op = (Op) add(FactoryOp.getOp(opName));
            add(Box.createRigidArea(new Dimension(5, 10)));
            ListenersPanelLeft.addShapeListeners(op);
        }
    }
}
