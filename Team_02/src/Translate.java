import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Rohit Kumar Singh
 * @modified on 04-26-2020
 * @version 1.0
 */
public class Translate {
	

	private static RightPanelDataProcessor processor = new RightPanelDataProcessor	();
	private static List<Icon> icons = new ArrayList<Icon>();
	private static HashMap<String, List<Icon>> iconMap; 
	
	private static HashMap<Integer,String> iconStore = new HashMap<Integer,String>();
	
	public static void update(RightPanelDataProcessor _processor) {
		processor = _processor;
		
	}
	
	
	public static void printTranslate() {
		List<Line> lines = processor.getLineList();
		HashMap<String, List<Icon>> icons = processor.getIconMap();
		iconMap = processor.getIconMap();
		
		storeAllPoints();
		
		System.out.println(iconMap.size());
		
		for(int i=0;i<lines.size();i++) {
			Dot s = lines.get(i).getStartDot();
			Dot e = lines.get(i).getEndDot();
			//System.out.println("Line: ("+s.getX()+","+s.getY()+") -->  ("+e.getX()+","+e.getY()+")");
			compute(s.getX(), e.getX());
		}
		
		
		
		
		
	}
	
	
	
	private static void storeAllPoints() {
		
		
		icons = new ArrayList<>(); 
		//iconStore = new HashMap<Integer,String>();
		
		for(String key: iconMap.keySet()) {
			
			System.out.println("Key: "+key);
			
			List<Icon> iconsStored = iconMap.get(key);
			
			for(Icon icon: iconsStored) {
				
				System.out.println("YOYO"+icon.getTraslateSymbol());
				
				iconStore.put(icon.getCenterX(), icon.getTraslateSymbol());
			}
			
			icons.addAll(iconsStored);
			
			
		}
		
		
		//displayAllPoints();
		
		
	}
		
	
	private static void displayAllPoints() {
		
		System.out.println("------------------------------------------");
		
//		for(Icon icon:icons) {
//			System.out.println(icon.getCenterX() +":"+ icon.getCenterY());
//		r
		
		for(Integer key: iconStore.keySet()) {
			System.out.println("Key: "+key);
		}
		
		
		System.out.println("------------------------------------------");
	}
	
	
	private static void compute(Integer sourceX, Integer targetX) {
		
	
		
		String s =iconStore.get(sourceX-187);
		String t = iconStore.get(targetX-7);
		System.out.println(s+ "is connected with"+ t);
		
	}

}
	
	