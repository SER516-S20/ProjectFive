package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import Model.Button;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class creates a View.Frame and adds two JPanels to the frame.
 *
 * @author Kartik Mathpal
 * @version 1.0
 */
public class Frame extends JFrame {
    private static final long serialVersionUID = 1L;
    public  static RightPanel rightPanel;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static JTabbedPane tabbedPane;
    static List<RightPanel> panelList = new ArrayList<>();
    static List<JTabbedPane> tabList = new ArrayList<>();
    static Map<JTabbedPane,RightPanel> map = new HashMap<>();
    public JButton button ;

    static int tabCount=0;
    JTabbedPane tab;

    public Frame() {
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        String TITLE = "Project 4 - Team 8";
        setTitle(TITLE);
        setBackground(Color.BLACK);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createLeftPanel() {
        try {
            LeftPanel leftPanel = new LeftPanel();
            Button button = new Button();

            button.addButtonsToLeftPanel(leftPanel);
            leftPanel.setBounds(0, 0, screenSize.width / 6, screenSize.height);
            leftPanel.setVisible(true);
            this.add(leftPanel);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createRightpanel() {
        try {
            tabbedPane = new JTabbedPane();
            tabbedPane.setBounds(screenSize.width / 6, 0, 4 * screenSize.width / 4, screenSize.height);
            tabbedPane.setVisible(true);
            rightPanel = new RightPanel();
            tabbedPane.add("Home Tab", rightPanel);//-----------
            tabList.add(tabbedPane);//base
            panelList.add(rightPanel); //default case
            //JButton button = new JButton("+ Tab");
            //button.addActionListener(new MyAction());
            //rightPanel.add(button);
            this.add(tabbedPane);
            tabbedPane.addChangeListener(new MyChange());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
public class MyChange implements ChangeListener{

    @Override
    public void stateChanged(ChangeEvent e) {
        JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
        int index = sourceTabbedPane.getSelectedIndex();
        System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
        //rightPanel = (View.RightPanel) panelList.get(index);
        //rightPanel.updateUI();
        //rightPanel.repaint();
        //tabbedPane.setSelectedComponent((JTabbedPane)sourceTabbedPane.getSelectedComponent());
        //rightPanel.revalidate();
        //rightPanel.updateUI();
        //rightPanel.repaint();
        //tabbedPane.updateUI();
        //tabbedPane = (JTabbedPane)sourceTabbedPane.getTabComponentAt(index);;
        //tabbedPane.add(panelList.get(index));
        rightPanel=panelList.get(index);

    }
}
    public static class MyAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            if (str.equals("#")) {
                String st = JOptionPane.showInputDialog(null, "Enter Tab Name.");
                if (!st.equals("")) {
                    RightPanel rp = new RightPanel();
                    tabCount++;
                    JTabbedPane tb = new JTabbedPane(JTabbedPane.TOP);
                    tb.setVisible(true);
                    //tb.setBounds(screenSize.width / 6, 0, 4 * screenSize.width / 4, screenSize.height);
                    tb.revalidate();
                    tb.repaint();
                    tb.add(st,rp);
                    tabList.add(tb);
                    tabbedPane.addTab(st,tb);
                    //System.out.println("current rightpane-->" +rightPanel );
                    rightPanel=rp;
                    panelList.add(rp);
                    //tabbedPane.add(rp); //new panel
                    //System.out.println("new panel--->"+rightPanel);
                    map.put(tb,rightPanel);
                }

            } else if (str.equals("Remove Tab")) {
                //tabbedPane.remove(tab.getTabCount() - 1);
            }
        }

    }

    public void createMenu() {
        this.setJMenuBar(new MenuBar());
    }

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.createLeftPanel();
        frame.createRightpanel();
        frame.createMenu();
        frame.setVisible(true);




    }

}
