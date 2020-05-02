package Controller;
import View.RightPanel;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Saves all the Symbols in a text file
 * on successful compilation else translation fails.
 *
 * @author Kartik Mathpal
 * @version 1.0
 */
public class Translate extends JMenuItem implements ActionListener {

    public Translate(String label) {
        super(label);
    }

    private static String fileName;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Translating the FRAME!!!!!");
        save();
        System.out.println("Translation Complete!!!!!");
    }

    public static void save() {
        if(CompileFile.isSuccessful()){
            try {
                JFileChooser chosenFile = new JFileChooser();
                int showSaveDialog = chosenFile.showSaveDialog(null);
                if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
                    String FILE_EXT = ".txt";
                    fileName = chosenFile.getSelectedFile().getAbsolutePath() + FILE_EXT;
                }
                Files.write(Paths.get(fileName), CompileFile.getCharMap().getBytes());
                infoBox("Translation Successful","Translation Success!!!");
            } catch (IOException i) {
                i.printStackTrace();
            }
        }else{
            infoBox("<<<<Check if the compilation was successful>>>>","Translation Failed!!!");
        }

    }

    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }


}
