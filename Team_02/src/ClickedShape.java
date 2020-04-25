/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * @author Rohit
 * @created on 02-17-2020 version 1.0
 */
public class ClickedShape {

	public static String shapeName;

	public String returnShape(String clickedShape) {

		try {
			shapeName = clickedShape;
			return clickedShape;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "";
	}
}