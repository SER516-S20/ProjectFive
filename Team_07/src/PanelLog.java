import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.util.Calendar;

/**
 * Operator "-"
 *
 * @author Karandeep Singh Grewal
 * @since May 3, 2020
 */

public class PanelLog{
    public JScrollPane PANEL_LOG_SCROLLPANE;
    static JTextPane panelLogText;

    PanelLog() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        panelLogText = new JTextPane();
        PANEL_LOG_SCROLLPANE = new JScrollPane(panelLogText);
        PANEL_LOG_SCROLLPANE.setPreferredSize(new Dimension(300,200));
        panelLogText.setEditable(false);
        PANEL_LOG_SCROLLPANE.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    public static void logString(String string,Color color)
    {
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setFontFamily(attributeSet, "Monospace");
        panelLogText.setCharacterAttributes(attributeSet, true);
        StyleConstants.setForeground(attributeSet, Color.GRAY);
        Document doc = panelLogText.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), "\n"+Calendar.getInstance().getTime().toString()+":  ", attributeSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        StyleConstants.setBold(attributeSet, true);
        StyleConstants.setForeground(attributeSet, color);
        try {
            doc.insertString(doc.getLength(), string, attributeSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }


}
