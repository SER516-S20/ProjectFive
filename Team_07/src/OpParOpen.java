import java.awt.*;

/**
 * Operator "("
 *
 * @author Arvind
 * @author Karandeep Singh Grewal
 * @since March 13, 2020
 */
public class OpParOpen extends Op {
    final Color BLUE = new Color(0, 122, 255);

    OpParOpen() {
        label = "(";
        opLabel.setText(label);
        color = BLUE;
        packOperator();
        addToConnector("O", FactoryConnector.getOp("Dot", this));
    }
}
