import java.awt.*;

/**
 * Operator "@"
 *
 * @author Arvind
 * @author Karandeep Singh Grewal
 * @since March 13, 2020
 */
public class OpAt extends Op {
    final Color PINK = new Color(255, 45, 85);

    OpAt() {
        label = "@";
        opLabel.setText(label);
        color = PINK;
        packOperator();
        addToConnector("I", FactoryConnector.getOp("Dot", this));
        addToConnector("I", FactoryConnector.getOp("Dot", this));
        addToConnector("O", FactoryConnector.getOp("Dot", this));
        addToConnector("O", FactoryConnector.getOp("Dot", this));
    }
}
