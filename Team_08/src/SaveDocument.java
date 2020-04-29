import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class adds save file functionality to
 * save the file from canvas.
 *
 * @author Kartik Mathpal
 * @version 1.0
 */

public class SaveDocument extends JMenuItem implements ActionListener {
    private static final long serialVersionUID = 1L;

    public SaveDocument(String label) {
        super(label);
    }

    private String fileName;

    @Override
    public void actionPerformed(ActionEvent e) {
        FileOutputStream fileOut = null;
        ObjectOutputStream out = null;
        
        try {
            JFileChooser chosenFile = new JFileChooser();
            int showSaveDialog = chosenFile.showSaveDialog(null);
            if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
                String FILE_EXT = ".ser";
                fileName = chosenFile.getSelectedFile().getAbsolutePath() + FILE_EXT;
            }
            fileOut = new FileOutputStream(new File(fileName));
            out = new ObjectOutputStream(fileOut);
            out.writeObject(RightPanel.getLines());
            out.writeObject(RightPanel.getRightPanelShapes());
            fileOut.flush();
        } catch (IOException i) {
            i.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
