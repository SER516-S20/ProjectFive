package View;

import Controller.Button;
import com.sun.jmx.snmp.SnmpStatusException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import Controller.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class creates a Frame and adds two JPanels to the frame.
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

    private void createRightpanel() {
        try {
            tabbedPane = new JTabbedPane();
            tabbedPane.setBounds(screenSize.width / 6, 0, 4 * screenSize.width / 4, screenSize.height);
            tabbedPane.setVisible(true);
            rightPanel = new RightPanel();
            tabbedPane.add("Home Tab", rightPanel);//-----------
            tabList.add(tabbedPane);//base
            panelList.add(rightPanel); //default case
            JButton button = new JButton("+ Tab");
            button.addActionListener(new MyAction());
            rightPanel.add(button);
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

            rightPanel = panelList.get(index);//map.get(sourceTabbedPane.getTitleAt(index));//update


            rightPanel.revalidate();


        }
    }
    public class MyAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            if (str.equals("+ Tab")) {
                String st = JOptionPane.showInputDialog(null, "Enter Tab Name.");
                if (!st.equals("")) {
                    JPanel panel2 = new JPanel();

                    RightPanel rp = new RightPanel();
                    //rightPanel = new RightPanel();
                    //rp.removeAll();
                    //rp.updateUI();

                    JLabel label = new JLabel("Tab No " + tabCount + " Created");
                    tabCount++;
                    panel2.add(label);
                    rp.add(label);
                    //rightPanel.add(label);
                    JTabbedPane tb = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT);
                    tb.setVisible(true);
                    tb.setBounds(screenSize.width / 6, 0, 4 * screenSize.width / 4, screenSize.height);
                    tb.revalidate();
                    tb.repaint();
                    tb.add(st,rp);
                    //tb.add(st,rightPanel);
                    tabList.add(tb);
                    tabbedPane.addTab(st,tb);
                    //tabbedPane.add(tb);
                    //tabbedPane.add(st, rp);
                    System.out.println("current rightpane-->" +rightPanel );
                    rightPanel=rp;
                    panelList.add(rp);
                    System.out.println("new panel--->"+rightPanel);
                    map.put(tb,rightPanel);
                }

            } else if (str.equals("Remove Tab")) {
                tabbedPane.remove(tab.getTabCount() - 1);
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

