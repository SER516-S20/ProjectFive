import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Custom JDialog used to get input when double click any operator
 *
 * @author Karandeep Singh Grewal
 * @since March 11, 2020
 */
public class InputPopup extends JDialog {
    final static Dimension DIMENSIONS_INPUT_POPUP = new Dimension(300, 110);
    JTextField name;
    Op op;

    InputPopup(MouseEvent e) {
        super();
        op = (Op) e.getSource();
        getContentPane().setBackground(Color.GRAY);
        setPreferredSize(DIMENSIONS_INPUT_POPUP);
        setUndecorated(true);
        setLocationRelativeTo(e.getComponent());
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

        this.add(getTextField());
        this.add(getDoneButton());
        this.add(getCancelButton());
        ListenersInputPopup.addCloseOnLostFocusListeners(this);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private JTextField getTextField() {
        name = new JTextField(op.getValue());
        name.setColumns(20);
        name.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10));
        name.setBackground(Color.BLUE);
        name.setForeground(Color.WHITE);
        name.setCaretColor(Color.WHITE);
        name.setEnabled(true);

        name.addActionListener(actionEvent -> {
            op.setValue(name.getText());
            dispose();
        });
        return name;
    }

    private ButtonCustom getDoneButton() {
        ButtonCustom doneButton = new ButtonCustom("Done", Color.DARK_GRAY);
        doneButton.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        ListenersInputPopup.addDoneButtonListeners(doneButton, this);
        return doneButton;
    }

    private ButtonCustom getCancelButton() {
        ButtonCustom cancelButton = new ButtonCustom("Cancel", Color.WHITE);
        cancelButton.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        ListenersInputPopup.addCancelButtonListeners(cancelButton, this);
        return cancelButton;
    }

}
