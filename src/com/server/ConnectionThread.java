package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import com.client.Client;
import com.server.shapes.Parameters;
import com.server.shapes.Shape;

public class ConnectionThread extends Thread {
	private PrintStream socketPrintStream = null;
	private BufferedReader socketInputStream = null;
	private Socket clientSocket;
	public static Parameters parameters;

	ConnectionThread(Socket socket) {
		this.clientSocket = socket;

	}

	@Override
	public void run() {
		try {
			socketPrintStream = new PrintStream(clientSocket.getOutputStream());
			socketInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String inputLine;
		try {// client tan gelen isteği bekliyoruz
			while ((inputLine = socketInputStream.readLine()) != null) {
				System.out.println("Server a mesaj gerldi :"+inputLine);
				if (inputLine.equals("YENI"))
				{
					sendMessage("BOYUTLAR " + parameters.getScreenWidth() + " ," + parameters.getScreenHeight());
				} // eğer doğrula mesajı geldiyse değerleri al yolla
				if (inputLine.startsWith("DOGRULA")) {
					inputLine = inputLine.substring("DOGRULA".length());
					inputLine = inputLine.replaceAll("\\s", "");
					String[] boyutlar = inputLine.split(",");
					int width = Integer.parseInt(boyutlar[0]);
					int height = Integer.parseInt(boyutlar[1]);
					if (width == parameters.getScreenWidth() && height == parameters.getScreenHeight()) {

						for (int i = 0; i < parameters.getShapeList().size(); i++) {

							Shape shape = parameters.getShapeList().get(i);
							String value = "";
							for (int j = 0; j < shape.getValues().length; j++) {
								value += shape.getValues()[j].toString() + " ";

							}
							sendMessage(shape.getShapeName() + " " + value);
						}

					}
					//şekil çizme metodunu çağırdık
					sendMessage("DRAW");

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
//Server a mesaj göndermemizi sağlar
	public void sendMessage(String message) {
		this.socketPrintStream.println(message);

	}

}