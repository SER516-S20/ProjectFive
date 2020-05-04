import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import javax.swing.text.Document;

/**
 * Operator "-"
 *
 * @author Karandeep Singh Grewal
 * @since May 3, 2020
 */

public class PanelLog{
    public JScrollPane scrollPanelLog;
    static JTextPane panelLogText;

    PanelLog() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        panelLogText = new JTextPane();
        scrollPanelLog = new JScrollPane(panelLogText);
        scrollPanelLog.setPreferredSize(new Dimension(200,200));
        panelLogText.setEditable(false);
        scrollPanelLog.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public static void logString(String string,Color color)
    {
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setFontFamily(attributeSet, "Monospace");
        panelLogText.setCharacterAttributes(attributeSet, true);
        Document doc = panelLogText.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), "\n"+Calendar.getInstance().getTime().toString()+":\t", attributeSet);
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
