import java.awt.*;

/**
 * Operator ">"
 *
 * @author Arvind
 * @author Karandeep Singh Grewal
 * @since March 13, 2020
 */
public class OpGreaterThan extends Op {
    final Color GREEN = new Color(52, 199, 89);

    OpGreaterThan() {
        label = ">";
        opLabel.setText(label);
        color = GREEN;
        packOperator();
        addToConnector("I", FactoryConnector.getOp("Dot", this));
        addToConnector("I", FactoryConnector.getOp("Dot", this));
        addToConnector("O", FactoryConnector.getOp("Dot", this));
    }
}
