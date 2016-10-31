package com.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.server.ConnectionThread;
import com.server.shapes.Parameters;
import com.server.shapes.Shape;

public class ReadFile {
	public static void loadProperties() {

		BufferedReader in = null;
		{//dosyadan okyr

			try {
				in = new BufferedReader(new FileReader("/home/suzan/clean_workspace/SDPHomework2/src/com/server/shapes.txt"));
				String read = null;
				//çizilecek pencerenn boyutlarını blrtyr
				String kordinat=in.readLine();
				kordinat.replaceAll("\\s,", "");
				String[] splitedK = kordinat.split(",");
				ConnectionThread.parameters = new Parameters(Integer.parseInt(splitedK[0].trim()), Integer.parseInt(splitedK[1].trim()));
				//dosyadan okuyup şekil isimlerini ve değerleri ayırıyor 
				while ((read = in.readLine()) != null) {
					String[] splited = read.split("[\\s,]");
					Integer[] values = new Integer[splited.length-1];
					for (int i = 1; i < splited.length; i++) {
						values[i-1] = Integer.parseInt(splited[i]);
					}
					Shape s = new Shape(splited[0], values);
					ConnectionThread.parameters.getShapeList().add(s);
					
				}
			} catch (IOException e) {
				System.out.println("problemle karşılaşıldı: " + e);
				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
	}
}