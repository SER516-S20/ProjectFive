import java.awt.*;

/**
 * Operator "@"
 *
 * @author Arvind
 * @author Karandeep Singh Grewal
 * @since March 13, 2020
 */
public class OpAt extends Op {
    static Color ORANGE = new Color(255, 149, 0);

    OpAt() {
        label = "@";
        opLabel.setText(label);
        color = ORANGE;
        packOperator();
        addToConnector("I", FactoryConnector.getOp("Dot", this));
        addToConnector("I", FactoryConnector.getOp("Dot", this));
        addToConnector("O", FactoryConnector.getOp("Dot", this));
        addToConnector("O", FactoryConnector.getOp("Dot", this));
    }
}
