package com.server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static int port = 1500;

	public static void main(String[] args) {
		try {//server bağlantısı oluşturuldu
			ReadFile.loadProperties();
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("server başlatıldı " + Server.port + " port  üzerinde");
			while (true) {
				Socket socket = serverSocket.accept();
				ClientManager.addClient(socket);

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
