package Model;

import View.RightPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

/**
 * This class creates a New tab in the canvas.
 *
 * @author Sayali Tanawade
 * @version 1.0
 */
public class NewTab extends JMenuItem implements ActionListener {

    public NewTab(String nEWSPACE) {
        super(nEWSPACE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Testing new tab functionality");
        JComponent rightPanel = new RightPanel();
        JTabbedPane tabbedPane = new JTabbedPane();
        rightPanel.setVisible(true);
        tabbedPane.addTab("New Tab", rightPanel);
        this.setVisible(true);
    }
}