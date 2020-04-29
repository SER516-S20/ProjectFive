import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AddRemoveTab{
    JTabbedPane tab;
    public static void main(String[] args){
        AddRemoveTab ar = new AddRemoveTab();
    }

    public AddRemoveTab(){
        JFrame frame = new JFrame("Add Remove Tab Frame");
        tab = new JTabbedPane();
        frame.add(tab, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        JButton button = new JButton("Add Tab");
        button.addActionListener(new MyAction());
        panel.add(button);
        tab.add("Add Tab", panel);
        JPanel panel1 = new JPanel();
        JButton button1 = new JButton("Remove Tab");
        button1.addActionListener(new MyAction());
        panel1.add(button1);
        tab.add("Remove Tab", panel1);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public class MyAction implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String str = e.getActionCommand();
            if(str.equals("Add Tab")){
                String st = JOptionPane.showInputDialog(null, "Enter Tab Name.");
                if(!st.equals("")){
                    JPanel panel2 = new JPanel();
                    JLabel label = new JLabel("Your program is working successfully.");
                    panel2.add(label);
                    tab.add(st, panel2);
                }
            }
            else if(str.equals("Remove Tab")){
                tab.remove(tab.getTabCount()-1);
            }
        }
    }
}