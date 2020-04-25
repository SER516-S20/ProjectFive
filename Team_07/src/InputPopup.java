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
    JTextField name;
    final static Dimension DIMENSIONS_INPUT_POPUP = new Dimension(300, 110);
    Op op;

    InputPopup(MouseEvent mouseEvent) {
        super();
        op = (Op) mouseEvent.getSource();
        getContentPane().setBackground(Database.THEME_BLUE);
        setPreferredSize(DIMENSIONS_INPUT_POPUP);
        setUndecorated(true);
        setLocationRelativeTo(mouseEvent.getComponent());
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
        name.setBorder(BorderFactory.createLineBorder(Database.THEME_BLUE_DARK, 10));
        name.setBackground(Database.THEME_BLUE_DARK);
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
        ButtonCustom doneButton = new ButtonCustom("Done", Database.THEME_BLUE_DARK);
        doneButton.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        ListenersInputPopup.addDoneButtonListeners(doneButton, this);
        return doneButton;
    }

    private ButtonCustom getCancelButton() {
        ButtonCustom cancelButton = new ButtonCustom("Cancel", Database.THEME_BLUE_DARK);
        ListenersInputPopup.addCancelButtonListeners(cancelButton, this);
        return cancelButton;
    }

}
