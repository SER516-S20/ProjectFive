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
            ("(", ")", "<", ">", "@", "||", "-"));
    final Dimension DIMENSION_PANEL_LEFT = new Dimension(200, Database.SCREEN_SIZE.height);


    PanelLeft() {
        super();
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        setPreferredSize(DIMENSION_PANEL_LEFT);
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.gray));
        setBackground(Database.THEME_BLUE);
        addOperators();
    }

    /**
     * Add operators to the left panel
     */
    void addOperators() {
        for (String opName : PANEL_LEFT_OPERATORS) {
            Op op = (Op) add(FactoryOp.getOp(opName));
            ListenersPanelLeft.addShapeListeners(op);
        }
    }
}
