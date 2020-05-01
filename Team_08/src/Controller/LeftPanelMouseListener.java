package Controller;

import Model.*;
import View.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Adding mouse listeners for providing mouse click functionality
 * to click buttons on the left panel.
 *
 * @author Sayali Tanawade
 * @version 1.0
 */
public class LeftPanelMouseListener extends MouseAdapter {

    /*
    * */
    private static boolean isPoundButtonClicked = false; //-----

    private static boolean isOpenBracketClicked = false;
    private static boolean isCloseBracketClicked = false;
    private static boolean isLessThanClicked = false;
    private static boolean isGreaterThanClicked = false;
    private static boolean isAtTheRateClicked = false;
    private static boolean isTwoBarsClicked = false;
    private static boolean isHyphenClicked = false;

    public static boolean isPoundButtonClicked(){
        return  isPoundButtonClicked;
    }

    public static void setIsPoundButtonClicked(boolean isPoundButtonClicked){
        LeftPanelMouseListener.isPoundButtonClicked = isPoundButtonClicked;
    }
    public static boolean isOpenBracketClicked() {
        return isOpenBracketClicked;
    }

    public static void setOpenBracketClicked(boolean isOpenBracketClicked) {
        LeftPanelMouseListener.isOpenBracketClicked = isOpenBracketClicked;
    }

    public static boolean isCloseBracketClicked() {
        return isCloseBracketClicked;
    }

    public static void setCloseBracketClicked(boolean isCloseBracketClicked) {
        LeftPanelMouseListener.isCloseBracketClicked = isCloseBracketClicked;
    }

    public static boolean isLessThanClicked() {
        return isLessThanClicked;
    }

    public static void setLessThanClicked(boolean isLessThanClicked) {
        LeftPanelMouseListener.isLessThanClicked = isLessThanClicked;
    }

    public static boolean isGreaterThanClicked() {
        return isGreaterThanClicked;
    }

    public static void setGreaterThanClicked(boolean isGreaterThanClicked) {
        LeftPanelMouseListener.isGreaterThanClicked = isGreaterThanClicked;
    }


    public static boolean isAtTheRateClicked() {
        return isAtTheRateClicked;
    }

    public static void setAtTheRateClicked(boolean isAtTheRateClicked) {
        LeftPanelMouseListener.isAtTheRateClicked = isAtTheRateClicked;
    }

    public static boolean isTwoBarsClicked() {
        return isTwoBarsClicked;
    }

    public static void setTwoBarsClicked(boolean isTwoBarsClicked) {
        LeftPanelMouseListener.isTwoBarsClicked = isTwoBarsClicked;
    }

    public static boolean isHyphenClicked() {
        return isHyphenClicked;
    }

    public static void setHyphenClicked(boolean isHyphenClicked) {
        LeftPanelMouseListener.isHyphenClicked = isHyphenClicked;
    }

    /**
     * Overridden method to add mouse click event handler.
     * Used to track which shape has been clicked on the left panel,
     * so that only that shape can be created on the right panel.
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        for (Shapes next : LeftPanel.leftPanelShapes) {
            if (next instanceof OpenBracket) {
                if (next.containsPoint(x, y)) {
                    markIsClickedTrue(ShapesEnum.OPENBRACKET);
                }
            } else if (next instanceof CloseBracket) {
                if (next.containsPoint(x, y)) {
                    markIsClickedTrue(ShapesEnum.CLOSEBRACKET);
                }
            } else if (next instanceof LessThan) {
                if (next.containsPoint(x, y)) {
                    markIsClickedTrue(ShapesEnum.LESSTHAN);
                }
            } else if (next instanceof GreaterThan) {
                if (next.containsPoint(x, y)) {
                    markIsClickedTrue(ShapesEnum.GREATERTHAN);
                }
            } else if (next instanceof AtTheRate) {
                if (next.containsPoint(x, y)) {
                    markIsClickedTrue(ShapesEnum.ATTHERATE);
                }
            } else if (next instanceof TwoBars) {
                if (next.containsPoint(x, y)) {
                    markIsClickedTrue(ShapesEnum.TWOBARS);
                }
            } else if (next instanceof Hyphen) {
                if (next.containsPoint(x, y)) {
                    markIsClickedTrue(ShapesEnum.HYPHEN);
                }
            }
        }
    }

    /**
     * Instantiates or uses the created instance of different shapes' class
     * and sets isClicked value as true for the shape which was clicked.
     */
    public static void markIsClickedTrue(ShapesEnum shape) {
        try {
            if(ShapesEnum.POUND == shape){
                setIsPoundButtonClicked(true);
                setOpenBracketClicked(false);
                setCloseBracketClicked(false);
                setLessThanClicked(false);
                setGreaterThanClicked(false);
                setAtTheRateClicked(false);
                setTwoBarsClicked(false);
                setHyphenClicked(false);
            }
            if (ShapesEnum.OPENBRACKET == shape) {
                setOpenBracketClicked(true);
                setCloseBracketClicked(false);
                setLessThanClicked(false);
                setGreaterThanClicked(false);
                setAtTheRateClicked(false);
                setTwoBarsClicked(false);
                setHyphenClicked(false);
            }
            if (ShapesEnum.CLOSEBRACKET == shape) {
                setOpenBracketClicked(false);
                setCloseBracketClicked(true);
                setLessThanClicked(false);
                setGreaterThanClicked(false);
                setAtTheRateClicked(false);
                setTwoBarsClicked(false);
                setHyphenClicked(false);
            }
            if (ShapesEnum.LESSTHAN == shape) {
                setOpenBracketClicked(false);
                setCloseBracketClicked(false);
                setLessThanClicked(true);
                setGreaterThanClicked(false);
                setAtTheRateClicked(false);
                setTwoBarsClicked(false);
                setHyphenClicked(false);
            }
            if (ShapesEnum.GREATERTHAN == shape) {
                setOpenBracketClicked(false);
                setCloseBracketClicked(false);
                setLessThanClicked(false);
                setGreaterThanClicked(true);
                setAtTheRateClicked(false);
                setTwoBarsClicked(false);
                setHyphenClicked(false);
            }
            if (ShapesEnum.ATTHERATE == shape) {
                setOpenBracketClicked(false);
                setCloseBracketClicked(false);
                setLessThanClicked(false);
                setGreaterThanClicked(false);
                setAtTheRateClicked(true);
                setTwoBarsClicked(false);
                setHyphenClicked(false);
            }
            if (ShapesEnum.TWOBARS == shape) {
                setOpenBracketClicked(false);
                setCloseBracketClicked(false);
                setLessThanClicked(false);
                setGreaterThanClicked(false);
                setAtTheRateClicked(false);
                setTwoBarsClicked(true);
                setHyphenClicked(false);
            }
            if (ShapesEnum.HYPHEN == shape) {
                setOpenBracketClicked(false);
                setCloseBracketClicked(false);
                setLessThanClicked(false);
                setGreaterThanClicked(false);
                setAtTheRateClicked(false);
                setTwoBarsClicked(false);
                setHyphenClicked(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * returns the shape that is clicked.
     */
    public static ShapesEnum getSelectedShape() {
        if(isPoundButtonClicked()){
            return ShapesEnum.POUND;
        }else if (isOpenBracketClicked()) {
            return ShapesEnum.OPENBRACKET;
        } else if (isCloseBracketClicked()) {
            return ShapesEnum.CLOSEBRACKET;
        } else if (isLessThanClicked()) {
            return ShapesEnum.LESSTHAN;
        } else if (isGreaterThanClicked()) {
            return ShapesEnum.GREATERTHAN;
        } else if (isAtTheRateClicked()) {
            return ShapesEnum.ATTHERATE;
        } else if (isTwoBarsClicked()) {
            return ShapesEnum.TWOBARS;
        } else if (isHyphenClicked()) {
            return ShapesEnum.HYPHEN;
        } else {
            return null;
        }
    }

}
