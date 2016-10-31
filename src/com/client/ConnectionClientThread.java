package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.server.Server;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.EllipseBuilder;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;

public class ConnectionClientThread extends Thread {
	private PrintStream socketPrintStream = null;
	private BufferedReader socketInputStream = null;
	private Socket clientSocket;

	@Override
	public void run() {
		try {// server bağlantısı olşturuldu
			clientSocket = new Socket("localhost", Server.port);
			System.out.println("Client bağlandı");
			socketPrintStream = new PrintStream(clientSocket.getOutputStream());
			socketInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			sendMessage("YENI");// yeni mesajını yolladık

		} catch (Exception e) {
			e.printStackTrace();
		}

		String inputLine;
		try {

			while ((inputLine = socketInputStream.readLine()) != null) {
				System.out.println("Client a mesaj gerldi :" + inputLine);
				// server dan gelen mesajın "BOYUTLAR" olup olmadığını kontrol
				// ediyoruz
				if (inputLine.startsWith("BOYUTLAR")) {
					inputLine = inputLine.substring("BOYUTLAR".length());
					inputLine = inputLine.replaceAll("\\s", "");
					String[] boyutlar = inputLine.split("[\\s,]");
					Client.screenWidth = Integer.parseInt(boyutlar[0]);
					Client.screenHeight = Integer.parseInt(boyutlar[1]);
					sendMessage("DOGRULA " + Client.screenWidth + ", " + Client.screenHeight);
				} else if (inputLine.startsWith("DRAW")) {
					if (Client.clientController == null) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								try {
									Client.client.showMainView();
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}
						});
					}
				} else {
					processShapeMessage(inputLine);

				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// çizilecek değerleri inte çevirdik ve valueList listesine ekledik
	public void processShapeMessage(String message) {
		String[] shapeInfoArray = message.split("[\\s,]");
		String[] values = Arrays.copyOfRange(shapeInfoArray, 1, shapeInfoArray.length);
		List<Integer> valueList = new ArrayList<Integer>();
		for (int i = 0; i < values.length; i++) {
			int valuesInt = Integer.parseInt(values[i]);
			valueList.add(valuesInt);

		}

		Shape shape = createShape(shapeInfoArray[0], valueList);
		drawShape(shape);
	}

	// Servera mesaj yollamak için kullanılır
	public void sendMessage(String message) {
		socketPrintStream.println(message);

	}

	// Çizilecek şekillerin boyutları verildi
	public Shape createShape(String shapeName, List<Integer> values) {
		Shape shape = null;
		// gelen şekil nokta ise shabe objesine at
		if (shapeName.equals("NOKTA")) {
			Line line = new Line();
			line.setStartX(values.get(0));
			line.setStartY(values.get(1));
			line.setEndX(values.get(0));
			line.setEndY(values.get(1));
			shape = line;

		} // gelen şekil dikdörtgen ise shabe objesine at
		if (shapeName.equals("DIKDORTGEN")) {
			Rectangle rectangle = new Rectangle();
			rectangle.setX(values.get(0));
			rectangle.setY(values.get(1));
			rectangle.setWidth(values.get(2));
			rectangle.setHeight(values.get(3));
			shape = rectangle;
		}
		// gelen şekil daire ise shabe objesine at
		if (shapeName.equals("DAIRE")) {

			Circle circle = new Circle();
			circle.setLayoutX(values.get(0));
			circle.setLayoutX(values.get(1));
			circle.setCenterX(values.get(0));
			circle.setCenterY(values.get(1));
			circle.setRadius(values.get(2));
			shape = circle;
		}
		// gelen şekil Dolu dikdörtgen ise shabe objesine at
		if (shapeName.equals("FILLEDRECTENGEL")) {
			Rectangle rect = new Rectangle();
			rect.setX(values.get(0));
			rect.setY(values.get(1));
			rect.setWidth(values.get(2));
			rect.setHeight(values.get(3));
			rect.setFill(Color.BLUE);

			shape = rect;
		}
		// iki çizgili daire çizilmiştir
		if (shapeName.equals("ELLIPSECIRCLE")) {
			Ellipse circle = EllipseBuilder.create()
		             .centerX(values.get(0))
		             .centerY(values.get(1))
		             .radiusX(values.get(2))
		             .radiusY(values.get(3))
		             .strokeWidth(values.get(4))
		             .stroke(Color.BLACK)
		             .fill(Color.BLUE)
		             .build();
			shape = circle;
		}
		
		if (shapeName.equals("ELLIPSE")) {
		Ellipse ellipse = EllipseBuilder.create()
	             .centerX(values.get(0))
	             .centerY(values.get(1))
	             .radiusX(values.get(2))
	             .radiusY(values.get(3))
	             .strokeWidth(values.get(4))
	             .stroke(Color.BLACK)
	             .fill(Color.WHITE)
	             .build();
		shape = ellipse;
		}
		return shape;

	}

	// şekiller listeye eklenecek eklenip çizilecek
	public void drawShape(Shape shape) {
		Client.shapeList.add(shape);
	}

}
