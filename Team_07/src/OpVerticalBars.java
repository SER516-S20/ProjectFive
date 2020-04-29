import java.awt.*;

/**
 * Operator "||"
 *
 * @author Arvind
 * @author Karandeep Singh Grewal
 * @since March 13, 2020
 */
public class OpVerticalBars extends Op {
    final Color RED = new Color(255, 59, 48);

    OpVerticalBars() {
        label = "||";
        opLabel.setText(label);
        color = RED;
        packOperator();
        addToConnector("I", FactoryConnector.getOp("Bar", this));
        addToConnector("O", FactoryConnector.getOp("Bar", this));
    }

}
