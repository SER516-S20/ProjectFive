import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Yijian Hu
 * @modified by Kairui Hsu
 */
public class FileManager {
	private List<Connection> connections;
	private Model model = new Model();
	private int sourceButton, destButton;
	
	public void save(File file) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootTab = doc.createElement("tabs");
			doc.appendChild(rootTab);
			for(int index = 0;index<Model.getTabs().size();index++) {
				String name = ((RightPanel)Model.getRightTabbedPane().getComponentAt(index)).getName();
				TabInfo tab = Model.getTabs().get(name);
				Element tabElement = doc.createElement("tab");
				tabElement.setAttribute("name", name);
				rootTab.appendChild(tabElement);
				Element rootShape = doc.createElement("shapes");
				tabElement.appendChild(rootShape);
				if(tab.getshapes() != null) {
					for(int key:tab.getshapes().keySet()) {
						Element shape = doc.createElement("shape");
						rootShape.appendChild(shape);
						shape.setAttribute("id",Integer.toString(key));
						ButtonBox theShape = tab.getshapes().get(key);
						Element type = doc.createElement("type");
						type.appendChild(doc.createTextNode(theShape.getToolTipText()));
						Element title = doc.createElement("title");
						title.appendChild(doc.createTextNode(theShape.getTitle()));
						Element position = doc.createElement("position");
						position.appendChild(doc.createTextNode((theShape.getLocation().x + theShape.getPreferredSize().width / 2) + "," + (theShape.getLocation().y + theShape.getHeight() / 2)));
						shape.appendChild(type);
						shape.appendChild(title);
						shape.appendChild(position);
					}
				}
				if(tab.getConnectionCollection() != null) {
					connections = tab.getConnectionCollection();
					for(int j = 0 ; j < this.connections.size(); j++) {
						Connection finishedconnection = connections.get(j);
						Element conn = doc.createElement("conn");
						tabElement.appendChild(conn);
						sourceButton = finishedconnection.getSourceButton();
						destButton = finishedconnection.getDestButton();
						Element sourceId = doc.createElement("sourceId");
						sourceId.appendChild(doc.createTextNode(Integer.toString(sourceButton)));
						Element destId = doc.createElement("destId");
						destId.appendChild(doc.createTextNode(Integer.toString(destButton)));
						Element position = doc.createElement("position");
						position.appendChild(doc.createTextNode((finishedconnection.getSourceX() + "," + 
																 finishedconnection.getSourceY() + "," +
																 finishedconnection.getDestX() + "," + 
																 finishedconnection.getDestY())));
						conn.appendChild(sourceId);
						conn.appendChild(destId);
						conn.appendChild(position);
					}
				}
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void open(File file) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(file);
			doc.getDocumentElement().normalize();
			RightTabbedPane pane = Model.getRightTabbedPane();
			Hashtable<String, TabInfo> tabs = Model.getTabs();
			tabs.clear();
			pane.removeAll();
			NodeList nodeList = doc.getElementsByTagName("tab");
			for(int i = 0;i < nodeList.getLength();i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {  
					Element tabElement = (Element) node;
					String name = node.getAttributes().getNamedItem("name").getNodeValue();
					RightPanel tab = new RightPanel(name);
					System.out.println(tab.getName());
					pane.addWorkingAreaTab(tab);
					TabInfo tabInfo = tabs.get(name);
					NodeList shapeList = tabElement.getElementsByTagName("shape");
					ShapeInfo[] shapes = new ShapeInfo[shapeList.getLength()];
					for(int j=0;j<shapeList.getLength();j++) {
						Node shapeNode = shapeList.item(j);
						if(shapeNode.getNodeType() == Node.ELEMENT_NODE) {
							Element shapeElement = (Element) shapeNode;
							int id = Integer.parseInt(shapeNode.getAttributes().getNamedItem("id").getNodeValue());
							String points[] = shapeElement.getElementsByTagName("position").item(0).getTextContent().split(",");
							Point position = new Point(Integer.parseInt(points[0]),Integer.parseInt(points[1]));
							String type = shapeElement.getElementsByTagName("type").item(0).getTextContent();
							String title = shapeElement.getElementsByTagName("title").item(0).getTextContent();
							shapes[j]=new ShapeInfo(id,type,title,position);
						}
					}
					System.out.println(shapes.length);
					for(ShapeInfo si: shapes)
						System.out.println(si.toString());
					tab.load(shapes);
					NodeList connList = tabElement.getElementsByTagName("conn");
					for(int j = 0;j < connList.getLength();j++) {
						Node conn = connList.item(j);
						if (conn.getNodeType() == Node.ELEMENT_NODE) {  
							Element connElement = (Element) conn;
							String points[] = connElement.getElementsByTagName("position").item(0).getTextContent().split(",");
							String sourceId = connElement.getElementsByTagName("sourceId").item(0).getTextContent();
							String destId = connElement.getElementsByTagName("destId").item(0).getTextContent();
							Connection newConn = new Connection();
							newConn.setSourceButton(Integer.parseInt(sourceId));
							newConn.setSourceX(Integer.parseInt(points[0]));
							newConn.setSourceY(Integer.parseInt(points[1]));
							newConn.setDestButton(Integer.parseInt(destId));
							newConn.setDestX(Integer.parseInt(points[2]));
							newConn.setDestY(Integer.parseInt(points[3]));
							tabInfo.addConnection(newConn);
						}
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
