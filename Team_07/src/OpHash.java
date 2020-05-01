import java.awt.*;

/**
 * Operator "#"
 *
 * @author Aditya Bajaj
 * @since April 26, 2020
 */
public class OpHash extends Op {

    OpHash() {
        label = "#";
        opLabel.setText(label);
        color = Color.GRAY;
        packOperator();
        addToConnector("I", FactoryConnector.getOp("Dot", this));
        addToConnector("O", FactoryConnector.getOp("Dot", this));
    }
}
