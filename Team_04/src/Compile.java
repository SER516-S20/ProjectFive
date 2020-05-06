import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 * 
 * @author Sree Pradeep Kumar Relangi
 * @since 03-14-2020
 *
 */
public class Compile implements ActionListener {
	private Frame frame;
	private ArrayList<DrawShape> visited;

	public Compile(Frame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int index = Frame.tabbedPane.getSelectedIndex();
		DrawingCanvas canvas = Frame.canvasArray.get(index);
		CompileTab(canvas);
	}
	
	public Boolean CompileTab(DrawingCanvas canvas) {
		Boolean result = false;
		if (!isBracesPresent(canvas))
			JOptionPane.showMessageDialog(null, "Compilation Failed and error at braces", "Error", 1);
		else if (!allConnected(canvas))
			JOptionPane.showMessageDialog(null, "all shapes are not connected", "Error", 1);
		else if (!loop(canvas))
			JOptionPane.showMessageDialog(null, "there is no loop @ operator", "Error", 1);
		else if (!checkTabs(canvas))
			JOptionPane.showMessageDialog(null, "problem with other Tabs", "Error", 1);
		else {
			JOptionPane.showMessageDialog(null, "alright!!", "alright!!", 1);
			result = true;
		}
		return result;
	}

	public Boolean isBracesPresent(DrawingCanvas canvas) {
		int index = Frame.tabbedPane.getSelectedIndex();
		//DrawingCanvas canvas = Frame.canvasArray.get(index);
		int open = 0, closed = 0;
		ArrayList<DrawShape> shapes = canvas.shapeObject;
		for (int i = 0; i < shapes.size(); i++) {
			DrawShape currentShape = (DrawShape) shapes.get(i);
			if (currentShape.operator.equals("(")) {
				open++;
			}

			else if (currentShape.operator.equals(")")) {
				closed++;
			}
		}
		
		if (open == 1 && closed == 1)
			return true;

		return false;
	}
	
	public Boolean allConnected(DrawingCanvas canvas) {
		int index = Frame.tabbedPane.getSelectedIndex();
		//DrawingCanvas canvas = Frame.canvasArray.get(index);
		ArrayList<DrawShape> shapes = canvas.shapeObject;
		for (int i = 0; i < shapes.size(); i++) {
			for (Component component : shapes.get(i).inputLinkage.getComponents()) {
	            if (component instanceof Linkage) {
	                if (!((Linkage) component).connected)
	                	return false;
	            }
			}
			for (Component component : shapes.get(i).outputLinkage.getComponents()) {
	            if (component instanceof Linkage) {
	                if (!((Linkage) component).connected)
	                	return false;
	            }
			}
		}
		return true;
	}
	
	public Boolean loop(DrawingCanvas canvas) {
		int index = Frame.tabbedPane.getSelectedIndex();
		Boolean result = false;
		int count=0;
		//DrawingCanvas canvas = Frame.canvasArray.get(index);
		ArrayList<DrawShape> shapes = canvas.shapeObject;
		ArrayList<Linkage[]> lineArray = canvas.lineArray; 
		for(int i=0; i<shapes.size(); i++) {
			DrawShape shape = shapes.get(i);
			if(shape.operator.equals("@")) {
				count++;
				this.visited = new ArrayList<DrawShape>();
				for (Component component : shape.outputLinkage.getComponents()) {
		            if (component instanceof Linkage) {
		            	for(int j=0; j<lineArray.size(); j++) {
		            		if(lineArray.get(j)[0].equals((Linkage) component))
		            			result = result || checkCycle(shape,lineArray.get(j)[1].shape);
		            	}
		            }
				}
			}
		}
		if(count>0)
			return result;
		return true;
	}
	
	public Boolean checkCycle(DrawShape src, DrawShape inter) {
		visited.add(inter);
		Boolean result=false;
		//System.out.println(inter.operator);
		ArrayList<Linkage[]> lineArray = inter.canvas.lineArray;
		for (Component component : inter.outputLinkage.getComponents()) {
            if (component instanceof Linkage) {
            	for(int j=0; j<lineArray.size(); j++) {
            		if(lineArray.get(j)[0].equals((Linkage) component)) {
            			//System.out.println(lineArray.get(j)[1].shape.operator);
            			if(lineArray.get(j)[1].shape.equals(src)) {
            				return true;
            			}
            			else if(!visited.contains(lineArray.get(j)[1].shape))
            				result = result || checkCycle(src,lineArray.get(j)[1].shape);
            		}
            	}
            }
		}
		
		return result;
	}
	
	public Boolean checkTabs(DrawingCanvas canvas) {
		int index = Frame.tabbedPane.getSelectedIndex();
		Boolean result = false;
		int count=0;
		//DrawingCanvas canvas = Frame.canvasArray.get(index);
		ArrayList<DrawShape> shapes = canvas.shapeObject; 
		for(int i=0; i<shapes.size(); i++) {
			DrawShape shape = shapes.get(i);
			if(shape.operator.equals("#")) {
				count++;
				int value = Integer.parseInt(shape.value);
				DrawingCanvas canvasTab = Frame.canvasArray.get(value);
				result = result || CompileTab(canvasTab);
			}
		}
		if(count>0)
			return result;
		return true;
	}
	public static void addActionListener(Compile compile) {
		// TODO Auto-generated method stub
		
	}
}