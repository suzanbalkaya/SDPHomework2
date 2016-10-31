package com.server.shapes;

import java.util.ArrayList;
import java.util.List;

public class Parameters {//oluşturulacak panenin boyutlarını ve şekil listesini tutar
	
	private Integer screenWidth;
	private Integer screenHeight;
	//bütün şekil listelerimizi tutar
	private List<Shape> shapeList = new ArrayList<>();
	
	public Parameters(Integer screenWidth, Integer screenHeight) {
		super();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
	}
	
	public Integer getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(Integer screenWidth) {
		this.screenWidth = screenWidth;
	}

	public Integer getScreenHeight() {
		return screenHeight;
	}
	public void setScreenHeight(Integer screenHeight) {
		this.screenHeight = screenHeight;
	}
	public List<Shape> getShapeList() {
		return shapeList;
	}
	public void setShapeList(List<Shape> shapeList) {
		this.shapeList = shapeList;
	}
	
	
}
