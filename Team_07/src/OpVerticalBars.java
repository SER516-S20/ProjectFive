import java.awt.*;

/**
 * Operator "||"
 *
 * @author Arvind
 * @author Karandeep Singh Grewal
 * @since March 13, 2020
 */
public class OpVerticalBars extends Op {
    final Color TEAL = new Color(90, 200, 250);

    OpVerticalBars() {
        label = "||";
        opLabel.setText(label);
        color = TEAL;
        packOperator();
        addToConnector("I", FactoryConnector.getOp("Bar", this));
        addToConnector("O", FactoryConnector.getOp("Bar", this));
    }

}
