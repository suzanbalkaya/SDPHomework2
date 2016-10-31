package com.server.shapes;

public class Shape {//şekil isimlerini tutar ve değerlerini
	private String shapeName;
	private Integer[] values;
	
	
	public Shape(String shapeName, Integer... values) {
		this.shapeName = shapeName;
		this.values = values;
	}
	public String getShapeName() {
		return shapeName;
	}
	public void setShapeName(String shapeName) {
		this.shapeName = shapeName;
	}
	public Integer[] getValues() {
		return values;
	}
	public void setValues(Integer[] values) {
		this.values = values;
	}
	
	
}
