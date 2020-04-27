import java.awt.*;

/**
 * Operator "#"
 *
 * @author Aditya Bajaj
 * @since April 26, 2020
 */
public class OpHash extends Op {
    final Color PINK = new Color(255, 45, 85);

    OpHash() {
        label = "#";
        opLabel.setText(label);
        color = Color.yellow;
        packOperator();
        addToConnector("I", FactoryConnector.getOp("Dot", this));
        addToConnector("O", FactoryConnector.getOp("Dot", this));
    }
}
