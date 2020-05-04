package Model;

import Controller.LeftPanelMouseListener;
import View.Frame;
import View.LeftPanel;
import View.RightPanel;
import View.TopPanel;

import javax.swing.*;

import java.awt.*;

/**
 * This class consists of GUI for the shapes in left panel.
 * @author Amudhan Manisekaran
 * @version 1.1
 */

public class Button {
    public void addButtonsToLeftPanel(LeftPanel panel) {

       JButton openButton = new JButton(new ImageIcon("Team_08/src/ImageFiles/openbracket1.jpg"));
        openButton.setPreferredSize(new Dimension(150, 50));
        openButton.addActionListener(e -> {
            openButton.setName("openButton");
            resetButtonClicked(openButton);
        });
        panel.add(openButton);

        JButton closeButton = new JButton(new ImageIcon("Team_08/src/ImageFiles/closebracket1.jpg"));
        closeButton.setPreferredSize(new Dimension(150, 50));
        closeButton.addActionListener(e -> {
            closeButton.setName("closeButton");
            resetButtonClicked(closeButton);
        });
        panel.add(closeButton);

        JButton lessThanButton = new JButton(new ImageIcon("Team_08/src/ImageFiles/lessthan1.jpg"));
        lessThanButton.setPreferredSize(new Dimension(150, 50));
        lessThanButton.addActionListener(e -> {
            lessThanButton.setName("lessThanButton");
            resetButtonClicked(lessThanButton);
        });
        panel.add(lessThanButton);

        JButton greaterThanButton = new JButton(new ImageIcon("Team_08/src/ImageFiles/greaterthan1.jpg"));
        greaterThanButton.setPreferredSize(new Dimension(150, 50));
        greaterThanButton.addActionListener(e -> {
            greaterThanButton.setName("greaterThanButton");
            resetButtonClicked(greaterThanButton);
        });
        panel.add(greaterThanButton);

        JButton atTheRateButton = new JButton(new ImageIcon("Team_08/src/ImageFiles/attherate1.jpg"));
        atTheRateButton.setPreferredSize(new Dimension(150, 50));
        atTheRateButton.addActionListener(e -> {
            atTheRateButton.setName("atTheRateButton");
            resetButtonClicked(atTheRateButton);
        });
        panel.add(atTheRateButton);

        JButton twoBarButton = new JButton(new ImageIcon("Team_08/src/ImageFiles/twobars1.jpg"));
        twoBarButton.setPreferredSize(new Dimension(150, 50));
        twoBarButton.addActionListener(e -> {
            twoBarButton.setName("twoBarButton");
            resetButtonClicked(twoBarButton);
        });
        panel.add(twoBarButton);

        JButton hyphenButton = new JButton(new ImageIcon("Team_08/src/ImageFiles/hyphen1.jpg"));
        hyphenButton.setPreferredSize(new Dimension(150, 50));
        hyphenButton.addActionListener(e -> {
            hyphenButton.setName("hyphenButton");
            resetButtonClicked(hyphenButton);
        });
        panel.add(hyphenButton);

        JButton poundButton = new JButton("#", new ImageIcon("Team_08/src/ImageFiles/pound1.jpg"));
        poundButton.setPreferredSize(new Dimension(150, 50));
        poundButton.addActionListener(new Frame.MyAction());
        poundButton.addActionListener(e -> {
            poundButton.setName("poundButton");
            resetButtonClicked(poundButton);

        });

        panel.add(poundButton);

    }

    public void addButtonsToTopPanel(TopPanel panel) {

        JButton openButton = new JButton(new ImageIcon("Team_08/src/ImageFiles/openbracket2.jpg"));
        openButton.setPreferredSize(new Dimension(100, 50));
        openButton.addActionListener(e -> {
            openButton.setName("openButton");
            resetButtonClicked(openButton);
        });
        panel.add(openButton);

        JButton closeButton = new JButton(new ImageIcon("Team_08/src/ImageFiles/closebracket2.jpg"));
        closeButton.setPreferredSize(new Dimension(100, 50));
        closeButton.addActionListener(e -> {
            closeButton.setName("closeButton");
            resetButtonClicked(closeButton);
        });
        panel.add(closeButton);

        JButton lessThanButton = new JButton(new ImageIcon("Team_08/src/ImageFiles/lessthan2.jpg"));
        lessThanButton.setPreferredSize(new Dimension(100, 50));
        lessThanButton.addActionListener(e -> {
            lessThanButton.setName("lessThanButton");
            resetButtonClicked(lessThanButton);
        });
        panel.add(lessThanButton);

        JButton greaterThanButton = new JButton(new ImageIcon("Team_08/src/ImageFiles/greaterthan2.jpg"));
        greaterThanButton.setPreferredSize(new Dimension(100, 50));
        greaterThanButton.addActionListener(e -> {
            greaterThanButton.setName("greaterThanButton");
            resetButtonClicked(greaterThanButton);
        });
        panel.add(greaterThanButton);

        JButton atTheRateButton = new JButton(new ImageIcon("Team_08/src/ImageFiles/attherate2.jpg"));
        atTheRateButton.setPreferredSize(new Dimension(100, 50));
        atTheRateButton.addActionListener(e -> {
            atTheRateButton.setName("atTheRateButton");
            resetButtonClicked(atTheRateButton);
        });
        panel.add(atTheRateButton);

        JButton twoBarButton = new JButton(new ImageIcon("Team_08/src/ImageFiles/twobars2.jpg"));
        twoBarButton.setPreferredSize(new Dimension(100, 50));
        twoBarButton.addActionListener(e -> {
            twoBarButton.setName("twoBarButton");
            resetButtonClicked(twoBarButton);
        });
        panel.add(twoBarButton);

        JButton hyphenButton = new JButton(new ImageIcon("Team_08/src/ImageFiles/hyphen2.jpg"));
        hyphenButton.setPreferredSize(new Dimension(100, 50));
        hyphenButton.addActionListener(e -> {
            hyphenButton.setName("hyphenButton");
            resetButtonClicked(hyphenButton);
        });
        panel.add(hyphenButton);

        JButton poundButton = new JButton("#", new ImageIcon("Team_08/src/ImageFiles/pound2.jpg"));
        poundButton.setPreferredSize(new Dimension(100, 50));
        poundButton.addActionListener(new Frame.MyAction());
        poundButton.addActionListener(e -> {
            poundButton.setName("poundButton");
            resetButtonClicked(poundButton);

        });

        panel.add(poundButton);

    }

    private void createTab(){
        System.out.println("Clicked to create new tab");
        RightPanel rp = new RightPanel();

    }
    private void resetButtonClicked(JButton button) {
        System.out.println(button.getName() + " clicked by the user!!!");
        switch (button.getName()) {
            case "poundButton":
                LeftPanelMouseListener.setIsPoundButtonClicked(Boolean.TRUE);
                LeftPanelMouseListener.setOpenBracketClicked(Boolean.FALSE);
                LeftPanelMouseListener.setCloseBracketClicked(Boolean.FALSE);
                LeftPanelMouseListener.setLessThanClicked(Boolean.FALSE);
                LeftPanelMouseListener.setGreaterThanClicked(Boolean.FALSE);
                LeftPanelMouseListener.setAtTheRateClicked(Boolean.FALSE);
                LeftPanelMouseListener.setTwoBarsClicked(Boolean.FALSE);
                LeftPanelMouseListener.setHyphenClicked(Boolean.FALSE);
                break;
            case "openButton":
                LeftPanelMouseListener.setOpenBracketClicked(Boolean.TRUE);
                LeftPanelMouseListener.setCloseBracketClicked(Boolean.FALSE);
                LeftPanelMouseListener.setLessThanClicked(Boolean.FALSE);
                LeftPanelMouseListener.setGreaterThanClicked(Boolean.FALSE);
                LeftPanelMouseListener.setAtTheRateClicked(Boolean.FALSE);
                LeftPanelMouseListener.setTwoBarsClicked(Boolean.FALSE);
                LeftPanelMouseListener.setHyphenClicked(Boolean.FALSE);
                LeftPanelMouseListener.setIsPoundButtonClicked(Boolean.FALSE);
                break;
            case "closeButton":
                LeftPanelMouseListener.setCloseBracketClicked(Boolean.TRUE);
                LeftPanelMouseListener.setOpenBracketClicked(Boolean.FALSE);
                LeftPanelMouseListener.setLessThanClicked(Boolean.FALSE);
                LeftPanelMouseListener.setGreaterThanClicked(Boolean.FALSE);
                LeftPanelMouseListener.setAtTheRateClicked(Boolean.FALSE);
                LeftPanelMouseListener.setTwoBarsClicked(Boolean.FALSE);
                LeftPanelMouseListener.setHyphenClicked(Boolean.FALSE);
                break;
            case "lessThanButton":
                LeftPanelMouseListener.setLessThanClicked(Boolean.TRUE);
                LeftPanelMouseListener.setCloseBracketClicked(Boolean.FALSE);
                LeftPanelMouseListener.setOpenBracketClicked(Boolean.FALSE);
                LeftPanelMouseListener.setGreaterThanClicked(Boolean.FALSE);
                LeftPanelMouseListener.setAtTheRateClicked(Boolean.FALSE);
                LeftPanelMouseListener.setTwoBarsClicked(Boolean.FALSE);
                LeftPanelMouseListener.setHyphenClicked(Boolean.FALSE);
                LeftPanelMouseListener.setIsPoundButtonClicked(Boolean.FALSE);
                break;
            case "greaterThanButton":
                LeftPanelMouseListener.setGreaterThanClicked(Boolean.TRUE);
                LeftPanelMouseListener.setCloseBracketClicked(Boolean.FALSE);
                LeftPanelMouseListener.setOpenBracketClicked(Boolean.FALSE);
                LeftPanelMouseListener.setLessThanClicked(Boolean.FALSE);
                LeftPanelMouseListener.setAtTheRateClicked(Boolean.FALSE);
                LeftPanelMouseListener.setTwoBarsClicked(Boolean.FALSE);
                LeftPanelMouseListener.setHyphenClicked(Boolean.FALSE);
                LeftPanelMouseListener.setIsPoundButtonClicked(Boolean.FALSE);
                break;
            case "atTheRateButton":
                LeftPanelMouseListener.setAtTheRateClicked(Boolean.TRUE);
                LeftPanelMouseListener.setCloseBracketClicked(Boolean.FALSE);
                LeftPanelMouseListener.setOpenBracketClicked(Boolean.FALSE);
                LeftPanelMouseListener.setLessThanClicked(Boolean.FALSE);
                LeftPanelMouseListener.setGreaterThanClicked(Boolean.FALSE);
                LeftPanelMouseListener.setTwoBarsClicked(Boolean.FALSE);
                LeftPanelMouseListener.setHyphenClicked(Boolean.FALSE);
                LeftPanelMouseListener.setIsPoundButtonClicked(Boolean.FALSE);
                break;
            case "twoBarButton":
                LeftPanelMouseListener.setTwoBarsClicked(Boolean.TRUE);
                LeftPanelMouseListener.setCloseBracketClicked(Boolean.FALSE);
                LeftPanelMouseListener.setOpenBracketClicked(Boolean.FALSE);
                LeftPanelMouseListener.setLessThanClicked(Boolean.FALSE);
                LeftPanelMouseListener.setGreaterThanClicked(Boolean.FALSE);
                LeftPanelMouseListener.setAtTheRateClicked(Boolean.FALSE);
                LeftPanelMouseListener.setHyphenClicked(Boolean.FALSE);
                LeftPanelMouseListener.setIsPoundButtonClicked(Boolean.FALSE);
                break;
            case "hyphenButton":
                LeftPanelMouseListener.setHyphenClicked(Boolean.TRUE);
                LeftPanelMouseListener.setCloseBracketClicked(Boolean.FALSE);
                LeftPanelMouseListener.setOpenBracketClicked(Boolean.FALSE);
                LeftPanelMouseListener.setLessThanClicked(Boolean.FALSE);
                LeftPanelMouseListener.setGreaterThanClicked(Boolean.FALSE);
                LeftPanelMouseListener.setAtTheRateClicked(Boolean.FALSE);
                LeftPanelMouseListener.setTwoBarsClicked(Boolean.FALSE);
                LeftPanelMouseListener.setIsPoundButtonClicked(Boolean.FALSE);
                break;
            default:
                System.out.println("Wrong Input Entry From User!!!!!");
                break;
        }
    }
}
