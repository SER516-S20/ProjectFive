/**
 * 
 * @author Sree Pradeep 
 * @since 05-03-2020
 * @version 1.0
 *
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Translate implements ActionListener{
	private String graph;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		graph = "digraph G"+ "{" + "\n";
		int index = Frame.tabbedPane.getSelectedIndex();
		DrawingCanvas canvas = Frame.canvasArray.get(index);
		translate(canvas);
	}
	
	public void translate(DrawingCanvas canvas) {
		ArrayList<Linkage[]> lineArray=canvas.lineArray;
		
		for(int i=0; i<lineArray.size(); i++) {
			
			if(lineArray.get(i)[1].shape.operator.equals("#")) {
				graph += '"' + lineArray.get(i)[0].shape.value + '"' + 
						 "->" + 
						 '"' + "start Tab"+ lineArray.get(i)[1].shape.value + '"' + "\n";
				int tabIndex = Integer.parseInt(lineArray.get(i)[1].shape.value);
				DrawingCanvas tab = Frame.canvasArray.get(tabIndex);
				String s = translateTab(tab, tabIndex);
				graph += s;
			}
			else if(lineArray.get(i)[0].shape.operator.equals("#")){
				graph += '"' + "end Tab"+ lineArray.get(i)[0].shape.value + '"' + 
						"->" +
						'"' + lineArray.get(i)[1].shape.value + '"' + "\n";
			}
			else {
				System.out.print("para");
				graph += '"' + lineArray.get(i)[0].shape.value + '"' + 
						 "->" + 
						 '"' + lineArray.get(i)[1].shape.value + '"' + "\n";
			}
			
		}
		
		graph = graph +'}';
		System.out.println(graph);
		 BufferedWriter bufferedWriter = null;
	        try {
	            File myFile = new File("./graph.txt");
	            // check if file exist, otherwise create the file before writing
	            if (!myFile.exists()) {
	                myFile.createNewFile();
	            }
	            Writer writer = new FileWriter(myFile);
	            bufferedWriter = new BufferedWriter(writer);
	            bufferedWriter.write(graph);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally{
	            try{
	                if(bufferedWriter != null) bufferedWriter.close();
	            } catch(Exception ex){
	                 
	            }
	        }
	        JOptionPane.showMessageDialog(null,graph,"translate text", 1 );
	}
	
	public String translateTab(DrawingCanvas canvas, int tabNo) {
		String subGraph = "subgraph "+ Integer.toString(tabNo) + " {"+ "\n" + 
							"style=filled;"+"\n"+
				            "color=lightgrey;"+"\n"+
				            "node [style=filled,color=white];"+"\n";
		ArrayList<Linkage[]> lineArray=canvas.lineArray;
		ArrayList<DrawShape> shapes = canvas.shapeObject; 
		DrawShape shapeStart = null;
		DrawShape shapeEnd = null;
		
		for(int i=0; i<shapes.size(); i++)
		{
			if(shapes.get(i).operator.equals("(")){
				shapeStart = shapes.get(i);
			}
			else if(shapes.get(i).operator.equals(")")) {
				shapeEnd = shapes.get(i);
			}
		}
		
		for(int i=0; i<lineArray.size(); i++) {
			
			if(lineArray.get(i)[1].shape.operator.equals("#")) {
				subGraph += '"' + lineArray.get(i)[0].shape.value + '"' + 
						 "->" + 
						 '"' + "start Tab"+ lineArray.get(i)[1].shape.value + '"' + "\n";
				int tabIndex = Integer.parseInt(lineArray.get(i)[1].shape.value);
				DrawingCanvas tab = Frame.canvasArray.get(tabIndex);
				String s = translateTab(tab, tabIndex);
				subGraph += s;
			}
			
			else if(lineArray.get(i)[0].shape.operator.equals("#")){
				subGraph += '"' + "end Tab"+ lineArray.get(i)[0].shape.value + '"' + 
						"->" +
						'"' + lineArray.get(i)[1].shape.value + '"' + "\n";
			}
			
			else {
				subGraph += '"' + lineArray.get(i)[0].shape.value + '"' + 
						 "->" + 
						 '"' + lineArray.get(i)[1].shape.value + '"' + "\n";
			}
		}
		subGraph = '"'+"start Tab"+Integer.toString(tabNo)+'"'+
					"->"+
					'"'+shapeStart.value+'"'+"\n" + subGraph;
		subGraph = subGraph +'}'+'\n';
		subGraph = subGraph + '"' + shapeEnd.value + '"' + "->"+
					'"' + "end Tab" + Integer.toString(tabNo) + '"' + "\n";

		return subGraph;
	}
}